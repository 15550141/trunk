window.onload=function(){	
	onConprovinceChange();
};

//修改审核状态
function consigneeInfoAuditSubmit(){
	var url = "auditConsigneeInfo";
	var data = $('#auditConsigneeInfoId').serialize();;
	var prompt = "恭喜您，审核成功！";
	var flag = confirm("您确认要修改审核状态吗？");
	var status = document.getElementsByName("status");
	if(status[0].checked || status[1].checked){
		if(flag==true){
			ajaxSubmit(url,data,prompt);
		}
	}
	else
		alert("请选择审批结果后，再进行提交");
}
//修改收货人基本信息
function updateConsigneeInfoSubmit(){
	var url = "updateConsigneeInfo";
	var data = $('#updateConsigneeInfoId').serialize();;
	var prompt = "恭喜您，修改成功！";
	var flag = confirm("您确认要修改收货人信息吗？");
		if(flag==true){
			ajaxSubmit(url,data,prompt);
		}
}
/*创建收货人功能*/
function creat(){
	var url="addConsigneeInfo";
	var data = $('#addconsigneeInfoId').serialize();
	var prompt = "恭喜您，创建成功！";
	var province=$("#consigneeProvince").val();
	var city=$("#consigneeCity").val();
	var county=$("#consigneeCounty").val();
	if(province==0 || city==0 || county==0){
		alert("地址信息不全，请补全选择地址信息！");
		return false;
	}
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
				window.location.href="/ConsigneeInfo/index";
			} else {
				alert("创建失败，请检查信息是否完整");
			}
		}
	});
}
/*收货人所在省*/
function onConprovinceChange(){
	var proSelection=$("#consigneeProvince");
	
	proSelection.bind("change", function(){
		
		var proId = proSelection.find("option:selected").val();
		var city = g("consigneeCity");
		var county = g("consigneeCounty");
		var town = g("consigneeTown");
//		clearOptions(province) ;
		clearOptions(city);
		clearOptions(county);
		clearOptions(town);
		initConCity(proId);
		});
}

/*收货人所在市*/
function initConCity(proId){
	var cityList;
	$.ajax({ 
        type: "post", 
        url : "/ajaxcity",
        dataType:'json',
        data: "areaId="+proId,
        success: function(json){
        	cityList = eval("("+json+")");  
        	
        	$("#consigneeCity").html("");
        	$("#consigneeCity").append("<option value='0'>请选择</option>");
        	for(i=0; i< cityList.length;i++){
        	$("#consigneeCity").append("<option value="+cityList[i].id+">"+cityList[i].name+"</option>");
        	}
        	var proSelection=$("#consigneeCity");
        	proSelection.bind("change", function(){
        		
        		var proId = proSelection.find("option:selected").val();
        		var county = g("consigneeCounty");
        		var town = g("consigneeTown");
        		clearOptions(county);
        		clearOptions(town);
        		initConCounty(proId);
        		});
        }
    });
}

/*收货人所在县*/
function initConCounty(proId){
	var cityList;
	
	$.ajax({ 
        type: "post", 
        url : "/ajaxcounty",
        dataType:'json',
        data: "areaId="+proId,
        success: function(json){
        	cityList = eval("("+json+")");  
        	
        	$("#consigneeCounty").html("");
        	$("#consigneeCounty").append("<option value='0'>请选择</option>");
        	for(i=0; i< cityList.length;i++){
        	$("#consigneeCounty").append("<option value="+cityList[i].id+">"+cityList[i].name+"</option>");
        	}
        	var proSelection=$("#consigneeCounty");
        	proSelection.bind("change", function(){
        		
        		var proId = proSelection.find("option:selected").val();
        		var town = g("consigneeTown");
        		clearOptions(town);
        		initConTown(proId);
        		});
        }
    });
}
/*收货人所在乡镇*/
function initConTown(proId){
	var cityList;
	
	$.ajax({ 
        type: "post", 
        url : "/ajaxtown",
        dataType:'json',
        data: "areaId="+proId,
        success: function(json){
        	cityList = eval("("+json+")");  
        	
        	$("#consigneeTown").html("");
        	$("#consigneeTown").append("<option value='0'>请选择</option>");
        	for(i=0; i< cityList.length;i++){
        	$("#consigneeTown").append("<option value="+cityList[i].id+">"+cityList[i].name+"</option>");
        	}
        	
        }
    });
}
function clearOptions(obj) {
	obj.options.length = 0;
	obj.options.add(new Option("请选择", ""));
	}
function g(id){
	return document.getElementById(id);
	}
//ajax提交
function ajaxSubmit(url,data,prompt){
	$.ajax({
		type : "POST",
		url : url,
		data : data,
		cache:false,
		dataType : "json",
		error : function(XMLHttpRequest, textStatus, errorThrow) {
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