package com.ec.erp.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.erp.dao.OrderDetailDao;
import com.ec.erp.domain.OrderDetail;

public class OrderDetailDaoImpl extends SqlMapClientTemplate implements OrderDetailDao {

	@Override
	public Integer insert(OrderDetail orderDetail) {
		return (Integer)insert("OrderDetail.insert", orderDetail);
	}

	@Override
	public List<OrderDetail> selectByOrderId(int orderId) {
		return (List<OrderDetail>)queryForList("OrderDetail.selectByOrderId",orderId);
	}

}
