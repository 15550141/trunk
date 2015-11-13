package com.ec.seller.dao;

import java.util.List;

import com.ec.seller.domain.BalanceInfo;
import com.ec.seller.domain.query.BalanceInfoQuery;

public interface BalanceInfoDao{
	
	public Integer insert(BalanceInfo balanceInfo);

	public int modify(BalanceInfo balanceInfo);

	public int countByCondition(BalanceInfoQuery balanceInfoQuery);
	
	public List<BalanceInfo> selectByCondition(BalanceInfoQuery balanceInfoQuery);
	
	public List<BalanceInfo> selectByConditionForPage(BalanceInfoQuery balanceInfoQuery);
	
}