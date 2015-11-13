
//删除促销
function del(obj){
	var promotionId = obj.id;
	// 根据商品ID 上架商品
	if(confirm("确定要删除促销？")){
		$.ajax( {
			url : "/promotion/delete",
			type : "post",
			dataType : "json",
			data : "promotionId="+promotionId,
			success : function(data) {
				if (data.msg == "success") {
					//删除成功
					alert("删除促销成功！");
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

//上线促销
function start(obj){
	var promotionId = obj.id;
	// 根据商品ID 上架商品
	if(confirm("确定要上线促销？")){
		$.ajax( {
			url : "/promotion/start",
			type : "post",
			dataType : "json",
			data : "promotionId="+promotionId,
			success : function(data) {
				if (data.msg == "success") {
					//删除成功
					alert("上线促销成功！");
					$(obj).parent().parent().remove();
				} else {
					alert("系统异常，上线促销失败！");
				}
			},
			errot : function() {
				alert("系统超时，上线促销失败！");
			}
		});
	}
}
//下线促销
function end(obj){
	var promotionId = obj.id;
	// 根据商品ID 上架商品
	if(confirm("确定要下线促销？")){
		$.ajax( {
			url : "/promotion/end",
			type : "post",
			dataType : "json",
			data : "promotionId="+promotionId,
			success : function(data) {
				if (data.msg == "success") {
					//删除成功
					alert("下线促销成功！");
					$(obj).parent().parent().remove();
				} else {
					alert("系统异常，下线促销失败！");
				}
			},
			errot : function() {
				alert("系统超时，下线促销失败！");
			}
		});
	}
}

//审核通过促销
function examine(obj){
	var promotionId = obj.id;
	// 根据商品ID 上架商品
	if(confirm("确定要审核通过？")){
		$.ajax( {
			url : "/promotion/examine",
			type : "post",
			dataType : "json",
			data : "promotionId="+promotionId,
			success : function(data) {
				if (data.msg == "success") {
					//删除成功
					alert("审核通过成功！");
					$(obj).parent().parent().remove();
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
	var promotionId = obj.id;
	// 根据商品ID 上架商品
	if(confirm("确定要驳回促销？")){
		$.ajax( {
			url : "/promotion/reject",
			type : "post",
			dataType : "json",
			data : "promotionId="+promotionId,
			success : function(data) {
				if (data.msg == "success") {
					//删除成功
					alert("审核驳回成功！");
					$(obj).parent().parent().remove();
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

//单品促销推荐到首页
function recommendS(obj){
	var id = obj.id;
	
	if(confirm("确定要推荐到首页？")){
		$.ajax( {
			url : "/promotion/recommend",
			type : "post",
			dataType : "json",
			data : "id="+id+"&promotionType=1",
			success : function(data) {
				if (data.msg == "success") {
					//删除成功
					alert("推荐到首页成功！");
					window.location.href = "/promotion/singlePromotionList";
					//$(obj).parent().parent().remove();
				} else {
					alert("系统异常，失败！");
				}
			},
			errot : function() {
				alert("系统超时，失败！");
			}
		});
	}
}

//单品促销取消推荐到首页
function unRecommendS(obj){
	var id = obj.id;
	
	if(confirm("确定要取消推荐到首页？")){
		$.ajax( {
			url : "/promotion/unRecommend",
			type : "post",
			dataType : "json",
			data : "id="+id+"&promotionType=1",
			success : function(data) {
				if (data.msg == "success") {
					//删除成功
					alert("取消推荐到首页成功！");
					window.location.href = "/promotion/singlePromotionList";
					//$(obj).parent().parent().remove();
				} else {
					alert("系统异常，失败！");
				}
			},
			errot : function() {
				alert("系统超时，失败！");
			}
		});
	}
}


//产地特供推荐到首页
function recommendP(obj){
	var id = obj.id;
	
	if(confirm("确定要推荐到首页？")){
		$.ajax( {
			url : "/promotion/recommend",
			type : "post",
			dataType : "json",
			data : "id="+id+"&promotionType=2",
			success : function(data) {
				if (data.msg == "success") {
					//删除成功
					alert("推荐到首页成功！");
					window.location.href = "/promotion/productPlacePromotionList";
					//$(obj).parent().parent().remove();
				} else {
					alert("系统异常，失败！");
				}
			},
			errot : function() {
				alert("系统超时，失败！");
			}
		});
	}
}

//产地特供促销取消推荐到首页
function unRecommendP(obj){
	var id = obj.id;
	
	if(confirm("确定要取消推荐到首页？")){
		$.ajax( {
			url : "/promotion/unRecommend",
			type : "post",
			dataType : "json",
			data : "id="+id+"&promotionType=2",
			success : function(data) {
				if (data.msg == "success") {
					//删除成功
					alert("取消推荐到首页成功！");
					window.location.href = "/promotion/productPlacePromotionList";
					//$(obj).parent().parent().remove();
				} else {
					alert("系统异常，失败！");
				}
			},
			errot : function() {
				alert("系统超时，失败！");
			}
		});
	}
}
$("#query").click(function() {
	$("#f1").attr("action", "/promotion/queryPromotion");
	$("#f1").attr("method", "post");
	$("#f1").submit();
});

$("#queryNew").click(function() {
	$("#f1").attr("action", "/promotion/plannedPromotion");
	$("#f1").attr("method", "post");
	$("#f1").submit();
});
$("#queryOn").click(function() {
	$("#f1").attr("action", "/promotion/ongoingPromotion");
	$("#f1").attr("method", "post");
	$("#f1").submit();
});

$("#queryOff").click(function() {
	$("#f1").attr("action", "/promotion/stoppedPromotion");
	$("#f1").attr("method", "post");
	$("#f1").submit();
});

$("#queryOff").click(function() {
	$("#f1").attr("action", "/promotion/stoppedPromotion");
	$("#f1").attr("method", "post");
	$("#f1").submit();
});



$("#queryNew").click(function() {
	$("#f1").attr("action", "/promotion/plannedPromotion");
	$("#f1").attr("method", "post");
	$("#f1").submit();
});

$("#queryProductPlace").click(function() {
	$("#f1").attr("action", "/promotion/productPlacePromotionList");
	$("#f1").attr("method", "post");
	$("#f1").submit();
});
$("#querySingle").click(function() {
	$("#f1").attr("action", "/promotion/singlePromotionList");
	$("#f1").attr("method", "post");
	$("#f1").submit();
});


$("#addPromotionBut").click(function() {
	
	var promotionName=$("#promotionName").val();
	if(promotionName.length==0){
		alert("促销名不能为空！");
		return;
	}
	var startTime=$("#startTime").val();
	if(startTime.length==0){
		alert("促销开始时间不能为空！");
		return;
	}
	var endTime=$("#endTime").val();
	if(endTime.length==0){
		alert("促销结束时间不能为空！");
		return;
	}
	
	var itemId=$("#itemId").val();
	if(itemId.length==0){
		alert("商品ID不能为空！");
		return;
	}
	var purchaseNumberMax=$("#purchaseNumberMax").val();
	var purchaseNumberMin=$("#purchaseNumberMin").val();
	if(purchaseNumberMax.length!=0 && purchaseNumberMin.length!=0 && purchaseNumberMax-purchaseNumberMin<=0){
		alert("最少购买量应小于最大购买量！请重新输入！");
		return;
	}
	
	
	if(confirm("确定要提交促销信息？")){
		$.ajax( {
			url : "/promotion/addPromotion",
			type : "post",
			dataType : "json",
			data : $("#f1").serialize(),
			success : function(data) {
				if (data.msg == "success") {
					alert("促销信息提交成功！转到待审核促销页面！");
					window.location.href = "/promotion/plannedPromotion";
				} else {
					alert("系统异常，添加失败！");
				}
			},
			errot : function() {
				alert("系统超时，添加失败！");
			}
	});
	}
});



$("#cancelEditPromotionBut").click(function() {
	if(confirm("确定要取消修改？")){
		window.location.href = "/promotion/plannedPromotion";
	}
});

$("#editPromotionBut").click(function() {
	

	var promotionName=$("#promotionName").val();
	if(promotionName.length==0){
		alert("促销名不能为空！");
		return;
	}
	var startTime=$("#startTime").val();
	if(startTime.length==0){
		alert("促销开始时间不能为空！");
		return;
	}
	var endTime=$("#endTime").val();
	if(endTime.length==0){
		alert("促销结束时间不能为空！");
		return;
	}
	

	var purchaseNumberMax=$("#purchaseNumberMax").val();
	var purchaseNumberMin=$("#purchaseNumberMin").val();
	if(purchaseNumberMax.length!=0 && purchaseNumberMin.length!=0 && purchaseNumberMax-purchaseNumberMin<=0){
		alert("最少购买量应小于最大购买量！请重新输入！");
		return;
	}
	
	
	
	if(confirm("确定要提交促销信息？")){
		$.ajax( {
			url : "/promotion/editPromotion",
			type : "post",
			dataType : "json",
			data : $("#f1").serialize(),
			success : function(data) {
				if (data.msg == "success") {
					alert("促销信息提交成功！转到待审核促销页面！");
					window.location.href = "/promotion/plannedPromotion";
				} else if(data.msg == "deductionNull") {
					alert("直降金额不能为空！");
				} else{
					alert("系统异常，添加失败！");
				}
			},
			errot : function() {
				alert("系统超时，添加失败！");
			}
	});
	}
	
//	$("#f1").attr("action", "/promotion/addPromotion");
//	$("#f1").attr("method", "post");
//	$("#f1").submit();
});



$("#addProductBut").click(function() {
	
	
	//获取商品信息
	$.ajax( {
		url : "/promotion/queryItem",
		type : "post",
		dataType : "json",
		data : "itemId="+$("#itemId").val(),
		success : function(data) {
			if (data.msg == "success") {
				$("#itemIdSp").css("color", "red");
				$("#itemIdSp").html("商品名："+data.item.itemName);
				var skuList = data.skuList;
				var item = data.item;
				var status;
				if(item.itemStatus==0){
					status="待售";
				}else if(item.itemStatus==1){
					status="上架";
				}else if(item.itemStatus==2){
					status="下架";
				}
				if(skuList.length==0){
					$("#itemIdSp").css("color", "red");
					$("#itemIdSp").html("该商品不存在！");
					return;
				}
				//$("#skuPromotionTB").empty();
				for(var i=0; i<skuList.length; i++){
					var sku =skuList[i];
						
					//动态添加SKU信息
					$("#skuPromotionTB").append(
								"<tr id="+sku.skuId+"><td class='tdgoods' style='width: 100px'>"+sku.itemId+"</td>"+
								"<td class='tdgoods' style='width: 100px'>"+sku.skuId+"</td>"+
								"<td class='tdgoods' style='width: 100px'>"+sku.stock+"</td>"+
								"<td class='tdgoods' style='width: 100px'>"+status+"</td>"+
								"<td class='tdgoods' style='width: 100px'>"+sku.tbPrice+"</td>"+
								"<td class='tdgoods' style='width: 100px'>"+"" +
										"<input type='text' name='deductionPrice' id="+sku.skuId+ " onblur='jg(this);' value='' style='width: 100px'  />"+
										"<input type = 'hidden' name= 'skuId' value ='" +sku.skuId+"'/>"+
										"<input type = 'hidden' name= 'itemIdHid' value ='" +sku.itemId+"'/>"+
										" <input type = 'hidden' id=tbPrice_"+sku.skuId+" name= 'tbPriceHid' value ='" +sku.tbPrice+"'/></td>"+ 
								"<td class='tdgoods' style='width: 100px'>"+"" +
										"<span id='newPrice_"+sku.skuId+"'></span></td>"+
								"</tr>");
				}
			} else {
				$("#itemIdSp").css("color", "red");
				$("#itemIdSp").html("该商品ID不存在！");
			}
		},
		errot : function() {
			alert("系统超时！");
		}
});
	
	
});

function jg(obj){
	var id = obj.id;
	var tbPrice=$("#tbPrice_"+id).val();
	var deductionPrice=$(obj).val();
	
	var txNum = /^[0-9]+.?[0-9]*$/;
	if (!txNum.test(deductionPrice)) {
		alert("直降价格应该是数字！");
		$(obj).val("");
		return;
	}
	
	if(deductionPrice-tbPrice>=0){
		$(obj).val("");
		alert("直降金额应小于售价！请重新输入！");
		
		return;
	}
	$("#newPrice_"+id).html(tbPrice-deductionPrice);

}

$("#clearProductBut").click(function() {
	if(confirm("确定要重置商品？")){
		$("#skuPromotionTB").empty();
	}
	
});


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

$("#specialProvince").change(function() {
	if ($("#specialProvince").val() == -1) {
		$("#specialCity").show();
		$("#specialCounty").show();
		$("#specialCity").empty(); // 清空市区选项
		$("#specialCounty").empty(); // 清空县区选项
		return;
	}
			$.ajax( {
					url : "/address/getAddress",
					type : "post",
					dataType : "json",
					data : "pId="+$("#specialProvince").val(),
					success : function(data) {
					if (data.msg == "success") {
						$("#specialCity").empty(); // 清空市区选项
						var addressList=data.addressList;
						for ( var i = 0; i < addressList.length; i++) {
							var address = addressList[i];
							$("<option value='" + address.addressId + "'>" 
										+  address.addressName + "</option>").appendTo("#specialCity");
							}
						if (addressList.length > 1) {
							if (initValue == 0) {
								$("#specialCity").attr("value", 72);// 设置当前省份
							}
						}
						
						//赋值产地市份ID
//						alert("产地市ID："+$("#specialCityHid").val());
						if($("#specialCityHid").val()!=null){
//							alert("修改产地市ID");
							$("#specialCity").val($("#specialCityHid").val());
						}
						
						$("#specialCity").change(); // 调用县区的连动
					}else{
						alert("无地址信息！");
					}
					},error : function(data) {
						alert("网络传输异常，无法获取地区信息");
					}
				});

});
$("#specialCity")
		.change(function() {
			if ($("#specialCity").val() == -1) {
				$("#specialCounty").show();
				$("#specialCounty").empty(); // 清空县区选项
				// $("<option value='-1'>请选择位置</option>").appendTo("#county");
				return;
			}
			var nowProvName = $("#specialProvince").find("option:selected").text();
			if (nowProvName == "香港" || nowProvName == "澳门"
					|| nowProvName == "台湾" || nowProvName == "海外") {
				$("#specialCity").show();
				$("#specialCity").hide();
			} else if (nowProvName == "重庆" || nowProvName == "天津") {
				$("#specialCounty").show();
				$("#specialCity").hide();
			} else {
				$("#specialCity").show();
			}
			$.ajax( {
				
				url : "/address/getAddress",
				type : "post",
				dataType : "json",
				data : "pId="+$("#specialCity").val(),
				success : function(data) {
				if (data.msg == "success") {
					$("#specialCounty").empty(); // 清空市区选项
					var addressList=data.addressList;
					for ( var i = 0; i < addressList.length; i++) {
						var address = addressList[i];
						$("<option value='" + address.addressId + "'>" 
									+  address.addressName + "</option>").appendTo("#specialCounty");
						}
						if (addressList.length > 1) {
							if (initValue == 0) {
								$("#specialCounty").attr("value", originalCounty);// 设置当前省份
								initValue = 1;
							}
						}
						
						//赋值产地县份ID
//						alert("产地县ID："+$("#specialCountyHid").val());
						if($("#specialCountyHid").val()!=null){
//							alert("修产地县ID");
							$("#specialCounty").val($("#specialCountyHid").val());
						}
						$("#specialCounty").change(); // 调用地区的连动
				}else{
					
				}
				},error : function(data) {
						alert("网络传输异常，无法获取地区信息");
				}
				});

		});
$("#specialCounty").change(
		function() {
			$("#specialProvince_msg").html("");
			var nowProvName = $("#specialProvince").find("option:selected").text();
			if (nowProvName == "香港" || nowProvName == "澳门"
					|| nowProvName == "台湾" || nowProvName == "海外") {
				$("#specialCounty").show();
				$("#specialCounty").hide();
			} else if (nowProvName == "重庆" || nowProvName == "天津") {
				$("#specialCounty").show();
				$("#specialCity").hide();
			} else {
				$("#specialCounty").show();
			}		
		});

$("#specialProvince").change(); // 调用市区的连动

