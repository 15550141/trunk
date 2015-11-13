package com.ec.seller.domain.query;

import java.io.Serializable;
import java.util.Date;

import com.ec.seller.domain.common.BaseSearchForMysqlVo;

public class BusinessUserExtQuery extends BaseSearchForMysqlVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    /** 用户ID=商家用户ID */
    private Integer userId;

    /** 店铺名称 */
    private String shopName;

    /** 店铺状态 */
    private Integer status;
    
    /** 开户行 */
    private String bank;
    
    /** 开户行账号 */
    private String bankAc;
    
    /** 开户行支行 */
    private String branch;
    
    /** 开户行支行联行号 */
    private String CNAPSCode;
    
    /** 开户行支行联行号 */
    private String bankProvince;
    
    /** 开户行支行联行号 */
    private String bankCity;
    
    /** 银行账号审核状态 */
    private Integer bankStatus;
    
    /** 银行账号审核备注 */
    private String bankRemark;
    
    /** 银行账号创建时间 */
    private Date bankCreated;
    
    /** 银行账号审核时间 */
    private Date bankChecked;
    
    /** 企业资质链接  */
    private String qualificationUrl;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankAc() {
		return bankAc;
	}

	public void setBankAc(String bankAc) {
		this.bankAc = bankAc;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCNAPSCode() {
		return CNAPSCode;
	}

	public void setCNAPSCode(String cNAPSCode) {
		CNAPSCode = cNAPSCode;
	}

	public String getBankProvince() {
		return bankProvince;
	}

	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}

	public String getBankCity() {
		return bankCity;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}

	public Integer getBankStatus() {
		return bankStatus;
	}

	public void setBankStatus(Integer bankStatus) {
		this.bankStatus = bankStatus;
	}

	public String getBankRemark() {
		return bankRemark;
	}

	public void setBankRemark(String bankRemark) {
		this.bankRemark = bankRemark;
	}

	public Date getBankCreated() {
		return bankCreated;
	}

	public void setBankCreated(Date bankCreated) {
		this.bankCreated = bankCreated;
	}

	public Date getBankChecked() {
		return bankChecked;
	}

	public void setBankChecked(Date bankChecked) {
		this.bankChecked = bankChecked;
	}

	public String getQualificationUrl() {
		return qualificationUrl;
	}

	public void setQualificationUrl(String qualificationUrl) {
		this.qualificationUrl = qualificationUrl;
	}
	
	

}