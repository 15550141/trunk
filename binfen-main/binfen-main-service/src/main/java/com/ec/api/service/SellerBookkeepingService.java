package com.ec.api.service;

import com.ec.api.domain.SellerBookkeeping;
import com.ec.api.domain.query.SellerBookkeepingQuery;
import com.ec.api.service.result.Result;

public interface SellerBookkeepingService {
	/**
	 * 添加记账本信息
	 * @param sellerBookkeeping
	 * @return
	 */
	public Result addSellerBookkeeping(SellerBookkeeping sellerBookkeeping);
	/**
	 * 根据相应的条件查询记账本信息---分页查询
	 * @param sellerBookkeepingQuery
	 * @return
	 */
	public Result getSellerBookkeepingByPage(SellerBookkeepingQuery sellerBookkeepingQuery);
	/**
	 * 根据相应的条件查询满足条件的记账本信息,并累加金额
	 * @param sellerBookkeepingQuery
	 * @return
	 */
	public Result selectSellerBookkeepingForCountMoney(SellerBookkeepingQuery sellerBookkeepingQuery);
}
