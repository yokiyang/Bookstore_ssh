package com.tz.bms.entity;

import java.util.HashSet;
import java.util.Set;
/**
 * 
 * 本来用来演示 购物车实体类
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:48:58
 */
public class Cart {
	private Set<OrderItem> items;
	private double allPrice;
	private int allcount;
	
	public Set<OrderItem> getItems() {
		if(items == null){
			items = new HashSet<OrderItem>();
		}
		return items;
	}
	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}
	public double getAllPrice() {
		return allPrice;
	}
	public void setAllPrice(double allPrice) {
		this.allPrice = allPrice;
	}
	
	
	public int getAllcount() {
		return allcount;
	}
	public void setAllcount(int allcount) {
		this.allcount = allcount;
	}
	/*
	 * 添加购物车
	 */
	public void addCart(OrderItem item){
		
		items = this.getItems();
		boolean flag  = true;//是否商品重复标记
		
		if(items.size() > 0){
			 //购物车中已经有商品
			 for(OrderItem i :items){
				 if(i.getBook().getBookId().equals(item.getBook().getBookId())){
					 //有重复 
					i.setCount(i.getCount()+1); 
					i.setAllPrice(i.getBook().getPrice()*i.getCount());
					flag = false; 
				 }
			 }
			 
			 if(flag){
				 // 处理不重复
				 items.add(item);
			 }
			 
		 }else {
			 //购物者没有商品
			 items.add(item);
		 }
		this.setAllcount(this.getAllcount()+1);
		this.setAllPrice(this.getAllPrice()+item.getBook().getPrice());
			
	}
	
	
	public void modifyCart(String id,String count){
		
		items = this.getItems();
		
		for(OrderItem i:items){
			if(i.getBook().getBookId().toString().equals(id)){
				this.setAllcount(this.getAllcount()-i.getCount());
				this.setAllPrice(this.getAllPrice()-i.getAllPrice());
				
				i.setCount(Integer.parseInt(count));
				i.setAllPrice(i.getBook().getPrice()*Integer.parseInt(count));
				
				this.setAllcount(this.getAllcount()+i.getCount());
				this.setAllPrice(this.getAllPrice()+i.getAllPrice());
				
				break;
			}
		}
	}
	
	
	public void removeCart(String ids){
		String[] arr = ids.substring(0, ids.length()-1).split(":");
		
		items = this.getItems();
		double prices = 0.0;
		int count = 0;
		Set<OrderItem> removes = new HashSet<OrderItem>();
		for(OrderItem i :items){
			for(String id :arr){
				if(i.getBook().getBookId().toString().equals(id)){
					removes.add(i);
					prices += i.getAllPrice();
					count += i.getCount();
				}
			}
		}
		
		items.removeAll(removes);
		this.setAllPrice(this.getAllPrice()-prices);
		this.setAllcount(this.getAllcount() - count);
		
	}
	
	//清空购物车
		public void clearCart(){
			items=this.getItems();
			items.removeAll(items);
			this.setAllcount(0);
			this.setAllPrice(0.0);
		}
}
