package com.tz.bms.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tz.bms.entity.User;
import com.tz.bms.user.dao.IUserDao;
import com.tz.bms.user.service.IUserService;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:53:56
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class UserServiceImpl implements IUserService{

	@Resource
	private IUserDao userDao;
	
	@Override
	public User loginUser(String name) {
		return userDao.selectUserByName(name);
	}

	@Override
	public boolean registerUser(User user) {
		return userDao.insertUser(user);
		
	}

}
