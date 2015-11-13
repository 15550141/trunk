package com.ec.api.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ec.api.domain.CartInfo;
import com.ec.api.domain.CartSku;
import com.ec.api.domain.PropertyValue;
import com.ec.api.service.result.Result;

public interface CartService {
	
	/**
	 * 添加购物车
	 */
	public Result addCartInfo(CartSku cart, HttpServletRequest request, HttpServletResponse response);
	/**
	 * 删除购物车
	 */
	public Result removeCartInfo(Integer uid, CartSku cartSku, HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 修改购物车
	 */
	public Result updateCartInfo(Integer uid, CartSku cart, HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 获取用户购物车数据
	 * @return
	 */
	public CartInfo getCartInfoByCookie(Integer userId, HttpServletRequest request);
	
	/**
	 * 获取购物车数量
	 * @param userId
	 * @param request
	 * @return
	 */
	public Result count(Integer userId, HttpServletRequest request);
	
}
