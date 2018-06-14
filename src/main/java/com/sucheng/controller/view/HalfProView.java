package com.sucheng.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/halfPro")
public class HalfProView {

    @RequestMapping("add")
    public String add() {
        return "halfPro/add";
    }

    @RequestMapping("proList")
    public String typeList() {
        return "halfPro/proManage";
    }

    @RequestMapping("update")
    public String update() {
        return "halfPro/update";
    }
}
