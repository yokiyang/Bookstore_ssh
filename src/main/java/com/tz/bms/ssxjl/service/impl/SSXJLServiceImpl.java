package com.tz.bms.ssxjl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tz.bms.entity.Area;
import com.tz.bms.entity.City;
import com.tz.bms.entity.Province;
import com.tz.bms.ssxjl.dao.ISSXJLDao;
import com.tz.bms.ssxjl.dao.impl.SSXJLDaoImpl;
import com.tz.bms.ssxjl.service.ISSXJLService;

/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:52:53
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class SSXJLServiceImpl implements ISSXJLService{
	
	@Resource
	ISSXJLDao SSXJLDao=new SSXJLDaoImpl();
	
	@Override
	public List<Province> findAllProvinces() {
		return SSXJLDao.selectAllProvinces();
	}

	@Override
	public List<City> findCityByProvinceCode(String proviceCode) {
		return SSXJLDao.selectCityByProvinceCode(proviceCode);
	}

	@Override
	public List<Area> findAreaByCityCode(String cityCode) {
		return SSXJLDao.selectAreaByCityCode(cityCode);
	}

	@Override
	public Province selectProvinceByCode(String provinceCode) {
		return SSXJLDao.selectProvinceByCode(provinceCode);
	}

	@Override
	public City selectCityByCode(String cityCode) {
		return SSXJLDao.selectCityByCode(cityCode);
	}

	@Override
	public Area selectAreaByCode(String areaCode) {
		return SSXJLDao.selectAreaByCode(areaCode);
	}

}
