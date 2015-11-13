package com.ec.api.service;

import com.ec.api.domain.ConsigneeInfo;
import com.ec.api.service.result.Result;

public interface ConsigneeInfoService {
	/**
	 * 用户收货人信息列表查询
	 * @return
	 */
	public Result getConsigneeInfosByUserId(Integer userId);
	/**
	 * 新增
	 * @return
	 */
	public Result addConsigneeInfo(ConsigneeInfo consigneeInfo);
	/**
	 * 修改
	 * @return
	 */
	public Result updateConsigneeInfo(ConsigneeInfo consigneeInfo);
	/**
	 * 删除
	 * @return
	 */
	public Result delConsigneeInfo(Integer consigneeId);
}
