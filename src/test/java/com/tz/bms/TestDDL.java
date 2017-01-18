package com.tz.bms;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:54:21
 */
public class TestDDL {
	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	@Test
	public void testDDL(){
		LocalSessionFactoryBean lsfb=(LocalSessionFactoryBean) ac.getBean("&sessionFactory");
		Configuration cfg=lsfb.getConfiguration();
		SchemaExport export = new SchemaExport(cfg);
		export.create(true, true);
	}
}
