package com.ec.seller.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import com.ec.seller.dao.PropertyDao;
import com.ec.seller.domain.Property;
import com.ec.seller.domain.query.PropertyQuery;
import com.ec.seller.manager.PropertyManager;


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
	public Property selectByPropertyId(int propertyId) {
		
		return propertyDao.selectByPropertyId(propertyId);
	}

	

}
