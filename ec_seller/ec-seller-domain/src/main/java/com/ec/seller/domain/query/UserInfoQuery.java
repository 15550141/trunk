package com.ec.seller.domain.query;

import java.io.Serializable;

import com.ec.seller.domain.common.BaseSearchForMysqlVo;

public class UserInfoQuery extends BaseSearchForMysqlVo implements Serializable{
	
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

}