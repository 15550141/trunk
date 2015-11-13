package com.ec.seller.dao;

import java.util.List;

import com.ec.seller.domain.OrderDetail;


public interface OrderDetailDao{
	
	/**
	 * 添加订单购物车信息
	 * @param orderDetail
	 * @return
	 */
	public Integer insert(OrderDetail orderDetail);


	/**
	 * 依据订单ID查询订单购物车信息
	 * @param orderId
	 * @return
	 */
	public List<OrderDetail> selectByOrderId(int orderId);
	
	
}