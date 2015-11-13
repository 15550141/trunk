package com.ec.api.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ec.api.dao.AddressDao;
import com.ec.api.domain.Address;
import com.ec.api.domain.query.AddressQuery;
import com.ec.api.service.AddressService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;

@Service(value="addressService")
public class AddressServiceImpl implements AddressService {
	private static final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);
	
	private AddressDao addressDao;

//	@Override
//	public Result getCounties(Integer city) {
//		Result result = new Result();
//		try{
//			AddressQuery addressQuery = new AddressQuery();
//			addressQuery.setAddressLevel(3);
//			addressQuery.setAddressFid(city);
//			List<Address> list = addressDao.selectByCondition(addressQuery);
//			if(list == null || list.size() ==0){
//				result.setResultCode("6001");
//				result.setResultMessage("地址列表不存在");
//				return result;
//			}
//			result.setResult(list);
//			EcUtils.setSuccessResult(result);
//		}catch (Exception e) {
//			log.error("", e);
//			EcUtils.setExceptionResult(result);
//		}
//		return result;
//	}

	@Override
	public List<Address> getArea(Integer fid) {
		try{
			AddressQuery addressQuery = new AddressQuery();
			addressQuery.setAddressFid(fid);
			return addressDao.selectByCondition(addressQuery);
		}catch (Exception e) {
			log.error("", e);
		}
		return null;
	}
	
	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

	
}
