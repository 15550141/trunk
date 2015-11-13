package com.ec.seller.domain.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ec.seller.domain.common.BaseSearchForMysqlVo;

public class SkuQuery extends BaseSearchForMysqlVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /** SKU_ID */
    private Integer skuId;

    /** 商品ID */
    private Integer itemId;

    /** 尺寸属性ID */
    private Integer sizeId;

    /** 尺寸属性值ID */
    private Integer sizeValueId;
    
    /** 起买量 */
    private Integer leastBuy;
    
    /**  销售属性 */
    private String salesProperty;
    
    /**  销售属性姓名组合 */
    private String salesPropertyName;
    
    /**  条形码 */
    private String barCode;

    /** 成本价 */
    private Double costPrice;

    /** 销售价 */
    private Double tbPrice;

    /** 库存数量 */
    private Integer stock;

    /** 创建时间 */
    private Date created;

    /** 修改时间 */
    private Date modified;
    
    private Integer yn;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public Integer getSizeValueId() {
        return sizeValueId;
    }

    public void setSizeValueId(Integer sizeValueId) {
        this.sizeValueId = sizeValueId;
    }

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getTbPrice() {
		return tbPrice;
	}

	public void setTbPrice(Double tbPrice) {
		this.tbPrice = tbPrice;
	}

	public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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

	public Integer getLeastBuy() {
		return leastBuy;
	}

	public void setLeastBuy(Integer leastBuy) {
		this.leastBuy = leastBuy;
	}

	public String getSalesProperty() {
		return salesProperty;
	}

	public void setSalesProperty(String salesProperty) {
		this.salesProperty = salesProperty;
	}

	public String getSalesPropertyName() {
		return salesPropertyName;
	}

	public void setSalesPropertyName(String salesPropertyName) {
		this.salesPropertyName = salesPropertyName;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Integer getYn() {
		return yn;
	}

	public void setYn(Integer yn) {
		this.yn = yn;
	}
	
    
}