package com.ec.seller.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.ec.seller.dao.CategoryDao;
import com.ec.seller.domain.Category;
import com.ec.seller.domain.query.CategoryQuery;
import com.ec.seller.manager.CategoryManager;


@Repository
public class CategoryManagerImpl implements CategoryManager{
	
	@Autowired
	private CategoryDao categoryDao;
	private final static Log LOG = LogFactory.getLog(CategoryManagerImpl.class);
	@Override
	public List<Category> selectByCondition(CategoryQuery categoryQuery) {
		
		return categoryDao.selectByCondition(categoryQuery);
	}
	@Override
	public Category selectByCategoryId(int categoryId) {
		return categoryDao.selectByCategoryId(categoryId);
	}
	

}
