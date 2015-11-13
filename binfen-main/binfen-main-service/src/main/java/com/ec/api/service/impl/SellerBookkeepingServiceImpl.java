package com.ec.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ec.api.dao.SellerBookkeepingDao;
import com.ec.api.domain.SellerBookkeeping;
import com.ec.api.domain.query.SellerBookkeepingQuery;
import com.ec.api.service.SellerBookkeepingService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;

@Service(value="sellerBookkeepingService")
public class SellerBookkeepingServiceImpl implements SellerBookkeepingService{
	private static final Logger log = LoggerFactory.getLogger(SellerBookkeepingServiceImpl.class);
	
	private SellerBookkeepingDao sellerBookkeepingDao;
	
	@Override
	public Result addSellerBookkeeping(SellerBookkeeping sellerBookkeeping) {
		Result result = new Result();
		try{
			sellerBookkeepingDao.insert(sellerBookkeeping);
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result getSellerBookkeepingByPage(
			SellerBookkeepingQuery sellerBookkeepingQuery) {
		Result result = new Result();
		try{
			int total = sellerBookkeepingDao.countByCondition(sellerBookkeepingQuery);
			if(total == 0){
				result.setResultCode("3003");
				result.setResultMessage("账单列表不存在");
				return result;
			}
			int totalPage = total / sellerBookkeepingQuery.getPageSize() + 1;
			if(sellerBookkeepingQuery.getPageNo() > totalPage){
				sellerBookkeepingQuery.setPageNo(totalPage);
			}
			
			List<SellerBookkeeping> list = sellerBookkeepingDao.selectByConditionForPage(sellerBookkeepingQuery);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", total);
			map.put("totalPage", totalPage);
			map.put("curPage", sellerBookkeepingQuery.getPageNo());
			map.put("list", list);
			
			result.setResult(map);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	@Override
	public Result selectSellerBookkeepingForCountMoney(
			SellerBookkeepingQuery sellerBookkeepingQuery) {
		Result result = new Result();
		try{
			SellerBookkeeping sellerBookkeeping = sellerBookkeepingDao.selectByConditionForCountMoney(sellerBookkeepingQuery);
			if(sellerBookkeeping == null){
				result.setResultCode("3003");
				result.setResultMessage("账单列表不存在");
				return result;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tradeMoney", sellerBookkeeping.getTradeMoney());
			map.put("paymentMoney", sellerBookkeeping.getPaymentMoney());
			
			result.setResult(map);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	public void setSellerBookkeepingDao(SellerBookkeepingDao sellerBookkeepingDao) {
		this.sellerBookkeepingDao = sellerBookkeepingDao;
	}


}
