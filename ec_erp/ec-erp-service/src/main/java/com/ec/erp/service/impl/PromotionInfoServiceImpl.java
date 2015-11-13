package com.ec.erp.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.ec.erp.common.utils.PaginatedList;
import com.ec.erp.domain.Item;
import com.ec.erp.domain.PromotionInfo;
import com.ec.erp.domain.Sku;
import com.ec.erp.domain.query.SkuQuery;
import com.ec.erp.manager.PromotionInfoManager;
import com.ec.erp.service.ItemService;
import com.ec.erp.service.PromotionInfoService;
import com.ec.erp.service.SkuService;

@Service(value = "promotionInfoService")
public class PromotionInfoServiceImpl implements PromotionInfoService{
	@Autowired
	private PromotionInfoManager promotionInfoManager;
	@Autowired
	ItemService itemService;
	@Autowired
	SkuService skuService;

	@Override
	public Map<String, Object> queryPromotionList(PromotionInfo promotionInfo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		PaginatedList<PromotionInfo>  promotionInfoList= promotionInfoManager.queryPromotionList(promotionInfo); 
		resultMap.put("promotionInfoList", promotionInfoList);
		resultMap.put("promotionInfo", promotionInfo);	
		return resultMap;
	}

	@Override
	public Map<String, Object> queryItem(Integer itemId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//查询是否有该商品
		Item item = itemService.selectByItemId(itemId);
		if(item == null){
			resultMap.put("msg", "error");//无商品
			return resultMap;
		}
		SkuQuery skuQuery=new SkuQuery();
		skuQuery.setItemId(itemId);
		skuQuery.setYn(1);
		List<Sku> skuList =skuService.selectByCondition(skuQuery);
		resultMap.put("msg", "success");//有商品
		resultMap.put("item", item);
		resultMap.put("skuList", skuList);
		return resultMap;
	}

	@Override
	public Integer insert(PromotionInfo promotionInfo) {
		
		return promotionInfoManager.insert(promotionInfo);
	}

	@Override
	public void modify(PromotionInfo promotionInfo) {
		promotionInfoManager.modify(promotionInfo);
		
	}
	@Override
	public PromotionInfo selectByPromotionId(int promotionId) {
		
		return promotionInfoManager.selectByPromotionId(promotionId);
	}




}
