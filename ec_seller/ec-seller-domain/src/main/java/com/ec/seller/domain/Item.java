package com.ec.seller.domain;


import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable{
	
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

    /** 商品二级分类ID */
    private Integer categoryId2;
    
    /** 商品三级分类ID */
    private Integer categoryId3;

    /** 商品四级分类ID */
    private Integer categoryId4;

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

//    /** 起买量 */
//    private Integer leastBuy;

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
    private Integer packingType;

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

    public Integer getCategoryId3() {
		return categoryId3;
	}

	public void setCategoryId3(Integer categoryId3) {
		this.categoryId3 = categoryId3;
	}

	public Integer getCategoryId4() {
		return categoryId4;
	}

	public void setCategoryId4(Integer categoryId4) {
		this.categoryId4 = categoryId4;
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

//	public Integer getLeastBuy() {
//        return leastBuy;
//    }
//
//    public void setLeastBuy(Integer leastBuy) {
//        this.leastBuy = leastBuy;
//    }

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

    public Integer getPackingType() {
		return packingType;
	}

	public void setPackingType(Integer packingType) {
		this.packingType = packingType;
	}

	public String getPackingTypeCustom() {
		return packingTypeCustom;
	}

	public void setPackingTypeCustom(String packingTypeCustom) {
		this.packingTypeCustom = packingTypeCustom;
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