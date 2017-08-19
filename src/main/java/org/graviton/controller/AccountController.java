package org.graviton.controller;

import org.graviton.controller.api.AbstractController;
import org.graviton.model.Account;
import org.graviton.model.Server;
import org.graviton.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;

@Controller
public class AccountController extends AbstractController {

    @Autowired
    private ServerService serverService;

    @RequestMapping("/account/")
    public String template(Model model, HttpSession session,
                           @CookieValue(value = "account", required = false) String accountName,
                           @CookieValue(value = "password", required = false) String password) {
        buildModel(model, session, accountName, password);

        Account account = (Account) session.getAttribute("account");

        if (account == null)
            return "redirect:/";

        String[] lastConnectionData = account.getLastConnection().split("~");

        model.addAttribute("account", account);
        model.addAttribute("password", account.getPassword().replaceAll(".", "*"));
        model.addAttribute("lastConnection", lastConnectionData.length > 0 ? lastConnectionData[2] + "/" + lastConnectionData[1] + "/" + lastConnectionData[0] + " à " + lastConnectionData[3] + "h" + lastConnectionData[4] : "jamais");
        model.addAttribute("servers", serverService.findAll().stream().collect(Collectors.toMap(Server::getId, Server::getKey)));
        return "account";
    }

    @Override
    protected byte index() {
        return 6;
    }

    @Override
    protected String name() {
        return "Compte";
    }
}
