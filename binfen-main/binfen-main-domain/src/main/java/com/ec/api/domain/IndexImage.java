package com.ec.api.domain;

import java.io.Serializable;
import java.util.Date;

public class IndexImage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String imageUrl;
	private String imageName;
	private String imageWebUrl;
	private Integer sortNumber;
	private Date created;
	private Date modified;
	private Integer yn;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImageWebUrl() {
		return imageWebUrl;
	}
	public void setImageWebUrl(String imageWebUrl) {
		this.imageWebUrl = imageWebUrl;
	}
	public Integer getSortNumber() {
		return sortNumber;
	}
	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
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
	public Integer getYn() {
		return yn;
	}
	public void setYn(Integer yn) {
		this.yn = yn;
	}
	
}
