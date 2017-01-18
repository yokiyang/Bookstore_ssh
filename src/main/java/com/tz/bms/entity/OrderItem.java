package com.tz.bms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * 
 * 本来用来演示 购买物品实体类
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:49:47
 */
@Entity
@Table(name="ONLINE_ITEM")
public class OrderItem {
	private Long itemId;
	private Book book;
	private int count;
	private double allPrice;
	private Order order;
	
	@Id
	@GeneratedValue
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	@OneToOne
	@JoinColumn(name="BOOK_ID")
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAllPrice() {
		return allPrice;
	}
	public void setAllPrice(double allPrice) {
		this.allPrice = allPrice;
	}
	@ManyToOne
	@JoinColumn(name="ORDER_ID")
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "OrderItem [itemId=" + itemId + ", book=" + book + ", count="
				+ count + ", allPrice=" + allPrice + ", order=" + order + "]";
	}
	
}

