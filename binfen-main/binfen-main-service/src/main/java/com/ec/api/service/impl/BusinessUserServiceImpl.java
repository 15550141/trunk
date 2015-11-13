package com.ec.api.service.impl;

import com.ec.api.dao.AddressDao;
import com.ec.api.dao.BusinessUserExtDao;
import com.ec.api.dao.UserInfoDao;
import com.ec.api.domain.Address;
import com.ec.api.domain.BusinessUserExt;
import com.ec.api.domain.UserInfo;
import com.ec.api.domain.query.AddressQuery;
import com.ec.api.service.BusinessUserService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: iboy
 * Date: 2014-9-22
 * Time: 17:24:55
 */
@Service(value="businessUserService")
public class BusinessUserServiceImpl implements BusinessUserService {
    private static final Logger log = LoggerFactory.getLogger(BusinessUserServiceImpl.class);
    /** 商户信息DAO */
    private BusinessUserExtDao businessUserExtDao;
    private AddressDao addressDao;
    private UserInfoDao userInfoDao;

    @Override
    public Result getBusinessUserExtByUserID(Integer userId) {
        Result result = new Result();
		try{
            log.info("BusinessUserServiceImpl.getBusinessUserExtByUserID.userId:"+userId);
            BusinessUserExt businessUserExt = businessUserExtDao.selectByUserId(userId);
            UserInfo ui = userInfoDao.selectByUserId(userId);
            if( null == businessUserExt ){
				result.setResultCode("4001");
				result.setResultMessage("用户不存在");
				return result;
			}
            if( null != ui ){
                businessUserExt.setMobile(ui.getMobile());
            }
            if( null != businessUserExt.getSupplyProvince() ){
                businessUserExt.setSupplyProvinceName(addressDao.selectByAddressId(businessUserExt.getSupplyProvince()).getAddressName());
            }
            if( null != businessUserExt.getSupplyCity() ){
                businessUserExt.setSupplyCityName(addressDao.selectByAddressId(businessUserExt.getSupplyCity()).getAddressName());
            }
            if( null != businessUserExt.getSupplyCounty() ){
                businessUserExt.setSupplyCountyName(addressDao.selectByAddressId(businessUserExt.getSupplyCounty()).getAddressName());
            }
            log.info("BusinessUserServiceImpl.getBusinessUserExtByUserID.businessUserExt==="+businessUserExt);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("businessUser", businessUserExt);
			result.setResult(map);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("BusinessUserServiceImpl.getBusinessUserExtByUserID.userId:"+userId, e);
			EcUtils.setExceptionResult(result);
		}
		return result;   
    }

    public void setBusinessUserExtDao(BusinessUserExtDao businessUserExtDao) {
        this.businessUserExtDao = businessUserExtDao;
    }

    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }
}
