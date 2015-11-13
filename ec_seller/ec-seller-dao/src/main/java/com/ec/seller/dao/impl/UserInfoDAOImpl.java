package com.ec.seller.dao.impl;

import com.ec.seller.dao.UserInfoDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ec.seller.domain.UserInfo;

import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class UserInfoDAOImpl extends SqlMapClientTemplate implements UserInfoDAO {


	@Override
	public UserInfo query(Map<String, String> params) {
		// TODO Auto-generated method stub
		UserInfo userInfo=null;
		userInfo = (UserInfo) super.queryForObject("user_info.query", params);
        return userInfo;		
	}

	@Override
	public Integer addUser(UserInfo user) {
		// TODO Auto-generated method stub
		return (Integer)super.insert("user_info.addUser", user);
		
	}

	@Override
	public void updateUser(UserInfo user) {
		super.update("user_info.updateUser", user);
	}


	@Override
	public UserInfo queryByMobile(String mobile) {
		return (UserInfo) super.queryForObject("user_info.queryByMobile", mobile);
	}

	@Override
	public void addSignSMSCode(Integer mobile) {
		// TODO Auto-generated method stub
		
	}

	
}