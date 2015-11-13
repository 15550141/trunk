package com.ec.seller.dao;

import java.util.List;

import com.ec.seller.domain.ItemImage;
import com.ec.seller.domain.query.ItemImageQuery;


public interface ItemImageDao{
	/**
	 * 添加商品图片信息
	 * @param itemImage
	 * @return
	 */
	public Integer insert(ItemImage itemImage);

	/**
	 * 依据商品图片ID修改商品图片信息
	 * @param itemImage
	 */
	public void modify(ItemImage itemImage);

	/**
	 * 依据商品ID查询商品图片信息
	 * @param itemId
	 * @return
	 */
	public List<ItemImage> selectByItemId(int itemId);
	
}