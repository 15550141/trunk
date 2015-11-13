package com.ec.erp.service;

import java.util.Map;

import com.ec.erp.domain.UserInfo;
import com.ec.erp.domain.query.UserInfoQuery;

public interface UserService {

	UserInfo queryUser(String loginname, String loginpwd);

	Integer addUser(UserInfo user);

	void updateUser(UserInfo user);

	Map<String, Object> queryUserList(UserInfoQuery userInfoQuery);

}
