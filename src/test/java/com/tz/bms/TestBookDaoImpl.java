package com.tz.bms;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tz.bms.book.dao.IBookDao;
import com.tz.bms.entity.Book;
import com.tz.bms.entity.Pageing;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:54:17
 */
public class TestBookDaoImpl {
	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	IBookDao bookDao=(IBookDao) ac.getBean("bookDaoImpl");
	@Test
	public void testSelectBookByCondition(){
		Pageing paging=bookDao.selectBookByCondition(1, 2, null);
		System.out.println(paging.getPageCount());
		List<Book> recommendbooks=paging.getBooks();
		for(Book book:recommendbooks){
			System.out.println(book);
		}
	}
	
	@Test
	public void testSelectBookById(){
		Book book=bookDao.selectBookById(1L);
		System.out.println(book);
	}
}
