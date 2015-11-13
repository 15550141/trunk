package demo;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.time.DateUtils;

import com.ec.api.common.utils.BFConstants;
import com.ec.api.common.utils.DESUtil;
import com.ec.api.common.utils.JsonUtils;
import com.ec.api.common.utils.MD5Util;
import com.ec.api.domain.OrderDetail;
import com.ec.api.domain.SkuList;
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
		
		System.out.println(DESUtil.encrypt("10003", BFConstants.loginCookieKey));
		
		
	}
}
