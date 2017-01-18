package com.tz.bms.entity;

import java.util.List;

/**
 * 
 * 本来用来演示 省份实体类
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:50:22
 */
public class Province {
	private int provinceId;
	private String provinceCode;
	private String provinceName;

	//一对多关系
	private List<City> cities;

	public Province() {
		super();
	}

	public Province(int provinceId, String provinceCode, String provinceName) {
		super();
		this.provinceId = provinceId;
		this.provinceCode = provinceCode;
		this.provinceName = provinceName;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	@Override
	public String toString() {
		return "Province [provinceId=" + provinceId + ", provinceCode="
				+ provinceCode + ", provinceName=" + provinceName + "]";
	}
}
