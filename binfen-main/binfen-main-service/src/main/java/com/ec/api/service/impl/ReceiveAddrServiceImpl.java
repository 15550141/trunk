package com.ec.api.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ec.api.common.utils.CookieUtils;
import com.ec.api.dao.AddressDao;
import com.ec.api.dao.ReceiveAddrDao;
import com.ec.api.domain.Address;
import com.ec.api.domain.ReceiveAddr;
import com.ec.api.domain.query.AddressQuery;
import com.ec.api.domain.query.ReceiveAddrQuery;
import com.ec.api.service.AddressService;
import com.ec.api.service.ReceiveAddrService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;

@Service(value="receiveAddrService")
public class ReceiveAddrServiceImpl implements ReceiveAddrService {
	private static final Logger log = LoggerFactory.getLogger(ReceiveAddrServiceImpl.class);
	
	private ReceiveAddrDao receiveAddrDao;
	
	private AddressDao addressDao;

	@Override
	public Result addAddr(ReceiveAddr receiveAddr) {
		Result result = new Result();
		try{
			if(receiveAddr.getDefaultFlag() == 1){
				//修改该用户所有的收货地址defaultFlag = 0
				ReceiveAddr updateReceiveAddr = new ReceiveAddr();
				updateReceiveAddr.setDefaultFlag(0);
				updateReceiveAddr.setUid(receiveAddr.getUid());
				
				receiveAddrDao.modify(updateReceiveAddr);
			}
			
			Address provinceAddr = addressDao.selectByAddressId(receiveAddr.getProvince());
			Address cityAddr = addressDao.selectByAddressId(receiveAddr.getCity());
			Address countyAddr = addressDao.selectByAddressId(receiveAddr.getCounty());
			
			receiveAddr.setProvinceName(provinceAddr.getAddressName());
			receiveAddr.setCityName(cityAddr.getAddressName());
			receiveAddr.setCountyName(countyAddr.getAddressName());
			
			receiveAddrDao.insert(receiveAddr);
			
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	@Override
	public List<ReceiveAddr> getAddrList(Integer uid) {
		ReceiveAddrQuery query = new ReceiveAddrQuery();
		query.setUid(uid);
		List<ReceiveAddr> result = receiveAddrDao.selectByCondition(query);
		if(result != null && result.size() > 0){
			return result;
		}
		return null;
	}
	
	@Override
	public Result delAddr(Integer uid, Integer receiveAddrId) {
		Result result = new Result();
		try{
			this.receiveAddrDao.deleteReceiveAddr(receiveAddrId);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			EcUtils.setExceptionResult(result);
			log.error("",e);
		}
		
		return result;
	}

	@Override
	public ReceiveAddr getReceiveAddr(Integer uid, Integer receiveAddrId) {
		ReceiveAddr addr = receiveAddrDao.selectById(receiveAddrId);
		if(addr != null && addr.getUid().equals(uid)){
			return addr;
		}
		return null;
	}

	@Override
	public Result updateAddr(ReceiveAddr receiveAddr) {
		
		Result result = new Result();
		try{
			if(receiveAddr.getDefaultFlag() == 1){
				//修改该用户所有的收货地址defaultFlag = 0
				ReceiveAddr updateReceiveAddr = new ReceiveAddr();
				updateReceiveAddr.setDefaultFlag(0);
				updateReceiveAddr.setUid(receiveAddr.getUid());
				
				receiveAddrDao.modify(updateReceiveAddr);
			}
			
			Address provinceAddr = addressDao.selectByAddressId(receiveAddr.getProvince());
			Address cityAddr = addressDao.selectByAddressId(receiveAddr.getCity());
			Address countyAddr = addressDao.selectByAddressId(receiveAddr.getCounty());
			
			receiveAddr.setProvinceName(provinceAddr.getAddressName());
			receiveAddr.setCityName(cityAddr.getAddressName());
			receiveAddr.setCountyName(countyAddr.getAddressName());
			
			receiveAddrDao.modify(receiveAddr);
			
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	@Override
	public Result choseAddr(Integer uid, Integer addrId) {
		Result result = new Result();
		ReceiveAddr receiveAddr = this.getReceiveAddr(uid, addrId);
		if(receiveAddr == null){
			EcUtils.setExceptionResult(result);
			return result;
		}
		try{
			if(receiveAddr.getDefaultFlag() != 1){
				//修改该用户所有的收货地址defaultFlag = 0
				ReceiveAddr updateReceiveAddr = new ReceiveAddr();
				updateReceiveAddr.setDefaultFlag(0);
				updateReceiveAddr.setUid(receiveAddr.getUid());
				
				receiveAddrDao.modify(updateReceiveAddr);
			}
			
			receiveAddr.setDefaultFlag(1);
			receiveAddrDao.modify(receiveAddr);
			
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	@Override
	public ReceiveAddr getDefaultReceiveAddr(Integer uid) {
		ReceiveAddrQuery query = new ReceiveAddrQuery();
		query.setUid(uid);
		query.setDefaultFlag(1);
		List<ReceiveAddr> result = receiveAddrDao.selectByCondition(query);
		if(result != null && result.size() > 0){
			return result.get(0);
		}
		return null;
	}
	
	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}


	public void setReceiveAddrDao(ReceiveAddrDao receiveAddrDao) {
		this.receiveAddrDao = receiveAddrDao;
	}

}
