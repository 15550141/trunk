package com.ec.seller.dao;


import java.util.List;

import com.ec.seller.domain.SellerEntry;
import com.ec.seller.domain.query.SellerEntryQuery;

public interface SellerEntryDao {
	/**
	 * 添加商家补录金额信息
	 * @param sellerEntry
	 * @return
	 */
	public Integer insert(SellerEntry sellerEntry);


	/**
	 * 依据订单ID查询商家补录金额信息
	 * @param orderId
	 * @return
	 */
	public List<SellerEntry> selectByOrderId(int orderId);
	
	/**
	 * 根据相应的条件查询满足条件的商家补录金额信息总数
	 * @param sellerEntryQuery
	 * @return
	 */
	public int countByCondition(SellerEntryQuery sellerEntryQuery);
	
	/**
	 * 根据相应的条件查询商家补录金额信息
	 * @param sellerEntryQuery
	 * @return
	 */
	public List<SellerEntry> selectByCondition(SellerEntryQuery sellerEntryQuery);
	
	/**
	 * 根据相应的条件查询商家补录金额信息---分页查询
	 * @param sellerEntryQuery
	 * @return
	 */
	public List<SellerEntry> selectByConditionForPage(SellerEntryQuery sellerEntryQuery);
	
	public Integer selectSumPayMoneyByCondition(SellerEntry sellerEntry);
}
