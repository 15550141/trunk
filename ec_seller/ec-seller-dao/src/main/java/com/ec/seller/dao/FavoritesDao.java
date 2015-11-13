package com.ec.seller.dao;

import java.util.List;

import com.ec.seller.domain.Favorites;
import com.ec.seller.domain.query.FavoritesQuery;

public interface FavoritesDao{
	
	/**
	 * 添加收藏夹信息
	 * @param Favorites
	 * @return
	 */
	public Integer insert(Favorites favorites);

	/**
	 * 依据收藏信息ID删除
	 * @param favoritesId
	 */
	public void delete(Integer favoritesId);
	
	/**
	 * 根据相应的条件查询满足条件的地址信息的总数
	 * @param FavoritesQuery
	 * @return
	 */
	public int countByCondition(FavoritesQuery favoritesQuery);
	
	/**
	 * 根据相应的条件查询地址信息
	 * @param FavoritesQuery
	 * @return
	 */
	public List<Favorites> selectByCondition(FavoritesQuery favoritesQuery);
	
	/**
	 * 根据相应的条件查询地址信息-分页查询
	 * @param FavoritesQuery
	 * @return
	 */
	public List<Favorites> selectByConditionForPage(FavoritesQuery favoritesQuery);
}