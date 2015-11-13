package com.ec.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ec.erp.domain.PropertyValue;
import com.ec.erp.domain.query.PropertyValueQuery;
import com.ec.erp.manager.PropertyValueManager;
import com.ec.erp.service.PropertyValueService;


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





}
