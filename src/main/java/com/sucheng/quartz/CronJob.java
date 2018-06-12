package com.sucheng.quartz;

import com.sucheng.service.TaskPlanService;
import com.sucheng.vo.TaskPlanVO;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Master on 2017/11/10.
 */
public class CronJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(CronJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

        ServletContext scontext = JobContext.getInstance().getContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(scontext);

        TaskPlanService taskPlanService = ctx.getBean(TaskPlanService.class);

        Trigger tg =  jobExecutionContext.getTrigger();
        TriggerKey tk = tg.getKey();
        String jobname = tk.getName();

        System.out.println(jobname+" 运行 ：" + dateFormat.format(new Date()));
        logger.error(jobname+" 运行 ：" + dateFormat.format(new Date()));

//        taskPlanService.updateActiveByName(jobname);
    }
}
