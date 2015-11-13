package com.ec.api.service;

import com.ec.api.service.result.Result;

public interface LoginService {
	/**
	 * 普通用户注册
	 * @param mobile
	 * @param password
	 * @param code
	 * @return
	 */
	public Result buyerReg(String mobile, String password, String code, String ip);
	
	/**
	 * 商家用户注册
	 * @param mobile
	 * @param password
	 * @param code
	 * @return
	 */
	public Result sellerReg(String mobile, String password, String code, String ip, String shopName);
	
	public Result sendCode(String mobile);
	
	/**
	 * 登陆方法
	 * @param mobile
	 * @param password
	 * @return
	 */
	public Result buyerLogin(String mobile, String password);
	
	/**
	 * 登陆方法
	 * @param mobile
	 * @param password
	 * @return
	 */
	public Result sellerLogin(String mobile, String password);
	
	/**
	 * 判断手机是否可以注册
	 * @param mobile
	 * @return
	 */
	public Result checkMobile(String mobile);
	
	/**
	 * 判断店铺名称是否可以注册
	 * @param mobile
	 * @return
	 */
	public Result checkShopName(String shopName);
	
	/**
	 * 发送找回密码手机验证码code
	 * @param mobile
	 * @return
	 */
	public Result sendFindPwdCode(String mobile);
	
	/**
	 * 验证找回密码code是否正确
	 * @param mobile
	 * @return
	 */
	public Result validFindPwdCode(String mobile, String code);
	
	/**
	 * 重置登陆密码
	 * @param k
	 * @param password
	 * @return
	 */
	public Result resetPwd(String k, String password);
}
