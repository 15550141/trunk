package com.ec.seller.domain;

import java.io.Serializable;
import java.util.Date;

public class PromotionSku implements Serializable{
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
	private Double deductionPrice;
	
	private Date modified;
	
	private Date created;
	
	/**  yn */
	private Integer yn;

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

	public Integer getYn() {
		return yn;
	}

	public void setYn(Integer yn) {
		this.yn = yn;
	}

	public Double getDeductionPrice() {
		return deductionPrice;
	}

	public void setDeductionPrice(Double deductionPrice) {
		this.deductionPrice = deductionPrice;
	}

	
}
