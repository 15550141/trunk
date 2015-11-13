package com.ec.erp.dao;

import java.util.List;

import com.ec.erp.domain.PromotionInfo;


public interface PromotionDAO {

	int queryPromotionCount(PromotionInfo promotionInfo);

	List<PromotionInfo> queryPromotionList(PromotionInfo promotionInfo);

}