package com.ec.api.web.controller;

import com.ec.api.common.utils.BFConstants;
import com.ec.api.common.utils.CookieUtils;
import com.ec.api.common.utils.DESUtil;
import com.ec.api.domain.UserInfo;
import com.ec.api.service.AccessTokenService;
import com.ec.api.web.base.BaseController;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Controller
@RequestMapping("/oauth")
public class OauthController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(OauthController.class);
	
	private AccessTokenService accessTokenService;
	
	@RequestMapping(value="login", method={RequestMethod.GET, RequestMethod.POST})
	public void login(String rurl, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		
		StringBuilder url = new StringBuilder();
		
		url.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
		url.append(BFConstants.appId);
		url.append("&redirect_uri=");
		url.append("http://www.binfenguoyuan.cn/oauth/callback");
		url.append("&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		
		
		try {
			Cookie cookie = new Cookie("rurl", URLEncoder.encode(rurl, "utf-8"));
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			cookie.setDomain(".binfenguoyuan.cn");
			response.addCookie(cookie);
			response.sendRedirect(url.toString());
		} catch (Exception e) {
			log.error("", e);
		}
		
	}
	
	@RequestMapping(value="out", method={RequestMethod.GET, RequestMethod.POST})
	public void out(String rurl, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		try {
			CookieUtils.clearCookie("rurl", response);
			CookieUtils.clearCookie("uid", response);
			response.sendRedirect("http://www.binfenguoyuan.cn");
		} catch (IOException e) {
		}
		
	}
	
	@RequestMapping(value="callback", method={RequestMethod.GET, RequestMethod.POST})
	public void callback(String code, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		UserInfo userInfo = accessTokenService.login(code, this.getRemoteIp(request));
		if(userInfo == null){
			log.error("callback用户userinfo为空，跳转到首页");
			try {
				response.sendRedirect("http://www.binfenguoyuan.cn");
				return;
			} catch (IOException e) {
			}
		}
		
		try {
			String uidValue = DESUtil.encrypt(userInfo.getUserId()+"", BFConstants.loginCookieKey);
			CookieUtils.addCookie("uid", uidValue, 60*60*24*30, response);
			log.error("写cookie成功，uid："+userInfo.getUserId());
		
			String rurl = CookieUtils.getCookieValue("rurl", request);
			if(StringUtils.isNotBlank(rurl)){
				rurl = URLDecoder.decode(rurl, "utf-8");
				CookieUtils.clearCookie("rurl", response);
			}else{
				rurl = "http://www.binfenguoyuan.cn";
			}
			log.error("跳转地址rurl："+rurl);
			response.sendRedirect(rurl);
			return;
		} catch (Exception e) {
			log.error("", e);
		}
		try {
			response.sendRedirect("http://www.binfenguoyuan.cn");
		} catch (IOException e) {
		}
		
	}

	public void setAccessTokenService(AccessTokenService accessTokenService) {
		this.accessTokenService = accessTokenService;
	}

	
}
