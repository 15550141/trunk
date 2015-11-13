package com.ec.api.service;

import com.ec.api.domain.SellerEntry;
import com.ec.api.domain.query.SellerEntryQuery;
import com.ec.api.service.result.Result;

public interface SellerEntryService {
	/**
	 * 商家补录金额
	 * @return
	 */
	public Result addSellerEntry(SellerEntry sellerEntry);
	
	/**
	 * 查看补录信息
	 * @param sellerEntryQuery
	 * @return
	 */
	public Result getSellerEntrys(SellerEntryQuery sellerEntryQuery);
}
