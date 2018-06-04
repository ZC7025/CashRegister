package com.sucheng.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/gift")
public class GiftView {

    @RequestMapping("add")
    public String add() {
        return "gift/add";
    }

    @RequestMapping("giftList")
    public String giftList() {
        return "gift/giftManage";
    }

    @RequestMapping("update")
    public String update() {
        return "gift/update";
    }
}
