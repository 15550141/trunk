package com.ec.api.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ec.api.common.utils.CookieUtils;
import com.ec.api.dao.CategoryDao;
import com.ec.api.dao.ItemDao;
import com.ec.api.dao.PropertyDao;
import com.ec.api.dao.PropertyValueDao;
import com.ec.api.domain.CartInfo;
import com.ec.api.domain.CartSku;
import com.ec.api.domain.Category;
import com.ec.api.domain.Item;
import com.ec.api.domain.Property;
import com.ec.api.domain.PropertyValue;
import com.ec.api.domain.Sku;
import com.ec.api.domain.query.CategoryQuery;
import com.ec.api.domain.query.PropertyQuery;
import com.ec.api.domain.query.PropertyValueQuery;
import com.ec.api.service.CartService;
import com.ec.api.service.CategoryService;
import com.ec.api.service.ItemService;
import com.ec.api.service.OrderInfoService;
import com.ec.api.service.SkuService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.CartUtils;
import com.ec.api.service.utils.EcUtils;

@Service(value="cartService")
public class CartServiceImpl implements CartService {
	
	private SkuService skuService;
	
	private ItemService itemService;
	
	private OrderInfoService orderInfoService;
	
	private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

	@Override
	public Result addCartInfo(CartSku cart, HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result();
		//TODO 校验商品的合法性
		if(cart == null || cart.getNum() == null || cart.getItemId() == null || cart.getSalesPropertyName() == null || cart.getSkuId() == null){
			result.setSuccess(false);
			result.setResultMessage("参数不正确！");
			return result;
		}
		
		//判断cookie中是否存在
		Map<Integer, CartSku> cartMap = CartUtils.getCartsMapByCookie(request);
		if(cartMap == null){
			cartMap = new HashMap<Integer, CartSku>();
		}
		Integer skuId = cart.getSkuId();
		if(cartMap.containsKey(skuId)){
			CartSku cookieCart = cartMap.get(skuId);
			cart.setNum(cookieCart.getNum() + cart.getNum());
		}
		
		cartMap.put(skuId, cart);
		
		CartUtils.writeCarts2Cookie(cartMap, response);
		
		result.setSuccess(true);
		return result;
	}

	@Override
	public Result removeCartInfo(Integer uid, CartSku cartSku, HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result();
		//TODO 校验商品的合法性
		if(cartSku == null || cartSku.getItemId() == null || cartSku.getSkuId() == null){
			result.setSuccess(false);
			result.setResultMessage("参数不正确！");
			return result;
		}
		
		//判断cookie中是否存在
		Map<Integer, CartSku> cartMap = CartUtils.getCartsMapByCookie(request);
		if(cartMap == null){
			cartMap = new HashMap<Integer, CartSku>();
		}
		
		cartMap.remove(cartSku.getSkuId());
		
		if(cartMap == null || cartMap.size() == 0){
			CartUtils.clearCookies(response);
			result.setSuccess(true);
			return result;
		}
		
		CartInfo cartInfo = this.AssemblyCartInfo(uid, cartMap);
		
		if(cartInfo != null){
			//更新cookie
			CartUtils.writeCarts2Cookie(cartMap, response);
			result.setSuccess(true);
			result.setResult(cartInfo);
		}else{
			result.setSuccess(false);
			result.setResultMessage("网络繁忙，请重试");
		}
		
		return result;
	}

	@Override
	public Result updateCartInfo(Integer uid, CartSku cartSku, HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result();
		//TODO 校验商品的合法性
		if(cartSku == null || cartSku.getNum() == null || cartSku.getItemId() == null || cartSku.getSalesPropertyName() == null || cartSku.getSkuId() == null){
			result.setSuccess(false);
			result.setResultMessage("参数不正确！");
			return result;
		}
		
		//判断cookie中是否存在
		Map<Integer, CartSku> cartMap = CartUtils.getCartsMapByCookie(request);
		if(cartMap == null){
			cartMap = new HashMap<Integer, CartSku>();
		}
		
		cartMap.put(cartSku.getSkuId(), cartSku);
		
		CartInfo cartInfo = this.AssemblyCartInfo(uid, cartMap);
		
		if(cartInfo != null){
			//更新cookie
			CartUtils.writeCarts2Cookie(cartMap, response);
			result.setSuccess(true);
			result.setResult(cartInfo);
		}else{
			result.setSuccess(false);
			result.setResultMessage("网络繁忙，请重试");
		}
		
		return result;
	}

	@Override
	public CartInfo getCartInfoByCookie(Integer userId, HttpServletRequest request) {
		//判断cookie中是否存在
		List<CartSku> cartSkuList = CartUtils.getCartsListByCookie(request);
		if(cartSkuList == null || cartSkuList.size() == 0){
			return null;
		}
		
		return this.AssemblyCartInfo(userId, cartSkuList);
	}
	
	private CartInfo AssemblyCartInfo(Integer uid, Map<Integer, CartSku> cartSkuMap){
		if(cartSkuMap == null || cartSkuMap.size() == 0){
			return null;
		}
		List<CartSku> cartSkuList = new ArrayList<CartSku>();
		
		for(Map.Entry<Integer, CartSku> entry: cartSkuMap.entrySet()) {
			cartSkuList.add(entry.getValue());
		}
		return this.AssemblyCartInfo(uid, cartSkuList);
	}
	
	private CartInfo AssemblyCartInfo(Integer uid, List<CartSku> cartSkuList){
		if(cartSkuList == null){
			return null;
		}
		
		CartInfo cartInfo = new CartInfo();
		cartInfo.setUid(uid);
		cartInfo.setSkuList(cartSkuList);
		
		List<Item> itemList = new ArrayList<Item>();
		try{
			//销售价格
			BigDecimal totleSalePrice = new BigDecimal(0);
			//原价
			BigDecimal totleOriginalPrice = new BigDecimal(0);
			//运费
			BigDecimal freightMoney = new BigDecimal(0);
			
			for(int i = 0 ; i < cartSkuList.size() ; i ++){
				CartSku cart = cartSkuList.get(i);
				
				Item item = null;
				for(int m = 0 ; m < itemList.size() ; m++){
					if(cart.getItemId().equals(itemList.get(m).getItemId())){
						item = itemList.get(m);
						break;
					}
				}
				
				if(item == null){
					item = itemService.getItemAndSkusByItemId(cart.getItemId());
					itemList.add(item);
				}
				
				if(item == null){
					throw new RuntimeException(cart.getItemId() + "商品不存在");
				}
				
				List<Sku> skuList = item.getSkuList();
				
				for(int j = 0 ; j < skuList.size() ; j ++){
					Sku sku = skuList.get(j);
					if(cart.getSkuId().equals(sku.getSkuId())){
						cart.setImage(item.getItemImage());
						cart.setSalesProperty(sku.getSalesProperty());
						cart.setSalesPropertyName(sku.getSalesPropertyName());
						cart.setName(item.getItemName());
						cart.setSkuPrice(new BigDecimal(sku.getSalePrice()).divide(new BigDecimal(100)));
						//累计购物车总金额
						totleSalePrice = totleSalePrice.add(cart.getSkuPrice().multiply(new BigDecimal(cart.getNum())));
						//累计购物车总原价金额
						if(sku.getOriginalPrice() != null){
							totleOriginalPrice = totleOriginalPrice.add((new BigDecimal(sku.getOriginalPrice()).divide(new BigDecimal(100))).multiply(new BigDecimal(cart.getNum())));
						}
						break;
					}
				}
			}
			
			//满赠逻辑
			if(totleSalePrice.compareTo(new BigDecimal(50))>= 0){
				CartSku cartSku = new CartSku();
				cartSku.setImage("/misc/style/image/2015102302.jpg");
				cartSku.setItemId(100019);
				cartSku.setName("越南白心火龙果");
				cartSku.setNum(1);
				cartSku.setGift(true);
				cartSku.setSalesPropertyName("规格：1个(约700g)");
				cartSku.setSkuId(100019);
				cartSku.setSkuPrice(new BigDecimal(0));
				
				cartInfo.getSkuList().add(cartSku);
			}
			//满赠逻辑结束
			
			//TODO 满减逻辑	满19块钱减5块
//			Integer count = orderInfoService.getEffectiveOrderCount(cartInfo.getUid());
//			if(count != null && count == 0){
//				cartInfo.setFirst(true);
//			}
//			if(totleSalePrice.compareTo(new BigDecimal(19))>= 0 && cartInfo.isFirst()){
//				totleSalePrice = totleSalePrice.subtract(new BigDecimal(5));
//			}
			//满减逻辑结束
			
			//运费逻辑开始
			if(totleSalePrice.compareTo(new BigDecimal(39)) < 0){
				freightMoney = new BigDecimal(4);//不满39，添加4元运费
				totleSalePrice = totleSalePrice.add(freightMoney);//销售价加上运费
			}
			//运费逻辑结束
			
			//直接清空对象，释放内存
			itemList = null;
			cartInfo.setCartSkusCount(cartSkuList.size());
			//设置运费价格
			cartInfo.setFreightMoney(freightMoney);
			//商品总价
			cartInfo.setTotleOriginalPrice(totleOriginalPrice);
			//总销售金额
			cartInfo.setTotleSalePrice(totleSalePrice);
			//总优惠金额	= 总商品销售价格 + 运费 - 最终订单价格
			cartInfo.setTotlePreferentialPrice(cartInfo.getTotleOriginalPrice().add(freightMoney).subtract(cartInfo.getTotleSalePrice()));
		}catch (Exception e) {
			log.error("", e);
			return null;
		}
		return cartInfo;
	}


	@Override
	public Result count(Integer userId, HttpServletRequest request) {
		Result result = new Result();
		result.setSuccess(true);
		Integer count = 0;
		String cartValue = CookieUtils.getCookieValue("cart", request);
		if(StringUtils.isNotBlank(cartValue)){
			count = cartValue.split("_").length;
		}
		result.setResult(count);
		return result;
	}


	public void setSkuService(SkuService skuService) {
		this.skuService = skuService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}
	
}
