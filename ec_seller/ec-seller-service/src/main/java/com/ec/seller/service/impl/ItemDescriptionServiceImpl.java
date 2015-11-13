package com.ec.seller.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ec.seller.domain.ItemDescription;
import com.ec.seller.manager.ItemDescriptionManager;
import com.ec.seller.service.ItemDescriptionService;



@Service(value = "itemDescriptionService")
public class ItemDescriptionServiceImpl implements ItemDescriptionService{
	@Autowired
	private ItemDescriptionManager itemDescriptionManager;

	@Override
	public Integer insert(ItemDescription itemDescription) {
		
		return itemDescriptionManager.insert(itemDescription);
	}

	@Override
	public ItemDescription selectByItemId(int itemId) {
		
		return itemDescriptionManager.selectByItemId(itemId);
	}

	@Override
	public void modify(ItemDescription itemDescription) {
		itemDescriptionManager.modify(itemDescription);
		
	}




}
