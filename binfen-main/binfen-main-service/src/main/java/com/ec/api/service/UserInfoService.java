package com.ec.api.service;

import com.ec.api.domain.UserInfo;
import com.ec.api.service.result.Result;

public interface UserInfoService {
	/**
	 * 登陆状态下，修改登陆密码
	 * @param userId
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	public Result updatePwd(Integer userId, String oldPwd, String newPwd);
	
	/**
	 * 获取登陆用户信息
	 * @param userId
	 * @return
	 */
	public UserInfo getUserInfoByUserId(Integer userId);
	
}
