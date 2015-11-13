package com.ec.api.dao;

import com.ec.api.domain.ItemDescription;


public interface ItemDescriptionDao{
	
	/**
	 * 添加商品描述信息
	 * @param itemDescription
	 * @return
	 */
	public Integer insert(ItemDescription itemDescription);

	/**
	 * 依据商品ID修改商品描述信息
	 * @param itemDescription
	 */
	public void modify(ItemDescription itemDescription);

	/**
	 * 依据商品ID查询商品描述信息
	 * @param itemId
	 * @return
	 */
	public ItemDescription selectByItemId(int itemId);
	
	/**
	 * 添加或修改
	 * @param itemDescription
	 */
	public void insertOrUpdate(ItemDescription itemDescription);
}