package com.ec.seller.dao;

import java.util.List;

import com.ec.seller.domain.OrderInfo;
import com.ec.seller.domain.query.OrderInfoQuery;

public interface OrderInfoDao{
	/**
	 * 添加订单信息
	 * @param orderInfo
	 * @return
	 */
	public Integer insert(OrderInfo orderInfo);

	/**
	 * 依据订单ID修改订单信息
	 * @param orderInfo
	 */
	public int modify(OrderInfo orderInfo);

	/**
	 * 依据订单ID查询订单信息
	 * @param userId
	 * @return
	 */
	public OrderInfo selectByOrderId(int orderId);
	
	/**
	 * 根据相应的条件查询满足条件的订单信息的总数
	 * @param orderInfoQuery
	 * @return
	 */
	public int countByCondition(OrderInfoQuery orderInfoQuery);
	
	/**
	 * 根据相应的条件查询订单信息
	 * @param orderInfoQuery
	 * @return
	 */
	public List<OrderInfo> selectByCondition(OrderInfoQuery orderInfoQuery);
	
	/**
	 * 根据相应的条件查询订单信息---分页查询
	 * @param orderInfoQuery
	 * @return
	 */
	public List<OrderInfo> selectByConditionForPage(OrderInfoQuery orderInfoQuery);
	
	public int modifyPayMoney(OrderInfo orderInfo);
}