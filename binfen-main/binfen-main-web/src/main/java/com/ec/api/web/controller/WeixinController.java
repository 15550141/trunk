package com.ec.api.web.controller;

import com.ec.api.common.utils.BFConstants;
import com.ec.api.common.utils.BFUtils;
import com.ec.api.service.utils.WeixinUtils;
import com.ec.api.service.utils.WxJsConfig;
import com.ec.api.web.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Controller
@RequestMapping("/weixin")
public class WeixinController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(WeixinController.class);
	
	@RequestMapping(value="valid", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String valid(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		log.error("进入验证信息页面");
		String [] str = new String[]{nonce, timestamp, BFConstants.token};
		Arrays.sort(str);
		
		String sign = str[0]+str[1]+str[2];
		sign = BFUtils.sha1Hex(sign);
		
		if(sign.equalsIgnoreCase(signature)){
			log.error("验证通过");
			return echostr;
		}
		log.error("验证失败");
		return "error";
	}

	@RequestMapping(value="getWxConfig", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody WxJsConfig getWxConfig(String url, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		WxJsConfig config = WeixinUtils.getWxJsConfig(url);
		return config;
	}
	
}
