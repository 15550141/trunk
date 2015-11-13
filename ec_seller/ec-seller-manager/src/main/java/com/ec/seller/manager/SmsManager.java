package com.ec.seller.manager;

import java.util.Map;
import java.util.List;

import com.ec.seller.domain.Sms;
import com.ec.seller.domain.query.SmsQuery;

public interface SmsManager {

	List<Sms> querySms(SmsQuery smsQuery);
	
	Sms queryLastSms(SmsQuery smsQuery);

	Integer addSms(Sms user);

	void updateSms(Sms user);

}
