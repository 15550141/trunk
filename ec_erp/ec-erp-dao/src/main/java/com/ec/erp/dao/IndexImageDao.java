package com.ec.erp.dao;

import java.util.List;

import com.ec.erp.domain.IndexImage;
import com.ec.erp.domain.query.IndexImageQuery;

public interface IndexImageDao{
	
	/**
	 * 添加商品信息
	 * @param item
	 * @return
	 */
	public Integer insert(IndexImage indexImage);

	/**
	 * 依据商品ID修改商品信息
	 * @param item
	 */
	public void modify(IndexImage indexImage);

	
	/**
	 * 根据相应的条件查询满足条件的商品信息的总数
	 * @param itemQuery
	 * @return
	 */
	public int countByCondition(IndexImageQuery indexImageQuery);
	
	/**
	 * 根据相应的条件查询商品信息
	 * @param itemQuery
	 * @return
	 */
	public List<IndexImage> selectByCondition(IndexImageQuery indexImageQuery);
	
	/**
	 * 根据相应的条件查询商品信息---分页查询
	 * @param itemQuery
	 * @return
	 */
	public List<IndexImage> selectByConditionForPage(IndexImageQuery indexImageQuery);
}