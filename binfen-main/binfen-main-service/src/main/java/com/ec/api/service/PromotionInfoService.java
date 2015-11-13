package com.ec.api.service;

import com.ec.api.domain.PromotionInfo;
import com.ec.api.domain.query.PromotionInfoQuery;
import com.ec.api.service.result.Result;

public interface PromotionInfoService {
	/**
	 * 依据商家ID查询商家所属促销列表查看
	 * @param venderUserId
	 * @return
	 */
	public Result getPromotionInfosByPromotionInfoQuery(PromotionInfoQuery promotionInfoQuery);
	/**
	 * 新增促销信息
	 * @return
	 */
	public Result addPromotionInfo(PromotionInfo promotionInfo);
	
	/**
	 * 根据促销ID获取促销信息
	 * @param promotionId
	 * @return
	 */
	public Result getPromotionInfoByPromotionId(Integer promotionId);
	
	/**
	 * 修改促销列表信息
	 * @param promotionInfo
	 * @return
	 */
	public Result updatePromotionInfo(PromotionInfo promotionInfo);
	
	/**
	 * 商家关闭促销
	 * @param venderUserId
	 * @param promotionId
	 * @return
	 */
	public Result closePromotion(Integer venderUserId, Integer promotionId);
	/**
	 * 根据skuId获取商品促销商品信息
	 * @param skuId
	 * @return
	 */
	public Result getPromotionInfoBySkuId(Integer skuId);
	/**
	 * 根据itemId获取商品促销商品信息
	 * @param ItemId
	 * @return
	 */
	public Result getPromotionInfoByItemId(Integer itemId);
	
	/**
	 * 获得首页折扣区top6促销商品信息
	 * @return
	 */
	public Result getIndexTop6DiscountPromotionInfo();
	
	/**
	 * 获得首页产地特供区top6促销商品信息
	 * @return
	 */
	public Result getIndexTop6SpecialPromotionInfo();
	
}
