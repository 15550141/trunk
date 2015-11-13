package com.ec.api.dao;


import java.util.List;

import com.ec.api.domain.Sms;
import com.ec.api.domain.query.SmsQuery;

public interface SmsDao{
	
	/**
	 * 添加短信信息
	 * @param Sms
	 * @return
	 */
	public Integer insert(Sms sms);

	/**
	 * 依据短信ID修改地址信息
	 * @param Sms
	 */
	public void modify(Sms sms);

	
	/**
	 * 根据相应的条件查询满足条件的地址信息的总数
	 * @param SmsQuery
	 * @return
	 */
	public int countByCondition(SmsQuery smsQuery);
	
	/**
	 * 根据相应的条件查询地址信息
	 * @param SmsQuery
	 * @return
	 */
	public List<Sms> selectByCondition(SmsQuery smsQuery);
	
	/**
	 * 根据相应的条件查询地址信息
	 * @param SmsQuery
	 * @return
	 */
	public List<Sms> selectByConditionForPage(SmsQuery smsQuery);
	
}