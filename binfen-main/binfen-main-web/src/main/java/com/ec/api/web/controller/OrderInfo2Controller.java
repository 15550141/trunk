package com.ec.api.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ec.api.common.utils.CookieUtils;
import com.ec.api.common.utils.JsonUtils;
import com.ec.api.common.utils.PaginatedArrayList;
import com.ec.api.domain.CartInfo;
import com.ec.api.domain.OrderDetail;
import com.ec.api.domain.OrderInfo;
import com.ec.api.domain.ReceiveAddr;
import com.ec.api.domain.SkuList;
import com.ec.api.domain.query.OrderInfoQuery;
import com.ec.api.service.OrderInfoService;
import com.ec.api.service.ReceiveAddrService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;
import com.ec.api.web.base.BaseController;

@Controller
@RequestMapping("/order2")
public class OrderInfo2Controller extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(OrderInfo2Controller.class);
	private OrderInfoService orderInfoService;
	
	private ReceiveAddrService receiveAddrService;
	
	private String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
	
	@RequestMapping(value="", method={RequestMethod.GET, RequestMethod.POST})
	public String index(HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Integer uid = CookieUtils.getUid(request);
		CartInfo cartInfo = orderInfoService.index(uid, request);
		if(cartInfo == null){
			try {
				response.sendRedirect("http://www.binfenguoyuan.cn");
			} catch (IOException e) {
				log.error("", e);
			}
			return null;
		}
		//获得默认收货地址
		ReceiveAddr addr = receiveAddrService.getDefaultReceiveAddr(uid);
		//获取可配送的时间
		List<String> timeList = this.getHopeArrivalTimeList();
		context.put("timeList", timeList);
		context.put("addr", addr);
		context.put("cartInfo", cartInfo);
		return "order/order2";
	}
	
	@RequestMapping(value="createOrder", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result createOrder(String orderType, String address_id, String hopeArrivalTime, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Integer uid = CookieUtils.getUid(request);
		Result result = new Result();
		if(StringUtils.isBlank(address_id)){
			result.setResult(null);
			result.setSuccess(false);
			result.setResultMessage("地址信息不能为空");
			return result;
		}
		if(StringUtils.isBlank(hopeArrivalTime)){
			result.setResult(null);
			result.setSuccess(false);
			result.setResultMessage("请选择配送时间");
			return result;
		}
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setUserId(uid);
		orderInfo.setHopeArrivalTime(hopeArrivalTime);
		if(StringUtils.isBlank(orderType) || (!"1".equals(orderType) && !"2".equals(orderType))){
			orderInfo.setOrderType(2);
		}else{
			orderInfo.setOrderType(Integer.parseInt(orderType));
		}
		return orderInfoService.createOrder(orderInfo, request, response);
	}
	
	@RequestMapping(value="my", method={RequestMethod.GET, RequestMethod.POST})
	public String my(Integer page, Integer type, Integer orderStatus, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(page == null){
			page = 1;
		}
		if(type == null){
			type = 1;
		}
		OrderInfoQuery orderInfoQuery = new OrderInfoQuery();
		orderInfoQuery.setPageNo(page);
		orderInfoQuery.setUserId(CookieUtils.getUid(request));
		
		PaginatedArrayList<OrderInfo> orders = null;
		
		
		if(type == 2){
			//已完成订单
			orderInfoQuery.setOrderStatus(50);
			orders = this.orderInfoService.getOrderInfosByOrderInfoQuery(orderInfoQuery);
		}else if(type == 3){
			//已取消订单
			orderInfoQuery.setOrderStatus(51);
			orders = this.orderInfoService.getOrderInfosByOrderInfoQuery(orderInfoQuery);
		}else{
			type = 1;
			orders = this.orderInfoService.getUnfinishedOrder(orderInfoQuery);
		}
		
		context.put("orders", orders);
		context.put("type", type);
		context.put("page", page);
		
		return "/order/my";
	}
	
	@RequestMapping(value="more", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result more(Integer page, Integer type, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(page == null){
			page = 1;
		}
		if(type == null){
			type = 1;
		}
		OrderInfoQuery orderInfoQuery = new OrderInfoQuery();
		orderInfoQuery.setPageNo(page);
		orderInfoQuery.setUserId(CookieUtils.getUid(request));
		
		PaginatedArrayList<OrderInfo> orders = null;
		
		
		if(type == 2){
			//已完成订单
			orderInfoQuery.setOrderStatus(50);
			orders = this.orderInfoService.getOrderInfosByOrderInfoQuery(orderInfoQuery);
		}else if(type == 3){
			//已取消订单
			orderInfoQuery.setOrderStatus(51);
			orders = this.orderInfoService.getOrderInfosByOrderInfoQuery(orderInfoQuery);
		}else{
			type = 1;
			orders = this.orderInfoService.getUnfinishedOrder(orderInfoQuery);
		}
		
		Result result = new Result();
		
		if(orders == null || orders.size() == 0){
			result.setSuccess(false);
			return result;
		}
		
		result.setSuccess(true);
		result.setResult(orders);
		
		return result;
	}
	
	@RequestMapping(value="detail", method={RequestMethod.GET, RequestMethod.POST})
	public String detail(Integer orderId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(orderId == null){
			return "error";
		}
		
		Integer userId = CookieUtils.getUid(request);
		OrderInfo order = this.orderInfoService.getOrderInfoByOrderIdAndUserId(orderId, userId);
		if(order == null){
			return "error";
		}
		context.put("order", order);
		
		return "/order/detail";
	}
	
	@RequestMapping(value="chosePayment", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result chosePayment(Integer paymentType, Integer orderType, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		Map<String, Object> map = new HashMap<String, Object>();
		result.setSuccess(true);
		Integer uid = CookieUtils.getUid(request);
		Integer count = orderInfoService.getEffectiveOrderCount(uid);
		map.put("pmt_goods", 0);
		if(count == null || count == 0){ //没下过订单
			if(orderType == 1){ //在线支付
				map.put("pmt_goods", 5);
			}
		}
		return result;
	}
	
	@RequestMapping(value="orderCancle", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result orderCancle(Integer orderId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Integer uid = CookieUtils.getUid(request);
		return orderInfoService.orderCancle(orderId, uid, request, response);
	}
	
	
	/**
	 * 下单
	 * @param orderInfo
	 * @param skus
	 * @param request
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="submit", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result submit(OrderInfo orderInfo, String skus, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(StringUtils.isBlank(skus)){
			result.setResultCode("1001");
			result.setResultMessage("skus不能为空");
			return result;
		}

		String listString = "{\"list\":"+skus+"}";
		SkuList skuList = null;
		try{
			skuList = JsonUtils.readValue(listString, SkuList.class);
		}catch (Exception e) {
			result.setResultCode("1001");
			result.setResultMessage("skus格式不正确");
			return result;
		}
		
		List<OrderDetail> orderDetailList = skuList.getList();
		for(int i=0;i<orderDetailList.size();i++){
			OrderDetail od = orderDetailList.get(i);
			if(od.getItemId() == null || od.getNum() == null || od.getSkuId() == null || od.getNum() <= 0){
				result.setResultCode("1001");
				result.setResultMessage("skus格式不正确");
				return result;
			}
		}
		
		if(orderInfo.getOrderType() == null || (orderInfo.getOrderType() !=1 && orderInfo.getOrderType() != 2)){
			result.setResultCode("1001");
			result.setResultMessage("orderType不能为空");
			return result;
		}
		
		if(StringUtils.isBlank(orderInfo.getConsigneeName())){
			result.setResultCode("1001");
			result.setResultMessage("consigneeName不能为空");
			return result;
		}
		if(orderInfo.getConsigneeProvince() == null){
			result.setResultCode("1001");
			result.setResultMessage("consigneeProvince不能为空");
			return result;
		}
		if(orderInfo.getConsigneeCity() == null){
			result.setResultCode("1001");
			result.setResultMessage("consigneeCity不能为空");
			return result;
		}
		if(orderInfo.getConsigneeCounty() == null){
			result.setResultCode("1001");
			result.setResultMessage("consigneeCounty不能为空");
			return result;
		}
		if(StringUtils.isBlank(orderInfo.getConsigneeAddress())){
			result.setResultCode("1001");
			result.setResultMessage("consigneeAddress不能为空");
			return result;
		}
		if(StringUtils.isBlank(orderInfo.getConsigneeMobile())){
			result.setResultCode("1001");
			result.setResultMessage("consigneeMobile不能为空");
			return result;
		}
		if(orderInfo.getVenderUserId() == null){
			result.setResultCode("1001");
			result.setResultMessage("venderUserId不能为空");
			return result;
		}
		
		orderInfo.setUserId(getUserId(request));
		orderInfo.setIp(getRemoteIp(request));
		
		return orderInfoService.submit(orderInfo, orderDetailList);
	}
	
    /**
	 * 根据订单号和用户id获取订单状态信息
	 * @param request
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="getOrderStatusByOrderIdAndUserId", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result getOrderStatusByOrderIdAndUserId(Integer orderId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		return orderInfoService.getOrderStatusByOrderIdAndUserId(orderId, getUserId(request));
	}
	
	/**
	 * 根据订单号和商家id获取订单详细信息
	 * @param request
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="getOrderInfoByOrderIdAndVenderUserId", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result getOrderInfoByOrderIdAndVenderUserId(Integer orderId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		return orderInfoService.getOrderInfoByOrderIdAndVenderUserId(orderId, getUserId(request));
	}
	
	
	/**
	 * 商家确认收款
	 * @param orderId
	 * @param request
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="confirmGetPrice", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result confirmGetPrice(Integer orderId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(orderId == null){
			Result result = new Result();
			result.setResultCode("1001");
			result.setResultMessage("orderId不能为空");
			return result;
		}
		return orderInfoService.confirmGetPrice(orderId, getUserId(request));
	}
	
	/**
	 * 商家尾款确认收款
	 * @param orderId
	 * @param request
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="confirmGetLastPrice", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result confirmGetLastPrice(Integer orderId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(orderId == null){
			Result result = new Result();
			result.setResultCode("1001");
			result.setResultMessage("orderId不能为空");
			return result;
		}
		return orderInfoService.confirmGetLastPrice(orderId, getUserId(request));
	}
	
	/**
	 * 商家发货确认
	 * @param orderId
	 * @param request
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="confirmSendOut", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result confirmSendOut(Integer orderId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(orderId == null){
			Result result = new Result();
			result.setResultCode("1001");
			result.setResultMessage("orderId不能为空");
			return result;
		}
		return orderInfoService.confirmSendOut(orderId, getUserId(request));
	}
	
	/**
	 * 用户收货确认
	 * @param orderId
	 * @param request
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="confirmGetGoods", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result confirmGetGoods(Integer orderId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(orderId == null){
			Result result = new Result();
			result.setResultCode("1001");
			result.setResultMessage("orderId不能为空");
			return result;
		}
		return orderInfoService.confirmGetGoods(orderId, getUserId(request));
	}
	
	/**
	 * 商家订单完成确认
	 * @param orderId
	 * @param request
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="orderSuccess", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result orderSuccess(Integer orderId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(orderId == null){
			Result result = new Result();
			result.setResultCode("1001");
			result.setResultMessage("orderId不能为空");
			return result;
		}
		return orderInfoService.orderSuccess(orderId);
	}
	
	/**
	 * 商家订单锁定
	 * @param orderId
	 * @param lockReason
	 * @param request
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="lockOrder", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result lockOrder(Integer orderId, String lockReason, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(orderId == null){
			Result result = new Result();
			result.setResultCode("1001");
			result.setResultMessage("orderId不能为空");
			return result;
		}
		return orderInfoService.lockOrder(orderId, lockReason);
	}
	
	/**
	 * 商家订单解锁
	 * @param orderId
	 * @param request
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="unlockOrder", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result unlockOrder(Integer orderId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(orderId == null){
			Result result = new Result();
			result.setResultCode("1001");
			result.setResultMessage("orderId不能为空");
			return result;
		}
		return orderInfoService.unlockOrder(orderId);
	}
	
	
	@RequestMapping(value="createTradeNo", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result createTradeNo(Integer orderId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(orderId == null){
			Result result = new Result();
			result.setResultCode("1001");
			result.setResultMessage("orderId不能为空");
			return result;
		}
		return orderInfoService.createTradeNo(orderId, getUserId(request));
	}
	
	private List<String> getHopeArrivalTimeList(){
		//定义配送时间
		//1、10-12	2、12-14		3、14-17		4、17-20
		List<String> list = new ArrayList<String>();
		
		Calendar cal = Calendar.getInstance();
		Integer nowHour = cal.get(Calendar.HOUR_OF_DAY);
		String nowDate = (cal.get(Calendar.MONTH)+ 1)+"-"+cal.get(Calendar.DAY_OF_MONTH)+"|"+weekDays[(cal.get(Calendar.DAY_OF_WEEK) - 1)];
		if(nowHour < 10){
			list.add(nowDate + " 10:00-12:00");
			list.add(nowDate + " 12:00-14:00");
			list.add(nowDate + " 14:00-17:00");
			list.add(nowDate + " 17:00-20:00");
		}else if(nowHour < 12){
			list.add(nowDate + " 12:00-14:00");
			list.add(nowDate + " 14:00-17:00");
			list.add(nowDate + " 17:00-20:00");
		}else if(nowHour < 15){
			list.add(nowDate + " 14:00-17:00");
			list.add(nowDate + " 17:00-20:00");
		}else if(nowHour < 19){
			list.add(nowDate + " 17:00-20:00");
		}
		
		cal.add(Calendar.DATE, 1);
		System.out.println();
		String nextDate = (cal.get(Calendar.MONTH)+ 1)+"-"+cal.get(Calendar.DAY_OF_MONTH)+"|"+weekDays[(cal.get(Calendar.DAY_OF_WEEK) - 1)];
		list.add(nextDate + " 10:00-12:00");
		list.add(nextDate + " 12:00-14:00");
		list.add(nextDate + " 14:00-17:00");
		list.add(nextDate + " 17:00-20:00");
		return list;
	}

	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

	public void setReceiveAddrService(ReceiveAddrService receiveAddrService) {
		this.receiveAddrService = receiveAddrService;
	}


}
