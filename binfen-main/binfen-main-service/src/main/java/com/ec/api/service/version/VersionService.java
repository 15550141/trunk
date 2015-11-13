package com.ec.api.service.version;

import com.ec.api.domain.version.VersionControl;
import com.ec.api.domain.version.query.VersionControlQuery;
import com.ec.api.service.result.Result;
import com.sun.org.apache.bcel.internal.classfile.ConstantString;

import java.util.ArrayList;
import java.util.List;

/**
 * 版本服务
 * User: iboy
 * Date: 2014-9-28
 * Time: 18:17:56
 */
public interface VersionService {

    /**
     * 根据手机端类型，版本号获得最新版本
     * @param versionControlQuery
     * @return
     */
    public Result getVersionControlByVersionControlQuery(VersionControlQuery versionControlQuery);







}
