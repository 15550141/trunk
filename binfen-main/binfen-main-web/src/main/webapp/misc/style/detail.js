$(function() {
	appraisePic();
	apprisefun();
	selectProitem();
})

function appraisePic(){	
	$('.proappraise-list').delegate('.m-appraise-imgItem>li>img', 'click', function(e){	
		var url=$(this).attr('src');
		$('.bs-appraise-modal-sm').modal('show')	
			.find('.appraise-img-dialog>img').attr({'src': url});
		$('.bs-appraise-modal-sm').on('click', function(){
			$(this).modal('hide')
		})		
	})
}

var productCommentEnd = false;
var foo =  function() {
	var totalheight = parseFloat($(window).height()) + parseFloat($(window).scrollTop());
	if ($(document).height() <= totalheight) {
		if(!productCommentEnd){
			appriseData();
		}
	}
}
function apprisefun() {
	$('.m-pro-tab>ul>li>a[href="#proappraise"]').on('click', function() {
		$("#comment_curr_page").val('0');
		appriseRate();
		appriseData();
	});

	$('.m-pro-tab>ul>li>a[href="#proinfo"]').on('click', function() {
		$(window).unbind('scroll', foo);
	});
}

function appriseRate() {
	$.ajax({
		type: "POST",
		url: '/ajax/comment/pRate',
		data: {
			id: $("#product_id").val()
		},
		success: function(data) {
			if (data.code == 200) {
				$("#proappraise .clearfix").removeClass('hide');
				$("#good-div").find('.m-progress-num').text(data.msg.good+"%").end()
					.find('.progress-bar').attr('aria-valuenow', data.msg.good)
					.width(data.msg.good + '%').end()
					.find('.sr-only').text(data.msg.good + '% Complete (warning)');
				$("#normal-div").find('.m-progress-num').text(data.msg.normal+"%").end()
					.find('.progress-bar').attr('aria-valuenow', data.msg.normal)
					.width(data.msg.normal + '%').end()
					.find('.sr-only').text(data.msg.normal + '% Complete (warning)');
				$("#bad-div").find('.m-progress-num').text(data.msg.bad+"%").end()
					.find('.progress-bar').attr('aria-valuenow', data.msg.bad)
					.width(data.msg.bad + '%').end()
					.find('.sr-only').text(data.msg.bad + '% Complete (warning)');
			}
		},
		dataType: 'json'
	});
}

function appriseData() {
		$.ajax({
			type: "POST",
			url: '/ajax/comment/pList',
			data: {
				id: $("#product_id").val(),
				curr_page: $("#comment_curr_page").val()
			},
			beforeSend: function() {
				$(window).unbind('scroll', foo);
				$('.ajax_loading').show() //鏄剧ず鍔犺浇鏃跺€欑殑鎻愮ず
			},
			success: function(data) {
				if(data.code==200){
					//$('#proappraise>.proappraise-list').append(ret); //灏哸jxa璇锋眰鐨勬暟鎹拷鍔犲埌鍐呭閲岄潰    
					var comment_html = '<ul class="list-unstyled">';    
					var comment_star_html = '';
					var comment_main_html = ''
					var comment_image_html = '';
					var list = data.msg;
					if(list.length==0){
						productCommentEnd = true;
						if($("#comment_curr_page").val() > 0){
							MessageBox.errorFadeout("宸茬粡鏈€鍚庝竴椤靛暒");
						}
					}
					$.each(list,function(k,val){
						comment_star_html = "";
						comment_main_html = "";
						comment_image_html = "";
						//set star
						for(var i=1;i<=5;i++){
							if(i<=val.star){
								comment_star_html = comment_star_html+'<i class="glyphicon fdayicon fdayicon-star-full"></i>';
							}else{
								comment_star_html = comment_star_html+'<i class="glyphicon fdayicon"></i>';
							}
						}
						//set image
						if(!val.images){
							comment_main_html = '<div class="m-appraise-content">'+val.content+'</div>';
						}else{						
							var comment_image = val.images.split(',');
							$.each(comment_image,function(kk,vv){
								comment_image_html = comment_image_html+'<li>'+
								'<img class="lazy" src="/images/DefaultImg@2x.png" data-original="'+vv+'" alt="">'+
								'</li>';
							})
							comment_main_html = ' <div class="m-appraise-content">'+
								'<blockquote>'+val.content+'</blockquote>'+
								'<ul class="list-unstyled clearfix m-appraise-imgItem">'+
								comment_image_html+
								'</ul></div>';
						}


						comment_html = comment_html+'<li>'+
							'<div><span class="m-appraise-user">'+val.user_name+'</span>'+
							'<sapn class="pull-right m-appraise-star">'+
							comment_star_html+
							'</sapn></div>'+
							comment_main_html+
							'<div class="m-appraise-data">'+val.time+'</div></li>'; 
					});
					comment_html = comment_html+'</ul>';
					$('#proappraise>.proappraise-list').append(comment_html)
					
					var comment_curr_page = parseInt($("#comment_curr_page").val()) + 1;
					$("#comment_curr_page").val(comment_curr_page);
					lazyloadImg();
				}
				setTimeout(function(){$(window).bind('scroll', foo)},800); 
				$('.ajax_loading').hide();
			},
			dataType: 'json'
		});
}
//璇︽儏椤甸€変腑瑙勬牸鏁版嵁鏄剧ず
function selectProitem(){
	$('.m-proselect-item').each(function(){
		var i=$(this);
		var p=i.find("ul>li");
			p.click(function(){
				if(!$(this).hasClass("selected")){
					$(this).addClass("selected").siblings("li").removeClass("selected");
					i.attr("data-attrval",$(this).attr("data-aid"))
				}
				getattrprice(); //杈撳嚭浠锋牸
			});		
			getattrprice(); //init 鑾峰彇瀵瑰簲灞炴€х殑浠锋牸
			function getattrprice(){
				var defaultstats=true;
				var _val='';
				var _resp={
					mktprice : ".originalcost",
					   price : ".price",
					   norms : ".selectnorms"
				}  //杈撳嚭瀵瑰簲鐨刢lass
				$(".m-proselect-item").each(function(){
					var i=$(this);
					var v=i.attr("data-attrval");	
								
					if(!v){
						defaultstats=false;						
					}else{	
						_val=v;
					}
					
				})
				if(!!defaultstats){
					_mktprice=sys_item['sys_attrprice'][_val]['mktprice'];
					_price=sys_item['sys_attrprice'][_val]['price'];
				}else{
					_mktprice=sys_item['mktprice'];
					_price=sys_item['price'];
				}
				//杈撳嚭浠锋牸
				$(_resp.mktprice).text(_mktprice);  ///鍏朵腑鐨刴ath.round涓烘埅鍙栧皬鏁扮偣浣嶆暟
				$(_resp.price).text(_price);
				if(_mktprice===_price || _mktprice=='0' || _mktprice==undefined || _mktprice=="" || _mktprice==null){
					$(_resp.mktprice).parent('del').hide();
				}else{
					$(_resp.mktprice).parent('del').show();
				}
				$(_resp.norms).text($('.sys_spec_text>.selected').find('a').attr('title'))
			}	
	})
}
