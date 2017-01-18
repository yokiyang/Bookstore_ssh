package com.tz.bms.entity;

import java.util.List;
/**
 * 
 * 本来用来演示 城市实体类
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:49:27
 */
public class City {
	private int cityId;
	private String cityCode;
	private String cityName;

	//一对多关系
	private List<Area> areas;

	public City(int cityId, String cityCode, String cityName) {
		super();
		this.cityId = cityId;
		this.cityCode = cityCode;
		this.cityName = cityName;
	}

	public City() {
		super();
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityCode=" + cityCode
				+ ", cityName=" + cityName + ", areas=" + areas + "]";
	}
}