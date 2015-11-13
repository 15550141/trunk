package com.ec.seller.manager.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ec.seller.domain.UserInfo;
import com.ec.seller.manager.UserManager;
import com.ec.seller.dao.UserInfoDAO;

@Repository
public class UserManagerImpl implements UserManager{
	
	@Autowired
	private UserInfoDAO userInfoDAO;

	@Override
	public UserInfo query(Map<String, String> params) {
		// TODO Auto-generated method stub
		UserInfo userInfo=userInfoDAO.query(params);
		return userInfo;
	}
	
	@Override
	public UserInfo queryByMobile(String mobile) {
		// TODO Auto-generated method stub
		UserInfo userInfo=userInfoDAO.queryByMobile(mobile);
		return userInfo;
	}

	public UserInfoDAO getUserInfoDAO() {
		return userInfoDAO;
	}

	public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}

	@Override
	public Integer addUser(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDAO.addUser(user);
		
	}

	@Override
	public void updateUser(UserInfo user) {
		userInfoDAO.updateUser(user);
		
	}

	@Override
	public void addSignSMSCode(Integer mobile) {
		
		userInfoDAO.addSignSMSCode(mobile);
	}

	

	
}
