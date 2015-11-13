package com.ec.erp.service;

import java.util.Map;

import com.ec.erp.domain.PromotionInfo;

public interface PromotionInfoService {

	Map<String, Object> queryPromotionList(PromotionInfo promotionInfo);

	Map<String, Object> queryItem(Integer itemId);
	
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
