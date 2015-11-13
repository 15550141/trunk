package com.ec.api.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ec.api.common.utils.CookieUtils;
import com.ec.api.domain.Address;
import com.ec.api.domain.ReceiveAddr;
import com.ec.api.service.AddressService;
import com.ec.api.service.ReceiveAddrService;
import com.ec.api.service.result.Result;
import com.ec.api.web.base.BaseController;

@Controller
@RequestMapping("/receiveAddr")
public class ReceiveAddrController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(ReceiveAddrController.class);
	
	private ReceiveAddrService receiveAddrService;
	
	@RequestMapping(value="addrList", method={RequestMethod.GET, RequestMethod.POST})
	public String addrList(HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Integer uid = CookieUtils.getUid(request);
		List<ReceiveAddr> addrList = this.receiveAddrService.getAddrList(uid);
		context.put("addrList", addrList);
		return "receiveAddr/addrList";
	}
	
	@RequestMapping(value="addAddrPage", method={RequestMethod.GET, RequestMethod.POST})
	public String addAddrPage(HttpServletRequest request,HttpServletResponse response, ModelMap context){
		return "receiveAddr/addAddrPage";
	}
	
	@RequestMapping(value="delAddr", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result delAddr(Integer id, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Integer uid = CookieUtils.getUid(request);
		return this.receiveAddrService.delAddr(uid, id);
	}
	
	@RequestMapping(value="editAddr", method={RequestMethod.GET, RequestMethod.POST})
	public String editAddr(Integer id, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Integer uid = CookieUtils.getUid(request);
		context.put("addr", this.receiveAddrService.getReceiveAddr(uid, id));
		return "receiveAddr/editAddr";
	}

	@RequestMapping(value="updateAddr", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result updateAddr(String address, Integer province, Integer city, Integer county, Integer defaultFlag, String mobile, String name, Integer address_id, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Integer uid = CookieUtils.getUid(request);
		ReceiveAddr receiveAddr = new ReceiveAddr();
		receiveAddr.setAddress(address);
		receiveAddr.setCity(city);
		receiveAddr.setCounty(county);
		receiveAddr.setDefaultFlag(defaultFlag);
		receiveAddr.setMobile(mobile);
		receiveAddr.setName(name);
		receiveAddr.setProvince(province);
		receiveAddr.setUid(uid);
		receiveAddr.setId(address_id);
		
		return receiveAddrService.updateAddr(receiveAddr);
	}
	
	@RequestMapping(value="addAddr", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result addAddr(String address, Integer province, Integer city, Integer county, Integer defaultFlag, String mobile, String name, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Integer uid = CookieUtils.getUid(request);
		
		ReceiveAddr receiveAddr = new ReceiveAddr();
		receiveAddr.setAddress(address);
		receiveAddr.setCity(city);
		receiveAddr.setCounty(county);
		receiveAddr.setDefaultFlag(defaultFlag);
		receiveAddr.setMobile(mobile);
		receiveAddr.setName(name);
		receiveAddr.setProvince(province);
		receiveAddr.setUid(uid);
		
		return receiveAddrService.addAddr(receiveAddr);
	}
	
	@RequestMapping(value="choseAddr", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result choseAddr(Integer id, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Integer uid = CookieUtils.getUid(request);
		return receiveAddrService.choseAddr(uid, id);
	}
	
	public void setReceiveAddrService(ReceiveAddrService receiveAddrService) {
		this.receiveAddrService = receiveAddrService;
	}

	
}
