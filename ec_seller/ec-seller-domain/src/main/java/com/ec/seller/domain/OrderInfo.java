package com.ec.seller.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 订单信息
 *
 */
public class OrderInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /** 订单ID */
    private Integer orderId;
    
    /** 订单类型（1、在线支付  2、货到付款） */
    private Integer orderType;
    
    /** 支付方式 （1现金，2银行卡，3微信支付 ， 4支付宝支付） */
    private Integer paymentType;

    /** 商家ID */
    private Integer venderUserId;

    /** 用户ID */
    private Integer userId;

    /** 收货地址ID  */
    private Integer consigneeId;

    /** 收货人姓名 */
    private String consigneeName;

    /** 收货人省份 */
    private Integer consigneeProvince;

    /** 收货人市区 */
    private Integer consigneeCity;

    /** 收货人县区 */
    private Integer consigneeCounty;

    /** 收货人详细地址 */
    private String consigneeAddress;

    /** 收货人手机号 */
    private String consigneeMobile;

    /** 备注 */
    private String remark;

    /** 订单总金额   不包含优惠金额  */
    private Integer orderMoney;

    /** 优惠总金额 */
    private Integer discountMoney;

    /** 优惠明细 */
    private String discountInfo;

    /** 下单时间 */
    private Date orderTime;
    /**
     * 支付时间
     */
    private Date payTime;
    
    /**
     * 期望到货时间
     */
    private String hopeArrivalTime;

    /** 点击确认发货时间 */
    private Date sendOutTime;

    /**
     * 预计发货时间
     */
    private Date estimateSendOutTime;
    
    /** 客服回电时间，确认预计发货时间 */
    private Date telephoneCallTime;
    
    /** 订单完成时间 */
    private Date finishTime;

    /** 订单状态（1 等待付款，2 等待付款确认 3 暂停 4 店长最终审核 5等待打印  6等待出库 7 等待打包  8等待发货  9 自提途中  10上门提货 11 自提退货  12确认自提 13 等待确认收货  14配送退货 15 收款确认 16 锁定  17等待退款  50完成 51取消订单） */
    private Integer orderStatus;

    /** 下单IP */
    private String ip;

    /** 锁定状态 */
    private Integer lockStatus;

    /** 锁定原因 */
    private String lockReason;

    /** 创建时间 */
    private Date created;

    /** 修改时间 */
    private Date modified;
    
    private List<OrderDetail> orderDetails;
    
    /** 订单属性标记位 */
    private Long orderProperty;
    
    /**
     * 有效无效
     * 1有效
     * 0无效
     */
    private Integer yn = 1;
    
    /**
     * 父订单号
     */
    private Long parentOrderId = 0l;
    
    /**
     * 订单标记位 key是位置，value 1 是有标
     */
    private Map<Integer, Integer> mapProp;
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getVenderUserId() {
		return venderUserId;
	}

	public void setVenderUserId(Integer venderUserId) {
		this.venderUserId = venderUserId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getConsigneeId() {
		return consigneeId;
	}

	public void setConsigneeId(Integer consigneeId) {
		this.consigneeId = consigneeId;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public Integer getConsigneeProvince() {
		return consigneeProvince;
	}

	public void setConsigneeProvince(Integer consigneeProvince) {
		this.consigneeProvince = consigneeProvince;
	}

	public Integer getConsigneeCity() {
		return consigneeCity;
	}

	public void setConsigneeCity(Integer consigneeCity) {
		this.consigneeCity = consigneeCity;
	}

	public Integer getConsigneeCounty() {
		return consigneeCounty;
	}

	public void setConsigneeCounty(Integer consigneeCounty) {
		this.consigneeCounty = consigneeCounty;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getConsigneeMobile() {
		return consigneeMobile;
	}

	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(Integer orderMoney) {
		this.orderMoney = orderMoney;
	}

	public Integer getDiscountMoney() {
		return discountMoney;
	}

	public void setDiscountMoney(Integer discountMoney) {
		this.discountMoney = discountMoney;
	}

	public String getDiscountInfo() {
		return discountInfo;
	}

	public void setDiscountInfo(String discountInfo) {
		this.discountInfo = discountInfo;
	}


	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getSendOutTime() {
		return sendOutTime;
	}

	public void setSendOutTime(Date sendOutTime) {
		this.sendOutTime = sendOutTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getLockReason() {
		return lockReason;
	}

	public void setLockReason(String lockReason) {
		this.lockReason = lockReason;
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

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}


	
	public Long getOrderProperty() {
		return orderProperty;
	}

	public void setOrderProperty(Long orderProperty) {
		this.orderProperty = orderProperty;
	}

	public Map<Integer, Integer> getMapProp() {
		return mapProp;
	}

	public void setMapProp(Map<Integer, Integer> mapProp) {
		this.mapProp = mapProp;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	public Integer getYn() {
		return yn;
	}

	public void setYn(Integer yn) {
		this.yn = yn;
	}

	public Long getParentOrderId() {
		return parentOrderId;
	}

	public void setParentOrderId(Long parentOrderId) {
		this.parentOrderId = parentOrderId;
	}

	public String getHopeArrivalTime() {
		return hopeArrivalTime;
	}

	public void setHopeArrivalTime(String hopeArrivalTime) {
		this.hopeArrivalTime = hopeArrivalTime;
	}

	/** 订单状态（1 等待付款，2 等待付款确认 3 暂停 4 店长最终审核 5等待打印  6等待出库 7 等待打包  8等待发货  9 自提途中  10上门提货 11 自提退货  12确认自提 13 等待确认收货  14配送退货 15 收款确认 16 锁定  17等待退款  50完成 51取消订单） */
	public String getBuyerOrderStatusName(){
		if(this.orderStatus == 0){
			return "新订单";
		}
		
		if(orderStatus == 1 || orderStatus == 2){
			return "等待付款";
		}
		
		if(orderStatus == 3 || orderStatus == 4 || orderStatus == 5 || orderStatus == 6 || orderStatus == 7 || orderStatus == 8){
			return "等待发货";
		}
		
		if(orderStatus == 13){
			return "已发货，配送途中";
		}
		if(orderStatus == 14){
			return "已发货，配送途中";
		}
		
		if(orderStatus == 15 || orderStatus == 50){
			return "完成";
		}
		if(orderStatus == 16){
			return "锁定";
		}
		if(orderStatus == 17){
			return "等待退款";
		}
		if(orderStatus == 51){
			return "订单已取消";
		}
		
		return "";
	}
	/**
	 * 获取订单总金额（元）
	 * @return
	 */
	public BigDecimal getBigDecimalOrderMoney(){
		if(this.getOrderMoney() == null){
			return new BigDecimal(0);
		}
		return new BigDecimal(this.getOrderMoney()).divide(new BigDecimal(100));
	}
	/**
	 * 获取订单总优惠金额（元）
	 * @return
	 */
	public BigDecimal getBigDecimalDiscountMoney(){
		if(this.getDiscountMoney() == null){
			return new BigDecimal(0);
		}
		return new BigDecimal(this.getDiscountMoney()).divide(new BigDecimal(100));
	}

	public Date getEstimateSendOutTime() {
		return estimateSendOutTime;
	}

	public void setEstimateSendOutTime(Date estimateSendOutTime) {
		this.estimateSendOutTime = estimateSendOutTime;
	}

	public Date getTelephoneCallTime() {
		return telephoneCallTime;
	}

	public void setTelephoneCallTime(Date telephoneCallTime) {
		this.telephoneCallTime = telephoneCallTime;
	}
	
}