package com.ec.seller.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.seller.dao.PromotionProductDao;
import com.ec.seller.domain.PromotionProduct;
import com.ec.seller.domain.query.PromotionProductQuery;

public class PromotionProductDaoImpl extends SqlMapClientTemplate implements PromotionProductDao {

	@Override
	public Integer insert(PromotionProduct promotionProduct) {
		return (Integer)insert("PromotionProduct.insert",promotionProduct);
	}

	@Override
	public void modify(PromotionProduct promotionProduct) {
		update("PromotionProduct.updateByPrimaryKey",promotionProduct);
	}

	@Override
	public PromotionProduct selectByPromotionId(int promotionId) {
		return (PromotionProduct)queryForObject("PromotionProduct.selectByPrimaryKey",promotionId);
	}

	@Override
	public int countByCondition(PromotionProductQuery promotionProductQuery) {
		return (Integer)queryForObject("PromotionProduct.countByCondition",promotionProductQuery);
	}

	@Override
	public List<PromotionProduct> selectByCondition(
			PromotionProductQuery promotionProductQuery) {
		return (List<PromotionProduct>)queryForList("PromotionProduct.selectByCondition",promotionProductQuery);
	}

	@Override
	public List<PromotionProduct> selectByConditionForPage(
			PromotionProductQuery promotionProductQuery) {
		return (List<PromotionProduct>)queryForList("PromotionProduct.selectByConditionForPage",promotionProductQuery);
	}
	@Override
	public int queryPromotionCount(PromotionProduct promotionProduct) {
		return ( Integer )super.queryForObject("PromotionProduct.queryPromotionCount", promotionProduct);
	}

	@Override
	public List<PromotionProduct> queryPromotionList(PromotionProduct promotionProduct) {
		
		return super.queryForList("PromotionProduct.queryPromotionList", promotionProduct);
	}

	@Override
	public List<PromotionProduct> querystartPromotionList(Integer offsetStartProm) {
		
		return super.queryForList("PromotionProduct.querystartPromotionList", offsetStartProm);
	}

	@Override
	public List<PromotionProduct> querystopPromotionList(Integer offsetStopProm) {
		
		return super.queryForList("PromotionProduct.querystopPromotionList", offsetStopProm);
	}

	@Override
	public void startPromotion(PromotionProduct promotionProduct) {
		update("PromotionProduct.startPromotion",promotionProduct);
		
	}

	@Override
	public void stopPromotion(PromotionProduct promotionProduct) {
		update("PromotionProduct.stopPromotion",promotionProduct);
		
	}

	@Override
	public void modifyByPromtionId(PromotionProduct promotionProduct) {
		update("PromotionProduct.modifyByPromtionId",promotionProduct);
		
	}
}
