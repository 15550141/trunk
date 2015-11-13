package com.ec.erp.service;

import java.util.List;
import java.util.Map;

import com.ec.erp.common.utils.PaginatedList;
import com.ec.erp.domain.BalanceInfo;
import com.ec.erp.domain.query.BalanceInfoQuery;

public interface BalanceInfoService {
	/**
	 * 分页查询
	 * @param orderInfoQuery
	 * @return
	 */
	public PaginatedList<BalanceInfo> getBalanceInfosByPage(BalanceInfoQuery balanceInfoQuery);
	
	/**
	 * 根据订单号以及商家id查询订单详细信息
	 * @param orderInfoQuery
	 * @return
	 */
	public BalanceInfo getBalanceInfoByBalanceId(BalanceInfoQuery balanceInfoQuery);
	
	/**
	 * 确认结算
	 * @param orderId
	 * @param venderUserId
	 * @return
	 */
	public Map<String, Object> doAgree(BalanceInfo balanceInfo);
	/**
	 * 获取所有，导出用
	 * @param balanceInfoQuery
	 * @return
	 */
	public List<BalanceInfo> selectByCondition(BalanceInfoQuery balanceInfoQuery);
}
