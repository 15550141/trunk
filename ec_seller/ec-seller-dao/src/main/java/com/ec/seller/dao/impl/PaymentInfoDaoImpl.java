package com.ec.seller.dao.impl;


import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.seller.dao.PaymentInfoDao;
import com.ec.seller.domain.PaymentInfo;
import com.ec.seller.domain.query.PaymentInfoQuery;

public class PaymentInfoDaoImpl extends SqlMapClientTemplate implements PaymentInfoDao {

	@Override
	public Integer insert(PaymentInfo paymentInfo) {
		return (Integer)insert("PaymentInfo.insert",paymentInfo);
	}

	@Override
	public List<PaymentInfo> selectByCondition(PaymentInfoQuery paymentInfoQuery) {
		return (List<PaymentInfo>)queryForList("PaymentInfo.selectByCondition",paymentInfoQuery);
	}


}
