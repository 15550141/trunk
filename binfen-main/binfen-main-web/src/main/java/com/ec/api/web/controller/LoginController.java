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

import com.ec.api.common.utils.RegisterValidateRules;
import com.ec.api.service.LoginService;
import com.ec.api.service.UserInfoService;
import com.ec.api.service.result.Result;
import com.ec.api.web.base.BaseController;

@Controller
@RequestMapping("user")
public class LoginController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	private LoginService loginService;
	private UserInfoService userInfoService;
	
	@RequestMapping(value="buy/login", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result buyerLogin(String mobile, String password, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(StringUtils.isBlank(mobile)){
			result.setResultCode("1001");
			result.setResultMessage("mobile不能为空");
			return result;
		}
		if(StringUtils.isBlank(password)){
			result.setResultCode("1001");
			result.setResultMessage("password不能为空");
			return result;
		}
		if(!RegisterValidateRules.isMobile(mobile)){
			result.setResultCode("1002");
			result.setResultMessage("手机格式不正确");
			return result;
		}
		return this.loginService.buyerLogin(mobile, password);
	}
	
	@RequestMapping(value="sell/login", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result sellerLogin(String mobile, String password, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(StringUtils.isBlank(mobile)){
			result.setResultCode("1001");
			result.setResultMessage("mobile不能为空");
			return result;
		}
		if(StringUtils.isBlank(password)){
			result.setResultCode("1001");
			result.setResultMessage("password不能为空");
			return result;
		}
		if(!RegisterValidateRules.isMobile(mobile)){
			result.setResultCode("1002");
			result.setResultMessage("手机格式不正确");
			return result;
		}
		return this.loginService.sellerLogin(mobile, password);
	}
	
	@RequestMapping(value="checkMobile", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result checkMobile(String mobile, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(StringUtils.isBlank(mobile)){
			result.setResultCode("1001");
			result.setResultMessage("mobile不能为空");
			return result;
		}
		if(!RegisterValidateRules.isMobile(mobile)){
			result.setResultCode("1002");
			result.setResultMessage("手机格式不正确");
			return result;
		}
		return this.loginService.checkMobile(mobile);
	}
	
	@RequestMapping(value="checkShopName", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result checkShopName(String shopName, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(StringUtils.isBlank(shopName)){
			result.setResultCode("1001");
			result.setResultMessage("shopName不能为空");
			return result;
		}
		return this.loginService.checkShopName(shopName);
	}
	
	@RequestMapping(value="sendCode", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result sendCode(String mobile, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(StringUtils.isBlank(mobile)){
			result.setResultCode("1001");
			result.setResultMessage("mobile不能为空");
			return result;
		}
		if(!RegisterValidateRules.isMobile(mobile)){
			result.setResultCode("1002");
			result.setResultMessage("手机格式不正确");
			return result;
		}
		return this.loginService.sendCode(mobile);
	}
	
	@RequestMapping(value="sendFindPwdCode", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result sendFindPwdCode(String mobile, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(StringUtils.isBlank(mobile)){
			result.setResultCode("1001");
			result.setResultMessage("mobile不能为空");
			return result;
		}
		if(!RegisterValidateRules.isMobile(mobile)){
			result.setResultCode("1002");
			result.setResultMessage("手机格式不正确");
			return result;
		}
		return this.loginService.sendFindPwdCode(mobile);
	}
	
	@RequestMapping(value="buy/reg", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result buyerReg(String mobile, String password, String code, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(StringUtils.isBlank(mobile)){
			result.setResultCode("1001");
			result.setResultMessage("mobile不能为空");
			return result;
		}
		if(StringUtils.isBlank(password)){
			result.setResultCode("1001");
			result.setResultMessage("password不能为空");
			return result;
		}
		
		if(RegisterValidateRules.isInvalidPassword(password)){
			result.setResultCode("1001");
			result.setResultMessage("密码格式不正确");
			return result;
		}
//		if(StringUtils.isBlank(code)){
//			result.setResultCode("1001");
//			result.setResultMessage("code不能为空");
//			return result;
//		}
		if(!RegisterValidateRules.isMobile(mobile)){
			result.setResultCode("1002");
			result.setResultMessage("手机格式不正确");
			return result;
		}
		return this.loginService.buyerReg(mobile, password, code, getRemoteIp(request));
	}
	
	@RequestMapping(value="sell/reg", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result sellerReg(String mobile, String password, String code,String shopName, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(StringUtils.isBlank(mobile)){
			result.setResultCode("1001");
			result.setResultMessage("mobile不能为空");
			return result;
		}
		if(StringUtils.isBlank(password)){
			result.setResultCode("1001");
			result.setResultMessage("password不能为空");
			return result;
		}
		if(StringUtils.isBlank(code)){
			result.setResultCode("1001");
			result.setResultMessage("code不能为空");
			return result;
		}
		if(StringUtils.isBlank(shopName)){
			result.setResultCode("1001");
			result.setResultMessage("shopName不能为空");
			return result;
		}
		if(!RegisterValidateRules.isMobile(mobile)){
			result.setResultCode("1002");
			result.setResultMessage("手机格式不正确");
			return result;
		}
		return this.loginService.sellerReg(mobile, password, code, getRemoteIp(request), shopName);
	}
	
	@RequestMapping(value="validFindPwdCode", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result validFindPwdCode(String mobile, String code, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(StringUtils.isBlank(mobile)){
			result.setResultCode("1001");
			result.setResultMessage("mobile不能为空");
			return result;
		}
		if(StringUtils.isBlank(code)){
			result.setResultCode("1001");
			result.setResultMessage("code不能为空");
			return result;
		}
		
		return this.loginService.validFindPwdCode(mobile, code);
	}
	
	@RequestMapping(value="resetPwd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result resetPwd(String k, String password, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(StringUtils.isBlank(k)){
			result.setResultCode("1001");
			result.setResultMessage("k不能为空");
			return result;
		}
		if(StringUtils.isBlank(password)){
			result.setResultCode("1001");
			result.setResultMessage("password不能为空");
			return result;
		}
		
		return this.loginService.resetPwd(k, password);
	}
	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

}
