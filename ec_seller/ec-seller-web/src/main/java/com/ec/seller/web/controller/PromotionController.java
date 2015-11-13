package com.ec.seller.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.expr.NewArray;

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
import com.ec.seller.domain.Item;
import com.ec.seller.domain.PromotionInfo;
import com.ec.seller.domain.PromotionProduct;
import com.ec.seller.domain.PromotionProductSku;
import com.ec.seller.domain.PromotionSku;
import com.ec.seller.domain.Sku;
import com.ec.seller.domain.query.PromotionProductQuery;
import com.ec.seller.domain.query.PromotionSkuQuery;
import com.ec.seller.domain.query.SkuQuery;
import com.ec.seller.manager.impl.PromotionInfoManagerImpl;
import com.ec.seller.service.ItemService;
import com.ec.seller.service.PromotionInfoService;
import com.ec.seller.service.PromotionProductService;
import com.ec.seller.service.PromotionSkuService;
import com.ec.seller.service.SkuService;




@Controller
@RequestMapping("/promotion")
public class PromotionController {
	@Autowired
	private PromotionInfoService promotionInfoService;
	@Autowired
	private PromotionProductService promotionProductService;
	@Autowired
	private PromotionSkuService promotionSkuService;
	@Autowired
	private SkuService skuService;
	@Autowired
	private ItemService itemService;
	
	
	private final static Log LOG = LogFactory.getLog(PromotionController.class);

	
	
	/**
	 * 促销上线
	 */
	@RequestMapping(value="/start", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> start(Integer promotionId,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		PromotionInfo promotionInfo= new PromotionInfo();
		promotionInfo.setPromotionId(promotionId);
		promotionInfo.setPromotionStatus(1);
		promotionInfo.setStartTime(new Date());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			promotionInfoService.modify(promotionInfo);
			PromotionProduct promotionProduct = new PromotionProduct();
			promotionProduct.setPromotionId(promotionId);
			promotionProduct.setPromotionStatus(1);
			promotionProduct.setStartTime(new Date());
			promotionProductService.modifyByPromtionId(promotionProduct);
			
			//itemService.modify(item);
		} catch (Exception e) {
			LOG.error("promotion.start:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	/**
	 * 促销下线
	 */
	@RequestMapping(value="/end", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> end(Integer promotionId,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Item item =new Item();
		PromotionInfo promotionInfo= new PromotionInfo();
		promotionInfo.setPromotionId(promotionId);
		promotionInfo.setPromotionStatus(2);
		promotionInfo.setEndTime(new Date());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			promotionInfoService.modify(promotionInfo);
			
			PromotionProduct promotionProduct = new PromotionProduct();
			promotionProduct.setPromotionId(promotionId);
			promotionProduct.setPromotionStatus(2);
			promotionProduct.setEndTime(new Date());
			promotionProductService.modifyByPromtionId(promotionProduct);
			//itemService.modify(item);
		} catch (Exception e) {
			LOG.error("promotion.start:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	/**
	 * 删除促销
	 */
	@RequestMapping(value="/delete", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> delete(Integer promotionId,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		PromotionInfo promotionInfo= new PromotionInfo();
		promotionInfo.setPromotionId(promotionId);
		promotionInfo.setPromotionStatus(2);
		promotionInfo.setYn(0);//设为无效
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			promotionInfoService.modify(promotionInfo);
			
			PromotionProduct promotionProduct = new PromotionProduct();
			promotionProduct.setPromotionId(promotionId);
			promotionProduct.setPromotionStatus(1);
			promotionProduct.setYn(0);
			promotionProductService.modifyByPromtionId(promotionProduct);
			//itemService.modify(item);
		} catch (Exception e) {
			LOG.error("promotion.delete:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		//context.put("resultMap", resultMap);
		resultMap.put("msg","success");
		return resultMap;
	}
	
	@RequestMapping(value="/queryPromotion", method={ RequestMethod.GET, RequestMethod.POST })
	public String queryPromotion(PromotionInfo promotionInfo, String startTimeString, String endTimeString,Integer page, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = null;

		try {	

			if(page==null){
				promotionInfo.setPageIndex(1);
			}else{
				promotionInfo.setPageIndex(page);
			}
			if(!StringUtils.isBlank(startTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startTime = sdf.parse(startTimeString);
				promotionInfo.setStartTime(startTime);
			}
			if(!StringUtils.isBlank(endTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date endTime = sdf.parse(endTimeString);
				promotionInfo.setEndTime(endTime);
			}
			if(StringUtils.isBlank(promotionInfo.getPromotionName())){
				promotionInfo.setPromotionName(null);
			}
			promotionInfo.setVenderUserId(CookieUtil.getUserId(reuqest));//设置商家ID
			resultMap = promotionInfoService.queryPromotionList(promotionInfo);
			
		} catch (Exception e) {
			LOG.error("controller.queryPromotion=======",e);
		}
		context.put("resultMap", resultMap);
		return "promotion/queryPromotion";
	}
	
	
	/**
	 * 添加促销
	 * @return
	 */
	@RequestMapping(value="/addPromotion", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addPromotion(PromotionInfo promotionInfo, String startTimeString, String endTimeString, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Integer promotionId;
		PromotionProduct promotionProduct=new PromotionProduct();
		
		String[] skuIdArray=reuqest.getParameterValues("skuId");
		String[] deductionPriceArray=reuqest.getParameterValues("deductionPrice");
		String[] itemIdHidArray=reuqest.getParameterValues("itemIdHid");
		
		for(int i=0; i<skuIdArray.length;i++){
			if(StringUtils.isBlank(deductionPriceArray[i])){
				resultMap.put("msg","deductionNull");
				return resultMap;
			}
		}
		
		
		try {
			
			if(!StringUtils.isBlank(startTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startTime = sdf.parse(startTimeString);
				promotionInfo.setStartTime(startTime);
				promotionProduct.setStartTime(startTime);
			}
			if(!StringUtils.isBlank(endTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date endTime = sdf.parse(endTimeString);
				promotionInfo.setEndTime(endTime);
				promotionProduct.setEndTime(endTime);
			}
			if(StringUtils.isBlank(promotionInfo.getPromotionName())){
				promotionInfo.setPromotionName(null);
			}
			promotionInfo.setPromotionStatus(0);
			promotionInfo.setDiscountFlag(0);//单品促销推荐到首页未开启
			promotionInfo.setSpecialFlag(0);//产地特供推荐到首页未开启
			promotionInfo.setVenderUserId(CookieUtil.getUserId(reuqest));//设置商家ID
			
			
			promotionId=promotionInfoService.insert(promotionInfo);
			
			
			promotionProduct.setPromotionStatus(0);
			promotionProduct.setPromotionType(promotionInfo.getPromotionType());
			promotionProduct.setDiscountFlag(0);
			promotionProduct.setSpecialFlag(0);
			promotionProduct.setVenderUserId(CookieUtil.getUserId(reuqest));
			promotionProduct.setPromotionId(promotionId);
			promotionProduct.setPromotionName(promotionInfo.getPromotionName());
			promotionProduct.setPurchaseNumberMax(promotionInfo.getPurchaseNumberMax());
			promotionProduct.setPurchaseNumberMin(promotionInfo.getPurchaseNumberMin());
			promotionProduct.setPromotionStock(promotionInfo.getPromotionStock());
			
			//设置促销的SKU属性
			PromotionSku promotionSku= new PromotionSku();
			boolean flag = true;
			for(int i=0; i<skuIdArray.length;i++){
				promotionSku.setPromotionId(promotionId);
				promotionSku.setSkuId(Integer.parseInt(skuIdArray[i]));
				promotionSku.setDeductionPrice(Double.parseDouble(deductionPriceArray[i]));
				promotionSkuService.insert(promotionSku);
				
				//插入促销商品表，重复的ItemId不插入
				if(flag==true){
					promotionProduct.setItemId(Integer.parseInt(itemIdHidArray[i]));
					promotionProductService.insert(promotionProduct);	
				}
				if((i+1)<skuIdArray.length && itemIdHidArray[i+1].equals(itemIdHidArray[i])){
					flag=false;
				}else{
					flag=true;
				}				
			}		

		} catch (Exception e) {
			LOG.error("controller.queryPromotion=======",e);
			resultMap.put("msg","error");
			return resultMap;
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	
	/**
	 * 修改促销
	 * @return
	 */
	@RequestMapping(value="/editPromotion", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> editPromotion(PromotionInfo promotionInfo, String startTimeString, String endTimeString, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//Integer promotionId;
		PromotionProduct promotionProduct=new PromotionProduct();
		
		String[] skuIdArray=reuqest.getParameterValues("skuId");
		String[] deductionPriceArray=reuqest.getParameterValues("deductionPrice");
		String[] itemIdHidArray=reuqest.getParameterValues("itemIdHid");
		
		for(int i=0; i<skuIdArray.length;i++){
			if(StringUtils.isBlank(deductionPriceArray[i])){
				resultMap.put("msg","deductionNull");
				return resultMap;
			}
		}
		
		
		try {
			
			if(!StringUtils.isBlank(startTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startTime = sdf.parse(startTimeString);
				promotionInfo.setStartTime(startTime);
				promotionProduct.setStartTime(startTime);
			}
			if(!StringUtils.isBlank(endTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date endTime = sdf.parse(endTimeString);
				promotionInfo.setEndTime(endTime);
				promotionProduct.setEndTime(endTime);
			}
			if(StringUtils.isBlank(promotionInfo.getPromotionName())){
				promotionInfo.setPromotionName(null);
			}
			promotionInfo.setPromotionStatus(0);
			promotionInfo.setDiscountFlag(0);//单品促销推荐到首页未开启
			promotionInfo.setSpecialFlag(0);//产地特供推荐到首页未开启
			//promotionInfo.setVenderUserId(CookieUtil.getUserId(reuqest));//设置商家ID
			
			//根据促销ID修改促销信息
			
			promotionInfoService.modify(promotionInfo);
			//promotionId=promotionInfoService.insert(promotionInfo);
			

			//老数据设为无效（根据促销ID修改促销product表中的状态为无效）
			promotionProduct.setYn(0);
			promotionProduct.setPromotionId(promotionInfo.getPromotionId());
			promotionProductService.modifyByPromtionId(promotionProduct);
			
			
			//老数据设为无效（根据促销ID修改促销SKU表中的状态为无效）
			PromotionSku promotionSku = new PromotionSku();
			promotionSku.setPromotionId(promotionInfo.getPromotionId());
			promotionSku.setYn(0);
			promotionSkuService.modifyByPromotionId(promotionSku);
			
			promotionProduct.setPromotionStatus(0);
			promotionProduct.setPromotionType(promotionInfo.getPromotionType());
			promotionProduct.setDiscountFlag(0);
			promotionProduct.setSpecialFlag(0);
			promotionProduct.setVenderUserId(CookieUtil.getUserId(reuqest));
			promotionProduct.setPromotionId(promotionInfo.getPromotionId());
			promotionProduct.setPromotionName(promotionInfo.getPromotionName());
			promotionProduct.setPurchaseNumberMax(promotionInfo.getPurchaseNumberMax());
			promotionProduct.setPurchaseNumberMin(promotionInfo.getPurchaseNumberMin());
			promotionProduct.setPromotionStock(promotionInfo.getPromotionStock());
			promotionProduct.setYn(1);
			
			//设置促销的SKU属性
			//PromotionSku promotionSku= new PromotionSku();
			boolean flag = true;
			for(int i=0; i<skuIdArray.length;i++){
				promotionSku.setPromotionId(promotionInfo.getPromotionId());
				promotionSku.setSkuId(Integer.parseInt(skuIdArray[i]));
				promotionSku.setDeductionPrice(Double.parseDouble(deductionPriceArray[i]));
				promotionSku.setYn(1);
				//所有数据新插入
				promotionSkuService.insert(promotionSku);
				
				//插入促销商品表，重复的ItemId不插入
				if(flag==true){
					promotionProduct.setItemId(Integer.parseInt(itemIdHidArray[i]));
					//所有数据新插入
					promotionProductService.insert(promotionProduct);	
				}
				if((i+1)<skuIdArray.length && itemIdHidArray[i+1].equals(itemIdHidArray[i])){
					flag=false;
				}else{
					flag=true;
				}				
			}		

		} catch (Exception e) {
			LOG.error("promotioncontroller.editPromotion=======",e);
			resultMap.put("msg","error");
			return resultMap;
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	
	@RequestMapping("/queryItem")
	public @ResponseBody Map<String, Object> queryItem(Integer itemId, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {	
			resultMap = promotionInfoService.queryItem(itemId,CookieUtil.getUserId(request));
		} catch (Exception e) {
			LOG.error("controller.queryItem=======",e);
		}
		return resultMap;
	}
	

	@RequestMapping("/list")
	public @ResponseBody Map<String, Object> list(PromotionInfo promotionInfo, Integer page, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {	
			promotionInfo.setPageIndex(page);
			promotionInfo.setVenderUserId(CookieUtil.getUserId(request));//设置商家ID
			resultMap = promotionInfoService.queryPromotionList(promotionInfo);
		} catch (Exception e) {
		}
		return resultMap;
	}

	@RequestMapping(value="/singleItemPromotion", method={ RequestMethod.GET, RequestMethod.POST })
	public String singleItemPromotion(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = null;


		return "promotion/singleItemPromotion";
	}
	
	@RequestMapping(value="/productPlacePromotion", method={ RequestMethod.GET, RequestMethod.POST })
	public String productPlacePromotion(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = null;


		return "promotion/productPlacePromotion";
	}
	
	
	/**
	 * 查看促销信息
	 */
	@RequestMapping(value="/showPromotionInfo", method={ RequestMethod.GET, RequestMethod.POST })
	public String shoePromotionInfo(Integer promotionId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		PromotionInfo promotionInfo = new PromotionInfo();
		List<PromotionProduct> promotionProductList;
		List<PromotionProductSku> promotionProductSkuList = new ArrayList<PromotionProductSku>();
		
		try {
			promotionInfo = promotionInfoService.selectByPromotionId(promotionId);
			resultMap.put("promotionInfo", promotionInfo);
			
			//拼装促销&商品&SKU信息
			PromotionProductQuery promotionProductQuery =new PromotionProductQuery();
			promotionProductQuery.setPromotionId(promotionId);
			promotionProductQuery.setYn(1);
			promotionProductList = promotionProductService.selectByCondition(promotionProductQuery);
			for(PromotionProduct promotionProduct:promotionProductList){
				
				SkuQuery skuQuery=new SkuQuery();
				skuQuery.setItemId(promotionProduct.getItemId());
				skuQuery.setYn(1);
				// TODO 获取商品的状态
				Item item = itemService.selectByItemId(promotionProduct.getItemId());
				
				List<Sku> skuList =skuService.selectByCondition(skuQuery);
				
				//PromotionProductSku promotionProductSku = new PromotionProductSku();
				PromotionSkuQuery promotionSkuQuery = new PromotionSkuQuery();
				for(Sku sku : skuList){
					PromotionProductSku promotionProductSku = new PromotionProductSku();
					promotionProductSku.setItemId(sku.getItemId());
					promotionProductSku.setSkuId(sku.getSkuId());
					promotionProductSku.setStock(sku.getStock());
					promotionProductSku.setTbPrice(sku.getTbPrice());
					//根据促销ID和SKU查促销SKU信息
					promotionSkuQuery.setPromotionId(promotionId);
					promotionSkuQuery.setSkuId(sku.getSkuId());
					promotionSkuQuery.setYn(1);
					List<PromotionSku> promotionSkuList = promotionSkuService.selectByCondition(promotionSkuQuery);
					promotionProductSku.setDeductionPrice(promotionSkuList.get(0).getDeductionPrice());
					promotionProductSku.setLastPrice(sku.getTbPrice()-promotionSkuList.get(0).getDeductionPrice());
					//
					String status="";
					if(item.getItemStatus().equals(0)){
						status="新建";					
					}else if(item.getItemStatus().equals(1)){
						status="上架";
					}else{
						status="下架";
					}
					promotionProductSku.setStatus(status);
					promotionProductSkuList.add(promotionProductSku);
				}
			}
			resultMap.put("promotionProductSkuList", promotionProductSkuList);
	
		} catch (Exception e) {
			LOG.error("controller.plannedPromotion=======",e);
		}
		context.put("promotionInfo", promotionInfo);
		context.put("promotionProductSkuList", promotionProductSkuList);
		if(promotionInfo.getPromotionType()==2){
			return "promotion/editProductPlacePromotion";
		}else{
			return "promotion/editSingleItemPromotion";
		}
	}
	
	
	/**
	 * 待审核促销
	 */
	@RequestMapping(value="/plannedPromotion", method={ RequestMethod.GET, RequestMethod.POST })
	public String plannedPromotion(PromotionInfo promotionInfo, String startTimeString, String endTimeString,Integer page, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = null;
	
		try {
			if(page==null){
				promotionInfo.setPageIndex(1);
			}else{
				promotionInfo.setPageIndex(page);
			}
			if(!StringUtils.isBlank(startTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startTime = sdf.parse(startTimeString);
				promotionInfo.setStartTime(startTime);
			}
			if(!StringUtils.isBlank(endTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date endTime = sdf.parse(endTimeString);
				promotionInfo.setEndTime(endTime);
			}
			if(StringUtils.isBlank(promotionInfo.getPromotionName())){
				promotionInfo.setPromotionName(null);
			}
			promotionInfo.setPromotionStatus(0);//促销状态（0-新建  1-开启  2-关闭 3-审核通过 4-审核不通过）
			promotionInfo.setVenderUserId(CookieUtil.getUserId(reuqest));//设置商家ID
			resultMap = promotionInfoService.queryPromotionList(promotionInfo);
	
		} catch (Exception e) {
			LOG.error("controller.plannedPromotion=======",e);
		}
		context.put("resultMap", resultMap);
		return "promotion/plannedPromotion";
	}

		/**
		 * 待上线促销
		 */
	@RequestMapping(value="/examinedPromotion", method={ RequestMethod.GET, RequestMethod.POST })
	public String examinedPromotion(PromotionInfo promotionInfo, String startTimeString, String endTimeString,Integer page, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = null;

		try {
			if(page==null){
				promotionInfo.setPageIndex(1);
			}else{
				promotionInfo.setPageIndex(page);
			}
			if(!StringUtils.isBlank(startTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startTime = sdf.parse(startTimeString);
				promotionInfo.setStartTime(startTime);
			}
			if(!StringUtils.isBlank(endTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date endTime = sdf.parse(endTimeString);
				promotionInfo.setEndTime(endTime);
			}
			if(StringUtils.isBlank(promotionInfo.getPromotionName())){
				promotionInfo.setPromotionName(null);
			}
			promotionInfo.setPromotionStatus(3);//促销状态（0-新建  1-开启  2-关闭 3-审核通过 4-审核不通过）
			promotionInfo.setVenderUserId(CookieUtil.getUserId(reuqest));//设置商家ID
			resultMap = promotionInfoService.queryPromotionList(promotionInfo);

		} catch (Exception e) {
			LOG.error("controller.examinedPromotion=======",e);
		}
		context.put("resultMap", resultMap);
		return "promotion/examinedPromotion";
	}
	
	/**
	 * 上线促销管理
	 * @return
	 */
	@RequestMapping(value="/ongoingPromotion", method={ RequestMethod.GET, RequestMethod.POST })
	public String ongoingPromotion(PromotionInfo promotionInfo, String startTimeString, String endTimeString,Integer page, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = null;

		try {
			if(page==null){
				promotionInfo.setPageIndex(1);
			}else{
				promotionInfo.setPageIndex(page);
			}
			if(!StringUtils.isBlank(startTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startTime = sdf.parse(startTimeString);
				promotionInfo.setStartTime(startTime);
			}
			if(!StringUtils.isBlank(endTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date endTime = sdf.parse(endTimeString);
				promotionInfo.setEndTime(endTime);
			}
			if(StringUtils.isBlank(promotionInfo.getPromotionName())){
				promotionInfo.setPromotionName(null);
			}
			promotionInfo.setPromotionStatus(1);//促销状态（0-新建  1-开启  2-关闭）
			promotionInfo.setVenderUserId(CookieUtil.getUserId(reuqest));//设置商家ID
			resultMap = promotionInfoService.queryPromotionList(promotionInfo);

		} catch (Exception e) {
			LOG.error("controller.ongoingPromotion=======",e);
		}
		context.put("resultMap", resultMap);
		return "promotion/ongoingPromotion";
	}
	/**
	 *  下线促销列表
	 */
	@RequestMapping(value="/stoppedPromotion", method={ RequestMethod.GET, RequestMethod.POST })
	public String stoppedPromotion(PromotionInfo promotionInfo, String startTimeString, String endTimeString,Integer page, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = null;

		try {
			if(page==null){
				promotionInfo.setPageIndex(1);
			}else{
				promotionInfo.setPageIndex(page);
			}
			if(!StringUtils.isBlank(startTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startTime = sdf.parse(startTimeString);
				promotionInfo.setStartTime(startTime);
			}
			if(!StringUtils.isBlank(endTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date endTime = sdf.parse(endTimeString);
				promotionInfo.setEndTime(endTime);
			}
			if(StringUtils.isBlank(promotionInfo.getPromotionName())){
				promotionInfo.setPromotionName(null);
			}
			promotionInfo.setPromotionStatus(2);////促销状态（0-新建  1-开启  2-关闭）
			promotionInfo.setVenderUserId(CookieUtil.getUserId(reuqest));//设置商家ID
			resultMap = promotionInfoService.queryPromotionList(promotionInfo);

		} catch (Exception e) {
			LOG.error("controller.stoppedPromotion=======",e);
		}
		context.put("resultMap", resultMap);
		return "promotion/stoppedPromotion";
	}
	
	/**
	 *  驳回促销列表
	 */
	@RequestMapping(value="/rejectPromotion", method={ RequestMethod.GET, RequestMethod.POST })
	public String rejectPromotion(PromotionInfo promotionInfo, String startTimeString, String endTimeString,Integer page, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = null;

		try {
			if(page==null){
				promotionInfo.setPageIndex(1);
			}else{
				promotionInfo.setPageIndex(page);
			}
			if(!StringUtils.isBlank(startTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startTime = sdf.parse(startTimeString);
				promotionInfo.setStartTime(startTime);
			}
			if(!StringUtils.isBlank(endTimeString)){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date endTime = sdf.parse(endTimeString);
				promotionInfo.setEndTime(endTime);
			}
			if(StringUtils.isBlank(promotionInfo.getPromotionName())){
				promotionInfo.setPromotionName(null);
			}
			promotionInfo.setPromotionStatus(4);////促销状态（0-新建  1-开启  2-关闭）
			promotionInfo.setVenderUserId(CookieUtil.getUserId(reuqest));//设置商家ID
			resultMap = promotionInfoService.queryPromotionList(promotionInfo);

		} catch (Exception e) {
			LOG.error("controller.rejectPromotion=======",e);
		}
		context.put("resultMap", resultMap);
		return "promotion/rejectPromotion";
	}
	
}
