package com.ec.erp.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ec.erp.dao.UserDao;
import com.ec.erp.domain.User;

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
