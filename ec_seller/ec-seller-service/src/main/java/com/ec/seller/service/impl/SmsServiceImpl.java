package com.ec.seller.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.seller.common.utils.RedisUtils;
import com.ec.seller.domain.Sms;
import com.ec.seller.domain.query.SmsQuery;
import com.ec.seller.manager.SmsManager;
import com.ec.seller.service.SmsService;

@Service(value = "smsService")
public class SmsServiceImpl implements SmsService{
	@Autowired
	private SmsManager smsManager;

	@Override
	public List<Sms> querySms(SmsQuery smsQuery) {
		return smsManager.querySms(smsQuery);
	}

	@Override
	public Sms queryLastSms(SmsQuery smsQuery) {
		Sms sms = smsManager.queryLastSms(smsQuery);
		return sms;
	}
	
	@Override
	public Integer addSms(Sms user) {
		
		return smsManager.addSms(user);
		
	}

	@Override
	public void updateSms(Sms user) {
		
		smsManager.updateSms(user);
		
	}

	@Override
	public Map<String, Object> sendSms(String mobile) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(mobile != null) {				
			//查询最近60秒内是否已经生成
			SmsQuery smsQuery = new SmsQuery();
			smsQuery.setMobile(mobile);
			Sms lastSms = smsManager.queryLastSms(smsQuery);
			boolean bSendSms = true;
			if(lastSms != null) {
				long lastTime = lastSms.getCreated().getTime();
				long nowTime = new Date().getTime();
				//60秒内不重复发送
				if(nowTime - lastTime < 60000) {
					resultMap.put("msg","error");
					resultMap.put("reason", "60秒内无需重新获取");
					bSendSms = false;
				}
			}

			if(bSendSms) {				
				//生成短信验证码
				Integer code = new Integer(0);
				while(code < 100000 || code > 999999) {
					double number = Math.random() * 1000000;
					code = new Integer((int) number);
				}
				
				String str = "验证码为: " + code;
				Sms sms = new Sms();
				sms.setMobile(mobile);
				sms.setContent(str);
				sms.setStatus(0);
				//写入数据库
				smsManager.addSms(sms);
				
				//写缓存
			
				RedisUtils.set("seller_"+mobile, 60, code.toString());
				}
			}
		return resultMap;
	}
	
	
}
