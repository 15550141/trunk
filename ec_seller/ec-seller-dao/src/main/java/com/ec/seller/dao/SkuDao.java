package com.ec.seller.dao;

import java.util.List;

import com.ec.seller.domain.Sku;
import com.ec.seller.domain.query.SkuQuery;


public interface SkuDao {
	/**
	 * 添加SKU信息
	 * @param sku
	 * @return
	 */
	public Integer insert(Sku sku);

	/**
	 * 依据SKU_ID修改SKU信息
	 * @param sku
	 */
	public void modify(Sku sku);
	
	/**
	 * 依据Item_Id修改SKU信息(无销售属性时)
	 * @param sku
	 */
	public void modifyByItemId(Sku sku);

	/**
	 * 依据用户ID查询SKU信息
	 * @param skuId
	 * @return
	 */
	public Sku selectBySkuId(int skuId);
	
	/**
	 * 根据相应的条件查询满足条件的SKU信息的总数
	 * @param skuQuery
	 * @return
	 */
	public int countByCondition(SkuQuery skuQuery);
	
	/**
	 * 根据相应的条件查询SKU信息
	 * @param skuQuery
	 * @return
	 */
	public List<Sku> selectByCondition(SkuQuery skuQuery);
	
	/**
	 * 根据相应的条件查询SKU信息-分页
	 * @param skuQuery
	 * @return
	 */
	public List<Sku> selectByConditionForPage(SkuQuery skuQuery);
	/**
	 * 根据itemId和销售属性插入更新
	 * @param sku
	 */
	public void insertOrUpdate(Sku sku);
	
}