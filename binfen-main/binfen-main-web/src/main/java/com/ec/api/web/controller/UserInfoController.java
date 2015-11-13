package com.ec.api.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ec.api.common.utils.CookieUtils;
import com.ec.api.service.UserInfoService;
import com.ec.api.service.result.Result;
import com.ec.api.web.base.BaseController;

@Controller
@RequestMapping("/user")
public class UserInfoController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);
	private UserInfoService userInfoService;
	
	@RequestMapping(value="", method={RequestMethod.GET, RequestMethod.POST})
	public String index(HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Integer userId = CookieUtils.getUid(request);
		context.put("userinfo", this.userInfoService.getUserInfoByUserId(userId));
		return "/user/index";
	}
	
	@RequestMapping(value="updatePwd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result updatePwd(String oldPwd, String newPwd, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(StringUtils.isBlank(oldPwd)){
			result.setResultCode("1001");
			result.setResultMessage("oldPwd不能为空");
			return result;
		}
		if(StringUtils.isBlank(newPwd)){
			result.setResultCode("1001");
			result.setResultMessage("newPwd不能为空");
			return result;
		}
		return userInfoService.updatePwd(getUserId(request), oldPwd, newPwd);
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

}
