package com.ec.seller.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.seller.domain.Sku;
import com.ec.seller.domain.query.SkuQuery;
import com.ec.seller.manager.SkuManager;
import com.ec.seller.service.SkuService;


@Service(value = "skuService")
public class SkuServiceImpl implements SkuService{
	@Autowired
	private SkuManager skuManager;

	@Override
	public List<Sku> selectByCondition(SkuQuery skuQuery) {

		return skuManager.selectByCondition(skuQuery);
	}

	@Override
	public Integer insert(Sku sku) {

		return skuManager.insert(sku);
	}

	@Override
	public void modifyByItemId(Sku sku) {
		skuManager.modifyByItemId(sku);
		
	}

	@Override
	public void insertOrUpdate(Sku sku) {
		skuManager.insertOrUpdate(sku);
		
	}
	
	



}
