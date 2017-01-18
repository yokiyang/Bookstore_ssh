package com.tz.bms.ssxjl.dao;

import java.util.List;

import com.tz.bms.entity.Area;
import com.tz.bms.entity.City;
import com.tz.bms.entity.Province;

public interface ISSXJLDao {
	/**
	 * 查询所有省份
	 */
	List<Province> selectAllProvinces();

	/**
	 * 根据省份编号查询城市
	 */
	List<City> selectCityByProvinceCode(String proviceCode);

	/**
	 * 根据城市编号查询区域
	 */
	List<Area> selectAreaByCityCode(String cityCode);

	Province selectProvinceByCode(String provinceCode);

	City selectCityByCode(String cityCode);

	Area selectAreaByCode(String areaCode);
}
