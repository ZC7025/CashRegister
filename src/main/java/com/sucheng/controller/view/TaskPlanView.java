package com.sucheng.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/taskPlan")
public class TaskPlanView {

    @RequestMapping("add")
    public String add() {
        return "taskPlan/add";
    }

    @RequestMapping("taskList")
    public String tasteList() {
        return "taskPlan/taskManage";
    }

    @RequestMapping("update")
    public String update() {
        return "taskPlan/update";
    }
}
