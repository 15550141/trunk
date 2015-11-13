package com.ec.api.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ec.api.domain.PropertyValue;
import com.ec.api.service.CategoryService;
import com.ec.api.service.result.Result;
import com.ec.api.web.base.BaseController;

@Controller
@RequestMapping("/sell/category")
public class SellerCategoryController extends BaseController {
	private CategoryService categoryService;
	
	/**
	 * 根据属性ID和商家ID获取属性值列表
	 * @param propertyId
	 * @param request
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="getPropertyValuesByPropertyId", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result getPropertyValuesByPropertyId(Integer propertyId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(propertyId == null){
			Result result = new Result();
			result.setResultCode("1001");
			result.setResultMessage("propertyId不能为空");
			return result;
		}
		return categoryService.getPropertyValuesByVenderUserIdAndPropertyId(getUserId(request), propertyId);
	}
	
	/**
	 * 添加自定义属性值信息
	 * @param propertyValue
	 * @param request
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="addPropertyValue", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result addPropertyValue(PropertyValue propertyValue, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(propertyValue.getPropertyId() == null){
			result.setResultCode("1001");
			result.setResultMessage("propertyId不能为空");
			return result;
		}
		if(StringUtils.isBlank(propertyValue.getPropertyValueName())){
			result.setResultCode("1001");
			result.setResultMessage("propertyValueName不能为空");
			return result;
		}
		if(propertyValue.getSortNumber() == null){
			result.setResultCode("1001");
			result.setResultMessage("sortNumber不能为空");
			return result;
		}
		propertyValue.setVenderUserId(getUserId(request));
		propertyValue.setPropertyValueType(1);//商家自定义
		propertyValue.setCreated(new Date());
		
		return categoryService.addPropertyValue(propertyValue);
	}
	
	@RequestMapping(value="updatePropertyValue", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result updatePropertyValue(PropertyValue propertyValue, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(propertyValue.getPropertyValueId() == null){
			result.setResultCode("1001");
			result.setResultMessage("propertyValueId不能为空");
			return result;
		}
		propertyValue.setModified(new Date());
		return categoryService.updatePropertyValues(propertyValue);
	}
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	


}
