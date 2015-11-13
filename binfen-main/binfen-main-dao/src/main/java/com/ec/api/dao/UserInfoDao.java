package com.ec.api.dao;

import java.util.List;

import com.ec.api.domain.UserInfo;
import com.ec.api.domain.query.UserInfoQuery;

public interface UserInfoDao{
	
	/**
	 * 添加用户账号信息
	 * @param userInfo
	 * @return
	 */
	public Integer insert(UserInfo userInfo);

	/**
	 * 依据用户ID修改用户账号信息
	 * @param userInfo
	 */
	public void modify(UserInfo userInfo);

	/**
	 * 依据用户ID查询用户账号信息
	 * @param userId
	 * @return
	 */
	public UserInfo selectByUserId(Integer userId);
	
	/**
	 * 根据相应的条件查询满足条件的用户账号信息的总数
	 * @param userInfoQuery
	 * @return
	 */
	public int countByCondition(UserInfoQuery userInfoQuery);
	
	/**
	 * 根据相应的条件查询用户账号信息
	 * @param userInfoQuery
	 * @return
	 */
	public List<UserInfo> selectByCondition(UserInfoQuery userInfoQuery);
	
	/**
	 * 根据相应的条件查询用户账号信息---分页查询
	 * @param userInfoQuery
	 * @return
	 */
	public List<UserInfo> selectByConditionForPage(UserInfoQuery userInfoQuery);
}