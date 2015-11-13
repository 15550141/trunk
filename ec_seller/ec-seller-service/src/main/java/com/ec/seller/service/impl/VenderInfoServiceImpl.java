package com.ec.seller.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ec.seller.domain.UserInfo;
import com.ec.seller.domain.BusinessUserExt;
import com.ec.seller.manager.UserManager;
import com.ec.seller.manager.VenderInfoManager;
import com.ec.seller.service.UserService;
import com.ec.seller.service.VenderInfoService;

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
