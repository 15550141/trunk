package com.ec.api.service.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ec.api.service.result.Result;

public class EcUtils {
	public static String getUsername(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getAttribute("username").toString();
	}
	
	public static void setExceptionResult(Result result){
		result.setSuccess(false);
		result.setResultCode("500");
		result.setResultMessage("网络异常，请稍后重试");
	}
	
	public static void setSuccessResult(Result result){
		result.setSuccess(true);
		result.setResultCode("200");
		result.setResultMessage("");
	}
}
