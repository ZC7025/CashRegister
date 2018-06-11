package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.dto.PagerDTO;
import com.sucheng.dto.TaskPlanDTO;
import com.sucheng.exception.ServiceException;
import com.sucheng.quartz.CronJob;
import com.sucheng.quartz.JobBaseUtil;
import com.sucheng.quartz.QuartzManager;
import com.sucheng.query.PageQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.query.TaskPlanQuery;
import com.sucheng.service.TaskPlanService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.PagerVO;
import com.sucheng.vo.TaskPlanVO;
import org.dozer.Mapper;
import org.quartz.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * TaskPlanController控制器类<br/>
 *
 * 创建于2018-06-11<br/>
 *
 *
 * @version 1.0
 */
@Controller
@RequestMapping("/data/taskPlan")
public class TaskPlanController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(TaskPlanController.class);

    private TaskPlanService taskPlanService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(TaskPlanVO taskPlanVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            taskPlanService.save(getBeanMapper().map(taskPlanVO, TaskPlanDTO.class));
            statusVO.okStatus(0, "添加成功");
            QuartzManager.addJob((Class<? extends Job>) Class.forName(taskPlanVO.getClassName()),
                    taskPlanVO.getJobName(), JobBaseUtil.GROUP_NAME, taskPlanVO.getCronExpression());
        } catch (ServiceException | ClassNotFoundException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
            e.printStackTrace();
        }
        return statusVO;
    }

    @GetMapping("remove/{id}")
    @ResponseBody
    public ControllerStatusVO removeById(@PathVariable("id") Integer id) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            taskPlanService.removeById(id);
            Object obj = taskPlanService.getById(id);
            if (obj != null) {
                TaskPlanVO taskPlanVO = getBeanMapper().map(obj, TaskPlanVO.class);
                QuartzManager.deleteJob(taskPlanVO.getJobName(), JobBaseUtil.GROUP_NAME);
            }
            statusVO.okStatus(0, "删除成功");
        } catch (ServiceException e) {
            logger.error("删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(TaskPlanVO taskPlanVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            taskPlanVO.setUpdateTime(Calendar.getInstance().getTime());
            taskPlanService.update(getBeanMapper().map(taskPlanVO, TaskPlanDTO.class));
            Object obj = taskPlanService.getById(taskPlanVO.getId());
            if (obj != null) {
                taskPlanVO = getBeanMapper().map(obj, TaskPlanVO.class);
            }
            statusVO.okStatus(0, "更新成功");
            QuartzManager.updateJob(taskPlanVO.getJobName(), JobBaseUtil.GROUP_NAME, taskPlanVO.getCronExpression());
        } catch (ServiceException e) {
            logger.error("更新失败：{}", e.getMessage());
            statusVO.errorStatus(500, "更新失败");
        }
        return statusVO;
    }

    @RequestMapping("active")
    @ResponseBody
    public ControllerStatusVO updateActiveStatus(Integer id, String status) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        TaskPlanVO taskPlanVO = null;
        try {
            taskPlanService.updateActiveStatus(id, status);
            Object obj = taskPlanService.getById(id);
            if (obj != null) {
                taskPlanVO = getBeanMapper().map(obj, TaskPlanVO.class);
            }
            if("Y".equals(status)) {
                if (taskPlanVO != null) {
                    QuartzManager.resumeJob(taskPlanVO.getJobName(), JobBaseUtil.GROUP_NAME);
                }
            } else {
                if (taskPlanVO != null) {
                    QuartzManager.pauseJob(taskPlanVO.getJobName(), JobBaseUtil.GROUP_NAME);
                }
            }
            statusVO.okStatus(0, "Y".equals(status) ? "启动成功" : "暂停成功");
        } catch (ServiceException e) {
            logger.error("启动或暂停失败：{}", e.getMessage());
            statusVO.errorStatus(500, "启动或暂停失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public TaskPlanVO getById(@PathVariable("id") Integer id) {
        TaskPlanVO taskPlanVO = new TaskPlanVO();
        try {
            Object obj = taskPlanService.getById(id);
            if (obj != null) {
                taskPlanVO = getBeanMapper().map(obj, TaskPlanVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return taskPlanVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<TaskPlanVO> listAll() {
        List<TaskPlanVO> taskPlanVOList = new ArrayList<>();
        try {
            List<Object> objectList = taskPlanService.listAll();
            taskPlanVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, TaskPlanVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return taskPlanVOList;
    }

    @RequestMapping("planList")
    @ResponseBody
    public PagerVO listPageByCondition(int page, int limit, TaskPlanQuery taskPlanQuery) {
        PageQuery pageQuery = new PageQuery(page, limit);
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = taskPlanService.listPageByCondition(pageQuery, taskPlanQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), TaskPlanQuery.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setTaskPlanService(TaskPlanService taskPlanService) {
        this.taskPlanService = taskPlanService;
    }
}
