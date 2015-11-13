package com.ec.api.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.api.dao.OrderInfoDao;
import com.ec.api.domain.OrderInfo;
import com.ec.api.domain.query.OrderInfoQuery;

public class OrderInfoDaoImpl extends SqlMapClientTemplate implements OrderInfoDao {

	@Override
	public Integer insert(OrderInfo orderInfo) {
		return (Integer)insert("OrderInfo.insert", orderInfo);
	}

	@Override
	public void modify(OrderInfo orderInfo) {
		update("OrderInfo.updateByPrimaryKey", orderInfo);
	}

	@Override
	public OrderInfo selectByOrderId(int orderId) {
		return (OrderInfo)queryForObject("OrderInfo.selectByPrimaryKey",orderId);
	}

	@Override
	public int countByCondition(OrderInfoQuery orderInfoQuery) {
		return (Integer)queryForObject("OrderInfo.countByCondition",orderInfoQuery);
	}

	@Override
	public List<OrderInfo> selectByCondition(OrderInfoQuery orderInfoQuery) {
		return (List<OrderInfo>)queryForList("OrderInfo.selectByCondition",orderInfoQuery);
	}

	@Override
	public List<OrderInfo> selectByConditionForPage(
			OrderInfoQuery orderInfoQuery) {
		return (List<OrderInfo>)queryForList("OrderInfo.selectByConditionForPage",orderInfoQuery);
	}
	
	@Override
	public int modifyPayMoney(OrderInfo orderInfo) {
		return update("OrderInfo.modifyPayMoney", orderInfo);
	}

	@Override
	public int countByUnfinishedOrder(OrderInfoQuery orderInfoQuery) {
		return (Integer)queryForObject("OrderInfo.countByUnfinishedOrder",orderInfoQuery);
	}

	@Override
	public List<OrderInfo> selectByUnfinishedOrder(OrderInfoQuery orderInfoQuery) {
		return (List<OrderInfo>)queryForList("OrderInfo.selectByUnfinishedOrder",orderInfoQuery);
	}
}
