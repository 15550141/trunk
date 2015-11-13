package com.ec.erp.service;

import java.util.List;
import java.util.Map;

import com.ec.erp.domain.BusinessUserExt;
import com.ec.erp.domain.query.BusinessUserExtQuery;

public interface BusinessUserExtService {
	/**
	 * 添加商家扩展信息
	 * @param businessUserExt
	 * @return
	 */
	public Integer insert(BusinessUserExt businessUserExt);
	/**
	 * 依据用户ID修改商家扩展信息
	 * @param businessUserExt
	 */
	public void modifyByUserId(BusinessUserExt businessUserExt);

	/**
	 * 依据用户ID查询商家扩展信息
	 * @param userId
	 * @return
	 */
	public BusinessUserExt selectByUserId(int userId);
	Map<String, Object> queryBusinessUserExtList(
			BusinessUserExtQuery businessUserExtQuery);


	/**
	 * 根据相应的条件查询商家扩展信息
	 * @param businessUserExtQuery
	 * @return
	 */
	public List<BusinessUserExt> selectByCondition(BusinessUserExtQuery businessUserExtQuery);
}
