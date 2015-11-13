package com.ec.api.domain;


import java.io.Serializable;
import java.util.Date;

/**
 * 商家记账本
 */
public class SellerBookkeeping implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 自增ID */
    private Integer id;

	/** 商家ID */
    private Integer venderUserId;

	/** 交易金额 */
    private Integer tradeMoney;

	/** 支付金额 */
    private Integer paymentMoney;

	/** 联系人 */
    private String linkman;

	/** 手机号 */
    private String mobile;

	/** 备注 */
    private String remark;

	/** 创建时间 */
    private Date created;

	/** 修改时间 */
    private Date modified;
    
    /** 商品名称 **/
    private String itemName;
    
    /** 商品单价 **/
    private Integer itemPrice;
    
    /** 公司名称 **/
    private String companyName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVenderUserId() {
        return venderUserId;
    }

    public void setVenderUserId(Integer venderUserId) {
        this.venderUserId = venderUserId;
    }

    public Integer getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(Integer tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public Integer getPaymentMoney() {
		return paymentMoney;
	}

	public void setPaymentMoney(Integer paymentMoney) {
		this.paymentMoney = paymentMoney;
	}

	public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
    
}