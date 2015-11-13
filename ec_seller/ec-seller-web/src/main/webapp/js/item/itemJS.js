//添加销售属性值
$("#addProVal").click(function() {
	var ifHaveProorty = $("#ifHaveProperty").val();
	if(ifHaveProorty==0){
		alert("该商品没有销售属性，不需要添加！");
		return;
	}
	var propertyValueName = $("#propertyValueName").val();
	if(propertyValueName.length==0){
		alert("销售属性值不能为空！");
		return;
	}
	if(propertyValueName.length>5){
		alert("销售属性值字数应小于5！");
		return;
	}
	var sortNumber = $("#sortNumber").val();
	if(sortNumber.length==0){
		alert("属性排序不能为空！");
		return;
	}
	//数字判断
	var txNum = /^[0-9]+$/;
	if (!txNum.test(sortNumber)) {
		alert("排序应该为数字！");
		return;
	}
	if(sortNumber.length>2){
		alert("属性排序不能大于两位数字！");
		return;
	}
	
	$.ajax( {
		url : "/category/addProVal",
		type : "post",
		dataType : "json",
		data : $("#f1").serialize(),
		success : function(data) {
			if (data.msg == "success") {
				//增加成功
				//window.location.href = "/item/addPro";
				alert("属性添加成功！");
				$("#categoryId4").change();
				//$(obj).parent().parent().remove();
			} else {
				alert("系统异常，添加失败！");
			}
		},
		errot : function() {
			alert("系统超时，添加失败！");
		}
});
	
	//$("#f1").attr("action", "/category/addProVal");
	//$("#f1").attr("method", "post");
	//$("#f1").submit();
	//window.location.href = "/item/addPro";//返回到原页面
});



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
						var categoryList=data.categoryList;
						for ( var i = 0; i < categoryList.length; i++) {
							var category = categoryList[i];
							
							$("<option value='" + category.categoryId + "'>" 
									+ category.categoryName + "</option>").appendTo("#categoryId2");
							
						}
						
						//通过二级分类获取销售属性
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
						var categoryList=data.categoryList;
						for ( var i = 0; i < categoryList.length; i++) {
							var category = categoryList[i];
							
							$("<option value='" + category.categoryId + "'>" 
									+ category.categoryName + "</option>").appendTo("#categoryId4");
							
						}
						
						//通过四级分类获取销售属性
						$("#categoryId4").change();
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
			var categoryId1Name=$("#categoryId1").find("option:selected").text();
			var categoryId2Name=$("#categoryId2").find("option:selected").text();
			var categoryId3Name=$("#categoryId3").find("option:selected").text();
			var categoryId4Name=$("#categoryId4").find("option:selected").text();
//			
//			alert(categoryId1Name);
//			alert(categoryId2Name);
			$.ajax( {
				url : "/category/getProperty",
				type : "post",
				dataType : "json",
				data : "categoryId="+$("#categoryId4").val(),
				success : function(data) {
					if (data.success == "success") {
						if(data.hasProperty=="no"){//没有销售属性
							
							$("#ifHaveProperty").val("0");
							alert("该商品没有销售属性，不需要添加！");
							$("#propertyTH").hide();
							$("#propertyTab").empty();

						}else{
							$("#ifHaveProperty").val("1");
							$("#propertyTH").show();
							$("#propertyTab").empty();
							if(data.haveProperyValue=="no"){//没有销售属性值
								alert(data.msg);
								return;
							}							
							$("#property").val(data.propertyName);
							$("#propertyId").val(data.propertyId);
							var propertyValueList=data.propertyValueList;
							//properId存放在页面上
							$("#propertyId").val(propertyValueList[0].propertyId);
							
							$("#propertyTab").append(	"<tr style='font-weight: bold;'>"+
									"<th class='tdgoods' style='width: 100px' >一级分类</th>"+
									"<th class='tdgoods' style='width: 100px'>二级分类</th>"+
									"<th class='tdgoods' style='width: 100px'>三级分类</th>"+
									"<th class='tdgoods' style='width: 100px'>四级分类</th>"+
									"<th class='tdgoods' style='width: 100px'>属性值</th>"+
									"<th class='tdgoods' style='width: 100px'>排序</th>"+
									"<th class='tdgoods' style='width: 100px'>操作</th></tr>");
							for(var i=0; i<propertyValueList.length; i++){
								var property=propertyValueList[i];
								
								//动态添加属性值区
								
								$("#propertyTab").append(
										"<tr><td class='tdgoods' style='width: 100px'>" + categoryId1Name + 
										"</td><td class='tdgoods' style='width: 100px'>" + categoryId2Name+
										"<td class='tdgoods' style='width: 100px'>" + categoryId3Name + 
										"</td><td class='tdgoods' style='width: 100px'>" + categoryId4Name										
												+ "</td><td class='tdgoods' style='width: 100px'>" + property.propertyValueName
												+ "</td><td class='tdgoods' style='width: 100px'>"+ property.sortNumber
												+ "<td class='tdgoods' style='width: 60px'>"+
												"<a id='"+property.propertyValueId+"' href='javascript:void(0)' onclick='removePbss(this);'>删除</a></td></tr>");
								
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

function removePbss(obj){
	var proValId = obj.id;
	//删除属性值 根据属性值ID
	if(confirm("确定要删除属性吗？")){
		$.ajax( {
			url : "/category/deletProVal",
			type : "post",
			dataType : "json",
			data : "proValId="+proValId,
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

