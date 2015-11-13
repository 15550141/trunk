package com.ec.api.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.api.dao.IndexImageDao;
import com.ec.api.domain.IndexImage;
import com.ec.api.domain.query.IndexImageQuery;

public class IndexImageDaoImpl extends SqlMapClientTemplate implements IndexImageDao {

	@Override
	public Integer insert(IndexImage indexImage) {
		return (Integer)insert("IndexImage.insert",indexImage);
	}

	@Override
	public void modify(IndexImage indexImage) {
		update("IndexImage.updateByPrimaryKey",indexImage);
	}

	@Override
	public int countByCondition(IndexImageQuery indexImageQuery) {
		return (Integer)queryForObject("IndexImage.countByCondition",indexImageQuery);
	}

	@Override
	public List<IndexImage> selectByCondition(IndexImageQuery indexImageQuery) {
		return (List<IndexImage>)queryForList("IndexImage.selectByCondition",indexImageQuery);
	}

	@Override
	public List<IndexImage> selectByConditionForPage(IndexImageQuery indexImageQuery) {
		return (List<IndexImage>)queryForList("IndexImage.selectByConditionForPage",indexImageQuery);
	}

}
