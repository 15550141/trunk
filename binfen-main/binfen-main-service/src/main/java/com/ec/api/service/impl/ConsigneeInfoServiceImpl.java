package com.ec.api.service.impl;

import java.util.List;

import com.ec.api.dao.AddressDao;
import com.ec.api.domain.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ec.api.dao.ConsigneeInfoDao;
import com.ec.api.domain.ConsigneeInfo;
import com.ec.api.domain.query.ConsigneeInfoQuery;
import com.ec.api.service.ConsigneeInfoService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;

@Service(value="consigneeInfoService")
public class ConsigneeInfoServiceImpl implements ConsigneeInfoService {
	private static final Logger log = LoggerFactory.getLogger(ConsigneeInfoServiceImpl.class);
	private ConsigneeInfoDao consigneeInfoDao;
    private AddressDao addressDao;
	
	@Override
	public Result getConsigneeInfosByUserId(Integer userId) {
		Result result = new Result();
		try{
			ConsigneeInfoQuery consigneeInfoQuery = new ConsigneeInfoQuery();
			consigneeInfoQuery.setUserId(userId);
			List<ConsigneeInfo> list = consigneeInfoDao.selectByCondition(consigneeInfoQuery);
			if(list == null || list.size() == 0){
				result.setResultCode("9005");
				result.setResultMessage("收货人列表不存在");
				return result;
			}
            for(ConsigneeInfo ci : list){
                if( null != ci.getConsigneeProvince() ){
                    Address address1 = addressDao.selectByAddressId(ci.getConsigneeProvince());
				    if(address1 != null){
					    ci.setConsigneeProvinceName(address1.getAddressName());
				    }
                }
                if( null != ci.getConsigneeCity() ){
                    Address address1 = addressDao.selectByAddressId(ci.getConsigneeCity());
				    if(address1 != null){
					    ci.setConsigneeCityName(address1.getAddressName());
				    }
                }
                if( null != ci.getConsigneeCounty() ){
                    Address address1 = addressDao.selectByAddressId(ci.getConsigneeCounty());
				    if(address1 != null){
					    ci.setConsigneeCountyName(address1.getAddressName());
				    }
                }
            }
			result.setResult(list);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result addConsigneeInfo(ConsigneeInfo consigneeInfo) {
		Result result = new Result();
		try{
			consigneeInfoDao.insert(consigneeInfo);
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result updateConsigneeInfo(ConsigneeInfo consigneeInfo) {
		Result result = new Result();
		try{
			ConsigneeInfo ci = consigneeInfoDao.selectByConsigneeId(consigneeInfo.getConsigneeId());
			if(ci == null){
				result.setResultCode("9005");
				result.setResultMessage("收货人列表不存在");
				return result;
			}
			consigneeInfoDao.modify(consigneeInfo);
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result delConsigneeInfo(Integer consigneeId) {
		Result result = new Result();
		try{
			ConsigneeInfo consigneeInfo = consigneeInfoDao.selectByConsigneeId(consigneeId);
			if(consigneeInfo == null){
				result.setResultCode("9005");
				result.setResultMessage("收货人列表不存在");
				return result;
			}
			consigneeInfoDao.delConsigneeInfo(consigneeId);
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	public void setConsigneeInfoDao(ConsigneeInfoDao consigneeInfoDao) {
		this.consigneeInfoDao = consigneeInfoDao;
	}

    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }
}
