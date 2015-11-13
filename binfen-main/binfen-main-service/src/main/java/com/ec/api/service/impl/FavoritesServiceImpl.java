package com.ec.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ec.api.dao.SkuDao;
import com.ec.api.domain.Item;
import com.ec.api.domain.query.SkuQuery;
import com.ec.api.service.vo.UserFavoritesResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ec.api.dao.FavoritesDao;
import com.ec.api.dao.ItemDao;
import com.ec.api.domain.Favorites;
import com.ec.api.domain.query.FavoritesQuery;
import com.ec.api.service.FavoritesService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;

@Service(value="favoritesService")
public class FavoritesServiceImpl implements FavoritesService {
	private static final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);
	
	private FavoritesDao favoritesDao;
	
	private ItemDao itemDao;

    private SkuDao skuDao;

	@Override
	public Result getFavoritesByPage(FavoritesQuery favoritesQuery) {
		Result result = new Result();
		try{
			int total = favoritesDao.countByCondition(favoritesQuery);
			if(total == 0){
				result.setResultCode("6002");
				result.setResultMessage("关注列表不存在");
				return result;
			}
			
			int totalPage = total/favoritesQuery.getPageSize() + 1;
			if(favoritesQuery.getPageNo() > totalPage){
				favoritesQuery.setPageNo(totalPage);
			}
            List<Favorites> favoritesList = favoritesDao.selectByConditionForPage(favoritesQuery);
            if( null != favoritesList && 0 < favoritesList.size()  ){
               for(Favorites favorites : favoritesList ){
                   if( null != favorites.getItemId() && 0 < favorites.getItemId() ){
                       Item item = itemDao.selectByItemId(favorites.getItemId());
                       SkuQuery skuQuery = new SkuQuery();
				       skuQuery.setItemId(item.getItemId());
				       skuQuery.setYn(1);
				       item.setSkuList(skuDao.selectByCondition(skuQuery));
                       favorites.setItem(item);
                   }         
               }
            }
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", total);
			map.put("totalPage", totalPage);
			map.put("curPage", favoritesQuery.getPageNo());
			map.put("list", favoritesList);
			result.setResult(map);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result getFavoritesByFavoritesId(Integer userId, Integer favoritesId) {
		Result result = new Result();
		try{
			FavoritesQuery favoritesQuery = new FavoritesQuery();
			favoritesQuery.setUserId(userId);
			favoritesQuery.setFavoritesId(favoritesId);
			List<Favorites> list = favoritesDao.selectByCondition(favoritesQuery);
			if(list == null || list.size() == 0){
				result.setResultCode("6003");
				result.setResultMessage("该用户关注不存在");
				return result;
			}
			
			for(int i=0;i<list.size();i++){
				Favorites f = list.get(i);
				f.setItem(itemDao.selectByItemId(f.getItemId()));
			}
			result.setResult(list.get(0));
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result delFavoritesBtFavoritesId(Integer userId, Integer favoritesId) {
		Result result = new Result();
		try{
			FavoritesQuery favoritesQuery = new FavoritesQuery();
			favoritesQuery.setUserId(userId);
			favoritesQuery.setFavoritesId(favoritesId);
			List<Favorites> list = favoritesDao.selectByCondition(favoritesQuery);
			if(list == null || list.size() == 0){
				result.setResultCode("6003");
				result.setResultMessage("该用户关注不存在");
				return result;
			}
			favoritesDao.delete(favoritesId);
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result addFavorites(Favorites favorites) {
		Result result = new Result();
		try{
			FavoritesQuery favoritesQuery = new FavoritesQuery();
			favoritesQuery.setItemId(favorites.getItemId());
			List<Favorites> list = favoritesDao.selectByCondition(favoritesQuery);
			if(list != null && list.size() > 0){
				result.setResultCode("6004");
				result.setResultMessage("已经关注该商品");
				return result;
			}
			favoritesDao.insert(favorites);
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	@Override
	public Result checkItemFavorites(Integer userId, Integer itemId) {
		Result result = new Result();
		try{
			FavoritesQuery query = new FavoritesQuery();
			query.setUserId(userId);
			query.setItemId(itemId);
			List<Favorites> list = favoritesDao.selectByCondition(query);
			result.setResult((list != null && list.size() > 0 ) ? true : false);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

    @Override
    public Result checkItemsFavorites(Integer userId, List<Integer> itemIdList) {
        Result result = new Result();
		try{
			FavoritesQuery query = new FavoritesQuery();
			query.setUserId(userId);
            List<UserFavoritesResultVo> userFavoritesResultVoList = new ArrayList<UserFavoritesResultVo>();
            for( Integer itemId : itemIdList ){
                query.setItemId(itemId);
			    List<Favorites> list = favoritesDao.selectByCondition(query);
                UserFavoritesResultVo userFavoritesResultVo = new UserFavoritesResultVo(userId,itemId,false);
                if( list != null && list.size() > 0 ){
                    userFavoritesResultVo.setFavorite(true);
                }
                userFavoritesResultVoList.add(userFavoritesResultVo);
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("list",userFavoritesResultVoList);
			result.setResult(map);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
        return result;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setFavoritesDao(FavoritesDao favoritesDao) {
		this.favoritesDao = favoritesDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

    public void setSkuDao(SkuDao skuDao) {
        this.skuDao = skuDao;
    }

	
}
