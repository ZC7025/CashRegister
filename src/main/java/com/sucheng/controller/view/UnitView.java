package com.sucheng.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/unit")
public class UnitView {

    @RequestMapping("add")
    public String add() {
        return "unit/add";
    }

    @RequestMapping("unitList")
    public String unitList() {
        return "unit/unitManage";
    }

    @RequestMapping("update")
    public String update() {
        return "unit/update";
    }
}
