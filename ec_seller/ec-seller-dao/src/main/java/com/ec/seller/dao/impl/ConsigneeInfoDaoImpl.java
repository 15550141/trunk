package com.ec.seller.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.seller.dao.ConsigneeInfoDao;
import com.ec.seller.domain.ConsigneeInfo;
import com.ec.seller.domain.query.ConsigneeInfoQuery;

public class ConsigneeInfoDaoImpl extends SqlMapClientTemplate implements ConsigneeInfoDao {

	@Override
	public Integer insert(ConsigneeInfo consigneeInfo) {
		return (Integer)insert("ConsigneeInfo.insert", consigneeInfo);
	}

	@Override
	public void modify(ConsigneeInfo consigneeInfo) {
		update("ConsigneeInfo.updateByPrimaryKey", consigneeInfo);
	}

	@Override
	public ConsigneeInfo selectByConsigneeId(int consigneeId) {
		return (ConsigneeInfo)queryForObject("ConsigneeInfo.selectByPrimaryKey",consigneeId);
	}

	@Override
	public ConsigneeInfo selectByUserIdForLastUse(int userId) {
		return (ConsigneeInfo)queryForObject("ConsigneeInfo.selectByUserIdForLastUse",userId);
	}

	@Override
	public int countByCondition(ConsigneeInfoQuery consigneeInfoQuery) {
		return (Integer)queryForObject("ConsigneeInfo.countByCondition",consigneeInfoQuery);
	}

	@Override
	public List<ConsigneeInfo> selectByCondition(
			ConsigneeInfoQuery consigneeInfoQuery) {
		return (List<ConsigneeInfo>)queryForList("ConsigneeInfo.selectByCondition",consigneeInfoQuery);
	}

}
