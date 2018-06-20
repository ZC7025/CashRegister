package com.sucheng.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/storeOrder")
public class StoreOrderView {

    @RequestMapping("add")
    public String add() {
        return "storeOrder/add";
    }

    @RequestMapping("orderList")
    public String typeList() {
        return "storeOrder/orderManage";
    }

    @RequestMapping("update")
    public String update() {
        return "storeOrder/update";
    }
}
