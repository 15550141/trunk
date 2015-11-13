package com.ec.seller.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BalanceInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer balanceId;
	private Date balanceDate;
	private Integer venderUserId;
	private BigDecimal orderMoneyAll;
	private BigDecimal paymentGoods;
	private BigDecimal commission;
	private BigDecimal balanceMoney;
	private Integer balanceStatus;
	private Date created;
	private Date modified;
	private String reason;
	/**
     * 商家店铺名称
     */
    private String venderUserShopName;
	
	private List<OrderInfo> orderInfoList;
	
	public Integer getBalanceId() {
		return balanceId;
	}
	public void setBalanceId(Integer balanceId) {
		this.balanceId = balanceId;
	}
	public Date getBalanceDate() {
		return balanceDate;
	}
	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}
	public Integer getVenderUserId() {
		return venderUserId;
	}
	public void setVenderUserId(Integer venderUserId) {
		this.venderUserId = venderUserId;
	}
	public BigDecimal getOrderMoneyAll() {
		return orderMoneyAll;
	}
	public void setOrderMoneyAll(BigDecimal orderMoneyAll) {
		this.orderMoneyAll = orderMoneyAll;
	}
	public BigDecimal getPaymentGoods() {
		return paymentGoods;
	}
	public void setPaymentGoods(BigDecimal paymentGoods) {
		this.paymentGoods = paymentGoods;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public BigDecimal getBalanceMoney() {
		return balanceMoney;
	}
	public void setBalanceMoney(BigDecimal balanceMoney) {
		this.balanceMoney = balanceMoney;
	}
	public Integer getBalanceStatus() {
		return balanceStatus;
	}
	public void setBalanceStatus(Integer balanceStatus) {
		this.balanceStatus = balanceStatus;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public List<OrderInfo> getOrderInfoList() {
		return orderInfoList;
	}
	public void setOrderInfoList(List<OrderInfo> orderInfoList) {
		this.orderInfoList = orderInfoList;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getVenderUserShopName() {
		return venderUserShopName;
	}
	public void setVenderUserShopName(String venderUserShopName) {
		this.venderUserShopName = venderUserShopName;
	}
	
	
}
