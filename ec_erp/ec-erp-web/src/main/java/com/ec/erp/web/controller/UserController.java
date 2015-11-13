package com.ec.erp.web.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ec.erp.domain.UserInfo;
import com.ec.erp.domain.query.BusinessUserExtQuery;
import com.ec.erp.domain.query.UserInfoQuery;
import com.ec.erp.service.BusinessUserExtService;
import com.ec.erp.service.UserService;
import com.ec.erp.domain.BusinessUserExt;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	BusinessUserExtService businessUserExtService;
	
	private final static Log LOG = LogFactory.getLog(UserController.class);

	/**
	 * 用户生效
	 */
	@RequestMapping(value="/effect", method={ RequestMethod.GET, RequestMethod.POST })
	public  @ResponseBody Map<String, Object> effect(String mobile,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		UserInfo userInfo =new UserInfo();
		if(!StringUtils.isBlank(mobile)){
			userInfo.setMobile(mobile);
		}
		userInfo.setYn(1);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			userService.updateUser(userInfo);
		} catch (Exception e) {
			LOG.error("user.effect:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	/**
	 * 用户失效
	 */
	@RequestMapping(value="/unEffect", method={ RequestMethod.GET, RequestMethod.POST })
	public  @ResponseBody Map<String, Object> unEffect(String mobile,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		UserInfo userInfo =new UserInfo();
		if(!StringUtils.isBlank(mobile)){
			userInfo.setMobile(mobile);
		}
		userInfo.setYn(0);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			userService.updateUser(userInfo);
		} catch (Exception e) {
			LOG.error("user.unEffect:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		context.put("resultMap", resultMap);
		resultMap.put("msg","success");
		return resultMap;
	}
	

	
	
	/**
	 * 用户列表
	 */
	@RequestMapping(value="/list", method={ RequestMethod.GET, RequestMethod.POST })
	public String stopSaleProduct(Integer page, UserInfoQuery userInfoQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(page==null){
				userInfoQuery.setPageNo(1);
				
			}else{
				userInfoQuery.setPageNo(page);
			}
			
			if(StringUtils.isBlank(userInfoQuery.getMobile())){
				userInfoQuery.setMobile(null);	
			}
			resultMap = userService.queryUserList(userInfoQuery);
			
		} catch (Exception e) {
			LOG.error("user.list:", e);
		}
		context.put("resultMap", resultMap);
		return "user/ordinaryUser";
	}
	
	@RequestMapping(value="/bank", method={ RequestMethod.GET, RequestMethod.POST })
	public String bank(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){

		return "user/addBank";
	}

	
	/**
	 * 添加银行信息
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/addBank", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addBank(BusinessUserExt businessUserExt,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//businessUserExt.setUserId(CookieUtil.getUserId(reuqest));
		businessUserExt.setBankCreated(new Date());
		businessUserExt.setBankStatus(0);
		try {
			businessUserExtService.modifyByUserId(businessUserExt);
		} catch (Exception e) {
			resultMap.put("msg","error");
			return resultMap;
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	
	
	/**
	 * 添加商家基本信息
	 * @return
	 */
	@RequestMapping(value="/addBaseInfo", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addBaseInfo(BusinessUserExt businessUserExt,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		businessUserExt.setBankCreated(new Date());
		businessUserExt.setBankStatus(0);
		try {
			businessUserExtService.modifyByUserId(businessUserExt);
		} catch (Exception e) {
			resultMap.put("msg","error");
			return resultMap;
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	
	@RequestMapping(value="/showBank", method={ RequestMethod.GET, RequestMethod.POST })
	public String showBank(Integer userId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){


		Map<String, Object> resultMap = new HashMap<String, Object>();
		BusinessUserExt businessUserExt =new BusinessUserExt();
		try {
			//根据用户ID查商家扩展信息
			businessUserExt = businessUserExtService.selectByUserId(userId);
			//resultMap = itemService.queryItemList(itemQuery);
		} catch (Exception e) {
			LOG.error("User.showBank:", e);
		}
		if(businessUserExt.getBank()!=null){
			context.put("businessUserExt", businessUserExt);
			
			return "user/bankInfo";
		}else{
			context.put("businessUserExt", businessUserExt);
			return "user/addBank";
		}
		
		
	}
	
	@RequestMapping(value="/showBaseInfo", method={ RequestMethod.GET, RequestMethod.POST })
	public String showBaseInfo(Integer userId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){


		Map<String, Object> resultMap = new HashMap<String, Object>();
		BusinessUserExt businessUserExt =new BusinessUserExt();
		try {
			//根据用户ID查商家扩展信息
			businessUserExt = businessUserExtService.selectByUserId(userId);
			//resultMap = itemService.queryItemList(itemQuery);
		} catch (Exception e) {
			LOG.error("User.showBaseInfo:", e);
		}
		context.put("businessUserExt", businessUserExt);
		return "user/editbaseInfo";	
	}
	
	/**
	 * 待审核银行列表
	 */
	@RequestMapping(value="/bankList", method={ RequestMethod.GET, RequestMethod.POST })
	public String bankList(Integer page, BusinessUserExtQuery businessUserExtQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(page==null){
				businessUserExtQuery.setPageNo(1);
				
			}else{
				businessUserExtQuery.setPageNo(page);
			}
			
			if(StringUtils.isBlank(businessUserExtQuery.getShopName())){
				businessUserExtQuery.setShopName(null);
			}
			resultMap = businessUserExtService.queryBusinessUserExtList(businessUserExtQuery);
			
		} catch (Exception e) {
			LOG.error("user.list:", e);
		}
		context.put("resultMap", resultMap);
		return "user/bankList";
	}

	/**
	 * 商家基本信息列表
	 */
	@RequestMapping(value="/baseInfoList", method={ RequestMethod.GET, RequestMethod.POST })
	public String baseInfoList(Integer page, BusinessUserExtQuery businessUserExtQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(page==null){
				businessUserExtQuery.setPageNo(1);
				
			}else{
				businessUserExtQuery.setPageNo(page);
			}
			
			if(StringUtils.isBlank(businessUserExtQuery.getShopName())){
				businessUserExtQuery.setShopName(null);
			}
			resultMap = businessUserExtService.queryBusinessUserExtList(businessUserExtQuery);
			
		} catch (Exception e) {
			LOG.error("user.list:", e);
		}
		context.put("resultMap", resultMap);
		return "user/baseInfoList";
	}
	
	/**
	 * 审核银行信息
	 */
	@RequestMapping(value="/examine", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> examine(Integer userId,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		BusinessUserExt businessUserExt = new BusinessUserExt();
		businessUserExt.setUserId(userId);
		businessUserExt.setBankStatus(1);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			businessUserExtService.modifyByUserId(businessUserExt);
		} catch (Exception e) {
			LOG.error("User.examine:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		context.put("resultMap", resultMap);
		resultMap.put("msg","success");
		return resultMap;
	}
	
	/**
	 * 驳回银行信息
	 */
	@RequestMapping(value="/reject", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> reject(Integer userId,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		BusinessUserExt businessUserExt = new BusinessUserExt();
		businessUserExt.setUserId(userId);
		businessUserExt.setBankStatus(2);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			businessUserExtService.modifyByUserId(businessUserExt);
		} catch (Exception e) {
			LOG.error("User.reject:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		context.put("resultMap", resultMap);
		resultMap.put("msg","success");
		return resultMap;
	}

    /**
	 * 认证商户
	 */
	@RequestMapping(value="/confirm", method={ RequestMethod.GET, RequestMethod.POST })
	public  @ResponseBody Map<String, Object> confirm(Integer userId,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		BusinessUserExt businessUserExt = new BusinessUserExt();
		businessUserExt.setUserId(userId);
        businessUserExt.setBusinessType(2);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			businessUserExtService.modifyByUserId(businessUserExt);
		} catch (Exception e) {
			LOG.error("user.confirm:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
        context.put("resultMap", resultMap);
		resultMap.put("msg","success");
		return resultMap;
	}
	/**
	 * 解除认证商户
	 */
	@RequestMapping(value="/unConfirm", method={ RequestMethod.GET, RequestMethod.POST })
	public  @ResponseBody Map<String, Object> unConfirm(Integer userId,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
			BusinessUserExt businessUserExt = new BusinessUserExt();
		businessUserExt.setUserId(userId);
		businessUserExt.setBusinessType(1);
        Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			businessUserExtService.modifyByUserId(businessUserExt);
		} catch (Exception e) {
			LOG.error("user.unConfirm:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		context.put("resultMap", resultMap);
		resultMap.put("msg","success");
		return resultMap;
	}
	



}
