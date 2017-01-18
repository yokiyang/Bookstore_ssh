package com.tz.bms.book.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tz.bms.book.dao.IBookDao;
import com.tz.bms.entity.Book;
import com.tz.bms.entity.Category;
import com.tz.bms.entity.Pageing;

/**
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:46:49
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class BookDaoImpl implements IBookDao {

	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pageing selectBookByCondition(final int now, final int size,
			final String cate) {
		Pageing pageing = new Pageing();
		List<Book> books = null;
		if (cate != null) {
			books = (List<Book>) getSession()
					.createQuery(
							"select b from Book as b join b.category "
									+ "as c where c.cateName=:cname")
					.setString("cname", cate).setFirstResult((now - 1) * size)
					.setMaxResults(size).list();
		} else {
			books = (List<Book>) getSession().createQuery("from Book")
					.setFirstResult((now - 1) * size).setMaxResults(size)
					.list();
		}
		pageing.setPageNow(now);
		pageing.setPageSize(size);
		pageing.setBooks(books);
		int rowCount = 0;
		if (cate != null) {
			rowCount = (int) getRowCount(cate);
		} else {
			rowCount = (int) getRowCountAll();
		}
		pageing.setRowCount(rowCount);
		int pageCount = 0;
		if (rowCount % size == 0) {
			pageCount = rowCount / size;
		} else {
			pageCount = rowCount / size + 1;
		}
		pageing.setPageCount(pageCount);
		return pageing;
	}

	private long getRowCountAll() {
		return (long) getSession().createQuery("select count(*) from Book")
				.uniqueResult();
	}

	private long getRowCount(final String cate) {
		return (long) getSession()
				.createQuery(
						"select count(*) from Book as b join b.category as c where c.cateName=:cname")
				.setString("cname", cate).uniqueResult();
	}

	@Override
	public Book selectBookById(final long id) {
		return (Book) getSession().get(Book.class, id);
	}

	@Override
	public Category selectById(final long id) {
		return (Category) getSession().get(Category.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> selectAll() {
		return getSession().createQuery("from Category").list();
	}

}
