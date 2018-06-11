package com.sucheng.quartz.listener;

import com.sucheng.quartz.JobContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class JobContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletcontextevent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent servletcontextevent) {
		// TODO Auto-generated method stub
		JobContext.getInstance().setContext(servletcontextevent.getServletContext());
	}
}