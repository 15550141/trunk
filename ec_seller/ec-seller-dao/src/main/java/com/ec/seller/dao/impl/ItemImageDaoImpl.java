package com.ec.seller.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.seller.dao.ItemImageDao;
import com.ec.seller.domain.ItemImage;

public class ItemImageDaoImpl extends SqlMapClientTemplate implements ItemImageDao {

	@Override
	public Integer insert(ItemImage itemImage) {
		return (Integer)insert("ItemImage.insert", itemImage);
	}

	@Override
	public void modify(ItemImage itemImage) {
		update("ItemImage.updateByPrimaryKey", itemImage);
	}

	@Override
	public List<ItemImage> selectByItemId(int itemId) {
		return queryForList("ItemImage.selectByItemId", itemId);
	}

}
