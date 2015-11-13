package com.ec.seller.dao;

import java.util.List;

import com.ec.seller.domain.ConsigneeInfo;
import com.ec.seller.domain.query.ConsigneeInfoQuery;


public interface ConsigneeInfoDao {
	/**
	 * 添加收货人信息
	 * @param consigneeInfo
	 * @return
	 */
	public Integer insert(ConsigneeInfo consigneeInfo);

	/**
	 * 依据收货人信息ID修改收货人信息
	 * @param consigneeInfo
	 */
	public void modify(ConsigneeInfo consigneeInfo);

	/**
	 * 依据收货人信息ID查询收货人信息
	 * @param consigneeId
	 * @return
	 */
	public ConsigneeInfo selectByConsigneeId(int consigneeId);
	
	/**
	 * 依据用户ID查询上次使用的收货人信息
	 * @param userId
	 * @return
	 */
	public ConsigneeInfo selectByUserIdForLastUse(int userId);
	
	/**
	 * 根据相应的条件查询满足条件的收货人信息的总数
	 * @param consigneeInfoQuery
	 * @return
	 */
	public int countByCondition(ConsigneeInfoQuery consigneeInfoQuery);
	
	/**
	 * 根据相应的条件查询收货人信息
	 * @param consigneeInfoQuery
	 * @return
	 */
	public List<ConsigneeInfo> selectByCondition(ConsigneeInfoQuery consigneeInfoQuery);
	
}