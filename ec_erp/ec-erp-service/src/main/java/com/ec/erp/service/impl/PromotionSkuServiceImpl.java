package com.ec.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ec.erp.domain.PromotionSku;
import com.ec.erp.domain.query.PromotionSkuQuery;
import com.ec.erp.manager.PromotionSkuManager;
import com.ec.erp.service.PromotionSkuService;


@Service(value = "promotionSkuService")
public class PromotionSkuServiceImpl implements PromotionSkuService{
	@Autowired
	private PromotionSkuManager promotionSkuManager;

	@Override
	public Integer insert(PromotionSku promotionSku) {
		
		return promotionSkuManager.insert(promotionSku);
	}

	@Override
	public List<PromotionSku> selectByCondition(
			PromotionSkuQuery promotionSkuQuery) {
		
		return promotionSkuManager.selectByCondition(promotionSkuQuery);
	}

	@Override
	public void modifyByPromotionId(PromotionSku promotionSku) {
		promotionSkuManager.modifyByPromotionId(promotionSku);
		
	}





}
