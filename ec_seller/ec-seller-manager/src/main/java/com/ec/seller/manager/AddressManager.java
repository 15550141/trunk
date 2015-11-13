package com.ec.seller.manager;

import java.util.List;

import com.ec.seller.domain.Address;
import com.ec.seller.domain.query.AddressQuery;

public interface AddressManager {
	public List<Address> selectByCondition(AddressQuery addressQuery);

}
