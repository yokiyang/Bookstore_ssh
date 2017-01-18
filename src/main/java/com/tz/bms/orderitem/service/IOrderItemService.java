package com.tz.bms.orderitem.service;

import java.util.List;

import com.tz.bms.entity.OrderItem;

/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:52:18
 */
public interface IOrderItemService {
	
	/**
	 * 
	 * 保存一个订单明细对象
	 */
	public boolean saveOrderItem(OrderItem item);
	
	/**
	 * 
	 * 得到所有的订单明细对象
	 */
	public List<OrderItem> getAllItem();

}
