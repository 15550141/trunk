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
				window.location.href = "/user/showBank";

			}else {
				alert("系统异常，失败！");
			}
		},
		errot : function() {
			alert("系统超时，失败！");
		}
});
});