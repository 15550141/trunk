package com.ec.api.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class CartInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 总商品数量
	 */
	private Integer cartSkusCount;
	
	/**
	 * 商品信息
	 */
	private List<CartSku> skuList;
	
	/**
	 * 总销售金额
	 */
	private BigDecimal totleSalePrice;
	/**
	 * 商品总价
	 */
	private BigDecimal totleOriginalPrice;
	
	/**
	 * 运费
	 */
	private BigDecimal freightMoney;
	
	/**
	 * 总优惠价格
	 */
	private BigDecimal totlePreferentialPrice;
	
	/**
	 * 判断是否是首单
	 */
	private boolean first;
	
	/**
	 * 买家用户名
	 */
	private Integer uid;

	public Integer getCartSkusCount() {
		return cartSkusCount;
	}

	public void setCartSkusCount(Integer cartSkusCount) {
		this.cartSkusCount = cartSkusCount;
	}

	public List<CartSku> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<CartSku> skuList) {
		this.skuList = skuList;
	}

	public BigDecimal getTotleSalePrice() {
		return totleSalePrice;
	}

	public void setTotleSalePrice(BigDecimal totleSalePrice) {
		this.totleSalePrice = totleSalePrice;
	}

	public BigDecimal getTotleOriginalPrice() {
		return totleOriginalPrice;
	}

	public void setTotleOriginalPrice(BigDecimal totleOriginalPrice) {
		this.totleOriginalPrice = totleOriginalPrice;
	}

	public BigDecimal getTotlePreferentialPrice() {
		return totlePreferentialPrice;
	}

	public void setTotlePreferentialPrice(BigDecimal totlePreferentialPrice) {
		this.totlePreferentialPrice = totlePreferentialPrice;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public BigDecimal getFreightMoney() {
		return freightMoney;
	}

	public void setFreightMoney(BigDecimal freightMoney) {
		this.freightMoney = freightMoney;
	}
	
	
}
