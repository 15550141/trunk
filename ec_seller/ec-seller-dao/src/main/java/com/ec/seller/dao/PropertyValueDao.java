package com.ec.seller.dao;

import java.util.List;

import com.ec.seller.domain.PropertyValue;
import com.ec.seller.domain.query.PropertyValueQuery;


public interface PropertyValueDao {
	/**
	 * 添加类目属性值信息
	 * @param propertyValue
	 * @return
	 */
	public Integer insert(PropertyValue propertyValue);

	/**
	 * 依据类目属性值ID修改类目属性值信息
	 * @param propertyValue
	 */
	public void modify(PropertyValue propertyValue);

	/**
	 * 依据类目属性值ID查询类目属性值信息
	 * @param userId
	 * @return
	 */
	public PropertyValue selectByPropertyValueId(int propertyValueId);
	
	/**
	 * 根据相应的条件查询满足条件的类目属性值信息的总数
	 * @param propertyValueQuery
	 * @return
	 */
	public int countByCondition(PropertyValueQuery propertyValueQuery);
	
	/**
	 * 根据相应的条件查询类目属性值信息
	 * @param propertyValueQuery
	 * @return
	 */
	public List<PropertyValue> selectByCondition(PropertyValueQuery propertyValueQuery);
	
	/**
	 * 根据相应的条件查询类目属性值信息---分页查询
	 * @param propertyValueQuery
	 * @return
	 */
	public List<PropertyValue> selectByConditionForPage(PropertyValueQuery propertyValueQuery);
	
	/**
	 * 根据属性值ID删除对应的记录
	 * @param propertyValueId
	 */
	public void deleteById(Integer propertyValueId);
}