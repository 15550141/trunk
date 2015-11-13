package com.ec.api.web.intercept;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ec.api.common.utils.BFConstants;
import com.ec.api.common.utils.CookieUtils;
import com.ec.api.common.utils.DESUtil;
import com.ec.api.common.utils.JsonUtils;
import com.ec.api.service.result.Result;



public class LoginInterceptor  implements HandlerInterceptor {
	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
	
	private final static String secret = "d18ed7feb9db4621b95da86943f7717f";
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		try{
			StringBuffer url = request.getRequestURL();
			if(url.toString().indexOf("oauth") > 0){
				return true;
			}
			
			if (request.getQueryString() != null) {
				url.append("?");
				url.append(request.getQueryString());
			}
			
			Integer uid = CookieUtils.getUid(request);
			
			if(uid == null || uid == 0){
				CookieUtils.clearCookie("uid", response);
				response.sendRedirect("http://www.binfenguoyuan.cn/oauth/login?rurl="+url.toString());
				return false;
			}
			return true;
		}catch (Exception e) {
			log.error("", e);
			this.printResult("1001", "token不正确", response);
		}
		return false;
	}
	
	
//	@Override
//	public boolean preHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler) throws Exception {
//		try{
//			String requestPath = request.getRequestURI();
//		    if(requestPath.contains("/sell/item/imageUpload")){
//		    	return true;
//		    }
//            if(requestPath.contains("/v/getVersion")){
//		    	return true;
//		    }
//			String token = request.getParameter("token");
//			if(StringUtils.isBlank(token)){
//				this.printResult("1001", "token不能为空", response);
//				return false;
//			}
//			String value = DESUtil.decrypt(token, "gs2y601z");
//			if(StringUtils.isBlank(value)){
//				this.printResult("1001", "token不正确", response);
//				return false;
//			}
//			
//			String [] tokenArr = value.split("-");
//			if(tokenArr == null || tokenArr.length != 3){
//				this.printResult("1001", "token不正确", response);
//				return false;
//			}
//			
//			request.setAttribute("userId", tokenArr[0]);
//			request.setAttribute("mobile", tokenArr[1]);
//			request.setAttribute("userType", tokenArr[2]);
//			
//			return true;
//		}catch (Exception e) {
//			log.error("", e);
//			this.printResult("1001", "token不正确", response);
//		}
//		return false;
//	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
	private void printResult(String resultCode, String resultMessage, HttpServletResponse response) throws Exception{
		Result result = new Result();
		PrintWriter pr = response.getWriter();
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		result.setSuccess(false);
		result.setResultCode(resultCode);
		result.setResultMessage(resultMessage);
		pr.print(JsonUtils.writeValue(result));
	}
	
	public static void main(String[] args) {
	}
}
