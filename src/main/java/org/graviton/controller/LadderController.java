package org.graviton.controller;

import org.graviton.controller.api.AbstractController;
import org.graviton.model.Player;
import org.graviton.model.Server;
import org.graviton.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
public class LadderController extends AbstractController {
    @Autowired
    private PlayerService playerService;

    @RequestMapping("/ladder/")
    public String template(Model model, HttpSession session,
                           @CookieValue(value = "account", required = false) String account,
                           @CookieValue(value = "password", required = false) String password) {
        buildModel(model, session, account, password);

        Server server = (Server) session.getAttribute("server");

        model.addAttribute("server", server.getKey());

        model.addAttribute("pvp_ladder", playerService.findAll().stream()
                .filter(p -> p.getServer() == server.getId())
                .sorted(Comparator.comparingInt(Player::getHonor).reversed()).limit(50).collect(Collectors.toList()));

        model.addAttribute("pvm_ladder", playerService.findAll().stream()
                .filter(p -> p.getServer() == server.getId())
                .sorted(Comparator.comparingLong(Player::getExperience).reversed()).limit(50).collect(Collectors.toList()));

        return "ladders";
    }

    @Override
    protected byte index() {
        return 1;
    }

    @Override
    protected String name() {
        return "Classements";
    }

}
