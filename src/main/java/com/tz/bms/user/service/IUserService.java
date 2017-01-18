package com.tz.bms.user.service;

import com.tz.bms.entity.User;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:54:00
 */
public interface IUserService {

	/**
	 * 验证用户是否合法
	 * @param name
	 * @return
	 */
	public User loginUser(String name);
	
	/**
	 * 完成用户的注册功能
	 * @param user
	 */
	public boolean registerUser(User user);
}
