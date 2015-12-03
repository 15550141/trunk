package com.ec.api.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ec.api.common.utils.HttpUtils;
import com.ec.api.common.utils.JsonUtils;
import com.ec.api.dao.AccessTokenDao;
import com.ec.api.dao.TaskDao;
import com.ec.api.domain.AccessToken;
import com.ec.api.domain.OrderInfo;
import com.ec.api.domain.Task;
import com.ec.api.service.OrderInfoService;
import com.ec.api.service.TaskService;
import com.ec.api.service.utils.WeixinUtils;


public class TaskServiceImpl implements TaskService{
	
	private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
	
	private OrderInfoService orderInfoService;
	private TaskDao taskDao;
	private AccessTokenDao accessTokenDao;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public void doTask(){
		Task query = new Task();
		query.setYn(1);
		query.setStatus(0);
		
		while(true){
			List<Task> list = taskDao.selectByCondition(query);
			if(list == null || list.size() == 0){
				return;
			}
			for(int i = 0 ; i < list.size() ; i++){
				Task task = list.get(i);
				if(task.getType() == 1){//下单成功任务
					Map<String, Object> map = JsonUtils.readValue(task.getContent());
					Integer orderId = (Integer)map.get("orderId");
					Integer uid = (Integer)map.get("uid");
					OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderIdAndUserId(orderId, uid);
					this.sendSubmitOrderSuccessTemplateMessage(orderInfo);
				}
				if(task.getType() == 2){//支付成功
					this.sendPaySuccessTemplateMessage(task.getContent());
				}
				if(task.getType() == 3){//确认发货
					this.sendDeliverySuccessTemplateMessage(task.getContent());
				}
				task.setStatus(1);//执行完成
				taskDao.modify(task);
			}
			
			if(list.size() < 1000){
				return;
			}
		}
		
	}
	
	/**
	 * 订单发货成功微信模板消息
	 */
	private boolean sendDeliverySuccessTemplateMessage(String taskContent){
		try{
			Map<String, Object> taskMap = JsonUtils.readValue(taskContent);
			Integer orderId = (Integer)taskMap.get("orderId");
			Integer uid = (Integer)taskMap.get("uid");
			OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderIdAndUserId(orderId, uid);
			
			AccessToken accessToken = accessTokenDao.selectByUserId(orderInfo.getUserId());
			if(accessToken == null){
				log.error("任务表，用户没有accesstoken	uid="+orderInfo.getUserId());
				return true;
			}
			
			String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+WeixinUtils.getAccessToken();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("url", "http://www.binfenguoyuan.cn/order/detail?orderId="+orderInfo.getOrderId());
			map.put("topcolor", "#FF0000");
			map.put("touser", accessToken.getOpenid());//openid
			Map<String, Map<String, String>> dataMap = new HashMap<String, Map<String, String>>();
			
			map.put("template_id", "iWSlzBnyMMc0KYsOcLkibxVYfZBFwnQjmML6dDKv7ag");
			
			Map<String, String> first = new HashMap<String, String>();
			first.put("value", "您好，您有一个订单已经发货，请保持手机畅通。");
			first.put("color", "#173177");
	
			Map<String, String> keyword1 = new HashMap<String, String>();
			keyword1.put("value", ""+orderInfo.getOrderId());
			keyword1.put("color", "#173177");
			
			Map<String, String> keyword2 = new HashMap<String, String>();
			keyword2.put("value", "快递员快速送货中...");
			keyword2.put("color", "#173177");
			
			Map<String, String> keyword3 = new HashMap<String, String>();
			keyword3.put("value", sdf.format(orderInfo.getEstimateSendOutTime()));
			keyword3.put("color", "#173177");
			
			Map<String, String> keyword4 = new HashMap<String, String>();
			keyword4.put("value", orderInfo.getConsigneeAddress());
			keyword4.put("color", "#173177");
			
			Map<String, String> remark = new HashMap<String, String>();
			remark.put("value", "如有问题请致电0335-3188123或直接在微信留言");
			remark.put("color", "#173177");
			
			dataMap.put("first", first);
			dataMap.put("keyword1", keyword1);
			dataMap.put("keyword2", keyword2);
			dataMap.put("keyword3", keyword3);
			dataMap.put("keyword4", keyword4);
			dataMap.put("remark", remark);
			
			map.put("data", dataMap);
			
			String data = JsonUtils.writeValue(map);
			String result = HttpUtils.httpPostData(url, data, "utf-8");
			log.error("调用发送支付成功模板接口，参数是："+data+"		返回内容是："+result);
		}catch (Exception e) {
			log.error("", e);
		}
		
		return true;
	}
	
	/**
	 * 支付成功微信模板消息
	 * @param taskContent
	 * @return
	 */
	private boolean sendPaySuccessTemplateMessage(String taskContent){
		try{
			Map<String, Object> taskMap = JsonUtils.readValue(taskContent);
			Integer orderId = (Integer)taskMap.get("orderId");
			Integer uid = (Integer)taskMap.get("uid");
			OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderIdAndUserId(orderId, uid);
			
			AccessToken accessToken = accessTokenDao.selectByUserId(orderInfo.getUserId());
			if(accessToken == null){
				log.error("任务表，用户没有accesstoken	uid="+orderInfo.getUserId());
				return true;
			}
			
			String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+WeixinUtils.getAccessToken();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("url", "http://www.binfenguoyuan.cn/order/detail?orderId="+orderInfo.getOrderId());
			map.put("topcolor", "#FF0000");
			map.put("touser", accessToken.getOpenid());//openid
			Map<String, Map<String, String>> dataMap = new HashMap<String, Map<String, String>>();
			
			map.put("template_id", "ul8fCHO5mMnd5lLAOJH6_nmLRXSltNiISKYm1Ov2ErE");
			
			Map<String, String> first = new HashMap<String, String>();
			first.put("value", "尊敬的客户，您的订单已支付成功！");
			first.put("color", "#173177");
	
			Map<String, String> keyword1 = new HashMap<String, String>();
			keyword1.put("value", ""+orderInfo.getOrderId());
			keyword1.put("color", "#173177");
			
			Map<String, String> keyword2 = new HashMap<String, String>();
			keyword2.put("value", orderInfo.getBigDecimalOrderMoney() + "元");
			keyword2.put("color", "#173177");
			
			Map<String, String> remark = new HashMap<String, String>();
			remark.put("value", "我们会尽快为您发货。");
			remark.put("color", "#173177");
			
			dataMap.put("first", first);
			dataMap.put("keyword1", keyword1);
			dataMap.put("keyword2", keyword2);
			dataMap.put("remark", remark);
			
			map.put("data", dataMap);
			
			String data = JsonUtils.writeValue(map);
			String result = HttpUtils.httpPostData(url, data, "utf-8");
			log.error("调用发送支付成功模板接口，参数是："+data+"		返回内容是："+result);
		}catch (Exception e) {
			log.error("", e);
		}
		
		return true;
	}
	
	private boolean sendSubmitOrderSuccessTemplateMessage(OrderInfo orderInfo){
		try{
			AccessToken accessToken = accessTokenDao.selectByUserId(orderInfo.getUserId());
			if(accessToken == null){
				log.error("任务表，用户没有accesstoken	uid="+orderInfo.getUserId());
				return true;
			}
			
			String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+WeixinUtils.getAccessToken();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("url", "http://www.binfenguoyuan.cn/order/detail?orderId="+orderInfo.getOrderId());
			map.put("topcolor", "#FF0000");
			map.put("touser", accessToken.getOpenid());//openid
			Map<String, Map<String, String>> dataMap = new HashMap<String, Map<String, String>>();
			
			if(orderInfo.getOrderType() == 1){//在线支付
				map.put("template_id", "tJ_N7VPWkQblYNIMj0mAxbzSFtSIg_k7EqP-BKq-BTE");
				
				Map<String, String> first = new HashMap<String, String>();
				first.put("value", "尊敬的客户，您的订单已提交成功！");
				first.put("color", "#173177");
				Map<String, String> keyword1 = new HashMap<String, String>();
				keyword1.put("value", ""+orderInfo.getOrderId());
				keyword1.put("color", "#173177");
				Map<String, String> keyword2 = new HashMap<String, String>();
				keyword2.put("value", sdf.format(orderInfo.getCreated()));
				keyword2.put("color", "#173177");
				Map<String, String> keyword3 = new HashMap<String, String>();
				keyword3.put("value", orderInfo.getBigDecimalOrderMoney() + "元");
				keyword3.put("color", "#173177");
				Map<String, String> keyword4 = new HashMap<String, String>();
				keyword4.put("value", ""+orderInfo.getBigDecimalDiscountMoney());
				keyword4.put("color", "#173177");
				Map<String, String> remark = new HashMap<String, String>();
				remark.put("value", "请您尽快支付，如已支付请忽略。");
				remark.put("color", "#173177");
				
				dataMap.put("first", first);
				dataMap.put("keyword1", keyword1);
				dataMap.put("keyword2", keyword2);
				dataMap.put("keyword3", keyword3);
				dataMap.put("keyword4", keyword4);
				dataMap.put("remark", remark);
			}else{//货到付款
				map.put("template_id", "T7U3nHxo1Y5gzYovKic2EmYAQbvjrwAIL3fcIXuEZ40");
				
				
				Map<String, String> first = new HashMap<String, String>();
				first.put("value", "您的订单已提交成功，开始为您打包商品，您在收货时还需要支付"+orderInfo.getBigDecimalOrderMoney()+"元。");
				first.put("color", "#173177");
				Map<String, String> orderID = new HashMap<String, String>();
				orderID.put("value", ""+orderInfo.getOrderId());
				orderID.put("color", "#173177");
				Map<String, String> orderMoneySum = new HashMap<String, String>();
				orderMoneySum.put("value", orderInfo.getBigDecimalOrderMoney() + "元");
				orderMoneySum.put("color", "#173177");
				Map<String, String> backupFieldName = new HashMap<String, String>();
				backupFieldName.put("value", "预计送达：");
				Map<String, String> backupFieldData = new HashMap<String, String>();
				backupFieldData.put("value", ""+orderInfo.getHopeArrivalTime());
				backupFieldData.put("color", "#173177");
				Map<String, String> remark = new HashMap<String, String>();
				remark.put("value", "如有问题请致电0335-3188123或直接在微信留言");
				remark.put("color", "#173177");
				
				dataMap.put("first", first);
				dataMap.put("orderID", orderID);
				dataMap.put("orderMoneySum", orderMoneySum);
				dataMap.put("backupFieldName", backupFieldName);
				dataMap.put("backupFieldData", backupFieldData);
				dataMap.put("remark", remark);
			}
			
			map.put("data", dataMap);
			
			String data = JsonUtils.writeValue(map);
			String result = HttpUtils.httpPostData(url, data, "utf-8");
			log.error("调用发送下单成功模板接口，参数是："+data+"		返回内容是："+result);
		}catch (Exception e) {
			log.error("", e);
		}
		
		this.sendTemplateMessageToSuliqiang(orderInfo);
		
		return true;
	}
	
	private void sendTemplateMessageToSuliqiang(OrderInfo orderInfo){
		try{
			String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+WeixinUtils.getAccessToken();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("url", "http://www.binfenguoyuan.cn:8081");
			map.put("topcolor", "#FF0000");
			map.put("touser", "oETAJv1ESa1u5a329QMMvQj0Dpv8");//openid
			
			Map<String, Map<String, String>> dataMap = new HashMap<String, Map<String, String>>();
			
			map.put("template_id", "T7U3nHxo1Y5gzYovKic2EmYAQbvjrwAIL3fcIXuEZ40");
			
			Map<String, String> first = new HashMap<String, String>();
			first.put("value", "强哥，有新订单！");
			first.put("color", "#173177");
			
			Map<String, String> orderID = new HashMap<String, String>();
			orderID.put("value", orderInfo.getOrderId() + "");
			orderID.put("color", "#173177");
			Map<String, String> orderMoneySum = new HashMap<String, String>();
			orderMoneySum.put("value", orderInfo.getBigDecimalOrderMoney() + "元");
			orderMoneySum.put("color", "#173177");
			Map<String, String> backupFieldName = new HashMap<String, String>();
			backupFieldName.put("value", "期望送货时间：");
			Map<String, String> backupFieldData = new HashMap<String, String>();
			backupFieldData.put("value", ""+orderInfo.getHopeArrivalTime());
			backupFieldData.put("color", "#173177");
			Map<String, String> remark = new HashMap<String, String>();
			remark.put("value", "查看详情请点击！");
			remark.put("color", "#173177");
			
			dataMap.put("first", first);
			dataMap.put("orderID", orderID);
			dataMap.put("orderMoneySum", orderMoneySum);
			dataMap.put("backupFieldName", backupFieldName);
			dataMap.put("backupFieldData", backupFieldData);
			dataMap.put("remark", remark);
			
			map.put("data", dataMap);
			
			String data = JsonUtils.writeValue(map);
			String result = HttpUtils.httpPostData(url, data, "utf-8");
			log.error("给小强发送模板消息，参数是："+data+"		返回内容是："+result);
		}catch (Exception e) {
			log.error("", e);
			// TODO: handle exception
		}
	}

	public OrderInfoService getOrderInfoService() {
		return orderInfoService;
	}

	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

	public TaskDao getTaskDao() {
		return taskDao;
	}

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	public void setAccessTokenDao(AccessTokenDao accessTokenDao) {
		this.accessTokenDao = accessTokenDao;
	}
	
	
}