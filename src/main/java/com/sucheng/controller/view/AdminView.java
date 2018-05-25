package com.sucheng.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/admin")
public class AdminView {

    @RequestMapping("login")
    public String adminLogin() {
        return "admin/login";
    }

    @RequestMapping("home")
    public String adminHome() {
        return "base/home";
    }
}
