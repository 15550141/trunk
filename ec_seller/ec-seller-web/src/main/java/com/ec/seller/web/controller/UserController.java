package com.ec.seller.web.controller;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.ec.seller.common.utils.CookieUtil;
import com.ec.seller.domain.BusinessUserExt;
import com.ec.seller.domain.Item;
import com.ec.seller.domain.UserInfo;
import com.ec.seller.domain.query.ItemQuery;
import com.ec.seller.domain.query.UserInfoQuery;
import com.ec.seller.service.BusinessUserExtService;
import com.ec.seller.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	BusinessUserExtService businessUserExtService;
	
	private final static Log LOG = LogFactory.getLog(UserController.class);
	
	
	@RequestMapping(value="/bank", method={ RequestMethod.GET, RequestMethod.POST })
	public String bank(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){

		return "user/addBank";
	}

	
	/**
	 * 添加银行信息
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/addBank", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addBank(BusinessUserExt businessUserExt,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		businessUserExt.setUserId(CookieUtil.getUserId(reuqest));
		businessUserExt.setBankCreated(new Date());
		businessUserExt.setBankStatus(0);
		try {
			businessUserExtService.modifyByUserId(businessUserExt);
		} catch (Exception e) {
			resultMap.put("msg","error");
			return resultMap;
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	
	/**
	 * 添加银行信息
	 * @param reuqest
	 * @param response
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/addUser", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addUser(BusinessUserExt businessUserExt,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		businessUserExt.setUserId(CookieUtil.getUserId(reuqest));
		try {
			businessUserExtService.modifyByUserId(businessUserExt);
		} catch (Exception e) {
			LOG.error("user/addUser:",e);
			resultMap.put("msg","error");
			return resultMap;
		}
		resultMap.put("msg","success");
		return resultMap;
	}
	
	@RequestMapping(value="/showBank", method={ RequestMethod.GET, RequestMethod.POST })
	public String showBank(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){


		Map<String, Object> resultMap = new HashMap<String, Object>();
		BusinessUserExt businessUserExt =new BusinessUserExt();
		try {
			//根据用户ID查商家扩展信息
			businessUserExt = businessUserExtService.selectByUserId(CookieUtil.getUserId(reuqest));
			//resultMap = itemService.queryItemList(itemQuery);
		} catch (Exception e) {
			LOG.error("User.showBank:", e);
		}
		if(businessUserExt.getBank()!=null){
			context.put("businessUserExt", businessUserExt);
			return "user/bankInfo";
		}else{
			return "user/addBank";
		}
		
		
	}
	
	@RequestMapping(value="/showUser", method={ RequestMethod.GET, RequestMethod.POST })
	public String showUser(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){


		Map<String, Object> resultMap = new HashMap<String, Object>();
		BusinessUserExt businessUserExt =new BusinessUserExt();
		try {
			//根据用户ID查商家扩展信息
			businessUserExt = businessUserExtService.selectByUserId(CookieUtil.getUserId(reuqest));
			//resultMap = itemService.queryItemList(itemQuery);
		} catch (Exception e) {
			LOG.error("User.showBank:", e);
		}
		if(businessUserExt.getQualificationUrl()!=null){
			context.put("businessUserExt", businessUserExt);
			return "user/userInfo";
		}else{
			return "user/addUser";
		}
		
		
	}
	
	@RequestMapping(value="/uploaImage", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> uploaImage(Integer imageId,HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String path = null;
		String savefilePath=null;
        //创建一个通用的多部分解析器.     
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext()); 
        //判断 request 是否有文件上传,即多部分请求...    
        if(multipartResolver.isMultipart(request))  
        {  
             //判断 request 是否有文件上传,即多部分请求...    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request);  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                MultipartFile file = multiRequest.getFile(iter.next());
                if(StringUtils.isBlank(file.getOriginalFilename())){//文件为空
        			resultMap.put("msg","null");
        			return resultMap;
                }

                //String rootPath = request.getSession().getServletContext().getRealPath("/");
                String fileName = file.getOriginalFilename();
                String fileType=fileName.substring(fileName.lastIndexOf("."));
                int choice=97;
                Random random=new Random();
                char var = (char) (choice + random.nextInt(26)); 
                fileName= "p"+ var +(int)(Math.random() * 1000000)+fileType;
                
                
				Calendar cal = Calendar.getInstance();
				int year=cal.get(Calendar.YEAR);//得到年
				int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
				int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
				
				path = "/img" + "/" + year +"/" + month+"/"+day+"/";
				savefilePath = "/www.tbny.net" + path;
				File foler = new File(savefilePath);
				if(!foler.exists()){
					foler.mkdirs();
				}
				
				File savedFile = new File(savefilePath, fileName);
				path +=  fileName;
				savefilePath ="http:/" + savefilePath + fileName;
                try {
					file.transferTo(savedFile);
				} catch (IllegalStateException e) {
					LOG.error("CategoryController.uploaImage:", e);
				} catch (IOException e) {
					LOG.error("CategoryController.uploaImage:", e);
				}  
            }  
        }  
		resultMap.put("msg","success");
		resultMap.put("imageUrl", savefilePath);	
		String jsonStr = "{\"msg\":\"success\",\"imageUrl\":\""+savefilePath+"\"}";
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.print(jsonStr);
//		map.put("msg", "success");
//		map.put("imageUrl",savefilePath);
	
		return null;
	}



}
