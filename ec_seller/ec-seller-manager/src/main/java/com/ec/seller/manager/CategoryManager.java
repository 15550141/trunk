package com.ec.seller.manager;

import java.util.List;

import com.ec.seller.domain.Category;
import com.ec.seller.domain.query.CategoryQuery;

public interface CategoryManager {
	public List<Category> selectByCondition(CategoryQuery categoryQuery);
	
	public Category selectByCategoryId(int categoryId);
	

}
