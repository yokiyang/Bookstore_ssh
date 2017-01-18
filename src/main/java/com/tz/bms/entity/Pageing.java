package com.tz.bms.entity;

import java.util.List;
/**
 * 
 * 本来用来演示 分页实体类
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:50:04
 */
public class Pageing {
	private List<Book> books ;
	private int pageNow;
	private int pageSize;
	private int pageCount;
	private int rowCount;
	private List<Address> as ;
	private List<Order> orders ;
	
	
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public List<Address> getAs() {
		return as;
	}
	public void setAs(List<Address> as) {
		this.as = as;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
