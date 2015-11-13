package com.ec.erp.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ec.erp.dao.IndexImageDao;
import com.ec.erp.domain.IndexImage;
import com.ec.erp.domain.query.IndexImageQuery;
import com.ec.erp.manager.IndexImageManager;


@Repository
public class IndexImageManagerImpl implements IndexImageManager{
	
	@Autowired
	private IndexImageDao indexImageDao;
	private final static Log LOG = LogFactory.getLog(IndexImageManagerImpl.class);
	@Override
	public Integer insert(IndexImage indexImage) {

		return indexImageDao.insert(indexImage);
	}
	@Override
	public List<IndexImage> selectByCondition(IndexImageQuery indexImageQuery) {
		// TODO Auto-generated method stub
		return indexImageDao.selectByCondition(indexImageQuery);
	}
	@Override
	public void modify(IndexImage indexImage) {
		indexImageDao.modify(indexImage);
		
	}

	

}
