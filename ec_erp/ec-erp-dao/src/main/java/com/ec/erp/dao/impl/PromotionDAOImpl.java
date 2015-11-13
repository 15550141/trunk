package com.ec.erp.dao.impl;

import java.util.List;

import com.ec.erp.dao.PromotionDAO;
import com.ec.erp.domain.PromotionInfo;


import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class PromotionDAOImpl extends SqlMapClientTemplate implements PromotionDAO {

	@Override
	public int queryPromotionCount(PromotionInfo promotionInfo) {
		return ( Integer )super.queryForObject("PromotionInfo.queryPromotionCount", promotionInfo);
	}

	@Override
	public List<PromotionInfo> queryPromotionList(PromotionInfo promotionInfo) {
		
		return super.queryForList("PromotionInfo.queryPromotionList", promotionInfo);
	}

}