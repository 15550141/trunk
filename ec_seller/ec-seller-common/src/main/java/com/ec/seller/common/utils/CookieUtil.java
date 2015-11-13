package com.ec.seller.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
	
	public static String getLoginName(HttpServletRequest request){
		//取cookie
		Cookie[] cookies =request.getCookies();
		
		String loginName=null;
		if(cookies==null){
			return loginName;
		}
		for(Cookie cookie : cookies){
			cookie.getName();
			cookie.getValue();
			if(cookie.getName().equals("loginname")&&cookie.getValue()!=null ){
				loginName=cookie.getValue().substring(2);
				loginName=loginName.substring(0, loginName.indexOf("^"));
				
				}
			}
		return loginName;
		}
	
	public static Integer getUserId(HttpServletRequest request){
		//取cookie
		Cookie[] cookies =request.getCookies();
		
		Integer userId=null;
		if(cookies==null){
			return userId;
		}
		for(Cookie cookie : cookies){
			cookie.getName();
			cookie.getValue();
			if(cookie.getName().equals("loginname")&&cookie.getValue()!=null ){
				String loginName=cookie.getValue().substring(2);
				userId=Integer.parseInt(
						loginName.substring(loginName.indexOf("^")+1));
				
				}
			}
		return userId;
		}
	
	
}
