$("#subButton").click(function() {
	
	var shopName=$("#shopName").val();
	if(shopName.length==0){
		alert("银行开户名不能为空！");
		return;
	}

	
	$.ajax( {
		url : "/user/addUser",
		type : "post",
		dataType : "json",
		data : $("#f1").serialize(),
		success : function(data) {
			if (data.msg == "success") {
				//增加成功
				alert("成功！");
				window.location.href = "/user/showUser";

			}else {
				alert("系统异常，失败！");
			}
		},
		errot : function() {
			alert("系统超时，失败！");
		}
});
});


//上传商家资质图
$("#btupload").click(function() {
	$.ajaxFileUpload( {
		url : "/user/uploaImage",
		secureuri : false,
		fileElementId : "qualificationImage",
		data : "imageId=0",
		dataType : "json",
		success : function(data) {		
			if (data.msg == "success") {
				$("#spUpload").css("color", "green");
				$("#spUpload").html("上传成功");
				$("#qualificationUrl").val(data.imageUrl);
			}else if(data.msg == "null"){
				alert("没有选择文件！");
				$("#spUpload").css("color", "red");
				$("#spUpload").html("没有选择文件！");
			} else {
				$("#spUpload").css("color", "red");
				$("#spUpload").html("上传失败");
			}
		},
		error : function() {
			alert("网络延迟!");
		}
	});
});
