package com.ec.erp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.erp.common.utils.PaginatedList;
import com.ec.erp.common.utils.impl.PaginatedArrayList;
import com.ec.erp.domain.BusinessUserExt;
import com.ec.erp.domain.query.BusinessUserExtQuery;
import com.ec.erp.domain.query.UserInfoQuery;
import com.ec.erp.manager.BusinessUserExtManager;
import com.ec.erp.service.BusinessUserExtService;


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
	
	@Override
	public Map<String, Object> queryBusinessUserExtList(BusinessUserExtQuery businessUserExtQuery) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		PaginatedList<BusinessUserExt> businessUserExtList =null;
		//创建一个分页的促销对象
		businessUserExtList=new PaginatedArrayList<BusinessUserExt>(businessUserExtQuery.getPageNo(),businessUserExtQuery.getPageSize());
		int count =	businessUserExtManager.countByCondition(businessUserExtQuery);
		businessUserExtList.setTotalItem(count);
		int startRow=businessUserExtList.getStartRow();
		if (startRow == 0) {
			startRow = 1;
		}
		businessUserExtQuery.setStart(startRow-1);
		List<BusinessUserExt> list = businessUserExtManager.selectByConditionWithPage(businessUserExtQuery);
		
		businessUserExtList.addAll(list);
		resultMap.put("businessUserExtList", businessUserExtList);
		resultMap.put("businessUserExtQuery", businessUserExtQuery);	
		return resultMap;
	}

	@Override
	public List<BusinessUserExt> selectByCondition(
			BusinessUserExtQuery businessUserExtQuery) {
		return this.businessUserExtManager.selectByCondition(businessUserExtQuery);
	}



}
