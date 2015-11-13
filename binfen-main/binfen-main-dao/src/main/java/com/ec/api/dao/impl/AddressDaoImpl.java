package com.ec.api.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.api.dao.AddressDao;
import com.ec.api.domain.Address;
import com.ec.api.domain.query.AddressQuery;

public class AddressDaoImpl extends SqlMapClientTemplate implements AddressDao {

	@Override
	public Integer insert(Address address) {
		return (Integer) insert("Address.insert", address);
	}

	@Override
	public void modify(Address address) {
		update("Address.updateByPrimaryKey", address);
	}

	@Override
	public Address selectByAddressId(Integer addressId) {
		return (Address) queryForObject("Address.selectByPrimaryKey", addressId);
	}

	@Override
	public Integer countByCondition(AddressQuery addressQuery) {
		return (Integer) queryForObject("Address.countByCondition", addressQuery);
	}

	@Override
	public List<Address> selectByCondition(AddressQuery addressQuery) {
		return (List<Address>)queryForList("Address.selectByCondition",addressQuery);
	}

}
