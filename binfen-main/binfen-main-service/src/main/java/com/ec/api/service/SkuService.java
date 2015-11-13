package com.ec.api.service;

import com.ec.api.service.result.Result;

public interface SkuService {
	public Result getSkuBySkuId(Integer skuId);
	
	/**
	 * 获得本地内存中的商品价格
	 * @param skuId
	 * @return
	 */
	public Integer getLocalSkuSalePrice(Integer skuId);
}
