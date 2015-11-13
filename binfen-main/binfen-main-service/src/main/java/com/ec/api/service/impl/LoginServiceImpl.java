package com.ec.api.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ec.api.common.utils.DESUtil;
import com.ec.api.common.utils.MD5Util;
import com.ec.api.common.utils.RandomUtil;
import com.ec.api.common.utils.RegisterValidateRules;
import com.ec.api.dao.BusinessUserExtDao;
import com.ec.api.dao.SmsDao;
import com.ec.api.dao.UserInfoDao;
import com.ec.api.domain.BusinessUserExt;
import com.ec.api.domain.Sms;
import com.ec.api.domain.UserInfo;
import com.ec.api.domain.query.BusinessUserExtQuery;
import com.ec.api.domain.query.UserInfoQuery;
import com.ec.api.service.LoginService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;
import com.ec.api.service.utils.RedisUtils;

@Service(value="loginService")
public class LoginServiceImpl implements LoginService {
	private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	private UserInfoDao userInfoDao;
	private BusinessUserExtDao businessUserExtDao;
	private SmsDao smsDao;

	@Override
	public Result checkMobile(String mobile) {
		Result result = new Result();
		try{
			UserInfoQuery userInfoQuery = new UserInfoQuery();
			userInfoQuery.setMobile(mobile);
			List<UserInfo> list = userInfoDao.selectByCondition(userInfoQuery);
			if(list != null && list.size() > 0){
				result.setResultCode("4003");
				result.setResultMessage("手机号已存在");
				return result;
			}
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result buyerReg(String mobile, String password, String code, String ip) {
		Result result = new Result();
		
		//TODO 根据mobile和code。判断短信验证码是否正确
//		if(mobile.equals("") && code.equals("")){
//			result.setResultCode("4004");
//			result.setResultMessage("短信验证码不正确");
//			return result;
//		}
		
		try{
			//先查询数据库，看是否有该用户
			UserInfoQuery userInfoQuery = new UserInfoQuery();
			userInfoQuery.setMobile(mobile);
			List<UserInfo> list = userInfoDao.selectByCondition(userInfoQuery);
			if(list != null && list.size() > 0){
				result.setResultCode("4003");
				result.setResultMessage("手机号已存在");
				return result;
			}
			
			//开始注册
			UserInfo userInfo = new UserInfo();
			userInfo.setMobile(mobile);
			userInfo.setYn(1);
			userInfo.setCreated(new Date());
			userInfo.setLastLoginIp(ip);
			userInfo.setLastLoginTime(new Date());
			userInfo.setRegisterIp(ip);
			userInfo.setRegisterTime(new Date());
			userInfo.setUserType(1);
			userInfo.setPassword(MD5Util.md5Hex(password));
			
			//注册
			Integer userId = userInfoDao.insert(userInfo);
			userInfo.setUserId(userId);
			
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("userId", userInfo.getUserId());
			userMap.put("mobile", userInfo.getMobile());
			map.put("userInfo", userMap);
			map.put("token", createLoginToken(userInfo));
			result.setResult(map);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result sendCode(String mobile) {
		Result result = new Result();
		try{
			//TODO 今后调用dao层方法
			UserInfoQuery userInfoQuery = new UserInfoQuery();
			userInfoQuery.setMobile(mobile);
			List<UserInfo> list = userInfoDao.selectByCondition(userInfoQuery);
			if(list != null && list.size() > 0){
				result.setSuccess(false);
				result.setResultCode("4003");
				result.setResultMessage("手机号已存在");
				return result;
			}
			
			int code = RandomUtil.randomRangeInt(100000, 999999);
			
			//添加发送短信记录
			Sms sms = new Sms();
			sms.setContent("注册验证码为："+code);
			sms.setMobile(mobile);
			sms.setStatus(0);
			smsDao.insert(sms);
			
			log.error("短信验证码："+code);
			String key = "reg_"+mobile;
			RedisUtils.set(key, 300, code+"");
			
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result buyerLogin(String mobile, String password) {
		Result result = new Result();
		
		try{
			//查询 用户信息
			UserInfoQuery userInfoQuery = new UserInfoQuery();
			userInfoQuery.setMobile(mobile);
			List<UserInfo> list = userInfoDao.selectByCondition(userInfoQuery);
			if(list == null || list.size() == 0){
				result.setResultCode("4001");
				result.setResultMessage("用户不存在");
				return result;
			}
			
			UserInfo userInfo = list.get(0);
			
			if(!userInfo.getPassword().equals(password)){
				result.setResultCode("4002");
				result.setResultMessage("密码不正确");
				return result;
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("userId", userInfo.getUserId());
			userMap.put("mobile", userInfo.getMobile());
			map.put("userInfo", userMap);
			map.put("token", createLoginToken(userInfo));
			
			result.setResult(map);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result sellerLogin(String mobile, String password) {
		Result result = new Result();
		try{
			//查询 用户信息
			UserInfoQuery userInfoQuery = new UserInfoQuery();
			userInfoQuery.setMobile(mobile);
			List<UserInfo> list = userInfoDao.selectByCondition(userInfoQuery);
			if(list == null || list.size() == 0){
				result.setResultCode("4001");
				result.setResultMessage("用户不存在");
				return result;
			}
			UserInfo userInfo = list.get(0);
			if(!userInfo.getPassword().equals(password)){
				result.setResultCode("4002");
				result.setResultMessage("密码不正确");
				return result;
			}
			if(userInfo.getUserType() != 2){
				result.setResultCode("4006");
				result.setResultMessage("非商家用户不能登陆");
				return result;
			}
			userInfo.setBusinessUserExt(this.businessUserExtDao.selectByUserId(userInfo.getUserId()));
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("userId", userInfo.getUserId());
			userMap.put("mobile", userInfo.getMobile());
            if( null != userInfo.getBusinessUserExt() ){
                userMap.put("businessType", userInfo.getBusinessUserExt().getBusinessType());
            }
			map.put("userInfo", userMap);
			map.put("token", createLoginToken(userInfo));
			
			result.setResult(map);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	@Override
	public Result sellerReg(String mobile, String password, String code,
			String ip, String shopName) {
		Result result = new Result();
		//TODO 根据mobile和code。判断短信验证码是否正确
//		if(mobile.equals("") && code.equals("")){
//			result.setResultCode("4004");
//			result.setResultMessage("短信验证码不正确");
//			return result;
//		}
		try{
			//先查询数据库，看是否有该用户
			UserInfoQuery userInfoQuery = new UserInfoQuery();
			userInfoQuery.setMobile(mobile);
			List<UserInfo> list = userInfoDao.selectByCondition(userInfoQuery);
			if(list != null && list.size() > 0){
				result.setResultCode("4003");
				result.setResultMessage("手机号已存在");
				return result;
			}
			BusinessUserExtQuery businessUserExtQuery = new BusinessUserExtQuery();
			businessUserExtQuery.setShopName(shopName);
			List list2 = businessUserExtDao.selectByCondition(businessUserExtQuery);
			if(list2 != null && list2.size() > 0){
				result.setResultCode("4007");
				result.setResultMessage("商家名称已存在");
				return result;
			}
			
			//开始注册
			UserInfo userInfo = new UserInfo();
			userInfo.setMobile(mobile);
			userInfo.setUserType(2);
			userInfo.setYn(1);
			userInfo.setCreated(new Date());
			userInfo.setLastLoginIp(ip);
			userInfo.setLastLoginTime(new Date());
			userInfo.setRegisterIp(ip);
			userInfo.setRegisterTime(new Date());
			userInfo.setPassword(MD5Util.md5Hex(password));
			
			//注册
			Integer userId = userInfoDao.insert(userInfo);
			
			BusinessUserExt businessUserExt = new BusinessUserExt();
			businessUserExt.setCreated(new Date());
			businessUserExt.setShopName(shopName);
			businessUserExt.setStatus(1);
			businessUserExt.setUserId(userId);
			//TODO 添加事物处理
			Integer id = businessUserExtDao.insert(businessUserExt);
			businessUserExt.setId(id);
			
			userInfo.setUserId(userId);
			userInfo.setBusinessUserExt(businessUserExt);
			
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("userId", userInfo.getUserId());
			userMap.put("mobile", userInfo.getMobile());
			map.put("userInfo", userMap);
			map.put("token", createLoginToken(userInfo));
			result.setResult(map);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	@Override
	public Result checkShopName(String shopName) {
		Result result = new Result();
		try{
			BusinessUserExtQuery businessUserExtQuery = new BusinessUserExtQuery();
			businessUserExtQuery.setShopName(shopName);
			List<BusinessUserExt> list = businessUserExtDao.selectByCondition(businessUserExtQuery);
			if(list != null && list.size() > 0){
				result.setResultCode("4007");
				result.setResultMessage("商家名称已存在");
				return result;
			}
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	@Override
	public Result sendFindPwdCode(String mobile) {
		Result result = new Result();
		try{
			UserInfoQuery userInfoQuery = new UserInfoQuery();
			userInfoQuery.setMobile(mobile);
			List<UserInfo> list = userInfoDao.selectByCondition(userInfoQuery);
			if(list == null || list.size() == 0){
				result.setSuccess(false);
				result.setResultCode("4001");
				result.setResultMessage("用户不存在");
				return result;
			}
			
			int code = RandomUtil.randomRangeInt(100000, 999999);
			//添加发送短信记录
			Sms sms = new Sms();
			sms.setContent("注册验证码为："+code);
			sms.setMobile(mobile);
			sms.setStatus(0);
			smsDao.insert(sms);
			
			log.error("发送短信验证码："+code);
			String key = "findPwd_"+mobile;
			RedisUtils.set(key, 300, code+"");
			
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result validFindPwdCode(String mobile, String code) {
		Result result = new Result();
		try{
			String key = "findPwd_"+mobile;
			String value = RedisUtils.get(key);
			if(!code.equals(value)){
				result.setResultCode("4004");
				result.setResultMessage("验证码不正确");
				return result;
			}
			RedisUtils.del(key);
			
			String k = MD5Util.md5Hex(""+System.currentTimeMillis()+RandomUtil.randomRangeInt(100000, 999999));
			RedisUtils.set("validCode_"+k, 300, mobile);
			result.setResult(k);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	@Override
	public Result resetPwd(String k, String password) {
		Result result = new Result();
		
		try{
			String mobile = RedisUtils.get("validCode_"+k);
			
			if(StringUtils.isBlank(mobile)){
				result.setResultCode("4004");
				result.setResultMessage("短信验证码不正确");
				return result;
			}
			UserInfoQuery userInfoQuery = new UserInfoQuery();
			userInfoQuery.setMobile(mobile);
			List<UserInfo> list = userInfoDao.selectByCondition(userInfoQuery);
			UserInfo userInfo = list.get(0);
			
			//判断新密码符合要求
			if(RegisterValidateRules.isInvalidPassword(password)){
				result.setResultCode("1001");
				result.setResultMessage("新密码格式不正确");
				return result;
			}
			
			//修改登陆密码
			userInfo.setPassword(MD5Util.md5Hex(password));
			userInfoDao.modify(userInfo);
			
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("userId", userInfo.getUserId());
			userMap.put("mobile", userInfo.getMobile());
			map.put("userInfo", userMap);
			map.put("token", createLoginToken(userInfo));
			
			result.setResult(map);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	
	private String createLoginToken(UserInfo userInfo) throws Exception{
		String token = userInfo.getUserId() + "-" + userInfo.getMobile() + "-" + userInfo.getUserType();
		return DESUtil.encrypt(token, "gs2y601z");
	}

	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public void setBusinessUserExtDao(BusinessUserExtDao businessUserExtDao) {
		this.businessUserExtDao = businessUserExtDao;
	}

	public void setSmsDao(SmsDao smsDao) {
		this.smsDao = smsDao;
	}

}
