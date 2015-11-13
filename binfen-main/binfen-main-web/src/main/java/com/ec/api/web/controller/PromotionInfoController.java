package com.ec.api.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ec.api.domain.PromotionInfo;
import com.ec.api.domain.query.PromotionInfoQuery;
import com.ec.api.service.PromotionInfoService;
import com.ec.api.service.result.Result;
import com.ec.api.web.base.BaseController;

@Controller
@RequestMapping("promotionInfo")
public class PromotionInfoController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(PromotionInfoController.class);
	
	private PromotionInfoService promotionInfoService;
	
	/**
	 * 依据商家ID查询商家所属促销列表查看
	 * @return
	 */
	@RequestMapping(value="getPromotionInfos", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result getPromotionInfos(PromotionInfoQuery promotionInfoQuery, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		promotionInfoQuery.setYn(1);
        if( null != promotionInfoQuery.getDiscountFlag() && 1 == promotionInfoQuery.getDiscountFlag() ){
            promotionInfoQuery.setPromotionType(1);
        }
        if( null != promotionInfoQuery.getSpecialFlag() && 1 == promotionInfoQuery.getSpecialFlag() ){
            promotionInfoQuery.setPromotionType(2);
        }
		return promotionInfoService.getPromotionInfosByPromotionInfoQuery(promotionInfoQuery);
	}
	
	/**
	 * 新增促销信息
	 * @return
	 */
	@RequestMapping(value="addPromotionInfo", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result addPromotionInfo(PromotionInfo promotionInfo, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		Result result = new Result();
		if(StringUtils.isBlank(promotionInfo.getPromotionName())){
			result.setResultCode("1001");
			result.setResultMessage("promotionName不能为空");
			return result;
		}
		if(promotionInfo.getItemId() == null){
			result.setResultCode("1001");
			result.setResultMessage("itemId不能为空");
			return result;
		}
		if(promotionInfo.getPromotionType() == null){
			result.setResultCode("1001");
			result.setResultMessage("promotionType不能为空");
			return result;
		}
		if(promotionInfo.getStartTime() == null){
			result.setResultCode("1001");
			result.setResultMessage("startTime不能为空");
			return result;
		}
		if(promotionInfo.getEndTime() == null){
			result.setResultCode("1001");
			result.setResultMessage("endTime不能为空");
			return result;
		}
		if(promotionInfo.getPurchaseNumberMin() == null){
			result.setResultCode("1001");
			result.setResultMessage("purchaseNumberMin不能为空");
			return result;
		}
		if(promotionInfo.getPurchaseNumberMax() == null){
			result.setResultCode("1001");
			result.setResultMessage("purchaseNumberMax不能为空");
			return result;
		}
		
		promotionInfo.setVenderUserId(getUserId(request));
		promotionInfo.setYn(1);
		promotionInfo.setCreated(new Date());
		promotionInfo.setPromotionStatus(0);
		
		return promotionInfoService.addPromotionInfo(promotionInfo);
	}

	/**
	 * 根据促销ID获取促销信息
	 * @return
	 */
	@RequestMapping(value="getPromotionInfoByPromotionId", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result getPromotionInfoByPromotionId(Integer promotionId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(promotionId == null){
			Result result = new Result();
			result.setResultCode("1001");
			result.setResultMessage("promotionId不能为空");
			return result;
		}
		return promotionInfoService.getPromotionInfoByPromotionId(promotionId);
	}
	
	/**
	 * 修改促销列表信息
	 * @return
	 */
	@RequestMapping(value="updatePromotionInfo", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result updatePromotionInfo(PromotionInfo PromotionInfo, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(PromotionInfo.getPromotionId() == null){
			Result result = new Result();
			result.setResultCode("1001");
			result.setResultMessage("promotionId不能为空");
			return result;
		}
		return promotionInfoService.updatePromotionInfo(PromotionInfo);
	}
	
	/**
	 * 商家关闭促销
	 * @return
	 */
	@RequestMapping(value="closePromotion", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result closePromotion(Integer promotionId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(promotionId == null){
			Result result = new Result();
			result.setResultCode("1001");
			result.setResultMessage("promotionId不能为空");
			return result;
		}
		return promotionInfoService.closePromotion(getUserId(request), promotionId);
	}
	
	/**
	 * 根据skuId查询促销信息
	 * @return
	 */
	@RequestMapping(value="getPromotionInfoBySkuId", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result getPromotionInfoBySkuId(Integer skuId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(skuId == null){
			Result result = new Result();
			result.setResultCode("1001");
			result.setResultMessage("skuId不能为空");
			return result;
		}
		return promotionInfoService.getPromotionInfoBySkuId(skuId);
	}
	
	/**
	 * 根据itemId查询促销信息
	 * @return
	 */
	@RequestMapping(value="getPromotionInfoByItemId", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result getPromotionInfoByItemId(Integer itemId, HttpServletRequest request,HttpServletResponse response, ModelMap context){
		if(itemId == null){
			Result result = new Result();
			result.setResultCode("1001");
			result.setResultMessage("itemId不能为空");
			return result;
		}
		return promotionInfoService.getPromotionInfoByItemId(itemId);
	}
	
	/**
	 * 获得首页折扣区top6促销商品信息
	 * @return
	 */
	@RequestMapping(value="getIndexTop6DiscountPromotionInfo", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result getIndexTop6DiscountPromotionInfo(HttpServletRequest request,HttpServletResponse response, ModelMap context){
		return promotionInfoService.getIndexTop6DiscountPromotionInfo();
	}
	
	/**
	 * 获得首页产地特供区top6促销商品信息
	 * @return
	 */
	@RequestMapping(value="getIndexTop6SpecialPromotionInfo", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result getIndexTop6SpecialPromotionInfo(HttpServletRequest request,HttpServletResponse response, ModelMap context){
		return promotionInfoService.getIndexTop6SpecialPromotionInfo();
	}
	
	
	public void setPromotionInfoService(PromotionInfoService promotionInfoService) {
		this.promotionInfoService = promotionInfoService;
	}


	
}
