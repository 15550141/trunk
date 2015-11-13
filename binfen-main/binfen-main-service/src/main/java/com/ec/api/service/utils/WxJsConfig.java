package com.ec.api.service.utils;

import java.io.Serializable;

import com.ec.api.common.utils.BFUtils;

public class WxJsConfig implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private boolean debug;
	private long timestamp;
	private String nonceStr = "yujianming";
	private String signature;
	private String url;
	public boolean isDebug() {
		return debug;
	}
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
