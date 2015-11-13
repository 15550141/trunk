package com.ec.api.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.api.dao.CategoryDao;
import com.ec.api.domain.Category;
import com.ec.api.domain.query.CategoryQuery;

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
	public Category selectByCategoryId(Integer categoryId) {
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

}
