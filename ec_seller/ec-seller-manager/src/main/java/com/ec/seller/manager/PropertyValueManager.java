package com.ec.seller.manager;

import java.util.List;
import com.ec.seller.domain.PropertyValue;
import com.ec.seller.domain.query.PropertyValueQuery;

public interface PropertyValueManager {
	
	public List<PropertyValue> selectByCondition(PropertyValueQuery propertyValueQuery);
	
	public Integer insert(PropertyValue propertyValue);
	
	public void deleteById(Integer propertyValueId);
	
	/**
	 * 依据类目属性值ID查询类目属性值信息
	 * @param userId
	 * @return
	 */
	public PropertyValue selectByPropertyValueId(int propertyValueId);

}
