package com.ec.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ec.api.dao.ItemDescriptionDao;
import com.ec.api.domain.ItemDescription;
import com.ec.api.service.ItemDescriptionService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;

@Service(value="itemDescriptionService")
public class ItemDescriptionServiceImpl implements ItemDescriptionService {
	private static final Logger log = LoggerFactory.getLogger(ItemDescriptionServiceImpl.class);

	private ItemDescriptionDao itemDescriptionDao;
	
	@Override
	public Result getItemDescriptionByItemId(Integer itemId) {
		Result result = new Result();
		try{
			ItemDescription itemDescription = itemDescriptionDao.selectByItemId(itemId);
			if(itemDescription == null){
				result.setResultCode("8003");
				result.setResultMessage("商品介绍不存在");
				return result;
			}
			EcUtils.setSuccessResult(result);
			result.setResult(itemDescription.getAppDescriptionInfo());
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result addItemDescription(ItemDescription itemDescription) {
		Result result = new Result();
		try{
			ItemDescription it = itemDescriptionDao.selectByItemId(itemDescription.getItemId());
			if(it != null){
				result.setResultCode("8008");
				result.setResultMessage("该商品介绍已存在");
				return result;
			}
			itemDescriptionDao.insert(itemDescription);
			EcUtils.setSuccessResult(result);
			result.setResult(true);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result updateItemDescription(ItemDescription itemDescription) {
		Result result = new Result();
		try{
			ItemDescription it = itemDescriptionDao.selectByItemId(itemDescription.getItemId());
			if(it == null){
				result.setResultCode("8003");
				result.setResultMessage("商品介绍不存在");
				return result;
			}
			
			itemDescriptionDao.modify(itemDescription);
			EcUtils.setSuccessResult(result);
			result.setResult(true);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	public void setItemDescriptionDao(ItemDescriptionDao itemDescriptionDao) {
		this.itemDescriptionDao = itemDescriptionDao;
	}

	@Override
	public Result insertOrUpdate(ItemDescription itemDescription) {
		Result result = new Result();
		try{
			itemDescriptionDao.insertOrUpdate(itemDescription);
			EcUtils.setSuccessResult(result);
			result.setResult(true);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	
}
