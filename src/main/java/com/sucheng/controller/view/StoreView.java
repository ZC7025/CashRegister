package com.sucheng.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/store")
public class StoreView {

    @RequestMapping("add")
    public String add() {
        return "store/add";
    }

    @RequestMapping("storeList")
    public String storeList() {
        return "store/storeManage";
    }

    @RequestMapping("update")
    public String update() {
        return "store/update";
    }

    @RequestMapping("home")
    public String home() {
        return "store/home";
    }

    @RequestMapping("updatePwd")
    public String updatePwd() {
        return "store/updatePwd";
    }
}
