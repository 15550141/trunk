package com.ec.seller.manager.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ec.seller.manager.VenderInfoManager;
import com.ec.seller.dao.VenderInfoDAO;
import com.ec.seller.domain.BusinessUserExt;

@Repository
public class VenderInfoManagerImpl implements VenderInfoManager{
	
	@Autowired
	private VenderInfoDAO venderInfoDAO;

	@Override
	public void addVender(BusinessUserExt venderInfo) {
		venderInfoDAO.addVender(venderInfo);
		
	}
	
	


}
