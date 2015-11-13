package com.ec.api.service;

import com.ec.api.domain.UserInfo;

public interface AccessTokenService{
	
	public UserInfo login(String code, String ip);
	
	
}