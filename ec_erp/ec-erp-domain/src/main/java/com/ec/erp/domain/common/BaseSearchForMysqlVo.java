package com.ec.erp.domain.common;

import java.io.Serializable;


public class BaseSearchForMysqlVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 默认每页条数 */
	public static Integer DEFAULT_PAGE_SIZE = 20;
	
	/** 页码 */
	public Integer pageNo=1;
	
	/** 每页条数 */
	public Integer pageSize=10;
	
	/** 初始位置-依据页码及每页条数计算获得 */
	public Integer start;
	
	
	public Integer getPageNo() {
		return pageNo;
	}


	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}


//	public Integer getPageSize() {
//		
//		return pageSize ==null ? DEFAULT_PAGE_SIZE : pageSize;
//	}


	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


	public void setStart(Integer start) {
		this.start = start;
	}


	public Integer getPageSize() {
		return pageSize;
	}


	public Integer getStart() {
		return start;
	}
	
	public void setPage(Integer page){
		this.pageNo = page;
	}


//	public Integer getStart(){
//		int start =  (pageNo - 1) * pageSize;
//		return start < 0 ? 0 : start;
//	}
	
	
}
