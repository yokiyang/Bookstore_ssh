package com.tz.bms;

import java.util.List;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tz.bms.entity.Address;
import com.tz.bms.entity.Order;
import com.tz.bms.entity.Pageing;
import com.tz.bms.entity.User;
import com.tz.bms.order.dao.IOrderDao;
import com.tz.bms.user.dao.IUserDao;
import com.tz.bms.util.DateUtil;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:54:26
 */
public class TestOrderDaoImpl {
	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");

	IOrderDao orderDao=(IOrderDao) ac.getBean("orderDaoImpl");
	IUserDao userDao=(IUserDao) ac.getBean("userDaoImpl");
	@Test
	public void testInsertAddress(){
		User user=userDao.selectUserByName("admin");
		Address a=new Address();
		a.setArea("苏州市");
		a.setDetailAddres("烽火路85号");
		a.setEmailCode("215000");
		a.setIsDefault("1");
		a.setReciver("ann");
		a.setTel("13456786758");
		a.setUser(user);
		boolean b=orderDao.insertAddress(a);
		System.out.println(b);
	}
	
	@Test
	public void testInsertOrder(){
		Order o=new Order("110001", DateUtil.parseString("yyyy-MM-dd", "2016-10-02"), 
				userDao.selectUserByName("admin"), orderDao.selectAddressById(42L), "未付款");
		boolean b=orderDao.insertOrder(o);
		System.out.println(b);
	}
	
	@Test
	public void testselectAddressByPage(){
		User user=userDao.selectUserByName("admin");
		Pageing pageing =orderDao.selectAddressByPage(user, 5, 1);
		List<Address> addresses=pageing.getAs();
		for(Address ad:addresses){
			System.out.println(ad);
		}
	}
	
	@Test
	public void testselectAddressByPageDefault(){
		User user=userDao.selectUserByName("admin");
		Pageing pageing =orderDao.selectAddressByPageDefault(user, 2, 1);
		List<Address> addresses=pageing.getAs();
		for(Address ad:addresses){
			System.out.println(ad);
		}
	}
	
	@Test
	public void testselectOrderByPage(){
		User user=userDao.selectUserByName("admin");
		Pageing pageing =orderDao.selectOrderByPage(user, 2, 1);
		List<Order> orders=pageing.getOrders();
		for(Order or:orders){
			System.out.println(or);
		}
	}
}
