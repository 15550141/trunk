package com.ec.api.service;

import com.ec.api.domain.Favorites;
import com.ec.api.domain.query.FavoritesQuery;
import com.ec.api.service.result.Result;

import java.util.List;

public interface FavoritesService {
	/**
	 * 分页查询
	 * @return
	 */
	public Result getFavoritesByPage(FavoritesQuery favoritesQuery);
	/**
	 * 根据id查询
	 * @return
	 */
	public Result getFavoritesByFavoritesId(Integer userId, Integer favoritesId);
	
	/**
	 * 删除商品关注
	 * @param userId
	 * @param favoritesId
	 * @return
	 */
	public Result delFavoritesBtFavoritesId(Integer userId, Integer favoritesId);
	
	/**
	 * 添加商品关注
	 * @param favorites
	 * @return
	 */
	public Result addFavorites(Favorites favorites);
	
	/**
	 * 判断是否添加关注
	 * @param userId,itemId
	 * @return
	 */
	public Result checkItemFavorites(Integer userId, Integer itemId);

/**
	 * 判断是否添加关注
	 * @param userId,itemId
	 * @return
	 */
	public Result checkItemsFavorites(Integer userId, List<Integer> itemIdList);

}
