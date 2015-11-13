$("#subButton").click(function() {
	
	var shopName=$("#shopName").val();
	if(shopName.length==0){
		alert("商家名称不能为空！");
		return;
	}
	var commision=$("#commision").val();
	if(commision.length==0){
		alert("佣金不能为空！");
		return;
	}

	
	$.ajax( {
		url : "/user/addBaseInfo",
		type : "post",
		dataType : "json",
		data : $("#f1").serialize(),
		success : function(data) {
			if (data.msg == "success") {
				//增加成功
				alert("成功！");
				window.location.href = "/user/baseInfoList";

			}else {
				alert("系统异常，失败！");
			}
		},
		errot : function() {
			alert("系统超时，失败！");
		}
});
});


$("#query").click(function() {
	$("#f1").attr("action", "/user/list");
	$("#f1").attr("method", "post");
	$("#f1").submit();
});

//用户生效
function effect(obj){
	var mobile = obj.id;
	if(confirm("确定要使用户生效？")){
		$.ajax( {
			url : "/user/effect",
			type : "post",
			dataType : "json",
			data : "mobile="+mobile,
			success : function(data) {
				if (data.msg == "success") {
					//删除成功
					alert("用户生效成功！");
					//$(obj).parent().parent().remove();
					window.location.href = "/user/list";
				} else {
					alert("系统异常，用户生效失败！");
				}
			},
			errot : function() {
				alert("系统超时，用户生效失败！");
			}
		});
	}
}

//用户失效
function unEffect(obj){
	var mobile = obj.id;
	if(confirm("确定要使用户失效？")){
		$.ajax( {
			url : "/user/unEffect",
			type : "post",
			dataType : "json",
			data : "mobile="+mobile,
			success : function(data) {
				if (data.msg == "success") {
					//删除成功
					alert("用户失效成功！");
					window.location.href = "/user/list";
					//$(obj).parent().parent().remove();
				} else {
					alert("系统异常，用户失效失败！");
				}
			},
			errot : function() {
				alert("系统超时，用户失效失败！");
			}
		});
	}
}


//认证商户
function cf(obj){
	var userId = obj.id;
	if(confirm("确定要认证商户？")){
		$.ajax( {
			url : "/user/confirm",
			type : "post",
			dataType : "json",
			data : "userId="+userId,
			success : function(data) {
				if (data.msg == "success") {
					//认证成功
					alert("商户认证成功！");
					//$(obj).parent().parent().remove();
					window.location.href = "/user/list";
				} else {
					alert("系统异常，商户认证失败！");
				}
			},
			errot : function() {
				alert("系统超时，商户认证失败！");
			}
		});
	}
}

//用户失效
function unCf(obj){
	var userId = obj.id;
	if(confirm("确定要解除商户认证？")){
		$.ajax( {
			url : "/user/unConfirm",
			type : "post",
			dataType : "json",
			data : "userId="+userId,
			success : function(data) {
				if (data.msg == "success") {
					//认证成功
					alert("解除商户认证成功！");
					window.location.href = "/user/list";
					//$(obj).parent().parent().remove();
				} else {
					alert("系统异常，解除商户认证失败！");
				}
			},
			errot : function() {
				alert("系统超时，解除商户认证失败！");
			}
		});
	}
}