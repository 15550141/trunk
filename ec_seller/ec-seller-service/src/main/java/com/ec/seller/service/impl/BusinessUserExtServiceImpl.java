package com.ec.seller.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ec.seller.domain.BusinessUserExt;
import com.ec.seller.manager.BusinessUserExtManager;
import com.ec.seller.service.BusinessUserExtService;


@Service(value = "businessUserExtService")
public class BusinessUserExtServiceImpl implements BusinessUserExtService{
	@Autowired
	private BusinessUserExtManager businessUserExtManager;

	@Override
	public Integer insert(BusinessUserExt businessUserExt) {
		
		return businessUserExtManager.insert(businessUserExt);
	}

	@Override
	public void modifyByUserId(BusinessUserExt businessUserExt) {
		businessUserExtManager.modifyByUserId(businessUserExt);
		
	}

	@Override
	public BusinessUserExt selectByUserId(int userId) {
		
		return businessUserExtManager.selectByUserId(userId);
	}


}
