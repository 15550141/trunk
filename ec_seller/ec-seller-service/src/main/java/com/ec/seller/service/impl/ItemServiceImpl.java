package com.ec.seller.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.seller.common.utils.PaginatedList;
import com.ec.seller.common.utils.impl.PaginatedArrayList;
import com.ec.seller.domain.Category;
import com.ec.seller.domain.Item;
import com.ec.seller.domain.Sku;
import com.ec.seller.domain.query.ItemQuery;
import com.ec.seller.domain.query.SkuQuery;
import com.ec.seller.manager.CategoryManager;
import com.ec.seller.manager.ItemManager;
import com.ec.seller.manager.SkuManager;
import com.ec.seller.service.ItemService;

@Service(value = "itemService")
public class ItemServiceImpl implements ItemService{
	@Autowired
	private ItemManager itemManager;
	@Autowired
	private SkuManager skuManager;
	@Autowired
	private CategoryManager categoryManager;

	@Override
	public List<Item> selectByConditionForPage(ItemQuery itemQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> queryItemList(ItemQuery itemQuery) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		PaginatedList<ItemQuery> itemList =null;
		//创建一个分页的促销对象
		itemList=new PaginatedArrayList<ItemQuery>(itemQuery.getPageNo(),itemQuery.getPageSize());
		int count = itemManager.countByCondition(itemQuery);
		itemList.setTotalItem(count);
		int startRow=itemList.getStartRow();
		if (startRow == 0) {
			startRow = 1;
		}
		itemQuery.setStart(startRow-1);
		List<ItemQuery> list= itemManager.selectByConditionWithPage(itemQuery);
		int i = 0;
		for(ItemQuery item:list){
			Integer itemId=item.getItemId();
			
			// TODO 获取商品对应的最低天宝价和库存量，查SKU表
			SkuQuery skuQuery=new SkuQuery();
			skuQuery.setItemId(itemId);
			List<Sku> skuList=skuManager.selectByCondition(skuQuery);
			if(skuList.size() > 0)
			{
				Double minTbPrice = skuList.get(0).getTbPrice();
				int stock=0;//库存为所有SKU的总和
				for(Sku sku :skuList){
					if(sku.getTbPrice()<=minTbPrice){
						minTbPrice=sku.getTbPrice();
					}
					stock = stock + sku.getStock();
				}
				list.get(i).setTbPrice(BigDecimal.valueOf(minTbPrice));
				list.get(i).setStock(stock);
			}
			
			// TODO 获取该商品对应的销量，查订单详情表
			
			//获取商品一级分类名称
			Integer categoryId1 = item.getCategoryId1();
			Integer categoryId2 = item.getCategoryId2();
			Integer categoryId3 = item.getCategoryId3();
			Integer categoryId4 = item.getCategoryId4();
			Category category1=categoryManager.selectByCategoryId(categoryId1);
			Category category2=categoryManager.selectByCategoryId(categoryId2);
			Category category3=categoryManager.selectByCategoryId(categoryId3);
			Category category4=categoryManager.selectByCategoryId(categoryId4);
			if(category1!=null){
				list.get(i).setCategoryId1Name(category1.getCategoryName());
			}
			if(category2!=null){
				list.get(i).setCategoryId2Name(category2.getCategoryName());
			}
			if(category3!=null){
				list.get(i).setCategoryId3Name(category3.getCategoryName());
			}
			if(category4!=null){
				list.get(i).setCategoryId4Name(category4.getCategoryName());
			}
			i = i+1;
		}
		itemList.addAll(list);
		resultMap.put("itemList", itemList);
		resultMap.put("item", itemQuery);	
		return resultMap;
	}

	@Override
	public Integer insert(Item item) {

		return itemManager.insert(item);
	}

	@Override
	public void modify(Item item) {
		itemManager.modify(item);
		
	}

	@Override
	public Item selectByItemId(int itemId) {

		return itemManager.selectByItemId(itemId);
	}




}
