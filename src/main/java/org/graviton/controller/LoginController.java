package org.graviton.controller;

import org.graviton.controller.api.AbstractController;
import org.graviton.cookie.CookieManager;
import org.graviton.model.Account;
import org.graviton.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController extends AbstractController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CookieManager cookieManager;

    @RequestMapping("/login/")
    public String connectionForm(Model model, HttpSession session) {
        parse(model, session);
        return "login";
    }

    @RequestMapping(value = "/login/", method = RequestMethod.POST)
    public String connect(Model model, HttpServletResponse response, HttpSession session,
                          @RequestParam("name") String name,
                          @RequestParam("password") String password,
                          @RequestParam(name = "remember", required = false) String remember) {
        Account account = accountService.findByName(name);

        if (account == null || !account.getPassword().equals(password)) {
            parse(model, session);
            putError(model, account);
            return "login";
        }

        session.setAttribute("account", account);

        if (remember != null && remember.equals("on")) {
            cookieManager.addCookie(response, "account", name);
            cookieManager.addCookie(response, "password", password);
        }

        return "redirect:/";
    }

    private void parse(Model model, HttpSession session) {
        buildModel(model, session, "", "");
    }

    private void putError(Model model, Account account) {
        model.addAttribute("error", account == null ? "Aucun compte ne correspond à ce nom d'utilisateur" : "Mot de passe incorrect");
        model.addAttribute(account == null ? "error.account" : "error.password", "true");
    }

    @Override
    protected byte index() {
        return -1;
    }

    @Override
    protected String name() {
        return "Connexion";
    }
}
