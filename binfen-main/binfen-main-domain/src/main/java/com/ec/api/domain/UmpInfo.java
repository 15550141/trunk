package com.ec.api.domain;


import java.io.Serializable;
import java.util.Date;

/**
 *	U付
 */
public class UmpInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer orderId;
	
	private Integer userId;
	/** 类型，1为支付，2为支付完成（U付回调） */
	private Integer type;
	/** U付订单号 */
	private String tradeNo;
	/** 参数列表 */
	private String param;
	/** 订单金额 */
	private Integer amount;
	/** 
	 *  支付状态 
	 *  WAIT_BUYER_PAY交易创建，等待买家付款     
	 *  TRADE_SUCCESS交易成功，不能再次进行交易
	 *  TRADE_CLOSED交易关闭 。指定时间段内未支付时关闭的交易；
	 *  TRADE_CANCEL交易撤销
	 *  TRADE_FAIL交易失败
	 * */
	private String tradeState;
	/** 生成支付信息时的日期 */
	private Date merDate;
	/** 用户支付日期 */
	private Date payDate;
	/** 对账日期 */
	private Date settleDate;
	
	private Date created;
	
	private Date modified;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	public Date getMerDate() {
		return merDate;
	}

	public void setMerDate(Date merDate) {
		this.merDate = merDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
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
	
	
	
}