package com.sucheng.controller.view;
/**
 * 原材料视图控制层
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/rawMat")
public class RawMatView {

    @RequestMapping("add")
    public String add() {
        return "rawMat/add";
    }

    @RequestMapping("rawList")
    public String rawList() {
        return "rawMat/rawManage";
    }

    @RequestMapping("update")
    public String update() {
        return "rawMat/update";
    }
}
