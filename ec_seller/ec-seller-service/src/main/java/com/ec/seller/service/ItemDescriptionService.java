package com.ec.seller.service;

import com.ec.seller.domain.ItemDescription;

public interface ItemDescriptionService {
	/**
	 * 添加商品描述信息
	 * @param itemDescription
	 * @return
	 */
	public Integer insert(ItemDescription itemDescription);
	/**
	 * 依据商品ID查询商品描述信息
	 * @param itemId
	 * @return
	 */
	public ItemDescription selectByItemId(int itemId);
	/**
	 * 依据商品ID修改商品描述信息
	 * @param itemDescription
	 */
	public void modify(ItemDescription itemDescription);



}
