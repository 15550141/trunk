//lazyload
function lazyloadImg() {	
    var $lazy = $("img.lazy");
    $lazy.lazyload({
    	//placeholder: 'img/grey.gif',
        effect: "fadeIn",
        threshold : 200
    });
}

function lazyloadfutureImg() {	
    $(document).delegate("img.lazy", "load", function(event) {  
	    $(this).lazyload({ 
	         effect: "fadeIn" ,
        	 threshold : 200
	    });     
	});
}


//关闭下载客户端
function closeDownloadmodal(){
	$('.m-close').click(function(){
		$('#downloadApp').slideUp(200);
		if($('#m-prolist').height){
			$('#m-prolist').css({'paddingTop':0})
		}
	})
}
//显示浮动
function operaSwitch(){		
	var $goToTop=$('#goToTop');	
	if( $(window).scrollTop() >= $(window).height()){
		if($goToTop.css('display') != 'block' || $goToTop.css('display') != ''){
			$goToTop.fadeIn(200);
		}
	}else{
		if($goToTop.css('display') != 'none'){
			$goToTop.fadeOut(100);
		}
	}	
}
//置顶
function scrollFun(){
	$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html') : $('body')) : $('html,body');// 这行是 Opera 的补丁, 少了它 Opera 是直接用跳的而且画面闪烁 by willin
	var $fload=$('.m-component-float'),
		$goToTop=$('#goToTop');;
	if( $fload.length>0){
		operaSwitch();		
		$(window).scroll(function(){
			operaSwitch();
		});			
		$goToTop.click(function(){
			$('body').animate({scrollTop: 0},100); 
		});
	}
}
//首页轮播
function slideBanner() {
    var $frameBanner = $('#frameBanner');  
    	$selector=$frameBanner.find('li');   
    $('#frameBanner>ul>li').width($frameBanner.width());
    $frameBanner.sly({
    	horizontal: 1,
    	itemNav: 'forceCentered',
		itemSelector: $selector,
		smart: true,
		activateMiddle: 1,
		pagesBar: $(".slidebtn"), 
		activatePageOn: "click",
		cycleBy: 'pages',  
		cycleInterval:3800, 
		pauseOnHover: 1,
		touchDragging:1,
		startPaused: 0,
		mouseDragging: 1,
		releaseSwing: 1,
		speed: 800,
		elasticBounds: 1,
		pageBuilder: function (index) {	return '<li>&nbsp;</li>'; }
    });
}

function chooseCurrRegion(){
	$("#region-ul li").click(function(){
		if($(this).children('a').length==0){
			var url = "/region/setRegion?region_id="+$(this).attr("data-id")+"&region_name="+$(this).attr("data-name");
			window.location.href=url;
		}
	});
}

function getCurrRegion(){
	/*$.ajax({
		type: "GET",
		url: '/ajax/region/getCurrRegion',
		success: function(data) {
			$("#curr_region_name").html(data.msg);
		},
		dataType: 'json'
	});
	*/
}

//添加购买数量
function selectPronum(){
	var $num, _val;
	$('.deC').click(function(){
		$num=$(this).prev('input');
		_val=parseInt($num.val());
		if(_val<=1){
			_val=1;
		}
		_val++;
		$num.val(_val);
	});
	$('.inC').click(function(){
		$num=$(this).next('input');
		_val=parseInt($num.val());
		_val--;
		if(_val>1){
			$num.val(_val);
		}else{
			$num.val(1);
		}		
	});
}
//详情页轮播
function slideProslide() {
    var $proslide = $('#proslide');  
    	$selector=$proslide.find('li');   
    $('#proslide>ul>li').width($proslide.width());
    $proslide.sly({
    	horizontal: 1,
    	itemNav: 'forceCentered',
		itemSelector: $selector,
		smart: true,
		activateMiddle: 1,
		pagesBar: $(".slidebtn"), 
		activatePageOn: "click",
		cycleBy: 'pages',  
		cycleInterval: false, 
		pauseOnHover: 1,
		touchDragging:1,
		startPaused: 0,
		mouseDragging: 1,
		releaseSwing: 1,
		speed: 1200,
		elasticBounds: 1,
		pageBuilder: function (index) {	return '<li>&nbsp;</li>'; }
    });
    $proslide.parent().click(function(){
			var $this=$(this);
			if($('.m-proslide').length==1){
				$this.clone(true).insertAfter(this).find('.frame').removeAttr("id");
				$this.css({
					'position':'fixed',
					'height':'100%',
					'top':'0',
					'zIndex':'1050',
					'background':'rgba(0,0,0,.5)'
				}).find('.frame').css({
					'marginTop':'50%',
					'overflow':'none'
				});						
			}else{
				$this.removeAttr("style").find('.frame').removeAttr("style").end().next('.m-proslide').detach();
			}			
	})
}

function appraisePic(){	
	$('.proappraise-list').delegate('.m-appraise-imgItem>li>img', 'click', function(e){	
		var url=$(this).attr('src');
		$(this).parents('.m-appraise-content').find('.bs-appraise-modal-sm').modal('show')
				.find('.appraise-img-dialog>img').attr({'src': url});
		$('.bs-appraise-modal-sm').on('click', function(){
			$(this).modal('hide')
		})		
	})
}
//品类轮播图
function slideCategory() {
    var $frameCate = $('#frameCate');  
    $frameCate.sly({
    	horizontal: 1,
			itemNav: 'forceCentered',
			smart: 1,
			activateMiddle: 1,
			activateOn: 'click',
			mouseDragging: 1,
			touchDragging: 1,
			releaseSwing: 1,
			startAt: 1,
			scrollBy: 1,
			speed: 300,
			elasticBounds: 1,
			easing: 'swing',
			dragHandle: 1,
			dynamicHandle: 1,
			clickBar: 1
    });
}


//404
function delayURL() { 
	if($('#timeout').length){
	    var delay = $('#timeout').text();
			var t = setTimeout("delayURL()", 1000);
	    if (delay > 0) {
	        delay--;
	        $('#timeout').text(delay);
	    } else {
	 	clearTimeout(t); 
	        window.location.href = "/";
	    }        
	}
}
function setCartCount(){
	if ($('.fdayicon-cart').length) {
		
		 $.ajax('/cart/count',{
			'type':'POST',
			'dataType':'json',
			success:function(resp, textStatus, jqXHR){
					var cartcount = resp.result;
					if (typeof(cartcount) != undefined &&cartcount > 0) {
						$('.fdayicon-cart').append('<i class="cart-sales-nums">'+cartcount+'</i>');
					}else if($('.fdayicon-cart .cart-sales-nums').length){
						$('.fdayicon-cart .cart-sales-nums').remove();
					}

			}
		});
		 
		
		/*
		var allcookies = document.cookie;  
		var cookie_name = "cart";
		var cookie_pos = allcookies.indexOf(cookie_name);
		if(cookie_pos != -1){   //索引的长度  
			cookie_pos = cookie_pos + cookie_name.length + 1;
			var cookie_end = allcookies.indexOf(";", cookie_pos);
			if (cookie_end == -1){  
				cookie_end = allcookies.length;  
			}
			var cookieValue = unescape(allcookies.substring(cookie_pos, cookie_end));
			var cartcount = cookieValue.split("_").length;
			$('.fdayicon-cart').append('<i class="cart-sales-nums">'+cartcount+'</i>');
		}

		*/
	};
}

function setApkUrl(){
//	var ua = navigator.userAgent.toString();
//	if(/android/i.test(ua)){///android/i.test(ua)
//		 var apk_url = "http://cdn.fruitday.com/fruitday.apk";
//	}else if(/(iphone|ipad|ipod)/i.test(ua)){
//		// var apk_url = 'https://itunes.apple.com/us/app/tian-tian-guo-yuan-fruitday/id880977721?mt=8&ign-mpt=uo%3D4';
//		var apk_url = 'http://um0.cn/8LC41';
//	}else{
//		var apk_url = "http://cdn.fruitday.com/fruitday.apk";
//	}
//	return apk_url;
}
function clickApkUrl(){
//	$("body").delegate("a[name='apk_url']",'click',function(){
//		var timeout, t = 500, hasApp = true, url='fruitday://m.fruitday.com';  
//		var product_id = $(this).attr('data-product-id');
//		// if(product_id){
//		// 	url='fruitday://Product?productId='+product_id;
//		// }
//		setTimeout(function () {
//			if (hasApp) {  
//				window.location.href=url;
//			} else {  
//				//验证是否微信浏览器
//				var ua = navigator.userAgent.toString().toLowerCase();
//				if(ua.match(/MicroMessenger/i)=="micromessenger") {  
//					MessageBox.error("请打开浏览器进行下载");
//					return false;
//				}
//				//获取下载路径
//				var apk_url = setApkUrl();
//				//url跳转
//				window.location.href=apk_url;
//			}  
//			document.body.removeChild(ifr);  
//		}, 1000)  
//
//		var t1 = Date.now();  
//		var ifr = document.createElement("iframe");  
//		ifr.setAttribute('src', url);  
//		ifr.setAttribute('style', 'display:none');  
//		document.body.appendChild(ifr);  
//		timeout = setTimeout(function () {  
//			var t2 = Date.now();  
//			if (!t1 || t2 - t1 < t + 100) {  
//				hasApp = false;  
//			}
//		}, t);  
//	});
}


//主函数
function rosefunction(){	
	var winH=parseInt($(window).height()),
		topH=parseInt($('body').css('paddingTop'));

	document.addEventListener("touchstart",function(){}, true);
	
	setCartCount();
	clickApkUrl();

	$("#categoryMenu li").addClass("route");
	lazyloadImg();	
	lazyloadfutureImg();
	scrollFun();
	
	if($('#m-banner').length){
		slideBanner();
		getCurrRegion();
	}
	if($('#m-prolist').length){

		$('body').css({'paddingBottom':80});
		$('#m-prolist').css({'min-height':winH-topH-80});

		$('#downloadApp').css({
			'width':'100%',
			'position':'fixed',
			'bottom':'0',
			'z-index':'100'
		})
	}
	if($('#m-prodetail').length){		
		slideProslide();
		selectPronum();	
	}

	if($('#m-region').length){
		$('.m-regionlist').height(winH-topH-126);		
		chooseCurrRegion();
		getCurrRegion();
	}	

	if($('#m-category').length){
		slideCategory();
	}

	if( $('#m-login').length ){
		console.log(winH-topH);
		$('body').css({'paddingBottom':100});
		$('#m-login').css({'min-height':winH-topH-100})
		$('#downloadApp').css({
			'width':'100%',
			'position':'fixed',
			'bottom':'0',
			'z-index':'100'
		});		
	}

	if($('#m-order').length){
		if($('.m-order-addresslist').length || $('.m-order-address-form').length || $('.m-ordersuc').length || $('.m-ordercoupon').length || $('.m-ordercanl').length ){
			$('.m-order-addresslist').css({'min-height':winH-topH});
			$('.container').css({'min-height':winH-topH, 'background':'#f2f2f2' });
			if($('.m-ordercoupon').length){
				$('.container').css({'min-height':winH-topH, 'background':'#fff' });
			}
		}else{
			$('body').css({'paddingBottom':47});
		}

		var $switch=$('.onoffswitch-checkbox');
			$switch.on('change', function(){
				$(this).parent('.onoffswitch').siblings('div').slideToggle(100);
			});

		$('#m-order-prolist').on('click', function(){
			var a='fdayicon-unfold',
				b='fdayicon-fold',
				c='m-order-prolist-tips',
				d='m-cartlist';
			if($(this).hasClass(a)){
				$(this).removeClass(a).addClass(b).parents('.'+c).next('.'+d).slideUp(200);
			}else{
				$(this).removeClass(b).addClass(a).parents('.'+c).next('.'+d).slideDown(200);
			}
		});		
	}

	if($('#m-404').length){
		var $m404=$('#m-404');
			$m404.height(winH);
			$('body').css({
				'paddingTop':0,
				'height':'100%',
				'background':'#faf8f5',
				'text-align':'center'
			});
			delayURL();
	}

	if($('#m-user').length){
		$('#m-user').css({'min-height':winH-topH});
	}


	closeDownloadmodal();


	
	

		

}
