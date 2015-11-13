package com.ec.api.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ec.api.dao.IndexImageDao;
import com.ec.api.domain.IndexImage;
import com.ec.api.domain.query.IndexImageQuery;
import com.ec.api.service.IndexImageService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;

@Service(value="indexImageService")
public class IndexImageServiceImpl implements IndexImageService {
	private static final Logger log = LoggerFactory.getLogger(IndexImageServiceImpl.class);
	
	private IndexImageDao indexImageDao;
	
	@Override
	public Result getIndexImages() {
		Result result = new Result();
		try{
			IndexImageQuery indexImageQuery = new IndexImageQuery();
			indexImageQuery.setYn(1);
			
			List<IndexImage> list = indexImageDao.selectByCondition(indexImageQuery);
			if(list == null || list.size() == 0){
				result.setSuccess(false);
				result.setResultCode("3007");
				result.setResultMessage("首页轮播图列表不存在");
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

	public void setIndexImageDao(IndexImageDao indexImageDao) {
		this.indexImageDao = indexImageDao;
	}

}
