package demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ec.api.common.utils.JsonUtils;
import com.ec.api.common.utils.MD5Util;

public class HttpTest {
	private static String secret = "d18ed7feb9db4621b95da86943f7717f";
	private static String domain = "http://www.tbny.net";
//	private static String domain = "http://localhost:8080";
	private static String pwd = "e10adc3949ba59abbe56e057f20f883e";//123456
	private static String token = "eb13d4a2b1541d854fbd24d87773f94d";     //4-13911191079-2
//	private static String token = "c329ad9e258c9d26c032dabb454eecdb";   //6-13693068578-1
//	private static String sellToken = "378dad4ea259826e4f7d05d733f944e7";
	private static String sellToken = "eb13d4a2b1541d854fbd24d87773f94d";

	private static final Logger log = LoggerFactory.getLogger(HttpTest.class);
	
	public static void main(String[] args) throws Exception {
//		String demo = "http://www.tbny.com/demo/aaa";
//		System.out.println(HttpUtils.httpPostData(demo, getSign(), null));
		
		//登陆方法
//		String url = "/user/buy/login?mobile=13333333333&password=e10adc3949ba59abbe56e057f20f883e";
//		判断手机是否能够注册
//		String url = "/user/checkMobile?mobile=13214231123";
		//根据用户id获取用户信息
//		String url = "/user/getUserInfoByUserId?userId=1";
		//买家注册
//		String url = "/user/buy/reg?mobile=13333333333&password=123456&code=1113";
		
		//买家修改密码
//		String url = "/userInfo/updatePwd?oldPwd=123456&newPwd=123456";
		//根据userId获取用户信息
//		String url = "/user/getUserInfoByUserId?userId=1";

//        String url = "/businessUser/getBusinessUserByUserId?userId=1";

        //查看商家信息
//        String url = "/businessUser/getBusinessUserByUserId?userId=31";

        //获得最新版本
//        String url = "/v/getVersion?versionType=1&versionNo=3";
		
		//卖家注册
//		String url = "/user/sell/reg?mobile=13333333334&password=123456&code=1113&shopName=asd123";
		//卖家登陆
//		String url = "/user/sell/login?mobile=13333333334&password=e10adc3949ba59abbe56e057f20f883e";
		
		//获取一级地址
//		String url = "/address/getProvinces";
		//获取二级地址
//		String url = "/address/getCities?province=1";
		//获取三级地址
//		String url = "/address/getCounties?city=72";
		
		
		//查询所有一级分类信息列表
//		String url = "/category/getAllParentCategory";
		//依据一级分类查询下属二级分类列表
//		String url = "/category/getCategoryByParentId?parentId=148";
		//查询二级分类下属性销售属性信息
//		String url = "/category/getSalePropertiesByCategoryId?categoryId=5";
		//依据属性ID及商家ID查询自定义属性值信息列表
//		String url = "/sell/category/getPropertyValuesByPropertyId?propertyId=2";
		//添加自定义属性值信息
//		String url = "/sell/category/addPropertyValue?propertyId=2&propertyValueName=shangpin&sortNumber=1";
		//修改自定义属性值信息
//		String url = "/sell/category/updatePropertyValue?propertyValueId=10&sortNumber=2";

		//依据商家ID获取所有该商家商品信息
//		String url = "/sell/item/getItemsByVenderUserId";
		//根据条件查询商品相信信息列表
//		String url = "/sell/item/getItemByItemQuery";
////		依据商品ID查看商品详情
		String url = "/item/getItemByItemId?itemId=9";
		//更改sku 价格、库存、最低起买量
//		String url = "/sell/item/updateSku?skuId=1&leastBuy=2&tbPrice=101&stock=1001";
		
		//新增商品信息
//		String url = "/sell/item/addItem?itemName=aaa&itemType=1&categoryId1=1&categoryId2=5&skus=[{\"tbPrice\":100,\"stock\":1000},{\"tbPrice\":100,\"stock\":1000}]";
		//修改商品信息
//		String url = "/sell/item/updateItem?itemId=114&itemType=2";
		//商品介绍查看
//		String url = "/item/description/getItemDescriptionByItemId?itemId=3";
		
		//添加
//		String url = "/item/description/getItemDescriptionByItemId?itemId=3";
		
		//商品介绍添加
//		String url = "/sell/item/description/addItemDescription?itemId=3&appDescriptionInfo=33";
		//修改商品介绍
//		String url = "/sell/item/description/updateItemDescription?itemId=3&appDescriptionInfo=44";
		//修改商品介绍
//		String url = "/sell/item/description/insertOrUpdate?itemId=11&appDescriptionInfo=66";
		
		//设置图片地址接口
//		String url = "/sell/item/addItemImage?itemId=11&imageUrl=123a&sortNumber=1";
        //批量设置图片地址接口
//		String url = "/sell/item/addItemImages?itemId=169&imageUrls=123-3,123-4";
		
		//依据SKU_ID查看SKU信息
//		String url = "/sku/getSkuBySkuId?skuId=10001";
		//依据商品ID查询商品下SKU信息
//		String url = "/item/getSkusByItemId?itemId=3";
		//依据商品ID查询商品轮播图
//		String url = "/item/getItemImages?itemId=120";
		
		
		//依据商家ID查询商家所属促销列表查看
//		String url = "/sell/promotionInfo/getPromotionInfos";
		//新增促销信息
//		String url = "/sell/promotionInfo/addPromotionInfo?promotionName=421&itemId=3&promotionType=1&purchaseNumberMin=1&purchaseNumberMax=1000";
		//查看促销详细信息
//		String url = "/promotionInfo/getPromotionInfoByPromotionId?promotionId=24";
		//查看促销详细信息
//		String url = "/sell/promotionInfo/updatePromotionInfo?promotionId=24&promotionName=222";
		//商家关闭促销
//		String url = "/sell/promotionInfo/closePromotion?promotionId=24";
		
		//买家APP首页，促销-更多A标签分页查询
//		String url = "/promotionInfo/getPromotionInfos?specialFlag=1";
		
		
//		商家所属列表信息查看（细分）
//		String url = "/orderInfo/getOrderInfosByVenderUserId";
		//用户所属订单列表查看（细分）
//		String url = "/orderInfo/getOrderInfosByUserId?orderId=";
//		下单
//		String url = "/orderInfo/submit?skus=[{\"skuId\":630,\"itemId\":371,\"num\":1}]&orderType=1&consigneeName=1&consigneeProvince=1&consigneeCity=1&consigneeCounty=1&consigneeAddress=1&consigneeMobile=1&venderUserId=2";
		//商家确认收款
//		String url = "/orderInfo/confirmGetPrice?orderId=3";
		//商家确认发货
//		String url = "/orderInfo/confirmSendOut?orderId=10000820";
		//用户收货确认
//		String url = "/orderInfo/confirmGetGoods?orderId=10000820";
		//商家订单完成确认
//		String url = "/orderInfo/orderSuccess?orderId=3";
		//商家订单锁定
//		String url = "/orderInfo/lockOrder?orderId=3&lockReason=121";
		//商家订单解锁
//		String url = "/orderInfo/unlockOrder?orderId=3";
		//判断商家名称是否可以注册
//		String url = "/user/checkShopName?shopName=11";
		//发送注册短信验证码
//		String url = "/user/sendCode?mobile=13333333303";
		//根据订单号和用户id获取订单详细信息
//		String url = "/orderInfo/getOrderInfoByOrderIdAndUserId?orderId=10000200";
        //根据订单号和用户id获取订单状态信息
//		String url = "/orderInfo/getOrderStatusByOrderIdAndUserId?orderId=10000200";
		//根据订单号和商家id获取订单详细信息
//		String url = "/orderInfo/getOrderInfoByOrderIdAndVenderUserId?orderId=4";
		//生成结算订单号。
//		String url = "/orderInfo/createTradeNo?orderId=10000818";
		
		//发送找回登陆密码验证码
//		String url = "/user/sendFindPwdCode?mobile=13333333333";
		//验证找回密码短信验证码
//		String url = "/user/validFindPwdCode?mobile=13333333333&code=745185";
		//重置登陆密码
//		String url = "/user/resetPwd?k=caa52824977f7af494034e99eeb47eec&password=123456";
		
		//用户收货人信息列表查询
//		String url = "/consigneeInfo/getConsigneeInfosByUserId";
		//用户新增收货人信息
//		String url = "/consigneeInfo/addConsigneeInfo?consigneeName=aaa&consigneeProvince=1&consigneeCity=2&consigneeCounty=3&consigneeAddress=123asdc&consigneeMobile=13423123214";
		//用户修改收货人信息
//		String url = "/consigneeInfo/updateConsigneeInfo?consigneeId=1&consigneeName=bbb";
		//用户删除收货人信息
//		String url = "/consigneeInfo/delConsigneeInfo?consigneeId=1";
		//根据skuId查询促销信息
//		String url = "/promotionInfo/getPromotionInfoBySkuId?skuId=10001";
		//根据itemId查询促销信息
//		String url = "/promotionInfo/getPromotionInfoByItemId?itemId=4";
		//获得首页折扣区top6促销商品信息
//		String url = "/promotionInfo/getIndexTop6DiscountPromotionInfo";
		//获得首页产地特供区top6促销商品信息
//		String url = "/promotionInfo/getIndexTop6SpecialPromotionInfo";
		
		//添加关注                                                  
//		String url = "/favorites/addFavorites?itemId=169";
		//查询关注
//		String url = "/favorites/getFavoritesByFavoritesId?favoritesId=1";
		//分页查询关注
//		String url = "/favorites/getFavoritesByPage";
		//删除关注
//		String url = "/favorites/delFavoritesBtFavoritesId?favoritesId=5";
		//判断是否关注商品
//		String url = "/favorites/checkItemFavorites?itemId=167";
// 		批量判断是否关注商品
//		String url = "/favorites/checkItemsFavorites?itemId=167,168,169";

		
		//记账功能
//		String url = "/sellerBookkeeping/addSellerBookkeeping?linkman=aaa&mobile=13242312311&paymentMoney=1000&tradeMoney=2000&companyName=1&itemPrice=1600&itemName=asd123";
		//分页查询记账功能接口
//		String url = "/sellerBookkeeping/getSellerBookkeepingByPage?startTime="+URLEncoder.encode("2014-01-20 11:11:11", "utf-8")+"&endTime="+URLEncoder.encode("2014-8-17 10:35:00", "utf-8");
//		统计金额接口
//		String url = "/sellerBookkeeping/selectSellerBookkeepingForCountMoney?startTime="+URLEncoder.encode("2014-01-20 11:11:11", "utf-8")+"&endTime="+URLEncoder.encode("2014-8-17 10:35:00", "utf-8");

		//订单支付接口
//		String url = "/paymentInfo/addPaymentInfo?orderId=4&orderPayType=1&paymentMode=1&paymentInfoType=1&paymentMoney=100&paymentNumber=1&busiPartner=a&dtOrder=1&validOrder=1&settleDate=1&bankName=1&bankCode=1";
		//订单支付成功接口
//		String url = "/paymentInfo/addPaymentInfo?orderId=4&orderPayType=1&paymentMode=1&paymentInfoType=2&paymentMoney=100&paymentNumber=1&busiPartner=a&dtOrder=1&validOrder=1&settleDate=1&bankName=1&bankCode=1";
		//尾款支付接口
//		String url = "/paymentInfo/addPaymentInfo?orderId=4&orderPayType=2&paymentMode=1&paymentInfoType=1&paymentMoney=100&paymentNumber=1&busiPartner=a&dtOrder=1&validOrder=1&settleDate=1&bankName=1&bankCode=1";
		//尾款支付成功接口
//		String url = "/paymentInfo/addPaymentInfo?orderId=4&orderPayType=2&paymentMode=1&paymentInfoType=2&paymentMoney=100&paymentNumber=1&busiPartner=a&dtOrder=1&validOrder=1&settleDate=1&bankName=1&bankCode=1";
		//查询支付接口
//		String url = "/paymentInfo/getPaymentInfos?orderId=4&paymentInfoType=";
		
//		商家补录订单金额接口
//		String url = "/sellerEntry/addSellerEntry?orderId=4&orderPayType=1&paymentMode=1&paymentMoney=1&busiPartner=1&remark=1";
		//查询支付接口
//		String url = "/sellerEntry/getSellerEntrys?orderId=4";

		//首页轮播图接口
//		String url = "/indexImage/getIndexImages";
		
		
		//商品图片上传，返回图片地址
//		String url = "/sell/item/imageUpload";
//		String fromUrl = "D:\\imageUpload\\1.jpeg";
//		System.out.println(HttpUtils.uploadImage(domain+url+"?"+getSign() +"&token="+sellToken, fromUrl));
		
//		System.out.println(domain + url+"&" +getSign() + "&token="+token);
//		System.out.println(HttpUtils.httpPostData(domain + url, getSign() + "&token="+token, null));
		
//		System.out.println(HttpUtils.httpGetData("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+URLEncoder.encode("OezXcEiiBSKSxW0eoylIeGww1Qr0IJP-ZV9qVqQRUp0QacNDdYOgaVbF8NBCJCZqxEDzamDnt72QBPGAg0xQjB1RVCZXRMeQmMnfGe6svIPtA0Ti2y7aIA6peDcCB9zCG6yFyHjSVBfAH4zXrOpxMQ","utf-8")+"&type=jsapi", null, null));
//		down();
//		log.error("OK"); 
		
		sendTemplateMessage();
	}
	
	private static void sendTemplateMessage(){
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=zei4LBZ4bREUD4y3PzeThsS2TLd9qFcdUEFmZICmfpBTKZZE60MLuHNXJ6bdB39PWiCdQ304VbFFHDTe-hQ6JqaQIzFrEj9-k-9HUuLLaFYLVJaAGAAKY";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("touser", "oETAJv0DR67KPB5u8IHUExp9Cm-8");//openid
		map.put("template_id", "tJ_N7VPWkQblYNIMj0mAxbzSFtSIg_k7EqP-BKq-BTE");
		map.put("url", "http://www.binfenguoyuan.cn/order/my");
		map.put("topcolor", "#FF0000");
		Map<String, Map<String, String>> dataMap = new HashMap<String, Map<String, String>>();
		Map<String, String> first = new HashMap<String, String>();
		first.put("value", "尊敬的客户，您的订单已提交成功！");
		first.put("color", "#173177");
		Map<String, String> keyword1 = new HashMap<String, String>();
		keyword1.put("value", "此处是订单号");
		keyword1.put("color", "#173177");
		Map<String, String> keyword2 = new HashMap<String, String>();
		keyword2.put("value", "此处是金额");
		keyword2.put("color", "#173177");
		Map<String, String> keyword3 = new HashMap<String, String>();
		keyword3.put("value", "我是商品名字");
		keyword3.put("color", "#173177");
		Map<String, String> keyword4 = new HashMap<String, String>();
		keyword4.put("value", "此处是时间");
		keyword4.put("color", "#173177");
		Map<String, String> remark = new HashMap<String, String>();
		remark.put("value", "请您尽快支付，如已支付请忽略。");
		remark.put("color", "#173177");
		
		dataMap.put("first", first);
		dataMap.put("keyword1", keyword1);
		dataMap.put("keyword2", keyword2);
		dataMap.put("keyword3", keyword3);
		dataMap.put("keyword4", keyword4);
		dataMap.put("remark", remark);
		map.put("data", dataMap);
		
		System.out.println(HttpUtils.httpPostData(url, JsonUtils.writeValue(map), "utf-8"));
		
	}
	
//	private static String getSign() throws Exception{
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String timestamp = sdf.format(new Date());
//		String v = "1.0";
//		String sign = MD5Util.md5Hex(secret + timestamp + v + secret).toUpperCase();
//		
//		StringBuilder sb = new StringBuilder();
//		sb.append("timestamp=").append(URLEncoder.encode(timestamp, "utf-8"));
//		sb.append("&").append("v=").append(v);
//		sb.append("&").append("sign=").append(sign);
//		
//		return sb.toString();
//	}
	
	private final static String REMOTE_FILE_URL = "http://m.fruitday.com/fonts//fonts/glyphicons-halflings-regular.svg";  
    
    private final static int BUFFER = 1024;  
    
	private static void down(){
		HttpClient client = new HttpClient();  
		GetMethod httpGet = new GetMethod(REMOTE_FILE_URL);  
        try {  
            client.executeMethod(httpGet);  
              
            InputStream in = httpGet.getResponseBodyAsStream();  
             
            FileOutputStream out = new FileOutputStream(new File("D:\\data\\glyphicons-halflings-regular.svg"));  
             
            byte[] b = new byte[BUFFER];  
            int len = 0;  
            while((len=in.read(b))!= -1){  
                out.write(b,0,len);  
            }  
            in.close();  
            out.close();  
              
        }catch (HttpException e){  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally{  
            httpGet.releaseConnection();  
        }  
        System.out.println("download, success!!");  
	}  
}
