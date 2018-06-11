package com.sucheng.quartz;

public class JobBaseUtil {
	// group name
	public static final String GROUP_NAME = "CronJob";

	// 状态:禁用,并且已删除
	public static final Integer JOB_STATUS_DEL = 0;

	// 状态:启用,并且已开始
	public static final Integer JOB_STATUS_START = 1;

	// 状态:定时器已暂停
	public static final Integer JOB_STATUS_TIGGER_STOP = 2;
	
}
