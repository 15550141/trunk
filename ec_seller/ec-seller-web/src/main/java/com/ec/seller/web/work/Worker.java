package com.ec.seller.web.work;

import org.springframework.beans.factory.annotation.Autowired;

import com.ec.seller.service.WorkerService;

public class Worker {
	
	@Autowired
	private WorkerService workerService;
	
	//商品上架
	public void onSheft(){
		workerService.onSheft();
	};
	
	//商品下架
	public void offSheft(){
		workerService.offSheft();
	};
	
	//促销开启
	public void startPromotion(){
		workerService.startPromotion();
	};
	
	//促销关闭
	public void stopPromotion(){
		workerService.startPromotion();
	};
	
	//自动发短信
	public void sendSms(){
		workerService.sendSms();
	};
	

}
