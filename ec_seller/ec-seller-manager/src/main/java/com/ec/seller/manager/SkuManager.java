package com.ec.seller.manager;

import java.util.List;
import com.ec.seller.domain.Sku;
import com.ec.seller.domain.query.SkuQuery;

public interface SkuManager {
	public List<Sku> selectByCondition(SkuQuery skuQuery);
	
	public Integer insert(Sku sku);
	
	/**
	 * 依据Item_Id修改SKU信息(无销售属性时)
	 * @param sku
	 */
	public void modifyByItemId(Sku sku);
	
	public void insertOrUpdate(Sku sku);

}
