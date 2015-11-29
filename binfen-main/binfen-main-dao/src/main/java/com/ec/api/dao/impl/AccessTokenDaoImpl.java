package com.ec.api.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.api.dao.AccessTokenDao;
import com.ec.api.domain.AccessToken;

public class AccessTokenDaoImpl extends SqlMapClientTemplate implements AccessTokenDao{

	@Override
	public Integer insert(AccessToken accessToken) {
		return (Integer) insert("AccessToken.insert", accessToken);
	}

	@Override
	public void modify(AccessToken accessToken) {
		if(accessToken.getId() == null && accessToken.getUid() == null){
			throw new RuntimeException("id或者uid不能同时为空");
		}
		update("AccessToken.modify", accessToken);
	}

	@Override
	public AccessToken selectById(Integer id) {
		return (AccessToken) queryForObject("AccessToken.selectByPrimaryKey", id);
	}

	@Override
	public List<AccessToken> selectByCondition(AccessToken accessToken) {
		return (List<AccessToken>)queryForList("AccessToken.selectByCondition", accessToken);
	}

	@Override
	public AccessToken selectByUserId(Integer userId) {
		return (AccessToken) queryForObject("AccessToken.selectByUserId", userId);
	}
	
	
}