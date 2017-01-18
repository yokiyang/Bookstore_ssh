package com.tz.bms;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tz.bms.book.dao.IBookDao;
import com.tz.bms.entity.Book;
import com.tz.bms.entity.Order;
import com.tz.bms.entity.OrderItem;
import com.tz.bms.entity.Pageing;
import com.tz.bms.entity.User;
import com.tz.bms.order.dao.IOrderDao;
import com.tz.bms.orderitem.dao.IOrderItemDao;
import com.tz.bms.user.dao.IUserDao;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:54:34
 */
public class TestOrderItemDaoImpl {
	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");

	IOrderItemDao itemDao=(IOrderItemDao) ac.getBean("orderItemDaoImpl");
	IBookDao bookDao=(IBookDao) ac.getBean("bookDaoImpl");
	IOrderDao orderDao=(IOrderDao) ac.getBean("orderDaoImpl");
	IUserDao userDao=(IUserDao) ac.getBean("userDaoImpl");
	@Test
	public void testsaveOrderItem(){
		OrderItem item=new OrderItem();
		Book book=bookDao.selectBookById(1L);
		User user=userDao.selectUserByName("admin");
		item.setBook(book);
		item.setAllPrice(44.0);
		item.setCount(2);
		Pageing pageing=orderDao.selectOrderByPage(user, 1, 1);
		List<Order> orders=pageing.getOrders();
		item.setOrder(orders.get(0));
		boolean bool=itemDao.saveOrderItem(item);
		System.out.println(bool);
	}
}
