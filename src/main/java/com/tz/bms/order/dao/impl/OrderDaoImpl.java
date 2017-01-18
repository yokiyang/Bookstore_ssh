package com.tz.bms.order.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tz.bms.entity.Address;
import com.tz.bms.entity.Order;
import com.tz.bms.entity.Pageing;
import com.tz.bms.entity.User;
import com.tz.bms.order.dao.IOrderDao;

/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:51:13
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class OrderDaoImpl implements IOrderDao {
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean insertAddress(final Address a) {
		try {
			if (a != null) {
				getSession().save(a);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Address selectAddressById(final long id) {
		return (Address) getSession().get(Address.class, id);
	}

	@Override
	public boolean insertOrder(final Order o) {
		try {
			if (o != null) {
				getSession().save(o);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pageing selectAddressByPageDefault(final User user, final int size,
			final int now) {
		Pageing pageing = new Pageing();
		List<Address> addresses = null;
		if (user != null) {
			addresses = getSession()
					.createQuery(
							"select a from Address as a join a.user as u where u.username=:username and a.isDefault like:d ")
					.setString("username", user.getUsername())
					.setString("d", "1").setFirstResult((now - 1) * size)
					.setMaxResults(size).list();
		}
		pageing.setPageNow(now);
		pageing.setPageSize(size);
		pageing.setAs(addresses);
		int rowCount = (int) getRowCountDefault(user);
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

	private long getRowCountDefault(final User user) {
		return (long) getSession()
				.createQuery(
						"select count(*) from Address as a join a.user as u where u.username=:username and a.isDefault like :d")
				.setString("username", user.getUsername()).setString("d", "1")
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pageing selectAddressByPage(final User user, final int size,
			final int now) {
		Pageing pageing = new Pageing();
		List<Address> addresses = null;
		if (user != null) {
			addresses = (List<Address>) getSession()
					.createQuery(
							"select a from Address as a join a.user as u where u.username=:username and a.isDefault<>'1'")
					.setString("username", user.getUsername())
					.setFirstResult((now - 1) * size).setMaxResults(size)
					.list();
		}
		pageing.setPageNow(now);
		pageing.setPageSize(size);
		pageing.setAs(addresses);
		int rowCount = (int) getRowCount(user);
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

	private long getRowCount(final User user) {
		return (long) getSession()
				.createQuery(
						"select count(*) from Address as a join a.user as u where u.username=:username and a.isDefault<>'1'")
				.setString("username", user.getUsername()).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pageing selectOrderByPage(final User user, final int size,
			final int now) {
		Pageing pageing = new Pageing();
		List<Order> orders = null;
		if (user != null) {
			orders = (List<Order>) getSession()
					.createQuery(
							"select o from Order as o join o.user as u where u.username=:username")
					.setString("username", user.getUsername())
					.setFirstResult((now - 1) * size).setMaxResults(size)
					.list();
		}
		pageing.setPageNow(now);
		pageing.setPageSize(size);
		pageing.setOrders(orders);
		int rowCount = (int) getRowCountOrder(user);
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

	private long getRowCountOrder(final User user) {
		return (long) getSession()
				.createQuery(
						"select count(*) from Order as o join o.user as u where u.username=:username")
				.setString("username", user.getUsername()).uniqueResult();
	}

	@Override
	public void updateaddress(final Address a) {
		getSession().update(a);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> findByUserId(final Long userId) {
		return getSession()
				.createQuery("from Address as a where a.user.userId=?")
				.setLong(0, userId).list();
	}

}
