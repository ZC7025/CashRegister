package com.sucheng.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/supplier")
public class SupplierView {

    @RequestMapping("add")
    public String add() {
        return "supplier/add";
    }

    @RequestMapping("supList")
    public String supplierList() {
        return "supplier/supManage";
    }

    @RequestMapping("update")
    public String update() {
        return "supplier/update";
    }
}
