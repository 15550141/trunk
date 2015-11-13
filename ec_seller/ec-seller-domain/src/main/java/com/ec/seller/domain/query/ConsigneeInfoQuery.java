package com.ec.seller.domain.query;

import java.io.Serializable;
import java.util.Date;

import com.ec.seller.domain.common.BaseSearchForMysqlVo;

public class ConsigneeInfoQuery extends BaseSearchForMysqlVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /** 收货人信息ID-自增 */
    private Integer consigneeId;

    /** 用户ID */
    private Integer userId;

    /** 收货人姓名 */
    private String consigneeName;

    /** 收货人省份 */
    private Integer consigneeProvince;

    /** 收货人市区 */
    private Integer consigneeCity;

    /** 收货人县区 */
    private Integer consigneeCounty;

    /** 收货人地址 */
    private String consigneeAddress;

    /** 收货人手机号 */
    private String consigneeMobile;

    /** 最后使用时间 */
    private Date lastTime;

    /** 创建时间 */
    private Date created;

    /** 修改时间  */
    private Date modified;

    public Integer getConsigneeId() {
        return consigneeId;
    }

    public void setConsigneeId(Integer consigneeId) {
        this.consigneeId = consigneeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
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