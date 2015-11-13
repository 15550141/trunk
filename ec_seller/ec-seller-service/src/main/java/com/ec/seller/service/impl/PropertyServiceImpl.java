package com.ec.seller.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ec.seller.domain.Property;
import com.ec.seller.domain.query.PropertyQuery;
import com.ec.seller.manager.PropertyManager;
import com.ec.seller.service.PropertyService;


@Service(value = "propertyService")
public class PropertyServiceImpl implements PropertyService{
	@Autowired
	private PropertyManager propertyManager;

	@Override
	public List<Property> selectByCondition(PropertyQuery propertyQuery) {
		return propertyManager.selectByCondition(propertyQuery);
	}

	@Override
	public Property selectByPropertyId(int propertyId) {
		
		return propertyManager.selectByPropertyId(propertyId);
	}




}
