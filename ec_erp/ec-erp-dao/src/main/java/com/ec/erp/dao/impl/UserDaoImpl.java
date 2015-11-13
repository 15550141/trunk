package com.ec.erp.dao.impl;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.erp.dao.UserDao;
import com.ec.erp.domain.User;

public class UserDaoImpl extends SqlMapClientTemplate implements UserDao {

	@Override
	public User getUserByUsername(String username) {
		return (User)super.queryForObject("User.getUserByUsername", username);
	}
}
