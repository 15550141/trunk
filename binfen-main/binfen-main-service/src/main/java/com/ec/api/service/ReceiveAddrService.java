package com.ec.api.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ec.api.domain.ReceiveAddr;
import com.ec.api.service.result.Result;

public interface ReceiveAddrService {
	/**
	 * 添加个人联系地址
	 * @param receiveAddr
	 * @return
	 */
	public Result addAddr(ReceiveAddr receiveAddr);
	
	/**
	 * 获取个人所有收货地址列表
	 */
	public List<ReceiveAddr> getAddrList(Integer uid);
	
	/**
	 * 删除个人联系方式地址
	 * @param uid
	 * @param receiveAddrId
	 * @return
	 */
	public Result delAddr(Integer uid, Integer receiveAddrId);
	
	/**
	 * 获取用户地址信息
	 * @param uid
	 * @param receiveAddrId
	 * @return
	 */
	public ReceiveAddr getReceiveAddr(Integer uid, Integer receiveAddrId);
	
	/**
	 * 获取用户默认收货地址
	 * @param uid
	 * @param receiveAddrId
	 * @return
	 */
	public ReceiveAddr getDefaultReceiveAddr(Integer uid);
	
	public Result updateAddr(ReceiveAddr receiveAddr);
	
	public Result choseAddr(Integer uid, Integer addrId);
}
