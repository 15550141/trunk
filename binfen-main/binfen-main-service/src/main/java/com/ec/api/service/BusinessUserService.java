package com.ec.api.service;

import com.ec.api.domain.BusinessUserExt;
import com.ec.api.service.result.Result;

/**
 * 商户
 * User: iboy
 * Date: 2014-9-22
 * Time: 17:22:24
 */
public interface BusinessUserService {
    /**
     * 根据商户ID,获得商户信息
     * @param userId 商户ID
     * @return
     */
    public Result getBusinessUserExtByUserID(Integer userId);
}
