package com.ec.seller.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.seller.dao.PromotionSkuDao;
import com.ec.seller.dao.PromotionSkuDao;
import com.ec.seller.domain.PromotionSku;
import com.ec.seller.domain.PromotionSku;
import com.ec.seller.domain.query.PromotionSkuQuery;

public class PromotionSkuDaoImpl extends SqlMapClientTemplate implements PromotionSkuDao {

	@Override
	public Integer insert(PromotionSku promotionSku) {
		return (Integer)insert("PromotionSku.insert",promotionSku);
	}

	@Override
	public void modify(PromotionSku PromotionSku) {
		update("PromotionSku.updateByPrimaryKey",PromotionSku);
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
	public int countByCondition(PromotionSkuQuery PromotionSkuQuery) {
		return (Integer)queryForObject("PromotionSku.countByCondition",PromotionSkuQuery);
	}

	@Override
	public List<PromotionSku> selectByCondition(
			PromotionSkuQuery PromotionSkuQuery) {
		return (List<PromotionSku>)queryForList("PromotionSku.selectByCondition",PromotionSkuQuery);
	}

	@Override
	public List<PromotionSku> selectByConditionForPage(
			PromotionSkuQuery PromotionSkuQuery) {
		return (List<PromotionSku>)queryForList("PromotionSku.selectByConditionForPage",PromotionSkuQuery);
	}

}
