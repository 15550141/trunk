package com.ec.seller.service;

import java.util.List;
import java.util.Map;

import com.ec.seller.domain.Address;
import com.ec.seller.domain.Item;
import com.ec.seller.domain.query.AddressQuery;
import com.ec.seller.domain.query.ItemPropertyQuery;
import com.ec.seller.domain.query.ItemQuery;

public interface ItemService {
	public List<Item> selectByConditionForPage(ItemQuery itemQuery);

	public Map<String, Object> queryItemList(ItemQuery itemQuery);
	
	public Integer insert(Item item);
	
	public void modify(Item item);
	
	public Item selectByItemId(int itemId);

}
