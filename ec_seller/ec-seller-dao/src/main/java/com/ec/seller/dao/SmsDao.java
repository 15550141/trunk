package com.ec.seller.dao;


import java.util.List;

import com.ec.seller.domain.Sms;
import com.ec.seller.domain.query.SmsQuery;

public interface SmsDao{
	
	/**
	 * 添加短信信息
	 * @param Sms
	 * @return
	 */
	public Integer addSms(Sms sms);

	/**
	 * 依据短信ID修改地址信息
	 * @param Sms
	 */
	public void updateSms(Sms sms);

	
	/**
	 * 根据相应的条件查询满足条件的短信信息的总数
	 * @param SmsQuery
	 * @return
	 */
	public int countByCondition(SmsQuery smsQuery);
	
	/**
	 * 根据相应的条件查询短信信息
	 * @param SmsQuery
	 * @return
	 */
	public List<Sms> selectByCondition(SmsQuery smsQuery);
	
	/**
	 * 根据相应的条件查询最新的短信
	 * @param SmsQuery
	 * @return
	 */
	public Sms selectLastRecordByCondition(SmsQuery smsQuery);
	
	/**
	 * 根据相应的条件查询短信信息
	 * @param SmsQuery
	 * @return
	 */
	public List<Sms> selectByConditionForPage(SmsQuery smsQuery);
	
}