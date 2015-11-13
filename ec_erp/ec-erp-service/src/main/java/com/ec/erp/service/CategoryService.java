package com.ec.erp.service;

import java.util.List;

import com.ec.erp.domain.Category;
import com.ec.erp.domain.query.CategoryQuery;

public interface CategoryService {
	public List<Category> selectByCondition(CategoryQuery categoryQuery);

	public List<Category> selectByLikeCondition(CategoryQuery categoryQuery);
	
	public Integer insert(Category category);

	Category selectByCategoryId(int categoryId);

	public void deleteCategory1(Integer categoryId);

	public void deleteCategory2ByPar(Integer categoryId);
	
	public void modify(Category category);

	

}
