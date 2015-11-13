package com.ec.seller.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ec.seller.dao.AddressDao;
import com.ec.seller.dao.ItemDao;
import com.ec.seller.dao.PromotionDAO;
import com.ec.seller.dao.PromotionInfoDao;
import com.ec.seller.domain.PromotionInfo;
import com.ec.seller.domain.query.ItemQuery;
import com.ec.seller.manager.WorkerManager;


@Repository
public class WorkManagerImpl implements WorkerManager{
	
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private PromotionInfoDao promotionInfoDao;
	private final static Log LOG = LogFactory.getLog(WorkManagerImpl.class);


	@Override
	public void sendSms() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<ItemQuery> queryOffSheftList(Integer offsetOffSheft) {
		
		return itemDao.queryOffSheftList(offsetOffSheft);
	}
	@Override
	public List<ItemQuery> queryOnSheftList(Integer offsetOnSheft) {
		
		return itemDao.queryOnSheftList(offsetOnSheft);
	}
	@Override
	public void offSheft(ItemQuery item) {
		itemDao.offSheft(item);
		
	}
	@Override
	public void onSheft(ItemQuery item) {
		itemDao.onSheft(item);
		
	}
	@Override
	public List<PromotionInfo> querystartPromotionList(int offsetStartProm) {
		
		return promotionInfoDao.querystartPromotionList(offsetStartProm);
	}
	@Override
	public List<PromotionInfo> querystopPromotionList(int offsetStopProm) {
		
		return promotionInfoDao.querystopPromotionList(offsetStopProm);
	}
	@Override
	public void startPromotion(PromotionInfo promotionInfo) {
		promotionInfoDao.startPromotion(promotionInfo);
		
	}
	@Override
	public void stopPromotion(PromotionInfo promotionInfo) {
		promotionInfoDao.stopPromotion(promotionInfo);
		
	}

	

}
