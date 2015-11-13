package com.ec.seller.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/bookKeeping")
public class BookKeepingController {

	private final static Log LOG = LogFactory.getLog(BookKeepingController.class);

	
	@RequestMapping(value="/sellerBookKeeping", method={ RequestMethod.GET, RequestMethod.POST })
	public String settlement(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		return "bookKeeping/sellerBookKeeping";
	}	
}