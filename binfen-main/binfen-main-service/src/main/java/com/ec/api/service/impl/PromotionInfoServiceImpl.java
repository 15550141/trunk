package com.ec.api.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ec.api.dao.ItemDao;
import com.ec.api.dao.PromotionInfoDao;
import com.ec.api.dao.PromotionSkuDao;
import com.ec.api.dao.SkuDao;
import com.ec.api.domain.Item;
import com.ec.api.domain.PromotionInfo;
import com.ec.api.domain.PromotionSku;
import com.ec.api.domain.Sku;
import com.ec.api.domain.query.PromotionInfoQuery;
import com.ec.api.domain.query.PromotionSkuQuery;
import com.ec.api.domain.query.SkuQuery;
import com.ec.api.service.PromotionInfoService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;

@Service(value="promotionInfoService")
public class PromotionInfoServiceImpl implements PromotionInfoService{
	private static final Logger log = LoggerFactory.getLogger(PromotionInfoServiceImpl.class);
	
	private PromotionInfoDao promotionInfoDao;
	
	private PromotionSkuDao promotionSkuDao;
	
	private ItemDao itemDao;

    private SkuDao skuDao;


	
	@Override
	public Result getPromotionInfosByPromotionInfoQuery(PromotionInfoQuery promotionInfoQuery) {
		Result result = new Result();
		try{
			int total = promotionInfoDao.countPromotionProductByCondition(promotionInfoQuery);
			if(total == 0){
				result.setResultCode("3001");
				result.setResultMessage("促销列表不存在");
				return result;
			}
			int totalPage = total / promotionInfoQuery.getPageSize() + 1;
			if(promotionInfoQuery.getPageNo() > totalPage){
				promotionInfoQuery.setPageNo(totalPage);
			}
			List<PromotionInfo> list = promotionInfoDao.selectPromotionProductByConditionForPage(promotionInfoQuery);
			
			for(int i=0;i<list.size();i++){
				PromotionInfo promotionInfo = list.get(i);
				if( null != promotionInfo.getItemId() && 0 < promotionInfo.getItemId() ){
                     Item item = itemDao.selectByItemId(promotionInfo.getItemId());
                     promotionInfo.setItem(item);
                     if( null != item ){
                         SkuQuery sq = new SkuQuery();
                         sq.setItemId(item.getItemId());
                         List<Sku> skuList = skuDao.selectByCondition(sq);
                         if( null != skuList && 0 < skuList.size() ){
                            item.setSkuList(skuList);
                         }
                     }
                }
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", total);
			map.put("totalPage", totalPage);
			map.put("curPage", promotionInfoQuery.getPageNo());
			map.put("list", list);
			
			result.setResult(map);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result addPromotionInfo(PromotionInfo promotionInfo) {
		Result result = new Result();
		try{
			this.promotionInfoDao.insert(promotionInfo);
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result getPromotionInfoByPromotionId(Integer promotionId) {
		Result result = new Result();
		try{
			PromotionInfo promotionInfo = this.promotionInfoDao.selectByPromotionId(promotionId);
			if(promotionInfo == null){
				result.setResultCode("3001");
				result.setResultMessage("促销列表不存在");
				return result;
			}
			promotionInfo.setItem(itemDao.selectByItemId(promotionInfo.getItemId()));
			result.setResult(promotionInfo);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result updatePromotionInfo(PromotionInfo promotionInfo) {
		Result result = new Result();
		try{
			PromotionInfo pi = this.promotionInfoDao.selectByPromotionId(promotionInfo.getPromotionId());
			if(pi == null){
				result.setResultCode("3001");
				result.setResultMessage("促销列表不存在");
				return result;
			}
			this.promotionInfoDao.modify(promotionInfo);
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result closePromotion(Integer venderUserId, Integer promotionId) {
		Result result = new Result();
		try{
			PromotionInfoQuery promotionInfoQuery = new PromotionInfoQuery();
			promotionInfoQuery.setVenderUserId(venderUserId);
			promotionInfoQuery.setPromotionId(promotionId);
			List<PromotionInfo> list = promotionInfoDao.selectByCondition(promotionInfoQuery);
			if(list == null || list.size() == 0){
				result.setResultCode("3001");
				result.setResultMessage("促销列表不存在");
				return result;
			}
			PromotionInfo promotionInfo = list.get(0);
			promotionInfo.setPromotionStatus(2);//2为关闭
			promotionInfoDao.modify(promotionInfo);
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result getPromotionInfoBySkuId(Integer skuId) {
		Result result = new Result();
		
		try{
			PromotionSku promotionSku = promotionSkuDao.selectBySkuId(skuId);
			if(promotionSku == null){
				result.setResultCode("3002");
				result.setResultMessage("该商品没有促销");
				return result;
			}
			PromotionInfo promotionInfo = promotionInfoDao.selectByPromotionId(promotionSku.getPromotionId());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("promotionInfo", promotionInfo);
			map.put("promotionSku", promotionSku);
			result.setResult(map);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result getPromotionInfoByItemId(Integer itemId) {
		Result result = new Result();
		
		try{
			PromotionInfoQuery promotionInfoQuery = new PromotionInfoQuery();
			promotionInfoQuery.setItemId(itemId);
			promotionInfoQuery.setYn(1);
			promotionInfoQuery.setPromotionStatus(1);
			List<PromotionInfo> promotionInfoList = this.promotionInfoDao.selectByCondition(promotionInfoQuery);
			if(promotionInfoList == null || promotionInfoList.size() == 0){
				result.setResultCode("3002");
				result.setResultMessage("该商品没有促销");
				return result;
			}
			PromotionInfo promotionInfo = promotionInfoList.get(0);
			
			Map<String, Object> map = new HashMap<String, Object>();
			PromotionSkuQuery promotionSkuQuery = new PromotionSkuQuery();
			promotionSkuQuery.setPromotionId(promotionInfo.getPromotionId());
			List<PromotionSku> list = promotionSkuDao.selectByCondition(promotionSkuQuery);
			
			map.put("promotionInfo", promotionInfo);
			map.put("promotionSkuList", list);
			result.setResult(map);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	@Override
	public Result getIndexTop6DiscountPromotionInfo() {
		Result result = new Result();
		try{
			PromotionInfoQuery promotionInfoQuery = new PromotionInfoQuery();
			promotionInfoQuery.setDiscountFlag(1);
			promotionInfoQuery.setStart(0);
			promotionInfoQuery.setPageSize(6);
			promotionInfoQuery.setYn(1);
            promotionInfoQuery.setPromotionStatus(1);
            promotionInfoQuery.setPromotionType(1);
			List<PromotionInfo> list = promotionInfoDao.selectPromotionProductByConditionForPage(promotionInfoQuery);
			if(list == null || list.size() == 0){
				result.setResultCode("3001");
				result.setResultMessage("促销列表不存在");
				return result;
			}
			
			for(int i=0;i<list.size();i++){
				PromotionInfo promotionInfo = list.get(i);
                if( null != promotionInfo.getItemId() && 0 < promotionInfo.getItemId() ){
                     Item item = itemDao.selectByItemId(promotionInfo.getItemId());
                     //如果不是上架商品，直接跳过
                     if(item.getItemStatus() != 1 || item.getYn() != 1){
                        continue;
                     }
                     promotionInfo.setItem(item);
                     if( null != item ){
                         SkuQuery sq = new SkuQuery();
                         sq.setItemId(item.getItemId());
                         List<Sku> skuList = skuDao.selectByCondition(sq);
                         if( null != skuList && 0 < skuList.size() ){
                            item.setSkuList(skuList);        
                         }
                         for(Sku sku : skuList){
                            Date now = new Date();
                            //计算SKU促销价格
                            //一个SKU可能有多个促销，取价格最优者
                            PromotionSkuQuery promotionSkuQuery = new PromotionSkuQuery();
                            promotionSkuQuery.setSkuId(sku.getSkuId());
                            List<PromotionSku> psList = promotionSkuDao.selectByCondition(promotionSkuQuery);
                            PromotionSku promotionSku = null;
                            PromotionInfo promotionInfo2 = null;
                            if( null != psList && 0 < psList.size() ){
                                promotionSku = psList.get(0);
                            }
                            for(PromotionSku ps :psList ){
                                if( ps.getDeductionPrice().compareTo( promotionSku.getDeductionPrice() ) ==1 ){
                                    promotionSku = ps;
                                }
                            }
                            //如果带促销，计算带促销价格
                            if(promotionSku != null && promotionSku.getDeductionPrice() > 0){
                                promotionInfo2  = promotionInfoDao.selectByPromotionId(promotionSku.getPromotionId());
                                //判断是否有促销活动
                                if(promotionInfo2 != null && promotionInfo2.getPurchaseNumberMin()!=null &&  promotionInfo2.getPurchaseNumberMax()!=null
                                        && promotionInfo2.getPromotionStatus()!=null && promotionInfo2.getStartTime()!=null && promotionInfo2.getEndTime() !=null
                                        && promotionInfo2.getYn() == 1 && promotionInfo2.getPromotionStatus() == 1
                                        //&& orderDetail.getNum() > promotionInfo.getPurchaseNumberMin() && orderDetail.getNum() < promotionInfo.getPurchaseNumberMax()
                                        && now.after(promotionInfo2.getStartTime()) && now.before(promotionInfo2.getEndTime())
                                        ){
                                        if(sku.getSalePrice().compareTo(promotionSku.getDeductionPrice())>0){
                                            sku.setSalePrice(sku.getSalePrice() - promotionSku.getDeductionPrice()); //商品实际出售单价
                                        }
                                }
                            }
                         }
                     }
                }
			}
			
			result.setResult(list);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result getIndexTop6SpecialPromotionInfo() {
		Result result = new Result();
		try{
			PromotionInfoQuery promotionInfoQuery = new PromotionInfoQuery();
			promotionInfoQuery.setSpecialFlag(1);
			promotionInfoQuery.setStart(0);
			promotionInfoQuery.setPageSize(6);
			promotionInfoQuery.setYn(1);
            promotionInfoQuery.setPromotionStatus(1);
            promotionInfoQuery.setPromotionType(2);
			List<PromotionInfo> list = promotionInfoDao.selectPromotionProductByConditionForPage(promotionInfoQuery);
			if(list == null || list.size() == 0){
				result.setResultCode("3001");
				result.setResultMessage("促销列表不存在");
				return result;
			}
			
			for(int i=0;i<list.size();i++){
				PromotionInfo promotionInfo = list.get(i);
                if( null != promotionInfo.getItemId() && 0 < promotionInfo.getItemId() ){
                     Item item = itemDao.selectByItemId(promotionInfo.getItemId());
                     //如果不是上架商品，直接跳过
                     if(item.getItemStatus() != 1 || item.getYn() != 1){
                        continue;
                     }
                     promotionInfo.setItem(item);
                     if( null != item ){
                         SkuQuery sq = new SkuQuery();
                         sq.setItemId(item.getItemId());
                         List<Sku> skuList = skuDao.selectByCondition(sq);
                         if( null != skuList && 0 < skuList.size() ){
                            item.setSkuList(skuList);
                         }
                          for(Sku sku : skuList){
                            Date now = new Date();
                            //计算SKU促销价格
                            //一个SKU可能有多个促销，取价格最优者
                            PromotionSkuQuery promotionSkuQuery = new PromotionSkuQuery();
                            promotionSkuQuery.setSkuId(sku.getSkuId());
                            List<PromotionSku> psList = promotionSkuDao.selectByCondition(promotionSkuQuery);
                            PromotionSku promotionSku = null;
                            PromotionInfo promotionInfo2 = null;
                            if( null != psList && 0 < psList.size() ){
                                promotionSku = psList.get(0);
                            }
                            for(PromotionSku ps :psList ){
                                if( ps.getDeductionPrice().compareTo( promotionSku.getDeductionPrice() ) ==1 ){
                                    promotionSku = ps;
                                }
                            }
                            //如果带促销，计算带促销价格
                            if(promotionSku != null && promotionSku.getDeductionPrice() > 0){
                                promotionInfo2  = promotionInfoDao.selectByPromotionId(promotionSku.getPromotionId());
                                //判断是否有促销活动
                                if(promotionInfo2 != null && promotionInfo2.getPurchaseNumberMin()!=null &&  promotionInfo2.getPurchaseNumberMax()!=null
                                        && promotionInfo2.getPromotionStatus()!=null && promotionInfo2.getStartTime()!=null && promotionInfo2.getEndTime() !=null
                                        && promotionInfo2.getYn() == 1 && promotionInfo2.getPromotionStatus() == 1
                                        //&& orderDetail.getNum() > promotionInfo.getPurchaseNumberMin() && orderDetail.getNum() < promotionInfo.getPurchaseNumberMax()
                                        && now.after(promotionInfo2.getStartTime()) && now.before(promotionInfo2.getEndTime())
                                        ){
                                        if(sku.getSalePrice().compareTo(promotionSku.getDeductionPrice())>0){
                                            sku.setSalePrice(sku.getSalePrice() - promotionSku.getDeductionPrice()); //商品实际出售单价
                                        }
                                }
                            }
                         }
                     }
                }
			}
			
			result.setResult(list);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	public void setPromotionInfoDao(PromotionInfoDao promotionInfoDao) {
		this.promotionInfoDao = promotionInfoDao;
	}

	public void setPromotionSkuDao(PromotionSkuDao promotionSkuDao) {
		this.promotionSkuDao = promotionSkuDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

    public SkuDao getSkuDao() {
        return skuDao;
    }

    public void setSkuDao(SkuDao skuDao) {
        this.skuDao = skuDao;
    }
}
