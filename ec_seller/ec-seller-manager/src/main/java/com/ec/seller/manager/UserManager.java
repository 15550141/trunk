package com.ec.seller.manager;

import java.util.Map;

import com.ec.seller.domain.UserInfo;

public interface UserManager {

	UserInfo query(Map<String, String> params);
	
	UserInfo queryByMobile(String mobile);

	Integer addUser(UserInfo user);

	void updateUser(UserInfo user);

	void addSignSMSCode(Integer mobile);

	

}
