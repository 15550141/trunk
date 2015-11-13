package com.ec.seller.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ec.seller.domain.Sms;
import com.ec.seller.domain.query.SmsQuery;
import com.ec.seller.manager.SmsManager;
import com.ec.seller.dao.SmsDao;


@Repository
public class SmsManagerImpl implements SmsManager{
	
	@Autowired
	private SmsDao smsDao;

	@Override
	public List<Sms> querySms(SmsQuery smsQuery) {
		// TODO Auto-generated method stub
		return smsDao.selectByCondition(smsQuery);
	}
	
	@Override
	public Sms queryLastSms(SmsQuery smsQuery) {
		// TODO Auto-generated method stub
		return smsDao.selectLastRecordByCondition(smsQuery);
	}

	public SmsDao getSmsDAO() {
		return smsDao;
	}

	public void setSmsDao(SmsDao smsDao) {
		this.smsDao = smsDao;
	}

	@Override
	public Integer addSms(Sms sms) {
		// TODO Auto-generated method stub
		return smsDao.addSms(sms);
		
	}

	@Override
	public void updateSms(Sms sms) {
		smsDao.updateSms(sms);
		
	}

	
}
