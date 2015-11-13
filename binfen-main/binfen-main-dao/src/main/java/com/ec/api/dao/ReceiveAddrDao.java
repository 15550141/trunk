package com.ec.api.dao;

import java.util.List;

import com.ec.api.domain.ReceiveAddr;
import com.ec.api.domain.query.ReceiveAddrQuery;

public interface ReceiveAddrDao{
	
	/**
	 * 添加地址信息
	 * @param receiveAddr
	 * @return
	 */
	public Integer insert(ReceiveAddr receiveAddr);

	/**
	 * 依据地址ID修改地址信息
	 * @param receiveAddr
	 */
	public void modify(ReceiveAddr receiveAddr);

	/**
	 * 依据地址ID查询地址信息
	 * @param id
	 * @return
	 */
	public ReceiveAddr selectById(Integer id);
	
	/**
	 * 根据相应的条件查询满足条件的地址信息的总数
	 * @param receiveAddrQuery
	 * @return
	 */
	public Integer countByCondition(ReceiveAddrQuery query);
	
	/**
	 * 根据相应的条件查询地址信息
	 * @param receiveAddrQuery
	 * @return
	 */
	public List<ReceiveAddr> selectByCondition(ReceiveAddrQuery query);
	
	/**
	 * 删除个人地址
	 * @param receiveAddr
	 * @return
	 */
	public Integer deleteReceiveAddr(Integer addrId);
}