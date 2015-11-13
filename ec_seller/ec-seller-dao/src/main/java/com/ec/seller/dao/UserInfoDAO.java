package com.ec.seller.dao;

import com.ec.seller.domain.UserInfo;

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

}