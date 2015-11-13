package com.ec.seller.service;

import java.util.List;
import java.util.Map;

import com.ec.seller.domain.Sms;
import com.ec.seller.domain.query.SmsQuery;

public interface SmsService {

	List<Sms> querySms(SmsQuery smsQuery);
	
	Sms queryLastSms(SmsQuery smsQuery);

	Integer addSms(Sms user);

	void updateSms(Sms user);
	
	Map<String, Object> sendSms(String mobile);

}
