package com.sucheng.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/taste")
public class TasteView {

    @RequestMapping("add")
    public String add() {
        return "taste/add";
    }

    @RequestMapping("tasteList")
    public String tasteList() {
        return "taste/tasteManage";
    }

    @RequestMapping("update")
    public String update() {
        return "taste/update";
    }
}
