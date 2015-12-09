package com.ec.api.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.ec.api.common.utils.CookieUtils;
import com.ec.api.common.utils.HttpUtils;
import com.ec.api.common.utils.JsonUtils;
import com.ec.api.common.utils.PaginatedArrayList;
import com.ec.api.dao.ConsigneeInfoDao;
import com.ec.api.dao.ItemDao;
import com.ec.api.dao.OrderDetailDao;
import com.ec.api.dao.OrderInfoDao;
import com.ec.api.dao.PromotionInfoDao;
import com.ec.api.dao.PromotionSkuDao;
import com.ec.api.dao.SkuDao;
import com.ec.api.dao.TaskDao;
import com.ec.api.dao.UmpInfoDao;
import com.ec.api.domain.CartInfo;
import com.ec.api.domain.CartSku;
import com.ec.api.domain.ConsigneeInfo;
import com.ec.api.domain.Item;
import com.ec.api.domain.OrderDetail;
import com.ec.api.domain.OrderInfo;
import com.ec.api.domain.PaymentInfo;
import com.ec.api.domain.PromotionInfo;
import com.ec.api.domain.PromotionSku;
import com.ec.api.domain.ReceiveAddr;
import com.ec.api.domain.Sku;
import com.ec.api.domain.Task;
import com.ec.api.domain.UmpInfo;
import com.ec.api.domain.query.OrderInfoQuery;
import com.ec.api.domain.query.PromotionSkuQuery;
import com.ec.api.service.CartService;
import com.ec.api.service.OrderInfoService;
import com.ec.api.service.PaymentInfoService;
import com.ec.api.service.ReceiveAddrService;
import com.ec.api.service.UmpInfoService;
import com.ec.api.service.result.Result;
import com.ec.api.service.utils.CartUtils;
import com.ec.api.service.utils.EcUtils;
import com.ec.api.service.vo.UmpPayServiceResultVo;

@Service(value="orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {
	private static final Logger log = LoggerFactory.getLogger(OrderInfoServiceImpl.class);

	private OrderInfoDao orderInfoDao;
	private ItemDao itemDao;
	private OrderDetailDao  orderDetailDao;
	private SkuDao skuDao;
	private PromotionSkuDao promotionSkuDao;
	private PromotionInfoDao promotionInfoDao;
    private ConsigneeInfoDao consigneeInfoDao;
	private DataSourceTransactionManager transactionManager;
	private UmpInfoDao umpInfoDao;
	private UmpInfoService umpInfoService;
	private CartService cartService;
	private ReceiveAddrService receiveAddrService;
	private PaymentInfoService paymentInfoService;
	private TaskDao taskDao;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	@Override
	public CartInfo index(Integer uid, HttpServletRequest request) {
		return  cartService.getCartInfoByCookie(uid, request);
	}
	
	@Override
	public Result createOrder(final OrderInfo order, HttpServletRequest request, HttpServletResponse response) {
		final Result result = new Result();
		try{
			CartInfo cartInfo = cartService.getCartInfoByCookie(order.getUserId(), request);
			
			if(cartInfo == null){
				result.setSuccess(false);
				result.setResultMessage("商品信息异常");
				return result;
			}
			
			//设置基本信息
			this.setBasicOrderInfo(order);
			order.setIp(HttpUtils.getRemoteIp(request));
			
			//设置订单运费
			order.setFreightMoney(cartInfo.getFreightMoney().multiply(new BigDecimal(100)).intValue());
			//设置订单总金额
			order.setOrderMoney((cartInfo.getTotleSalePrice().multiply(new BigDecimal(100))).intValue());
			//设置订单总优惠金额
			order.setDiscountMoney((cartInfo.getTotlePreferentialPrice().multiply(new BigDecimal(100))).intValue());
			//设置订单优惠明细
//			if(cartInfo.getTotlePreferentialPrice().compareTo(new BigDecimal(0)) > 0){
//				order.setDiscountInfo("首单满19元减5元");
//			}
			
			//如果是货到付款订单
			if(order.getOrderType() != 2){
				order.setOrderStatus(1);
			}else{
				//订单状态，默认支付方式是货到付款，订单状态为给客户回电确认真实发货时间
				order.setOrderStatus(4);
			}
			//商品信息
			final List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
			
			List<CartSku> cartSkus = cartInfo.getSkuList();
			for(int i = 0 ; i < cartSkus.size() ; i++){
				OrderDetail orderDetail = new OrderDetail();
				CartSku sku = cartSkus.get(i);
				
				orderDetail.setUid(order.getUserId());
				orderDetail.setItemId(sku.getItemId());
				orderDetail.setItemImage(sku.getImage());
				orderDetail.setItemName(sku.getName());
				orderDetail.setNum(sku.getNum());
				orderDetail.setPrice((sku.getSkuPrice().multiply(new BigDecimal(100))).intValue());
				orderDetail.setSalesProperty(sku.getSalesProperty());
				orderDetail.setSalesPropertyName(sku.getSalesPropertyName());
				orderDetail.setSkuId(sku.getSkuId());
				orderDetailList.add(orderDetail);
			}
			
			new TransactionTemplate(transactionManager).execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					Integer orderId = orderInfoDao.insert(order);
					order.setOrderId(orderId);
					
					for(int i=0;i<orderDetailList.size();i++){
						OrderDetail orderDetail = orderDetailList.get(i);
						orderDetail.setOrderId(orderId);
						orderDetailDao.insert(orderDetail);
						
						//扣库存
//						Sku sku = new Sku();
//						sku.setSkuId(orderDetail.getSkuId());
//						sku.setStock(orderDetail.getNum());
//						Integer delState = skuDao.delStock(sku);
//						if(delState == null || delState == 0){
//							//TODO 抛个异常，回滚事务
//							throw new RuntimeException("商品库存不足");
//						}
						
//						//TODO 扣除促销库存
//						for(int j=0;j<delPromotionInfoStock.size();j++){
//							int delResult = promotionInfoDao.updatePromotionInfoStock(delPromotionInfoStock.get(j));
//							if(delResult <= 0){
//								throw new RuntimeException("促销商品库存不足,扣减失败");
//							}
//						}
					}
					
					//添加任务表
					Task task = new Task();
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("orderId", order.getOrderId());
					map.put("userId", order.getUserId());
					task.setContent(JsonUtils.writeValue(map));//内容
					task.setStatus(0);//初始状态
					task.setType(1);//下单成功任务
					task.setYn(1);//有效
					taskDao.insert(task);
					EcUtils.setSuccessResult(result);
				}
			});
			CartUtils.clearCookies(response);//清除
			
			if(order.getPaymentType() == 3){//微信支付
				PaymentInfo paymentInfo = new PaymentInfo();
				paymentInfo.setOrderId(order.getOrderId());
				paymentInfo.setOrderPayType(1);
				paymentInfo.setUid(order.getUserId());
				return paymentInfoService.userCreatePayment(paymentInfo);
			}
			
		}catch (Exception e) {
			log.error("下单异常，userId:"+order.getUserId(), e);
			EcUtils.setExceptionResult(result);
		}
		
		return result;
	}
	
	@Override
	public PaginatedArrayList<OrderInfo> getUnfinishedOrder(OrderInfoQuery orderInfoQuery) {
		//分页反参
		PaginatedArrayList<OrderInfo> orders = new PaginatedArrayList<OrderInfo>(orderInfoQuery.getPageNo(),orderInfoQuery.getPageSize());
		try{
			//获取总数量
			int count = orderInfoDao.countByUnfinishedOrder(orderInfoQuery);
			if(count > 0){
				//设置反参总数量
				orders.setTotalItem(count);
				//设置起始行
				orderInfoQuery.setStart(orders.getStartRow());
				//查询用户订单信息
				List<OrderInfo> orderInfoList= orderInfoDao.selectByUnfinishedOrder(orderInfoQuery);
				//加载商品信息
				for(OrderInfo orderInfo : orderInfoList){
					List<OrderDetail> orderDetailList = orderDetailDao.selectByOrderId(orderInfo.getOrderId());
					orderInfo.setOrderDetails(orderDetailList);
				}
				
				orders.addAll(orderInfoList);
			}
		}catch (Exception e) {
			log.error("", e);
		}
		
		return orders;
		
	}
	
	/**
	 * 用户所属订单列表查看（细分）
	 * @return
	 */
	@Override
	public PaginatedArrayList<OrderInfo> getOrderInfosByOrderInfoQuery(OrderInfoQuery orderInfoQuery) {
		//分页反参
		PaginatedArrayList<OrderInfo> orders = new PaginatedArrayList<OrderInfo>(orderInfoQuery.getPageNo(),orderInfoQuery.getPageSize());
		try{
			//获取总数量
			int count = orderInfoDao.countByCondition(orderInfoQuery);
			if(count > 0){
				//设置反参总数量
				orders.setTotalItem(count);
				//设置起始行
				orderInfoQuery.setStart(orders.getStartRow());
				//查询用户订单信息
				List<OrderInfo> orderInfoList= orderInfoDao.selectByConditionForPage(orderInfoQuery);
				//加载商品信息
				for(OrderInfo orderInfo : orderInfoList){
					List<OrderDetail> orderDetailList = orderDetailDao.selectByOrderId(orderInfo.getOrderId());
					orderInfo.setOrderDetails(orderDetailList);
				}
				
				orders.addAll(orderInfoList);
			}
		}catch (Exception e) {
			log.error("", e);
		}
		
		return orders;
	}
	
	@Override
	public OrderInfo getOrderInfoByOrderIdAndUserId(Integer orderId, Integer userId) {
		OrderInfo order = null;
		try{
			OrderInfoQuery orderInfoQuery = new OrderInfoQuery();
			orderInfoQuery.setUserId(userId);
			orderInfoQuery.setOrderId(orderId);
			
			List<OrderInfo> list = this.orderInfoDao.selectByCondition(orderInfoQuery);
			
			if(list == null || list.size() == 0){
				return null;
			}
			
			order = list.get(0);
			order.setOrderDetails(orderDetailDao.selectByOrderId(order.getOrderId()));
			
		}catch (Exception e) {
			log.error("", e);
		}
		return order;
	}
	
	@Override
	public Result orderCancle(Integer orderId, Integer uid, HttpServletRequest request,
			HttpServletResponse response) {
		
		Result result = new Result();
		try{
			OrderInfo orderInfo = this.orderInfoDao.selectByOrderId(orderId);
			if(orderInfo == null){
				result.setSuccess(false);
				result.setResultCode("9004");
				result.setResultMessage("该订单不存在");
				return result;
			}
			
			if(!orderInfo.getUserId().equals(uid)){
				result.setSuccess(false);
				result.setResultCode("9005");
				result.setResultMessage("您不能取消不属于您的订单");
				return result;
			}
			//订单改为已取消
			orderInfo.setOrderStatus(51);
			orderInfoDao.modify(orderInfo);
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	/**
	 * 查看该用户是否未生成过订单
	 * @param uid
	 * @param request
	 * @return
	 */
	@Override
	public Integer getEffectiveOrderCount(Integer uid) {
		
		try{
			OrderInfoQuery orderInfoQuery = new OrderInfoQuery();
			orderInfoQuery.setUserId(uid);
			
			return this.orderInfoDao.countByCondition(orderInfoQuery);
			
		}catch (Exception e) {
			log.error("", e);
		}
		return null;
		
	}
	
	//-----------------------------------------------------------------------------------
	
	/**
	 * 下单方法
	 */
	@Override
	public Result submit(final OrderInfo orderInfo, final List<OrderDetail> orderDetailList) {
		final Result result = new Result();
		try{
			orderInfo.setOrderId(null);
			//优惠明细的map
			Map<Integer, Integer> promotionMap = new HashMap<Integer, Integer>();
			Date now = new Date();
			final List<PromotionInfo> delPromotionInfoStock = new ArrayList<PromotionInfo>();
			for(int i=0;i<orderDetailList.size();i++){
				OrderDetail orderDetail = orderDetailList.get(i);
				orderDetail.setCreated(now);
				
				Item item = itemDao.selectByItemId(orderDetail.getItemId());
				if(item == null){
					result.setResultMessage("商品编号为："+orderDetail.getItemId()+"不存在");
					result.setResultCode("8009");
					return result;
				}
				
				if(item.getItemStatus() != 1 || item.getYn() != 1){
					result.setResultMessage("商品编号为："+item.getItemId()+"已下架");
					result.setResultCode("8010");
					return result;
				}
				
				if(item.getLeastBuy()!=null && orderDetail.getNum() < item.getLeastBuy()){
					result.setResultMessage("购买商品数量小于该商品的起买量");
					result.setResultCode("9002");
					return result;
				}
				
				if(orderInfo.getOrderType() == 2){
					if(item.getIfDeposit() == 0 || item.getDepositRate() == null){
						result.setResultMessage("商品编号为："+item.getItemId()+"不支持定金支付");
						result.setResultCode("9002");
						return result;
					}
				}
				
				orderDetail.setItemId(item.getItemId());
				orderDetail.setItemImage(item.getItemImage());
				orderDetail.setItemName(item.getItemName());
				
				Sku sku = skuDao.selectBySkuId(orderDetail.getSkuId());
				if(sku == null){
					result.setResultMessage("sku编号为"+orderDetail.getSkuId()+"的商品不存在");
					result.setResultCode("8006");
					return result;
				}
				
				if(sku.getStock() < orderDetail.getNum()){
					result.setResultMessage("sku编号为"+orderDetail.getSkuId()+"的商品无货");
					result.setResultCode("9001");
					return result;
				}

                //一个SKU可能有多个促销，取价格最优者
                PromotionSkuQuery promotionSkuQuery = new PromotionSkuQuery();
                promotionSkuQuery.setSkuId(sku.getSkuId());
                List<PromotionSku> psList = promotionSkuDao.selectByCondition(promotionSkuQuery);
                PromotionSku promotionSku = null;
                if( null != psList && 0 < psList.size() ){
                    promotionSku = psList.get(0);   
                }
                for(PromotionSku ps :psList ){
                    if( ps.getDeductionPrice() > promotionSku.getDeductionPrice() ){
                        promotionSku = ps;
                    }
                }
                //如果带促销，计算带促销订单价格
				if(promotionSku != null && promotionSku.getDeductionPrice() > 0){
					PromotionInfo promotionInfo = promotionInfoDao.selectByPromotionId(promotionSku.getPromotionId());
					//判断是否有促销活动
					if(promotionInfo != null && promotionInfo.getPurchaseNumberMin()!=null &&  promotionInfo.getPurchaseNumberMax()!=null
                            && promotionInfo.getPromotionStatus()!=null && promotionInfo.getStartTime()!=null && promotionInfo.getEndTime() !=null
                            && promotionInfo.getYn() == 1 && promotionInfo.getPromotionStatus() == 1
							&& orderDetail.getNum() > promotionInfo.getPurchaseNumberMin() && orderDetail.getNum() < promotionInfo.getPurchaseNumberMax()
							&& now.after(promotionInfo.getStartTime()) && now.before(promotionInfo.getEndTime())
							//&& promotionInfo.getPromotionStock() > orderDetail.getNum()  //TODO 为什么把这里注释了？
                            ){
                            if(sku.getSalePrice() > promotionSku.getDeductionPrice()){
                                orderDetail.setPrice(sku.getSalePrice() - promotionSku.getDeductionPrice());//商品实际出售单价
                            }
						    promotionMap.put(orderDetail.getSkuId(), orderDetail.getPrice());//TODO 优惠信息明细，需要再确认
						
//						//下面删除库存用
//						PromotionInfo del = new PromotionInfo();
//						del.setPromotionId(promotionInfo.getPromotionId());
//						del.setPromotionStock(orderDetail.getNum());
//						delPromotionInfoStock.add(promotionInfo);
					}
				}
				
				//如果商品无促销，该商品价格为商品单价
				if(orderDetail.getPrice() == null || orderDetail.getPrice() == 0){
					orderDetail.setPrice(sku.getSalePrice());//商品实际出售单价
				}
				
				//该商品原价总价
				Integer itemOrderMoney = sku.getSalePrice() * orderDetail.getNum();
				//计算订单总金额，订单总金额为原价，不带促销
				if(orderInfo.getOrderMoney() == null){
					orderInfo.setOrderMoney(itemOrderMoney);
				}else{
					orderInfo.setOrderMoney(orderInfo.getOrderMoney() + itemOrderMoney);
				}
				
				//该商品优惠金额 = (商品原价-商品实际出售价格)*商品数量
				//如果商品没有优惠的话，商品原价减去商品出售价格的值为0.即没有优惠
				Integer itemDiscountMoney = (sku.getSalePrice() - orderDetail.getPrice()) * orderDetail.getNum();
				//计算订单总优惠金额
				if(orderInfo.getDiscountMoney() == null){
					orderInfo.setDiscountMoney(itemDiscountMoney);
				}else{
					orderInfo.setDiscountMoney(orderInfo.getDiscountMoney() + itemDiscountMoney);
				}
				
				//该商品全额支付的订单金额
				Integer itemOughtPayMoney = orderDetail.getPrice() * orderDetail.getNum();
				
//				//计算订单总应付金额
//				if(orderInfo.getOughtPayMoney() == null){
//					orderInfo.setOughtPayMoney(itemOughtPayMoney);
//				}else{
//					orderInfo.setOughtPayMoney(orderInfo.getOughtPayMoney() + itemOughtPayMoney);
//				}
				
				//添加商品销售属性
				orderDetail.setSalesProperty(sku.getSalesProperty());
				orderDetail.setSalesPropertyName(sku.getSalesPropertyName());
			}
			orderInfo.setDiscountInfo(JsonUtils.writeValue(promotionMap));//TODO 优惠信息明细，需要再确认
			orderInfo.setOrderTime(now);
			orderInfo.setOrderStatus(0);//新建订单
			orderInfo.setCreated(now);

			new TransactionTemplate(transactionManager).execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					Integer orderId = orderInfoDao.insert(orderInfo);
					orderInfo.setOrderId(orderId);
					
					for(int i=0;i<orderDetailList.size();i++){
						OrderDetail orderDetail = orderDetailList.get(i);
						orderDetail.setOrderId(orderId);
						orderDetailDao.insert(orderDetail);
						
						//扣库存
						Sku sku = new Sku();
						sku.setSkuId(orderDetail.getSkuId());
						sku.setStock(orderDetail.getNum());
						Integer delState = skuDao.delStock(sku);
						if(delState == null || delState == 0){
							//TODO 抛个异常，回滚事务
							throw new RuntimeException("商品库存不足");
						}
						
//						//TODO 扣除促销库存
//						for(int j=0;j<delPromotionInfoStock.size();j++){
//							int delResult = promotionInfoDao.updatePromotionInfoStock(delPromotionInfoStock.get(j));
//							if(delResult <= 0){
//								throw new RuntimeException("促销商品库存不足,扣减失败");
//							}
//						}
					}
					EcUtils.setSuccessResult(result);
					result.setResult(orderId);
				}
			});

            //设置收货人地址为默认地址
            if( null != orderInfo.getConsigneeId() ){
                try{
                    ConsigneeInfo consigneeInfo = new ConsigneeInfo();
                    consigneeInfo.setConsigneeId(orderInfo.getConsigneeId());
                    consigneeInfo.setDefaultConsigneeFlag(1);
                    consigneeInfoDao.modify(consigneeInfo);
                }catch(Exception e1){
                    log.error("setDefaultConsigneeFlag error! ", e1);
                }
            }

		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		
		return result;
	}
	
	/**
	 * 商家确认收款
	 * @param orderId
	 * @return
	 */
	@Override
	public Result confirmGetPrice(Integer orderId, Integer venderUserId) {
		Result result = new Result();
		try{
			OrderInfo orderInfo = this.orderInfoDao.selectByOrderId(orderId);
			if(orderInfo == null){
				result.setResultCode("9004");
				result.setResultMessage("该订单不存在");
				return result;
			}
			orderInfo.setOrderStatus(2);//收款确认
			orderInfoDao.modify(orderInfo);
			
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	@Override
	public Result confirmGetLastPrice(Integer orderId, Integer venderUserId) {
		Result result = new Result();
		try{
			OrderInfo orderInfo = this.orderInfoDao.selectByOrderId(orderId);
			if(orderInfo == null){
				result.setResultCode("9004");
				result.setResultMessage("该订单不存在");
				return result;
			}
			orderInfo.setOrderStatus(4);//尾款收款确认
			orderInfoDao.modify(orderInfo);
			
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	/**
	 * 商家发货确认
	 * @param orderId
	 * @return
	 */
	@Override
	public Result confirmSendOut(Integer orderId, Integer venderUserId) {
		Result result = new Result();
		try{
			OrderInfo orderInfo = this.orderInfoDao.selectByOrderId(orderId);
			if(orderInfo == null){
				result.setResultCode("9004");
				result.setResultMessage("该订单不存在");
				return result;
			}
			orderInfo.setOrderStatus(5);//TODO 商家发货
			orderInfo.setSendOutTime(new Date());
			orderInfoDao.modify(orderInfo);
			
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	/**
	 * 用户收货确认
	 * @param orderId
	 * @return
	 */
	@Override
	public Result confirmGetGoods(Integer orderId, Integer userId) {
		Result result = new Result();
		try{
			OrderInfo orderInfo = this.orderInfoDao.selectByOrderId(orderId);
			if(orderInfo == null){
				result.setResultCode("9004");
				result.setResultMessage("该订单不存在");
				return result;
			}
			
			if(orderInfo.getOrderType() == 1){//如果是订单全额支付
				orderInfo.setOrderStatus(50);// 买家收货确认完成以后，直接变为订单完成状态
			}else{//如果是定金支付
				orderInfo.setOrderStatus(7);//订单变为等待支付尾款
			}
			
			orderInfoDao.modify(orderInfo);
			
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	/**
	 * 商家订单完成确认
	 * @param orderId
	 * @return
	 */
	@Override
	public Result orderSuccess(Integer orderId) {
		Result result = new Result();
		try{
			OrderInfo orderInfo = this.orderInfoDao.selectByOrderId(orderId);
			if(orderInfo == null){
				result.setResultCode("9004");
				result.setResultMessage("该订单不存在");
				return result;
			}
			orderInfo.setOrderStatus(50);// 订单完成
			orderInfo.setFinishTime(new Date());
			orderInfoDao.modify(orderInfo);
			
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	/**
	 * 商家订单锁定
	 * @param orderId
	 * @param lockReason
	 * @return
	 */
	@Override
	public Result lockOrder(Integer orderId, String lockReason) {
		Result result = new Result();
		try{
			OrderInfo orderInfo = this.orderInfoDao.selectByOrderId(orderId);
			if(orderInfo == null){
				result.setResultCode("9004");
				result.setResultMessage("该订单不存在");
				return result;
			}
			orderInfo.setLockStatus(1);
			orderInfo.setLockReason(lockReason);
			orderInfoDao.modify(orderInfo);
			
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	/**
	 * 商家订单解锁
	 * @param orderId
	 * @return
	 */
	@Override
	public Result unlockOrder(Integer orderId) {
		Result result = new Result();
		try{
			OrderInfo orderInfo = this.orderInfoDao.selectByOrderId(orderId);
			if(orderInfo == null){
				result.setResultCode("9004");
				result.setResultMessage("该订单不存在");
				return result;
			}
			orderInfo.setLockStatus(0);
			orderInfoDao.modify(orderInfo);
			result.setResult(true);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	@Override
	public Result getOrderInfoByOrderIdAndVenderUserId(Integer orderId,
			Integer venderUserId) {
		Result result = new Result();
		try{
			OrderInfoQuery orderInfoQuery = new OrderInfoQuery();
			orderInfoQuery.setVenderUserId(venderUserId);
			orderInfoQuery.setOrderId(orderId);
			
			List<OrderInfo> list = this.orderInfoDao.selectByCondition(orderInfoQuery);
			
			if(this == null || list.size() == 0){
				result.setResultCode("9003");
				result.setResultMessage("该用户订单列表不存在");
				return result;
			}
			
			OrderInfo orderInfo = list.get(0);
			orderInfo.setOrderDetails(orderDetailDao.selectByOrderId(orderInfo.getOrderId()));
			result.setResult(orderInfo);
			EcUtils.setSuccessResult(result);
		}catch (Exception e) {
			log.error("", e);
			EcUtils.setExceptionResult(result);
		}
		return result;
	}
	
	@Override
	public Result createTradeNo(Integer orderId, Integer userId) {
//		Result result = new Result();
//		try{
//			OrderInfoQuery orderInfoQuery = new OrderInfoQuery();
//			orderInfoQuery.setUserId(userId);
//			orderInfoQuery.setOrderId(orderId);
//			
//			List<OrderInfo> list = this.orderInfoDao.selectByCondition(orderInfoQuery);
//			
//			if(this == null || list.size() == 0){
//				result.setResultCode("9003");
//				result.setResultMessage("该用户订单列表不存在");
//				return result;
//			}
//			
//			final OrderInfo orderInfo = list.get(0);
//			if(StringUtils.isBlank(orderInfo.getTradeNo())){
//				final UmpPayServiceResultVo umpPayServiceResultVo = umpInfoService.payservice(orderInfo);
//				if(umpPayServiceResultVo != null){
//					new TransactionTemplate(transactionManager).execute(new TransactionCallbackWithoutResult() {
//						@Override
//						protected void doInTransactionWithoutResult(TransactionStatus arg0) {
//							//修改订单表，添加tradeNo值
//							orderInfo.setTradeNo(umpPayServiceResultVo.getTrade_no());
//							orderInfoDao.modify(orderInfo);
//							
//							//创建一条支付记录
//							UmpInfo umpInfo = new UmpInfo();
//							umpInfo.setTradeNo(orderInfo.getTradeNo());
//							umpInfo.setUserId(orderInfo.getUserId());
//							umpInfo.setOrderId(orderInfo.getOrderId());
//							try {
//								umpInfo.setMerDate(sdf.parse(sdf.format(orderInfo.getOrderTime())));
//							} catch (ParseException e) {
//							}
////							umpInfo.setAmount( orderInfo.getOughtPayMoney());
//							String param = JsonUtils.writeValue(umpPayServiceResultVo);
//							umpInfo.setParam(param);
//							umpInfoDao.insert(umpInfo);
//						}
//					});
//				}
//				
//			}
//			HashMap<String, Object> resultMap = new HashMap<String, Object>();
//			resultMap.put("tradeNo", orderInfo.getTradeNo());
//			resultMap.put("orderStatus", orderInfo.getOrderStatus());
//			result.setResult(resultMap);
//			EcUtils.setSuccessResult(result);
//		}catch (Exception e) {
//			log.error("", e);
//			EcUtils.setExceptionResult(result);
//		}
//		return result;
		
		
		return null;
	}

    @Override
	public Result getOrderStatusByOrderIdAndUserId(Integer orderId, Integer userId) {
//		Result result = new Result();
//		try{
//			OrderInfoQuery orderInfoQuery = new OrderInfoQuery();
//			orderInfoQuery.setUserId(userId);
//			orderInfoQuery.setOrderId(orderId);
//
//			List<OrderInfo> list = this.orderInfoDao.selectByCondition(orderInfoQuery);
//
//			if(this == null || list.size() == 0){
//				result.setResultCode("9003");
//				result.setResultMessage("该用户订单列表不存在");
//				return result;
//			}
//			OrderInfo orderInfo = list.get(0);
//			HashMap<String, Object> resultMap = new HashMap<String, Object>();
////			resultMap.put("tradeNo", orderInfo.getTradeNo());
//			resultMap.put("orderStatus", orderInfo.getOrderStatus());
//            result.setResult(resultMap);
//			EcUtils.setSuccessResult(result);
//		}catch (Exception e) {
//			log.error("", e);
//			EcUtils.setExceptionResult(result);
//		}
//		return result;
    	
    	return null;
	}
	
    private void setBasicOrderInfo(OrderInfo order){
		//默认卖家
		order.setVenderUserId(10000);
		
		//设置收货人地址，使用默认物流收货人信息
		ReceiveAddr addr = receiveAddrService.getDefaultReceiveAddr(order.getUserId());
		order.setConsigneeAddress(addr.getProvinceName()+addr.getCityName()+addr.getCountyName()+addr.getAddress());
		order.setConsigneeCity(addr.getCity());
		order.setConsigneeCounty(addr.getCounty());
		order.setConsigneeId(addr.getId());
		order.setConsigneeMobile(addr.getMobile());
		order.setConsigneeName(addr.getName());
		order.setConsigneeProvince(addr.getProvince());
	}
    
	public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public void setSkuDao(SkuDao skuDao) {
		this.skuDao = skuDao;
	}

	public void setPromotionSkuDao(PromotionSkuDao promotionSkuDao) {
		this.promotionSkuDao = promotionSkuDao;
	}

	public void setPromotionInfoDao(PromotionInfoDao promotionInfoDao) {
		this.promotionInfoDao = promotionInfoDao;
	}

    public void setConsigneeInfoDao(ConsigneeInfoDao consigneeInfoDao) {
        this.consigneeInfoDao = consigneeInfoDao;
    }

    public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setUmpInfoDao(UmpInfoDao umpInfoDao) {
		this.umpInfoDao = umpInfoDao;
	}

	public void setUmpInfoService(UmpInfoService umpInfoService) {
		this.umpInfoService = umpInfoService;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	public void setReceiveAddrService(ReceiveAddrService receiveAddrService) {
		this.receiveAddrService = receiveAddrService;
	}

	public void setPaymentInfoService(PaymentInfoService paymentInfoService) {
		this.paymentInfoService = paymentInfoService;
	}

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

}
