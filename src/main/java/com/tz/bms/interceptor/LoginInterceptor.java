package com.tz.bms.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器
 * 本来用来演示
 *@author 杨倩Yoki
 *@2017-1-12 @上午10:12:53
 */
public class LoginInterceptor implements HandlerInterceptor{

	private List<String> allowedPath;
	public void setAllowedPath(List<String> allowedPath) {
		this.allowedPath = allowedPath;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url=request.getRequestURL().toString();
		
		//判断session中是否有用户信息
		Object user=request.getSession().getAttribute("user");
		if(user!=null){
			return true;
		}
		response.sendRedirect(request.getContextPath()+"/jsp/user/login.jsp");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
