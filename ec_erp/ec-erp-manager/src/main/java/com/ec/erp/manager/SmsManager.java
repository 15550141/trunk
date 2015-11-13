package com.ec.erp.manager;

import java.util.Map;
import java.util.List;

import com.ec.erp.domain.Sms;
import com.ec.erp.domain.query.SmsQuery;

public interface SmsManager {

	List<Sms> querySms(SmsQuery smsQuery);
	
	Sms queryLastSms(SmsQuery smsQuery);

	Integer addSms(Sms user);

	void updateSms(Sms user);

}
