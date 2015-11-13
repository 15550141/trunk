package com.ec.erp.dao;

import com.ec.erp.domain.User;

public interface UserDao {
	User getUserByUsername(String username);
}
