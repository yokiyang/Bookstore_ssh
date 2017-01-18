package com.tz.bms;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tz.bms.entity.Area;
import com.tz.bms.entity.City;
import com.tz.bms.entity.Province;
import com.tz.bms.ssxjl.dao.ISSXJLDao;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:54:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:application.xml")
public class TestSSXJLDaoImpl {
	@Resource
	private ISSXJLDao SSXJLDao;
	
	@Test
	public void testselectAllProvinces(){
		List<Province> provinces=SSXJLDao.selectAllProvinces();
		for(Province pro:provinces){
			System.out.println(pro);
		}
	}
	@Test
	public void testselectCityByProvincecode(){
		List<City> listCity=SSXJLDao.selectCityByProvinceCode("110000");
		for(City city:listCity){
			System.out.println(city);
		}
	}
	
	@Test
	public void testselectAreaBycitycode(){
		List<Area> listArea=SSXJLDao.selectAreaByCityCode("320600");
		for(Area area:listArea){
			System.out.println(area);
		}
	}
	
	@Test
	public void testSelectProvince(){
		Province province =SSXJLDao.selectProvinceByCode("110000");
		System.out.println(province);
	}
	@Test
	public void testSelectCity(){
		City city=SSXJLDao.selectCityByCode("320600");
		System.out.println(city);
	}
	@Test
	public void testSelectArea(){
		Area area=SSXJLDao.selectAreaByCode("320682");
		System.out.println(area);
	}
}
