function funAddparent(obj){
	var contractNumberVal=$("#parentPinValue").val();
	var proId =obj;
	if(proId==2){
		$("#motherTypename").text("关联母账号:");
		$("#motherType").replaceWith("<input id='parentPin' name='parentPin' value='"+contractNumberVal+"'/>");
	}else{
		$("#motherTypename").text("");
		$("#test").text("");
		$("#test").html("<span id='motherType'></span>");
	}
}
function funAdd(obj){
	var contractNumberVal=$("#contractNumbervalue").val();
	var proId =obj.value;
	if(proId==2){
		$("#motherTypename").text("关联母账号:");
		$("#motherType").replaceWith("<input id='parentPin' name='parentPin' />");
		$("#contractNumber1").text("");
		$("#test1").text("");
		$("#test1").html("<span id='contractNumber'></span>");
	}else{
		$("#contractNumber1").text("合同号:");
		$("#contractNumber").html("<input id='contractNumber' name='contractNumber'  onblur='queryconNum(this)'/>");
		$("#motherTypename").text("");
		$("#test").text("");
		$("#test").html("<span id='motherType'></span>");
	}
}

/*查询合同号是否存在*/
function queryconNumClick(){
	var contractNumber=$("contractNumber");
	var url="/EnterpriseUser/checkContractNumber";
	var data="contractNumber="+contractNumber;
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
			if (!result.success) {
				alert("合同号不存在，请重新输入");
			} 
				
		}
	});
}
/*修改密码时候检查pin是否已经在用户组存在*/
function checkPin(){
	var pin=$("#pin").val();
	var url="/EnterpriseUser/checkPinOnlyFor";
	var data="pin="+pin;
	if(pin==""){
		alert("企业账号不能为空,请输入!");
		$("#pin").val("");
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
			if (!result.success) {
				alert("用户账号不存在，请重新输入!");
				$("#pin").val("");	
			}
				
		}
	});
}
/*创建企业账号时候检查pin是否已经在用户组存在*/
function AddCheckPinOnly(obj){
	var pin=$(obj).val();
	var url="/EnterpriseUser/checkPinOnly";
	var data="pin="+pin;
	if(pin==""){
		alert("企业账号不能为空,请输入!");
		$("#pin").val("");
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
			if (!result.success) {
				alert("用户账号已存在，请重新输入!");
				$("#pin").val("");	
			}
				
		}
	});
}
/*挂载企业账号时候检查pin是否已经在用户组存在*/
function mountCheckPinOnly(obj){
	var pin=$(obj).val();
	var url="/EnterpriseUser/checkPinOnlyFor";
	var data="pin="+pin;
	if(pin==""){
		alert("企业账号不能为空,请输入!");
		$("#pin").val("");
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
			if (!result.success) {
				alert("用户账号不存在，请重新输入!");
				$("#pin").val("");	
			}
				
		}
	});
}
/*挂载企业账号功能*/
function mount(){
	var url="/EnterpriseUser/mountTosaf";
	var data = $('#mountEnterpriseUserId').serialize();
	var prompt = "恭喜您，挂载成功！";
	
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
				window.location.href="/EnterpriseUser/list";
			} else {
				alert("挂载出错，请检查挂载信息是否正确");
			}
				
		}
	});
}
/*创建企业账号时候检查pin是否已经在用户组存在*/
function checkPinOnly(obj){
	var pin=$(obj).val();
	var url="/EnterpriseUser/checkPinOnly";
	var data="pin="+pin;
	if(pin==""){
		alert("企业账号不能为空,请输入!");
		$("#pin").val("");
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
			if (!result.success) {
				alert(result.message);
				$("#pin").val("");	
			}
				
		}
	});
}
/*创建企业账号功能*/
function creatEnterpriseUser(){
	var url="/EnterpriseUser/creat";
	var data = $('#addenterpriseUserid').serialize();
	var prompt = "恭喜您，创建成功！";
	var message = "创建失败，请检查子母账号标记和企业名称信息是否存在！";
	
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
				window.location.href="/EnterpriseUser/list";
			} else {
				alert(result.message);
			}
				
		}
	});
}
/*检查合同号是否存在*/
function queryconNum(obj){
	var contractNumber=$(obj).val();
	var url="/EnterpriseUser/checkContractNumber";
	var data="contractNumber="+contractNumber;
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
				
				//queryconName(contractNumber);
			} 
			else if(result.message){
			alert(result.message);
			$("#operator").val("");				
			$("#comName").val("");}
			else {
			alert("合同号不能为空");
			}
		}	
	});
}

function queryconName(contractNumber){
	var url="/EnterpriseUser/checkContractNumber";
	
	var data="contractNumber="+contractNumber;
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
				$("#operator").val(result.saler);				
				$("#comName").val(result.enterpriseName);
			} 
			else {
				alert(result.message);
				$("#operator").val("");				
				$("#comName").val("");
			}
				
		}
	});
}