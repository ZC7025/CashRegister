package com.sucheng.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/stock")
public class StockView {

    @RequestMapping("add")
    public String add() {
        return "stock/add";
    }

    @RequestMapping("stockList")
    public String stockList() {
        return "stock/stockManage";
    }

    @RequestMapping("update")
    public String update() {
        return "stock/update";
    }
}
