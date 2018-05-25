package com.sucheng.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/industry")
public class IndustryView {

    @RequestMapping("add")
    public String add() {
        return "industry/add";
    }

    @RequestMapping("typeList")
    public String typeList() {
        return "industry/typeManage";
    }

    @RequestMapping("update")
    public String update() {
        return "industry/update";
    }
}
