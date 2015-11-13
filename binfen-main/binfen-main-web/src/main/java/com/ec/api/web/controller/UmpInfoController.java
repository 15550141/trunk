package com.ec.api.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ec.api.service.AddressService;
import com.ec.api.service.UmpInfoService;
import com.ec.api.service.result.Result;
import com.ec.api.service.vo.UmpCallbackResultVo;
import com.ec.api.service.vo.UmpCallbackVo;
import com.ec.api.web.base.BaseController;

@Controller
@RequestMapping("/ump")
public class UmpInfoController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(UmpInfoController.class);
	private AddressService addressService;
	private UmpInfoService umpInfoService;
	
	@RequestMapping(value="callback", method={RequestMethod.GET, RequestMethod.POST})
	public String callback(HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Map reqMap = new HashMap();
        log.info("calback.................");
		reqMap.put("service", request.getParameter("service"));//
		reqMap.put("mer_id", request.getParameter("mer_id"));//
		reqMap.put("sign_type", request.getParameter("sign_type"));//
		reqMap.put("sign", request.getParameter("sign"));//
		reqMap.put("version", request.getParameter("version"));//
        //  
		reqMap.put("trade_no", request.getParameter("trade_no")); //
		reqMap.put("goods_id", request.getParameter("goods_id")); //
		reqMap.put("order_id", request.getParameter("order_id"));//
		reqMap.put("mer_date", request.getParameter("mer_date"));//
		reqMap.put("pay_date", request.getParameter("pay_date"));//
		reqMap.put("amount", request.getParameter("amount")); //
		reqMap.put("amt_type", request.getParameter("amt_type"));//
		reqMap.put("pay_type", request.getParameter("pay_type")); //
		reqMap.put("media_id", request.getParameter("media_id"));//
		reqMap.put("media_type", request.getParameter("media_type"));//
		reqMap.put("settle_date", request.getParameter("settle_date"));//
//		reqMap.put("mer_priv", request.getParameter("mer_priv"));
		reqMap.put("trade_state", request.getParameter("trade_state")); //
		reqMap.put("pay_seq", request.getParameter("pay_seq"));//
		reqMap.put("error_code", request.getParameter("error_code"));//
        reqMap.put("charset", "UTF-8");  //
//		reqMap.put("usr_busi_agreement_id", request.getParameter("usr_busi_agreement_id"));
//		reqMap.put("usr_pay_agreement_id", request.getParameter("usr_pay_agreement_id"));
//		reqMap.put("gate_id", request.getParameter("gate_id"));
//		reqMap.put("last_four_cardid", request.getParameter("last_four_cardid"));
//		reqMap.put("identity_type", request.getParameter("identity_type"));
//		reqMap.put("identity_code", request.getParameter("identity_code"));
		//reqMap.put("card_holder", request.getParameter("card_holder"));
        log.info("reqMap...end.................");
		String data = umpInfoService.callBack(reqMap);
		context.put("data", data);
		return "/ump/callback";
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

    public void setUmpInfoService(UmpInfoService umpInfoService) {
        this.umpInfoService = umpInfoService;
    }
}

