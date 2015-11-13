package com.ec.seller.dao;

import java.util.List;

import com.ec.seller.domain.Item;
import com.ec.seller.domain.query.ItemQuery;

public interface ItemDao{
	
	/**
	 * 添加商品信息
	 * @param item
	 * @return
	 */
	public Integer insert(Item item);

	/**
	 * 依据商品ID修改商品信息
	 * @param item
	 */
	public void modify(Item item);

	/**
	 * 依据商品ID查询商品信息
	 * @param itemId
	 * @return
	 */
	public Item selectByItemId(int itemId);
	
	/**
	 * 根据相应的条件查询满足条件的商品信息的总数
	 * @param itemQuery
	 * @return
	 */
	public int countByCondition(ItemQuery itemQuery);
	
	/**
	 * 根据相应的条件查询商品信息
	 * @param itemQuery
	 * @return
	 */
	public List<Item> selectByCondition(ItemQuery itemQuery);
	
	/**
	 * 根据相应的条件查询商品信息---分页查询
	 * @param itemQuery
	 * @return
	 */
	public List<Item> selectByConditionForPage(ItemQuery itemQuery);
	/**
	 * 根据相应的条件查询商品信息---分页查询
	 * @param itemQuery
	 * @return
	 */
	public List<ItemQuery> selectByConditionWithPage(ItemQuery itemQuery);
	
	/**
	 * 根据相应的条件查询满足条件的商品信息的总数 ----分页查询
	 * @param itemQuery
	 * @return
	 */
	public int countByConditionForPage(ItemQuery itemQuery);
	/**
	 * 查询要上架的商品列表
	 * @param itemQuery
	 * @return
	 */
	public List<ItemQuery> queryOnSheftList(Integer offsetOnSheft);
	
	/**
	 * 查询要下架的商品列表
	 * @param itemQuery
	 * @return
	 */
	public List<ItemQuery> queryOffSheftList(Integer offsetOffSheft);
	

	//商品上架
	public void onSheft(ItemQuery item);
	
	//商品下架
	public void offSheft(ItemQuery item);
	
}