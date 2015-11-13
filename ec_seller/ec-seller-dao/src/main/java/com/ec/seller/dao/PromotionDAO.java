package com.ec.seller.dao;

import java.util.List;

import com.ec.seller.domain.PromotionInfo;


public interface PromotionDAO {

	int queryPromotionCount(PromotionInfo promotionInfo);

	List<PromotionInfo> queryPromotionList(PromotionInfo promotionInfo);

}