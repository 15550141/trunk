package com.ec.api.dao.impl;


import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.api.dao.SellerBookkeepingDao;
import com.ec.api.domain.SellerBookkeeping;
import com.ec.api.domain.query.SellerBookkeepingQuery;

public class SellerBookkeepingDaoImpl extends SqlMapClientTemplate implements SellerBookkeepingDao {

	@Override
	public Integer insert(SellerBookkeeping sellerBookkeeping) {
		return (Integer)insert("SellerBookkeeping.insert", sellerBookkeeping);
	}

	@Override
	public SellerBookkeeping selectByConditionForCountMoney(
			SellerBookkeepingQuery sellerBookkeepingQuery) {
		return (SellerBookkeeping)queryForObject("SellerBookkeeping.selectByConditionForCountMoney",sellerBookkeepingQuery);
	}

	@Override
	public int countByCondition(SellerBookkeepingQuery sellerBookkeepingQuery) {
		return (Integer)queryForObject("SellerBookkeeping.countByCondition",sellerBookkeepingQuery);
	}

	@Override
	public List<SellerBookkeeping> selectByConditionForPage(
			SellerBookkeepingQuery sellerBookkeepingQuery) {
		return (List<SellerBookkeeping>)queryForList("SellerBookkeeping.selectByConditionForPage",sellerBookkeepingQuery);
	}

	@Override
	public Integer modify(SellerBookkeeping sellerBookkeeping) {
		return update("SellerBookkeeping.updateByPrimaryKey",sellerBookkeeping);
	}

}
