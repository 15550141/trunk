package com.ec.erp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ec.erp.domain.UserInfo;
import com.ec.erp.domain.BusinessUserExt;
import com.ec.erp.manager.UserManager;
import com.ec.erp.manager.VenderInfoManager;
import com.ec.erp.service.UserService;
import com.ec.erp.service.VenderInfoService;

@Service(value = "venderInfoService")
public class VenderInfoServiceImpl implements VenderInfoService{
	@Autowired
	private VenderInfoManager venderInfoManager;

	@Override
	public void addVender(BusinessUserExt venderInfo) {
		// TODO Auto-generated method stub
		venderInfoManager.addVender(venderInfo);
		
	}



}
