package com.tz.bms;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tz.bms.entity.User;
import com.tz.bms.user.dao.IUserDao;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:54:44
 */
public class TestUserDaoImpl {
	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	IUserDao userDao=(IUserDao) ac.getBean("userDaoImpl");
	@Test
	public void testselectUserByName(){
		User user=userDao.selectUserByName("admin");
		System.out.println(user);
	}
	
	@Test
	public void testinsertUser(){
		User user=new User();
		user.setAddress("苏州");
		user.setComparate("天智");
		user.setEmail("56783454@qq.com");
		user.setPassword("123");
		user.setTel("13787657589");
		user.setUsername("jack");
		boolean b=userDao.insertUser(user);
		System.out.println(b);
	}
}
