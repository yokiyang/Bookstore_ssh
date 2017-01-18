package com.tz.bms.order.service;

import java.util.List;

import com.tz.bms.entity.Address;
import com.tz.bms.entity.Order;
import com.tz.bms.entity.Pageing;
import com.tz.bms.entity.User;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:51:30
 */
public interface IOrderService {
	/**
	 * 查询地址
	 * @param user
	 * @param size
	 * @param now
	 * @return
	 */
	public Pageing queryAllAddressByUser(User user,int size,int now);
	
	public Pageing quetyAddressByPageDefault(User user,int size,int now);

	
	public Address queryAddressById(long id);
	
	public void updateaddress(Address a);
	
	List<Address> getAddressByUser(User user);

	/**
	 * 完成地址信息的保存
	 * @param a 地址信息对象
	 */
	public boolean saveAddress(Address a);
	/**
	 * 保存订单对象
	 * @param o
	 */
	public boolean saveOrder(Order o);
	/**
	 * 根据用户查询订单
	 * @param user
	 * @param size
	 * @param now
	 * @return
	 */
	public Pageing queryOrderByPage(User user,int size,int now);
}
