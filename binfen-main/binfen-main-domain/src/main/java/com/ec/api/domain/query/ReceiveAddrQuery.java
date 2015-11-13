package com.ec.api.domain.query;


import java.io.Serializable;
import java.util.Date;

/**
 * 用户收货地址
 *
 */
public class ReceiveAddrQuery extends BaseSearchForMysqlVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	/**
	 * 用户id
	 */
	private Integer uid;
	
	/**
	 * 收货人姓名
	 */
	private String name;
	
	/**
	 * 省
	 */
	private Integer province;
	/**
	 * 市
	 */
	private Integer city;
	/**
	 * 县
	 */
	private Integer county;
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 是否是默认地址
	 */
	private Integer defaultFlag;
	/**
	 * 暂时不用，地址类型
	 */
	private Integer addrType;

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

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getCounty() {
		return county;
	}

	public void setCounty(Integer county) {
		this.county = county;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public Integer getAddrType() {
		return addrType;
	}

	public void setAddrType(Integer addrType) {
		this.addrType = addrType;
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