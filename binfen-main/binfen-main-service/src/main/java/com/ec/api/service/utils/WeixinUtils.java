package com.ec.api.service.utils;

import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ec.api.common.utils.BFConstants;
import com.ec.api.common.utils.HttpUtils;
import com.ec.api.common.utils.JsonUtils;
import com.ec.api.service.impl.CartServiceImpl;

/**
 * 获取微信的公共access_token 和 js用到的jsapi_tiket（调用微信的js必须要用）
 * @author yujianming
 *
 */
public class WeixinUtils {
	
	private static final Logger log = LoggerFactory.getLogger(WeixinUtils.class);

	private static String access_token = "";
	
	private static String jsapi_ticket = "";
	
	public static void refreshAccessTokenFromWechat() {
		String json = HttpUtils.httpGetData("https://api.weixin.qq.com/cgi-bin/token", "grant_type=client_credential&appid="+BFConstants.appId+"&secret="+BFConstants.appSecret, "");
		if(StringUtils.isNotBlank(json)){
			Map accessToken = JsonUtils.readValue(json);
			if(accessToken != null && accessToken.get("access_token") != null){
				access_token = accessToken.get("access_token").toString();
			}
		}
		log.error("成功刷新access_token："+access_token);
	}
	
	public static void refreshJsapiTicket() {
		String token = WeixinUtils.getAccessToken();
		String json = HttpUtils.httpGetData("https://api.weixin.qq.com/cgi-bin/ticket/getticket", "access_token="+token+"&type=jsapi", "");
		/*
		 	{
				"errcode":0,
				"errmsg":"ok",
				"ticket":"bxLdikRXVbTPdHSM05e5u5sUoXNKd8-41ZO3MhKoyN5OfkWITDGgnr2fwJ0m9E8NYzWKVZvdVtaUgWvsdshFKA",
				"expires_in":7200
			}
		 */
		if(StringUtils.isNotBlank(json)){
			Map<String, Object> map = JsonUtils.readValue(json);
			if(map != null && map.get("ticket") != null){
				jsapi_ticket = map.get("ticket").toString();
			}
		}
		log.error("成功刷新Jsapi_ticket："+jsapi_ticket);
	}
	
	public static String getJsapiTicket(){
		if(StringUtils.isBlank(jsapi_ticket)){
			refreshJsapiTicket();
		}
		
		return jsapi_ticket;
	}
	
	
	public static String getAccessToken(){
		if(StringUtils.isBlank(access_token)){
			refreshAccessTokenFromWechat();
		}
		
		return access_token;
	}
	
	public static WxJsConfig getWxJsConfig(String url){
		WxJsConfig config = new WxJsConfig();
		config.setDebug(false);
		config.setTimestamp(System.currentTimeMillis()/1000);
		config.setNonceStr("yujianming");
		config.setUrl(url);
		StringBuilder sb = new StringBuilder();
		sb.append("jsapi_ticket=").append(WeixinUtils.getJsapiTicket());
		sb.append("&noncestr=").append(config.getNonceStr());
		sb.append("&timestamp=").append(config.getTimestamp());
		sb.append("&url=").append(config.getUrl());
		config.setSignature(DigestUtils.shaHex(sb.toString()));
		
		return config;
	}
	
	public static void main(String[] args) {
		WxJsConfig config = WeixinUtils.getWxJsConfig("http://www.binfenguoyuan.cn");
		
		System.out.println("nonceStr："+config.getNonceStr());
		System.out.println("signature："+config.getSignature());
		System.out.println("timestamp："+config.getTimestamp());
		System.out.println("url："+config.getUrl());
	}
	
	public void refreshAll(){
		WeixinUtils.refreshAccessTokenFromWechat();
		WeixinUtils.refreshJsapiTicket();
	}
}
