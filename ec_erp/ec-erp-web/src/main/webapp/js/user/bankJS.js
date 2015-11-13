$("#subButton").click(function() {
	
	var bank=$("#bank").val();
	if(bank.length==0){
		alert("银行开户名不能为空！");
		return;
	}
	var branch=$("#branch").val();
	if(branch.length==0){
		alert("开户银行支行名称不能为空！");
		return;
	}
	var bankProvince=$("#bankProvince").val();
	if(bankProvince.length==0){
		alert("开户银行所在地不能为空！");
		return;
	}
	var bankAc=$("#bankAc").val();
	if(bankAc.length==0){
		alert("开户银行所在地不能为空！");
		return;
	}

	
	$.ajax( {
		url : "/user/addBank",
		type : "post",
		dataType : "json",
		data : $("#f1").serialize(),
		success : function(data) {
			if (data.msg == "success") {
				//增加成功
				alert("成功！");
				window.location.href = "/user/bankList";

			}else {
				alert("系统异常，失败！");
			}
		},
		errot : function() {
			alert("系统超时，失败！");
		}
});
});

$("#queryNew").click(function() {
	$("#f1").attr("action", "/user/bankList");
	$("#f1").attr("method", "post");
	$("#f1").submit();
});

//审核通过银行信息
function examine(obj){
	var userId = obj.id;
	// 根据商品ID 上架商品
	if(confirm("确定要审核通过？")){
		$.ajax( {
			url : "/user/examine",
			type : "post",
			dataType : "json",
			data : "userId="+userId,
			success : function(data) {
				if (data.msg == "success") {
					//删除成功
					alert("审核通过成功！");
					window.location.href = "/user/bankList";
				} else {
					alert("系统异常，审核通过失败！");
				}
			},
			errot : function() {
				alert("系统超时，审核通过促销失败！");
			}
		});
	}
}

//审核不通过促销
function reject(obj){
	var userId = obj.id;
	// 根据商品ID 上架商品
	if(confirm("确定要不通过？")){
		$.ajax( {
			url : "/user/reject",
			type : "post",
			dataType : "json",
			data : "userId="+userId,
			success : function(data) {
				if (data.msg == "success") {
					//删除成功
					alert("审核驳回成功！");
					window.location.href = "/user/bankList";
				} else {
					alert("系统异常，审核驳回失败！");
				}
			},
			errot : function() {
				alert("系统超时，审核驳回失败！");
			}
		});
	}
}
