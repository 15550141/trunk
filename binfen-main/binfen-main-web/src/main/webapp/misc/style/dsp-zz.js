if($("#dsp_page").val()){
	var _zzsiteid="14kNRv048zPr";
	var _zzid = "14kNRv048zPq";
	(function() {
	  var zz = document.createElement('script');
	  zz.type = 'text/javascript';
	  zz.async = true;
	  zz.src = 'https:' == document.location.protocol ? 'https://ssl.trace.zhiziyun.com/api/trace.js' : 'http://static.zhiziyun.com/trace/api/trace.js';
	  var s = document.getElementsByTagName('script')[0];
	  s.parentNode.insertBefore(zz, s);
	})();
}

// * _zzot['zzsiteId'], _zzot['zzId']用默认值,不能改动
// * 订单编号, 订单总金额, 订单详情,根据实际数据赋值
// * 订单详情通过以下方式赋值
// * _zzot.push(["商品1的编号", "商品1的购买数量", "商品1的价格"]);
// * _zzot.push(["商品2的编号", "商品1的购买数量", "商品2的价格"]);

if($("#dsp_order").val()){
	var _zzot = [];
	_zzot['zzsiteId'] = "14kNRv048zPr";
	_zzot['zzId'] = "14kNRv048zPq";

	_zzot['zzOrderId'] = $("#static_caseid").val();               //订单编号
	_zzot['zzOrderTotal'] = $("#static_income").val();                       //订单总金额
	// _zzot.push(["productId001", "1", "168.80"]); //订单详情
	(function() {
	  var zz = document.createElement('script');
	  zz.type = 'text/javascript';
	  zz.async = true;
	  zz.src = 'https:' == document.location.protocol ? 'https://ssl.trace.zhiziyun.com/api/order_v2.js' : 'http://static.zhiziyun.com/trace/api/order_v2.js';
	  var s = document.getElementsByTagName('script')[0];
	  s.parentNode.insertBefore(zz, s);
	})();
}