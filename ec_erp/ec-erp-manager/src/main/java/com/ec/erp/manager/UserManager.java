package com.ec.erp.manager;

import java.util.List;
import java.util.Map;

import com.ec.erp.domain.UserInfo;
import com.ec.erp.domain.query.UserInfoQuery;

public interface UserManager {

	UserInfo query(Map<String, String> params);

	Integer addUser(UserInfo user);

	void updateUser(UserInfo user);

	void addSignSMSCode(Integer mobile);

	int countByCondition(UserInfoQuery userInfoQuery);

	List<UserInfoQuery> selectByConditionWithPage(UserInfoQuery userInfoQuery);

}
