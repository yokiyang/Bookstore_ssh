package com.tz.bms.orderitem.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tz.bms.entity.OrderItem;
import com.tz.bms.orderitem.dao.IOrderItemDao;

/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:51:58
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
@SuppressWarnings("unchecked")
public class OrderItemDaoImpl implements IOrderItemDao {

	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean saveOrderItem(final OrderItem item) {

		if (item != null) {
			getSession().save(item);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<OrderItem> getAllItem() {
		return getSession().createQuery("from OrderItem").list();
	}
}
