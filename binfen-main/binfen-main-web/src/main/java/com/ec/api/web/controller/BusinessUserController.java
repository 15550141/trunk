package com.ec.api.web.controller;

import com.ec.api.service.AddressService;
import com.ec.api.service.BusinessUserService;
import com.ec.api.service.result.Result;
import com.ec.api.web.base.BaseController;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: iboy
 * Date: 2014-9-22
 * Time: 17:28:09
 */
@Controller
@RequestMapping("/businessUser")
public class BusinessUserController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(BusinessUserController.class);
	private BusinessUserService businessUserService;


	@RequestMapping(value="getBusinessUserByUserId", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result getBusinessUserByUserId(Integer userId,HttpServletRequest request, HttpServletResponse response, ModelMap context){
        Result result = new Result();
		if( null == userId || userId < 1 ){
			result.setResultCode("1001");
			result.setResultMessage("userId不能为空");
			return result;
		}
        return businessUserService.getBusinessUserExtByUserID(userId);
	}

    public void setBusinessUserService(BusinessUserService businessUserService) {
        this.businessUserService = businessUserService;
    }
}
