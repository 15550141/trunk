//package com.ec.seller.web.intercept;
//
//import java.io.PrintWriter;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.ec.seller.common.utils.JsonUtils;
//import com.ec.seller.common.utils.MD5Util;
//import com.ec.seller.service.result.BaseResult;
//
//
//public class EcInterceptor  implements HandlerInterceptor {
//	private static final Logger log = LoggerFactory.getLogger(EcInterceptor.class);
//	
//	private final static String secret = "d18ed7feb9db4621b95da86943f7717f";
//	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	
//	@Override
//	public boolean preHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler) throws Exception {
//		BaseResult result = new BaseResult();
//		result.setSuccess(false);
//		
//		String username = request.getParameter("username");
//		String v = request.getParameter("v");
//		String timestamp = request.getParameter("timestamp");
//		String sign = request.getParameter("sign");
//		if(StringUtils.isBlank(username)){
//			this.printResult("1001", "username不能为空", response);
//			return false;
//		}
//		
//		if(StringUtils.isBlank(v)){
//			this.printResult("1001", "v不能为空", response);
//			return false;
//		}
//		
//		if(StringUtils.isBlank(timestamp)){
//			this.printResult("1001", "timestamp不能为空", response);
//			return false;
//		}
//		
//		if(StringUtils.isBlank(sign)){
//			this.printResult("1001", "sign不能为空", response);
//			return false;
//		}
//		
//		try{
//			Date date = sdf.parse(timestamp);
//			Date now = new Date();
//			if(Math.abs(now.getTime() - date.getTime()) > 30000){
//				this.printResult("1001", "时间不正确，时间差不能大于5分钟。服务器当前时间为："+sdf.format(now), response);
//				return false;
//			}
//		}catch (Exception e) {
//			log.error("", e);
//			this.printResult("1001", "时间格式不正确，必须是yyyy-MM-dd HH:mm:ss格式", response);
//			return false;
//		}
//		
//		String mysign = MD5Util.md5Hex(secret + timestamp + username + v + secret).toUpperCase();
//		if(!mysign.equals(sign)){
//			this.printResult("1001", "sign不正确", response);
//			return false;
//		}
//		
//		request.setAttribute("username", username);
//		
//		return true;
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request,
//			HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		
//	}
//	
//	private void printResult(String resultCode, String resultMessage, HttpServletResponse response) throws Exception{
//		BaseResult result = new BaseResult();
//		PrintWriter pr = response.getWriter();
//		response.setHeader("Content-Type", "application/json;charset=UTF-8");
//		result.setSuccess(false);
//		result.setResultCode(resultCode);
//		result.setResultMessage(resultMessage);
//		pr.print(JsonUtils.writeValue(result));
//	}
//	
//	public static void main(String[] args) {
//	}
//}
