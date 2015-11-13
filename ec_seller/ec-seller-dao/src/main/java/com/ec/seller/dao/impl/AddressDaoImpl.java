package com.ec.seller.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.seller.dao.AddressDao;
import com.ec.seller.domain.Address;
import com.ec.seller.domain.query.AddressQuery;

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
	public Address selectByAddressId(int addressId) {
		return (Address) queryForObject("Address.selectByPrimaryKey", addressId);
	}

	@Override
	public int countByCondition(AddressQuery addressQuery) {
		return (Integer) queryForObject("Address.countByCondition", addressQuery);
	}

	@Override
	public List<Address> selectByCondition(AddressQuery addressQuery) {
		return (List<Address>)queryForList("Address.selectByCondition",addressQuery);
	}

}
