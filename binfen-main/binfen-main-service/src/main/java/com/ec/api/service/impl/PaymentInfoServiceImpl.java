package com.ec.api.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.ec.api.dao.OrderInfoDao;
import com.ec.api.dao.PaymentInfoDao;
import com.ec.api.domain.OrderInfo;
import com.ec.api.domain.PaymentInfo;
import com.ec.api.domain.query.PaymentInfoQuery;
import com.ec.api.service.PaymentInfoService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;

@Service(value="paymentInfoService")
public class PaymentInfoServiceImpl implements PaymentInfoService {
	private static final Logger log = LoggerFactory.getLogger(PaymentInfoServiceImpl.class);

	private PaymentInfoDao paymentInfoDao;
	private OrderInfoDao orderInfoDao;
	private DataSourceTransactionManager transactionManager;
	
	
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
