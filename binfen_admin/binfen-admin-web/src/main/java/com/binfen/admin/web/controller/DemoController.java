package com.binfen.admin.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @auth lsg
 * @version 1.0.0
 */
@Controller
@RequestMapping("/menu")
public class DemoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

	@RequestMapping(value = "ajax", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> ajax() {
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("one", "hello中国");
		root.put("date", new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(root);
		root.put("map", map);
		return root;
	}

    @RequestMapping(value = "/list")
    public String list(Model view) {
        return "core/list";
    }

    @RequestMapping(value = "/home")
    public String homePage(Model view) {
        return "home";
    }

}
