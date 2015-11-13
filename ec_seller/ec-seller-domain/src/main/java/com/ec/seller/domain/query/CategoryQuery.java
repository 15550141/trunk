package com.ec.seller.domain.query;

import java.io.Serializable;

import com.ec.seller.domain.common.BaseSearchForMysqlVo;

public class CategoryQuery extends BaseSearchForMysqlVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /** 分类ID */
    private Integer categoryId;

    /** 分类名称 */
    private String categoryName;

    /** 分类等级 */
    private Integer categoryLevel;

    /** 父分类ID */
    private Integer parentCategoryId;

    /** 排序号 */
    private Integer sortNumber;

    /** 是否有销售属性 */
    private Integer ifHaveSaleProperty;

    /** 有效性 */
    private Integer yn;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(Integer categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public Integer getIfHaveSaleProperty() {
        return ifHaveSaleProperty;
    }

    public void setIfHaveSaleProperty(Integer ifHaveSaleProperty) {
        this.ifHaveSaleProperty = ifHaveSaleProperty;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

}