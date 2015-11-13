package com.ec.api.service;

import com.ec.api.domain.ItemDescription;
import com.ec.api.service.result.Result;

public interface ItemDescriptionService {
	
	/**
	 * 商品介绍查看
	 * @return
	 */
	public Result getItemDescriptionByItemId(Integer itemId);
	
	/**
	 * 商品介绍添加
	 * @param itemDescription
	 * @return
	 */
	public Result addItemDescription(ItemDescription itemDescription);
	
	/**
	 * 商品介绍修改
	 * @param itemDescription
	 * @return
	 */
	public Result updateItemDescription(ItemDescription itemDescription);
	
	/**
	 * 添加或者修改
	 * @param itemDescription
	 * @return
	 */
	public Result insertOrUpdate(ItemDescription itemDescription);
}
