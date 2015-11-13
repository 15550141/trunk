package com.ec.seller.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping("")
	public String index() {
		return "mainFrame/index";
	}

	@RequestMapping("contentFrame")
	public String contentFrame() {
		return "mainFrame/contentFrame";
	}

	@RequestMapping("rightFrame")
	public String rightFrame() {
		return "mainFrame/rightFrame";
	}

	@RequestMapping("leftFrame")
	public String leftFrame() {
		return "mainFrame/leftFrame";
	}

	@RequestMapping("commonSepatator")
	public String commonSepatator() {
		return "mainFrame/commonSepatator";
	}
}
