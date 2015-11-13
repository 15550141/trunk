package com.ec.seller.dao;

import java.util.List;

import com.ec.seller.domain.ItemProperty;
import com.ec.seller.domain.query.ItemPropertyQuery;


public interface ItemPropertyDao{
	/**
	 * 添加商品属性信息
	 * @param itemProperty
	 * @return
	 */
	public Integer insert(ItemProperty itemProperty);

	/**
	 * 依据商品ID修改商品属性信息
	 * @param itemProperty
	 */
	public void modify(ItemProperty itemProperty);

	/**
	 * 依据商品ID查询商品属性信息
	 * @param itemId
	 * @return
	 */
	public ItemProperty selectByItemId(int itemId);
}