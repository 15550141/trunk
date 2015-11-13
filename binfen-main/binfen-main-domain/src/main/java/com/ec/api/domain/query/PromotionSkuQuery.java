package com.ec.api.domain.query;

import java.io.Serializable;
import java.util.Date;

public class PromotionSkuQuery  extends BaseSearchForMysqlVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 自增ID */
	private Integer id;
	
	/** 促销ID */
	private Integer promotionId;
	
	/** SKU_ID */
	private Integer skuId;
	
	/** 直降价格 */
	private Integer deduction_price;
	
	private Date modified;
	
	private Date created;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}

	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}


	public Integer getDeduction_price() {
		return deduction_price;
	}

	public void setDeduction_price(Integer deduction_price) {
		this.deduction_price = deduction_price;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	
}
