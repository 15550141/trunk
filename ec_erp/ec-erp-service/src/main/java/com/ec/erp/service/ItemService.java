package com.ec.erp.service;

import java.util.List;
import java.util.Map;

import com.ec.erp.domain.Address;
import com.ec.erp.domain.Item;
import com.ec.erp.domain.query.AddressQuery;
import com.ec.erp.domain.query.ItemPropertyQuery;
import com.ec.erp.domain.query.ItemQuery;

public interface ItemService {
	public List<Item> selectByConditionForPage(ItemQuery itemQuery);

	public Map<String, Object> queryItemList(ItemQuery itemQuery);
	
	public Integer insert(Item item);
	
	public void modify(Item item);
	
	public Item selectByItemId(int itemId);

}
