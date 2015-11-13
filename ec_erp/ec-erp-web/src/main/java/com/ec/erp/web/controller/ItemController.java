package com.ec.erp.web.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ec.erp.service.ItemService;

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

