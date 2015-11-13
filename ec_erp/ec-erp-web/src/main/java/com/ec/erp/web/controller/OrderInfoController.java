package com.ec.erp.web.controller;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ec.erp.common.utils.PaginatedList;
import com.ec.erp.domain.OrderInfo;
import com.ec.erp.domain.PaymentInfo;
import com.ec.erp.domain.query.OrderInfoQuery;
import com.ec.erp.service.AddressService;
import com.ec.erp.service.OrderInfoService;
import com.ec.erp.service.PaymentInfoService;
import com.ec.erp.service.SellerEntryService;

@Controller
@RequestMapping("/orderInfo")
public class OrderInfoController {

	private final static Log log = LogFactory.getLog(OrderInfoController.class);
	
	private OrderInfoService orderInfoService;
	private SellerEntryService sellerEntryService;
	private PaymentInfoService paymentInfoService;
	private AddressService addressService;
	
	@InitBinder
	public void initDateBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	/**
	 * 订单列表页面
	 * @param orderInfoQuery
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/index", method={ RequestMethod.GET, RequestMethod.POST })
	public String index(OrderInfoQuery orderInfoQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		PaginatedList<OrderInfo> list = orderInfoService.getOrderInfosByPage(orderInfoQuery);
		context.put("list", list);
		context.put("query", orderInfoQuery);
		return "/orderInfo/index";
	}
	
	/**
	 * 锁定订单弹层
	 * @param orderId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/lockOrder", method={ RequestMethod.GET, RequestMethod.POST })
	public String lockOrder(Integer orderId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		context.put("orderId", orderId);
		return "/orderInfo/lockOrder";
	}
	
	/**
	 * 补录金额弹层
	 * @param orderId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/entry", method={ RequestMethod.GET, RequestMethod.POST })
	public String entry(Integer orderId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		context.put("orderId", orderId);
		return "/orderInfo/entry";
	}
	
	/**
	 * 收款确认弹层
	 * @param orderId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/detail", method={ RequestMethod.GET, RequestMethod.POST })
	public String detail(Integer orderId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		OrderInfoQuery orderInfoQuery = new OrderInfoQuery();
		orderInfoQuery.setOrderId(orderId);
		OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderId(orderInfoQuery);
		String address = addressService.getDetailConsigneeAddress(orderInfo.getConsigneeProvince(), orderInfo.getConsigneeCity(), orderInfo.getConsigneeCounty(), orderInfo.getConsigneeAddress());
		
		context.put("orderInfo", orderInfo);
		context.put("address", address);
		PaymentInfo paymentInfo1 = paymentInfoService.getPaymentInfoByOrderId(orderId, 1);//获取全款或者订金支付金额
		context.put("paymentInfo1", paymentInfo1);//全款或者定金支付金额
		PaymentInfo paymentInfo2 = paymentInfoService.getPaymentInfoByOrderId(orderId, 2);//获取全款或者订金支付金额
		context.put("paymentInfo2", paymentInfo2);//尾款支付金额
		context.put("zaixianSellEntryPrice", sellerEntryService.selectSumPayMoneyByCondition(orderId, 1));//补录金额
		context.put("weikuanSellEntryPrice", sellerEntryService.selectSumPayMoneyByCondition(orderId, 2));//尾款补录金额
		return "/orderInfo/detail";
	}
	
	/**
	 * 收款确认弹层
	 * @param orderId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/confirmGetPrice", method={ RequestMethod.GET, RequestMethod.POST })
	public String confirmGetPrice(Integer orderId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		OrderInfoQuery orderInfoQuery = new OrderInfoQuery();
		orderInfoQuery.setOrderId(orderId);
		OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderId(orderInfoQuery);
		PaymentInfo paymentInfo = paymentInfoService.getPaymentInfoByOrderId(orderId, 1);//获取全款或者订金支付金额
		
		context.put("paymentInfo", paymentInfo);
		context.put("orderInfo", orderInfo);
		context.put("sellerEntryMoney", sellerEntryService.selectSumPayMoneyByCondition(orderId, 1));
		return "/orderInfo/confirmGetPrice";
	}
	
	/**
	 * 尾款确认弹层
	 * @param orderId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/confirmGetLastPrice", method={ RequestMethod.GET, RequestMethod.POST })
	public String confirmGetLastPrice(Integer orderId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		OrderInfoQuery orderInfoQuery = new OrderInfoQuery();
		orderInfoQuery.setOrderId(orderId);
		OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderId(orderInfoQuery);
		
		context.put("orderInfo", orderInfo);
		PaymentInfo paymentInfo1 = paymentInfoService.getPaymentInfoByOrderId(orderId, 1);//获取全款或者订金支付金额
		context.put("paymentInfo1", paymentInfo1);//全款或者定金支付金额
		PaymentInfo paymentInfo2 = paymentInfoService.getPaymentInfoByOrderId(orderId, 2);//获取全款或者订金支付金额
		context.put("paymentInfo2", paymentInfo2);//尾款支付金额
		context.put("zaixianSellEntryPrice", sellerEntryService.selectSumPayMoneyByCondition(orderId, 1));//补录金额
		context.put("weikuanSellEntryPrice", sellerEntryService.selectSumPayMoneyByCondition(orderId, 2));//尾款补录金额
		return "/orderInfo/confirmGetLastPrice";
	}
	
	/**
	 * 收款确认
	 * @param orderId
	 * @param remark
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/doConfirmGetPrice", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> doConfirmGetPrice(Integer orderId, Integer venderUserId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> map = new HashMap<String, Object>();
		if(orderId == null){
			map.put("success", false);
			map.put("message", "订单号不能为空");
			return map;
		}
		
		//TODO 需要改为从cookie中获取 venderUserId
		return orderInfoService.confirmGetPrice(orderId, venderUserId);
	}
	
	/**
	 * 收款确认
	 * @param orderId
	 * @param remark
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/doFinish", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> doFinish(Integer orderId, Integer venderUserId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> map = new HashMap<String, Object>();
		if(orderId == null){
			map.put("success", false);
			map.put("message", "订单号不能为空");
			return map;
		}
		
		//TODO 需要改为从cookie中获取 venderUserId
		return orderInfoService.updateOrderInfoFinish(orderId, venderUserId);
	}
	
	/**
	 * 尾款收款确认
	 * @param orderId
	 * @param remark
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/doConfirmGetLastPrice", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> doConfirmGetLastPrice(Integer orderId, Integer venderUserId, String remark, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> map = new HashMap<String, Object>();
		if(orderId == null){
			map.put("success", false);
			map.put("message", "订单号不能为空");
			return map;
		}
		
		return orderInfoService.confirmGetLastPrice(orderId, venderUserId);
	}
	
	/**
	 * 锁定订单
	 * @param orderId
	 * @param lockReason
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/doLock", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> doLock(Integer orderId, Integer venderUserId, String lockReason, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> map = new HashMap<String, Object>();
		if(orderId == null){
			map.put("success", false);
			map.put("message", "订单号不能为空");
			return map;
		}
		if(StringUtils.isBlank(lockReason)){
			map.put("success", false);
			map.put("message", "锁定原因不能为空");
			return map;
		}
		return orderInfoService.lockOrder(orderId, venderUserId, lockReason);
	}
	
	/**
	 * 确认发货
	 * @param orderId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/sendGoods", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> sendGoods(Integer orderId, Integer venderUserId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> map = new HashMap<String, Object>();
		if(orderId == null){
			map.put("success", false);
			map.put("message", "订单号不能为空");
			return map;
		}
		return orderInfoService.sendGoods(orderId, venderUserId);
	}

	/**
	 * 解锁订单
	 * @param orderId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/doUnLock", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> doUnLock(Integer orderId, Integer venderUserId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> map = new HashMap<String, Object>();
		if(orderId == null){
			map.put("success", false);
			map.put("message", "订单号不能为空");
			return map;
		}
		//TODO 需要改为从cookie中获取 venderUserId
		return orderInfoService.unLockOrder(orderId, venderUserId);
	}
	
	/**
	 * 订单金额补录
	 * @param orderId
	 * @param orderPayType
	 * @param paymentMode
	 * @param paymentMoney
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/doEntry", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> doEntry(Integer orderId,
			Integer orderPayType, Integer paymentMode, BigDecimal paymentMoney, Integer venderUserId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> map = new HashMap<String, Object>();
		if(orderId == null){
			map.put("success", false);
			map.put("message", "订单号不能为空");
			return map;
		}
		if(orderPayType == null){
			map.put("success", false);
			map.put("message", "订单款项类型不能为空");
			return map;
		}
		if(paymentMode == null){
			map.put("success", false);
			map.put("message", "支付方式不能为空");
			return map;
		}
		if(paymentMoney == null){
			map.put("success", false);
			map.put("message", "金额不能为空");
			return map;
		}
		//TODO 需要改为从cookie中获取 venderUserId
		return orderInfoService.addSellerEntry(orderId, orderPayType, paymentMode, paymentMoney, venderUserId);
	}
	
	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

	public void setSellerEntryService(SellerEntryService sellerEntryService) {
		this.sellerEntryService = sellerEntryService;
	}

	public void setPaymentInfoService(PaymentInfoService paymentInfoService) {
		this.paymentInfoService = paymentInfoService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	
}

