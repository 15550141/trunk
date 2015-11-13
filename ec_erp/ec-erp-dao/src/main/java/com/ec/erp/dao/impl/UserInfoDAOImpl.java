package com.ec.erp.dao.impl;

import com.ec.erp.dao.UserInfoDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ec.erp.domain.UserInfo;
import com.ec.erp.domain.query.ItemQuery;
import com.ec.erp.domain.query.UserInfoQuery;

import java.util.List;
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

	@Override
	public UserInfo queryUserInfo(UserInfoQuery userInfoQuery) {
		// TODO Auto-generated method stub
		return (UserInfo) super.queryForObject("user_info.queryUserInfo", userInfoQuery);
	}

	@Override
	public int countByCondition(UserInfoQuery userInfoQuery) {
		// TODO Auto-generated method stub
		return (Integer)queryForObject("user_info.countByCondition",userInfoQuery);
	}

	@Override
	public List<UserInfoQuery> selectByConditionWithPage(
			UserInfoQuery userInfoQuery) {
		return (List<UserInfoQuery>)queryForList("user_info.selectByConditionWithPage",userInfoQuery);
	}

	
}