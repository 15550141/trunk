package com.ec.api.web.controller;

import java.util.Date;

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

import com.ec.api.domain.ItemDescription;
import com.ec.api.service.ItemDescriptionService;
import com.ec.api.service.result.Result;
import com.ec.api.web.base.BaseController;

@Controller
@RequestMapping("/sell/item/description")
public class SellerItemDescriptionController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(SellerItemDescriptionController.class);
	
	private ItemDescriptionService itemDescriptionService;
	
	@RequestMapping(value="addItemDescription", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result addItemDescription(ItemDescription itemDescription, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(itemDescription.getItemId() == null){
			result.setResultCode("1001");
			result.setResultMessage("itemId不能为空");
			return result;
		}
		if(StringUtils.isBlank(itemDescription.getAppDescriptionInfo())){
			result.setResultCode("1001");
			result.setResultMessage("appDescriptionInfo不能为空");
			return result;
		}
		itemDescription.setCreated(new Date());
		return this.itemDescriptionService.addItemDescription(itemDescription);
	}
	
	@RequestMapping(value="updateItemDescription", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result updateItemDescription(ItemDescription itemDescription, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(itemDescription.getItemId() == null){
			result.setResultCode("1001");
			result.setResultMessage("itemId不能为空");
			return result;
		}
		itemDescription.setModified(new Date());
		return this.itemDescriptionService.updateItemDescription(itemDescription);
	}
	
	@RequestMapping(value="insertOrUpdate", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result insertOrUpdate(ItemDescription itemDescription, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(itemDescription.getItemId() == null){
			result.setResultCode("1001");
			result.setResultMessage("itemId不能为空");
			return result;
		}
		if(StringUtils.isBlank(itemDescription.getAppDescriptionInfo())){
			result.setResultCode("1001");
			result.setResultMessage("appDescriptionInfo不能为空");
			return result;
		}
		itemDescription.setModified(new Date());
		return this.itemDescriptionService.insertOrUpdate(itemDescription);
	}

	public void setItemDescriptionService(
			ItemDescriptionService itemDescriptionService) {
		this.itemDescriptionService = itemDescriptionService;
	}
	
}
