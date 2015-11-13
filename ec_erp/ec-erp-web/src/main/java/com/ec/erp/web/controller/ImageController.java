package com.ec.erp.web.controller;

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

import com.ec.erp.domain.IndexImage;
import com.ec.erp.service.IndexImageService;

import com.ec.erp.domain.query.IndexImageQuery;

@Controller
@RequestMapping("/image")
public class ImageController {

	private final static Log LOG = LogFactory.getLog(ImageController.class);
	@Autowired
	private IndexImageService indexImageService;
	

	@RequestMapping(value="/addImg", method={ RequestMethod.GET, RequestMethod.POST })
	public String addPro(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){

		return "image/addImage";
	}
	
	@RequestMapping(value="/edit", method={ RequestMethod.GET, RequestMethod.POST })
	public String edit(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		return "image/image";
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
		return null;
	}
	
	
	
	@RequestMapping(value="/addImage", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> addImage(HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String[] imageUrlArray=reuqest.getParameterValues("imageUrl");//图片保存地址
		String[] imageNameArray=reuqest.getParameterValues("imageName");//图名称
		String[] imageWebUrlArray=reuqest.getParameterValues("imageWebUrl");//图片外部访问连接
		String[] sortNumberArray=reuqest.getParameterValues("sortNumber");//图片外部访问连接
		try {
			IndexImage indexImage=new IndexImage();
			for(int i=0; i<imageUrlArray.length;i++){
				// TODO 插入到首页联播图表
				indexImage.setImageUrl(imageUrlArray[i]);
				indexImage.setImageName(imageNameArray[i]);
				indexImage.setImageWebUrl(imageWebUrlArray[i]);
				indexImage.setSortNumber(Integer.parseInt(sortNumberArray[i]));
				indexImage.setYn(1);
				indexImageService.insert(indexImage);
			}
		} catch (Exception e) {
			LOG.error("image.addImage:", e);
			resultMap.put("msg","error");
			return resultMap;
		}
		resultMap.put("msg","success");
		return resultMap;
		}
	
	
	
	@RequestMapping(value="/getIndexImage", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> getIndexImage(HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		IndexImageQuery indexImageQuery =new IndexImageQuery();
		try {	
			//List<Category> categoryList = categoryService.selectByCondition(categoryQuery);
			indexImageQuery.setYn(1);
			List<IndexImage> indexImageList =indexImageService.selectByCondition(indexImageQuery);
			//没有销售属性
			if(indexImageList!=null){
				resultMap.put("success","success");
				resultMap.put("indexImageList",indexImageList);
				return resultMap;
			}else{
				resultMap.put("success","error");
			}
		} catch (Exception e) {
			LOG.error("CategoryController.getLevel:", e);
		}
		return resultMap;
	}
	
	
	@RequestMapping(value="/deletIndexImage", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> deletIndexImage(Integer id, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		IndexImage indexImage = new IndexImage();
		try {	
			if(id!=null){
				indexImage.setId(id);
				indexImage.setYn(0);
				indexImageService.modify(indexImage);
			}
			resultMap.put("msg","success");
		} catch (Exception e) {
			LOG.error("CategoryController.deletProVal===", e);
		}
		return resultMap;
	}
	
	@RequestMapping(value="/modifyIndexImage", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map<String, Object> modifyIndexImage(Integer id,Integer sortNumber, HttpServletResponse response, HttpServletRequest request, ModelMap map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		IndexImage indexImage = new IndexImage();
		try {	
			if(id!=null){
				indexImage.setId(id);
			}
			if(sortNumber!=null){
				indexImage.setSortNumber(sortNumber);	
			}
			indexImageService.modify(indexImage);
			resultMap.put("msg","success");
		} catch (Exception e) {
			LOG.error("CategoryController.deletProVal===", e);
		}
		return resultMap;
	}
	
	
}