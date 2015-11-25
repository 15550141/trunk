package com.ec.api.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ec.api.common.utils.CookieUtils;
import com.ec.api.domain.PaymentInfo;
import com.ec.api.domain.query.PaymentInfoQuery;
import com.ec.api.service.AddressService;
import com.ec.api.service.PaymentInfoService;
import com.ec.api.service.result.Result;
import com.ec.api.web.base.BaseController;

@Controller
@RequestMapping("/wxpay")
public class PaymentInfoController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(PaymentInfoController.class);
	
	private PaymentInfoService paymentInfoService;
	
	@RequestMapping(value="pay", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result pay(PaymentInfo paymentInfo, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		
		if(paymentInfo.getOrderId() == null){
			result.setResultCode("1001");
			result.setResultMessage("orderId不能为空");
			return result;
		}
		if(paymentInfo.getOrderPayType() == null){
			result.setResultCode("1001");
			result.setResultMessage("orderPayType不能为空");
			return result;
		}
		Integer uid = CookieUtils.getUid(request);
		paymentInfo.setUid(uid);
		return paymentInfoService.userCreatePayment(paymentInfo);
	}
	
	@RequestMapping(value="wxCallBack", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String wxCallback(PaymentInfo paymentInfo, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		String str = null;
		try {
			StringBuffer sb = new StringBuffer() ;
			InputStream is = request.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);  
			BufferedReader br = new BufferedReader(isr);
			String s = "" ;
			while((s=br.readLine())!=null){
				sb.append(s) ;
			}
			str = sb.toString();
		} catch (IOException e) {
			log.error("", e);
		}
		return paymentInfoService.wxCallback(str);
	}
	
	
	
	
	//---------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value="addPaymentInfo", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result addPaymentInfo(PaymentInfo paymentInfo, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(paymentInfo.getOrderId() == null){
			result.setResultCode("1001");
			result.setResultMessage("orderId不能为空");
			return result;
		}
		if(paymentInfo.getOrderPayType() == null){
			result.setResultCode("1001");
			result.setResultMessage("orderPayType不能为空");
			return result;
		}
		if(paymentInfo.getPaymentInfoType() == null){
			result.setResultCode("1001");
			result.setResultMessage("paymentInfoType不能为空");
			return result;
		}
		if(paymentInfo.getPaymentMoney() == null){
			result.setResultCode("1001");
			result.setResultMessage("paymentMoney不能为空");
			return result;
		}
		if(StringUtils.isBlank(paymentInfo.getPaymentNumber())){
			result.setResultCode("1001");
			result.setResultMessage("paymentNumber不能为空");
			return result;
		}
		
		return paymentInfoService.addPaymentInfo(paymentInfo);
	}

	@RequestMapping(value="getPaymentInfos", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result getPaymentInfos(PaymentInfoQuery paymentInfoQuery, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(paymentInfoQuery.getOrderId() == null){
			result.setResultCode("1001");
			result.setResultMessage("orderId不能为空");
			return result;
		}
		return paymentInfoService.getPaymentInfos(paymentInfoQuery);
	}
	public void setPaymentInfoService(PaymentInfoService paymentInfoService) {
		this.paymentInfoService = paymentInfoService;
	}
	
	
	

}
