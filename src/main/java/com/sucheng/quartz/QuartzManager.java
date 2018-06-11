package com.sucheng.quartz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuartzManager {

	@Autowired
	private static Scheduler sched;

	/**
	 * 增加一个job
	 * 
	 * @param jobClass
	 *            任务实现类
	 * @param jobName
	 *            任务名称
	 * @param jobGroupName
	 *            任务组名
	 * @param jobTime
	 *            时间表达式 （如：0/5 * * * * ? ）
	 */
	public static void addJob(Class<? extends Job> jobClass, String jobName,
							  String jobGroupName, String jobTime) {
		try {
			// 创建jobDetail实例，绑定Job实现类
			// 指明job的名称，所在组的名称，以及绑定job类
			// 任务名称和组构成任务key
			JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName)
					.build();
			// 定义调度触发规则
			// 使用cornTrigger规则
			// 触发器key
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
					.startAt(DateBuilder.futureDate(1, IntervalUnit.SECOND))
					.withSchedule(CronScheduleBuilder.cronSchedule(jobTime)).startNow().build();
			// 把作业和触发器注册到任务调度中
			if (sched == null) {
				SchedulerFactory sf = new StdSchedulerFactory();
				sched = sf.getScheduler();
			}
			sched.scheduleJob(jobDetail, trigger);
			// 启动
			if (!sched.isShutdown()) {
				sched.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 增加一个job
	 * 
	 * @param jobClass
	 *            任务实现类
	 * @param jobName
	 *            任务名称
	 * @param jobGroupName
	 *            任务组名
	 * @param jobTime
	 *            时间表达式 (这是每隔多少秒为一次任务)
	 */
	public static void addJob(Class<? extends Job> jobClass, String jobName,
							  String jobGroupName, int jobTime) {
		addJob(jobClass, jobName, jobGroupName, jobTime, -1);
	}

	/**
	 * 增加一个job
	 * 
	 * @param jobClass
	 *            任务实现类
	 * @param jobName
	 *            任务名称
	 * @param jobGroupName
	 *            任务组名
	 * @param jobTime
	 *            时间表达式 (这是每隔多少秒为一次任务)
	 * @param jobTimes
	 *            运行的次数 （<0:表示不限次数）
	 */
	public static void addJob(Class<? extends Job> jobClass, String jobName,
							  String jobGroupName, int jobTime,
			int jobTimes) {
		try {
			// 任务名称和组构成任务key
			JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName)
					.build();
			// 使用simpleTrigger规则
			Trigger trigger = null;
			if (jobTimes < 0) {
				trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
						.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1)
								.withIntervalInSeconds(jobTime))
						.startNow().build();
			} else {
				trigger = TriggerBuilder
						.newTrigger().withIdentity(jobName, jobGroupName).withSchedule(SimpleScheduleBuilder
								.repeatSecondlyForever(1).withIntervalInSeconds(jobTime)
								.withRepeatCount(jobTimes))
						.startNow().build();
			}
			if (sched == null) {
				SchedulerFactory sf = new StdSchedulerFactory();
				sched = sf.getScheduler();
			}
			sched.scheduleJob(jobDetail, trigger);
			if (!sched.isShutdown()) {
				sched.start();
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改 一个job的 时间表达式
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param jobTime
	 */
	public static void updateJob(String jobName, String jobGroupName, String jobTime) {
		try {
			if (sched == null) {
				SchedulerFactory sf = new StdSchedulerFactory();
				sched = sf.getScheduler();
			}
			
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
			CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
					.withSchedule(CronScheduleBuilder.cronSchedule(jobTime)
							.withMisfireHandlingInstructionIgnoreMisfires()).build();
			//withMisfireHandlingInstructionIgnoreMisfires 在下一个时间点开始执行
			// 重启触发器
			sched.rescheduleJob(triggerKey, trigger);
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除任务一个job
	 * 
	 * @param jobName
	 *            任务名称
	 * @param jobGroupName
	 *            任务组名
	 */
	public static void deleteJob(String jobName, String jobGroupName) {
		try {
			if (sched == null) {
				SchedulerFactory sf = new StdSchedulerFactory();
				sched = sf.getScheduler();
			}
			
			JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
			List<? extends Trigger> triggers = sched.getTriggersOfJob(jobKey);
			if(!triggers.isEmpty()){
				for (Trigger trigger : triggers) {
					TriggerKey tk = trigger.getKey();
					TriggerState state = sched.getTriggerState(tk);
					if(state == TriggerState.NORMAL){
						sched.pauseJob(jobKey);
					}
				}
			}
			
			sched.deleteJob(new JobKey(jobName, jobGroupName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 暂停一个job
	 * 
	 * @param jobName
	 * @param jobGroupName
	 */
	public static void pauseJob(String jobName, String jobGroupName) {
		try {
			if (sched == null) {
				SchedulerFactory sf = new StdSchedulerFactory();
				sched = sf.getScheduler();
			}
			JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
			sched.pauseJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 恢复一个job
	 * 
	 * @param jobName
	 * @param jobGroupName
	 */
	public static void resumeJob(String jobName, String jobGroupName) {
		try {
			if (sched == null) {
				SchedulerFactory sf = new StdSchedulerFactory();
				sched = sf.getScheduler();
			}
			JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
			sched.resumeJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 立即执行一个job
	 * 
	 * @param jobName
	 * @param jobGroupName
	 */
	public static void runAJobNow(String jobName, String jobGroupName) {
		try {
			if (sched == null) {
				SchedulerFactory sf = new StdSchedulerFactory();
				sched = sf.getScheduler();
			}
			JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
			sched.triggerJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取所有计划中的任务列表
	 * 
	 * @return
	 */
	public static List<Map<String, Object>> queryAllJob() {
		List<Map<String, Object>> jobList = null;
		try {
			if (sched == null) {
				SchedulerFactory sf = new StdSchedulerFactory();
				sched = sf.getScheduler();
			}
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			Set<JobKey> jobKeys = sched.getJobKeys(matcher);
			jobList = new ArrayList<Map<String, Object>>();
			for (JobKey jobKey : jobKeys) {
				List<? extends Trigger> triggers = sched.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggers) {
					Map<String, Object> map = new HashMap<>();
					map.put("jobName", jobKey.getName());
					map.put("jobGroupName", jobKey.getGroup());
					map.put("description", "触发器:" + trigger.getKey());
					TriggerState triggerState = sched.getTriggerState(trigger.getKey());
					map.put("jobStatus", triggerState.name());
					if (trigger instanceof CronTrigger) {
						CronTrigger cronTrigger = (CronTrigger) trigger;
						String cronExpression = cronTrigger.getCronExpression();
						map.put("jobTime", cronExpression);
					}
					jobList.add(map);
				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return jobList;
	}

	/**
	 * 获取所有正在运行的job
	 * 
	 * @return
	 */
	public static List<Map<String, Object>> queryRunJon() {
		List<Map<String, Object>> jobList = null;
		try {
			if (sched == null) {
				SchedulerFactory sf = new StdSchedulerFactory();
				sched = sf.getScheduler();
			}
			List<JobExecutionContext> executingJobs = sched.getCurrentlyExecutingJobs();
			jobList = new ArrayList<Map<String, Object>>(executingJobs.size());
			for (JobExecutionContext executingJob : executingJobs) {
				Map<String, Object> map = new HashMap<String, Object>();
				JobDetail jobDetail = executingJob.getJobDetail();
				JobKey jobKey = jobDetail.getKey();
				Trigger trigger = executingJob.getTrigger();
				map.put("jobName", jobKey.getName());
				map.put("jobGroupName", jobKey.getGroup());
				map.put("description", "触发器:" + trigger.getKey());
				TriggerState triggerState = sched.getTriggerState(trigger.getKey());
				map.put("jobStatus", triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					map.put("jobTime", cronExpression);
				}
				jobList.add(map);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return jobList;
	}
}