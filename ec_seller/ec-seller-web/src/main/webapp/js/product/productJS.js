$("#query").click(function() {
	$("#f1").attr("action", "/product/onSaleProduct");
	$("#f1").attr("method", "post");
	$("#f1").submit();
});


$("#itemName").blur(function() {
	var itemName=$("#itemName").val();
	if(itemName.length<6){
		$("#itemNameSp").css("color", "red");
		$("#itemNameSp").html("商品名称应大于5个字！");
	}
	if(itemName.length>45){
		$("#itemNameSp").css("color", "red");
		$("#itemNameSp").html("商品名称应小于46个字！");
	}	
});

$("#subButton").click(function() {
	//alert("editor.getContent()=="+UE.getEditor('editorPC').getContent());
	//$("#pcDescriptionInfo").val(UE.getEditor('editorPC').getContent());
	//$("#appDescriptionInfo").val(UE.getEditor('editorAPP').getContent());
	$("#pcDescriptionInfo").val($("#contentInfoPC").val());
	$("#appDescriptionInfo").val($("#contentInfoAPP").val());
	var itemName=$("#itemName").val();
	if(itemName.length==0){
		alert("商品名称不能为空！");
		return;
	}
	if(itemName.length<6){
		alert("商品名称应大于5个字！");
		return;
	}
	if(itemName.length>45){
		alert("商品名称应小于46个字！");
		return;
	}
//	var ifDeposit=$("#ifDeposit").val();
//	if(document.getElementById("ifDepositYes").checked==true){
//		if($("#depositRate").val().length==0){
//			alert("定金支付比例不能为空！！");
//			return;
//		}
//		if($("#depositRate").val()-100>=0){
//			alert("定金支付比例不能大于100%！！");
//			return;
//			
//		}
//	}
	
	$.ajax( {
		url : "/product/addProduct",
		type : "post",
		dataType : "json",
		data : $("#f1").serialize(),
		success : function(data) {
			if (data.msg == "success") {
				//增加成功
				alert("商品添加成功！转到待售商品页面！");
				window.location.href = "/product/preSaleProduct";

			}else if(data.msg=="priceNull"){
				alert("商品库存价格模块参数不能为空！");
			}else if(data.msg=="nullProperty"){
				alert("请添加销售属性！");
			}else if(data.msg=="nullSelectProperty"){
				alert("请选择销售属性！");
			}
			else {
				alert("系统异常，添加失败！");
			}
		},
		errot : function() {
			alert("系统超时，添加失败！");
		}
});
});

//上架
function startSale(obj){
	var itemId = obj.id;
	// 根据商品ID 上架商品
	if(confirm("确定要上架吗？")){
		$.ajax( {
			url : "/product/startSale",
			type : "post",
			dataType : "json",
			data : "itemId="+itemId,
			success : function(data) {
				if (data.msg == "success") {
					//删除成功
					alert("商品上架成功！");
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

//下架
function offSale(obj){
	var itemId = obj.id;
	// 根据商品ID 下架商品
	if(confirm("确定要下架吗？")){
		$.ajax( {
			url : "/product/offSale",
			type : "post",
			dataType : "json",
			data : "itemId="+itemId,
			success : function(data) {
				if (data.msg == "success") {
					//删除成功
					alert("商品下架成功！");
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



$("#editButton").click(function() {
	$("#pcDescriptionInfo").val($("#contentInfoPC").val());
	$("#appDescriptionInfo").val($("#contentInfoAPP").val());
	//conferm("确认添加商品？添加成功后转到待售商品列表！");
	//alert("确认添加商品？添加成功后转到待售商品列表！");
	var itemName=$("#itemName").val();
	if(itemName.length==0){
		alert("商品名称不能为空！");
		return;
	}
	if(itemName.length<6){
		alert("商品名称应大于5个字！");
		return;
	}
	if(itemName.length>45){
		alert("商品名称应小于46个字！");
		return;
	}
//	var ifDeposit=$("#ifDeposit").val();
//	if(document.getElementById("ifDepositYes").checked==true){
//		if($("#depositRate").val().length==0){
//			alert("定金支付比例不能为空！！");
//			return;
//		}
//	}
	if(confirm("确定要修改商品信息？")){
		$.ajax( {
			url : "/product/modifyProduct",
			type : "post",
			dataType : "json",
			data : $("#f1").serialize(),
			success : function(data) {
				if (data.msg == "success") {
					alert("商品修改保存成功！转到待售商品页面！");
					window.location.href = "/product/preSaleProduct";
				}else if(data.msg=="priceNull"){
					alert("商品库存价格模块参数不能为空！");
				} else {
					alert("系统异常，添加失败！");
				}
			},
			errot : function() {
				alert("系统超时，添加失败！");
			}
	});
	}
	
	//$("#f1").attr("action", "/product/modifyProduct");
	//$("#f1").attr("method", "post");
	//$("#f1").submit();
//	alert("修改成功！");
});


$("#packingType").change(
		function() {
			if($("#packingType").val()==4){
				$("#packingTypeCustom").show();
			}else{
				$("#packingTypeCustom").hide();
			}

});

$("#ifDepositNo").click(function() {
	$("#zfbl").hide();
	$("#zfblInput").hide();
});
$("#ifDepositYes").click(function() {
	$("#zfbl").show();
	$("#zfblInput").show();
});

//上传主图
$("#btupload0").click(function() {
	$.ajaxFileUpload( {
		url : "/category/uploaImage",
		secureuri : false,
		fileElementId : "mainImage",
		data : "imageId=0",
		dataType : "json",
		success : function(data) {		
			if (data.msg == "success") {
				$("#spUpload0").css("color", "green");
				$("#spUpload0").html("上传成功");
				$("#imageUrl0").val(data.imageUrl);
			}else if(data.msg == "null"){
				alert("没有选择文件！");
				$("#spUpload0").css("color", "red");
				$("#spUpload0").html("没有选择文件！");
			} else {
				$("#spUpload0").css("color", "red");
				$("#spUpload0").html("上传失败");
			}
		},
		error : function() {
			alert("网络延迟!");
		}
	});
});

//上传细节图1
$("#btupload1").click(function() {
	$.ajaxFileUpload( {
		url : "/category/uploaImage",
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
			}else {
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
		url : "/category/uploaImage",
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
		url : "/category/uploaImage",
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
		url : "/category/uploaImage",
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


////上传主图
//$("#btupload1").click(function() {
////	$("#loadpanel").showLoading();
//	$.uploadify({
//	    uploader: "/js/uploadify/uploadify.swf",
//	    script: "/category/uploaImage1",
//	    queueSizeLimit: 20,
//	    scriptAccess:"always",
//	    queueID: "image1",
//	    fileDesc: "图片文件",
//	    folder : "/userImage",     //要上传到的服务器路径，默认‘/’ 
//	    fileExt: "*.jpg;*.png;*.gif;*.bmp;*.jpeg",
//	    auto: false,
//	    multi: true,
//	    sizeLimit: 1024 * 1024 * 4,
//	    onComplete: function(event, queueID, fileObj, response, data) { 
//	    	OnComplete(event, queueID, fileObj, response, data); 
//	    	return false; 
//	    	},
//
//	    onError: function(event, queueID, fileObj, errorObj) { 
//	    		alert("文件" + fileObj.name + "上传出错"); 
//	    		}
//
//	});
//});

//
//$("#image1").uploadify({
//    'uploader': '/js/jqueryplus/uploadify/uploadify.swf',
//    'script': '../../upload/uploadonefile/multifile.aspx?r=' + Math.random(),
//    'queueSizeLimit': 20,
//    'scriptAccess': 'always',
//    'queueID': 'divphoto_list',
//    'fileDesc': '图片文件',
//    'folder' : '/userImage',     //要上传到的服务器路径，默认‘/’ 
//    'fileExt': '*.jpg;*.png;*.gif;*.bmp;*.jpeg',
//    'width': 84,
//    'height': 24,
//    'auto': false,
//    'multi': true,
//    'sizeLimit': 1024 * 1024 * 4,
//    'onComplete': function(event, queueID, fileObj, response, data) { 
//    	OnComplete(event, queueID, fileObj, response, data); 
//    	return false; 
//    	},
//
//    'onError': function(event, queueID, fileObj, errorObj) { 
//    		alert("文件" + fileObj.name + "上传出错"); 
//    		}
//
//
//});



//一级分类联动，获取二级分类
$("#categoryId1").change(
		function() {
			$.ajax( {
				url : "/category/getLevel",
				type : "post",
				dataType : "json",
				data : "level=2&pId="+$("#categoryId1").val(),
				success : function(data) {
					if (data.msg == "success") {
						$("#categoryId2").empty(); // 清空分类选项
//						$("<option value=''> 全选 </option>").appendTo("#categoryId2");
						var categoryList=data.categoryList;
						for ( var i = 0; i < categoryList.length; i++) {
							var category = categoryList[i];
							
							$("<option value='" + category.categoryId + "'>" 
									+ category.categoryName + "</option>").appendTo("#categoryId2");
							
						}

						$("#categoryId2").change();


					} else {
						alert("无二级分类！");
					}
				},
				errot : function() {
					alert("系统超时！");
				}
		});
});


//二级分类联动，获取三级分类
$("#categoryId2").change(
		function() {
			$.ajax( {
				url : "/category/getLevel",
				type : "post",
				dataType : "json",
				data : "level=3&pId="+$("#categoryId2").val(),
				success : function(data) {
					if (data.msg == "success") {
						$("#categoryId3").empty(); // 清空分类选项
//						$("<option value=''> 全选 </option>").appendTo("#categoryId3");
						var categoryList=data.categoryList;
						for ( var i = 0; i < categoryList.length; i++) {
							var category = categoryList[i];
							
							$("<option value='" + category.categoryId + "'>" 
									+ category.categoryName + "</option>").appendTo("#categoryId3");
							
						}
						
						//通过二级分类获取销售属性
						$("#categoryId3").change();
					} else {
						alert("无二级分类！");
					}
				},
				errot : function() {
					alert("系统超时！");
				}
		});
});

//三级分类联动，获取四级分类
$("#categoryId3").change(
		function() {
			$.ajax( {
				url : "/category/getLevel",
				type : "post",
				dataType : "json",
				data : "level=4&pId="+$("#categoryId3").val(),
				success : function(data) {
					if (data.msg == "success") {
						$("#categoryId4").empty(); // 清空分类选项
//						$("<option value=''> 全选 </option>").appendTo("#categoryId4");
						var categoryList=data.categoryList;
						for ( var i = 0; i < categoryList.length; i++) {
							var category = categoryList[i];
							
							$("<option value='" + category.categoryId + "'>" 
									+ category.categoryName + "</option>").appendTo("#categoryId4");
							
						}
						
						//通过四级分类获取销售属性
						if($("#categoryId4").val()==""){
							//alert("全选！");
						}else{
							//alert("获取属性！");
							$("#categoryId4").change();
						}
						
					} else {
						alert("无二级分类！");
					}
				},
				errot : function() {
					alert("系统超时！");
				}
		});
});


//四级分类联动时判断是否有销售属性
$("#categoryId4").change(
		function() {
			$.ajax( {
				url : "/category/getProperty",
				type : "post",
				dataType : "json",
				data : "categoryId="+$("#categoryId4").val(),
				success : function(data) {
					if (data.success == "success") {
						if(data.hasProperty=="no"){
							$("#propertyDiv").hide();
							$("#priceWithPropertyDiv").hide();
							$("#priceDiv").show();
							$("#hasPropertyInput").val("0");//没有销售属性
							return;
						}else{
							$("#propertyDiv").show();
							$("#priceWithPropertyDiv").show();
							$("#priceDiv").hide();
							$("#hasPropertyInput").val("1");//有销售属性
							if(data.haveProperyValue=="no"){
								alert(data.msg);
								$("#propertyTB").empty();
								$("#priceTB").empty();
								return;
							}
							var propertyValueList=data.propertyValueList;
							$("#propertyTB").empty();
							$("#priceTB").empty();
							
							for(var i=0; i<propertyValueList.length; i++){
								var property=propertyValueList[i];
								
								//动态添加属性值区
								$("#propertyTB").append(
										"<td style='width: 20px'></td><td align='left' style='width: 70px'>"+
										"<input type='checkbox' id='"+property.propertyValueId+"' name='"+property.propertyValueName+"' value='"+
										property.propertyValueId+"' onclick='propertyCheck(this.value)'/>"+property.propertyValueName+
										"</td>");
								
								
								
								//动态添加价格库存区
								
								$("#priceTB").append(
										"<tr id='priceTR_"+property.propertyValueId+"' style='display: none;'> <td style='width: 100px' align='right'>"+property.propertyValueName+
										"</td><td align='right' style='width: 100px'>*阿土价：</td>"+
										"<td style='width: 100px'><input type='text' name='tbPrice' id='tbPrice_"+property.propertyValueId+"' value='' style='width: 80px' />&nbsp;元</td>"+
										"<td align='right' style='width: 100px'>*供应量：</td>"+
										"<td style='width: 80px'><input type='text' name='stock' id='stock_"+property.propertyValueId+"' value='' style='width: 80px' /></td>"+
										"<td align='right' style='width: 100px'>*最低起卖量：</td>"+
										"<td style='width: 80px'><input type='text' name='leastBuy' id='leastBuy_"+property.propertyValueId+"' value='' style='width: 80px' /></td>"+
										"<td align='right' style='width: 100px'>条形码：</td>"+
										"<td style='width: 80px'><input type='text' name='barCode' id='barCode_"+property.propertyValueId+"' value='' style='width: 80px' /></td>"+
										"<td><input type = 'hidden' id='ifChoose_"+property.propertyValueId+"' name= 'ifChoose' value =''/>"+
										"<input type = 'hidden' name= 'propertyId' value ='" +property.propertyId+"'/>"+
										"<input type = 'hidden' name= 'propertyValueId' value ='" +property.propertyValueId+"'/></td></tr>");
							}
						}

					} else {
						alert(data.msg);
					}
				},
				errot : function() {
					alert("系统超时！");
				}
		});
			
});


function propertyCheck(id){
//	alert("是否为选中"+document.getElementById(id).checked);
	if(document.getElementById(id).checked==true){//多选按钮选中，显示
//		alert(document.getElementById(id).name);
		var propertyName = document.getElementById(id).name;
		document.getElementById("priceTR_"+id).style.display='table-row';
		document.getElementById("ifChoose_"+id).value="1";
		
	}
	if(document.getElementById(id).checked==false){//多选按钮不选，隐藏
		//$("#tbPrice").val("");
		//不选择时，清空输入框
		document.getElementById("tbPrice_"+id).value="";
		document.getElementById("stock_"+id).value="";
		document.getElementById("tbPrice_"+id).value="";
		document.getElementById("leastBuy_"+id).value="";
		document.getElementById("barCode_"+id).value="";
		document.getElementById("ifChoose_"+id).value="0";
		document.getElementById("priceTR_"+id).style.display='none';
	}
	
	$("#priceTB_"+id).hide();
}




var originalProvince = '0';// 省ID
if (originalProvince == 0) {
	originalProvince = 1;
}
var originalCity = '0';// 市ID
if (originalCity == 0) {
	originalCity = 72;
}
var originalCounty = '0';// 县区ID
if (originalCounty == 0) {
	originalCounty = 2799;
}
//$("#supplyProvince").attr("value", originalProvince);// 设置当前省份
var initValue = 0;

$("#supplyProvince").change(function() {
	if ($("#supplyProvince").val() == -1) {
		$("#supplyCity").show();
		$("#supplyCounty").show();
		$("#supplyCity").empty(); // 清空市区选项
		$("#supplyCounty").empty(); // 清空县区选项
		return;
	}
			$.ajax( {
					url : "/address/getAddress",
					type : "post",
					dataType : "json",
					data : "pId="+$("#supplyProvince").val(),
					success : function(data) {
					if (data.msg == "success") {
						$("#supplyCity").empty(); // 清空市区选项
						var addressList=data.addressList;
						for ( var i = 0; i < addressList.length; i++) {
							var address = addressList[i];
							$("<option value='" + address.addressId + "'>" 
										+  address.addressName + "</option>").appendTo("#supplyCity");
							}
						if (addressList.length > 1) {
							if (initValue == 0) {
								$("#supplyCity").attr("value", 72);// 设置当前省份
							}
						}
						
						//赋值供货地址市份ID
//						alert("供货地址市ID："+$("#supplyCityHid").val());
						if($("#supplyCityHid").val()!=null){
//							alert("修改市ID");
							$("#supplyCounty").val($("#supplyCityHid").val());
						}
						
						$("#supplyCity").change(); // 调用县区的连动
					}else{
						alert("无地址信息！");
					}
					},error : function(data) {
						alert("网络传输异常，无法获取地区信息");
					}
				});

});
$("#supplyCity")
		.change(function() {
			if ($("#supplyCity").val() == -1) {
				$("#supplyCounty").show();
				$("#supplyCounty").empty(); // 清空县区选项
				// $("<option value='-1'>请选择位置</option>").appendTo("#county");
				return;
			}
			var nowProvName = $("#supplyProvince").find("option:selected").text();
			if (nowProvName == "香港" || nowProvName == "澳门"
					|| nowProvName == "台湾" || nowProvName == "海外") {
				$("#supplyCity").show();
				$("#supplyCity").hide();
			} else if (nowProvName == "重庆" || nowProvName == "天津") {
				$("#supplyCounty").show();
				$("#supplyCity").hide();
			} else {
				$("#supplyCity").show();
			}
			$.ajax( {
				
				url : "/address/getAddress",
				type : "post",
				dataType : "json",
				data : "pId="+$("#supplyCity").val(),
				success : function(data) {
				if (data.msg == "success") {
					$("#supplyCounty").empty(); // 清空市区选项
					var addressList=data.addressList;
					for ( var i = 0; i < addressList.length; i++) {
						var address = addressList[i];
						$("<option value='" + address.addressId + "'>" 
									+  address.addressName + "</option>").appendTo("#supplyCounty");
						}
						if (addressList.length > 1) {
							if (initValue == 0) {
								$("#supplyCounty").attr("value", originalCounty);// 设置当前省份
								initValue = 1;
							}
						}
						//赋值供货地址县份ID
//						alert("供货地址县ID："+$("#supplyCountyHid").val());
						if($("#supplyCountyHid").val()!=null){
//							alert("修改县ID");
							$("#supplyCounty").val($("#supplyCountyHid").val());
						}
						
						$("#supplyCounty").change(); // 调用地区的连动
				}else{
					
				}
				},error : function(data) {
						alert("网络传输异常，无法获取地区信息");
				}
				});

		});
$("#supplyCounty").change(
		function() {
			$("#supplyProvince_msg").html("");
			var nowProvName = $("#supplyProvince").find("option:selected").text();
			if (nowProvName == "香港" || nowProvName == "澳门"
					|| nowProvName == "台湾" || nowProvName == "海外") {
				$("#supplyCounty").show();
				$("#supplyCounty").hide();
			} else if (nowProvName == "重庆" || nowProvName == "天津") {
				$("#supplyCounty").show();
				$("#supplyCity").hide();
			} else {
				$("#supplyCounty").show();
			}		
		});
$("#supplyProvince").change(); // 调用市区的连动
function isNotNull(str) {
	if ($.trim(str) == "") {
		return false;
	} else {
		return true;
	}
}


var originalProvince = '0';// 省ID
if (originalProvince == 0) {
	originalProvince = 1;
}
var originalCity = '0';// 市ID
if (originalCity == 0) {
	originalCity = 72;
}
var originalCounty = '0';// 县区ID
if (originalCounty == 0) {
	originalCounty = 2799;
}
//$("#supplyProvince").attr("value", originalProvince);// 设置当前省份
var initValue = 0;

$("#originProvince").change(function() {
	if ($("#originProvince").val() == -1) {
		$("#originCity").show();
		$("#originCounty").show();
		$("#originCity").empty(); // 清空市区选项
		$("#originCounty").empty(); // 清空县区选项
		return;
	}
			$.ajax( {
					url : "/address/getAddress",
					type : "post",
					dataType : "json",
					data : "pId="+$("#originProvince").val(),
					success : function(data) {
					if (data.msg == "success") {
						$("#originCity").empty(); // 清空市区选项
						var addressList=data.addressList;
						for ( var i = 0; i < addressList.length; i++) {
							var address = addressList[i];
							$("<option value='" + address.addressId + "'>" 
										+  address.addressName + "</option>").appendTo("#originCity");
							}
						if (addressList.length > 1) {
							if (initValue == 0) {
								$("#originCity").attr("value", 72);// 设置当前省份
							}
						}
						
						//赋值产地市份ID
//						alert("产地市ID："+$("#originCityHid").val());
						if($("#originCityHid").val()!=null){
//							alert("修改产地市ID");
							$("#originCity").val($("#originCityHid").val());
						}
						
						$("#originCity").change(); // 调用县区的连动
					}else{
						alert("无地址信息！");
					}
					},error : function(data) {
						alert("网络传输异常，无法获取地区信息");
					}
				});

});
$("#originCity")
		.change(function() {
			if ($("#originCity").val() == -1) {
				$("#originCounty").show();
				$("#originCounty").empty(); // 清空县区选项
				// $("<option value='-1'>请选择位置</option>").appendTo("#county");
				return;
			}
			var nowProvName = $("#originProvince").find("option:selected").text();
			if (nowProvName == "香港" || nowProvName == "澳门"
					|| nowProvName == "台湾" || nowProvName == "海外") {
				$("#originCity").show();
				$("#originCity").hide();
			} else if (nowProvName == "重庆" || nowProvName == "天津") {
				$("#originCounty").show();
				$("#originCity").hide();
			} else {
				$("#originCity").show();
			}
			$.ajax( {
				
				url : "/address/getAddress",
				type : "post",
				dataType : "json",
				data : "pId="+$("#originCity").val(),
				success : function(data) {
				if (data.msg == "success") {
					$("#originCounty").empty(); // 清空市区选项
					var addressList=data.addressList;
					for ( var i = 0; i < addressList.length; i++) {
						var address = addressList[i];
						$("<option value='" + address.addressId + "'>" 
									+  address.addressName + "</option>").appendTo("#originCounty");
						}
						if (addressList.length > 1) {
							if (initValue == 0) {
								$("#originCounty").attr("value", originalCounty);// 设置当前省份
								initValue = 1;
							}
						}
						
						//赋值产地县份ID
//						alert("产地县ID："+$("#originCountyHid").val());
						if($("#originCountyHid").val()!=null){
//							alert("修产地县ID");
							$("#originCounty").val($("#originCountyHid").val());
						}
						$("#originCounty").change(); // 调用地区的连动
				}else{
					
				}
				},error : function(data) {
						alert("网络传输异常，无法获取地区信息");
				}
				});

		});
$("#originCounty").change(
		function() {
			$("#originProvince_msg").html("");
			var nowProvName = $("#originProvince").find("option:selected").text();
			if (nowProvName == "香港" || nowProvName == "澳门"
					|| nowProvName == "台湾" || nowProvName == "海外") {
				$("#originCounty").show();
				$("#originCounty").hide();
			} else if (nowProvName == "重庆" || nowProvName == "天津") {
				$("#originCounty").show();
				$("#originCity").hide();
			} else {
				$("#originCounty").show();
			}		
		});

$("#originProvince").change(); // 调用市区的连动


//控制全选按钮
function CheckAll(form) {
	for ( var i = 0; i < form.elements.length; i++) {
		var e = form.elements[i];
		if (e.Name != "chkTable" && e.disabled == false) {
			e.checked = form.chkTable.checked;
		}
	}
	form.chkAll.checked = false;
}

//全部下架
$("#stopAllBut").click(function() {
	if(confirm("确定要批量下架吗？")){
	$("[name='id']:checked").each(function() {
		var itemId = $(this).val();
		//ckValue = $(this).val() + "," + ckValue;
		//alert("itemId=="+itemId);
		// 根据商品ID 下架商品
			$.ajax( {
				url : "/product/offSale",
				type : "post",
				dataType : "json",
				data : "itemId="+itemId,
				success : function(data) {
					if (data.msg == "success") {
						//删除成功
						alert("编号为："+itemId+"的商品下架成功！");
						$("#"+itemId).parent().parent().remove();
					} else {
						alert("系统异常，下架失败！");
					}
				},
				errot : function() {
					alert("系统超时，下架失败！");
				}
			});	
	
	});
	alert("批量下架成功！");
	
	}
	
	
});


//批量上架
$("#startAllBut").click(function() {
	if(confirm("确定要批量上架吗？")){
	$("[name='id']:checked").each(function() {
		var itemId = $(this).val();
		//ckValue = $(this).val() + "," + ckValue;
		//alert("itemId=="+itemId);
		// 根据商品ID 下架商品
			$.ajax( {
				url : "/product/startSale",
				type : "post",
				dataType : "json",
				data : "itemId="+itemId,
				success : function(data) {
					if (data.msg == "success") {
						//alert("编号为："+itemId+"的商品上架成功！");
						$("#"+itemId).parent().parent().remove();
					} else {
						alert("系统异常，上架失败！");
					}
				},
				errot : function() {
					alert("系统超时，上架失败！");
				}
			});	
	
	});
	alert("批量上架成功！");
	}	
});



