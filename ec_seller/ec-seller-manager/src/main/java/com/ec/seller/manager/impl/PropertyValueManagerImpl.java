package com.ec.seller.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ec.seller.dao.PropertyValueDao;
import com.ec.seller.domain.PropertyValue;
import com.ec.seller.domain.query.PropertyValueQuery;
import com.ec.seller.manager.PropertyValueManager;


@Repository
public class PropertyValueManagerImpl implements PropertyValueManager{
	
	@Autowired
	private PropertyValueDao propertyValueDao;
	private final static Log LOG = LogFactory.getLog(PropertyValueManagerImpl.class);
	@Override
	public List<PropertyValue> selectByCondition(
			PropertyValueQuery propertyValueQuery) {
		return propertyValueDao.selectByCondition(propertyValueQuery);
	}
	@Override
	public Integer insert(PropertyValue propertyValue) {
		return propertyValueDao.insert(propertyValue);
	}
	@Override
	public void deleteById(Integer propertyValueId) {
		propertyValueDao.deleteById(propertyValueId);
		
	}
	@Override
	public PropertyValue selectByPropertyValueId(int propertyValueId) {
		return propertyValueDao.selectByPropertyValueId(propertyValueId);
	}


	

}
