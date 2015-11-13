package com.ec.api.dao;


import java.util.List;

import com.ec.api.domain.SellerBookkeeping;
import com.ec.api.domain.query.SellerBookkeepingQuery;

public interface SellerBookkeepingDao {
	/**
	 * 添加记账本信息
	 * @param sellerBookkeeping
	 * @return
	 */
	public Integer insert(SellerBookkeeping sellerBookkeeping);
	
	/**
	 * 修改记账本信息
	 * @param sellerBookkeeping
	 * @return
	 */
	public Integer modify(SellerBookkeeping sellerBookkeeping);

	/**
	 * 根据相应的条件查询满足条件的记账本信息,并累加金额
	 * @param sellerBookkeepingQuery
	 * @return
	 */
	public SellerBookkeeping selectByConditionForCountMoney(SellerBookkeepingQuery sellerBookkeepingQuery);
		
	/**
	 * 根据相应的条件查询满足条件的记账本信息的总数
	 * @param sellerBookkeepingQuery
	 * @return
	 */
	public int countByCondition(SellerBookkeepingQuery sellerBookkeepingQuery);
	
	
	/**
	 * 根据相应的条件查询记账本信息---分页查询
	 * @param sellerBookkeepingQuery
	 * @return
	 */
	public List<SellerBookkeeping> selectByConditionForPage(SellerBookkeepingQuery sellerBookkeepingQuery);
}
