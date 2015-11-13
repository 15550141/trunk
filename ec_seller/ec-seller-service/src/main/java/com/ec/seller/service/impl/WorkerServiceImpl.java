package com.ec.seller.service.impl;


import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.seller.domain.PromotionInfo;
import com.ec.seller.domain.query.ItemQuery;
import com.ec.seller.manager.WorkerManager;
import com.ec.seller.service.WorkerService;


@Service(value = "workerService")
public class WorkerServiceImpl implements WorkerService{
	@Autowired
	private WorkerManager workerManager;
	private static int offsetOffSheft = 0; // worker分段查询数据库偏移量	
	private static int offsetOnSheft = 0; // worker分段查询数据库偏移量	
	private static int offsetStartProm = 0; // worker分段查询数据库偏移量
	private static int offsetStopProm = 0; // worker分段查询数据库偏移量
	
	
	private final static Log LOG = LogFactory.getLog(WorkerServiceImpl.class);
	
    /**
     * 商品自动下架
     */
	@Override
	public void offSheft() {
		LOG.error("-------商品自动下架任务 start-------");
		try {
			//查询所有待下架的商品
			List<ItemQuery> itemList=workerManager.queryOffSheftList(offsetOffSheft);
			if (itemList.isEmpty()) {
				offsetOffSheft = 0; // 当从数据库中取不到数据时，把偏移量设为0，即初始值
			} else {
				for(ItemQuery  item: itemList){
					workerManager.offSheft(item);
					LOG.error("###自动下架处理编号："+offsetOffSheft+"无异常成功执行###");
					LOG.error("###自动下架处理商品编号："+item.getItemId()+"无异常成功执行###");
				}//for end
				offsetOffSheft=offsetOffSheft+500;
			}
			
			} catch (Exception e) {
				LOG.error("-----WorkerServiceImpl.offSheft-----", e);
				e.printStackTrace();
			} finally {
			}
			LOG.error("-------商品自动下架任务 end-------");
		}

    /**
     * 商品自动上架
     */
	@Override
	public void onSheft() {
		LOG.error("-------商品自动上架任务 start-------");
		try {
			
			List<ItemQuery> itemList=workerManager.queryOnSheftList(offsetOnSheft);//查询所有待下架的商品
			if (itemList.isEmpty()) {
				offsetOnSheft = 0; // 当从数据库中取不到数据时，把偏移量设为0，即初始值
			} else {
				for(ItemQuery  item: itemList){
					workerManager.onSheft(item);
					LOG.error("###自动下架处理编号："+offsetOnSheft+"无异常成功执行###");
					LOG.error("###自动下架处理商品编号："+item.getItemId()+"无异常成功执行###");
				}//for end
				offsetOnSheft=offsetOnSheft+500;
			}
			
			} catch (Exception e) {
				LOG.error("-----WorkerServiceImpl.onSheft-----", e);
				e.printStackTrace();
			} finally {
			}
			LOG.error("-------商品自动上架任务 end-------");
		}
	@Override
	public void sendSms() {
		// TODO Auto-generated method stub
		
	}

    /**
     * 自动开启促销
     */
	@Override
	public void startPromotion() {
		LOG.error("-------自动开启促销任务 start-------");
		try {
			//查询所有待下架的商品
			List<PromotionInfo> promotionInfoList=workerManager.querystartPromotionList(offsetStartProm);
			if (promotionInfoList.isEmpty()) {
				offsetStartProm = 0; // 当从数据库中取不到数据时，把偏移量设为0，即初始值
			} else {
				for(PromotionInfo  promotionInfo: promotionInfoList){
					workerManager.startPromotion(promotionInfo);
					LOG.error("###自动开启促销处理编号："+offsetStartProm+"无异常成功执行###");
					LOG.error("###自动开启促销促销编号："+promotionInfo.getPromotionId()+"无异常成功执行###");
				}//for end
				offsetStartProm=offsetStartProm+500;
			}
			} catch (Exception e) {
				LOG.error("-----WorkerServiceImpl.startPromotion-----", e);
				e.printStackTrace();
			} finally {
			}
			LOG.error("-------自动开启促销任务 end-------");
		}

    /**
     * 自动关闭促销
     */
		@Override
		public void stopPromotion() {
			LOG.error("-------自动关闭促销任务 start-------");
			try {
				//查询所有待下架的商品
				List<PromotionInfo> promotionInfoList=workerManager.querystopPromotionList(offsetStopProm);
				if (promotionInfoList.isEmpty()) {
					offsetStopProm = 0; // 当从数据库中取不到数据时，把偏移量设为0，即初始值
				} else {
					for(PromotionInfo  promotionInfo: promotionInfoList){
						workerManager.stopPromotion(promotionInfo);
						LOG.error("###自动关闭促销处理编号："+offsetStartProm+"无异常成功执行###");
						LOG.error("###自动关闭促销促销编号："+promotionInfo.getPromotionId()+"无异常成功执行###");
					}//for end
					offsetStopProm=offsetStopProm+500;
				}
				
				} catch (Exception e) {
					LOG.error("-----WorkerServiceImpl.stopPromotion-----", e);
					e.printStackTrace();
				} finally {
				}
				LOG.error("-------自动关闭促销任务 end-------");
			}


}
