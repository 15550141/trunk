package com.ec.seller.service;

import java.util.List;

import com.ec.seller.domain.Category;
import com.ec.seller.domain.query.CategoryQuery;

public interface CategoryService {
	public List<Category> selectByCondition(CategoryQuery categoryQuery);
	
	/**
	 * 依据分类ID查询分类信息
	 * @param categoryId
	 * @return
	 */
	public Category selectByCategoryId(int categoryId);

}
