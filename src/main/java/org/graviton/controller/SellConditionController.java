package org.graviton.controller;

import org.graviton.controller.api.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SellConditionController extends AbstractController {

    @RequestMapping("/cgv/")
    public String template(Model model, HttpSession session,
                           @CookieValue(value = "account", required = false) String account,
                           @CookieValue(value = "password", required = false) String password) {
        buildModel(model, session, account, password);
        return "cgv";
    }

    @Override
    protected byte index() {
        return -1;
    }

    @Override
    protected String name() {
        return "C.G.V";
    }
}
