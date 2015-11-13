package com.ec.seller.domain;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付记录信息
 *
 */
public class PaymentInfo  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    /** 支付记录ID-自增 */
    private Integer paymentId;

    /** 订单ID */
    private Integer orderId;
    
    /** 订单款项类型（1-定金OR全款支付  2-尾款支付） */
    private Integer orderPayType;

    /** 支付方式（1、连连支付 ） */
    private Integer paymentMode;

    /** 支付信息类型(1、支付信息   2、支付成功确认信息) */
    private Integer paymentInfoType;
    
    /** 支付金额 */
    private BigDecimal paymentMoney;

    /** 第三方支付单号 */
    private String paymentNumber;

    /** 业务类型 (虚拟商品销售： 101001 实物商品销售： 109001) */
    private String busiPartner;

    /** 支付订单时间 */
    private String dtOrder;

    /** 订单有效时间 */
    private Integer validOrder;

    /** 清算日期 */
    private String settleDate;

    /** 银行名称 */
    private String bankName;

    /** 银行编号 */
    private String bankCode;

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

	public Integer getPaymentInfoType() {
		return paymentInfoType;
	}

	public void setPaymentInfoType(Integer paymentInfoType) {
		this.paymentInfoType = paymentInfoType;
	}


	public BigDecimal getPaymentMoney() {
		return paymentMoney;
	}

	public void setPaymentMoney(BigDecimal paymentMoney) {
		this.paymentMoney = paymentMoney;
	}

	public String getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	public String getBusiPartner() {
		return busiPartner;
	}

	public void setBusiPartner(String busiPartner) {
		this.busiPartner = busiPartner;
	}

	public String getDtOrder() {
		return dtOrder;
	}

	public void setDtOrder(String dtOrder) {
		this.dtOrder = dtOrder;
	}

	public Integer getValidOrder() {
		return validOrder;
	}

	public void setValidOrder(Integer validOrder) {
		this.validOrder = validOrder;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
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