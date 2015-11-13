//修改联系人基本信息
function linkmanInfoModifySubmit(){
	var url = "updateLinkmanInfo";
	var data = $('#updateLinkmanId').serialize();
	var prompt = "恭喜您，联系人信息修改成功！";
	var flag = confirm("您确认要修改联系人信息吗？");
	if(flag==true){
		ajaxSubmit(url,data,prompt);
	}
}
//ajax提交
function ajaxSubmit(url,data,prompt){
	$.ajax({
		type : "POST",
		url : url,
		data : data,
		cache:false,
		dataType : "json",
		error : function() {
			 alert("网络繁忙，请稍后再试");
		},
		success : function(result) {
			if (result.success) {
				alert(prompt);
			} else {
				alert(result.message);
			}
		}
	});
}