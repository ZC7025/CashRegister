package com.sucheng.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/product")
public class ProductView {

    @RequestMapping("add")
    public String add() {
        return "product/add";
    }

    @RequestMapping("proList")
    public String typeList() {
        return "product/proManage";
    }

    @RequestMapping("update")
    public String update() {
        return "product/update";
    }
}
