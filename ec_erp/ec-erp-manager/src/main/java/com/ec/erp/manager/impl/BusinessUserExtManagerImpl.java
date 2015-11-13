package com.ec.erp.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ec.erp.dao.AddressDao;
import com.ec.erp.dao.BusinessUserExtDao;
import com.ec.erp.domain.Address;
import com.ec.erp.domain.BusinessUserExt;
import com.ec.erp.domain.query.AddressQuery;
import com.ec.erp.domain.query.BusinessUserExtQuery;
import com.ec.erp.manager.AddressManager;
import com.ec.erp.manager.BusinessUserExtManager;


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
	@Override
	public int countByCondition(BusinessUserExtQuery businessUserExtQuery) {
		
		return businessUserExtDao.countByCondition(businessUserExtQuery);
	}
	@Override
	public List<BusinessUserExt> selectByConditionWithPage(
			BusinessUserExtQuery businessUserExtQuery) {
		
		return businessUserExtDao.selectByConditionWithPage(businessUserExtQuery);
	}
	@Override
	public List<BusinessUserExt> selectByCondition(
			BusinessUserExtQuery businessUserExtQuery) {
		return businessUserExtDao.selectByCondition(businessUserExtQuery);
	}

}
