$(document).ready(function() {
	
	//---控制透明层兼容----
	if (navigator.userAgent.indexOf('IE') >= 0){
	$(".cover").css("filter","alpha(opacity=50)");
	}else{
	$(".cover").css("opacity","0.5");
	}
	
	//---控制鼠标放在表格上面的颜色---
	$(".panel_grid table tr")
	.mouseover(function(){$(this).addClass("trOver");})
	.mouseout(function(){$(this).removeClass("trOver");})
	$(".panel_grid2 table tr")
	.mouseover(function(){$(this).addClass("trOver");})
	.mouseout(function(){$(this).removeClass("trOver");})
});
