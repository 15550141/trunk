package com.ec.seller.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.seller.dao.BalanceInfoDao;
import com.ec.seller.domain.BalanceInfo;
import com.ec.seller.domain.BalanceInfo;
import com.ec.seller.domain.query.BalanceInfoQuery;
import com.ec.seller.domain.query.BalanceInfoQuery;

public class BalanceInfoDaoImpl extends SqlMapClientTemplate implements BalanceInfoDao {

	@Override
	public Integer insert(BalanceInfo balanceInfo) {
		return (Integer)insert("BalanceInfo.insert", balanceInfo);
	}

	@Override
	public int modify(BalanceInfo balanceInfo) {
		return update("BalanceInfo.updateByPrimaryKey", balanceInfo);
	}

	@Override
	public int countByCondition(BalanceInfoQuery balanceInfoQuery) {
		return (Integer)queryForObject("BalanceInfo.countByCondition", balanceInfoQuery);
	}

	@Override
	public List<BalanceInfo> selectByCondition(BalanceInfoQuery balanceInfoQuery) {
		return (List<BalanceInfo>)queryForList("BalanceInfo.selectByCondition",balanceInfoQuery);
	}

	@Override
	public List<BalanceInfo> selectByConditionForPage(BalanceInfoQuery balanceInfoQuery) {
		return (List<BalanceInfo>)queryForList("BalanceInfo.selectByConditionForPage",balanceInfoQuery);
	}
	
}
