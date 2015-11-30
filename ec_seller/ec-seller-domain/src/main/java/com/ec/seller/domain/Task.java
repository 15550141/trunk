package com.ec.seller.domain;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	/**
	 * 任务内容json数据
	 */
	private String content;
	/**
	 * 任务类型，1是下单成功微信推送消息，2是支付完成消息，
	 */
	private Integer type;
	/**
	 * 执行状态，0是初始状态
	 */
	private Integer status;
	/**
	 * 是否有效，1为有效
	 */
	private Integer yn;
	/**
	 * 创建时间
	 */
	private Date created;
	/**
	 * 修改时间
	 */
	private Date modified;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	
	
}
