package com.sucheng.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/stockLog")
public class StockOperatingView {

    @RequestMapping("stockList")
    public String stockList() {
        return "stockOperating/stockManage";
    }
}
