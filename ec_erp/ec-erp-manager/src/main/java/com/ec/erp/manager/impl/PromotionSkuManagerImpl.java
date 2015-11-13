package com.ec.erp.manager.impl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ec.erp.dao.PromotionSkuDao;
import com.ec.erp.domain.PromotionSku;
import com.ec.erp.manager.PromotionSkuManager;
import com.ec.erp.domain.query.PromotionSkuQuery;


@Repository
public class PromotionSkuManagerImpl implements PromotionSkuManager{
	
	@Autowired
	private PromotionSkuDao promotionSkuDao;
	private final static Log LOG = LogFactory.getLog(PromotionSkuManagerImpl.class);
	@Override
	public Integer insert(PromotionSku promotionSku) {
		
		return promotionSkuDao.insert(promotionSku);
	}
	
	@Override
	public List<PromotionSku> selectByCondition(
			PromotionSkuQuery promotionSkuQuery) {
		
		return promotionSkuDao.selectByCondition(promotionSkuQuery);
	}

	@Override
	public void modifyByPromotionId(PromotionSku promotionSku) {
		promotionSkuDao.modifyByPromotionId(promotionSku);
		
	}


}
