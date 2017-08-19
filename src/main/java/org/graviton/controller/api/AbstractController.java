package org.graviton.controller.api;

import org.graviton.model.Account;
import org.graviton.model.Server;
import org.graviton.service.AccountService;
import org.graviton.service.ServerService;
import org.graviton.view.ViewManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public abstract class AbstractController {
    @Autowired
    protected ViewManager viewManager;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ServerService serverService;

    protected abstract byte index();

    protected abstract String name();

    protected void buildModel(Model model, HttpSession session, String cookieAccount, String cookiePassword) {
        Account account = (Account) session.getAttribute("account");
        Server server = (Server) session.getAttribute("server");

        if (account == null && cookieAccount != null && !cookieAccount.isEmpty()) {
            account = accountService.findByNameAndPassword(cookieAccount, cookiePassword);
            session.setAttribute("account", account);
        }

        if (server == null) {
            server = serverService.findAll().stream().findFirst().orElse(null);
            session.setAttribute("server", server);
        }

        model.addAttribute("name", name());
        model.addAttribute("top", viewManager.generateTop(account, server));
        model.addAttribute("headerContent", viewManager.generateHeader(index(), account != null));
        model.addAttribute("breadCrumbs", viewManager.generateBreadCrumbs(name()));
    }

}
