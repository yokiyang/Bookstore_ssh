package com.tz.bms.orderitem.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tz.bms.entity.OrderItem;
import com.tz.bms.orderitem.dao.IOrderItemDao;
import com.tz.bms.orderitem.service.IOrderItemService;

/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:52:12
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class OrderItemServiceImpl implements IOrderItemService{
	@Resource
	private IOrderItemDao itemDao;

	@Override
	public boolean saveOrderItem(OrderItem item) {
		return itemDao.saveOrderItem(item);
		
	}

	@Override
	public List<OrderItem> getAllItem() {
		return itemDao.getAllItem();
	}

}
