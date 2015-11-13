package com.ec.seller.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ec.seller.domain.Address;
import com.ec.seller.domain.Category;
import com.ec.seller.domain.query.AddressQuery;
import com.ec.seller.domain.query.CategoryQuery;
import com.ec.seller.service.AddressService;
import com.ec.seller.service.CategoryService;

@Controller
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressService addressService;
	private final static Log LOG = LogFactory.getLog(CategoryController.class);

	
//	@RequestMapping(value="/getLevelOne", method={ RequestMethod.GET, RequestMethod.POST })
//	public String queryPromotion(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
//
//		return "product/product";
//	}

	@RequestMapping(value="/getAddress", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> getAddress(Integer pId,HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Category category=new Category();
		try {	
			AddressQuery addressQuery=new AddressQuery();
			//设置查询几级分类
			if(pId!=null){
				addressQuery.setAddressFid(pId);
			}
			
			List<Address> addressList= addressService.selectByCondition(addressQuery);
			resultMap.put("msg","success");
			resultMap.put("addressList",addressList);//登录成功后，跳转到的页面
//			resultMap.put("category",category);//登录成功后，跳转到的页面
		} catch (Exception e) {
			LOG.error("AddressController.getAddress===", e);
		}
		return resultMap;
	}


	
}

