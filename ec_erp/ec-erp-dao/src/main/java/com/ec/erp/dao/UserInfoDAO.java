package com.ec.erp.dao;

import com.ec.erp.domain.UserInfo;
import com.ec.erp.domain.query.UserInfoQuery;

import java.util.List;
import java.util.Map;

public interface UserInfoDAO {
//    void insert(UserInfo record) throws SQLException;
//
//    int updateByPrimaryKey(UserInfo record) throws SQLException;
//
//    int updateByPrimaryKeySelective(UserInfo record) throws SQLException;
//
//    List selectByExample(UserInfoExample example) throws SQLException;
//
//    UserInfo selectByPrimaryKey(Integer userId) throws SQLException;
//
//    int deleteByExample(UserInfoExample example) throws SQLException;
//
//    int deleteByPrimaryKey(Integer userId) throws SQLException;
//
//    int countByExample(UserInfoExample example) throws SQLException;
//
//    int updateByExampleSelective(UserInfo record, UserInfoExample example) throws SQLException;
//
//    int updateByExample(UserInfo record, UserInfoExample example) throws SQLException;

	UserInfo query(Map<String, String> params);

	Integer addUser(UserInfo user);

	void updateUser(UserInfo user);

	void addSignSMSCode(Integer mobile);
	
	UserInfo queryByMobile(String mobile);
	
	UserInfo queryUserInfo(UserInfoQuery userInfoQuery);

	int countByCondition(UserInfoQuery userInfoQuery);

	List<UserInfoQuery> selectByConditionWithPage(UserInfoQuery userInfoQuery);
}