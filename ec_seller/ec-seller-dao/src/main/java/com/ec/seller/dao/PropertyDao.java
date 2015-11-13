package com.ec.seller.dao;

import java.util.List;

import com.ec.seller.domain.Property;
import com.ec.seller.domain.query.PropertyQuery;


public interface PropertyDao {
	/**
	 * 添加类目属性信息
	 * @param property
	 * @return
	 */
	public Integer insert(Property property);

	/**
	 * 依据类目属性ID修改类目属性信息
	 * @param property
	 */
	public void modify(Property property);

	/**
	 * 依据类目属性ID查询类目属性信息
	 * @param propertyId
	 * @return
	 */
	public Property selectByPropertyId(int propertyId);
	
	/**
	 * 根据相应的条件查询满足条件的类目属性信息的总数
	 * @param propertyQuery
	 * @return
	 */
	public int countByCondition(PropertyQuery propertyQuery);
	
	/**
	 * 根据相应的条件查询类目属性信息
	 * @param propertyQuery
	 * @return
	 */
	public List<Property> selectByCondition(PropertyQuery propertyQuery);
	
	/**
	 * 根据相应的条件查询类目属性信息---分页查询
	 * @param propertyQuery
	 * @return
	 */
	public List<Property> selectByConditionForPage(PropertyQuery propertyQuery);
}