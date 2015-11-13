package com.ec.erp.dao.impl;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.erp.dao.ItemDescriptionDao;
import com.ec.erp.domain.ItemDescription;

public class ItemDescriptionDaoImpl extends SqlMapClientTemplate implements ItemDescriptionDao {

	@Override
	public Integer insert(ItemDescription itemDescription) {
		return (Integer)insert("ItemDescription.insert",itemDescription);
	}

	@Override
	public void modify(ItemDescription itemDescription) {
		update("ItemDescription.updateByItemId",itemDescription);
	}

	@Override
	public ItemDescription selectByItemId(int itemId) {
		return (ItemDescription)queryForObject("ItemDescription.selectByItemId",itemId);
	}

}
