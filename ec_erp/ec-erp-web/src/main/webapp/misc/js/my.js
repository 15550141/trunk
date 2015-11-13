function updatelinkmanInfo(){
	window.alert("添加成功");
	window.location.href="/InvoiceReceiverinfo/search";
}
function delInfo(invoiceReceiverId){
	if(!window.confirm("确定要删除这条增票收票人记录吗？")){
		return false;
	}
	window.location.href="/InvoiceReceiverinfo/delete?invoiceReceiverId="+invoiceReceiverId;
}
function dellinkmanInfo(linkmanId){
	if(!window.confirm("确定要删除这条联系人记录吗？")){
		return false;
	}
	window.location.href="/linkmaninfo/delete?linkmanId="+linkmanId;
}
function delInvoInfo(consigneeId){
	if(!window.confirm("确定要删除这条收货人记录吗？")){
		return false;
	}
	window.location.href="/ConsigneeInfo/delete?consigneeId="+consigneeId;
}
function returnindex(){
	
	window.location.href="/InvoiceReceiverinfo/search";
}

function returnInvoindex(){
	
	window.location.href="/ConsigneeInfo/index";
}
function addInvoindex(){
	var pin =$("#pin").val();
	window.location.href="/ConsigneeInfo/add?pin="+pin;
}
function addlinkmaninfo(){
	var pin =$("#pin").val();
	window.location.href="/linkmaninfo/addlinkmaninfo?pin="+pin;
}
function addInvoiceReceInfo(){
	var pin =$("#pin").val();
	window.location.href="/InvoiceReceiverinfo/add?pin="+pin;
}
/*修改企业账号密码按钮链接*/
function modifyEnterpriseUserPassword(){
	
	window.location.href="/EnterpriseUser/modifyPassword";
}
/*创建企业账号按钮链接*/
function createEnterpriseUser(){
	
	window.location.href="/EnterpriseUser/add";
}
function returnIndex(){
	
	window.location.href="/EnterpriseUser/list";
}
/*挂载企业账号*/
function mountEnterpriseUser(){
	
	window.location.href="/EnterpriseUser/mount";
}
//新增联系人信息
function addLinkmanSubmit(){
	var url = "/linkmaninfo/add";
	var data = $('#addlinkmanid').serialize();
	var prompt = "恭喜您，新增联系人成功！";
		var flag = confirm("您确认要新增联系人吗？");
		if(flag==true){
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
	}

//增票收票人审核
function invoiceReceiverAuditSubmit(){
	var url = "auditInvoiceReceiver";
	var data = $('#invoiceReceiverAuditId').serialize();
	var prompt = "恭喜您，审核成功！";
	var flag = confirm("您确认要审核增票收票人信息吗？");
	var status = document.getElementsByName("status");
	if(status[0].checked || status[1].checked){
		if(flag==true){
			ajaxSubmit(url,data,prompt);
		}
	}
	else
		alert("请选择审批结果后，再进行提交");
}
//修改收票人信息
function invoiceReceiverInfoSubmit(){
	var url = "updateInvoiceReceiverInfo";
	var data = $('#updateInvoiceReceiverInfoId').serialize();
	var prompt = "恭喜您，修改成功！";
	var flag = confirm("您确认要修改增票收票人信息吗？");
		if(flag==true){
			ajaxSubmit(url,data,prompt);
		}
}
/*修改企业账号登录密码*/
function modifyPassword(){
//	var pin=$("#pin").val();
//	var newpwd=$("#newpwd").val();
	var url = "modifyPswTosaf";
	var data = $('#modifyPswfoid').serialize();
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
				var flag = result.success;
				if(flag) {
					alert("恭喜您，密码修改成功！");					
				} else {
					alert("修改密码失败！");
				}
			}
		});
	
}
/*创建增票收票人功能*/
function creat(){
	var url="/InvoiceReceiverinfo/addInvoiceReceiverinfo";
	var data = $('#addInvoiceReceiverinfoId').serialize();
	var prompt = "恭喜您，创建成功！";
	var province=$("#invoiceReceiverProvince").val();
	var city=$("#invoiceReceiverCity").val();
	var county=$("#invoiceReceiverCounty").val();
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
		error : function(XMLHttpRequest, textStatus, errorThrow) {
			 alert("网络繁忙，请稍后再试");
		},
		success : function(result) {
			if (result.success) {
				alert(prompt);
				window.location.href="/InvoiceReceiverinfo/search";
			} else {
				alert("创建失败，请检查信息是否完整");
			}
		}
	});
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
window.onload=function(){
	onInvoprovinceChange();
	
};
/*增票收票人所在省*/
function onInvoprovinceChange(){
	var proSelection=$("#invoiceReceiverProvince");
	proSelection.bind("change", function(){
		var proId = proSelection.find("option:selected").val();
		var city = g("invoiceReceiverCity");
		var county = g("invoiceReceiverCounty");
		var town = g("invoiceReceiverTown");
		clearOptions(city);
		clearOptions(county);
		clearOptions(town);
		initInvoCity(proId);
		});


}

/*增票收票人所在市*/
function initInvoCity(proId){
	var cityList;
	$.ajax({ 
        type: "post", 
        url : "/ajaxcity",
        dataType:'json',
        data: "areaId="+proId,
        success: function(json){
        	cityList = eval("("+json+")");  
        	
        	$("#invoiceReceiverCity").html("");
        	$("#invoiceReceiverCity").append("<option value='0'>请选择</option>");
        	for(i=0; i< cityList.length;i++){
        	$("#invoiceReceiverCity").append("<option value="+cityList[i].id+">"+cityList[i].name+"</option>");
        	}
        	var proSelection=$("#invoiceReceiverCity");
        	
        	proSelection.bind("change", function(){
        		
        		var proId = proSelection.find("option:selected").val();
        		var county = g("invoiceReceiverCounty");
            	var town = g("invoiceReceiverTown");
            	clearOptions(county);
            	clearOptions(town);
        		
        		initInvoCounty(proId);
        		});
        }
    });
}

/*增票收票人所在县*/
function initInvoCounty(proId){
	var cityList;
	
	$.ajax({ 
        type: "post", 
        url : "/ajaxcounty",
        dataType:'json',
        data: "areaId="+proId,
        success: function(json){
        	cityList = eval("("+json+")");  
        	
        	$("#invoiceReceiverCounty").html("");
        	$("#invoiceReceiverCounty").append("<option value='0'>请选择</option>");
        	for(i=0; i< cityList.length;i++){
        	$("#invoiceReceiverCounty").append("<option value="+cityList[i].id+">"+cityList[i].name+"</option>");
        	}
        	var proSelection=$("#invoiceReceiverCounty");
        	
        	proSelection.bind("change", function(){
        		
        		var proId = proSelection.find("option:selected").val();
        		var town = g("invoiceReceiverTown");
            	clearOptions(town);
        		initInvoTown(proId);
        		});
        }
    });
}
/*增票收票人所在乡镇*/
function initInvoTown(proId){
	var cityList;
	
	$.ajax({ 
        type: "post", 
        url : "/ajaxtown",
        dataType:'json',
        data: "areaId="+proId,
        success: function(json){
        	cityList = eval("("+json+")");  
        	$("#invoiceReceiverTown").html("");
        	$("#invoiceReceiverTown").append("<option value='0'>请选择</option>");
        	if(cityList.length>0){
        	for(i=0; i< cityList.length;i++){
        	$("#invoiceReceiverTown").append("<option value="+cityList[i].id+">"+cityList[i].name+"</option>");
        	}
        }
        	else{
        		$("#invoiceReceiverTown").append("<option value='0'>无</option>");
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
