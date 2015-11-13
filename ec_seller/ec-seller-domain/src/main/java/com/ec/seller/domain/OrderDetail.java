package com.ec.seller.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

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
     * 销售属性名称
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
    
	public String getSalesPropertyName(){
		return this.salesPropertyName;
	}
	
	public List<String> getSalesPropertyNameList(){
		if(StringUtils.isBlank(this.salesPropertyName)){
			return null;
		}
		String[] arr = this.salesPropertyName.split("\\^");
		List<String> list = new ArrayList<String>();
		for(int i=0;i<arr.length;i++){
			list.add(arr[i]);
		}
		return list;
	}
	
	public BigDecimal getBigDecimalPrice(){
		if(this.price == null){
			return new BigDecimal(0);
		}
		
		return new BigDecimal(this.price).divide(new BigDecimal(100));
	}
}