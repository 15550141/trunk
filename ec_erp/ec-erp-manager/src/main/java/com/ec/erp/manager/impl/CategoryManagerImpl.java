package com.ec.erp.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import com.ec.erp.dao.CategoryDao;
import com.ec.erp.domain.Category;
import com.ec.erp.domain.query.CategoryQuery;
import com.ec.erp.manager.CategoryManager;


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
	public List<Category> selectByLikeCondition(CategoryQuery categoryQuery) {
		// TODO Auto-generated method stub
		return categoryDao.selectByLikeCondition(categoryQuery);
	}
	
	@Override
	public Category selectByCategoryId(int categoryId) {
		return categoryDao.selectByCategoryId(categoryId);
	}
	
	@Override
	public Integer insert(Category category) {
		return categoryDao.insert(category);
	}

	@Override
	public void deleteCategory1(Integer categoryId) {
		categoryDao.deleteCategory1(categoryId);
		
	}

	@Override
	public void deleteCategory2ByPar(Integer parentCategoryId) {
		categoryDao.deleteCategory2ByPar(parentCategoryId);
		
	}

	@Override
	public void modify(Category category) {
		categoryDao.modify(category);
		
	}

	
	

}
