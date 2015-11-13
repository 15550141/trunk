package com.ec.erp.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ec.erp.common.utils.PaginatedList;
import com.ec.erp.common.utils.impl.PaginatedArrayList;
import com.ec.erp.dao.BalanceInfoDao;
import com.ec.erp.dao.BusinessUserExtDao;
import com.ec.erp.dao.OrderDetailDao;
import com.ec.erp.dao.OrderInfoDao;
import com.ec.erp.dao.PaymentInfoDao;
import com.ec.erp.dao.UserInfoDAO;
import com.ec.erp.domain.BalanceInfo;
import com.ec.erp.domain.BusinessUserExt;
import com.ec.erp.domain.OrderDetail;
import com.ec.erp.domain.OrderInfo;
import com.ec.erp.domain.PaymentInfo;
import com.ec.erp.domain.UserInfo;
import com.ec.erp.domain.query.BalanceInfoQuery;
import com.ec.erp.domain.query.BusinessUserExtQuery;
import com.ec.erp.domain.query.OrderInfoQuery;
import com.ec.erp.domain.query.PaymentInfoQuery;
import com.ec.erp.domain.query.UserInfoQuery;
import com.ec.erp.service.BalanceInfoService;

@Service(value="balanceInfoService")
public class BalanceInfoServiceImpl implements BalanceInfoService {
	private static Log log = LogFactory.getLog(BalanceInfoServiceImpl.class);

	private BalanceInfoDao balanceInfoDao;
	private OrderInfoDao orderInfoDao;
	private OrderDetailDao OrderDetailDao;
	
	private PaymentInfoDao paymentInfoDao;
	
	private BusinessUserExtDao businessUserExtDao;
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public PaginatedList<BalanceInfo> getBalanceInfosByPage(
			BalanceInfoQuery balanceInfoQuery) {
		PaginatedList<BalanceInfo> balanceInfoList = new PaginatedArrayList<BalanceInfo>(balanceInfoQuery.getPageNo(),balanceInfoQuery.getPageSize());
		//当前台同时输入商家ID和商家名称 时，以商家ID为准，如果前台输入商家名称，没有输入商家ID，则先根据商家名称查询商家ID，查询完后，再把商家ID从query里去掉，不显示在页面上
		boolean deleteVenderUserId = false;
		try{
			if(balanceInfoQuery.getVenderUserId() == null && StringUtils.isNotBlank(balanceInfoQuery.getVenderUserShopName())){
				BusinessUserExtQuery businessUserExtQuery = new BusinessUserExtQuery();
				businessUserExtQuery.setShopName(balanceInfoQuery.getVenderUserShopName());
				List<BusinessUserExt> list = businessUserExtDao.selectByCondition(businessUserExtQuery);
				if(list != null && list.size() > 0){
					balanceInfoQuery.setVenderUserId(list.get(0).getUserId());
				}
				deleteVenderUserId = true;
			}
			
			int count = balanceInfoDao.countByCondition(balanceInfoQuery);
			if(count > 0){
				balanceInfoList.setTotalItem(count);
				balanceInfoQuery.setStart(balanceInfoList.getStartRow() - 1);
				List<BalanceInfo> list= balanceInfoDao.selectByConditionForPage(balanceInfoQuery);
				for(int i=0;i<list.size();i++){
					BalanceInfo balanceInfo = list.get(i);
					BusinessUserExt businessUserExt = businessUserExtDao.selectByUserId(balanceInfo.getVenderUserId());
					balanceInfo.setVenderUserShopName(businessUserExt != null ? businessUserExt.getShopName() : null);
				}
				balanceInfoList.addAll(list);
				return balanceInfoList;
			}
		}catch (Exception e) {
			log.error("", e);
		}finally{
			if(deleteVenderUserId){
				balanceInfoQuery.setVenderUserId(null);
			}
		}
		
		return balanceInfoList;
	}

	@Override
	public BalanceInfo getBalanceInfoByBalanceId(BalanceInfoQuery balanceInfoQuery) {
		
		try {
			
			if(balanceInfoQuery.getVenderUserId() == null && StringUtils.isNotBlank(balanceInfoQuery.getVenderUserShopName())){
				BusinessUserExtQuery businessUserExtQuery = new BusinessUserExtQuery();
				businessUserExtQuery.setShopName(balanceInfoQuery.getVenderUserShopName());
				List<BusinessUserExt> list = businessUserExtDao.selectByCondition(businessUserExtQuery);
				if(list != null && list.size() > 0){
					balanceInfoQuery.setVenderUserId(list.get(0).getUserId());
				}
			}
			
			List<BalanceInfo> list = balanceInfoDao.selectByCondition(balanceInfoQuery);
			
			if(list == null || list.size() == 0){
				return null;
			}
			
			BalanceInfo balanceInfo = list.get(0);
			OrderInfoQuery orderInfoQuery = new OrderInfoQuery();
			orderInfoQuery.setVenderUserId(balanceInfo.getVenderUserId());
			orderInfoQuery.setOrderStatus(50);
			
			Date queryDate = balanceInfo.getBalanceDate();
			orderInfoQuery.setStartFinishTime(sdf.parse(sdf.format(queryDate)));
			orderInfoQuery.setEndFinishTime(sdf.parse(sdf.format(new Date(orderInfoQuery.getStartFinishTime().getTime() + 60*60*24*1000))));
			List<OrderInfo> orderInfoList = orderInfoDao.selectByCondition(orderInfoQuery);
			
			for(int i=0;i<orderInfoList.size();i++){
				OrderInfo orderInfo = orderInfoList.get(i);
				List<OrderDetail> orderDetailList = OrderDetailDao.selectByOrderId(orderInfo.getOrderId());
				orderInfo.setOrderDetail(orderDetailList.get(0));
				
				PaymentInfoQuery paymentInfoQuery = new PaymentInfoQuery();
				paymentInfoQuery.setOrderId(orderInfo.getOrderId());
				paymentInfoQuery.setPaymentInfoType(2);
				List<PaymentInfo> paymentInfolist = paymentInfoDao.selectByCondition(paymentInfoQuery);
				if(paymentInfolist != null && paymentInfolist.size() > 0){
					BigDecimal paymentGoodsPrice = new BigDecimal(0);
					for(int j=0;j<paymentInfolist.size();j++){
						paymentGoodsPrice = paymentGoodsPrice.add(paymentInfolist.get(j).getPaymentMoney());
					}
					orderInfo.setPaymentGoodsPrice(paymentGoodsPrice);
				}
			}
			
			BusinessUserExt businessUserExt = businessUserExtDao.selectByUserId(balanceInfo.getVenderUserId());
			balanceInfo.setVenderUserShopName(businessUserExt.getShopName());
			
			balanceInfo.setOrderInfoList(orderInfoList);
			return balanceInfo;
		} catch (Exception e) {
			log.error("", e);
		}
		return null;
	}

	@Override
	public Map<String, Object> doAgree(BalanceInfo balanceInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int result = balanceInfoDao.modify(balanceInfo);
		if(result == 0){
			map.put("success", false);
			map.put("message", "修改失败");
		}else{
			map.put("success", true);
		}
		return map;
	}
	
	@Override
	public List<BalanceInfo> selectByCondition(BalanceInfoQuery balanceInfoQuery) {
		List<BalanceInfo> list = null;
		try{
			if(balanceInfoQuery.getVenderUserId() == null && StringUtils.isNotBlank(balanceInfoQuery.getVenderUserShopName())){
				BusinessUserExtQuery businessUserExtQuery = new BusinessUserExtQuery();
				businessUserExtQuery.setShopName(balanceInfoQuery.getVenderUserShopName());
				List<BusinessUserExt> userList = businessUserExtDao.selectByCondition(businessUserExtQuery);
				if(userList != null && userList.size() > 0){
					balanceInfoQuery.setVenderUserId(userList.get(0).getUserId());
				}
			}
			
			list = balanceInfoDao.selectByCondition(balanceInfoQuery);
			if(list != null && list.size() > 0){
				for(int i=0;i<list.size();i++){
					BalanceInfo balanceInfo = list.get(i);
					BusinessUserExt businessUserExt = businessUserExtDao.selectByUserId(balanceInfo.getVenderUserId());
					balanceInfo.setVenderUserShopName(businessUserExt != null ? businessUserExt.getShopName() : null);
				}
			}
		}catch (Exception e) {
			log.error("", e);
		}
		return list;
	}
	
	public void setBalanceInfoDao(BalanceInfoDao balanceInfoDao) {
		this.balanceInfoDao = balanceInfoDao;
	}

	public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
	}

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		OrderDetailDao = orderDetailDao;
	}

	public void setPaymentInfoDao(PaymentInfoDao paymentInfoDao) {
		this.paymentInfoDao = paymentInfoDao;
	}

	public void setBusinessUserExtDao(BusinessUserExtDao businessUserExtDao) {
		this.businessUserExtDao = businessUserExtDao;
	}

}
