package com.ec.api.service;

import java.util.Map;

import com.ec.api.domain.OrderInfo;
import com.ec.api.domain.UmpInfo;
import com.ec.api.service.result.Result;
import com.ec.api.service.vo.UmpCallbackResultVo;
import com.ec.api.service.vo.UmpCallbackVo;
import com.ec.api.service.vo.UmpPayServiceResultVo;

public interface UmpInfoService {
	public Result insertUmpInfo(UmpInfo umpInfo);
	
	public UmpPayServiceResultVo payservice(OrderInfo orderInfo);
	
	public String callBack(Map reqMap);
}
