package com.ec.seller.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ec.seller.dao.ItemImageDao;
import com.ec.seller.domain.ItemImage;
import com.ec.seller.manager.ItemImageManager;


@Repository
public class ItemImageManagerImpl implements ItemImageManager{
	
	@Autowired
	private ItemImageDao itemImageDao;
	private final static Log LOG = LogFactory.getLog(ItemImageManagerImpl.class);
	@Override
	public Integer insert(ItemImage itemImage) {

		return itemImageDao.insert(itemImage);
	}
	@Override
	public List<ItemImage> selectByItemId(int itemId) {
		
		return itemImageDao.selectByItemId(itemId);
	}
	@Override
	public void modify(ItemImage itemImage) {
		itemImageDao.modify(itemImage);
		
	}

	

}
