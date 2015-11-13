package com.ec.seller.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ec.seller.dao.UserDao;
import com.ec.seller.domain.User;

@Controller
@RequestMapping("demo")
public class DemoController {
	
	private UserDao userDao;
	
	@RequestMapping(value="aaa", method={RequestMethod.GET, RequestMethod.POST})
	public String aaa(String username, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		User user = userDao.getUserByUsername(username);
		context.put("user", user);
		return "demo/aaa";
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
}
