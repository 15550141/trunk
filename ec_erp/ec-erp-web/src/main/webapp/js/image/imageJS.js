//上传细节图1
$("#btupload1").click(function() {
	$.ajaxFileUpload( {
		url : "/image/uploaImage",
		secureuri : false,
		fileElementId : "image1",
		data : "imageId=1",
		dataType : "json",
		success : function(message) {
		var data = eval(message);
		
			if (data.msg == "success") {
				$("#spUpload1").css("color", "green");
				$("#spUpload1").html("上传成功");
				$("#imageUrl1").val(data.imageUrl);
				
			}else if(data.msg == "null"){
				alert("没有选择文件！");
				$("#spUpload1").css("color", "red");
				$("#spUpload1").html("没有选择文件！");
			} else {
				alert("上传失败！请重新上传");
				$("#spUpload1").css("color", "red");
				$("#spUpload1").html("上传失败");
			}
		},
		error : function() {
			alert("网络延迟!");
		}
	});
});

//上传细节图2
$("#btupload2").click(function() {
	$.ajaxFileUpload( {
		url : "/image/uploaImage",
		secureuri : false,
		fileElementId : "image2",
		data : "imageId=2",
		dataType : "json",
		success : function(message) {
		var data = eval(message);
		
			if (data.msg == "success") {
				$("#spUpload2").css("color", "green");
				$("#spUpload2").html("上传成功");
				$("#imageUrl2").val(data.imageUrl);
			}else if(data.msg == "null"){
				alert("没有选择文件！");
				$("#spUpload2").css("color", "red");
				$("#spUpload2").html("没有选择文件！");
			} else {
				alert("上传失败！请重新上传");

				$("#spUpload2").css("color", "red");
				$("#spUpload2").html("上传失败");
			}
		},
		error : function() {
			alert("网络延迟!请重新上传");
		}
	});
});


//上传细节图3
$("#btupload3").click(function() {
	$.ajaxFileUpload( {
		url : "/image/uploaImage",
		secureuri : false,
		fileElementId : "image3",
		data : "imageId=3",
		dataType : "json",
		success : function(message) {
		var data = eval(message);
		
			if (data.msg == "success") {
				$("#spUpload3").css("color", "green");
				$("#spUpload3").html("上传成功");
				$("#imageUrl3").val(data.imageUrl);
			}else if(data.msg == "null"){
				alert("没有选择文件！");
				$("#spUpload3").css("color", "red");
				$("#spUpload3").html("没有选择文件！");
			} else {
				alert("上传失败！请重新上传");

				$("#spUpload3").css("color", "red");
				$("#spUpload3").html("上传失败");
			}
		},
		error : function() {
			alert("网络延迟!");
		}
	});
});


//上传细节图4
$("#btupload4").click(function() {
	$.ajaxFileUpload( {
		url : "/image/uploaImage",
		secureuri : false,
		fileElementId : "image4",
		data : "imageId=4",
		dataType : "json",
		success : function(message) {
		var data = eval(message);
		
			if (data.msg == "success") {
				$("#spUpload4").css("color", "green");
				$("#spUpload4").html("上传成功");
				$("#imageUrl4").val(data.imageUrl);
			}else if(data.msg == "null"){
				alert("没有选择文件！");
				$("#spUpload4").css("color", "red");
				$("#spUpload4").html("没有选择文件！");
			} else {
				alert("上传失败！请重新上传");

				$("#spUpload4").css("color", "red");
				$("#spUpload4").html("上传失败");
			}
		},
		error : function() {
			alert("网络延迟!");
		}
	});
});



$("#save").click(function() {
	
	$.ajax( {
		url : "/image/addImage",
		type : "post",
		dataType : "json",
		data : $("#f1").serialize(),
		success : function(data) {
			if (data.msg == "success") {
				//增加成功
				alert("轮播图添加成功！");
				window.location.href = "/image/edit";
			} else {
				alert("系统异常，添加失败！");
			}
		},
		errot : function() {
			alert("系统超时，添加失败！");
		}
});

});


//添加图片
$("#addIndexImage").click(function() {
	
	var imageName = $("#imageName").val();
	if(imageName.length==0){
		alert("图片名称不能为空！");
		return;
	}
	var sortNumber = $("#sortNumber").val();
	if(sortNumber.length==0){
		alert("排序不能为空！");
		return;
	}
	//数字判断
	var txNum = /^[0-9]+$/;
	if (!txNum.test(sortNumber)) {
		alert("排序应该为数字！");
		return;
	}
	
	var imageUrl = $("#imageUrl1").val();
	if(imageUrl.length==0){
		alert("须先上传图片！");
		return;
	}
	
	$.ajax( {
		url : "/image/addImage",
		type : "post",
		dataType : "json",
		data : $("#f1").serialize(),
		success : function(data) {
			if (data.msg == "success") {
				alert("添加成功！");
				$("#imageName").val("");
				$("#sortNumber").val("");
				$("#imageWebUrl").val("");
				$("#imageUrl1").val("");
				$.ajax( {
					url : "/image/getIndexImage",
					type : "post",
					dataType : "json",
					data : "id=1",
					success : function(data) {
						if (data.success == "success") {
								$("#propertyTH").show();
								$("#propertyTab").empty();	
								var indexImageList=data.indexImageList;
								$("#propertyTab").append(	"<tr style='font-weight: bold;'>"+
								"<th class='tdgoods' style='width: 100px' >图片名称</th>"+
								"<th class='tdgoods' style='width: 100px'>图片URL</th>"+
								"<th class='tdgoods' style='width: 100px'>图片链接</th>"+
								"<th class='tdgoods' style='width: 100px'>图片</th>"+
								"<th class='tdgoods' style='width: 100px'>排序</th>"+
								"<th class='tdgoods' style='width: 100px'>操作</th></tr>");

								for(var i=0; i<indexImageList.length; i++){
									var indexImage=indexImageList[i];
									
									//动态添加图片
									
									$("#propertyTab").append(
											"<tr ><td class='tdgoods' style='width: 100px'>" + indexImage.imageName + 
											"</td><td class='tdgoods' style='width: 100px'>" + indexImage.imageWebUrl
													+ "</td><td class='tdgoods' style='width: 100px'>" + indexImage.imageUrl
													+ "</td><td class='tdgoods' style='width: 100px'> <img src='"+indexImage.imageUrl+"' width='60' height='60' alt='无图片' /> " 
													+ "</td><td class='tdgoods' style='width: 100px'> <input style='width: 50px' type='text' name='sortNumber' id='sortNumber_"+indexImage.id+"' value='"+indexImage.sortNumber+"' />"
													+ "<td class='tdgoods' style='width: 100px'>"+
													"<a id='"+indexImage.id+"' href='javascript:void(0)' onclick='removePbss(this);'>删除</a>"
													+"&nbsp&nbsp&nbsp<a id='"+indexImage.id+"' href='javascript:void(0)' onclick='modifyNum(this);'>修改序号</a></td></tr>");
									
								}

						} else {
							alert(data.msg);
						}
					},
					errot : function() {
						alert("系统超时！");
					}
			});
				
				
			} else {
				alert("系统异常，添加失败！");
			}
		},
		errot : function() {
			alert("系统超时，添加失败！");
		}
});
});

function removePbss(obj){
	var id = obj.id;
	//删除属性值 根据属性值ID
	if(confirm("确定要删除属性吗？")){
		$.ajax( {
			url : "/image/deletIndexImage",
			type : "post",
			dataType : "json",
			data : "id="+id,
			success : function(data) {
				if (data.msg == "success") {
					//删除成功
					$(obj).parent().parent().remove();
				} else {
					alert("系统异常，删除失败！");
				}
			},
			errot : function() {
				alert("系统超时，删除失败！");
			}
	});
	}

}

function modifyNum(obj){
	var id = obj.id;
	var sortNumber=$("#sortNumber_"+id).val();
	//删除属性值 根据属性值ID
	if(confirm("确定要修改序号吗？")){
		$.ajax( {
			url : "/image/modifyIndexImage",
			type : "post",
			dataType : "json",
			data : "id="+id+"&sortNumber="+sortNumber,
			success : function(data) {
				if (data.msg == "success") {
					//删除成功
					//$(obj).parent().parent().remove();
					alert("修改成功！");
					window.location.href = "/image/addImg";
					
				} else {
					alert("系统异常，修改失败！");
				}
			},
			errot : function() {
				alert("系统超时，修改失败！");
			}
	});
	}

}
