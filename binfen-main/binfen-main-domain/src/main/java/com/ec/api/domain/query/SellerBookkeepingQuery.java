package com.ec.api.domain.query;


import java.io.Serializable;
import java.util.Date;

/**
 * 商家记账本
 */
public class SellerBookkeepingQuery extends BaseSearchForMysqlVo implements Serializable {
	
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

	/** 账目创建起始时间 */
    private Date startTime;

	/** 账目创建截止时间 */
    private Date endTime;
    
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