package com.ec.api.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CartSku implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer itemId;
	
	/**
	 * 商品名称
	 */
	private String name;
	
	/**
	 * 商品图片
	 */
	private String image;
	
	private Integer skuId;
	
	/**
	 * 购买数量
	 */
	private Integer num;
	
	/**
	 * sku价格
	 */
	private BigDecimal skuPrice;
	
	/**
	 * 标记位
	 */
	private Long property;
	
	/**
	 * 商品属性
	 */
	private String salesProperty;
	
	/**
	 * 商品属性中文
	 */
	private String salesPropertyName;
	
	private Date created;
	
	private Date updated;
	
	/**
	 * 是否是赠品
	 * 0是非赠品
	 * 1是赠品
	 */
	private boolean gift = false; 

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}


	public BigDecimal getSkuPrice() {
		return skuPrice;
	}

	public void setSkuPrice(BigDecimal skuPrice) {
		this.skuPrice = skuPrice;
	}

	public Long getProperty() {
		return property;
	}

	public void setProperty(Long property) {
		this.property = property;
	}


	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSalesProperty() {
		return salesProperty;
	}

	public void setSalesProperty(String salesProperty) {
		this.salesProperty = salesProperty;
	}

	public String getSalesPropertyName() {
		return salesPropertyName;
	}

	public void setSalesPropertyName(String salesPropertyName) {
		this.salesPropertyName = salesPropertyName;
	}

	public boolean isGift() {
		return gift;
	}

	public void setGift(boolean gift) {
		this.gift = gift;
	}

}
