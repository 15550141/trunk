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

import com.ec.api.domain.ConsigneeInfo;
import com.ec.api.service.ConsigneeInfoService;
import com.ec.api.service.result.Result;
import com.ec.api.web.base.BaseController;

@Controller
@RequestMapping("/consigneeInfo")
public class ConsigneeInfoController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(ConsigneeInfoController.class);
	private ConsigneeInfoService consigneeInfoService;
	
	/**
	 * 用户收货人信息列表查询
	 * @param request
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="getConsigneeInfosByUserId", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result getConsigneeInfosByUserId(HttpServletRequest request,HttpServletResponse response, ModelMap context){
		return consigneeInfoService.getConsigneeInfosByUserId(getUserId(request));
	}
	
	@RequestMapping(value="addConsigneeInfo", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result addConsigneeInfo(ConsigneeInfo consigneeInfo, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(StringUtils.isBlank(consigneeInfo.getConsigneeName())){
			result.setResultCode("1001");
			result.setResultMessage("consigneeName不能为空");
			return result;
		}
		if(consigneeInfo.getConsigneeProvince() == null){
			result.setResultCode("1001");
			result.setResultMessage("consigneeProvince不能为空");
			return result;
		}
		if(consigneeInfo.getConsigneeCity() == null){
			result.setResultCode("1001");
			result.setResultMessage("consigneeCity不能为空");
			return result;
		}
		if(consigneeInfo.getConsigneeCounty() == null){
			result.setResultCode("1001");
			result.setResultMessage("consigneeCounty不能为空");
			return result;
		}
		if(StringUtils.isBlank(consigneeInfo.getConsigneeAddress())){
			result.setResultCode("1001");
			result.setResultMessage("consigneeAddress不能为空");
			return result;
		}
		if(StringUtils.isBlank(consigneeInfo.getConsigneeMobile())){
			result.setResultCode("1001");
			result.setResultMessage("consigneeMobile不能为空");
			return result;
		}
		consigneeInfo.setUserId(getUserId(request));
		return consigneeInfoService.addConsigneeInfo(consigneeInfo);
	}
	
	@RequestMapping(value="updateConsigneeInfo", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result updateConsigneeInfo(ConsigneeInfo consigneeInfo, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(consigneeInfo.getConsigneeId() == null){
			result.setResultCode("1001");
			result.setResultMessage("consigneeId不能为空");
			return result;
		}
		return consigneeInfoService.updateConsigneeInfo(consigneeInfo);
	}
	
	@RequestMapping(value="delConsigneeInfo", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result delConsigneeInfo(Integer consigneeId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(consigneeId == null){
			result.setResultCode("1001");
			result.setResultMessage("consigneeId不能为空");
			return result;
		}
		return consigneeInfoService.delConsigneeInfo(consigneeId);
	}

	public void setConsigneeInfoService(ConsigneeInfoService consigneeInfoService) {
		this.consigneeInfoService = consigneeInfoService;
	}



}
