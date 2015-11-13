package com.ec.seller.service.impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ec.seller.domain.Category;
import com.ec.seller.domain.query.CategoryQuery;
import com.ec.seller.manager.CategoryManager;
import com.ec.seller.service.CategoryService;


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
	public Category selectByCategoryId(int categoryId) {
		// TODO Auto-generated method stub
		return categoryManager.selectByCategoryId(categoryId);
	}


}
