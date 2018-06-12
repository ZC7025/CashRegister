package com.sucheng.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/payWay")
public class PayWayView {

    @RequestMapping("add")
    public String add() {
        return "payWay/add";
    }

    @RequestMapping("payWayList")
    public String typeList() {
        return "payWay/typeManage";
    }

    @RequestMapping("update")
    public String update() {
        return "payWay/update";
    }
}
