package com.sucheng.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/employee")
public class EmployeeView {

    @RequestMapping("add")
    public String add() {
        return "employee/add";
    }

    @RequestMapping("empList")
    public String empList() {
        return "employee/empManage";
    }

    @RequestMapping("update")
    public String update() {
        return "employee/update";
    }
}
