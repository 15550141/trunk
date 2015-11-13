package com.ec.erp.service;

import java.util.List;
import com.ec.erp.domain.PropertyValue;
import com.ec.erp.domain.query.PropertyValueQuery;

public interface PropertyValueService {
	public List<PropertyValue> selectByCondition(PropertyValueQuery propertyValueQuery);
	
	public Integer insert(PropertyValue propertyValue);
	
	public void deleteById(Integer propertyValueId);

}
