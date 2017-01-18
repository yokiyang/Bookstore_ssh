package com.tz.bms.user.controller;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tz.bms.entity.User;
import com.tz.bms.user.service.IUserService;
/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:53:29
 */
@Controller
public class RegisterAction{
	@Resource
	private IUserService userService;
	
	@RequestMapping(value="/user/init")
	public String init(Model model){
		User user=new User();
		model.addAttribute("user", user);
		return "/jsp/user/register.jsp";
	}

	@RequestMapping("/user/register")
	public String register(Model model,@Validated User user,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			model.addAttribute("user", user);
			return "/jsp/user/register.jsp";
		}else{
			User user1=userService.loginUser(user.getUsername());
			model.addAttribute("user", user);
			if(user1==null){
					boolean bool=userService.registerUser(user);
					if(bool){
						return "redirect:/jsp/user/login.jsp";
					}else{
						return "redirect:/failure.html";
					}
				
			}else{
				return "redirect:/failure.html";
			}
		}
	}
}
