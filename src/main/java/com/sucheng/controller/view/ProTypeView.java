package com.sucheng.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/proType")
public class ProTypeView {

    @RequestMapping("add")
    public String add() {
        return "proType/add";
    }

    @RequestMapping("proTypeList")
    public String typeList() {
        return "proType/typeManage";
    }

    @RequestMapping("update")
    public String update() {
        return "proType/update";
    }
}
