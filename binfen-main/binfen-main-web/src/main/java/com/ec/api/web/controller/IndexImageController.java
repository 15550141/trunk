package com.ec.api.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ec.api.service.AddressService;
import com.ec.api.service.IndexImageService;
import com.ec.api.service.result.Result;
import com.ec.api.web.base.BaseController;

@Controller
@RequestMapping("/indexImage")
public class IndexImageController extends BaseController {
	private IndexImageService indexImageService;
	
	@RequestMapping(value="getIndexImages", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result getIndexImages(HttpServletRequest request,HttpServletResponse response, ModelMap context){
		return indexImageService.getIndexImages();
	}

	public void setIndexImageService(IndexImageService indexImageService) {
		this.indexImageService = indexImageService;
	}


}
