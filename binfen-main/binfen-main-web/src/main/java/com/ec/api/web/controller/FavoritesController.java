package com.ec.api.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ec.api.domain.Favorites;
import com.ec.api.domain.query.FavoritesQuery;
import com.ec.api.service.FavoritesService;
import com.ec.api.service.result.Result;
import com.ec.api.web.base.BaseController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/favorites")
public class FavoritesController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(FavoritesController.class);
	    
	private FavoritesService favoritesService;
	
	@RequestMapping(value="getFavoritesByPage", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result getFavoritesByPage(FavoritesQuery favoritesQuery, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		return favoritesService.getFavoritesByPage(favoritesQuery);
	}
	
	@RequestMapping(value="getFavoritesByFavoritesId", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result getFavoritesByFavoritesId(Integer favoritesId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(favoritesId == null){
			Result result = new Result();
			result.setResultCode("1001");
			result.setResultMessage("favoritesId不能为空");
			return result;
		}
		return favoritesService.getFavoritesByFavoritesId(getUserId(request), favoritesId);
	}
	
	@RequestMapping(value="delFavoritesBtFavoritesId", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result delFavoritesBtFavoritesId(Integer favoritesId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(favoritesId == null){
			Result result = new Result();
			result.setResultCode("1001");
			result.setResultMessage("favoritesId不能为空");
			return result;
		}
		return favoritesService.delFavoritesBtFavoritesId(getUserId(request), favoritesId);
	}
	
	@RequestMapping(value="addFavorites", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result addFavorites(Favorites favorites, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(favorites.getItemId() == null){
			result.setResultCode("1001");
			result.setResultMessage("itemId不能为空");
			return result;
		}
		favorites.setUserId(getUserId(request));
		return favoritesService.addFavorites(favorites);
	}
	
	@RequestMapping(value="checkItemFavorites", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result checkItemFavorites(Integer itemId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(itemId == null){
			result.setResultCode("1001");
			result.setResultMessage("itemId不能为空");
			return result;
		}
		return favoritesService.checkItemFavorites(getUserId(request), itemId);
		
	}

    @RequestMapping(value="checkItemsFavorites", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result checkItemsFavorites(String itemId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(itemId == null){
			result.setResultCode("1001");
			result.setResultMessage("itemId不能为空");
			return result;
		}
        List<Integer> itemIdList = toList(itemId);
		return favoritesService.checkItemsFavorites(getUserId(request), itemIdList);

	}




    


    //////////////////////////////////////////////////////////////////////
    private List<Integer> toList(String itemIdStr) {
        List<Integer> itemIdList = new ArrayList<Integer>();
        String[] itemIds = itemIdStr.split(",");
        for( String is : itemIds  ){
            try{
                itemIdList.add(Integer.parseInt(is));
            }catch(NumberFormatException e){
                log.error(e.getMessage(),e);
            }
        }
        return itemIdList;
    }

    public void setFavoritesService(FavoritesService favoritesService) {
		this.favoritesService = favoritesService;
	}
	
	
}
