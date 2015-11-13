//package com.ec.seller.service.impl;
//
//import java.math.BigDecimal;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.stereotype.Service;
//
//import com.ec.seller.common.utils.PaginatedList;
//import com.ec.seller.common.utils.impl.PaginatedArrayList;
//import com.ec.seller.dao.BalanceInfoDao;
//import com.ec.seller.dao.OrderDetailDao;
//import com.ec.seller.dao.OrderInfoDao;
//import com.ec.seller.dao.PaymentInfoDao;
//import com.ec.seller.domain.BalanceInfo;
//import com.ec.seller.domain.OrderDetail;
//import com.ec.seller.domain.OrderInfo;
//import com.ec.seller.domain.PaymentInfo;
//import com.ec.seller.domain.query.BalanceInfoQuery;
//import com.ec.seller.domain.query.OrderInfoQuery;
//import com.ec.seller.domain.query.PaymentInfoQuery;
//import com.ec.seller.service.BalanceInfoService;
//
//@Service(value="balanceInfoService")
//public class BalanceInfoServiceImpl implements BalanceInfoService {
//	private static Log log = LogFactory.getLog(BalanceInfoServiceImpl.class);
//
//	private BalanceInfoDao balanceInfoDao;
//	private OrderInfoDao orderInfoDao;
//	private OrderDetailDao OrderDetailDao;
//	
//	private PaymentInfoDao paymentInfoDao;
//	
//	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	private final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	@Override
//	public PaginatedList<BalanceInfo> getBalanceInfosByPage(
//			BalanceInfoQuery balanceInfoQuery) {
//		PaginatedList<BalanceInfo> balanceInfoList = new PaginatedArrayList<BalanceInfo>(balanceInfoQuery.getPageNo(),balanceInfoQuery.getPageSize());
//		try{
//			int count = balanceInfoDao.countByCondition(balanceInfoQuery);
//			if(count > 0){
//				balanceInfoList.setTotalItem(count);
//				balanceInfoQuery.setStart(balanceInfoList.getStartRow() - 1);
//				List<BalanceInfo> list= balanceInfoDao.selectByConditionForPage(balanceInfoQuery);
//				balanceInfoList.addAll(list);
//				return balanceInfoList;
//			}
//		}catch (Exception e) {
//			log.error("", e);
//		}
//		
//		return balanceInfoList;
//	}
//
//	@Override
//	public BalanceInfo getBalanceInfoByBalanceId(BalanceInfoQuery balanceInfoQuery) {
//		
//		try {
//			List<BalanceInfo> list = balanceInfoDao.selectByCondition(balanceInfoQuery);
//			
//			if(list == null || list.size() == 0){
//				return null;
//			}
//			
//			BalanceInfo balanceInfo = list.get(0);
//			OrderInfoQuery orderInfoQuery = new OrderInfoQuery();
//			orderInfoQuery.setVenderUserId(balanceInfoQuery.getVenderUserId());
//			orderInfoQuery.setOrderStatus(50);
//			
//			Date queryDate = balanceInfo.getBalanceDate();
//			orderInfoQuery.setStartFinishTime(sdf.parse(sdf.format(queryDate)));
//			orderInfoQuery.setEndFinishTime(sdf.parse(sdf.format(new Date(orderInfoQuery.getStartFinishTime().getTime() + 60*60*24*1000))));
//			List<OrderInfo> orderInfoList = orderInfoDao.selectByCondition(orderInfoQuery);
//			
//			for(int i=0;i<orderInfoList.size();i++){
//				OrderInfo orderInfo = orderInfoList.get(i);
//				List<OrderDetail> orderDetailList = OrderDetailDao.selectByOrderId(orderInfo.getOrderId());
//				//TODO 这里有问题
////				orderInfo.setOrderDetail(orderDetailList.get(0));
//				
//				PaymentInfoQuery paymentInfoQuery = new PaymentInfoQuery();
//				paymentInfoQuery.setOrderId(orderInfo.getOrderId());
//				paymentInfoQuery.setPaymentInfoType(2);
//				List<PaymentInfo> paymentInfolist = paymentInfoDao.selectByCondition(paymentInfoQuery);
//				if(paymentInfolist != null && paymentInfolist.size() > 0){
//					BigDecimal paymentGoodsPrice = new BigDecimal(0);
//					for(int j=0;j<paymentInfolist.size();j++){
//						paymentGoodsPrice = paymentGoodsPrice.add(paymentInfolist.get(j).getPaymentMoney());
//					}
//					orderInfo.setPaymentGoodsPrice(paymentGoodsPrice);
//				}
//			}
//			
//			balanceInfo.setOrderInfoList(orderInfoList);
//			return balanceInfo;
//		} catch (Exception e) {
//			log.error("", e);
//		}
//		return null;
//	}
//
//	@Override
//	public Map<String, Object> doFinish(Integer balanceId, Integer venderUserId) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		BalanceInfo balanceInfo = new BalanceInfo();
//		balanceInfo.setBalanceId(balanceId);
//		balanceInfo.setBalanceStatus(4);
//		balanceInfo.setVenderUserId(venderUserId);
//		
//		int result = balanceInfoDao.modify(balanceInfo);
//		if(result == 0){
//			map.put("success", false);
//			map.put("message", "修改失败");
//		}else{
//			map.put("success", true);
//		}
//		return map;
//	}
//	
//	@Override
//	public List<BalanceInfo> selectByCondition(BalanceInfoQuery balanceInfoQuery) {
//		return balanceInfoDao.selectByCondition(balanceInfoQuery);
//	}
//	
//	public void setBalanceInfoDao(BalanceInfoDao balanceInfoDao) {
//		this.balanceInfoDao = balanceInfoDao;
//	}
//
//	public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
//		this.orderInfoDao = orderInfoDao;
//	}
//
//	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
//		OrderDetailDao = orderDetailDao;
//	}
//
//	public void setPaymentInfoDao(PaymentInfoDao paymentInfoDao) {
//		this.paymentInfoDao = paymentInfoDao;
//	}
//
//}
