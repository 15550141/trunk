package com.ec.seller.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ec.seller.dao.AddressDao;
import com.ec.seller.dao.BusinessUserExtDao;
import com.ec.seller.domain.Address;
import com.ec.seller.domain.BusinessUserExt;
import com.ec.seller.domain.query.AddressQuery;
import com.ec.seller.manager.AddressManager;
import com.ec.seller.manager.BusinessUserExtManager;


@Repository
public class BusinessUserExtManagerImpl implements BusinessUserExtManager{
	
	@Autowired
	private BusinessUserExtDao businessUserExtDao;
	private final static Log LOG = LogFactory.getLog(BusinessUserExtManagerImpl.class);
	@Override
	public Integer insert(BusinessUserExt businessUserExt) {
		return businessUserExtDao.insert(businessUserExt);
	}
	@Override
	public void modifyByUserId(BusinessUserExt businessUserExt) {
		businessUserExtDao.modifyByUserId(businessUserExt);
		
	}
	@Override
	public BusinessUserExt selectByUserId(int userId) {
		
		return businessUserExtDao.selectByUserId(userId);
	}
	
	
	

}
