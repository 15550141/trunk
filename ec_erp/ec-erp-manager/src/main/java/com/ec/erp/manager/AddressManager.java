package com.ec.erp.manager;

import java.util.List;

import com.ec.erp.domain.Address;
import com.ec.erp.domain.query.AddressQuery;

public interface AddressManager {
	public List<Address> selectByCondition(AddressQuery addressQuery);

}
