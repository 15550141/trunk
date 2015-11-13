package com.ec.seller.domain.query;


import java.io.Serializable;
import java.util.Date;

import com.ec.seller.domain.common.BaseSearchForMysqlVo;

/**
 * 短信服务
 */
public class SmsQuery extends BaseSearchForMysqlVo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** 自增ID */
    private Integer id;

	/** 手机号码 */
    private String mobile;

	/** 短信内容 */
    private String content;

	/** 发送状态 */
    private Integer status;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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