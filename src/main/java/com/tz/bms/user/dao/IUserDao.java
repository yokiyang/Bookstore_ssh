package com.tz.bms.user.dao;

import com.tz.bms.entity.User;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:53:49
 */
public interface IUserDao {
	
	/**
	 * 根据用户名查找用户
	 * @param name
	 * @return
	 */
	public User selectUserByName(String name);
	
	/**
	 * 保存用户对象
	 * @param user
	 */
	public boolean insertUser(User user);
}
