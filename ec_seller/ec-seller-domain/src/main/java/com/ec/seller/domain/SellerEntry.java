package com.ec.seller.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商家补录金额信息
 */
public class SellerEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 自增ID */
	private Integer paymentId;

	/** 订单号 */
    private Integer orderId;

	/** 订单款项类型（1-定金OR全款支付  2-尾款支付） */
    private Integer orderPayType;

    /** 支付方式（1、现金  2、银行卡) */
    private Integer paymentMode;

    /** 支付金额 */
    private BigDecimal paymentMoney;

    /** 业务类型 (虚拟商品销售： 101001 实物商品销售： 109001) */
    private String busiPartner;

	/** 备注 */
    private String remark;

	/** 创建时间 */
    private Date created;

	/** 修改时间 */
    private Date modified;

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderPayType() {
		return orderPayType;
	}

	public void setOrderPayType(Integer orderPayType) {
		this.orderPayType = orderPayType;
	}

	public Integer getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(Integer paymentMode) {
		this.paymentMode = paymentMode;
	}

	public BigDecimal getPaymentMoney() {
		return paymentMoney;
	}

	public void setPaymentMoney(BigDecimal paymentMoney) {
		this.paymentMoney = paymentMoney;
	}

	public String getBusiPartner() {
		return busiPartner;
	}

	public void setBusiPartner(String busiPartner) {
		this.busiPartner = busiPartner;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

    
}