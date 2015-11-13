package com.ec.api.web.controller.version;

import com.ec.api.domain.version.query.VersionControlQuery;
import com.ec.api.service.result.Result;
import com.ec.api.service.version.VersionService;
import com.ec.api.web.base.BaseController;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: iboy
 * Date: 2014-9-28
 * Time: 23:49:19
 */
@Controller
@RequestMapping("/v")
public class VersionController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(VersionController.class);

    private VersionService versionService;

    @RequestMapping(value="getVersion", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody
    Result getProvinces(VersionControlQuery versionControlQuery,HttpServletRequest request, HttpServletResponse response, ModelMap context){
        logger.info("versionControlQuery=="+versionControlQuery);
        Result result = new Result();
		if( StringUtils.isBlank(versionControlQuery.getVersionType()) ){
			result.setResultCode("1001");
			result.setResultMessage("versionType不能为空");
			return result;
		}
		return versionService.getVersionControlByVersionControlQuery(versionControlQuery);
	}

    public void setVersionService(VersionService versionService) {
        this.versionService = versionService;
    }
}
