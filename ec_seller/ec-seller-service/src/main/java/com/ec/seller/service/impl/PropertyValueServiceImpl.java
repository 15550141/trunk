package com.ec.seller.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ec.seller.domain.PropertyValue;
import com.ec.seller.domain.query.PropertyValueQuery;
import com.ec.seller.manager.PropertyValueManager;
import com.ec.seller.service.PropertyValueService;


@Service(value = "propertyValueService")
public class PropertyValueServiceImpl implements PropertyValueService{
	@Autowired
	private PropertyValueManager propertyValueManager;

	@Override
	public List<PropertyValue> selectByCondition(
			PropertyValueQuery propertyValueQuery) {
		
		return propertyValueManager.selectByCondition(propertyValueQuery);
	}

	@Override
	public Integer insert(PropertyValue propertyValue) {
		
		return propertyValueManager.insert(propertyValue);
	}

	@Override
	public void deleteById(Integer propertyValueId) {
		propertyValueManager.deleteById(propertyValueId);
		
	}

	@Override
	public PropertyValue selectByPropertyValueId(int propertyValueId) {
		
		return propertyValueManager.selectByPropertyValueId(propertyValueId);
	}





}
