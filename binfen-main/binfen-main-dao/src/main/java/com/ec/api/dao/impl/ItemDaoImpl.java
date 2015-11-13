package com.ec.api.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.api.dao.ItemDao;
import com.ec.api.domain.Item;
import com.ec.api.domain.query.ItemQuery;

public class ItemDaoImpl extends SqlMapClientTemplate implements ItemDao {

	@Override
	public Integer insert(Item item) {
		return (Integer)insert("Item.insert",item);
	}

	@Override
	public void modify(Item item) {
		update("Item.updateByPrimaryKey",item);
	}

	@Override
	public Item selectByItemId(Integer itemId) {
		return (Item)queryForObject("Item.selectByPrimaryKey",itemId);
	}

	@Override
	public int countByCondition(ItemQuery itemQuery) {
		return (Integer)queryForObject("Item.countByCondition",itemQuery);
	}

	@Override
	public List<Item> selectByCondition(ItemQuery itemQuery) {
		return (List<Item>)queryForList("Item.selectByCondition",itemQuery);
	}

	@Override
	public List<Item> selectByConditionForPage(ItemQuery itemQuery) {
		return (List<Item>)queryForList("Item.selectByConditionForPage",itemQuery);
	}

}
