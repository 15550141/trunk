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
function BrandsOnload(){
	onCat1ListChange();
	$("#categoryId2").bind("change", function(){
		
		var proId = $("#categoryId2").find("option:selected").val();
		//清空
		var categoryId3 = g("categoryId3");
		clearOptions(categoryId3);
		$("#brands").text("");
		initCat3List(proId);
		});
	
		$("#categoryId3").bind("change", function(){
		var proId = $("#categoryId3").find("option:selected").val();
		//清空
		$("#brands").text("");
//    	initBrandsList(proId);
		});
}
/*品类一级分类*/
function onCat1ListChange(){
	var proSelection=$("#categoryId1");
	proSelection.bind("change", function(){
		var proId = proSelection.find("option:selected").val();
		//清空
		var categoryId2 = g("categoryId2");
		var categoryId3 = g("categoryId3");
		clearOptions(categoryId2);
		clearOptions(categoryId3);
		$("#brands").text("");
		initCat2List(proId);
		});
}

/*品类二级分类*/
function initCat2List(proId){
	var categoryId2List;
	$.ajax({ 
        type: "post", 
        url : "/industryBrand/ajaxcategoryId2",
        dataType:'json',
        data: "areaId="+proId,
        success: function(json){
//        	categoryId2List = eval("("+json+")");  
        	categoryId2List = json;
        	
        	$("#categoryId2").html("");
        	$("#categoryId2").append("<option value='0'>请选择</option>");
        	for(i=0; i< categoryId2List.length;i++){
        	$("#categoryId2").append("<option value="+categoryId2List[i].id+">"+categoryId2List[i].name+"</option>");
        	}
        }
    });
}

/*品类三级分类*/
function initCat3List(proId){
	var categoryId3List;
	
	$.ajax({ 
        type: "post", 
        url : "/industryBrand/ajaxcategoryId2",
        dataType:'json',
        data: "areaId="+proId,
        success: function(json){
//        	categoryId3List = eval("("+json+")");  
        	categoryId3List = json;
        	
        	$("#categoryId3").html("");
        	$("#categoryId3").append("<option value='0'>请选择</option>");
        	for(i=0; i< categoryId3List.length;i++){
        	$("#categoryId3").append("<option value="+categoryId3List[i].id+">"+categoryId3List[i].name+"</option>");
        	}
        	
        }
    });
}

//清空
function clearOptions(obj) {
	obj.options.length = 0;
	obj.options.add(new Option("请选择", ""));
	}
//选定
function g(id){
	return document.getElementById(id);
	}

//转向到合同信息添加页
function createContract(){
	window.location.href="/contractinfo/addcontractinfoinit";
}

//转向到合同业务审核列表页面
function businessRedirectToList(){
	window.location.href="/contractinfo/businesscontractlist";
}

//转向到合同信控审核列表页面
function creditRedirectToList(){
	window.location.href="/contractinfo/creditcontractlist";
}

//转向到合同财务审核列表页面
function financialRedirectToList(){
	window.location.href="/contractinfo/financialcontractlist";
}

//去除空格
function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g,"");
}

//去除空格
String.prototype.trim = function(){
	return this.replace(/(^\s*)|(\s*$)/g,"");
};
function submitOk(){
	var categoryId1 = $("#categoryId1").find("option:selected").attr("value");
	var categoryId2 = $("#categoryId2").find("option:selected").attr("value");
	var categoryId3 = $("#categoryId3").find("option:selected").attr("value");
	var prompt ="查询成功";
	 var url = "/industryBrand/selectByCategory?categoryId1="+categoryId1+"&categoryId2="+categoryId2+"&categoryId3="+categoryId3;
		var data = $('#catCountForm').serialize();;
		if(confirm("确定根据分类id查询?")){
			ajaxSubmit(url,data,prompt);
		};
}