package com.ec.erp.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.ec.erp.common.utils.PaginatedList;
import com.ec.erp.common.utils.impl.PaginatedArrayList;
import com.ec.erp.dao.OrderDetailDao;
import com.ec.erp.dao.OrderInfoDao;
import com.ec.erp.dao.SellerEntryDao;
import com.ec.erp.domain.OrderDetail;
import com.ec.erp.domain.OrderInfo;
import com.ec.erp.domain.SellerEntry;
import com.ec.erp.domain.query.OrderInfoQuery;
import com.ec.erp.service.OrderInfoService;

@Service(value="orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {
	private static Log log = LogFactory.getLog(OrderInfoServiceImpl.class);
	private OrderInfoDao orderInfoDao;
	private OrderDetailDao orderDetailDao;
	private SellerEntryDao sellerEntryDao;
	private DataSourceTransactionManager transactionManager;
	@Override
	public PaginatedList<OrderInfo> getOrderInfosByPage(OrderInfoQuery orderInfoQuery) {
		PaginatedList<OrderInfo> OrderInfoList = new PaginatedArrayList<OrderInfo>(orderInfoQuery.getPageNo(),orderInfoQuery.getPageSize());
		try{
			int count = orderInfoDao.countByCondition(orderInfoQuery);
			if(count > 0){
				OrderInfoList.setTotalItem(count);
				orderInfoQuery.setStart(OrderInfoList.getStartRow() - 1);
				List<OrderInfo> list= orderInfoDao.selectByConditionForPage(orderInfoQuery);
				for(OrderInfo orderInfo : list){
					List<OrderDetail> orderDetailList = orderDetailDao.selectByOrderId(orderInfo.getOrderId());
					if(orderDetailList != null && orderDetailList.size() > 0){
						orderInfo.setOrderDetail(orderDetailList.get(0));
					}
				}
				OrderInfoList.addAll(list);
				return OrderInfoList;
			}
		}catch (Exception e) {
			log.error("", e);
		}
		
		return OrderInfoList;
	}
	
	@Override
	public OrderInfo getOrderInfoByOrderId(OrderInfoQuery orderInfoQuery) {
		OrderInfo orderInfo = null;
		try{
			List<OrderInfo> list = orderInfoDao.selectByCondition(orderInfoQuery);
			if(list != null && list.size() > 0){
				orderInfo = list.get(0);
				List<OrderDetail> orderDetailList = orderDetailDao.selectByOrderId(orderInfo.getOrderId());
				orderInfo.setOrderDetail(orderDetailList.get(0));
			}
		}catch (Exception e) {
			log.error("", e);
		}
		return orderInfo;
	}
	
	@Override
	public Map<String, Object> lockOrder(Integer orderId, Integer venderUserId, String lockReason) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderId(orderId);
		orderInfo.setVenderUserId(venderUserId);
		orderInfo.setLockReason(lockReason);
		orderInfo.setLockStatus(1);
		int result = orderInfoDao.modify(orderInfo);
		if(result == 0){
			map.put("success", false);
			map.put("message", "修改失败");
		}else{
			map.put("success", true);
		}
		return map;
	}

	@Override
	public Map<String, Object> unLockOrder(Integer orderId, Integer venderUserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderId(orderId);
		orderInfo.setVenderUserId(venderUserId);
		orderInfo.setLockStatus(0);
		int result = orderInfoDao.modify(orderInfo);
		if(result == 0){
			map.put("success", false);
			map.put("message", "修改失败");
		}else{
			map.put("success", true);
		}
		return map;
	}

	@Override
	public Map<String, Object> confirmGetPrice(Integer orderId,
			Integer venderUserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderId(orderId);
		orderInfo.setVenderUserId(venderUserId);
		orderInfo.setOrderStatus(2);
		
		int result = orderInfoDao.modify(orderInfo);
		if(result == 0){
			map.put("success", false);
			map.put("message", "修改失败");
		}else{
			map.put("success", true);
		}
		return map;
	}
	
	@Override
	public Map<String, Object> confirmGetLastPrice(Integer orderId,
			Integer venderUserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderId(orderId);
		orderInfo.setVenderUserId(venderUserId);
		orderInfo.setOrderStatus(4);
		
		int result = orderInfoDao.modify(orderInfo);
		if(result == 0){
			map.put("success", false);
			map.put("message", "修改失败");
		}else{
			map.put("success", true);
		}
		return map;
	}
	
	@Override
	public Map<String, Object> addSellerEntry(final Integer orderId,
			final Integer orderPayType, final Integer paymentMode, final BigDecimal paymentMoney, final Integer venderUserId) {
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		try{
			new TransactionTemplate(transactionManager).execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					SellerEntry se = new SellerEntry();
					se.setOrderId(orderId);
					se.setOrderPayType(orderPayType);
					se.setPaymentMode(paymentMode);
					se.setPaymentMoney(paymentMoney);
					
					if(sellerEntryDao.insert(se) > 0){//添加一条记录
						//修改实际支付金额
						OrderInfo orderInfo = new OrderInfo();
						orderInfo.setPayMoney(paymentMoney);
						orderInfo.setVenderUserId(venderUserId);
						orderInfo.setOrderId(orderId);
						if(orderInfoDao.modifyPayMoney(orderInfo) > 0){
							map.put("success", true);
							return;
						}
					}
				}
			});
			boolean success = (Boolean)map.get("success");
			if(success){
				return map;
			}
		}catch (Exception e) {
			log.error("", e);
		}
		map.put("success", false);
		map.put("message", "添加失败");
		return map;
	}
	
	@Override
	public Map<String, Object> sendGoods(Integer orderId, Integer venderId) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		OrderInfoQuery query = new OrderInfoQuery();
		query.setOrderId(orderId);
		query.setVenderUserId(venderId);
		List<OrderInfo> list = orderInfoDao.selectByCondition(query);
		
		if(list == null || list.size() == 0){
			map.put("success", false);
			map.put("message", "订单不存在");
			return map;
		}
		
		OrderInfo orderInfo = list.get(0);
		if(orderInfo.getOrderStatus() == 51){//订单已取消
			map.put("success", false);
			map.put("message", "该订单已被取消");
			return map;
		}
		if(orderInfo.getOrderStatus() >= 5){
			map.put("success", false);
			map.put("message", "该订单已发货");
			return map;
		}
		
		orderInfo.setOrderId(orderId);
		orderInfo.setVenderUserId(venderId);
		orderInfo.setOrderStatus(5);
		
		int result = orderInfoDao.modify(orderInfo);
		if(result == 0){
			map.put("success", false);
			map.put("message", "修改失败");
		}else{
			map.put("success", true);
		}
		return map;
	}
	
	@Override
	public Map<String, Object> updateOrderInfoFinish(Integer orderId,
			Integer venderUserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		OrderInfoQuery query = new OrderInfoQuery();
		query.setOrderId(orderId);
		query.setVenderUserId(venderUserId);
		List<OrderInfo> list = orderInfoDao.selectByCondition(query);
		
		if(list == null || list.size() == 0){
			map.put("success", false);
			map.put("message", "订单不存在");
			return map;
		}
		
		OrderInfo orderInfo = list.get(0);
		if(orderInfo.getOrderStatus() == 51){//订单已取消
			map.put("success", false);
			map.put("message", "该订单已被取消");
			return map;
		}
		
		if(!((orderInfo.getOrderStatus() == 6 && orderInfo.getOrderType() == 1) || (orderInfo.getOrderStatus() == 4 && orderInfo.getOrderType() == 2))){
			map.put("success", false);
			map.put("message", "此订单不能标记为完成订单");
			return map;
		}
		
		orderInfo.setOrderId(orderId);
		orderInfo.setVenderUserId(venderUserId);
		orderInfo.setOrderStatus(50);//标记为订单完成
		
		int result = orderInfoDao.modify(orderInfo);
		if(result == 0){
			map.put("success", false);
			map.put("message", "修改失败");
		}else{
			map.put("success", true);
		}
		return map;
	}
	
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
	}

	public void setSellerEntryDao(SellerEntryDao sellerEntryDao) {
		this.sellerEntryDao = sellerEntryDao;
	}

	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

}
