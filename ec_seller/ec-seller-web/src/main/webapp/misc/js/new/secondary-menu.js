$(document).ready(function(){
	 //查询订单详情记录
	function queryOrderDetail(){
		var value = $("#queryInputField").val();
		if(""!=value){
			parent.document.getElementById("rightFrame").src ="/foundation/"+encodeURI(encodeURI(value));
		}
	}
	
	$("#searchButton").bind('click',queryOrderDetail);
	
	 $("#queryInputField").powerFloat({ eventType: "focus", targetMode: "remind", targetAttr: "inputNotice", position: "1-4" });
	 $("#queryInputField").bind('keyup',function(event){
	        if(event.keyCode == 13){
	        	queryOrderDetail();
	        }
	  });
	 $(".menu-item").bind('click',function(){
			if($(this).hasClass('menu-open')){
				$(this).removeClass('menu-open');
				$(this).next('ul').hide();
				if($(".sub-menu :visible").length==0){
					$("#collapseAll").hide();
					$("#expandAll").show();
				}
			}else{
				$(this).addClass('menu-open');
				$(this).next('ul').show();
				if($(".sub-menu :hidden").length==0){
					$("#expandAll").hide();
					$("#collapseAll").show();
				}
			}
		});
		//展开按钮绑定单击事件
		$("#expandAll").bind('click',function(){
			$(".menu-item").addClass('menu-open');
			$(".sub-menu").show();
			$(this).hide();
			$("#collapseAll").show();
		});
		//折叠按钮绑定单击事件
		$("#collapseAll").bind('click',function(){
			$(".menu-item").removeClass('menu-open');
			$(".sub-menu").hide();
			$(this).hide();
			$("#expandAll").show();
		});
	});
