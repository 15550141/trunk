package com.ec.seller.dao;

import java.util.List;

import com.ec.seller.domain.PromotionProduct;
import com.ec.seller.domain.query.PromotionProductQuery;


public interface PromotionProductDao {
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

	/**
	 * 依据促销ID查询促销信息
	 * @param promotionId
	 * @return
	 */
	public PromotionProduct selectByPromotionId(int promotionId);
	
	/**
	 * 根据相应的条件查询满足条件的促销信息的总数
	 * @param promotionProductQuery
	 * @return
	 */
	public int countByCondition(PromotionProductQuery promotionProductQuery);
	
	/**
	 * 根据相应的条件查询促销信息
	 * @param promotionProductQuery
	 * @return
	 */
	public List<PromotionProduct> selectByCondition(PromotionProductQuery promotionProductQuery);
	
	/**
	 * 根据相应的条件查询促销信息---分页查询
	 * @param promotionProductQuery
	 * @return
	 */
	public List<PromotionProduct> selectByConditionForPage(PromotionProductQuery promotionProductQuery);
	
	int queryPromotionCount(PromotionProduct promotionProduct);

	List<PromotionProduct> queryPromotionList(PromotionProduct promotionProduct);
	
	public List<PromotionProduct> querystartPromotionList(Integer offsetStartProm);
	
	public List<PromotionProduct> querystopPromotionList(Integer offsetStopProm);
	
	public void startPromotion(PromotionProduct promotionProduct);
	
	public void stopPromotion(PromotionProduct promotionProduct);

	public void modifyByPromtionId(PromotionProduct promotionProduct);
	
}