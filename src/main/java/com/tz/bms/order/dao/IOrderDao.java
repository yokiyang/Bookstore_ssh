package com.tz.bms.order.dao;

import java.util.List;

import com.tz.bms.entity.Address;
import com.tz.bms.entity.Order;
import com.tz.bms.entity.Pageing;
import com.tz.bms.entity.User;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:51:18
 */
public interface IOrderDao {
	/**
	 * 保存地址
	 * @param a
	 */
	public boolean insertAddress(Address a);
	/**
	 * 根据id选择地址
	 * @param id
	 * @return
	 */
	public Address selectAddressById(long id);
	
	/**
	 * 更新地址
	 * @param a
	 */
	public void updateaddress(Address a);
	
	public List<Address> findByUserId(Long userId);
	/**
	 * 保存订单
	 * @param o
	 */
	public boolean insertOrder(Order o);
	/**
	 * 分页查询地址
	 * @param user
	 * @param size
	 * @param now
	 * @return
	 */
	public Pageing selectAddressByPage(User user,int size,int now);
	
	public Pageing selectAddressByPageDefault(User user,int size,int now);
	/**
	 * 分页查询订单
	 * @param user
	 * @param size
	 * @param now
	 * @return
	 */
	public Pageing selectOrderByPage(User user,int size,int now);
}
