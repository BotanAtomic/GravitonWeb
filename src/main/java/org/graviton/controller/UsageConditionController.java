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
public class UsageConditionController extends AbstractController {
    @RequestMapping("/cgu/")
    public String template(Model model, HttpSession session,
                           @CookieValue(value = "account", required = false) String account,
                           @CookieValue(value = "password", required = false) String password) {
        buildModel(model, session, account, password);
        return "cgu";
    }

    @Override
    protected byte index() {
        return -13;
    }

    @Override protected String name() {
        return "C.G.U";
    }

}
