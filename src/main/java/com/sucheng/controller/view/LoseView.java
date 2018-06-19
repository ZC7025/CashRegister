package com.sucheng.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/lose")
public class LoseView {

    @RequestMapping("add")
    public String add() {
        return "lose/add";
    }

    @RequestMapping("loseList")
    public String loseList() {
        return "lose/loseManage";
    }

    @RequestMapping("update")
    public String update() {
        return "lose/update";
    }

}
