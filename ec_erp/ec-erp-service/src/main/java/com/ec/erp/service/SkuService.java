package com.ec.erp.service;

import java.util.List;

import com.ec.erp.domain.Sku;

import com.ec.erp.domain.query.SkuQuery;

public interface SkuService {
	public List<Sku> selectByCondition(SkuQuery skuQuery);
	
	public Integer insert(Sku sku);
	
	/**
	 * 依据Item_Id修改SKU信息(无销售属性时)
	 * @param sku
	 */
	public void modifyByItemId(Sku sku);
	
	public void insertOrUpdate(Sku sku);

}
