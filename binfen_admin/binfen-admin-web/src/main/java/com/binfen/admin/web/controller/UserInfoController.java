/*
 * Copyright (c) 2015 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */
package com.binfen.admin.web.controller;

import com.binfen.admin.domain.UserInfo;
import com.binfen.admin.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 *userInfo controller层
 * @author J-ONE
 * @since 2015-12-12
 */
@Controller
@RequestMapping(value = "/userInfo")
public class UserInfoController{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);
	@Resource private UserInfoService userInfoService;

	
	/**
	 * 列表展示
	 * @param userInfo 实体对象
	 * @return

	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public String list(UserInfo userInfo,Page<UserInfo> page,Model view) throws Exception{
		try {
			view.addAttribute("userInfo",userInfo);
			view.addAttribute("page",userInfoService.selectPage(userInfo,page));			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}	
		return "userInfo/list";
	}
	 */

	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public String list(UserInfo userInfo,Model view) throws Exception{
		try {
			userInfoService.saveUserInfo(userInfo);
			view.addAttribute("userInfo",userInfo);
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}
		return "userInfo/test";
	}
}