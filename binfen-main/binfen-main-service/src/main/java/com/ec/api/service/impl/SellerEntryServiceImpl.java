package com.ec.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ec.api.dao.OrderInfoDao;
import com.ec.api.dao.SellerEntryDao;
import com.ec.api.domain.OrderInfo;
import com.ec.api.domain.SellerEntry;
import com.ec.api.domain.query.SellerEntryQuery;
import com.ec.api.service.SellerEntryService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;

@Service(value="sellerEntryService")
public class SellerEntryServiceImpl implements  SellerEntryService{
	private static final Logger log = LoggerFactory.getLogger(SellerEntryServiceImpl.class);
	
	private SellerEntryDao sellerEntryDao;
	
	private OrderInfoDao orderInfoDao;
	@Override
	public Result addSellerEntry(SellerEntry sellerEntry) {
		Result result = new Result();
		try{
			OrderInfo orderInfo = orderInfoDao.selectByOrderId(sellerEntry.getOrderId());
			if(orderInfo == null ){
				result.setResultCode("9004");
				result.setResultMessage("该订单不存在");
				return result;
			}
			
			sellerEntryDao.insert(sellerEntry);
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result getSellerEntrys(SellerEntryQuery sellerEntryQuery) {
		Result result = new Result();
		try{
			
//			int total = sellerEntryDao.countByCondition(sellerEntryQuery);
//			if(total == 0){
//				result.setResultCode("3005");
//				result.setResultMessage("该订单补录信息不存在");
//				return result;
//			}
//			int totalPage = total / sellerEntryQuery.getPageSize() + 1;
//			if(sellerEntryQuery.getPageNo() > totalPage){
//				sellerEntryQuery.setPageNo(totalPage);
//			}
//			List<SellerEntry> list = sellerEntryDao.selectByCondition(sellerEntryQuery);
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("total", total);
//			map.put("totalPage", totalPage);
//			map.put("curPage", sellerEntryQuery.getPageNo());
//			map.put("list", list);
//			
//			result.setResult(map);
			
			List<SellerEntry> list = sellerEntryDao.selectByCondition(sellerEntryQuery);
			if(list == null || list.size() == 0){
				result.setResultCode("3005");
				result.setResultMessage("该订单补录信息不存在");
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

	public void setSellerEntryDao(SellerEntryDao sellerEntryDao) {
		this.sellerEntryDao = sellerEntryDao;
	}

	public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
	}

}
