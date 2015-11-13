package com.ec.seller.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ec.seller.common.utils.CookieUtil;
import com.ec.seller.domain.Category;
import com.ec.seller.domain.Item;
import com.ec.seller.domain.ItemDescription;
import com.ec.seller.domain.ItemImage;
import com.ec.seller.domain.PromotionInfo;
import com.ec.seller.domain.Property;
import com.ec.seller.domain.PropertyValue;
import com.ec.seller.domain.Sku;
import com.ec.seller.domain.query.ItemQuery;
import com.ec.seller.domain.query.SkuQuery;
import com.ec.seller.service.CategoryService;
import com.ec.seller.service.ItemDescriptionService;
import com.ec.seller.service.ItemImageService;
import com.ec.seller.service.ItemService;
import com.ec.seller.service.PromotionInfoService;
import com.ec.seller.service.PropertyService;
import com.ec.seller.service.PropertyValueService;
import com.ec.seller.service.SkuService;

@Controller
@RequestMapping("/product")
public class Product {
	@Autowired
	private PromotionInfoService promotionInfoService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private SkuService skuService;
	@Autowired	
	private ItemImageService itemImageService;
	@Autowired
	private ItemDescriptionService itemDescriptionService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private PropertyValueService propertyValueService;
	
	
	
	private final static Log LOG = LogFactory.getLog(Product.class);

	
	@RequestMapping(value="/detail", method={ RequestMethod.GET, RequestMethod.POST })
	public String queryPromotion(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){

		return "product/product";
	}
	/**
	 * 编辑商品详情
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/edit", method={ RequestMethod.GET, RequestMethod.POST })
	public String edit(Integer itemId,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		try {
			//查询商品信息
			//itemId=49;
			Item item = itemService.selectByItemId(itemId);
			
			//查询分类ID对应的分类
			Category category1 = categoryService.selectByCategoryId(item.getCategoryId1());
			Category category2 = categoryService.selectByCategoryId(item.getCategoryId2());
			Category category3 = categoryService.selectByCategoryId(item.getCategoryId3());
			Category category4 = categoryService.selectByCategoryId(item.getCategoryId4());
			
			//查询sku信息
			SkuQuery skuQuery =new SkuQuery();
			skuQuery.setItemId(item.getItemId());
			skuQuery.setYn(1);
			List<Sku>skuList = skuService.selectByCondition(skuQuery);
			
			//查询商品图片信息
			List<ItemImage> itemImageList = itemImageService.selectByItemId(itemId);
			for(int i=0; i<itemImageList.size();i++){
				context.put("imageUrl"+(i+1), itemImageList.get(i).getImageUrl());
				context.put("imageUrlId"+(i+1), itemImageList.get(i).getId());
			}
			context.put("imageUrl0", item.getItemImage());

			
			//查询商品详情
			ItemDescription itemDescription =itemDescriptionService.selectByItemId(itemId);
			
			//商品无销售属性
			if(category4.getIfHaveSaleProperty()==0){
				context.put("tbPriceNoPro", skuList.get(0).getTbPrice());
				context.put("stockNoPro", skuList.get(0).getStock());
				context.put("minSaleNumNoPro", skuList.get(0).getLeastBuy());
				context.put("barCodeNoPro", skuList.get(0).getBarCode());
				
				context.put("proValIdList", "null");
				context.put("tbPriceList", "null");
				context.put("stockList", "null");
				context.put("leastBuyList", "null");
				context.put("barCodeList", "null");
				
			}else if(category4.getIfHaveSaleProperty() == 1){//有销售属性
				List<Integer> proValIdList = new ArrayList();
				List<Double> tbPriceList = new ArrayList();
				List<Integer> stockList = new ArrayList();
				List<Integer> leastBuyList = new ArrayList();
				List<String> barCodeList = new ArrayList();
				for(int i=0; i<skuList.size(); i++){
					Sku sku = skuList.get(i);
					String propert = sku.getSalesProperty();
					String proValIDString = propert.substring(
							propert.indexOf(":")+1, propert.indexOf("^"));
					proValIdList.add(Integer.parseInt(proValIDString));
					tbPriceList.add(sku.getTbPrice());
					stockList.add(sku.getStock());
					leastBuyList.add(sku.getLeastBuy());
					barCodeList.add(sku.getBarCode());
					
				}
				context.put("proValIdList", proValIdList);
				context.put("tbPriceList", tbPriceList);
				context.put("stockList", stockList);
				context.put("leastBuyList", leastBuyList);
				context.put("barCodeList", barCodeList);
				
			}
			context.put("item", item);
			context.put("category1", category1);
			context.put("category2", category2);
			context.put("category3", category3);
			context.put("category4", category4);
			context.put("skuList", skuList);
			context.put("itemImageList", itemImageList);
			context.put("itemDescription", itemDescription);
			

			
		} catch (Exception e) {
			// TODO: handle exception
		}


		return "product/editProduct";
	}
	/**
	 * 上架
	 * @param itemId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/startSale", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> startSale(Integer itemId,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Item item =new Item();
		item.setItemId(itemId);
		item.setVenderUserId(CookieUtil.getUserId(reuqest));
		item.setItemStatus(1);
		item.setOnShelfTime(new Date());
		try {
			itemService.modify(item);
		} catch (Exception e) {
			LOG.error("Product.startSale:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		//context.put("resultMap", resultMap);
		resultMap.put("msg","success");
		return resultMap;
	}
	/**
	 * 下架
	 * @param itemId
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/offSale", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> offSale(Integer itemId,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Item item =new Item();
		item.setItemId(itemId);
		item.setVenderUserId(CookieUtil.getUserId(reuqest));
		item.setItemStatus(2);//2下架
		item.setOffShelfTime(new Date());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			itemService.modify(item);
		} catch (Exception e) {
			LOG.error("Product.offSale:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		//context.put("resultMap", resultMap);
		resultMap.put("msg","success");
		return resultMap;
	}

	@RequestMapping("/list")
	public @ResponseBody Map<String, Object> list(PromotionInfo promotionInfo, Integer page, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {	
			promotionInfo.setPageIndex(page);
			resultMap = promotionInfoService.queryPromotionList(promotionInfo);
		} catch (Exception e) {
		}
		return resultMap;
	}

	/**
	 * 上架商品列表
	 * @param page
	 * @param itemQuery
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/onSaleProduct", method={ RequestMethod.GET, RequestMethod.POST })
	public String onSaleProduct(Integer page,ItemQuery itemQuery,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		String onShelfTimeString=reuqest.getParameter("onShelfTimeString");
		String offShelfTimeString=reuqest.getParameter("offShelfTimeString");

		
		itemQuery.setItemStatus(1);	//0：待售商品，1：上架 2：下架	
		itemQuery.setVenderUserId(CookieUtil.getUserId(reuqest));//设置商家ID
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(page==null){
				itemQuery.setPageNo(1);
			}else{
				itemQuery.setPageNo(page);
			}
			if(!StringUtils.isBlank(onShelfTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startTime = sdf.parse(onShelfTimeString);
				itemQuery.setOnShelfTime(startTime);
				
			}
			if(!StringUtils.isBlank(offShelfTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date endTime = sdf.parse(offShelfTimeString);
				itemQuery.setOffShelfTime(endTime);
			}
			resultMap = itemService.queryItemList(itemQuery);
		} catch (Exception e) {
			LOG.error("Product.onSaleProduct:", e);
		}
		context.put("resultMap", resultMap);
		return "product/onSaleProduct";
	}
	/**
	 * 待售商品列表
	 * @param page
	 * @param itemQuery
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/preSaleProduct", method={ RequestMethod.GET, RequestMethod.POST })
	public String preSaleProduct(Integer page, ItemQuery itemQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String onShelfTimeString=reuqest.getParameter("onShelfTimeString");
		String offShelfTimeString=reuqest.getParameter("offShelfTimeString");
		try {
			if(page==null){
				itemQuery.setPageNo(1);
			}else{
				itemQuery.setPageNo(page);
			}
			if(!StringUtils.isBlank(onShelfTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startTime = sdf.parse(onShelfTimeString);
				itemQuery.setOnShelfTime(startTime);
				
			}
			if(!StringUtils.isBlank(offShelfTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date endTime = sdf.parse(offShelfTimeString);
				itemQuery.setOffShelfTime(endTime);
			}
			
			itemQuery.setItemStatus(0);	//0：待售商品，1：上架 2：下架
			itemQuery.setVenderUserId(CookieUtil.getUserId(reuqest));//设置商家ID
			
		
			resultMap = itemService.queryItemList(itemQuery);
		} catch (Exception e) {
			LOG.error("Product.preSaleProduct:", e);
		}
		context.put("resultMap", resultMap);
		return "product/preSaleProduct";
	}
	/**
	 * 下架商品列表
	 * @param page
	 * @param itemQuery
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/stopSaleProduct", method={ RequestMethod.GET, RequestMethod.POST })
	public String stopSaleProduct(Integer page, ItemQuery itemQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String onShelfTimeString=reuqest.getParameter("onShelfTimeString");
		String offShelfTimeString=reuqest.getParameter("offShelfTimeString");
		
		itemQuery.setItemStatus(2);	//0：待售商品，1：上架 2：下架
		itemQuery.setVenderUserId(CookieUtil.getUserId(reuqest));//设置商家ID
		try {
			if(page==null){
				itemQuery.setPageNo(1);
			}else{
				itemQuery.setPageNo(page);
			}
			
			if(!StringUtils.isBlank(onShelfTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startTime = sdf.parse(onShelfTimeString);
				itemQuery.setOnShelfTime(startTime);
				
			}
			if(!StringUtils.isBlank(offShelfTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date endTime = sdf.parse(offShelfTimeString);
				itemQuery.setOffShelfTime(endTime);
			}
			
			resultMap = itemService.queryItemList(itemQuery);
		} catch (Exception e) {
			LOG.error("Product.stopSaleProduct:", e);
		}
		context.put("resultMap", resultMap);
		return "product/stopSaleProduct";
	}
	
	
	
	/**
	 * 修改商品
	 */
	@RequestMapping(value="/modifyProduct", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> modifyProduct(Item item,Integer hasPropertyInput, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//库存价格参数判断
		//无销售属性
		if(hasPropertyInput.equals(0)){
			String tbPriceNoPro= reuqest.getParameter("tbPriceNoPro");
			String stockNoPro= reuqest.getParameter("stockNoPro");
			String minSaleNumNoPro= reuqest.getParameter("minSaleNumNoPro");
			String barCodeNpPro= reuqest.getParameter("barCodeNpPro");
			if(StringUtils.isBlank(tbPriceNoPro)){
				resultMap.put("msg","priceNull");
				return resultMap;
			}
			if(StringUtils.isBlank(stockNoPro)){
				resultMap.put("msg","priceNull");
				return resultMap;
			}
			if(StringUtils.isBlank(minSaleNumNoPro)){
				resultMap.put("msg","priceNull");
				return resultMap;
			}
//			if(StringUtils.isBlank(barCodeNpPro)){
//				resultMap.put("msg","priceNull");
//				return resultMap;
//			}
			
		}else if(hasPropertyInput.equals(1)){
			//有销售属性 参数判断
			String[] tbPriceArray=reuqest.getParameterValues("tbPrice");
			String[] stockArray=reuqest.getParameterValues("stock");
			String[] leastBuyArray=reuqest.getParameterValues("leastBuy");
			String[] barCodeArray=reuqest.getParameterValues("barCode");
			String[] ifChooseArray=reuqest.getParameterValues("ifChoose");
			
			for(int i=0; i<ifChooseArray.length;i++){
				if(StringUtils.isBlank(ifChooseArray[i])||ifChooseArray[i].equals("0")){
					//不添加
				}else{
					if(StringUtils.isBlank(tbPriceArray[i])){
						resultMap.put("msg","priceNull");
						return resultMap;
					}
					if(StringUtils.isBlank(stockArray[i])){
						resultMap.put("msg","priceNull");
						return resultMap;
					}
					if(StringUtils.isBlank(leastBuyArray[i])){
						resultMap.put("msg","priceNull");
						return resultMap;
					}
					//暂时不判断条形码
//					if(StringUtils.isBlank(barCodeArray[i])){
//						resultMap.put("msg","priceNull");
//						return resultMap;
//					}
				}//esle end
			}//for end
			
		}//else if end
		
		
		//修改商品
		try {
			String itemImage=reuqest.getParameter("imageMainUrl");//主图URL
			item.setItemType(2);// 第三方商品为2
			item.setItemStatus(0);//新创建
			item.setYn(1);//待售商品为无效
			item.setItemImage(itemImage);
			item.setVenderUserId(CookieUtil.getUserId(reuqest));//设置商家ID
			itemService.modify(item);//修改商品
			//itemId = itemService.insert(item);
			LOG.error("修改商品ID成功："+item.getItemId());
		} catch (Exception e) {
			LOG.error("product.modifyProduct 修改商品:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		
		//修改细节图片（根据细节图ID修改）
		ItemImage itemImage=new ItemImage();
		String[] imageUrlArray=reuqest.getParameterValues("imageUrl");
		String[] imageUrlIdArray=reuqest.getParameterValues("imageUrlId");
		try {
			for(int i=0; i<imageUrlArray.length;i++){
				itemImage.setId(Integer.parseInt(imageUrlIdArray[i]));
				itemImage.setImageUrl(imageUrlArray[i]);
				itemImage.setItemId(item.getItemId());
				itemImage.setSortNumber(i);
				itemImage.setYn(1);
				itemImageService.modify(itemImage);//修改细节图
				LOG.error("修改商品细节图成功ID为："+itemImage.getId());
			}
		} catch (Exception e) {
			LOG.error("product.modifyProduct 修改细节图:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		
		//修改商品描述(根据 itemId修改)
		String pcDescriptionInfo= reuqest.getParameter("pcDescriptionInfo");
		String appDescriptionInfo= reuqest.getParameter("appDescriptionInfo");
		ItemDescription itemDescription=new ItemDescription();
		itemDescription.setAppDescriptionInfo(appDescriptionInfo);
		itemDescription.setPcDescriptionInfo(pcDescriptionInfo);
		itemDescription.setItemId(item.getItemId());
		try {
			itemDescriptionService.modify(itemDescription);
		} catch (Exception e) {
			LOG.error("product.modifyProduct 修改商品描述:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		

		
		Sku sku=new Sku();
		//无销售属性(根据itemId修改SKU信息)
		if(hasPropertyInput.equals(0)){
			String tbPriceNoPro= reuqest.getParameter("tbPriceNoPro");
			String stockNoPro= reuqest.getParameter("stockNoPro");
			String minSaleNumNoPro= reuqest.getParameter("minSaleNumNoPro");
			String barCodeNpPro= reuqest.getParameter("barCodeNpPro");
			sku.setItemId(item.getItemId());
			sku.setTbPrice(Double.parseDouble(tbPriceNoPro));
			sku.setStock(Integer.parseInt(stockNoPro));
			sku.setLeastBuy(Integer.parseInt(minSaleNumNoPro));
			sku.setBarCode(barCodeNpPro);
			sku.setYn(1);//插入即是有效
			//Integer skuId = skuService.insert(sku);//插入sku	
			skuService.modifyByItemId(sku);
			LOG.error("修改SKU成功ItemId："+item.getItemId());
			resultMap.put("msg","success");
			return resultMap;
		}
		
		//如果有销售属性（根据ItemId和销售属性修改）
		
		//现将所有的sku设为无效，后续进行插入更新操作（选中的更新为有效，新选中的新建插入）
		Sku sku2= new Sku();
		sku2.setItemId(item.getItemId());
		sku2.setYn(0);
		skuService.modifyByItemId(sku2);
		
		
		String  salesPropertyItem="";//item中的销售属性
		String[] tbPriceArray=reuqest.getParameterValues("tbPrice");
		String[] stockArray=reuqest.getParameterValues("stock");
		String[] leastBuyArray=reuqest.getParameterValues("leastBuy");
		String[] barCodeArray=reuqest.getParameterValues("barCode");
		
		String[] propertyIdArray=reuqest.getParameterValues("propertyId");
		String[] propertyValueIdArray=reuqest.getParameterValues("propertyValueId");
		String[] ifChooseArray=reuqest.getParameterValues("ifChoose");
		
		
		salesPropertyItem=propertyIdArray[0]+":";
		
		for(int i=0; i<ifChooseArray.length;i++){
			if(StringUtils.isBlank(ifChooseArray[i])||ifChooseArray[i].equals("0")){
				//不添加
			}else{//ifChoose为1时表示已选
				sku.setItemId(item.getItemId());
				sku.setTbPrice(Double.parseDouble(tbPriceArray[i]));
				sku.setStock(Integer.parseInt(stockArray[i]));
				sku.setLeastBuy(Integer.parseInt(leastBuyArray[i]));
				sku.setBarCode(barCodeArray[i]);
				sku.setYn(1);//插入即是有效
				String  salesProperty=propertyIdArray[i]+":"+propertyValueIdArray[i]+"^";
				//查询属性
				Property property = propertyService.selectByPropertyId(Integer.parseInt(propertyIdArray[i]));
				//查询属性值名称
				PropertyValue propertyValue = propertyValueService.selectByPropertyValueId(Integer.parseInt(propertyValueIdArray[i]));
				String salesPropertyName=property.getPropertyName()+":"+propertyValue.getPropertyValueName()+"^";
				sku.setSalesPropertyName(salesPropertyName);
				salesPropertyItem=salesPropertyItem+propertyValueIdArray[i]+",";//item中的属性值ID
				sku.setSalesProperty(salesProperty);
				skuService.insertOrUpdate(sku);
				//Integer skuId = skuService.insert(sku);//插入sku	
				//LOG.error("生成SKU成功："+skuId);
			}
		}
		Item item2=new Item();
		item2.setItemId(item.getItemId());
		item2.setSalesPropertySet(salesPropertyItem+"^");
		try {
			itemService.modify(item2);
		} catch (Exception e) {
			LOG.error("更新Item销售属性异常", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		context.put("item", item);
		resultMap.put("msg","success");
		return resultMap;
	}
	
	
	
	/**
	 * 添加商品，成功跳转到预售商品列表
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/addProduct", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addProduct(Item item,Integer hasPropertyInput,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//库存价格参数判断
		//无销售属性
		if(hasPropertyInput.equals(0)){
			String tbPriceNoPro= reuqest.getParameter("tbPriceNoPro");
			String stockNoPro= reuqest.getParameter("stockNoPro");
			String minSaleNumNoPro= reuqest.getParameter("minSaleNumNoPro");
			String barCodeNpPro= reuqest.getParameter("barCodeNpPro");
			if(StringUtils.isBlank(tbPriceNoPro)){
				resultMap.put("msg","priceNull");
				return resultMap;
			}
			if(StringUtils.isBlank(stockNoPro)){
				resultMap.put("msg","priceNull");
				return resultMap;
			}
			if(StringUtils.isBlank(minSaleNumNoPro)){
				resultMap.put("msg","priceNull");
				return resultMap;
			}
//			if(StringUtils.isBlank(barCodeNpPro)){
//				resultMap.put("msg","priceNull");
//				return resultMap;
//			}
			
		}else if(hasPropertyInput.equals(1)){
			//有销售属性 参数判断
			String[] tbPriceArray=reuqest.getParameterValues("tbPrice");
			String[] stockArray=reuqest.getParameterValues("stock");
			String[] leastBuyArray=reuqest.getParameterValues("leastBuy");
			String[] barCodeArray=reuqest.getParameterValues("barCode");
			String[] ifChooseArray=reuqest.getParameterValues("ifChoose");
			//如果没有添加销售属性，该ifChoose数组为空
			if(ifChooseArray==null){
				resultMap.put("msg","nullProperty");
				return resultMap;
			}
			int flag=0;//判断是否选择了销售属性值 0没有选择 1选择了至少一项
			for(int i=0; i<ifChooseArray.length;i++){
				if(StringUtils.isBlank(ifChooseArray[i])||ifChooseArray[i].equals("0")){
					//不添加
				}else{
					flag=1;
					if(StringUtils.isBlank(tbPriceArray[i])){
						resultMap.put("msg","priceNull");
						return resultMap;
					}
					if(StringUtils.isBlank(stockArray[i])){
						resultMap.put("msg","priceNull");
						return resultMap;
					}
					if(StringUtils.isBlank(leastBuyArray[i])){
						resultMap.put("msg","priceNull");
						return resultMap;
					}
//					if(StringUtils.isBlank(barCodeArray[i])){
//						resultMap.put("msg","priceNull");
//						return resultMap;
//					}
				}//esle end
			}//for end
			if(flag==0){
				resultMap.put("msg","nullSelectProperty");
				return resultMap;
			}
			
		}//else if end
		
		
		
		
		Integer itemId=null;	
		String marketStartTimeString=reuqest.getParameter("marketStartTimeString");
		String marketEndTimeString=reuqest.getParameter("marketEndTimeString");
		String autoOnShelfTimeString=reuqest.getParameter("autoOnShelfTimeString");
		String autoOffShelfTimeString=reuqest.getParameter("autoOffShelfTimeString");
		
		
		try {
			LOG.error("开始添加商品！");
			
			if(!StringUtils.isBlank(marketStartTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date marketStartTime = sdf.parse(marketStartTimeString);
				item.setMarketStartTime(marketStartTime);
			}
			
			if(!StringUtils.isBlank(marketEndTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date marketEndTime = sdf.parse(marketEndTimeString);
				item.setMarketEndTime(marketEndTime);
			}
			if(!StringUtils.isBlank(autoOnShelfTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date autoOnShelfTime = sdf.parse(autoOnShelfTimeString);
				item.setAutoOnShelfTime(autoOnShelfTime);
			}
			if(!StringUtils.isBlank(autoOffShelfTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date autoOffShelfTime = sdf.parse(autoOffShelfTimeString);
				item.setAutoOffShelfTime(autoOffShelfTime);
			}
			String itemImage=reuqest.getParameter("imageMainUrl");//主图URL
			item.setItemType(2);// 第三方商品为2
			// TODO通过取cooke获取商家ID
			item.setVenderUserId(1);
			item.setItemStatus(0);//新创建
			item.setYn(0);//待售商品为无效
			item.setItemImage(itemImage);
			item.setVenderUserId(CookieUtil.getUserId(reuqest));//设置商家ID
			itemId = itemService.insert(item);
			LOG.error("生成商品ID成功："+itemId);
		} catch (Exception e) {
			LOG.error("product.addProduct:", e);
			resultMap.put("msg","error");
		}
		
		//细节图片地址入库
		ItemImage itemImage=new ItemImage();
		String[] imageUrlArray=reuqest.getParameterValues("imageUrl");
		try {
			for(int i=0; i<imageUrlArray.length;i++){
				itemImage.setImageUrl(imageUrlArray[i]);
				itemImage.setItemId(itemId);
				itemImage.setSortNumber(i);
				itemImage.setYn(1);
				itemImageService.insert(itemImage);
				
			}
		} catch (Exception e) {
			LOG.error("product.addProduct:", e);
			resultMap.put("msg","error");
		}
		
		//商品描述入库
		String pcDescriptionInfo= reuqest.getParameter("pcDescriptionInfo");
		String appDescriptionInfo= reuqest.getParameter("appDescriptionInfo");
		ItemDescription itemDescription=new ItemDescription();
		itemDescription.setAppDescriptionInfo(appDescriptionInfo);
		itemDescription.setPcDescriptionInfo(pcDescriptionInfo);
		itemDescription.setItemId(itemId);
		try {
			itemDescriptionService.insert(itemDescription);
		} catch (Exception e) {
			LOG.error("product.addProduct:", e);
			resultMap.put("msg","error");
		}
		

		
		Sku sku=new Sku();
		//无销售属性
		if(hasPropertyInput.equals(0)){
			String tbPriceNoPro= reuqest.getParameter("tbPriceNoPro");
			String stockNoPro= reuqest.getParameter("stockNoPro");
			String minSaleNumNoPro= reuqest.getParameter("minSaleNumNoPro");
			String barCodeNpPro= reuqest.getParameter("barCodeNpPro");
			sku.setItemId(itemId);
			sku.setTbPrice(Double.parseDouble(tbPriceNoPro));
			sku.setStock(Integer.parseInt(stockNoPro));
			sku.setLeastBuy(Integer.parseInt(minSaleNumNoPro));
			sku.setBarCode(barCodeNpPro);
			sku.setYn(1);//插入即是有效
			Integer skuId = skuService.insert(sku);//插入sku	
			LOG.error("生成SKU成功："+skuId);
			resultMap.put("msg","success");
			return resultMap;
		}
		
		//如果有销售属性
		
		String  salesPropertyItem="";//item中的销售属性
		String[] tbPriceArray=reuqest.getParameterValues("tbPrice");
		String[] stockArray=reuqest.getParameterValues("stock");
		String[] leastBuyArray=reuqest.getParameterValues("leastBuy");
		String[] barCodeArray=reuqest.getParameterValues("barCode");
		
		String[] propertyIdArray=reuqest.getParameterValues("propertyId");
		String[] propertyValueIdArray=reuqest.getParameterValues("propertyValueId");
		String[] ifChooseArray=reuqest.getParameterValues("ifChoose");
		
		
		salesPropertyItem=propertyIdArray[0]+":";
		
		for(int i=0; i<ifChooseArray.length;i++){
			if(StringUtils.isBlank(ifChooseArray[i])||ifChooseArray[i].equals("0")){
				//不添加
			}else{
				sku.setItemId(itemId);
				sku.setTbPrice(Double.parseDouble(tbPriceArray[i]));
				sku.setStock(Integer.parseInt(stockArray[i]));
				sku.setLeastBuy(Integer.parseInt(leastBuyArray[i]));
				sku.setBarCode(barCodeArray[i]);
				sku.setYn(1);//插入即是有效
				String  salesProperty=propertyIdArray[i]+":"+propertyValueIdArray[i]+"^";
				//查询属性
				Property property = propertyService.selectByPropertyId(Integer.parseInt(propertyIdArray[i]));
				//查询属性值名称
				PropertyValue propertyValue = propertyValueService.selectByPropertyValueId(Integer.parseInt(propertyValueIdArray[i]));
				String salesPropertyName=property.getPropertyName()+":"+propertyValue.getPropertyValueName()+"^";
				sku.setSalesPropertyName(salesPropertyName);
				salesPropertyItem=salesPropertyItem+propertyValueIdArray[i]+",";//item中的属性值ID
				sku.setSalesProperty(salesProperty);
				Integer skuId = skuService.insert(sku);//插入sku	
				LOG.error("生成SKU成功："+skuId);
			}
		}
		Item item2=new Item();
		item2.setItemId(itemId);
		item2.setSalesPropertySet(salesPropertyItem+"^");
		try {
			itemService.modify(item2);
		} catch (Exception e) {
			LOG.error("更新Item销售属性异常", e);
			resultMap.put("msg","error");
		}
		context.put("item", item);
		resultMap.put("msg","success");
		//resultMap.put("propertyValue",propertyValue);
		
		return resultMap;
	}
	
	
	
}

