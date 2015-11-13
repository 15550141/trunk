package com.ec.api.service;

import com.ec.api.domain.PaymentInfo;
import com.ec.api.domain.query.PaymentInfoQuery;
import com.ec.api.service.result.Result;

public interface PaymentInfoService {
	/**
	 * 添加订单支付记录
	 * @param paymentInfo
	 * @return
	 */
	public Result addPaymentInfo(PaymentInfo paymentInfo);
	
	/**
	 * 分页查询
	 * @param paymentInfoQuery
	 * @return
	 */
	public Result getPaymentInfos(PaymentInfoQuery paymentInfoQuery);
	
}
