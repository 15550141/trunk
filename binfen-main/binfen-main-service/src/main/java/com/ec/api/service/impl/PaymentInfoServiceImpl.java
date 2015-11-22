package com.ec.api.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.ec.api.common.utils.JsonUtils;
import com.ec.api.dao.OrderInfoDao;
import com.ec.api.dao.PaymentInfoDao;
import com.ec.api.domain.OrderInfo;
import com.ec.api.domain.PaymentInfo;
import com.ec.api.domain.query.PaymentInfoQuery;
import com.ec.api.service.OrderInfoService;
import com.ec.api.service.PaymentInfoService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;

@Service(value="paymentInfoService")
public class PaymentInfoServiceImpl implements PaymentInfoService {
	private static final Logger log = LoggerFactory.getLogger(PaymentInfoServiceImpl.class);

	private PaymentInfoDao paymentInfoDao;
	private OrderInfoDao orderInfoDao;
	private DataSourceTransactionManager transactionManager;
	
	private OrderInfoService orderInfoService;
	
	//用户发起支付请求
	@Override
	public Result userCreatePayment(PaymentInfo paymentInfo) {
		Result result = new Result();
		try{
			if(paymentInfo == null || paymentInfo.getOrderId() == null || paymentInfo.getUid() == null){
				result.setSuccess(false);
				result.setResultMessage("参数不正确");
				return result;
			}
			//获取订单信息
			OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderIdAndUserId(paymentInfo.getOrderId(), paymentInfo.getUid());
			if(orderInfo == null){
				result.setSuccess(false);
				result.setResultMessage("该订单号不存在");
				return result;
			}
			
			//此处放订单详细信息
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderMoney", orderInfo.getBigDecimalOrderMoney());
			map.put("orderId", orderInfo.getOrderId());
			map.put("consigneeName", orderInfo.getConsigneeName());
			paymentInfo.setPaymentInfoMessage(JsonUtils.writeValue(map));

			//发起支付类型
			paymentInfo.setPaymentInfoType(1);
			//订单总金额
			paymentInfo.setPaymentMoney(orderInfo.getOrderMoney());
			//第三方支付单号
			String paymentNumber = this.getWxPayId(paymentInfo);
			if(StringUtils.isBlank(paymentNumber)){
				result.setSuccess(false);
				result.setResultMessage("调用微信支付失败，请稍后重试");
				return result;
			}
			paymentInfo.setPaymentNumber(paymentNumber);
			
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, 1);
			paymentInfo.setValidOrder(c.getTime());
			
			paymentInfoDao.insert(paymentInfo);
			
			result.setSuccess(true);
		}catch (Exception e) {
			log.error("添加支付信息异常	orderId="+paymentInfo.getOrderId(), e);
			EcUtils.setExceptionResult(result);
		}
		
		return result;
	}
	
	//微信支付结果回调函数
	@Override
	public String wxCallback(String callbackString) {
//		Result result = new Result();
//		try{
//			Document doc = DocumentHelper.parseText(callbackString);
//			Element root = doc.getRootElement();
//			Iterator<Element> it = root.elementIterator();
//			
////			String 
//			while(it.hasNext()){
//				
//			}
//			
//			
//			
//			PaymentInfo paymentInfo = new PaymentInfo();
//			//获取订单信息
//			OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderIdAndUserId(paymentInfo.getOrderId(), paymentInfo.getUid());
//			if(orderInfo == null){
//				result.setSuccess(false);
//				result.setResultMessage("该订单号不存在");
//				return null;
//			}
//			
//			//此处放订单详细信息
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("orderMoney", orderInfo.getBigDecimalOrderMoney());
//			map.put("orderId", orderInfo.getOrderId());
//			map.put("consigneeName", orderInfo.getConsigneeName());
//			paymentInfo.setPaymentInfoMessage(JsonUtils.writeValue(map));
//
//			//发起支付类型
//			paymentInfo.setPaymentInfoType(1);
//			//订单总金额
//			paymentInfo.setPaymentMoney(orderInfo.getOrderMoney());
//			//第三方支付单号
//			String paymentNumber = this.getWxPayId(paymentInfo);
//			if(StringUtils.isBlank(paymentNumber)){
//				result.setSuccess(false);
//				result.setResultMessage("调用微信支付失败，请稍后重试");
//				return null;
//			}
//			paymentInfo.setPaymentNumber(paymentNumber);
//			
//			Calendar c = Calendar.getInstance();
//			c.add(Calendar.DATE, 1);
//			paymentInfo.setValidOrder(c.getTime());
//			
//			paymentInfoDao.insert(paymentInfo);
//			
//			result.setSuccess(true);
//		}catch (Exception e) {
//			log.error("添加支付信息异常	orderId="+paymentInfo.getOrderId(), e);
//			EcUtils.setExceptionResult(result);
//		}
//		
//		return result;
		
		return null;
	}
	
	private String getWxPayId(PaymentInfo paymentInfo){
		
		return null;
	}
	
	//------------------------------------------------------------------------------------------------------------
	@Override
	public Result addPaymentInfo(final PaymentInfo paymentInfo) {
		final Result result = new Result();
		try{
			final OrderInfo orderInfo = orderInfoDao.selectByOrderId(paymentInfo.getOrderId());
			if(orderInfo == null){
				result.setResultCode("9004");
				result.setResultMessage("该订单不存在");
				return result;
			}
			PaymentInfoQuery query = new PaymentInfoQuery();
			query.setOrderPayType(paymentInfo.getOrderPayType());
			query.setPaymentInfoType(2);//支付成功标示，如果支付成功了，则不让再支付了。
			query.setOrderId(paymentInfo.getOrderId());
			List<PaymentInfo> list = paymentInfoDao.selectByCondition(query);
			
			if(list != null && list.size() > 0){
				result.setResultCode("3006");
				result.setResultMessage("该订单已经被支付，不能重复支付");
				return result;
			}
			
			new TransactionTemplate(transactionManager).execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					if(paymentInfoDao.insert(paymentInfo) <= 0){
						throw new RuntimeException("添加支付信息失败");
					}
					
					//修改订单实际支付金额
					OrderInfo dbOrderInfo = new OrderInfo();
					dbOrderInfo.setVenderUserId(orderInfo.getVenderUserId());
					dbOrderInfo.setOrderId(orderInfo.getOrderId());
//					dbOrderInfo.setPayMoney(paymentInfo.getPaymentMoney());
					if(orderInfoDao.modifyPayMoney(dbOrderInfo) <= 0){
						throw new RuntimeException("修改订单应付金额失败");
					}
					
					result.setResult(true);
					EcUtils.setSuccessResult(result);
				}
			});
			
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result getPaymentInfos(PaymentInfoQuery paymentInfoQuery) {
		Result result = new Result();
		try{
			List<PaymentInfo> list = paymentInfoDao.selectByCondition(paymentInfoQuery);
			if(list == null || list.size() == 0){
				result.setResultCode("3004");
				result.setResultMessage("支付信息不存在");
				return result;
			}
			result.setResult(list);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	public void setPaymentInfoDao(PaymentInfoDao paymentInfoDao) {
		this.paymentInfoDao = paymentInfoDao;
	}

	public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
	}

	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	

	
}
