package com.ec.seller.service;

import java.util.List;
import java.util.Map;

import com.ec.seller.domain.PromotionProduct;
import com.ec.seller.domain.query.PromotionProductQuery;

public interface PromotionProductService {

	Map<String, Object> queryPromotionList(PromotionProduct promotionProduct);

	Map<String, Object> queryItem(Integer itemId);
	
	/**
	 * 添加促销信息
	 * @param promotionProduct
	 * @return
	 */
	public Integer insert(PromotionProduct promotionProduct);
	/**
	 * 依据促销ID修改促销信息
	 * @param promotionInfo
	 */
	public void modify(PromotionProduct promotionProduct);

	public void modifyByPromtionId(PromotionProduct promotionProduct);
	
	public List<PromotionProduct> selectByCondition(PromotionProductQuery promotionProductQuery);


}
