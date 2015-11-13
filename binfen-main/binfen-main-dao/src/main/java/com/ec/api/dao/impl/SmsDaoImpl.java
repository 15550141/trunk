package com.ec.api.dao.impl;


import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.api.dao.SmsDao;
import com.ec.api.domain.Sms;
import com.ec.api.domain.query.SmsQuery;

public class SmsDaoImpl extends SqlMapClientTemplate implements SmsDao {

	@Override
	public Integer insert(Sms sms) {
		return (Integer)insert("Sms.insert",sms);
	}

	@Override
	public void modify(Sms sms) {
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
	public List<Sms> selectByConditionForPage(SmsQuery smsQuery) {
		return (List<Sms>)queryForList("Sms.selectByConditionForPage",smsQuery);
	}

}
