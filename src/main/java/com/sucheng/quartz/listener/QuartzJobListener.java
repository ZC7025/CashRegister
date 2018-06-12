package com.sucheng.quartz.listener;

import com.sucheng.dto.TaskPlanDTO;
import com.sucheng.quartz.JobBaseUtil;
import com.sucheng.quartz.QuartzManager;
import com.sucheng.service.TaskPlanService;
import org.quartz.Job;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuartzJobListener implements ServletContextListener {
	
	@Override
	@SuppressWarnings("unchecked")
	public void contextInitialized(ServletContextEvent sce) {
		List<Map<String, Object>> listMap = new ArrayList<>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		TaskPlanService taskPlanService = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext())
				.getBean(TaskPlanService.class);
		List<Object> list = taskPlanService.listAll();
		if(!list.isEmpty()){
			List<TaskPlanDTO> taskPlanDTOList = new ArrayList<>();
			for (Object object : list) {
				taskPlanDTOList.add((TaskPlanDTO) object);
			}
			for (TaskPlanDTO taskPlanDTO : taskPlanDTOList) {
				if("Y".equals(taskPlanDTO.getIsStart()) && "Y".equals(taskPlanDTO.getStatus())) {
					map1 = new HashMap<String, Object>();
					map1.put("jobClass", "com.sucheng.quartz.CronJob");
					map1.put("jobName", taskPlanDTO.getJobName());
					map1.put("jobGroupName", JobBaseUtil.GROUP_NAME);
					map1.put("jobTime", taskPlanDTO.getCronExpression());
					listMap.add(map1);
				}
			}
		}else{
			map1 = new HashMap<String, Object>();
			map1.put("jobClass", "com.sucheng.quartz.CronJob");
			map1.put("jobName", "job1");
			map1.put("jobGroupName", "job1");
			map1.put("jobTime", "0/5 * * * * ? ");
			listMap.add(map1);
		}
		
		if(!listMap.isEmpty()){
			for (Map<String, Object> map : listMap) {
				try {
					QuartzManager.addJob(
							(Class<? extends Job>) (Class.forName((String) map1.get("jobClass"))
									.newInstance().getClass()),
							(String) map.get("jobName"), (String) map.get("jobGroupName"),
							(String) map.get("jobTime"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
}