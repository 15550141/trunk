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


function wxShare(imgUrl, link, title, desc){
	wx.ready(function(){
		wx.onMenuShareTimeline({
			title: title, // 分享标题
			link: link, // 分享链接
			imgUrl: imgUrl, // 分享图标
			success: function () { 
				// 用户确认分享后执行的回调函数
			},
			cancel: function () { 
				// 用户取消分享后执行的回调函数
			}
		});

		wx.onMenuShareAppMessage({
			title: title, // 分享标题
			desc: desc, // 分享描述
			link: link, // 分享链接
			imgUrl: imgUrl, // 分享图标
			type: '', // 分享类型,music、video或link，不填默认为link
			dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
			success: function () { 
				// 用户确认分享后执行的回调函数
			},
			cancel: function () { 
				// 用户取消分享后执行的回调函数
			}
		});

		wx.onMenuShareQQ({
			title: title, // 分享标题
			desc: desc, // 分享描述
			link: link, // 分享链接
			imgUrl: imgUrl, // 分享图标
			success: function () { 
			   // 用户确认分享后执行的回调函数
			},
			cancel: function () { 
			   // 用户取消分享后执行的回调函数
			}
		});
	});
}

function getWxConfig(){
	$.ajax({
		type: "GET",
        url: '/weixin/getWxConfig',
        dataType : "json",
        data: "url="+window.location.href,
        
		success: function(data) {
			wx.config({
			    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			    appId: 'wx1845cbfdc233b86b', // 必填，公众号的唯一标识
			    timestamp: data.timestamp, // 必填，生成签名的时间戳
			    nonceStr: data.nonceStr, // 必填，生成签名的随机串
			    signature: data.signature,// 必填，签名，见附录1
			    jsApiList: [
				   'checkJsApi',  //判断当前客户端版本是否支持指定JS接口
			       'onMenuShareTimeline', //分享给好友
			       'onMenuShareAppMessage', //分享到朋友圈
			       'onMenuShareQQ',  //分享到QQ
			       'chooseWXPay'  //微信支付
				] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
			
			wxShare('http://www.binfenguoyuan.cn/misc/style/logo2.jpg', window.location.href, "鲜果味道。首单满19立减5元", "首单满19立减5元，满50元更有好礼赠送！秦皇岛人自己的电商平台！");
		}
	});
}

$(function(){
	getWxConfig();
}); 
