package com.ec.seller.manager;

import java.util.List;
import com.ec.seller.domain.Item;
import com.ec.seller.domain.query.ItemQuery;

public interface ItemManager {
	public List<Item> selectByConditionForPage(ItemQuery itemQuery);
	
	public int countByConditionForPage(ItemQuery itemQuery);
	
	public int countByCondition(ItemQuery itemQuery);
	
	public List<ItemQuery> selectByConditionWithPage(ItemQuery itemQuery);
	
	public Integer insert(Item item);
	
	public void modify(Item item);
	
	public Item selectByItemId(int itemId);

}
