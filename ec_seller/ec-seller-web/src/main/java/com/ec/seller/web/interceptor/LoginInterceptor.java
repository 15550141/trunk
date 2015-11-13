package com.ec.seller.web.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exc)
			throws Exception {
		
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		//取cookie
		Cookie[] cookies =request.getCookies();
		boolean flag=false;
		if(cookies==null){
			//System.out.println("未登录！");
			response.sendRedirect("/");
			return false;
		}
		for(Cookie cookie : cookies){
			cookie.getName();
			cookie.getValue();
			if(cookie.getName().equals("loginname")&&cookie.getValue()!=null ){
				flag=true;
				//System.out.println("已登录！");
				return true;
			}
		}
		if(flag==false){
			//System.out.println("未登录！");
			response.sendRedirect("/");
		}
		return false;
	}

}
