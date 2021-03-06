package com.ec.seller.service;

import java.util.List;

import com.ec.seller.domain.PromotionInfo;
import com.ec.seller.domain.PromotionSku;
import com.ec.seller.domain.query.PromotionSkuQuery;

public interface PromotionSkuService {
	/**
	 * 添加促销信息
	 * @param promotionInfo
	 * @return
	 */
	public Integer insert(PromotionSku promotionSku);
	
	public List<PromotionSku> selectByCondition(PromotionSkuQuery promotionSkuQuery);
	
	/**
	 * 依据促销ID修改促销信息
	 * @param promotionInfo
	 */
	public void modifyByPromotionId(PromotionSku promotionSku);

}
