package com.ec.seller.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import com.ec.seller.domain.UserInfo;
import com.ec.seller.manager.UserManager;
import com.ec.seller.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserManager userManager;

	@Override
	public UserInfo queryUser(String loginname, String loginpwd) {
		Map<String,String> params=new HashMap<String,String>();
		params.put("mobile", loginname);
		params.put("password", loginpwd);
		UserInfo userInfo = userManager.query(params);
		return userInfo;
	}	

	@Override
	public UserInfo queryByMobile(String mobile) {
		// TODO Auto-generated method stub
		UserInfo userInfo = userManager.queryByMobile(mobile);
		return userInfo;
	}

	@Override
	public Integer addUser(UserInfo user) {
		
		return userManager.addUser(user);
		
	}

	@Override
	public void updateUser(UserInfo user) {
		
		userManager.updateUser(user);
		
	}


}
