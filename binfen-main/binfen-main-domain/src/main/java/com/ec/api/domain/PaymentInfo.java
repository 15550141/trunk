package com.ec.api.domain;


import java.io.Serializable;
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
    
    private Integer uid;

    /** 订单ID */
    private Integer orderId;
    
    /** 订单支付方式（1、微信支付） */
    private Integer orderPayType;


    /** 支付信息类型(1、支付信息   2、支付成功确认信息) */
    private Integer paymentInfoType;
    
    /** 支付信息、第三方返回内容信息 */
    private String paymentInfoMessage;
    
    /** 支付金额 */
    private Integer paymentMoney;

    /** 第三方支付单号 */
    private String paymentNumber;

    /** 支付订单时间 */
    private Date dtOrder;

    /** 订单有效时间 */
    private Date validOrder;

    /** 清算日期 */
    private Date settleDate;

    /** 创建时间 */
    private Date created;

    /** 修改时间 */
    private Date modified;
    
    private Integer yn;

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

	public Integer getPaymentInfoType() {
		return paymentInfoType;
	}

	public void setPaymentInfoType(Integer paymentInfoType) {
		this.paymentInfoType = paymentInfoType;
	}


	public Integer getPaymentMoney() {
		return paymentMoney;
	}

	public void setPaymentMoney(Integer paymentMoney) {
		this.paymentMoney = paymentMoney;
	}

	public String getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	public Date getDtOrder() {
		return dtOrder;
	}

	public void setDtOrder(Date dtOrder) {
		this.dtOrder = dtOrder;
	}

	public Date getValidOrder() {
		return validOrder;
	}

	public void setValidOrder(Date validOrder) {
		this.validOrder = validOrder;
	}

	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
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

	public String getPaymentInfoMessage() {
		return paymentInfoMessage;
	}

	public void setPaymentInfoMessage(String paymentInfoMessage) {
		this.paymentInfoMessage = paymentInfoMessage;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getYn() {
		return yn;
	}

	public void setYn(Integer yn) {
		this.yn = yn;
	}
    
}