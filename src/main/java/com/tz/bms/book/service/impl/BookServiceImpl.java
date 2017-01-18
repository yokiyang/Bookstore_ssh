package com.tz.bms.book.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tz.bms.book.dao.IBookDao;
import com.tz.bms.book.service.IBookService;
import com.tz.bms.entity.Book;
import com.tz.bms.entity.Category;
import com.tz.bms.entity.Pageing;
/**
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:47:15
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class BookServiceImpl implements IBookService {
	@Resource
	private IBookDao bookDao;

	@Override
	public Pageing queryBookByCondition(int now, int size, String cate) {
		return bookDao.selectBookByCondition(now, size, cate);
	}

	@Override
	public Book queryBookById(long id) {
		return bookDao.selectBookById(id);
	}

	@Override
	public Category querytById(long id) {
		return bookDao.selectById(id);
	}

	@Override
	public List<Category> selectAllCategory() {
		return bookDao.selectAll();
	}

}
