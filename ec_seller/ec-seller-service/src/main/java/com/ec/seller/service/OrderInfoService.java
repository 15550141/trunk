package com.ec.seller.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ec.seller.common.utils.PaginatedList;
import com.ec.seller.domain.OrderInfo;
import com.ec.seller.domain.query.OrderInfoQuery;

public interface OrderInfoService {
	/**
	 * 分页查询
	 * @param orderInfoQuery
	 * @return
	 */
	public PaginatedList<OrderInfo> getOrderInfosByPage(OrderInfoQuery orderInfoQuery);
	
	/**
	 * 根据订单号以及商家id查询订单详细信息
	 * @param orderInfoQuery
	 * @return
	 */
	public OrderInfo getOrderInfoByOrderId(OrderInfoQuery orderInfoQuery);
	
	/**
	 * 锁定订单
	 * @param orderId
	 * @param venderUserId
	 * @return
	 */
	public Map<String, Object> lockOrder(Integer orderId, Integer venderUserId, String lockReason);
	
	/**
	 * 解锁订单
	 * @param orderId
	 * @param venderUserId
	 * @return
	 */
	public Map<String, Object> unLockOrder(Integer orderId, Integer venderUserId);
	
	/**
	 * 确认收款
	 * @param orderInfo
	 * @return
	 */
	public Map<String, Object> confirmGetPrice(Integer orderId, Integer venderUserId);
	
	/**
	 * 尾款收款
	 * @param orderInfo
	 * @return
	 */
	public Map<String, Object> confirmGetLastPrice(Integer orderId, Integer venderUserId);
	
	/**
	 * 订单完成
	 * @param orderInfo
	 * @return
	 */
	public Map<String, Object> updateOrderInfoFinish(Integer orderId, Integer venderUserId);
	
	/**
	 * 取消订单
	 * @param orderInfo
	 * @return
	 */
	public Map<String, Object> cancelOrder(Integer orderId, Integer venderUserId);
	
	/**
	 * 添加商家补录信息
	 * @param orderId
	 * @param orderPayType
	 * @param paymentMode
	 * @param paymentMoney
	 * @return
	 */
	public Map<String, Object> addSellerEntry(Integer orderId, Integer orderPayType, Integer paymentMode, BigDecimal paymentMoney, Integer venderUserId);
	
	public Map<String, Object> sendGoods(Integer orderId, Integer venderId, Date estimateSendOutTime);
	
	public Map<String, Object> doEstimateSendOutTime(Integer orderId, Integer venderId, Date estimateSendOutTime);
}
