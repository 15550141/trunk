package com.ec.erp.manager;


import java.util.List;

import com.ec.erp.domain.PromotionSku;
import com.ec.erp.domain.query.PromotionSkuQuery;


public interface PromotionSkuManager {
	/**
	 * 添加促销信息
	 * @param promotionInfo
	 * @return
	 */
	public Integer insert(PromotionSku promotionSku);

	public List<PromotionSku> selectByCondition(
			PromotionSkuQuery promotionSkuQuery);
	
	public void modifyByPromotionId(PromotionSku promotionSku);

}
