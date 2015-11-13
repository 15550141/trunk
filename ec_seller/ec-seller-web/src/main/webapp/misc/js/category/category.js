$(document).ready(function(){
	if(flag==1){
		$("#actived").attr("disabled","disabled");
		$("#activeDes").append("当前已激活");
	}else if(flag==0){
		$("#activeDes").append("当前未激活");
	}else{
		$("#activeDes").append("激活状态未知");
		$("#actived").attr("disabled","disabled");
	}
});
function submitCat(content){
		$.ajax({
			type : "POST",
			url : "/contractCat/insertCat",
			data : "ids="+content+"&contractNumber="+contractNumber,
			cache:false,
			dataType : "json",
			error : function(XMLHttpRequest, textStatus, errorThrow) {
				 alert("网络繁忙，请稍后再试");
			},
			success : function(result) {
					alert(result.message);
					$("#actived").removeAttr("disabled");
					$("#activeDes").html("");
					$("#activeDes").append("当前未激活");
			}
		});
	
}

function active(){
	$.ajax({
		type : "POST",
		url : "/userCat/active",
		data : "contractNumber="+contractNumber,
		cache:false,
		dataType : "json",
		error : function(XMLHttpRequest, textStatus, errorThrow) {
			 alert("网络繁忙，请稍后再试");
		},
		success : function(result) {
				alert(result.message);
				$("#actived").attr("disabled","disabled");
				$("#activeDes").html("");
				$("#activeDes").append("当前已激活");
		}
	});
}
function submitChildCats(content,childPin){
	var flag=window.confirm("您为子账号"+childPin+"重新设置了分类，确定提交吗?");
	if(!flag){
		return false;
	}
	
	$.ajax({
		type : "POST",
		url : "/userCat/insertChild",
		data : "ids="+content+"&childPin="+childPin,
		cache:false,
		dataType : "json",
		error : function(XMLHttpRequest, textStatus, errorThrow) {
			 alert("网络繁忙，请稍后再试");
		},
		success : function(result) {
				alert(result.message);
		}
	});
}