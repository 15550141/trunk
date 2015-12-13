package com.binfen.admin.service.impl;

import com.binfen.admin.dao.UserInfoDao;
import com.binfen.admin.domain.UserInfo;
import com.binfen.admin.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * UserInfoIncrService 实现类
 * @author J-ONE
 * @since 2015-12-12
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;


	@Override
	public Integer saveUserInfo(UserInfo userInfo) {
		return null;
	}
}