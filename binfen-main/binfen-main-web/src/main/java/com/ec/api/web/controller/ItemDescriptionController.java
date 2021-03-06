package com.ec.api.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ec.api.service.ItemDescriptionService;
import com.ec.api.service.result.Result;
import com.ec.api.web.base.BaseController;

@Controller
@RequestMapping("/item/description")
public class ItemDescriptionController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(ItemDescriptionController.class);
	
	private ItemDescriptionService itemDescriptionService;
	
	@RequestMapping(value="getItemDescriptionByItemId", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result getItemDescriptionByItemId(Integer itemId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(itemId == null){
			Result result = new Result();
			result.setResultCode("1001");
			result.setResultMessage("itemId不能为空");
			return result;
		}
		return this.itemDescriptionService.getItemDescriptionByItemId(itemId);
	}
	
	public void setItemDescriptionService(
			ItemDescriptionService itemDescriptionService) {
		this.itemDescriptionService = itemDescriptionService;
	}
	
}
