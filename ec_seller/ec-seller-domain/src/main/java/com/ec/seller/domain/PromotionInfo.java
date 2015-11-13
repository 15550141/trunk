package com.ec.seller.domain;


import java.io.Serializable;
import java.util.Date;

public class PromotionInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /** 促销ID */
    private Integer promotionId;

    /** 促销名称 */
    private String promotionName;

    /** 商家ID */
    private Integer venderUserId;
    
	/** 商品ID */
    private Integer itemId;

    /** 促销类型 */
    private Integer promotionType;

    /** 促销起始时间 */
    private Date startTime;

    /** 促销截至时间 */
    private Date endTime;

    /** 最小购买数量 */
    private Integer purchaseNumberMin=1;

    /** 最大购买数量 */
    private Integer purchaseNumberMax;

    /** 促销库存数量 */
    private Integer promotionStock;

    /** 促销规则 */
    private String promotionRule;

    /** 促销状态 */
    private Integer promotionStatus;

    /** 有效性 */
    private Integer yn;

    /** 创建时间 */
    private Date created;

    /** 修改时间 */
    private Date modified;
    
    /** 发起方省 */
    private Integer specialProvince;
    
    /** 发起方市 */
    private Integer specialCity;
    
    /** 发起方县 */
    private Integer specialCounty;
    
    /** 产地详细地址*/
    private String specialAddress;
    
    /** 折扣区标示(0-未标示  1-有标示) **/
    private Integer discountFlag;
    
    /** 折扣区标示时间 **/
    private Date discountTime;
    
    /** 产地特供标示(0-未标示  1-已标示) **/
    private Integer specialFlag;
    
    /** 产地特供标示(0-未标示  1-已标示) **/
    private Date specialTime;
    
	/** 分页起点 **/
	private int startRow;
    
	/** 显示数量 **/
	private int pageSize = 10;
	
	/** 当前页数 **/
	private int pageIndex = 1;

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public Integer getVenderUserId() {
        return venderUserId;
    }

    public void setVenderUserId(Integer venderUserId) {
        this.venderUserId = venderUserId;
    }

    public Integer getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(Integer promotionType) {
        this.promotionType = promotionType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPurchaseNumberMin() {
        return purchaseNumberMin;
    }

    public void setPurchaseNumberMin(Integer purchaseNumberMin) {
        this.purchaseNumberMin = purchaseNumberMin;
    }

    public Integer getPurchaseNumberMax() {
        return purchaseNumberMax;
    }

    public void setPurchaseNumberMax(Integer purchaseNumberMax) {
        this.purchaseNumberMax = purchaseNumberMax;
    }

    public Integer getPromotionStock() {
        return promotionStock;
    }

    public void setPromotionStock(Integer promotionStock) {
        this.promotionStock = promotionStock;
    }

    public String getPromotionRule() {
        return promotionRule;
    }

    public void setPromotionRule(String promotionRule) {
        this.promotionRule = promotionRule;
    }

    public Integer getPromotionStatus() {
        return promotionStatus;
    }

    public void setPromotionStatus(Integer promotionStatus) {
        this.promotionStatus = promotionStatus;
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
    

	public Integer getSpecialProvince() {
		return specialProvince;
	}

	public void setSpecialProvince(Integer specialProvince) {
		this.specialProvince = specialProvince;
	}

	public Integer getSpecialCity() {
		return specialCity;
	}

	public void setSpecialCity(Integer specialCity) {
		this.specialCity = specialCity;
	}

	public Integer getSpecialCounty() {
		return specialCounty;
	}

	public void setSpecialCounty(Integer specialCounty) {
		this.specialCounty = specialCounty;
	}

	public String getSpecialAddress() {
		return specialAddress;
	}

	public void setSpecialAddress(String specialAddress) {
		this.specialAddress = specialAddress;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getDiscountFlag() {
		return discountFlag;
	}

	public void setDiscountFlag(Integer discountFlag) {
		this.discountFlag = discountFlag;
	}

	public Date getDiscountTime() {
		return discountTime;
	}

	public void setDiscountTime(Date discountTime) {
		this.discountTime = discountTime;
	}

	public Integer getSpecialFlag() {
		return specialFlag;
	}

	public void setSpecialFlag(Integer specialFlag) {
		this.specialFlag = specialFlag;
	}

	public Date getSpecialTime() {
		return specialTime;
	}

	public void setSpecialTime(Date specialTime) {
		this.specialTime = specialTime;
	}	
	
    
}