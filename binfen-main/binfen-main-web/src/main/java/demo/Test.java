package demo;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.time.DateUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ec.api.common.utils.BFConstants;
import com.ec.api.common.utils.DESUtil;
import com.ec.api.common.utils.JsonUtils;
import com.ec.api.common.utils.MD5Util;
import com.ec.api.domain.OrderDetail;
import com.ec.api.domain.PaymentInfo;
import com.ec.api.domain.SkuList;
import com.ec.api.domain.WxPay;
import com.ec.api.domain.WxPayCallback;
import com.ec.api.service.utils.WeixinUtils;
import com.ec.api.service.utils.WxJsConfig;

public class Test {
	public static void main(String[] args) throws Exception {
//		Calendar cal = Calendar.getInstance();
//		int year=cal.get(Calendar.YEAR);//得到年
//		int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
//		int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
//		String fileName = "D:\\imageUpload\\" + year +"\\" + month+"\\"+day;
//		String aa = "jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg&noncestr=1234567&timestamp=1445614863&url=http://www.binfenguoyuan.cn";
//		System.out.println(DigestUtils.shaHex(""));
		
//		WxJsConfig config = WeixinUtils.getWxJsConfig("http://www.binfenguoyuan.cn");
//		
//		System.out.println("nonceStr："+config.getNonceStr());
//		System.out.println("signature："+config.getSignature());
//		System.out.println("timestamp："+config.getTimestamp());
//		System.out.println("url："+config.getUrl());

//		String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
//		Calendar cal = Calendar.getInstance();
		
		
//		String a = "2015-1-1";
//		Date date = null;
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			date = sdf.parse(a);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		cal.setTime(date);
		
		
//		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
//		Integer month = cal.get(Calendar.MONTH)+ 1;
//		Integer dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
//		System.out.println("月："+month);
//		System.out.println("日："+dayOfMonth);
//		System.out.println("星期几："+weekDays[w]);
//		System.out.println("小时"+cal.get(Calendar.HOUR_OF_DAY));
//		
//		System.out.println(month+"-"+dayOfMonth+"|"+weekDays[w]);
//		
//		cal.add(Calendar.DATE, 5);
//		System.out.println((cal.get(Calendar.MONTH)+ 1)+"-"+cal.get(Calendar.DAY_OF_MONTH)+"|"+weekDays[(cal.get(Calendar.DAY_OF_WEEK) - 1)]);
		
//		System.out.println(DESUtil.encrypt("10003", BFConstants.loginCookieKey));
		
//		Document document = DocumentHelper.parseText("<xml><appid><![CDATA[wx2421b1c4370ec43b]]></appid><attach><![CDATA[支付测试]]></attach><bank_type><![CDATA[CFT]]></bank_type><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[10000100]]></mch_id><nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str><openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid><out_trade_no><![CDATA[1409811653]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign><sub_mch_id><![CDATA[10000100]]></sub_mch_id><time_end><![CDATA[20140903131540]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id></xml>");
//		Element node = document.getRootElement();
//		Element node1 = node.element("appid1");
//		if(node1 != null){
//			System.out.println(node1.asXML());
//			System.out.println(node1.getName() + ":"+node1.getTextTrim());
//		}else{
//			System.out.println("node1 == null");
//		}
//		WxPay wxPay = new WxPay();
//		wxPay.setAppid(BFConstants.appId);
		
//		System.out.println(DocumentHelper);
		
		
//		List<Element> e = node.selectNodes("appid");
//		System.out.println(e.get(0).getName()+":"+e.get(0).getText());
//		listNodes(node);
		
//		System.out.println(MD5Util.md5Hex("Yu_15550141"));
//		System.out.println(HttpUtils.httpPostData("https://api.mch.weixin.qq.com/pay/unifiedorder", "<xml><appid>wx1845cbfdc233b86b</appid><mch_id>1267232401</mch_id><device_info>WEB</device_info><nonce_str>yujianming</nonce_str><sign>8681AE82E00C9D15E9A38530ED2C7850</sign><body>陕西红心奇异果</body><out_trade_no>1000078</out_trade_no><total_fee>500</total_fee><spbill_create_ip>111.201.24.163</spbill_create_ip><notify_url>http://www.binfenguoyuan.cn/wxpay/wxCallBack</notify_url><trade_type>JSAPI</trade_type><openid>oETAJv0DR67KPB5u8IHUExp9Cm-8</openid></xml> ", "utf-8"));
		
		
//		Map<String, String> resultMap = new HashMap<String, String>();
//		resultMap.put("appId", BFConstants.appId);
//		resultMap.put("timeStamp", System.currentTimeMillis()/1000+"");
//		resultMap.put("nonceStr", "xianguoweidao");
//		resultMap.put("package", "prepay_id=wx20151123232638e2a0e153750219669787");
//		resultMap.put("signType", "MD5");
//		
//		StringBuilder sb = new StringBuilder();
//		sb.append("appId=").append(resultMap.get("appId")).append("&");
//		sb.append("nonceStr=").append(resultMap.get("nonceStr")).append("&");
//		sb.append("package=").append(resultMap.get("package")).append("&");
//		sb.append("signType=").append(resultMap.get("signType")).append("&");
//		sb.append("timeStamp=").append(resultMap.get("timeStamp")).append("&");
//		sb.append("key=").append(BFConstants.wxPaySkey);
//		String paySign = MD5Util.md5Hex(sb.toString());
//		System.out.println(resultMap.get("appId"));
//		System.out.println(resultMap.get("nonceStr"));
//		System.out.println(resultMap.get("package"));
//		System.out.println(resultMap.get("signType"));
//		System.out.println(resultMap.get("timeStamp"));
//		System.out.println(paySign);
		
		System.out.println(DateUtil.parseDate("20151125231928").getTime());;
		System.out.println(System.currentTimeMillis());
	}
	
	public static void listNodes(Element node){  
        System.out.println("当前节点的名称：" + node.getName());  
        //首先获取当前节点的所有属性节点  
        List<Attribute> list = node.attributes();  
        //遍历属性节点  
        for(Attribute attribute : list){  
            System.out.println("属性"+attribute.getName() +":" + attribute.getValue());  
        }  
        //如果当前节点内容不为空，则输出  
        if(!(node.getTextTrim().equals(""))){  
             System.out.println( node.getName() + "：" + node.getText());    
        }  
        //同时迭代当前节点下面的所有子节点  
        //使用递归  
        Iterator<Element> iterator = node.elementIterator();  
        while(iterator.hasNext()){  
            Element e = iterator.next();  
            listNodes(e);  
        }  
    }  
}
