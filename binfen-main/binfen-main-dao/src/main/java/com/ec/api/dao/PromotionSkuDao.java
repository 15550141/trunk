package com.ec.api.dao;

import java.util.List;

import com.ec.api.domain.PromotionSku;
import com.ec.api.domain.query.PromotionSkuQuery;


public interface PromotionSkuDao {
	/**
	 * 添加促销信息
	 * @param promotionInfo
	 * @return
	 */
	public Integer insert(PromotionSku promotionSku);

	/**
	 * 依据促销ID修改促销信息
	 * @param promotionInfo
	 */
	public void modify(PromotionSku promotionSku);

	/**
	 * 依据促销ID查询促销信息
	 * @param promotionId
	 * @return
	 */
	public PromotionSku selectByPromotionId(int promotionId);
	
	/**
	 * 依据促销ID查询促销信息
	 * @param promotionId
	 * @return
	 */
	public PromotionSku selectBySkuId(int skuId);
	
	/**
	 * 根据相应的条件查询满足条件的促销信息的总数
	 * @param promotionInfoQuery
	 * @return
	 */
	public int countByCondition(PromotionSkuQuery promotionSkuQuery);
	
	/**
	 * 根据相应的条件查询促销信息
	 * @param promotionInfoQuery
	 * @return
	 */
	public List<PromotionSku> selectByCondition(PromotionSkuQuery promotionSkuQuery);
	
	/**
	 * 根据相应的条件查询促销信息---分页查询
	 * @param promotionInfoQuery
	 * @return
	 */
	public List<PromotionSku> selectByConditionForPage(PromotionSkuQuery promotionSkuQuery);
}