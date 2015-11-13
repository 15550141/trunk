package com.ec.seller.dao;

import com.ec.seller.domain.User;

public interface UserDao {
	User getUserByUsername(String username);
}
