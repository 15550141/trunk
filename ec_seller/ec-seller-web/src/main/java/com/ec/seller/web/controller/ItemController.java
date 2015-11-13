package com.ec.seller.web.controller;


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

import com.ec.seller.domain.Item;
import com.ec.seller.domain.PromotionInfo;
import com.ec.seller.domain.query.ItemPropertyQuery;
import com.ec.seller.domain.query.ItemQuery;
import com.ec.seller.service.ItemService;
import com.ec.seller.service.PromotionInfoService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	private final static Log LOG = LogFactory.getLog(ItemController.class);

	
	@RequestMapping(value="/addPro", method={ RequestMethod.GET, RequestMethod.POST })
	public String addPro(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){

		return "item/addProperty";
	}
	

	
}

