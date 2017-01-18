package com.tz.bms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * 本来用来演示 图书类别实体类
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:49:11
 */
@Entity
@Table(name="ONLINE_CATEGORY")
public class Category {
	private long cateId;
	private String cateName;
	
	@Id
	@GeneratedValue
	public long getCateId() {
		return cateId;
	}
	public void setCateId(long cateId) {
		this.cateId = cateId;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
}
