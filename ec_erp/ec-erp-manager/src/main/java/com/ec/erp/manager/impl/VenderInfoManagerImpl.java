package com.ec.erp.manager.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ec.erp.manager.VenderInfoManager;
import com.ec.erp.dao.VenderInfoDAO;
import com.ec.erp.domain.BusinessUserExt;

@Repository
public class VenderInfoManagerImpl implements VenderInfoManager{
	
	@Autowired
	private VenderInfoDAO venderInfoDAO;

	@Override
	public void addVender(BusinessUserExt venderInfo) {
		venderInfoDAO.addVender(venderInfo);
		
	}
	
	


}
