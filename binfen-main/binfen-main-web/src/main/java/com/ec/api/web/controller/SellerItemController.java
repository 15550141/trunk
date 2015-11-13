package com.ec.api.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ec.api.common.utils.JsonUtils;
import com.ec.api.common.utils.RandomUtil;
import com.ec.api.domain.Item;
import com.ec.api.domain.ItemImage;
import com.ec.api.domain.ItemSkuList;
import com.ec.api.domain.Sku;
import com.ec.api.domain.SkuList;
import com.ec.api.domain.query.ItemQuery;
import com.ec.api.service.ItemService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;
import com.ec.api.web.base.BaseController;

@Controller
@RequestMapping("/sell/item")
public class SellerItemController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(SellerItemController.class);

	private ItemService itemService;
	
	/**
	 * 依据条件查询商家所属所有商品信息
	 * 
	 * @param itemQuery
	 * @param request
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "getItemByItemQuery", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	Result getItemByItemQuery(ItemQuery itemQuery, HttpServletRequest request,
			HttpServletResponse response, ModelMap context) {
		itemQuery.setVenderUserId(getUserId(request));
		return itemService.getItemByItemQuery(itemQuery);
	}

	@RequestMapping(value = "addItem", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	Result addItem(Item item, String skus, HttpServletRequest request,
			HttpServletResponse response, ModelMap context) {
		Result result = new Result();

		ItemSkuList itemSkuList = null;
		try {
			itemSkuList = JsonUtils.readValue("{\"list\":" + skus + "}",
					ItemSkuList.class);
		} catch (Exception e) {
			result.setResultCode("1001");
			result.setResultMessage("skus格式不正确");
			return result;
		}

		if (itemSkuList == null || itemSkuList.getList() == null
				|| itemSkuList.getList().size() == 0) {
			result.setResultCode("1001");
			result.setResultMessage("skus格式不正确");
			return result;
		}

		List<Sku> list = itemSkuList.getList();
		for (int i = 0; i < list.size(); i++) {
			Sku sku = list.get(i);
			if (sku.getSalePrice() == null) {
				result.setResultCode("1001");
				result.setResultMessage("skus格式不正确，tbPrice不能为空");
				return result;
			}
			if (sku.getStock() == null) {
				result.setResultCode("1001");
				result.setResultMessage("skus格式不正确，stock不能为空");
				return result;
			}
			if (sku.getLeastBuy() == null) {
				sku.setLeastBuy(1);// 起买量默认为1
			}
			sku.setYn(1);
		}

		if (StringUtils.isBlank(item.getItemName())) {
			result.setResultCode("1001");
			result.setResultMessage("itemName不能为空");
			return result;
		}
		if (item.getItemType() == null) {
			result.setResultCode("1001");
			result.setResultMessage("itemType不能为空");
			return result;
		}
		if (item.getCategoryId1() == null) {
			result.setResultCode("1001");
			result.setResultMessage("categoryId1不能为空");
			return result;
		}
		if (item.getCategoryId2() == null) {
			result.setResultCode("1001");
			result.setResultMessage("categoryId2不能为空");
			return result;
		}
		if (item.getCategoryId3() == null) {
			result.setResultCode("1001");
			result.setResultMessage("categoryId2不能为空");
			return result;
		}
		if (item.getCategoryId4() == null) {
			result.setResultCode("1001");
			result.setResultMessage("categoryId2不能为空");
			return result;
		}
		if (item.getLeastBuy() == null) {
			item.setLeastBuy(1);// 默认起买量为1 数据库标注为冗余
		}

		item.setVenderUserId(getUserId(request));
		item.setItemStatus(1);// 上架
		item.setYn(1);
		item.setCreated(new Date());
		item.setOnShelfTime(new Date());

		return this.itemService.addItem(item, list);
	}

	@RequestMapping(value = "updateItem", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody Result updateItem(Item item, HttpServletRequest request,
			HttpServletResponse response, ModelMap context) {
		Result result = new Result();
		if (item.getItemId() == null) {
			result.setResultCode("1001");
			result.setResultMessage("itemId不能为空");
			return result;
		}
		item.setModified(new Date());
		return this.itemService.updateItem(item);
	}

	@RequestMapping(value = "imageUpload", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Result imageUpload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return itemService.imageUpload(request);
	}
	
	@RequestMapping(value = "addItemImage", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Result addItemImage(ItemImage itemImage, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Result result = new Result();
		if(StringUtils.isBlank(itemImage.getImageUrl())){
			result.setResultCode("1001");
			result.setResultMessage("imageUrl不能为空");
			return result;
		}
		if(itemImage.getItemId() == null){
			result.setResultCode("1001");
			result.setResultMessage("itemId不能为空");
			return result;
		}
		if(itemImage.getSortNumber() == null){
			result.setResultCode("1001");
			result.setResultMessage("sortNumber不能为空");
			return result;
		}
		return itemService.addItemImage(itemImage);
	}

    @RequestMapping(value = "addItemImages", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Result addItemImages(String imageUrls,Integer itemId, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Result result = new Result();
		if(StringUtils.isBlank(imageUrls)){
			result.setResultCode("1001");
			result.setResultMessage("imageUrls不能为空");
			return result;
		}
		if(itemId == null){
			result.setResultCode("1001");
			result.setResultMessage("itemId不能为空");
			return result;
		}
        List<ItemImage> itemImageList = toList(imageUrls, itemId);
		return itemService.addItemImages(itemImageList);
	}
    @RequestMapping(value = "updateSku", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Result updateSku(Sku sku, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Result result = new Result();
		if(sku.getSkuId() == null){
			result.setResultCode("1001");
			result.setResultMessage("skuId不能为空");
			return result;
		}
		return itemService.updateSku(sku);
	}

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
     private List<ItemImage> toList(String imageUrls, Integer itemId) {
        List<ItemImage> itemImageList = new ArrayList<ItemImage>();
        String[] imageUrlArr = imageUrls.split(",");
        int i = 1;
        for(String imageUrl : imageUrlArr ){
            ItemImage itemImage = new ItemImage();
            itemImage.setItemId(itemId);
            itemImage.setSortNumber(i++);
            itemImage.setImageUrl(imageUrl);
            itemImageList.add(itemImage);
        }
        return itemImageList;
    }

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

}
