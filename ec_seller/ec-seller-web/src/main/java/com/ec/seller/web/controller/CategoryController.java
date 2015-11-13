package com.ec.seller.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import com.ec.seller.domain.Category;
import com.ec.seller.domain.Property;
import com.ec.seller.domain.PropertyValue;
import com.ec.seller.domain.query.CategoryQuery;
import com.ec.seller.domain.query.PropertyValueQuery;
import com.ec.seller.service.CategoryService;
import com.ec.seller.service.PropertyService;
import com.ec.seller.service.PropertyValueService;
import com.ec.seller.domain.query.PropertyQuery;


@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PropertyService propertyService;
	@Autowired
	PropertyValueService propertyValueService;
	
	private final static Log LOG = LogFactory.getLog(CategoryController.class);

	
//	@RequestMapping(value="/getLevelOne", method={ RequestMethod.GET, RequestMethod.POST })
//	public String queryPromotion(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
//
//		return "product/product";
//	}

	@RequestMapping(value="/getLevel", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> getLevel(Integer level,Integer pId,HttpServletResponse response, HttpServletRequest request, ModelMap map) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Category category=new Category();
		try {	
			CategoryQuery categoryQuery=new CategoryQuery();
			//设置查询几级分类
			if(level!=null){
				categoryQuery.setCategoryLevel(level);
			}
			if(pId!=null){
				categoryQuery.setParentCategoryId(pId);
			}
			List<Category> categoryList = categoryService.selectByCondition(categoryQuery);
			resultMap.put("msg","success");
			resultMap.put("categoryList",categoryList);//登录成功后，跳转到的页面
			resultMap.put("category",category);//登录成功后，跳转到的页面
		} catch (Exception e) {
			LOG.error("CategoryController.getLevel===", e);
		}
		return resultMap;
	}

	@RequestMapping(value="/addProVal", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addProVal(PropertyValue propertyValue, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			propertyValue.setVenderUserId(CookieUtil.getUserId(reuqest));
			propertyValueService.insert(propertyValue);
//			context.put("propertyValue", propertyValue);
			resultMap.put("msg","success");
			resultMap.put("propertyValue",propertyValue);
			//resultMap.put("category",category);
		} catch (Exception e) {
			LOG.error("CategoryController.addProVal===", e);
			resultMap.put("msg","error");
		}
		//return "item/addProperty";
		return resultMap;
	}

	
	@RequestMapping(value="/deletProVal", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> deletProVal(Integer proValId, HttpServletResponse response, HttpServletRequest request, ModelMap map) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {	
			if(proValId!=null){
				propertyValueService.deleteById(proValId);
			}
			resultMap.put("msg","success");
		} catch (Exception e) {
			LOG.error("CategoryController.deletProVal===", e);
		}
		return resultMap;
	}
	
	@RequestMapping(value="/getProperty", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> getProperty(Integer categoryId,HttpServletResponse response, HttpServletRequest request, ModelMap map) {
	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Category category=new Category();
		try {	
			CategoryQuery categoryQuery=new CategoryQuery();
			//设置查询几级分类
			if(categoryId!=null){
				categoryQuery.setCategoryId(categoryId);
			}
			List<Category> categoryList = categoryService.selectByCondition(categoryQuery);
			//没有销售属性
			if(categoryList!=null&&categoryList.get(0).getIfHaveSaleProperty()!=1){
				resultMap.put("success","success");
				resultMap.put("hasProperty","no");
				return resultMap;
			}else{
				resultMap.put("hasProperty","yes");
				
				//查询所有的销售属性和对应的值
				PropertyQuery propertyQuery=new PropertyQuery();
				propertyQuery.setCategoryId(categoryId);
				List<Property> propertyList = propertyService.selectByCondition(propertyQuery);
				if(propertyList.isEmpty()){
					//没有销售属性，提示用户添加销售属性
					resultMap.put("success","success");
					resultMap.put("haveProperyValue","no");
//					resultMap.put(key, value);
					resultMap.put("msg","该商品需要添加销售属性，请添加！");
					return resultMap;	
				}
				Property property = propertyList.get(0);
				resultMap.put("propertyId",property.getPropertyId());
				resultMap.put("propertyName",property.getPropertyName());
			
				PropertyValueQuery propertyValueQuery =new PropertyValueQuery();
				propertyValueQuery.setPropertyId(property.getPropertyId());
				propertyValueQuery.setVenderUserId(CookieUtil.getUserId(request));//设置商家ID
				List<PropertyValue> propertyValueList = propertyValueService.selectByCondition(propertyValueQuery);
				if(propertyValueList.isEmpty()){
					//商家没有销售属性，提示添加
					resultMap.put("success","success");
					resultMap.put("havePropery","no");
					resultMap.put("msg","该商品需要添加销售属性，请添加！");
					return resultMap;
				}
				resultMap.put("success","success");
				resultMap.put("propertyValueList",propertyValueList);
			}
			resultMap.put("msg","success");
			resultMap.put("category",category);//登录成功后，跳转到的页面
		} catch (Exception e) {
			LOG.error("CategoryController.getLevel:", e);
		}
		return resultMap;
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

	@RequestMapping(value="/upDescripImg", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> upDescripImg(HttpServletResponse response, HttpServletRequest request, ModelMap map) {
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
        			resultMap.put("err","文件为空！");
        			resultMap.put("msg","");
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
        resultMap.put("err","");
		resultMap.put("msg",savefilePath);
		//resultMap.put("imageUrl", savefilePath);	
		
		String jsonStr = "{\"err\":\"\",\"msg\":\""+savefilePath+"\"}";
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.print(jsonStr);
//		map.put("err", "");
//		map.put("msg",savefilePath);
		
	
		return null;
	}
	

	
}

