package com.ec.api.service.version.impl;

import com.ec.api.dao.VersionControlDao;
import com.ec.api.domain.Address;
import com.ec.api.domain.query.AddressQuery;
import com.ec.api.domain.version.VersionControl;
import com.ec.api.domain.version.query.VersionControlQuery;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;
import com.ec.api.service.version.VersionService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: iboy
 * Date: 2014-9-28
 * Time: 23:40:07
 */
@Service(value="versionService")
public class VersionServiceImpl implements VersionService {

    private final static Logger logger = Logger.getLogger(VersionServiceImpl.class);

    private VersionControlDao versionControlDao;

    @Override
    public Result getVersionControlByVersionControlQuery(VersionControlQuery versionControlQuery) {
        Result result = new Result();
		try{
            logger.info("versionControlDao==="+versionControlDao);
			List<VersionControl> versionControlList = versionControlDao.selectByCondition(versionControlQuery);
			if(versionControlList == null || versionControlList.size() ==0){
				result.setResultCode("10001");
				result.setResultMessage("没有找到相关版本");
				return result;
			}
			result.setResult(versionControlList.get(0));
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			logger.error("", e);
			EcUtils.setExceptionResult(result);
		}
        return result;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setVersionControlDao(VersionControlDao versionControlDao) {
        this.versionControlDao = versionControlDao;
    }
}
