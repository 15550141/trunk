package com.ec.seller.domain.common;

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
	/** 排序规则  */
	public String order = "asc";
	/** 第几页 */
	private Integer page;
	/** 每页条数  */
	private Integer rows;
	/** 根据哪个字段进行排序 */
	private String sort;
	
	
	public Integer getPageNo() {
		return pageNo;
	}


	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public void setPage(Integer page){
		this.page = page;
		this.pageNo = page;
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


	public String getOrder() {
		return order;
	}


	public void setOrder(String order) {
		this.order = order;
	}


	public Integer getRows() {
		return rows;
	}


	public void setRows(Integer rows) {
		this.rows = rows;
		this.pageSize = rows;
	}


	public String getSort() {
		return sort;
	}


	public void setSort(String sort) {
		this.sort = sort;
	}


	public Integer getPage() {
		return page;
	}
	


//	public Integer getStart(){
//		int start =  (pageNo - 1) * pageSize;
//		return start < 0 ? 0 : start;
//	}
	
	
}
