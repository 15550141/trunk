package com.ec.api.service.impl;

import java.io.File;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.ec.api.dao.*;
import com.ec.api.domain.*;
import com.ec.api.domain.query.PromotionSkuQuery;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ec.api.common.utils.RandomUtil;
import com.ec.api.domain.query.ItemQuery;
import com.ec.api.domain.query.SkuQuery;
import com.ec.api.service.ItemService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;

public class ItemServiceImpl implements ItemService {
	private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);
	
	private ItemDao itemDao;
	
	private CategoryDao categoryDao;
	
	private AddressDao addressDao;
	
	private SkuDao skuDao;
	
	private ItemDescriptionDao itemDescriptionDao;
	
	private ItemImageDao itemImageDao;

    private PromotionSkuDao promotionSkuDao;

    private PromotionInfoDao promotionInfoDao;
	
	private String uploadFile  = "D://imageUpload111/";
	
	private String tempPath = "D://imageUpload/temp/";
	
	@Override
	public Result getItemByItemId(Integer itemId) {
		Result result = new Result();
		try{
			Item item = itemDao.selectByItemId(itemId);
			if(item == null){
				result.setResultCode("8004");
				result.setResultMessage("商品属性不存在");
				return result;
			}
			SkuQuery skuQuery = new SkuQuery();
			skuQuery.setItemId(itemId);
			skuQuery.setYn(1);
			
			List<Sku> skuList = skuDao.selectByCondition(skuQuery);
            Date now = new Date();
            for(Sku sku : skuList){
                //计算SKU促销价格
               //一个SKU可能有多个促销，取价格最优者
                PromotionSkuQuery promotionSkuQuery = new PromotionSkuQuery();
                promotionSkuQuery.setSkuId(sku.getSkuId());
                List<PromotionSku> psList = promotionSkuDao.selectByCondition(promotionSkuQuery);
                PromotionSku promotionSku = null;
                if( null != psList && 0 < psList.size() ){
                    promotionSku = psList.get(0);
                }
                for(PromotionSku ps :psList ){
                    if( ps.getDeductionPrice() > promotionSku.getDeductionPrice()){
                        promotionSku = ps;
                    }
                }
                //如果带促销，计算带促销价格
				if(promotionSku != null && promotionSku.getDeductionPrice() > 0){
					PromotionInfo promotionInfo = promotionInfoDao.selectByPromotionId(promotionSku.getPromotionId());
					//判断是否有促销活动
					if(promotionInfo != null && promotionInfo.getPurchaseNumberMin()!=null &&  promotionInfo.getPurchaseNumberMax()!=null
                            && promotionInfo.getPromotionStatus()!=null && promotionInfo.getStartTime()!=null && promotionInfo.getEndTime() !=null
                            && promotionInfo.getYn() == 1 && promotionInfo.getPromotionStatus() == 1
							//&& orderDetail.getNum() > promotionInfo.getPurchaseNumberMin() && orderDetail.getNum() < promotionInfo.getPurchaseNumberMax()
							&& now.after(promotionInfo.getStartTime()) && now.before(promotionInfo.getEndTime())
                            ){
                            if(sku.getSalePrice()> promotionSku.getDeductionPrice()){
                                sku.setSalePrice(sku.getSalePrice() - promotionSku.getDeductionPrice()); //商品实际出售单价
                            }
					}
				}
            }            
			Map<String, Object> map = new HashMap<String, Object>();
			
			Category category1 = categoryDao.selectByCategoryId(item.getCategoryId1());
			if(category1 != null){
				item.setCategoryId1Name(category1.getCategoryName());
			}
			Category category2 = categoryDao.selectByCategoryId(item.getCategoryId2());
			if(category2 != null){
				item.setCategoryId2Name(category2.getCategoryName());
			}
			
			Category category3 = categoryDao.selectByCategoryId(item.getCategoryId3());
			if(category3 != null){
				item.setCategoryId3Name(category3.getCategoryName());
			}
			
			Category category4 = categoryDao.selectByCategoryId(item.getCategoryId4());
			if(category4 != null){
				item.setCategoryId4Name(category4.getCategoryName());
			}
			
			Address address1 = addressDao.selectByAddressId(item.getOriginProvince());
			if(address1 != null){
				item.setOriginProvinceName(address1.getAddressName());
			}
			
			Address address2 = addressDao.selectByAddressId(item.getOriginCity());
			if(address2 != null){
				item.setOriginCityName(address2.getAddressName());
			}
			
			Address address3 = addressDao.selectByAddressId(item.getOriginCounty());
			if(address3 != null){
				item.setOriginCountyName(address3.getAddressName());
			}
			
			map.put("item", item);
			map.put("skuList", skuList);
			result.setResult(map);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	@Override
	public Result getItemByItemQuery(ItemQuery itemQuery) {
		Result result = new Result();
		try{
			itemQuery.setYn(1);
			int total = itemDao.countByCondition(itemQuery);
			if(total == 0){
				result.setResultCode("8001");
				result.setResultMessage("商品属性列表不存在");
				return result;
			}
			int totalPage = total / itemQuery.getPageSize() + 1;
			if(itemQuery.getPageNo() > totalPage){
				itemQuery.setPageNo(totalPage);
			}
			
			List<Item> itemList = itemDao.selectByConditionForPage(itemQuery);
			
			for(int i = 0; i < itemList.size(); i++){
				
				
				
				Item item = itemList.get(i);
				
				Category category1 = categoryDao.selectByCategoryId(item.getCategoryId1());
				if(category1 != null){
					item.setCategoryId1Name(category1.getCategoryName());
				}
				Category category2 = categoryDao.selectByCategoryId(item.getCategoryId2());
				if(category2 != null){
					item.setCategoryId2Name(category2.getCategoryName());
				}
				
				Category category3 = categoryDao.selectByCategoryId(item.getCategoryId3());
				if(category3 != null){
					item.setCategoryId3Name(category3.getCategoryName());
				}
				
				Category category4 = categoryDao.selectByCategoryId(item.getCategoryId4());
				if(category4 != null){
					item.setCategoryId4Name(category4.getCategoryName());
				}
				
				Address address1 = addressDao.selectByAddressId(item.getOriginProvince());
				if(address1 != null){
					item.setOriginProvinceName(address1.getAddressName());
				}
				
				Address address2 = addressDao.selectByAddressId(item.getOriginCity());
				if(address2 != null){
					item.setOriginCityName(address2.getAddressName());
				}
				
				Address address3 = addressDao.selectByAddressId(item.getOriginCounty());
				if(address3 != null){
					item.setOriginCountyName(address3.getAddressName());
				}
				
				SkuQuery skuQuery = new SkuQuery();
				skuQuery.setItemId(item.getItemId());
				skuQuery.setYn(1);
				
				item.setSkuList(skuDao.selectByCondition(skuQuery));
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", total);
			map.put("totalPage", totalPage);
			map.put("curPage", itemQuery.getPageNo());
			map.put("itemList", itemList);
			
			result.setResult(map);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	@Override
	public Result getItemsByVenderUserId(Integer venderUserId) {
		Result result = new Result();
		try{
			ItemQuery itemQuery = new ItemQuery();
			itemQuery.setVenderUserId(venderUserId);
			itemQuery.setYn(1);
			List<Item> list = itemDao.selectByCondition(itemQuery);
			if(list == null || list.size() == 0){
				result.setResultCode("8001");
				result.setResultMessage("商品属性列表不存在");
				return result;
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
	public Item getItemAndSkusByItemId(Integer itemId) {
		Item item = itemDao.selectByItemId(itemId);
		if(item == null){
			log.error(itemId + "商品不存在");
			return null;
		}
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.setItemId(itemId);
		skuQuery.setYn(1);
		List<Sku> list = skuDao.selectByCondition(skuQuery);
		if(list == null || list.size() == 0){
			log.error(itemId + "没有sku信息");
			return null;
		}
		item.setSkuList(list);
		return item;
	}

	@Override
	public Result addItem(Item item, List<Sku> skus) {
		Result result = new Result();
		try{
			Integer itemId = itemDao.insert(item);
			for(int i=0;i<skus.size();i++){
				Sku sku = skus.get(i);
				sku.setItemId(itemId);
				skuDao.insert(sku);
			}
			result.setResult(itemId);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result updateItem(Item item) {
		Result result = new Result();
		try{
			itemDao.modify(item);
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	@Override
	public Result getappDescriptionInfo(Integer itemId) {
		Result result = new Result();
		try{
			ItemDescription itemDescription = itemDescriptionDao.selectByItemId(itemId);
			if(itemDescription == null){
				result.setResultCode("8003");
				result.setResultMessage("商品介绍不存在");
				return result;
			}
			
			result.setResult(itemDescription.getAppDescriptionInfo());
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("");
			EcUtils.setExceptionResult(result);
		}
		
		return result;
	}
	
	@Override
	public Result getItemImages(Integer itemId) {
		Result result = new Result();
		try{
			List<ItemImage> list = itemImageDao.selectByItemId(itemId);
			if(list == null || list.size() ==0){
				result.setResultCode("8007");
				result.setResultMessage("商品图片不存在");
				return result;
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
	public Result imageUpload(HttpServletRequest request) {
		Result result = new Result();
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb

			File tempFile = new File(tempPath);
			factory.setRepository(tempFile);// 设置缓冲区目录
			ServletFileUpload upload = new ServletFileUpload(factory);
			
//			upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB
			List<FileItem> items = upload.parseRequest(request);//得到所有的文件
			for(int i=0;i<items.size();i++){
				FileItem fi = items.get(i);
				if(!fi.isFormField()){
					String fileName = fi.getName();
					Calendar cal = Calendar.getInstance();
					int year=cal.get(Calendar.YEAR);//得到年
					int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
					int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
					String path = uploadFile + year +"/" + month+"/"+day+"/";
					File file = new File(path);
					if(!file.exists()){
						file.mkdirs();
					}
					
					File savedFile = new File(path, RandomUtil.randomRangeInt(100000, 999999)+fileName);
					fi.write(savedFile);
					result.setResult("http:/"+path+savedFile.getName());
				}
			}
			EcUtils.setSuccessResult(result);
		} catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	@Override
	public Result addItemImage(ItemImage itemImage) {
		Result result = new Result();
		try{
			itemImageDao.insert(itemImage);
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	@Override
	public Result updateSku(Sku sku) {
		Result result = new Result();
		try{
			if(skuDao.selectBySkuId(sku.getSkuId()) == null){
				result.setResultCode("8002F");
				result.setResultMessage("商品图片不存在");
				return result;
			}
			
			skuDao.modify(sku);
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		
		return result;
	}

    @Override
    public Result addItemImages(List<ItemImage> itemImageList) {
        Result result = new Result();
		try{
            for( ItemImage itemImage : itemImageList ){
                itemImageDao.insert(itemImage);
            }
            result.setResult(true);
		    EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
        return result;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public void setSkuDao(SkuDao skuDao) {
		this.skuDao = skuDao;
	}

	public void setItemDescriptionDao(ItemDescriptionDao itemDescriptionDao) {
		this.itemDescriptionDao = itemDescriptionDao;
	}

	public void setItemImageDao(ItemImageDao itemImageDao) {
		this.itemImageDao = itemImageDao;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

    public void setPromotionSkuDao(PromotionSkuDao promotionSkuDao) {
        this.promotionSkuDao = promotionSkuDao;
    }

    public void setPromotionInfoDao(PromotionInfoDao promotionInfoDao) {
        this.promotionInfoDao = promotionInfoDao;
    }
}
