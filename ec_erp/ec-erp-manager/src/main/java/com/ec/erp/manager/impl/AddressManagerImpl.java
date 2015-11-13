package com.ec.erp.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ec.erp.dao.AddressDao;
import com.ec.erp.domain.Address;
import com.ec.erp.domain.query.AddressQuery;
import com.ec.erp.manager.AddressManager;


@Repository
public class AddressManagerImpl implements AddressManager{
	
	@Autowired
	private AddressDao addressDao;
	private final static Log LOG = LogFactory.getLog(AddressManagerImpl.class);
	@Override
	public List<Address> selectByCondition(AddressQuery addressQuery) {
		
		return addressDao.selectByCondition(addressQuery);
	}
	

}
