//package com.ec.seller.web.controller;
//
//
//import java.lang.reflect.Field;
//import java.math.BigDecimal;
//import java.net.URLEncoder;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.springframework.beans.propertyeditors.CustomDateEditor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.ec.seller.common.utils.CookieUtil;
//import com.ec.seller.common.utils.PaginatedList;
//import com.ec.seller.domain.BalanceInfo;
//import com.ec.seller.domain.OrderInfo;
//import com.ec.seller.domain.PaymentInfo;
//import com.ec.seller.domain.query.BalanceInfoQuery;
//import com.ec.seller.domain.query.OrderInfoQuery;
//import com.ec.seller.service.AddressService;
//import com.ec.seller.service.BalanceInfoService;
//import com.ec.seller.service.OrderInfoService;
//import com.ec.seller.service.PaymentInfoService;
//import com.ec.seller.service.SellerEntryService;
//
//import demo.Student;
//
//@Controller
//@RequestMapping("/balanceInfo")
//public class BalanceInfoController {
//
//	private final static Log log = LogFactory.getLog(BalanceInfoController.class);
//	
//	private OrderInfoService orderInfoService;
//	private SellerEntryService sellerEntryService;
//	private PaymentInfoService paymentInfoService;
//	private AddressService addressService;
//	
//	private BalanceInfoService balanceInfoService;
//	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//	@InitBinder
//	public void initDateBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//	}
//	
//	@RequestMapping(value="/list", method={ RequestMethod.GET, RequestMethod.POST })
//	public @ResponseBody Map<String, Object> list(BalanceInfoQuery balanceInfoQuery,Integer page, Integer rows, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
//		balanceInfoQuery.setVenderUserId(CookieUtil.getUserId(reuqest));
//		balanceInfoQuery.setPageNo(page);
//		balanceInfoQuery.setPageSize(rows);
//		PaginatedList<BalanceInfo> list = balanceInfoService.getBalanceInfosByPage(balanceInfoQuery);
//		context.put("list", list);
//		context.put("query", balanceInfoQuery);
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("rows", list);
//		map.put("total", list.getTotalItem());
//		return map;
//	}
//	
//	/**
//	 * 订单列表页面
//	 * @param orderInfoQuery
//	 * @param reuqest
//	 * @param response
//	 * @param context
//	 * @return
//	 */
//	@RequestMapping(value="/demo", method={ RequestMethod.GET, RequestMethod.POST })
//	public String demo(BalanceInfoQuery balanceInfoQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
//		balanceInfoQuery.setVenderUserId(CookieUtil.getUserId(reuqest));
//		PaginatedList<BalanceInfo> list = balanceInfoService.getBalanceInfosByPage(balanceInfoQuery);
//		context.put("list", list);
//		context.put("query", balanceInfoQuery);
//		return "/balanceInfo/demo";
//	}
//	
//	/**
//	 * 订单列表页面
//	 * @param orderInfoQuery
//	 * @param reuqest
//	 * @param response
//	 * @param context
//	 * @return
//	 */
//	@RequestMapping(value="/index", method={ RequestMethod.GET, RequestMethod.POST })
//	public String index(BalanceInfoQuery balanceInfoQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
//		balanceInfoQuery.setVenderUserId(CookieUtil.getUserId(reuqest));
//		PaginatedList<BalanceInfo> list = balanceInfoService.getBalanceInfosByPage(balanceInfoQuery);
//		context.put("list", list);
//		context.put("query", balanceInfoQuery);
//		return "/balanceInfo/index3";
//	}
//	
//	/**
//	 * 订单列表页面
//	 * @param orderInfoQuery
//	 * @param reuqest
//	 * @param response
//	 * @param context
//	 * @return
//	 */
//	@RequestMapping(value="/index2", method={ RequestMethod.GET, RequestMethod.POST })
//	public String index2(BalanceInfoQuery balanceInfoQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
//		balanceInfoQuery.setVenderUserId(CookieUtil.getUserId(reuqest));
//		PaginatedList<BalanceInfo> list = balanceInfoService.getBalanceInfosByPage(balanceInfoQuery);
//		context.put("list", list);
//		context.put("query", balanceInfoQuery);
//		return "/balanceInfo/index";
//	}
//	
//	@RequestMapping(value="/detail", method={ RequestMethod.GET, RequestMethod.POST })
//	public String detail(Integer balanceId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
//		
//		BalanceInfoQuery balanceInfoQuery = new BalanceInfoQuery();
//		balanceInfoQuery.setBalanceId(balanceId);
//		balanceInfoQuery.setVenderUserId(CookieUtil.getUserId(reuqest));
//		BalanceInfo balanceInfo = balanceInfoService.getBalanceInfoByBalanceId(balanceInfoQuery);
//		context.put("balanceInfo", balanceInfo);
//		return "/balanceInfo/detail";
//	}
//	
//	@RequestMapping(value="/windowDetail", method={ RequestMethod.GET, RequestMethod.POST })
//	public String windowDetail(Integer balanceId, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
//		
//		BalanceInfoQuery balanceInfoQuery = new BalanceInfoQuery();
//		balanceInfoQuery.setBalanceId(balanceId);
//		balanceInfoQuery.setVenderUserId(CookieUtil.getUserId(reuqest));
//		BalanceInfo balanceInfo = balanceInfoService.getBalanceInfoByBalanceId(balanceInfoQuery);
//		context.put("balanceInfo", balanceInfo);
//		return "/balanceInfo/detailJson";
//	}
//	
//	/**
//	 * 确认结算
//	 * @param balanceId
//	 * @param reuqest
//	 * @param response
//	 * @param context
//	 * @return
//	 */
//	@RequestMapping(value="/doFinish", method={ RequestMethod.GET, RequestMethod.POST })
//	public @ResponseBody Map<String, Object> doFinish(Integer balanceId,HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
//		Map<String, Object> map = new HashMap<String, Object>();
//		if(balanceId == null){
//			map.put("success", false);
//			map.put("message", "结算单号不能为空");
//			return map;
//		}
//		
//		return balanceInfoService.doFinish(balanceId, CookieUtil.getUserId(reuqest));
//	}
//	
//	/**
//	 * 导出excel
//	 * @param balanceId
//	 * @param reuqest
//	 * @param response
//	 * @param context
//	 * @return
//	 */
//	@RequestMapping(value="/excel", method={ RequestMethod.GET, RequestMethod.POST })
//	public void excel(BalanceInfoQuery balanceInfoQuery, HttpServletRequest reuqest,HttpServletResponse response, ModelMap context){
//		balanceInfoQuery.setVenderUserId(CookieUtil.getUserId(reuqest));
//		try{
//			List<BalanceInfo> list = this.balanceInfoService.selectByCondition(balanceInfoQuery);
//			if(list == null){
//				list = new ArrayList<BalanceInfo>();
//			}
//			
//			// 第一步，创建一个webbook，对应一个Excel文件
//			HSSFWorkbook wb = new HSSFWorkbook();
//			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
//			HSSFSheet sheet = wb.createSheet("结算管理");
//			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
//			HSSFRow row = sheet.createRow((int) 0);
//			// 第四步，创建单元格，并设置值表头 设置表头居中
//			HSSFCellStyle style = wb.createCellStyle();
//			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//			
//			HSSFCell cell = row.createCell((short) 0);
//			cell.setCellValue("结算单号");
//			cell.setCellStyle(style);
//			cell = row.createCell((short) 1);
//			cell.setCellValue("结算日期");
//			cell.setCellStyle(style);
//			cell = row.createCell((short) 2);
//			cell.setCellValue("本期应收");
//			cell.setCellStyle(style);
//			cell = row.createCell((short) 3);
//			cell.setCellValue("货款（总）");
//			cell.setCellStyle(style);
//			cell = row.createCell((short) 4);
//			cell.setCellValue("佣金");
//			cell.setCellStyle(style);
//			cell = row.createCell((short) 5);
//			cell.setCellValue("应结金额");
//			cell.setCellStyle(style);
//			cell = row.createCell((short) 6);
//			cell.setCellValue("结算状态");
//			cell.setCellStyle(style);
//			
//			for(int i=0;i<list.size();i++){
//				row = sheet.createRow((int) i + 1);
//				BalanceInfo balanceInfo = (BalanceInfo) list.get(i);
//				// 第四步，创建单元格，并设置值
//				row.createCell((short) 0).setCellValue(balanceInfo.getBalanceId());
//				row.createCell((short) 1).setCellValue(sdf.format(balanceInfo.getBalanceDate()));
//				
//				row.createCell((short) 2).setCellValue(balanceInfo.getOrderMoneyAll() != null ? balanceInfo.getOrderMoneyAll().doubleValue() : 0);
//				row.createCell((short) 3).setCellValue(balanceInfo.getPaymentGoods() != null ? balanceInfo.getPaymentGoods().doubleValue() : 0);
//				row.createCell((short) 4).setCellValue(balanceInfo.getCommission() != null ? balanceInfo.getCommission().doubleValue() : 0);
//				row.createCell((short) 5).setCellValue(balanceInfo.getBalanceMoney() != null ? balanceInfo.getBalanceMoney().doubleValue() : 0);
//				String status = "";
//				if(balanceInfo.getBalanceStatus() == 1)
//					status =  "待审核";
//				else if(balanceInfo.getBalanceStatus() == 2)
//            		status =  "审核通过";
//				else if(balanceInfo.getBalanceStatus() == 3)
//            		status =  "审核驳回";
//				else if(balanceInfo.getBalanceStatus() == 4)
//            		status =  "结算完成";
//				row.createCell((short) 6).setCellValue(status);
//			}
//			
//			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
//			response.setHeader("Content-disposition", "attachment;filename="+sdf2.format(new Date())+".xls");
//			wb.write(response.getOutputStream());
//		}catch (Exception e) {
//			log.error("", e);
//		}
//	}
//	
//	public void setOrderInfoService(OrderInfoService orderInfoService) {
//		this.orderInfoService = orderInfoService;
//	}
//
//	public void setSellerEntryService(SellerEntryService sellerEntryService) {
//		this.sellerEntryService = sellerEntryService;
//	}
//
//	public void setPaymentInfoService(PaymentInfoService paymentInfoService) {
//		this.paymentInfoService = paymentInfoService;
//	}
//
//	public void setAddressService(AddressService addressService) {
//		this.addressService = addressService;
//	}
//
//	public void setBalanceInfoService(BalanceInfoService balanceInfoService) {
//		this.balanceInfoService = balanceInfoService;
//	}
//
//	
//}

