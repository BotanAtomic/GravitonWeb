package org.graviton.controller;

import org.graviton.controller.api.AbstractController;
import org.graviton.view.ViewManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SupportController extends AbstractController {
    @RequestMapping("/support/")
    public String template(Model model, HttpSession session,
                           @CookieValue(value = "account", required = false) String account,
                           @CookieValue(value = "password", required = false) String password) {
        buildModel(model, session, account, password);
        return "support";
    }

    @Override
    protected byte index() {
        return 44;
    }

    @Override
    protected String name() {
        return "Support";
    }
}
