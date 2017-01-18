package com.tz.bms.order.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tz.bms.entity.Address;
import com.tz.bms.entity.Area;
import com.tz.bms.entity.City;
import com.tz.bms.entity.Province;
import com.tz.bms.entity.User;
import com.tz.bms.order.service.IOrderService;
import com.tz.bms.ssxjl.service.ISSXJLService;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:51:01
 */
@Controller
public class SaveAddressAction{
	@Resource
	private IOrderService orderService;
	@Resource
	private ISSXJLService SSXJLService;
	
	@RequestMapping("/address/add")
	public String add(HttpServletRequest request,String province,String city,String area,String reciverName,String tel,String defaultAddress,String address_detail,String code){
		Province pro=SSXJLService.selectProvinceByCode(province);
		City c=SSXJLService.selectCityByCode(city);
		Area a=SSXJLService.selectAreaByCode(area);
		String province1=pro.getProvinceName();
		String city1=c.getCityName();
		String area1=a.getAreaName();
		defaultAddress=defaultAddress==null?"0":defaultAddress;
		User user=(User) request.getSession().getAttribute("user");
		List<Address> addrs = orderService.getAddressByUser(user);
		for(int i =0;i<=addrs.size()-1;i++){
			addrs.get(i).setIsDefault("0");
			orderService.updateaddress(addrs.get(i));
		}
		Address address=new Address();
		address.setArea(province1+","+city1+","+area1);
		address.setDetailAddres(address_detail);
		address.setEmailCode(code);
		address.setIsDefault(defaultAddress);
		address.setReciver(reciverName);
		address.setTel(tel);
		address.setUser(user);
		boolean bool=orderService.saveAddress(address);
		if(bool){
			return "redirect:/permission/order/1/3/balance.do";
		}else{
			return "redirect:/jsp/cart/cart.jsp";
		}
	}
}
