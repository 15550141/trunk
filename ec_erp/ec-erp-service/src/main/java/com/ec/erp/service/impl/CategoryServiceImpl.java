package com.ec.erp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.erp.domain.Category;
import com.ec.erp.domain.query.CategoryQuery;
import com.ec.erp.manager.CategoryManager;
import com.ec.erp.service.CategoryService;


@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryManager categoryManager;

	@Override
	public List<Category> selectByCondition(CategoryQuery categoryQuery) {
		// TODO Auto-generated method stub		
		return categoryManager.selectByCondition(categoryQuery);
	}

	@Override
	public List<Category> selectByLikeCondition(CategoryQuery categoryQuery) {
		// TODO Auto-generated method stub
		return categoryManager.selectByLikeCondition(categoryQuery);
	}
	
	@Override
	public Integer insert(Category category) {		
		return categoryManager.insert(category);
	}

	@Override
	public Category selectByCategoryId(int categoryId) {
		// TODO Auto-generated method stub
		return categoryManager.selectByCategoryId(categoryId);
	}

	@Override
	public void deleteCategory1(Integer categoryId) {
		categoryManager.deleteCategory1(categoryId);
		
	}

	@Override
	public void deleteCategory2ByPar(Integer parentCategoryId) {
		categoryManager.deleteCategory2ByPar(parentCategoryId);
		
	}

	@Override
	public void modify(Category category) {
		categoryManager.modify(category);
		
	}
	


}
