package org.graviton.controller;

import org.graviton.controller.api.AbstractController;
import org.graviton.cookie.CookieManager;
import org.graviton.model.Server;
import org.graviton.service.NewsService;
import org.graviton.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class MainController extends AbstractController {
    @Autowired
    private CookieManager cookieManager;

    @Autowired
    private NewsService newsService;

    @Autowired
    private ServerService serverService;

    @RequestMapping("/")
    public String home(Model model, HttpSession session,
                       @CookieValue(value = "account", required = false) String account,
                       @CookieValue(value = "password", required = false) String password) {

        model.addAttribute("news", newsService.findAll());

        buildModel(model, session, account, password);
        return "index";
    }

    @RequestMapping("/logout/")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        cookieManager.clearCookie(request, response);
        session.setAttribute("account", null);
        return "redirect:/";
    }

    @RequestMapping(value = "/selectServer", method = RequestMethod.GET)
    public String selectServer(@RequestParam(value = "id", defaultValue = "-1") Integer id, HttpSession session,
                               HttpServletRequest request) throws IOException {

        Server server = serverService.findAll().stream().filter(s -> s.getId() == id).findAny().orElse(null);
        Server currentServer = (Server) session.getAttribute("server");

        if (server != null && (currentServer != null && currentServer.getId() != server.getId()))
            session.setAttribute("server", server);

        return "redirect:" + request.getHeader("Referer");

    }

    @RequestMapping("/refresh/")
    public String refresh() {

        return "redirect:/";
    }

    @Override
    protected byte index() {
        return 0;
    }

    @Override protected String name() {
        return "Acceuil";
    }
}
