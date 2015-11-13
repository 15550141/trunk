package com.ec.seller.domain;

import java.io.Serializable;
import java.util.Date;

public class ItemProperty implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /** 商品属性自增ID */
    private Integer id;

    /** 商品ID */
    private Integer itemId;

    /** 商品属性 */
    private String itemPropertys;

    /** 创建时间 */
    private Date created;

    /** 修改时间 */
    private Date modified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemPropertys() {
        return itemPropertys;
    }

    public void setItemPropertys(String itemPropertys) {
        this.itemPropertys = itemPropertys;
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