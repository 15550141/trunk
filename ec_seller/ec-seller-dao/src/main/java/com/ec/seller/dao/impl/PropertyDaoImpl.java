package com.ec.seller.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.seller.dao.PropertyDao;
import com.ec.seller.domain.Property;
import com.ec.seller.domain.query.PropertyQuery;

public class PropertyDaoImpl extends SqlMapClientTemplate implements PropertyDao {

	@Override
	public Integer insert(Property property) {
		return (Integer)insert("Property.insert",property);
	}

	@Override
	public void modify(Property property) {
		update("Property.updateByPrimaryKey",property);
	}

	@Override
	public Property selectByPropertyId(int propertyId) {
		return (Property)queryForObject("Property.selectByPrimaryKey",propertyId);
	}

	@Override
	public int countByCondition(PropertyQuery propertyQuery) {
		return (Integer)queryForObject("Property.countByCondition",propertyQuery);
	}

	@Override
	public List<Property> selectByCondition(PropertyQuery propertyQuery) {
		return (List<Property>)queryForList("Property.selectByCondition",propertyQuery);
	}

	@Override
	public List<Property> selectByConditionForPage(PropertyQuery propertyQuery) {
		return (List<Property>)queryForList("Property.selectByConditionForPage",propertyQuery);
	}

}
