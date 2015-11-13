package com.ec.erp.manager;

import java.util.List;

import com.ec.erp.common.utils.PaginatedList;
import com.ec.erp.domain.PromotionProduct;
import com.ec.erp.domain.query.PromotionProductQuery;



public interface PromotionProductManager {

	PaginatedList<PromotionProduct> queryPromotionList(PromotionProduct promotionProduct);
	/**
	 * 添加促销信息
	 * @param promotionProduct
	 * @return
	 */
	public Integer insert(PromotionProduct promotionProduct);
	/**
	 * 依据促销ID修改促销信息
	 * @param promotionProduct
	 */
	public void modify(PromotionProduct promotionProduct);
	public void modifyByPromtionId(PromotionProduct promotionProduct);
	PaginatedList<PromotionProduct> queryPromotionPList(
			PromotionProduct promotionProduct);
	List<PromotionProduct> selectByCondition(
			PromotionProductQuery promotionProductQuery);




}
