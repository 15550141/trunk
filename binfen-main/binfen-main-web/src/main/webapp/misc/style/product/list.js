$(function() {
	scrollbottomadd();
})

//把这个改成false就有效果了。
//TODO 应该是false
var productListEnd = true;
//滚动到底部加载
function scrollbottomadd(){
	var totalheight = 0;     //定义一个总的高度变量
	function loadDynamicdata(){ 
		totalheight = parseFloat($(window).height()) + parseFloat($(window).scrollTop());     //浏览器的高度加上滚动条的高度 
		if ($(document).height() <= totalheight)    //当文档的高度小于或者等于总的高度的时候，开始动态加载数据
		{ 
			var curr_page = parseInt($("#pro_curr_page").val())+1;
			var class_id = $("#class_id").val();
			var class_name = $("#class_name").val();
			var ajax_data,ajax_url;
			if(class_id == '-1'){
				ajax_data = {
					keyword: class_name,
					curr_page: curr_page
				}
				ajax_url = '/ajax/prolist/search';
			}else{
				ajax_data = {
					class_id: class_id,
					curr_page: curr_page
				}
				ajax_url = '/ajax/prolist/index';
			}
			$.ajax({
				type:"POST",
				url:ajax_url,
				data: ajax_data,
				beforeSend:function(){
					$(window).unbind('scroll', foo); 
					$('.ajax_loading').show() //显示加载时候的提示
				},
				success:function(data){
					if(data.code==200){
						var list_html = '<ul class="list-unstyled">';
						var list_price_html = '';
						var list = data.msg;
						if(list.length==0){
							MessageBox.errorFadeout("已经最后一页啦");
							productListEnd = true;
						}
						$.each(list,function(k,val){
							if(val.old_price==0){
								list_price_html = '<h5>￥ '+val.price+'</h5>';
							}else{
								list_price_html = '<h5>￥'+val.price+' <del>￥'+val.old_price+'</del></h5>';
							}
							list_html = list_html+'<li>'+
								'<a href="/detail/index/'+val.id+'">'+
								'<img class="lazy pull-left" data-original="'+val.thum_photo+'" alt="'+val.product_name+'">'+
								'<div class="m-prolist-info" pid="'+val.id+'" ppid="'+val.price_id+'" pno="'+val.product_no+'" type="normal">'+
								'<h3>'+val.product_name+'</h3>'+
								'<h4>规格：'+val.volume+'</h4>'+
								list_price_html+
								'<span class="m-prolist-car"><i class="glyphicon fdayicon fdayicon-procart"></i></span>'+
								'</div></a></li>'; 
						});
						list_html = list_html+'</ul>';
						$("#container").append(list_html); //将ajxa请求的数据追加到内容里面       

						var pro_curr_page = parseInt($("#pro_curr_page").val()) + 1;
						$("#pro_curr_page").val(pro_curr_page);
						lazyloadImg();
					}
					setTimeout(function(){$(window).bind('scroll', foo)},800); 
					$('.ajax_loading').hide() //请求成功,隐藏加载提示
				},
				dataType: 'json'
			});
		} 
	} 

	var foo = function(){
		if(!productListEnd){
			loadDynamicdata();
		}
	}
	$(window).bind('scroll', foo); 
}
