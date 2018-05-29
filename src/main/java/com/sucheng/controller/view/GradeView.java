package com.sucheng.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/grade")
public class GradeView {

    @RequestMapping("add")
    public String add() {
        return "grade/add";
    }

    @RequestMapping("gradeList")
    public String gradeList() {
        return "grade/gradeManage";
    }

    @RequestMapping("update")
    public String update() {
        return "grade/update";
    }
}
