package com.ec.seller.domain.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ec.seller.domain.common.BaseSearchForMysqlVo;

public class ItemQuery extends BaseSearchForMysqlVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /** 商品ID */
    private Integer itemId;

    /** 商品名称 */
    private String itemName;

    /** 商品类型 */
    private Integer itemType;

    /** 商品一级分类ID */
    private Integer categoryId1;
    
    /** 商品一级分类名称 */
    private String categoryId1Name;

    /** 商品二级分类ID */
    private Integer categoryId2;
    
    /** 商品一级分类名称 */
    private String categoryId2Name;
    
    /** 商品三级分类ID */
    private Integer categoryId3;
    
    /** 商品三级分类名称 */
    private String categoryId3Name;

    /** 商品四级分类ID */
    private Integer categoryId4;
    
    /** 商品四级分类名称 */
    private String categoryId4Name;

    /** 商家ID */
    private Integer venderUserId;

    /** 商品状态 */
    private Integer itemStatus;
    
    /** 自动上架时间 */
    private Date autoOnShelfTime;

    /** 自动下架时间 */
    private Date autoOffShelfTime;

    /** 上架时间 */
    private Date onShelfTime;

    /** 下架时间 */
    private Date offShelfTime;

    /** 商品主图 */
    private String itemImage;

    /** 是否支持定金支付 */
    private Integer ifDeposit;

    /** 定金支付比例 */
    private Integer depositRate;

    /** 产地省 */
    private Integer originProvince;
    
    /** 产地市 */
    private Integer originCity;
    
    /** 产地县 */
    private Integer originCounty;
    
    /** 产地详细地址*/
    private String originAddress;
    
    /** 供货省 */
    private Integer supplyProvince;
    
    /** 供货市 */
    private Integer supplyCity;
    
    /** 供货县 */
    private Integer supplyCounty;

    /** 重量 */
    private String weight;

    /** 重量正负差值 */
    private String differWeight;

    /** 包装类型 */
    private String packingType;
    
    /** 包装类型 */
    private String packingTypeCustom;

    /** 长 */
    private String length;

    /** 宽 */
    private String wide;

    /** 高 */
    private String high;
    
    /** 销售属性拼装字段 */
    private String salesPropertySet;
    
    /** 销售单位 */
    private Integer unit;
    
    /** 是否有检测报告*/
    private Integer ifHaveZjbg;
    
    /** 是否有产品认证*/
    private Integer ifHaveCprz;
    
    /** 上市开始时间*/
    private Date marketStartTime;
    
    /** 上市结束时间*/
    private Date marketEndTime;

    /** 是否有效 */
    private Integer yn;

    /** 创建时间 */
    private Date created;

    /** 修改时间 */
    private Date modified;
    
    /** 天宝价（最低价） */
    private BigDecimal tbPrice;
    
    /** 库存（商品Id对应的SKU的库存总和） */
    private Integer stock;
    
    /** 30天销量 */
    private Integer purchaseNumber;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

	public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Integer getCategoryId1() {
        return categoryId1;
    }

    public void setCategoryId1(Integer categoryId1) {
        this.categoryId1 = categoryId1;
    }

    public Integer getCategoryId2() {
        return categoryId2;
    }

    public void setCategoryId2(Integer categoryId2) {
        this.categoryId2 = categoryId2;
    }

    public Integer getVenderUserId() {
        return venderUserId;
    }

    public void setVenderUserId(Integer venderUserId) {
        this.venderUserId = venderUserId;
    }

    public Integer getItemStatus() {
        return itemStatus;
    }

    public Date getAutoOnShelfTime() {
		return autoOnShelfTime;
	}

	public void setAutoOnShelfTime(Date autoOnShelfTime) {
		this.autoOnShelfTime = autoOnShelfTime;
	}

	public Date getAutoOffShelfTime() {
		return autoOffShelfTime;
	}

	public void setAutoOffShelfTime(Date autoOffShelfTime) {
		this.autoOffShelfTime = autoOffShelfTime;
	}

	public void setItemStatus(Integer itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Date getOnShelfTime() {
        return onShelfTime;
    }

    public void setOnShelfTime(Date onShelfTime) {
        this.onShelfTime = onShelfTime;
    }

    public Date getOffShelfTime() {
        return offShelfTime;
    }

    public void setOffShelfTime(Date offShelfTime) {
        this.offShelfTime = offShelfTime;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public Integer getIfDeposit() {
        return ifDeposit;
    }

    public void setIfDeposit(Integer ifDeposit) {
        this.ifDeposit = ifDeposit;
    }

    public Integer getDepositRate() {
        return depositRate;
    }

    public void setDepositRate(Integer depositRate) {
        this.depositRate = depositRate;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDifferWeight() {
        return differWeight;
    }

    public void setDifferWeight(String differWeight) {
        this.differWeight = differWeight;
    }

    public String getPackingType() {
        return packingType;
    }

    public void setPackingType(String packingType) {
        this.packingType = packingType;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWide() {
        return wide;
    }

    public void setWide(String wide) {
        this.wide = wide;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getSalesPropertySet() {
		return salesPropertySet;
	}

	public void setSalesPropertySet(String salesPropertySet) {
		this.salesPropertySet = salesPropertySet;
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

	public Integer getOriginProvince() {
		return originProvince;
	}

	public void setOriginProvince(Integer originProvince) {
		this.originProvince = originProvince;
	}

	public Integer getOriginCity() {
		return originCity;
	}

	public void setOriginCity(Integer originCity) {
		this.originCity = originCity;
	}

	public Integer getOriginCounty() {
		return originCounty;
	}

	public void setOriginCounty(Integer originCounty) {
		this.originCounty = originCounty;
	}

	public String getOriginAddress() {
		return originAddress;
	}

	public void setOriginAddress(String originAddress) {
		this.originAddress = originAddress;
	}

	public Integer getSupplyProvince() {
		return supplyProvince;
	}

	public void setSupplyProvince(Integer supplyProvince) {
		this.supplyProvince = supplyProvince;
	}

	public Integer getSupplyCity() {
		return supplyCity;
	}

	public void setSupplyCity(Integer supplyCity) {
		this.supplyCity = supplyCity;
	}

	public Integer getSupplyCounty() {
		return supplyCounty;
	}

	public void setSupplyCounty(Integer supplyCounty) {
		this.supplyCounty = supplyCounty;
	}

	public String getPackingTypeCustom() {
		return packingTypeCustom;
	}

	public void setPackingTypeCustom(String packingTypeCustom) {
		this.packingTypeCustom = packingTypeCustom;
	}

	public BigDecimal getTbPrice() {
		return tbPrice;
	}

	public void setTbPrice(BigDecimal tbPrice) {
		this.tbPrice = tbPrice;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(Integer purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

	public String getCategoryId1Name() {
		return categoryId1Name;
	}

	public void setCategoryId1Name(String categoryId1Name) {
		this.categoryId1Name = categoryId1Name;
	}

	public String getCategoryId2Name() {
		return categoryId2Name;
	}
	
	public void setCategoryId2Name(String categoryId2Name) {
		this.categoryId2Name = categoryId2Name;
	}

	public Integer getCategoryId3() {
		return categoryId3;
	}

	public void setCategoryId3(Integer categoryId3) {
		this.categoryId3 = categoryId3;
	}

	public String getCategoryId3Name() {
		return categoryId3Name;
	}

	public void setCategoryId3Name(String categoryId3Name) {
		this.categoryId3Name = categoryId3Name;
	}

	public Integer getCategoryId4() {
		return categoryId4;
	}

	public void setCategoryId4(Integer categoryId4) {
		this.categoryId4 = categoryId4;
	}

	public String getCategoryId4Name() {
		return categoryId4Name;
	}

	public void setCategoryId4Name(String categoryId4Name) {
		this.categoryId4Name = categoryId4Name;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public Integer getIfHaveZjbg() {
		return ifHaveZjbg;
	}

	public void setIfHaveZjbg(Integer ifHaveZjbg) {
		this.ifHaveZjbg = ifHaveZjbg;
	}

	public Integer getIfHaveCprz() {
		return ifHaveCprz;
	}

	public void setIfHaveCprz(Integer ifHaveCprz) {
		this.ifHaveCprz = ifHaveCprz;
	}

	public Date getMarketStartTime() {
		return marketStartTime;
	}

	public void setMarketStartTime(Date marketStartTime) {
		this.marketStartTime = marketStartTime;
	}

	public Date getMarketEndTime() {
		return marketEndTime;
	}

	public void setMarketEndTime(Date marketEndTime) {
		this.marketEndTime = marketEndTime;
	}	
    
}