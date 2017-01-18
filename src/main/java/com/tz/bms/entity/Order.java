package com.tz.bms.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * 
 * 本来用来演示 订单实体类
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:49:38
 */
@Entity
@Table(name="ONLINE_ORDER")
public class Order {
	private Long orderId;
	private String orderNum;
	private Date createDate;
	private Set<OrderItem> items;
	private User user;
	private Address address;
	private String orderStatus;
	
	public Order() {
		super();
	}
	public Order(String orderNum, Date createDate, User user, Address address,
			String orderStatus) {
		super();
		this.orderNum = orderNum;
		this.createDate = createDate;
		this.user = user;
		this.address = address;
		this.orderStatus = orderStatus;
	}
	@Id
	@GeneratedValue
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@OneToMany(mappedBy="order")
	public Set<OrderItem> getItems() {
		return items;
	}
	public void setItems(Set<OrderItem> items) {
		for(OrderItem oi:items){
			oi.setOrder(this);
		}
		this.items = items;
	}
	@ManyToOne
	@JoinColumn(name="USER_ID")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	@JoinColumn(name="ADDRESS_ID")
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderNum=" + orderNum
				+ ", createDate=" + createDate + ", items=" + items + ", user="
				+ user + ", address=" + address + ", orderStatus="
				+ orderStatus + "]";
	}
	
}
