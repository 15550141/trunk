package com.ec.seller.domain;


import java.io.Serializable;
import java.util.Date;

public class ItemDescription  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /** 自增ID */
    private Integer id;

    /** 商品ID */
    private Integer itemId;

    /** PC端商品描述 */
    private String pcDescriptionInfo;

    /** APP端商品描述 */
    private String appDescriptionInfo;

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

    public String getPcDescriptionInfo() {
        return pcDescriptionInfo;
    }

    public void setPcDescriptionInfo(String pcDescriptionInfo) {
        this.pcDescriptionInfo = pcDescriptionInfo;
    }

    public String getAppDescriptionInfo() {
        return appDescriptionInfo;
    }

    public void setAppDescriptionInfo(String appDescriptionInfo) {
        this.appDescriptionInfo = appDescriptionInfo;
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