package com.ec.erp.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.erp.dao.CategoryDao;
import com.ec.erp.domain.Category;
import com.ec.erp.domain.query.CategoryQuery;

public class CategoryDaoImpl extends SqlMapClientTemplate implements CategoryDao {

	@Override
	public Integer insert(Category category) {
		return (Integer)insert("Category.insert",category);
	}

	@Override
	public void modify(Category category) {
		update("Category.updateByPrimaryKey",category);
	}

	@Override
	public Category selectByCategoryId(int categoryId) {
		return (Category)queryForObject("Category.selectByPrimaryKey",categoryId);
	}

	@Override
	public int countByCondition(CategoryQuery categoryQuery) {
		return (Integer)queryForObject("Category.countByCondition", categoryQuery);
	}

	@Override
	public List<Category> selectByCondition(CategoryQuery categoryQuery) {
		return (List<Category>)queryForList("Category.selectByCondition", categoryQuery);
	}

	@Override
	public List<Category> selectByConditionForPage(CategoryQuery categoryQuery) {
		return (List<Category>)queryForList("Category.selectByConditionForPage", categoryQuery);
	}

	@Override
	public List<Category> selectByLikeCondition(CategoryQuery categoryQuery) {
		
		return (List<Category>)queryForList("Category.selectByLikeCondition", categoryQuery);
	}

	@Override
	public void deleteCategory1(Integer categoryId) {
		delete("Category.deleteCategory1",categoryId);
		
	}

	@Override
	public void deleteCategory2ByPar(Integer parentCategoryId) {
		delete("Category.deleteCategory2ByPar",parentCategoryId);
		
	}

}
