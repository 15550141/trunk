package com.ec.seller.dao;

import java.util.List;

import com.ec.seller.domain.PromotionInfo;
import com.ec.seller.domain.query.PromotionInfoQuery;


public interface PromotionInfoDao {
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

	/**
	 * 依据促销ID查询促销信息
	 * @param promotionId
	 * @return
	 */
	public PromotionInfo selectByPromotionId(int promotionId);
	
	/**
	 * 根据相应的条件查询满足条件的促销信息的总数
	 * @param promotionInfoQuery
	 * @return
	 */
	public int countByCondition(PromotionInfoQuery promotionInfoQuery);
	
	/**
	 * 根据相应的条件查询促销信息
	 * @param promotionInfoQuery
	 * @return
	 */
	public List<PromotionInfo> selectByCondition(PromotionInfoQuery promotionInfoQuery);
	
	/**
	 * 根据相应的条件查询促销信息---分页查询
	 * @param promotionInfoQuery
	 * @return
	 */
	public List<PromotionInfo> selectByConditionForPage(PromotionInfoQuery promotionInfoQuery);
	
	int queryPromotionCount(PromotionInfo promotionInfo);

	List<PromotionInfo> queryPromotionList(PromotionInfo promotionInfo);
	
	public List<PromotionInfo> querystartPromotionList(Integer offsetStartProm);
	
	public List<PromotionInfo> querystopPromotionList(Integer offsetStopProm);
	
	public void startPromotion(PromotionInfo promotionInfo);
	
	public void stopPromotion(PromotionInfo promotionInfo);
	
}