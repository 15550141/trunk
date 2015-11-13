package com.ec.seller.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ec.seller.dao.AddressDao;
import com.ec.seller.dao.ItemDao;
import com.ec.seller.domain.Address;
import com.ec.seller.domain.Item;
import com.ec.seller.domain.query.AddressQuery;
import com.ec.seller.domain.query.ItemQuery;
import com.ec.seller.manager.AddressManager;
import com.ec.seller.manager.ItemManager;


@Repository
public class ItemManagerImpl implements ItemManager{
	
	@Autowired
	private ItemDao itemDao;
	private final static Log LOG = LogFactory.getLog(ItemManagerImpl.class);
	@Override
	public List<Item> selectByConditionForPage(ItemQuery itemQuery) {
		
		return itemDao.selectByConditionForPage(itemQuery);
	}
	@Override
	public int countByConditionForPage(ItemQuery itemQuery) {
		
		return itemDao.countByConditionForPage(itemQuery);
	}
	@Override
	public List<ItemQuery> selectByConditionWithPage(ItemQuery itemQuery) {
		// TODO Auto-generated method stub
		if(itemQuery.getCategoryId1() == null || itemQuery.getCategoryId1() == 1)
			itemQuery.setCategoryId1(null);
		if(itemQuery.getCategoryId2() == null || itemQuery.getCategoryId2() == 1)
			itemQuery.setCategoryId2(null);
		
		return itemDao.selectByConditionWithPage(itemQuery);
	}
	@Override
	public int countByCondition(ItemQuery itemQuery) {
		return itemDao.countByCondition(itemQuery);
	}
	@Override
	public Integer insert(Item item) {
		return itemDao.insert(item);
	}
	@Override
	public void modify(Item item) {
		itemDao.modify(item);
		
	}
	@Override
	public Item selectByItemId(int itemId) {
		
		return itemDao.selectByItemId(itemId);
	}


}
