package com.ec.erp.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.ec.erp.domain.PromotionInfo;
import com.ec.erp.service.PromotionInfoService;




@Controller
@RequestMapping("/app")
public class AppManagerController {
	@Autowired
	private PromotionInfoService promotionInfoService;
	private final static Log LOG = LogFactory.getLog(AppManagerController.class);

	@RequestMapping("/list")
	public @ResponseBody Map<String, Object> list(PromotionInfo promotionInfo, Integer page, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {	
			promotionInfo.setPageIndex(page);
			resultMap = promotionInfoService.queryPromotionList(promotionInfo);
		} catch (Exception e) {
		}
		return resultMap;
	}
	
	@RequestMapping(value="/adsManager", method={ RequestMethod.GET, RequestMethod.POST })
	public String stoppedPromotion(PromotionInfo promotionInfo, String startTimeString, String endTimeString,Integer page, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = null;

		context.put("resultMap", resultMap);
		return "app/adsManager";
	}
	
}
