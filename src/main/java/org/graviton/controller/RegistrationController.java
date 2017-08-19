package org.graviton.controller;

import org.graviton.controller.api.AbstractController;
import org.graviton.model.Account;
import org.graviton.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegistrationController extends AbstractController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/register/", method = RequestMethod.GET)
    public String template(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        return parse(model, account == null ? new Account() : account, session, account != null);
    }

    @RequestMapping(value = "/register/", method = RequestMethod.POST)
    public String registerAccount(Model model, @Valid Account account, BindingResult bindingResult, HttpSession session) {

        if (accountService.findByName(account.getName()) != null)
            bindingResult.rejectValue("name", "error.name", "Nom d'utilisateur indisponible");
        else if (!account.getConfirmedPassword().equals(account.getPassword()))
            bindingResult.rejectValue("password", "error.password", "Les mots de passes de correspondent pas");

        if (bindingResult.hasErrors()) {
            return parse(model, account, session, false);
        } else {
            accountService.saveAccount(account);
            return parse(model, account, session, true);
        }
    }

    private String parse(Model model, Account account, HttpSession session, boolean success) {
        if (success) {
            session.setAttribute("account", account);
            return "redirect:/";
        }

        model.addAttribute("account", account);
        buildModel(model, session, "", "");
        return "register";
    }


    @Override
    protected byte index() {
        return -1;
    }

    @Override
    protected String name() {
        return "Inscription";
    }
}
