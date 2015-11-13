package com.ec.seller.manager;

import java.util.List;

import com.ec.seller.domain.PromotionInfo;
import com.ec.seller.domain.query.ItemQuery;


public interface WorkerManager {
	
	//查询待上架商品
	public List<ItemQuery> queryOnSheftList(Integer offsetOnSheft);
	//查询待下架商品
	public List<ItemQuery> queryOffSheftList(Integer offsetOffSheft);
	
	
	

	
	

	
	//自动发短信
	public void sendSms();
	
	//商品上架
	public void onSheft(ItemQuery item);
	
	//商品下架
	public void offSheft(ItemQuery item);
	
	public List<PromotionInfo> querystartPromotionList(int offsetStartProm);
	
	//促销开启
	public void startPromotion(PromotionInfo promotionInfo);
	
	public List<PromotionInfo> querystopPromotionList(int offsetStopProm);
	//促销关闭
	public void stopPromotion(PromotionInfo promotionInfo);


}
