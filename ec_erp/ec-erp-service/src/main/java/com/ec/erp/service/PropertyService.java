package com.ec.erp.service;

import java.util.List;

import com.ec.erp.domain.Property;
import com.ec.erp.domain.query.PropertyQuery;

public interface PropertyService {
	public List<Property> selectByCondition(PropertyQuery propertyQuery);
	
	public Integer insert(Property property);

}
