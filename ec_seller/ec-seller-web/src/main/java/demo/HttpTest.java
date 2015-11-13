package demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ec.seller.common.utils.MD5Util;

public class HttpTest {
	private static String secret = "d18ed7feb9db4621b95da86943f7717f";
	public static void main(String[] args) {
//		String demo = "http://127.0.0.1/demo/aaa";
//		System.out.println(HttpUtils.httpPostData(demo, getSign(), null));
	}
	
	private static String getSign(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp = sdf.format(new Date());
		String v = "1.0";
		String username = "ceshi1";
		String sign = MD5Util.md5Hex(secret + timestamp + username + v + secret).toUpperCase();
		
		StringBuilder sb = new StringBuilder();
		sb.append("timestamp=").append(timestamp);
		sb.append("&").append("v=").append(v);
		sb.append("&").append("username=").append(username);
		sb.append("&").append("sign=").append(sign);
		
		return sb.toString();
	}
}
