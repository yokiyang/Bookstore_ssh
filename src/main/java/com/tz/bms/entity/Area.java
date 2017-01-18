package com.tz.bms.entity;

/**
 * 
 * 本来用来演示 区 实体类
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:48:19
 */
public class Area {
	private int areaId;
	private String areaCode;
	private String areaName;
	
	public Area() {
		super();
	}
	
	public Area(int areaId, String areaCode, String areaName) {
		super();
		this.areaId = areaId;
		this.areaCode = areaCode;
		this.areaName = areaName;
	}
	
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Override
	public String toString() {
		return "Area [areaId=" + areaId + ", areaCode=" + areaCode
				+ ", areaName=" + areaName + "]";
	}
}
