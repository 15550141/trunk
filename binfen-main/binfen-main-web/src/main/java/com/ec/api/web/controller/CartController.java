package com.ec.api.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ec.api.common.utils.CookieUtils;
import com.ec.api.common.utils.JsonUtils;
import com.ec.api.domain.CartInfo;
import com.ec.api.domain.CartSku;
import com.ec.api.service.CartService;
import com.ec.api.service.CategoryService;
import com.ec.api.service.result.Result;
import com.ec.api.web.base.BaseController;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {
	
	private CartService cartService;
	
	@RequestMapping(value="", method={RequestMethod.GET, RequestMethod.POST})
	public String index(HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Integer uid = CookieUtils.getUid(request);
		CartInfo cartInfo = cartService.getCartInfoByCookie(uid, request);
		
		context.put("cartInfo", cartInfo);
		return "cart/cart";
	}
	
	@RequestMapping(value="add", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result add(Integer skuId, Integer num, Integer itemId, String salesProperty, String salesPropertyName, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		CartSku cartSku = new CartSku();
		
		cartSku.setSalesPropertyName(salesPropertyName);
		cartSku.setSalesProperty(salesProperty);
		
		cartSku.setItemId(itemId);
		cartSku.setNum(num);
		cartSku.setSkuId(skuId);
		
		return cartService.addCartInfo(cartSku, request, response);
	}
	
	@RequestMapping(value="update", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result update(Integer skuId, Integer num, Integer itemId, String salesProperty, String salesPropertyName,  HttpServletRequest request,HttpServletResponse response, ModelMap context){
		
		CartSku cartSku = new CartSku();
		
		cartSku.setSalesPropertyName(salesPropertyName);
		cartSku.setSalesProperty(salesProperty);
		
		cartSku.setItemId(itemId);
		cartSku.setNum(num);
		cartSku.setSkuId(skuId);
		
		Integer uid = CookieUtils.getUid(request);
		
		return cartService.updateCartInfo(uid, cartSku, request, response);
	}
	
	@RequestMapping(value="remove", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result remove(Integer skuId, Integer itemId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		CartSku cartSku = new CartSku();
		cartSku.setItemId(itemId);
		cartSku.setSkuId(skuId);
		
		Integer uid = CookieUtils.getUid(request);
		
		return cartService.removeCartInfo(uid, cartSku, request, response);
	}
	
	@RequestMapping(value="count", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result count(Integer skuId, Integer itemId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Integer userId = CookieUtils.getUid(request);
		return cartService.count(userId, request);
	}
	
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}


}
