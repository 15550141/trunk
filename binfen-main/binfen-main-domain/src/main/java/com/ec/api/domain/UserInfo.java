package com.ec.api.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户账户信息
 *
 */
public class UserInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Integer userId;

    /** 手机号 */
    private String mobile;

    /** 登陆密码 */
    private String password;

    /** 用户类型 */
    private Integer userType;

    /** 有效性 */
    private Integer yn;

    /** 注册时间 */
    private Date registerTime;

    /** 注册IP */
    private String registerIp;

    /** 最后登陆时间 */
    private Date lastLoginTime;

    /** 最后登陆IP */
    private String lastLoginIp;

    /** 创建时间 */
    private Date created;

    /** 修改时间 */
    private Date modified;
    
    
    /** 用户昵称 */
    private String nickname;
    /** 性别 0未知，1男 2女 */
    private Integer sex;
    /** 用户级别 */
    private Integer user_level;
    /** 省*/
    private Integer province;
    /** 市 */
    private Integer city;
    /** 县 */
    private Integer country;
    /** 用户邮箱 */
    private String email;
    /** 用户头像 */ 
    private String headimgurl;
    /** 用户真实姓名 */
    private String truename;
    /** 用户邮编 */
    private String postcode;
    /** 用户地址 */
    private String address;
    /** 注册来源 */
    private Integer regist_source;
    
    private BusinessUserExt businessUserExt;


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
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

	public BusinessUserExt getBusinessUserExt() {
		return businessUserExt;
	}

	public void setBusinessUserExt(BusinessUserExt businessUserExt) {
		this.businessUserExt = businessUserExt;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getUser_level() {
		return user_level;
	}

	public void setUser_level(Integer user_level) {
		this.user_level = user_level;
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

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getRegist_source() {
		return regist_source;
	}

	public void setRegist_source(Integer regist_source) {
		this.regist_source = regist_source;
	}
    
}