package com.ec.api.service.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ec.api.dao.PropertyDao;
import com.ec.api.dao.PropertyValueDao;
import com.ec.api.dao.SkuDao;
import com.ec.api.domain.Sku;
import com.ec.api.service.SkuService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;

@Service(value="skuService")
public class SkuServiceImpl implements SkuService {
	private static final Logger log = LoggerFactory.getLogger(SkuServiceImpl.class);
	
	private SkuDao skuDao;
	
	private PropertyDao propertyDao;
	
	private PropertyValueDao propertyValueDao;
	
	private Map<Integer, Integer> skuPriceMap;
	
	@Override
	public Result getSkuBySkuId(Integer skuId) {
		Result result = new Result();
		try{
			Sku sku = skuDao.selectBySkuId(skuId);
			if(sku == null){
				result.setResultCode("8006");
				result.setResultMessage("sku信息不存在");
				return result;
			}
			
			String salesProperty = sku.getSalesProperty();
			if(StringUtils.isNotBlank(salesProperty)){
				try{
					String value = salesProperty.split("^")[0];
					String [] values = value.split(":");
					String id = propertyDao.selectByPropertyId(Integer.parseInt(values[0])).getPropertyName();
					String idValue = propertyValueDao.selectByPropertyValueId(Integer.parseInt(values[1])).getPropertyValueName();
					sku.setSalesPropertyName(id+":"+idValue);
				}catch (Exception e) {
					log.error("", e);
				}
			}
			result.setResult(sku);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	@Override
	public Integer getLocalSkuSalePrice(Integer skuId) {
		return this.skuPriceMap.get(skuId);
	}

	
	public void setSkuDao(SkuDao skuDao) {
		this.skuDao = skuDao;
	}
	public void setPropertyDao(PropertyDao propertyDao) {
		this.propertyDao = propertyDao;
	}
	public void setPropertyValueDao(PropertyValueDao propertyValueDao) {
		this.propertyValueDao = propertyValueDao;
	}
	public void setSkuPriceMap(Map<Integer, Integer> skuPriceMap) {
		this.skuPriceMap = skuPriceMap;
	}
	
}
