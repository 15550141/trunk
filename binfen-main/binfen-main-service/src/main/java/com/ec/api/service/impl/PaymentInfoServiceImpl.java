package com.ec.api.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.ec.api.common.utils.BFConstants;
import com.ec.api.common.utils.BFUtils;
import com.ec.api.common.utils.HttpUtils;
import com.ec.api.common.utils.JsonUtils;
import com.ec.api.common.utils.MD5Util;
import com.ec.api.dao.AccessTokenDao;
import com.ec.api.dao.OrderInfoDao;
import com.ec.api.dao.PaymentInfoDao;
import com.ec.api.dao.TaskDao;
import com.ec.api.domain.AccessToken;
import com.ec.api.domain.OrderInfo;
import com.ec.api.domain.PaymentInfo;
import com.ec.api.domain.Task;
import com.ec.api.domain.UserInfo;
import com.ec.api.domain.WxPayCallback;
import com.ec.api.domain.WxPay;
import com.ec.api.domain.query.PaymentInfoQuery;
import com.ec.api.service.OrderInfoService;
import com.ec.api.service.PaymentInfoService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.EcUtils;

@Service(value="paymentInfoService")
public class PaymentInfoServiceImpl implements PaymentInfoService {
	private static final Logger log = LoggerFactory.getLogger(PaymentInfoServiceImpl.class);

	private PaymentInfoDao paymentInfoDao;
	private OrderInfoDao orderInfoDao;
	private DataSourceTransactionManager transactionManager;
	
	private OrderInfoService orderInfoService;
	private AccessTokenDao accessTokenDao;
	private TaskDao taskDao;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	//用户发起支付请求
	@Override
	public Result userCreatePayment(PaymentInfo paymentInfo) {
		Result result = new Result();
		try{
			if(paymentInfo == null || paymentInfo.getOrderId() == null || paymentInfo.getUid() == null){
				log.error("访问参数不正确");
				result.setSuccess(false);
				result.setResultMessage("参数不正确");
				return result;
			}
			//获取订单信息
			OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderIdAndUserId(paymentInfo.getOrderId(), paymentInfo.getUid());
			if(orderInfo == null){
				log.error("微信支付，订单不存在	orderId="+paymentInfo.getOrderId() + "	uid="+paymentInfo.getUid());
				result.setSuccess(false);
				result.setResultMessage("该订单号不存在");
				return result;
			}
			paymentInfo.setOrderId(orderInfo.getOrderId());
			paymentInfo.setPaymentInfoType(1);//发起支付类型
			
			paymentInfo.setPaymentMoney(orderInfo.getOrderMoney());//订单总金额
			//第三方支付单号
			String paymentNumber = this.getPrepayId(paymentInfo, orderInfo);
			if(StringUtils.isBlank(paymentNumber)){
				result.setSuccess(false);
				result.setResultMessage("调用微信支付失败，请稍后重试");
				return result;
			}
			paymentInfo.setPaymentNumber(paymentNumber);
			
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, 1);
			paymentInfo.setValidOrder(c.getTime());
			
			paymentInfoDao.insert(paymentInfo);
			
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("appId", BFConstants.appId);
			resultMap.put("timeStamp", System.currentTimeMillis()/1000+"");
			resultMap.put("nonceStr", "xianguoweidao");
			resultMap.put("package", "prepay_id="+paymentNumber);
			resultMap.put("signType", "MD5");
			resultMap.put("paySign", this.getPaySign(resultMap));
			
			log.error("appId:"+resultMap.get("appId"));
			log.error("timeStamp:"+resultMap.get("timeStamp"));
			log.error("nonceStr:"+resultMap.get("nonceStr"));
			log.error("package:"+resultMap.get("package"));
			log.error("signType:"+resultMap.get("signType"));
			log.error("paySign:"+resultMap.get("paySign"));
			
			result.setResult(resultMap);
			result.setSuccess(true);
		}catch (Exception e) {
			log.error("添加支付信息异常	orderId="+paymentInfo.getOrderId(), e);
			EcUtils.setExceptionResult(result);
		}
		
		return result;
	}
	
	private String getPaySign(Map<String, String> map){
		StringBuilder sb = new StringBuilder();
		sb.append("appId=").append(map.get("appId")).append("&");
		sb.append("nonceStr=").append(map.get("nonceStr")).append("&");
		sb.append("package=").append(map.get("package")).append("&");
		sb.append("signType=").append(map.get("signType")).append("&");
		sb.append("timeStamp=").append(map.get("timeStamp")).append("&");
		sb.append("key=").append(BFConstants.wxPaySkey);
		return MD5Util.md5Hex(sb.toString());
	}
	
	//微信支付结果回调函数
	@Override
	public String wxCallback(String callbackString) {
		log.error("腾讯微信支付回调接口："+callbackString);
		try{
			WxPayCallback wxPayCallback = parseXmlToWxPayCallback(callbackString);
			if(wxPayCallback == null){
				return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[NOT OK]]></return_msg></xml>";
			}
			
			if(!"success".equalsIgnoreCase(wxPayCallback.getReturn_code()) || !"success".equalsIgnoreCase(wxPayCallback.getResult_code())){
				log.error("腾讯给返回的不是成功，是这玩应："+callbackString);
				return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
			}
		
			final PaymentInfo paymentInfo = new PaymentInfo();
			//获取订单信息
			final OrderInfo orderInfo = orderInfoDao.selectByOrderId(Integer.parseInt(wxPayCallback.getOut_trade_no()));
			if(orderInfo == null){
				log.error("腾讯微信支付回调接口：订单号不存在	"+wxPayCallback.getOut_trade_no());
				return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
			}
			
			if(orderInfo.getOrderStatus() > 8){
				log.error("该订单已支付过，无需再次支付，当前订单状态："+orderInfo.getOrderStatus() + "	订单号："+wxPayCallback.getOut_trade_no());
				return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
			}
			
			//添加了一条支付信息
			paymentInfo.setUid(orderInfo.getUserId());
			paymentInfo.setOrderId(orderInfo.getOrderId());
			paymentInfo.setOrderPayType(1);//微信支付
			paymentInfo.setPaymentInfoType(2);//发起支付类型
			paymentInfo.setPaymentInfoMessage(callbackString);//此处放订单详细信息
			paymentInfo.setPaymentMoney(wxPayCallback.getTotal_fee());//订单总金额
			paymentInfo.setPaymentNumber(wxPayCallback.getTransaction_id());//第三方支付单号
			paymentInfo.setDtOrder(sdf.parse(wxPayCallback.getTime_end()));
			
			new TransactionTemplate(transactionManager).execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					Integer paymentId = paymentInfoDao.insert(paymentInfo);
					
					//修改订单状态为支付完成
					orderInfo.setOrderStatus(4);//修改订单状态为已支付完成，等待给客户回电，确认发货时间状态
					orderInfo.setPaymentId(paymentId);
					orderInfoDao.modify(orderInfo);
					
					
					//添加任务表
					Task task = new Task();
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("orderId", orderInfo.getOrderId());
					map.put("userId", orderInfo.getUserId());
					task.setContent(JsonUtils.writeValue(map));//内容
					task.setStatus(0);//初始状态
					task.setType(2);//支付成功
					task.setYn(1);//有效
					taskDao.insert(task);
				}
			});
			
			log.error("给腾讯返回的是成功！	订单号："+orderInfo.getOrderId());
			return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
		}catch (Exception e) {
			log.error("添加支付信息异常	callbackString="+callbackString, e);
			return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[NOT OK]]></return_msg></xml>";
		}
		
	}
	
	private WxPayCallback parseXmlToWxPayCallback(String xml){
		try {
			WxPayCallback wxPayCallback = new WxPayCallback();
			Document doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			Element return_code = root.element("return_code");
			if(return_code != null){
				wxPayCallback.setReturn_code(return_code.getTextTrim());
			}
			
			Element return_msg = root.element("return_msg");
			if(return_msg != null){
				wxPayCallback.setReturn_msg(return_msg.getTextTrim());
			}
			
			Element appid = root.element("appid");
			if(appid != null){
				wxPayCallback.setAppid(appid.getTextTrim());
			}
			
			Element mch_id = root.element("mch_id");
			if(mch_id != null){
				wxPayCallback.setMch_id(mch_id.getTextTrim());
			}
			
			Element device_info = root.element("device_info");
			if(device_info != null){
				wxPayCallback.setDevice_info(device_info.getTextTrim());
			}
			
			Element nonce_str = root.element("nonce_str");
			if(nonce_str != null){
				wxPayCallback.setNonce_str(nonce_str.getTextTrim());
			}
			
			Element sign = root.element("sign");
			if(sign != null){
				wxPayCallback.setSign(sign.getTextTrim());
			}
			
			Element result_code = root.element("result_code");
			if(result_code != null){
				wxPayCallback.setResult_code(result_code.getTextTrim());
			}
			
			Element err_code = root.element("err_code");
			if(err_code != null){
				wxPayCallback.setErr_code(err_code.getTextTrim());
			}

			Element err_code_des = root.element("err_code_des");
			if(err_code_des != null){
				wxPayCallback.setErr_code_des(err_code_des.getTextTrim());
			}
			
			Element openid = root.element("openid");
			if(openid != null){
				wxPayCallback.setOpenid(openid.getTextTrim());
			}
			
			Element is_subscribe = root.element("is_subscribe");
			if(is_subscribe != null){
				wxPayCallback.setIs_subscribe(is_subscribe.getTextTrim());
			}
			Element trade_type = root.element("trade_type");
			if(trade_type != null){
				wxPayCallback.setTrade_type(trade_type.getTextTrim());
			}
			Element bank_type = root.element("bank_type");
			if(bank_type != null){
				wxPayCallback.setBank_type(bank_type.getTextTrim());
			}
			Element total_fee = root.element("total_fee");
			if(total_fee != null){
				wxPayCallback.setTotal_fee(Integer.parseInt(total_fee.getTextTrim()));
			}
			Element fee_type = root.element("fee_type");
			if(fee_type != null){
				wxPayCallback.setFee_type(fee_type.getTextTrim());
			}
			Element cash_fee = root.element("cash_fee");
			if(cash_fee != null){
				wxPayCallback.setCash_fee(Integer.parseInt(cash_fee.getTextTrim()));
			}
			Element cash_fee_type = root.element("cash_fee_type");
			if(cash_fee_type != null){
				wxPayCallback.setCash_fee_type(cash_fee_type.getTextTrim());
			}
			Element coupon_fee = root.element("coupon_fee");
			if(coupon_fee != null){
				wxPayCallback.setCoupon_fee(Integer.parseInt(coupon_fee.getTextTrim()));
			}
			Element coupon_count = root.element("coupon_count");
			if(coupon_count != null){
				wxPayCallback.setCoupon_count(Integer.parseInt(coupon_count.getTextTrim()));
			}
			Element transaction_id = root.element("transaction_id");
			if(transaction_id != null){
				wxPayCallback.setTransaction_id(transaction_id.getTextTrim());
			}
			Element out_trade_no = root.element("out_trade_no");
			if(out_trade_no != null){
				wxPayCallback.setOut_trade_no(out_trade_no.getTextTrim());
			}
			Element attach = root.element("attach");
			if(attach != null){
				wxPayCallback.setAttach(attach.getTextTrim());
			}
			Element time_end = root.element("time_end");
			if(time_end != null){
				wxPayCallback.setTime_end(time_end.getTextTrim());
			}
			return wxPayCallback;
		} catch (Exception e) {
			log.error("解析微信支付callback异常	"+xml, e);
		}
		
		return null;
	}
	
	private String parseWxPayToXml(WxPay wxPay){
		
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<appid>").append(wxPay.getAppid()).append("</appid>");
		sb.append("<mch_id>").append(wxPay.getMch_id()).append("</mch_id>");
		sb.append("<device_info>").append(wxPay.getDevice_info()).append("</device_info>");
		sb.append("<nonce_str>").append(wxPay.getNonce_str()).append("</nonce_str>");
		sb.append("<sign>").append(wxPay.getSign()).append("</sign>");
		sb.append("<body>").append(wxPay.getBody()).append("</body>");
		sb.append("<out_trade_no>").append(wxPay.getOut_trade_no()).append("</out_trade_no>");
		sb.append("<total_fee>").append(wxPay.getTotal_fee()).append("</total_fee>");
		sb.append("<spbill_create_ip>").append(wxPay.getSpbill_create_ip()).append("</spbill_create_ip>");
		sb.append("<notify_url>").append(wxPay.getNotify_url()).append("</notify_url>");
		sb.append("<trade_type>").append(wxPay.getTrade_type()).append("</trade_type>");
		sb.append("<openid>").append(wxPay.getOpenid()).append("</openid>");
		sb.append("</xml>");
		log.error("调用腾讯借口的data是："+sb.toString());
		return sb.toString();
		
	}
	
	/**
	 * 获取微信支付的支付号
	 * @param paymentInfo
	 * @param orderInfo
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	private String getPrepayId(PaymentInfo paymentInfo, OrderInfo orderInfo) throws Exception{
		String urlPath = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		WxPay wxPay = new WxPay();
		wxPay.setAppid(BFConstants.appId);
		wxPay.setBody(orderInfo.getOrderDetails().get(0).getItemName());//商品描述
		wxPay.setDevice_info("WEB");//设备号
		wxPay.setMch_id(BFConstants.mchId);//商户号
		wxPay.setNonce_str("yujianming");//随机字符串
		wxPay.setNotify_url("http://www.binfenguoyuan.cn/wxpay/wxCallBack");//通知地址
		
		wxPay.setOut_trade_no(orderInfo.getOrderId().toString());//商户订单号
		wxPay.setSpbill_create_ip(orderInfo.getIp());//终端IP
		wxPay.setTotal_fee(orderInfo.getOrderMoney());//总金额
		wxPay.setTrade_type("JSAPI");//交易类型
		
		AccessToken query = new AccessToken();
		query.setUid(orderInfo.getUserId());
		query.setAppid(BFConstants.appId);
		List<AccessToken> list = this.accessTokenDao.selectByCondition(query);
		if(list == null || list.size() == 0){
			log.error("该用户没有openid");
			throw new Exception("使用微信支付时发现，未获取到用户的openid");
		}
		wxPay.setOpenid(list.get(0).getOpenid());
		
		StringBuilder sb = new StringBuilder();
		sb.append("appid=").append(wxPay.getAppid()).append("&");
		sb.append("body=").append(wxPay.getBody()).append("&");
		sb.append("device_info=").append(wxPay.getDevice_info()).append("&");
		sb.append("mch_id=").append(wxPay.getMch_id()).append("&");
		sb.append("nonce_str=").append(wxPay.getNonce_str()).append("&");
		sb.append("notify_url=").append(wxPay.getNotify_url()).append("&");
		sb.append("openid=").append(wxPay.getOpenid()).append("&");
		sb.append("out_trade_no=").append(wxPay.getOut_trade_no()).append("&");
		sb.append("spbill_create_ip=").append(wxPay.getSpbill_create_ip()).append("&");
		sb.append("total_fee=").append(wxPay.getTotal_fee()).append("&");
		sb.append("trade_type=").append(wxPay.getTrade_type()).append("&");
		sb.append("key=").append(BFConstants.wxPaySkey);
		wxPay.setSign(MD5Util.md5Hex(sb.toString()).toUpperCase());//签名
		
		String callbackString = HttpUtils.httpPostData(urlPath, this.parseWxPayToXml(wxPay), "utf-8");
		
		Document doc = DocumentHelper.parseText(callbackString);
		Element root = doc.getRootElement();
		
		Element return_code = root.element("return_code");
		Element result_code = root.element("result_code");
		Element prepay_id = root.element("prepay_id");
		
		if(return_code != null && return_code.getTextTrim().equals("SUCCESS") 
				&& result_code != null && result_code.getTextTrim().equals("SUCCESS")
				&& prepay_id != null && StringUtils.isNotBlank(prepay_id.getTextTrim())){
			log.error("成功调用微信支付接口，生成微信支付ID = "+prepay_id.getTextTrim());
			paymentInfo.setPaymentInfoMessage(callbackString);
			return prepay_id.getTextTrim();
		}
		
		log.error("调用微信支付接口生成微信支付id失败，具体返回值："+callbackString);
		return null;
	}
	
	//------------------------------------------------------------------------------------------------------------
	@Override
	public Result addPaymentInfo(final PaymentInfo paymentInfo) {
		final Result result = new Result();
		try{
			final OrderInfo orderInfo = orderInfoDao.selectByOrderId(paymentInfo.getOrderId());
			if(orderInfo == null){
				result.setResultCode("9004");
				result.setResultMessage("该订单不存在");
				return result;
			}
			PaymentInfoQuery query = new PaymentInfoQuery();
			query.setOrderPayType(paymentInfo.getOrderPayType());
			query.setPaymentInfoType(2);//支付成功标示，如果支付成功了，则不让再支付了。
			query.setOrderId(paymentInfo.getOrderId());
			List<PaymentInfo> list = paymentInfoDao.selectByCondition(query);
			
			if(list != null && list.size() > 0){
				result.setResultCode("3006");
				result.setResultMessage("该订单已经被支付，不能重复支付");
				return result;
			}
			
			new TransactionTemplate(transactionManager).execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					if(paymentInfoDao.insert(paymentInfo) <= 0){
						throw new RuntimeException("添加支付信息失败");
					}
					
					//修改订单实际支付金额
					OrderInfo dbOrderInfo = new OrderInfo();
					dbOrderInfo.setVenderUserId(orderInfo.getVenderUserId());
					dbOrderInfo.setOrderId(orderInfo.getOrderId());
//					dbOrderInfo.setPayMoney(paymentInfo.getPaymentMoney());
					if(orderInfoDao.modifyPayMoney(dbOrderInfo) <= 0){
						throw new RuntimeException("修改订单应付金额失败");
					}
					
					result.setResult(true);
					EcUtils.setSuccessResult(result);
				}
			});
			
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	@Override
	public Result getPaymentInfos(PaymentInfoQuery paymentInfoQuery) {
		Result result = new Result();
		try{
			List<PaymentInfo> list = paymentInfoDao.selectByCondition(paymentInfoQuery);
			if(list == null || list.size() == 0){
				result.setResultCode("3004");
				result.setResultMessage("支付信息不存在");
				return result;
			}
			result.setResult(list);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}

	public void setPaymentInfoDao(PaymentInfoDao paymentInfoDao) {
		this.paymentInfoDao = paymentInfoDao;
	}

	public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
	}

	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

	public void setAccessTokenDao(AccessTokenDao accessTokenDao) {
		this.accessTokenDao = accessTokenDao;
	}

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	
}
