package com.ec.api.common.utils;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieUtils {
	
	private static final Logger log = LoggerFactory.getLogger(CookieUtils.class);
	
	public static String getCookieValue(String name, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();

		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				try {
					return URLDecoder.decode(cookie.getValue(), "utf-8");
				} catch (Exception e) {
					
				}
			}
		}
		return null;
	}
	
	public static void clearCookie(String name, HttpServletResponse response) {
		Cookie cookie = new Cookie(name, "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		cookie.setDomain(".binfenguoyuan.cn");
		response.addCookie(cookie);
	}
	
	public static void addCookie(String name, String value, int expire, HttpServletResponse response) {
		Cookie cookie = new Cookie(name,  value);
		cookie.setMaxAge(expire);
		cookie.setPath("/");
		cookie.setDomain(".binfenguoyuan.cn");
		response.addCookie(cookie);
	}

	public static Integer getUid(HttpServletRequest request) {
		// 取cookie
		Cookie[] cookies = request.getCookies();
		
		if (cookies == null) {
			return null;
		}
		
		Integer userId = null;
		try{
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("uid")
						&& cookie.getValue() != null) {
					String cookieValue = cookie.getValue();
					String uidString = DESUtil.decrypt(cookieValue, BFConstants.loginCookieKey);
					if(StringUtils.isNotBlank(uidString)){
						userId = Integer.parseInt(uidString);
					}
				}
			}
		}catch (Exception e) {
			log.error("获取cookie中uid异常", e);
		}
		return userId;
	}
	
}
