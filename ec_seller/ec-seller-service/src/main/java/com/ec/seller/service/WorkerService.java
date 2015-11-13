package com.ec.seller.service;

public interface WorkerService {
	//商品上架
	public void onSheft();
	
	//商品下架
	public void offSheft();
	
	//促销开启
	public void startPromotion();
	
	//促销关闭
	public void stopPromotion();
	
	//自动发短信
	public void sendSms();

}
