package com.ec.api.web.controller;

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

import com.ec.api.domain.PaymentInfo;
import com.ec.api.domain.SellerEntry;
import com.ec.api.domain.query.PaymentInfoQuery;
import com.ec.api.domain.query.SellerEntryQuery;
import com.ec.api.service.AddressService;
import com.ec.api.service.PaymentInfoService;
import com.ec.api.service.SellerEntryService;
import com.ec.api.service.result.Result;
import com.ec.api.web.base.BaseController;

@Controller
@RequestMapping("/sellerEntry")
public class SellerEntryController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(SellerEntryController.class);
	private SellerEntryService sellerEntryService;
	
	@RequestMapping(value="addSellerEntry", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result addSellerEntry(SellerEntry sellerEntry, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(sellerEntry.getOrderId() == null){
			result.setResultCode("1001");
			result.setResultMessage("orderId不能为空");
			return result;
		}
		if(sellerEntry.getOrderPayType() == null){
			result.setResultCode("1001");
			result.setResultMessage("orderPayType不能为空");
			return result;
		}
		if(sellerEntry.getPaymentMode() == null){
			result.setResultCode("1001");
			result.setResultMessage("paymentMode不能为空");
			return result;
		}
		
		return sellerEntryService.addSellerEntry(sellerEntry);
	}

	@RequestMapping(value="getSellerEntrys", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result getSellerEntrys(SellerEntryQuery sellerEntryQuery, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(sellerEntryQuery.getOrderId() == null){
			result.setResultCode("1001");
			result.setResultMessage("orderId不能为空");
			return result;
		}
		return sellerEntryService.getSellerEntrys(sellerEntryQuery);
	}

	public void setSellerEntryService(SellerEntryService sellerEntryService) {
		this.sellerEntryService = sellerEntryService;
	}
	
	

}
