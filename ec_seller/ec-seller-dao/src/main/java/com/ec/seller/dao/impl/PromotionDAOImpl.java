package com.ec.seller.dao.impl;

import java.util.List;

import com.ec.seller.dao.PromotionDAO;
import com.ec.seller.domain.PromotionInfo;


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