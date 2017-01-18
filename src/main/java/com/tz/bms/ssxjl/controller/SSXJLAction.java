package com.tz.bms.ssxjl.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tz.bms.entity.Area;
import com.tz.bms.entity.City;
import com.tz.bms.entity.Province;
import com.tz.bms.ssxjl.service.ISSXJLService;

@Controller
@RequestMapping("/ssxjl")
public class SSXJLAction{
	
	@Resource
	private ISSXJLService SSXJLService;
	
	@RequestMapping("/province")
	@ResponseBody
	public List<Province> getProvinces(){
		List<Province> provinces=SSXJLService.findAllProvinces();
		return provinces;
	}

	@RequestMapping("/city")
	@ResponseBody
	public List<City> getCities(String provinceCode){
		List<City> cities=SSXJLService.findCityByProvinceCode(provinceCode);
		return cities;
	}
	@RequestMapping("/area")
	@ResponseBody
	public List<Area> getAreas(String cityCode){
		List<Area> areas=SSXJLService.findAreaByCityCode(cityCode);
		return areas;
	}
}
