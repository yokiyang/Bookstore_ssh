package com.tz.bms.user.dao.impl;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tz.bms.entity.User;
import com.tz.bms.user.dao.IUserDao;

/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:53:45
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class UserDaoImpl implements IUserDao {

	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public User selectUserByName(final String name) {
		return (User) getSession()
				.createQuery("from User u where u.username=:username")
				.setString("username", name).uniqueResult();
	}

	@Override
	public boolean insertUser(final User user) {
		try {
			if (user != null) {
				getSession().save(user);
				return true;
			} else {
				return false;
			}
		} catch (HibernateException e) {
			return false;
		}

	}

}
