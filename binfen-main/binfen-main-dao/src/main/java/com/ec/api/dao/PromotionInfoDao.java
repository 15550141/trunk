package com.ec.api.dao;

import java.util.List;

import com.ec.api.domain.PromotionInfo;
import com.ec.api.domain.query.PromotionInfoQuery;


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
	
	/**
	 * 删除促销的库存数量
	 * @param promotionInfo
	 */
	public int updatePromotionInfoStock(PromotionInfo promotionInfo);


  /**
	 * 根据相应的条件查询满足条件的促销商品信息的总数
	 * @param promotionInfoQuery
	 * @return
	 */
	public int countPromotionProductByCondition(PromotionInfoQuery promotionInfoQuery);
   /**
	 * 根据相应的条件查询促销商品信息---分页查询
	 * @param promotionInfoQuery
	 * @return
	 */
	public List<PromotionInfo> selectPromotionProductByConditionForPage(PromotionInfoQuery promotionInfoQuery);




}