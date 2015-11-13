package com.ec.api.service;

import java.util.List;

import com.ec.api.domain.Address;
import com.ec.api.service.result.Result;

public interface AddressService {
		
	public List<Address> getArea(Integer fid);

}
