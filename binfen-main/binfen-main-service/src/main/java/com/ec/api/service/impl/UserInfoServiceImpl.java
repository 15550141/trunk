package com.ec.api.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ec.api.common.utils.MD5Util;
import com.ec.api.common.utils.RegisterValidateRules;
import com.ec.api.dao.BusinessUserExtDao;
import com.ec.api.dao.UserInfoDao;
import com.ec.api.domain.BusinessUserExt;
import com.ec.api.domain.UserInfo;
import com.ec.api.service.UserInfoService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;

@Service(value="userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
	private static final Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	
	private UserInfoDao userInfoDao;
	
	private BusinessUserExtDao businessUserExtDao;

	@Override
	public Result updatePwd(Integer userId, String oldPwd, String newPwd) {
		Result result = new Result();
		
		try{
			//根据userId获取用户信息
			UserInfo userInfo = userInfoDao.selectByUserId(userId);
			
			if(userInfo == null){
				result.setResultCode("4001");
				result.setResultMessage("用户不存在");
				return result;
			}
			
			if(!userInfo.getPassword().equals(MD5Util.md5Hex(oldPwd))){
				result.setResultCode("4005");
				result.setResultMessage("旧密码不匹配");
				return result;
			}
			
			if(RegisterValidateRules.isInvalidPassword(newPwd)){
				result.setResultCode("1001");
				result.setResultMessage("新密码格式不正确");
				return result;
			}
			
			//修改登陆密码
			userInfo.setPassword(MD5Util.md5Hex(newPwd));
			userInfoDao.modify(userInfo);
			
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public UserInfo getUserInfoByUserId(Integer userId) {
		try{
			//获取用户信息
			UserInfo userInfo = userInfoDao.selectByUserId(userId);
			if(userInfo == null){
				return null;
			}
			//如果是卖家
			if(userInfo.getUserType()==2 ){
				//查询用户扩展表
				BusinessUserExt businessUserExt = this.businessUserExtDao.selectByUserId(userId);
				if(businessUserExt != null){
					userInfo.setBusinessUserExt(businessUserExt);
				}
			}
			
			return userInfo;
		}catch (Exception e) {
			log.error("", e);
		}
		return null;
	}






	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public void setBusinessUserExtDao(BusinessUserExtDao businessUserExtDao) {
		this.businessUserExtDao = businessUserExtDao;
	}

}
