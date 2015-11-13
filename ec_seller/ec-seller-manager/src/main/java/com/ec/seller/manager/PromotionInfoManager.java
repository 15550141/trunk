package com.ec.seller.manager;

import com.ec.seller.common.utils.PaginatedList;
import com.ec.seller.domain.PromotionInfo;



public interface PromotionInfoManager {

	PaginatedList<PromotionInfo> queryPromotionList(PromotionInfo promotionInfo);
	/**
	 * 添加促销信息
	 * @param promotionInfo
	 * @return
	 */
	public Integer insert(PromotionInfo promotionInfo);
	/**
	 * 依据促销ID修改促销信息
	 * @param promotionInfo
	 */
	public void modify(PromotionInfo promotionInfo);
	
	public PromotionInfo selectByPromotionId(int promotionId);




}
