package com.ec.api.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.api.dao.SkuDao;
import com.ec.api.domain.Sku;
import com.ec.api.domain.query.SkuQuery;

public class SkuDaoImpl extends SqlMapClientTemplate implements SkuDao {

	@Override
	public Integer insert(Sku sku) {
		return (Integer)insert("Sku.insert",sku);
	}

	@Override
	public void modify(Sku sku) {
		update("Sku.updateByPrimaryKey", sku);
	}

	@Override
	public Sku selectBySkuId(int skuId) {
		return (Sku)queryForObject("Sku.selectByPrimaryKey", skuId);
	}

	@Override
	public int countByCondition(SkuQuery skuQuery) {
		return (Integer)queryForObject("Sku.countByCondition", skuQuery);
	}

	@Override
	public List<Sku> selectByCondition(SkuQuery skuQuery) {
		return (List<Sku>)queryForList("Sku.selectByCondition",skuQuery);
	}

	@Override
	public List<Sku> selectByConditionForPage(SkuQuery skuQuery) {
		return (List<Sku>)queryForList("Sku.selectByConditionForPage",skuQuery);
	}

	@Override
	public Integer delStock(Sku sku) {
		return delete("Sku.delStock", sku);
	}
}
