package com.ec.seller.dao.impl;


import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.seller.dao.SmsDao;
import com.ec.seller.domain.Sms;
import com.ec.seller.domain.query.SmsQuery;

public class SmsDaoImpl extends SqlMapClientTemplate implements SmsDao {

	@Override
	public Integer addSms(Sms sms) {
		return (Integer)insert("Sms.insert",sms);
	}

	@Override
	public void updateSms(Sms sms) {
		update("Sms.updateByPrimaryKey",sms);
	}

	@Override
	public int countByCondition(SmsQuery smsQuery) {
		return (Integer)queryForObject("Sms.countByCondition",smsQuery);
	}

	@Override
	public List<Sms> selectByCondition(SmsQuery smsQuery) {
		return (List<Sms>)queryForList("Sms.selectByCondition",smsQuery);
	}

	@Override
	public Sms selectLastRecordByCondition(SmsQuery smsQuery) {
		return (Sms)queryForObject("Sms.selectLastRecordByCondition",smsQuery);
	}
	
	@Override
	public List<Sms> selectByConditionForPage(SmsQuery smsQuery) {
		return (List<Sms>)queryForList("Sms.selectByConditionForPage",smsQuery);
	}

}
