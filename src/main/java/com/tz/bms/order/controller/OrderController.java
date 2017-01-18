package com.tz.bms.order.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tz.bms.entity.Address;
import com.tz.bms.entity.Cart;
import com.tz.bms.entity.Order;
import com.tz.bms.entity.OrderItem;
import com.tz.bms.entity.Pageing;
import com.tz.bms.entity.User;
import com.tz.bms.order.service.IOrderService;
import com.tz.bms.orderitem.service.IOrderItemService;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:50:53
 */
@Controller
public class OrderController{
	@Resource
	private IOrderService orderService;
	@Resource
	private IOrderItemService itemService;
	@SuppressWarnings("unchecked")
	@RequestMapping("/permission/order/{pageNow}/{pageSize}/balance")
	public String balance(Model model,HttpServletRequest request,@PathVariable String pageNow,@PathVariable String pageSize){
		String ids=request.getParameter("ids");
		User user=(User) request.getSession().getAttribute("user");
		pageNow = pageNow == null ? "1" : pageNow;
        pageSize = pageSize == null ? "3" : pageSize;
        Pageing addressPageingDefault=orderService.quetyAddressByPageDefault(user, 1+Integer.parseInt(pageSize)-3, 1);
		Pageing addressPageing=orderService.queryAllAddressByUser(user, Integer.parseInt(pageSize),Integer.parseInt(pageNow));
		if(ids==null||ids.trim().length()==0){
			ids=(String) request.getSession().getAttribute("ids");
		}else{
			request.getSession().setAttribute("ids", ids);
		}
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		Set<OrderItem> itemsset = cart.getItems();
		List<OrderItem> items=new ArrayList<OrderItem>(itemsset);
		List<OrderItem> newItems = (List<OrderItem>) request.getSession().getAttribute("newItems");
		double itemAllPrice=0.0;
		if(null==newItems){
			newItems=new ArrayList<>();
			String[] idsarr=ids.split(":");
			for(int i=items.size()-1;i>=0;i--){
				for(String s:idsarr){
					if(s.equals(String.valueOf(items.get(i).getBook().getBookId()))){
						OrderItem item=new OrderItem();
						item.setBook(items.get(i).getBook());
						item.setCount(items.get(i).getCount());
						item.setAllPrice(items.get(i).getAllPrice());
						newItems.add(item);
						double price = items.get(i).getAllPrice();
						itemAllPrice +=price;
						break;
					}
				}
			}
		}else{
			request.getSession().removeAttribute("newItems");
		}
		request.getSession().setAttribute("cart", cart);
		model.addAttribute("addressPageingDefault", addressPageingDefault);
		model.addAttribute("addressPageing", addressPageing);
		request.getSession().setAttribute("newItems", newItems);
		if(itemAllPrice!=0.0){
			request.getSession().setAttribute("itemAllPrice", itemAllPrice);
		}
		return "/jsp/order/confirm_order.jsp";
	}
	
	@RequestMapping("/permission/order/{pageNow}/{pageSize}/orderList")
	public String orderList(Model model,@PathVariable String pageNow,@PathVariable String pageSize,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		
		pageNow=pageNow==null?"1":pageNow;
		pageSize=pageSize==null?"8":pageSize;
		
		Pageing paging=orderService.queryOrderByPage(user, Integer.parseInt(pageSize), Integer.parseInt(pageNow));
		model.addAttribute("paging", paging);
		return "/jsp/order/orderlist.jsp";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/order/{addressId}/{itemAllPrice}/save")
	public String save(Model model,@PathVariable String addressId,@PathVariable String itemAllPrice,HttpServletRequest request){
		Address address=orderService.queryAddressById(Long.parseLong(addressId));
		Order order=new Order();
		User user=(User) request.getSession().getAttribute("user");
		List<OrderItem> newItemslist=(List<OrderItem>) request.getSession().getAttribute("newItems");
		for(OrderItem o:newItemslist){
			System.out.println(o);
		}
		Set<OrderItem> newItems=new HashSet<OrderItem>(newItemslist);
		order.setOrderNum("TZ"+new Date().getTime());
		order.setCreateDate(new Date());
		order.setItems(newItems);
		order.setUser(user);
		order.setAddress(address);
		order.setOrderStatus("交易成功");
		boolean b=orderService.saveOrder(order);
		
		String ids="";
		for(OrderItem item:newItems){
			item.setOrder(order);
			itemService.saveOrderItem(item);
			ids+=item.getBook().getBookId()+":";
		}
		request.getSession().removeAttribute("newItems");
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		cart.removeCart(ids);
		request.getSession().setAttribute("cart", cart);
		model.addAttribute("itemAllPrice", itemAllPrice);
		if(b){
			return "redirect:/permission/order/1/8/orderList.do";
		}else{
			return "redirect:/failure.html";
		}
	}
}