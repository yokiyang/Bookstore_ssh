package com.tz.bms.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyContextPathListner implements ServletContextListener {

	ServletContext context = null;

	//当应用装载时执行该方法
	@Override
	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();
		String base = context.getContextPath();
		context.setAttribute("base", base);
	}

	//当应用卸载时执行该方法
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		context.removeAttribute("base");
	}
}
