package com.ec.api.web.controller;

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

import com.ec.api.domain.Address;
import com.ec.api.service.AddressService;
import com.ec.api.service.result.Result;
import com.ec.api.web.base.BaseController;

@Controller
@RequestMapping("/address")
public class AddressController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(AddressController.class);
	
	private AddressService addressService;
	
	@RequestMapping(value="getArea", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<Address> getArea(Integer fid, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		return addressService.getArea(fid);
	}
	
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

}
