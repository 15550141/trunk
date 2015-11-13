package com.ec.api.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.api.dao.UmpInfoDao;
import com.ec.api.domain.UmpInfo;
import com.ec.api.domain.query.UmpInfoQuery;

public class UmpInfoDaoImpl extends SqlMapClientTemplate implements UmpInfoDao {

	@Override
	public Integer insert(UmpInfo umpInfo) {
		return (Integer)insert("UmpInfo.insert", umpInfo);
	}

	@Override
	public void modify(UmpInfo umpInfo) {
		update("UmpInfo.updateByPrimaryKey", umpInfo);
	}

	@Override
	public UmpInfo selectByPrimaryKey(Integer id) {
		return (UmpInfo)queryForObject("UmpInfo.selectByPrimaryKey", id);
	}

	@Override
	public int countByCondition(UmpInfoQuery umpInfoQuery) {
		return (Integer)queryForObject("UmpInfo.countByCondition",umpInfoQuery);
	}

	@Override
	public List<UmpInfo> selectByCondition(UmpInfoQuery umpInfoQuery) {
		return (List<UmpInfo>)queryForList("UmpInfo.selectByCondition",umpInfoQuery);
	}

	@Override
	public List<UmpInfo> selectByConditionForPage(
			UmpInfoQuery umpInfoQuery) {
		return (List<UmpInfo>)queryForList("UmpInfo.selectByConditionForPage",umpInfoQuery);
	}
	
}
