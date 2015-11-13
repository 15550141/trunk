package com.ec.erp.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.erp.dao.PromotionSkuDao;
import com.ec.erp.dao.PromotionSkuDao;
import com.ec.erp.domain.PromotionSku;
import com.ec.erp.domain.PromotionSku;
import com.ec.erp.domain.query.PromotionSkuQuery;

public class PromotionSkuDaoImpl extends SqlMapClientTemplate implements PromotionSkuDao {

	@Override
	public Integer insert(PromotionSku promotionSku) {
		return (Integer)insert("PromotionSku.insert",promotionSku);
	}

	@Override
	public void modify(PromotionSku promotionSku) {
		update("PromotionSku.updateByPrimaryKey",promotionSku);
	}
	
	
	@Override
	public void modifyByPromotionId(PromotionSku promotionSku) {
		update("PromotionSku.updateByPromotionId",promotionSku);
	}

	@Override
	public PromotionSku selectByPromotionId(int promotionId) {
		return (PromotionSku)queryForObject("PromotionSku.selectByPrimaryKey",promotionId);
	}
	
	@Override
	public PromotionSku selectBySkuId(int skuId) {
		return (PromotionSku)queryForObject("PromotionSku.selectBySkuId", skuId);
	}

	@Override
	public int countByCondition(PromotionSkuQuery promotionSkuQuery) {
		return (Integer)queryForObject("PromotionSku.countByCondition",promotionSkuQuery);
	}

	@Override
	public List<PromotionSku> selectByCondition(
			PromotionSkuQuery promotionSkuQuery) {
		return (List<PromotionSku>)queryForList("PromotionSku.selectByCondition",promotionSkuQuery);
	}

	@Override
	public List<PromotionSku> selectByConditionForPage(
			PromotionSkuQuery promotionSkuQuery) {
		return (List<PromotionSku>)queryForList("PromotionSku.selectByConditionForPage",promotionSkuQuery);
	}

}
