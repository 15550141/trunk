package com.ec.api.dao;

import java.util.List;

import com.ec.api.domain.AccessToken;

public interface AccessTokenDao{
	
	/**
	 * 添加用户授权信息
	 */
	public Integer insert(AccessToken accessToken);

	/**
	 * 修改用户授权信息
	 */
	public void modify(AccessToken accessToken);

	public AccessToken selectById(Integer id);
	
	/**
	 * 根据条件查询
	 */
	public List<AccessToken> selectByCondition(AccessToken accessToken);
	
	public AccessToken selectByUserId(Integer userId);
}