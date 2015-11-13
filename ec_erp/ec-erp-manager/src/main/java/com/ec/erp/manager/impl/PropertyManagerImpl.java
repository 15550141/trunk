package com.ec.erp.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import com.ec.erp.dao.PropertyDao;
import com.ec.erp.domain.Property;
import com.ec.erp.domain.query.PropertyQuery;
import com.ec.erp.manager.PropertyManager;


@Repository
public class PropertyManagerImpl implements PropertyManager{
	
	@Autowired
	private PropertyDao propertyDao;
	private final static Log LOG = LogFactory.getLog(PropertyManagerImpl.class);
	@Override
	public List<Property> selectByCondition(PropertyQuery propertyQuery) {
		return propertyDao.selectByCondition(propertyQuery);
	}
	@Override
	public Integer insert(Property property) {
		
		return propertyDao.insert(property);
	}

	

}
