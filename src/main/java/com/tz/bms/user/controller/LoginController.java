package com.tz.bms.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tz.bms.entity.User;
import com.tz.bms.user.service.IUserService;

/**
 * 
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:53:18
 */
@Controller
@RequestMapping("/user")
public class LoginController{

	@Resource
	private IUserService userService;
	
	@RequestMapping(value = "/login")
	public String login(String username, String password,
			String noLogin, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		if ((username != null && username.length() != 0)
				&& (password != null && password.length() != 0)) {
			Cookie cookie = null;
			if (noLogin != null) {
				//说明要七天免登陆
				cookie = new Cookie("userInfo", URLEncoder.encode(username
						+ ":" + password, "UTF-8"));
				cookie.setMaxAge(7 * 24 * 60 * 60);
				//设置Cookie作用域
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			
			User user=userService.loginUser(username);
				if (user != null) {
					if(user.getPassword().equals(password)){
						//把用户名放入到共享数据范围
						request.getSession().setAttribute("user", user);
						//设置Session的最长访问时间间隔
						request.getSession().setMaxInactiveInterval(60 * 15);
						//说明登陆成功
						return "redirect:/book/IndexAction.do";
					}else{
						return "redirect:/jsp/user/login.jsp?info=1";
					}
				} else {
					return "redirect:/jsp/user/login.jsp?info=0";
				}
		}else{
			return "redirect:/jsp/user/login.jsp";
		}
	}
	
	@RequestMapping(value="/exit",method=RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/index.jsp";
	}
}
