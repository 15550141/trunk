package com.ec.api.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.api.dao.ReceiveAddrDao;
import com.ec.api.domain.ReceiveAddr;
import com.ec.api.domain.query.ReceiveAddrQuery;

public class ReceiveAddrDaoImpl extends SqlMapClientTemplate implements ReceiveAddrDao {


	@Override
	public Integer insert(ReceiveAddr receiveAddr) {
		return (Integer) insert("ReceiveAddr.insert", receiveAddr);
	}

	@Override
	public void modify(ReceiveAddr receiveAddr) {
		if(receiveAddr.getId() == null && receiveAddr.getUid() == null){
			throw new RuntimeException("id或者uid不能同时为空");
		}
		update("ReceiveAddr.updateByReceiveAddr", receiveAddr);
	}

	@Override
	public ReceiveAddr selectById(Integer id) {
		return (ReceiveAddr) queryForObject("ReceiveAddr.selectByPrimaryKey", id);
	}

	@Override
	public Integer countByCondition(ReceiveAddrQuery query) {
		return (Integer) queryForObject("ReceiveAddr.countByCondition", query);
	}

	@Override
	public List<ReceiveAddr> selectByCondition(ReceiveAddrQuery query) {
		return (List<ReceiveAddr>)queryForList("ReceiveAddr.selectByCondition",query);
	}

	@Override
	public Integer deleteReceiveAddr(Integer addrId) {
		return (Integer) delete("ReceiveAddr.deleteByPrimaryKey", addrId);
	}

}
