package com.ec.api.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单购物车
 *
 */
public class OrderDetail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /** 自增ID */
    private Integer id;

    /** 订单ID */
    private Integer orderId;
    
    /** 用户uid */
    private Integer uid;

    /** SKU_ID */
    private Integer skuId;
    
    /** item_id */
    private Integer itemId;
    
    /** 商品名称 */
    private String itemName;
    
    /**
     * 销售属性
     */
    private String salesProperty;
    /**
     * 销售属性中文
     */
    private String salesPropertyName;

    /** 价格 */
    private Integer price;

    /** 数量 */
    private Integer num;

    /** 商品图片 */
    private String itemImage;

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }


    public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
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

	public String getSalesProperty() {
		return salesProperty;
	}

	public void setSalesProperty(String salesProperty) {
		this.salesProperty = salesProperty;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getSalesPropertyName() {
		return salesPropertyName;
	}

	public void setSalesPropertyName(String salesPropertyName) {
		this.salesPropertyName = salesPropertyName;
	}
    
	public BigDecimal getBigDecimalPrice(){
		if(this.price == null){
			return new BigDecimal(0);
		}
		
		return new BigDecimal(this.price).divide(new BigDecimal(100));
	}
}