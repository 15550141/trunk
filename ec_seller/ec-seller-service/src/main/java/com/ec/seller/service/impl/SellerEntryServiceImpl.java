package com.ec.seller.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.ec.seller.dao.SellerEntryDao;
import com.ec.seller.domain.SellerEntry;
import com.ec.seller.service.SellerEntryService;

@Service(value="sellerEntryService")
public class SellerEntryServiceImpl implements SellerEntryService {

	private SellerEntryDao sellerEntryDao;
		
	@Override
	public BigDecimal selectSumPayMoneyByCondition(Integer orderId, Integer orderPayType) {
		SellerEntry sellerEntry = new SellerEntry();
		sellerEntry.setOrderId(orderId);
		sellerEntry.setOrderPayType(orderPayType);
		Integer result = sellerEntryDao.selectSumPayMoneyByCondition(sellerEntry);
		if(result != null){
			return new BigDecimal(result).divide(new BigDecimal(100));
		}
		return null;
	}

	public void setSellerEntryDao(SellerEntryDao sellerEntryDao) {
		this.sellerEntryDao = sellerEntryDao;
	}

}
