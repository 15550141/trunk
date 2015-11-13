package com.ec.api.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.api.dao.UserInfoDao;
import com.ec.api.domain.UserInfo;
import com.ec.api.domain.query.UserInfoQuery;

public class UserInfoDaoImpl extends SqlMapClientTemplate implements UserInfoDao {

	@Override
	public Integer insert(UserInfo userInfo) {
		return (Integer)insert("UserInfo.insert", userInfo);
	}

	@Override
	public void modify(UserInfo userInfo) {
		update("UserInfo.updateByPrimaryKey", userInfo);
	}

	@Override
	public UserInfo selectByUserId(Integer userId) {
		return (UserInfo)queryForObject("UserInfo.selectByPrimaryKey",userId);
	}

	@Override
	public int countByCondition(UserInfoQuery userInfoQuery) {
		return (Integer)queryForObject("UserInfo.countByCondition",userInfoQuery);
	}

	@Override
	public List<UserInfo> selectByCondition(UserInfoQuery userInfoQuery) {
		return queryForList("UserInfo.selectByCondition",userInfoQuery);
	}

	@Override
	public List<UserInfo> selectByConditionForPage(UserInfoQuery userInfoQuery) {
		return queryForList("UserInfo.selectByConditionForPage",userInfoQuery);
	}

}
