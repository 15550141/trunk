package com.ec.api.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ec.api.domain.Item;
import com.ec.api.domain.ItemImage;
import com.ec.api.domain.Sku;
import com.ec.api.domain.query.ItemQuery;
import com.ec.api.service.result.Result;

public interface ItemService {
	
	public Result getItemByItemId(Integer itemId);
	
	/**
	 * 根据条件查询商品相信信息列表
	 * @param itemQuery
	 * @return
	 */
	public Result getItemByItemQuery(ItemQuery itemQuery);
	
	/**
	 * 依据条件查询商家所属所有商品信息
	 * @return
	 */
	public Result getItemsByVenderUserId(Integer venderUserId);
	
	/**
	 * 依据商品ID查询商品下SKU信息
	 * @param itemId
	 * @return
	 */
	public Item getItemAndSkusByItemId(Integer itemId);
	
	/**
	 * 添加商品
	 * @param item
	 * @return
	 */
	public Result addItem(Item item, List<Sku> skus);
	
	/**
	 * 修改商品
	 * @param item
	 * @return
	 */
	public Result updateItem(Item item);
	
	/**
	 * 查看手机端商品介绍
	 * @param itemId
	 * @return
	 */
	public Result getappDescriptionInfo(Integer itemId);
	
	public Result getItemImages(Integer itemId);

	/**
	 * 上传商品图片
	 * @param request
	 * @return
	 */
	public Result imageUpload(HttpServletRequest request);
	
	/**
	 * 添加一个商品图片
	 * @param itemImage
	 * @return
	 */
	public Result addItemImage(ItemImage itemImage);
	
	/**
	 * 修改sku信息
	 * 价格、库存、最低起买量等
	 * @param sku
	 * @return
	 */
	public Result updateSku(Sku sku);

    /**
	 * 添加多个商品图片
	 * @param itemImageList
	 * @return
	 */
	public Result addItemImages(List<ItemImage> itemImageList);
}
