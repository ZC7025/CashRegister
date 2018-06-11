package com.sucheng.service.impl;

import com.sucheng.dao.TaskPlanDAO;
import com.sucheng.dos.TaskPlanDO;
import com.sucheng.dto.TaskPlanDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.TaskPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * TaskPlanServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-06-11<br/>
 *
 *
 * @version 1.0
 */
@Service(value = "taskPlanService")
public class TaskPlanServiceImpl extends AbstractBaseService implements TaskPlanService {

    private TaskPlanDAO taskPlanDAO;

    @Autowired
    public void setTaskPlanDAO(TaskPlanDAO taskPlanDAO) {
        super.setBaseDAO(taskPlanDAO);
        this.taskPlanDAO = taskPlanDAO;
    }

    @PostConstruct
    public void init() {
        super.init(TaskPlanDO.class, TaskPlanDTO.class);
    }

    @Override
    public void updateActiveStatus(Integer id, String status) {
        taskPlanDAO.updateActiveStatus(id, status);
    }
}
