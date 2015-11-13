package com.ec.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ec.erp.domain.Property;
import com.ec.erp.domain.query.PropertyQuery;
import com.ec.erp.manager.PropertyManager;
import com.ec.erp.service.PropertyService;


@Service(value = "propertyService")
public class PropertyServiceImpl implements PropertyService{
	@Autowired
	private PropertyManager propertyManager;

	@Override
	public List<Property> selectByCondition(PropertyQuery propertyQuery) {
		return propertyManager.selectByCondition(propertyQuery);
	}

	@Override
	public Integer insert(Property property) {
		
		return propertyManager.insert(property);
	}




}
