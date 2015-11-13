package com.ec.seller.manager.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ec.seller.dao.SkuDao;
import com.ec.seller.domain.Sku;
import com.ec.seller.domain.query.SkuQuery;
import com.ec.seller.manager.SkuManager;


@Repository
public class SkuManagerImpl implements SkuManager{
	
	@Autowired
	private SkuDao skuDao;

	@Override
	public List<Sku> selectByCondition(SkuQuery skuQuery) {
		return skuDao.selectByCondition(skuQuery);
	}

	@Override
	public Integer insert(Sku sku) {

		return skuDao.insert(sku);
	}

	@Override
	public void modifyByItemId(Sku sku) {
		skuDao.modifyByItemId(sku);
		
	}

	@Override
	public void insertOrUpdate(Sku sku) {
		skuDao.insertOrUpdate(sku);
		
	}


	

}
