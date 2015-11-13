package com.ec.erp.manager;

import java.util.List;

import com.ec.erp.domain.IndexImage;
import com.ec.erp.domain.query.IndexImageQuery;


public interface IndexImageManager {
	public Integer insert(IndexImage indexImage);
	
	public List<IndexImage> selectByCondition(IndexImageQuery indexImageQuery);
	
	public void modify(IndexImage indexImage);

}
