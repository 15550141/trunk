package com.ec.seller.domain;


import java.io.Serializable;
import java.util.Date;

public class Property implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /** 属性ID */
    private Integer propertyId;

    /** 分类ID */
    private Integer categoryId;

    /** 属性名 */
    private String propertyName;

    /** 属性类型 */
    private Integer propertyType;

    /** 是否多选 */
    private Integer ifMultiSelect;

    /** 是否可自定义属性值 */
    private Integer ifCanCustom;

    /** 排序 */
    private Integer sortNumber;

    /** 有效性 */
    private Integer yn;

    /** 创建时间 */
    private Date created;

    /** 修改时间 */
    private Date modified;

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Integer getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(Integer propertyType) {
        this.propertyType = propertyType;
    }

    public Integer getIfMultiSelect() {
        return ifMultiSelect;
    }

    public void setIfMultiSelect(Integer ifMultiSelect) {
        this.ifMultiSelect = ifMultiSelect;
    }

    public Integer getIfCanCustom() {
        return ifCanCustom;
    }

    public void setIfCanCustom(Integer ifCanCustom) {
        this.ifCanCustom = ifCanCustom;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
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