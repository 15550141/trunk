package com.ec.seller.domain.query;

import java.io.Serializable;
import java.util.Date;

import com.ec.seller.domain.common.BaseSearchForMysqlVo;

public class PropertyValueQuery extends BaseSearchForMysqlVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /** 商品属性值ID */
    private Integer propertyValueId;

    /** 属性ID */
    private Integer propertyId;

    /** 属性值ID */
    private String propertyValueName;

    /** 属性值类型 */
    private Integer propertyValueType;

    /** 排序号 */
    private Integer sortNumber;

    /** 商家ID */
    private Integer venderUserId;

    /** 创建时间 */
    private Date created;

    /** 修改时间 */
    private Date modified;

    public Integer getPropertyValueId() {
        return propertyValueId;
    }

    public void setPropertyValueId(Integer propertyValueId) {
        this.propertyValueId = propertyValueId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyValueName() {
        return propertyValueName;
    }

    public void setPropertyValueName(String propertyValueName) {
        this.propertyValueName = propertyValueName;
    }

    public Integer getPropertyValueType() {
        return propertyValueType;
    }

    public void setPropertyValueType(Integer propertyValueType) {
        this.propertyValueType = propertyValueType;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public Integer getVenderUserId() {
        return venderUserId;
    }

    public void setVenderUserId(Integer venderUserId) {
        this.venderUserId = venderUserId;
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