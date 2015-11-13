package com.ec.api.service.utils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ec.api.common.utils.CookieUtils;
import com.ec.api.common.utils.JsonUtils;
import com.ec.api.domain.CartSku;
import com.ec.api.service.impl.CartServiceImpl;

public class CartUtils {
	private static final Logger log = LoggerFactory.getLogger(CartUtils.class);
	/**
	 * 从cookie中获取购物车商品列表
	 * @return
	 */
	public static Map<Integer, CartSku> getCartsMapByCookie(HttpServletRequest request){
		
		String cartInfoValue = CookieUtils.getCookieValue("cart", request);
		if(StringUtils.isBlank(cartInfoValue)){
			return null;
		}
		
		//购物车数据格式：
		//salesProperty 商品类目属性id1:值id1|商品类目属性id2:值id2
		//salesPropertyName 商品类目属性中文1:值中文1|商品类目属性中文2:值中文2
		//itemId-skuId-salesPropertyName-num-salesProperty_itemId-skuId-salesPropertyName-num-salesProperty
		
		try{
			String [] cartListStringArr = CartUtils.getCartsArrByCookie(request);
			Map<Integer, CartSku> cartMap = new HashMap<Integer, CartSku>();
			for(int i = 0 ; i < cartListStringArr.length ; i++){
				String [] cartInfoStringArr = cartListStringArr[i].split("-");
				Integer itemId = Integer.parseInt(cartInfoStringArr[0]);
				Integer skuId = Integer.parseInt(cartInfoStringArr[1]);
				String salesPropertyName = cartInfoStringArr[2];
				Integer num = Integer.parseInt(cartInfoStringArr[3]);
				
				//构建购物车数据
				CartSku cart = new CartSku();
				cart.setCreated(new Date());
				cart.setCreated(new Date());
				//商品属性中文
				cart.setSalesPropertyName(salesPropertyName);
				cart.setItemId(itemId);
				cart.setNum(num);
				cart.setSkuId(skuId);
				
				cartMap.put(skuId, cart);
			}
			return cartMap;
		}catch (Exception e) {
			log.error("从cookie中获取购物车商品异常", e);
		}
		
		return null;
	}

	/**
	 * 从cookie中获取购物车商品列表
	 * @return
	 */
	public static List<CartSku> getCartsListByCookie(HttpServletRequest request){
		
		String cartInfoValue = CookieUtils.getCookieValue("cart", request);
		if(StringUtils.isBlank(cartInfoValue)){
			return null;
		}
		
		//购物车数据格式：
		//salesProperty 商品类目属性id1:值id1|商品类目属性id2:值id2
		//salesPropertyName 商品类目属性中文1:值中文1|商品类目属性中文2:值中文2
		//itemId-skuId-salesPropertyName-num-salesProperty_itemId-skuId-salesPropertyName-num-salesProperty
		
		try{
			String [] cartListStringArr = CartUtils.getCartsArrByCookie(request);
			
			List<CartSku> cartList = new ArrayList<CartSku>();
			for(int i = 0 ; i < cartListStringArr.length ; i++){
				String [] cartInfoStringArr = cartListStringArr[i].split("-");
				Integer itemId = Integer.parseInt(cartInfoStringArr[0]);
				Integer skuId = Integer.parseInt(cartInfoStringArr[1]);
				String salesPropertyName = cartInfoStringArr[2];
				Integer num = Integer.parseInt(cartInfoStringArr[3]);
				
				
				//构建购物车数据
				CartSku cart = new CartSku();
				cart.setCreated(new Date());
				//商品属性中文
				cart.setSalesPropertyName(salesPropertyName);
				cart.setItemId(itemId);
				cart.setNum(num);
				cart.setSkuId(skuId);
				
				cartList.add(cart);
			}
			return cartList;
		}catch (Exception e) {
			log.error("从cookie中获取购物车商品异常", e);
		}
		
		return null;
	}
	
	public static void clearCookies(HttpServletResponse response){
		Cookie cookie = new Cookie("cart", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		cookie.setDomain(".binfenguoyuan.cn");

		response.addCookie(cookie);
	}
	
	public static void writeCarts2Cookie(Map<Integer, CartSku> cartMap, HttpServletResponse response){
		if(cartMap == null || cartMap.size() == 0){
			return;
		}
		
		//购物车数据格式：
		//salesProperty 商品类目属性id1:值id1|商品类目属性id2:值id2
		//salesPropertyName 商品类目属性中文1:值中文1|商品类目属性中文2:值中文2
		//itemId-skuId-salesPropertyName-num-salesProperty_itemId-skuId-salesPropertyName-num-salesProperty
		
		try{
			StringBuilder cookieValue = new StringBuilder();
			Set<Integer> keySet = cartMap.keySet();
			Iterator<Integer> it = keySet.iterator();
			while(it.hasNext()){
				CartSku cart = cartMap.get(it.next());
				
				cookieValue.append(cart.getItemId());
				cookieValue.append("-");
				cookieValue.append(cart.getSkuId());
				cookieValue.append("-");
				cookieValue.append(cart.getSalesPropertyName());
				cookieValue.append("-");
				cookieValue.append(cart.getNum());
				
				if(it.hasNext()){
					cookieValue.append("_");
				}
			}
			
			Cookie cookie = new Cookie("cart", URLEncoder.encode(cookieValue.toString() , "utf-8"));
			cookie.setMaxAge(60*60*24*30);
			cookie.setPath("/");
			cookie.setDomain(".binfenguoyuan.cn");

			response.addCookie(cookie);
		}catch (Exception e) {
			log.error("", e);
		}
	}
	
	public static String [] getCartsArrByCookie(HttpServletRequest request){
		String cartInfoValue = CookieUtils.getCookieValue("cart", request);
		if(StringUtils.isBlank(cartInfoValue)){
			return null;
		}
		
		//购物车数据格式：
		//salesProperty 商品类目属性id1:值id1|商品类目属性id2:值id2
		//salesPropertyName 商品类目属性中文1:值中文1|商品类目属性中文2:值中文2
		//itemId-skuId-salesPropertyName-num-salesProperty_itemId-skuId-salesPropertyName-num-salesProperty
		
		String [] cartListStringArr = cartInfoValue.split("_");
		if(cartListStringArr == null || cartListStringArr.length < 1){
			return null;
		}
		return cartListStringArr;
	}
}
