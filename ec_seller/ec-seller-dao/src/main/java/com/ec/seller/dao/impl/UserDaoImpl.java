package com.ec.seller.dao.impl;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.seller.dao.UserDao;
import com.ec.seller.domain.User;

public class UserDaoImpl extends SqlMapClientTemplate implements UserDao {

	@Override
	public User getUserByUsername(String username) {
		return (User)super.queryForObject("User.getUserByUsername", username);
	}
}
