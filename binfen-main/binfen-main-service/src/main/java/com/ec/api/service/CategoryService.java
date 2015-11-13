package com.ec.api.service;

import com.ec.api.domain.PropertyValue;
import com.ec.api.service.result.Result;

public interface CategoryService {
	/**
	 * 获取所有一级分类
	 * @return
	 */
	public Result getAllParentCategory();
	
	/**
	 * 根据父类别，获取分类信息
	 * @return
	 */
	public Result getCategoryByParentId(Integer parentId);
	
	/**
	 * 查询二级分类下属性销售属性信息
	 * @param categoryId
	 * @param propertyType
	 * @return
	 */
	public Result getPropertiesByCategoryId(Integer categoryId, Integer propertyType);
	
	/**
	 * 根据属性ID和商家ID获取属性值列表
	 * @param venderUserId
	 * @param propertyId
	 * @return
	 */
	public Result getPropertyValuesByVenderUserIdAndPropertyId(Integer venderUserId, Integer propertyId);
	
	/**
	 * 添加自定义属性值信息
	 * @param propertyValue
	 * @return
	 */
	public Result addPropertyValue(PropertyValue propertyValue);
	
	/**
	 * 修改自定义属性值信息
	 * @param propertyValue
	 * @return
	 */
	public Result updatePropertyValues(PropertyValue propertyValue);
}
