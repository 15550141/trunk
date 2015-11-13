package com.ec.seller.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.seller.dao.ItemDao;
import com.ec.seller.domain.Item;
import com.ec.seller.domain.query.ItemQuery;

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
	public Item selectByItemId(int itemId) {
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

	@Override
	public int countByConditionForPage(ItemQuery itemQuery) {
		return (Integer)queryForObject("Item.countByConditionForPage",itemQuery);
	}

	@Override
	public List<ItemQuery> selectByConditionWithPage(ItemQuery itemQuery) {
		return (List<ItemQuery>)queryForList("Item.selectByConditionWithPage",itemQuery);
	}

	@Override
	public List<ItemQuery> queryOnSheftList(Integer offsetOnSheft) {
		
		return (List<ItemQuery>)queryForList("Item.queryOnSheftList",offsetOnSheft);
	}
	@Override
	public List<ItemQuery> queryOffSheftList(Integer offsetOffSheft) {
		
		return (List<ItemQuery>)queryForList("Item.queryOffSheftList",offsetOffSheft);
	}

	@Override
	public void offSheft(ItemQuery item) {
		update("Item.offSheft",item);
		
	}

	@Override
	public void onSheft(ItemQuery item) {
		update("Item.onSheft",item);
		
	}


	
}
