//修改企业基本信息
function basicModifySubmit(){
	var url = "modifyenterpriseinfo";
	var data = $('#modifyenterpriseinfobasic').serialize();
	var prompt = "恭喜您，企业基本信息修改成功！";
	var flag = confirm("您确认要修改企业基本信息吗？");
	if(flag==true){
		ajaxSubmit(url,data,prompt);
	}
}
//修改企业有效性信息
function ynModifySubmit(){
	var url = "modifyenterpriseyn";
	var data = $('#modifyenterpriseyn').serialize();
	var prompt = "恭喜您，企业有效性信息修改成功！";
	var flag = confirm("您确认要修改企业有效性信息吗？");
	if(flag==true){
		ajaxSubmit(url,data,prompt);
	}
}
//修改开票信息
function invoiceModifySubmit(){
	var url = "modifyenterpriseinfo";
	var data = $('#modifyenterpriseinfoinvoice').serialize();
	var prompt = "恭喜您，开票信息修改成功！";
	var flag = confirm("您确认要修改开票信息吗？");
	if(flag==true){
		ajaxSubmit(url,data,prompt);
	}
}

//修改银行信息
function bankModifySubmit(){
	var url = "modifyenterpriseinfo";
	var data = $('#modifyenterpriseinfobank').serialize();
	var prompt = "恭喜您，银行账户信息修改成功！";
	var flag = confirm("您确认要修改银行账户信息吗？");
	if(flag==true){
		ajaxSubmit(url,data,prompt);
	}
}

//修改企业个人信息
function personModifySubmit(){
	var url = "modifyenterpriseinfo";
	var data = $('#modifyenterpriseforperson').serialize();
	var prompt = "恭喜您，个人信息修改成功！";
	var flag = confirm("您确认要修改个人信息吗？");
	if(flag==true){
		ajaxSubmit(url,data,prompt);
	}
}
//修改企业个人有效性信息
function personYnModifySubmit(){
	var url = "modifyenterpriseinfo";
	var data = $('#modifypersonyn').serialize();
	var prompt = "恭喜您，个人信息有效性修改成功！";
	var flag = confirm("您确认要修改个人信息有效性吗？");
	if(flag==true){
		ajaxSubmit(url,data,prompt);
	}
}
//添加企业信息
function addEnterpriseInfo(){
	//var enterpriseFlag = $("#enterpriseFlag").val();
	var enterpriseFlag = document.getElementsByName("enterpriseFlag");
	if(enterpriseFlag[3].checked || enterpriseFlag[2].checked){
		alert("企业标记不合法，请重新选择");
	}
	if(enterpriseFlag[0].checked||enterpriseFlag[1].checked ){
		var enterpriseName = $("#enterpriseName").val();
		var credentialsNumber = $("#credentialsNumber").val();
		var organizationCode = $("#organizationCode").val();
		var corporationNumber = $("#corporationNumber").val();
		if(enterpriseName == "" || credentialsNumber == "" || organizationCode == "" || corporationNumber == ""){
			alert("基本信息中有些必填字段为空");
		}else{
			var url = "addenterpriseinfo";
			var data = $('#addenterpriseinfoid').serialize();;
			var prompt = "恭喜您，添加企业信息成功！";
			var flag = confirm("您确认要创建企业信息吗？");
			if(flag==true){
//				ajaxSubmit(url,data,prompt);
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
							var enterpriseInfoObj = result.enterpriseInfo;
							alert(prompt);
							window.location.href="/enterpriseinfo/modifyenterpriseinit?enterpriseNumber="+enterpriseInfoObj.enterpriseNumber;
						} else {
							
							alert(result.message);
						}
					}
				});
			}
		}
	}
}

//添加企业中的个人信息
function addEnterprisePerson(){
	var enterpriseFlag = document.getElementsByName("enterpriseFlag");
	if(enterpriseFlag[0].checked||enterpriseFlag[1].checked){
		alert("企业标记不合法，请重新选择");
	}
	if(enterpriseFlag[3].checked || enterpriseFlag[2].checked){
		var idcard = $("#personalIdentityCard").val();
		if(idcard == ""){
			$("#personalIdentityCard").attr("style","border:1px solid red");
			$("#personalIdentityCard").focus();
		}else{
			var url = "addenterpriseinfo";
			var data = $('#addenterprisepersonid').serialize();;
			var prompt = "恭喜您，添加个人信息成功！";
			var flag = confirm("您确认要创建个人信息吗？");
			if(flag==true){
//				ajaxSubmit(url,data,prompt);
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
							var enterpriseInfoObj = result.enterpriseInfo;
							alert(prompt);
							window.location.href="/enterpriseinfo/modifyenterpriseinit?enterpriseNumber="+enterpriseInfoObj.enterpriseNumber;
						} else {
							alert(result.message);
						}
					}
				});
			}
		}
	}
}

//企业基本信息审核
function basicAuditSubmit(){
	var url = "basicenterpriseaudit";
	var data = $('#basicenterpriseauditid').serialize();;
	var prompt = "恭喜您，企业基本信息审核成功！";
	var flag = confirm("您确认要审核企业基本信息吗？");
	var enterpriseInfoStatus = document.getElementsByName("enterpriseInfoStatus");
	if(enterpriseInfoStatus[0].checked || enterpriseInfoStatus[1].checked){
		if(flag==true){
			ajaxSubmit(url,data,prompt);
		}
	}
	else
		alert("请选择审批结果后，再进行提交");
}

//银行资料审核
function bankAuditSubmit(){
	var url = "bankenterpriseaudit";
	var data = $('#bankenterpriseauditid').serialize();;
	var prompt = "恭喜您，银行资料审核成功！";
	var flag = confirm("您确认要审核银行资料信息吗？");
	var bankInfoStatus = document.getElementsByName("bankInfoStatus");
	if(bankInfoStatus[0].checked || bankInfoStatus[1].checked){
		if(flag==true){
		ajaxSubmit(url,data,prompt);
		}
	}
	else
		alert("请选择审批结果后，再进行提交");
}

//增票资料审核
function invoiceAuditSubmit(){
	var url = "invoiceenterpriseaudit";
	var data = $('#invoiceenterpriseauditid').serialize();;
	var prompt = "恭喜您，增票资料审核成功！";
	var flag = confirm("您确认要审核增票资料信息吗？");
	var invoiceInfoStatus = document.getElementsByName("invoiceInfoStatus");
	if(invoiceInfoStatus[0].checked || invoiceInfoStatus[1].checked){
		if(flag==true){
		ajaxSubmit(url,data,prompt);
		}
	}
	else
		alert("请选择审批结果后，再进行提交");
}

//个人资料审核
function personAuditSubmit(){
	var url = "personenterpriseaudit";
	var data = $('#personenterpriseauditid').serialize();;
	var prompt = "恭喜您，个人资料审核成功！";
	var flag = confirm("您确认要审核个人资料信息吗？");
	var personalStatus = document.getElementsByName("personalStatus");
	if(personalStatus[0].checked || personalStatus[1].checked){
		if(flag==true){
			ajaxSubmit(url,data,prompt);
		}
	}
	else
		alert("请选择审批结果后，再进行提交");
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

/*//翻页中的上一页
function previousPage(url,page,formid){
	$("#viewenterpriseinfolistid").action="/enterpriseinfo/viewenterpriseinfolist?page="+page;
	$("#viewenterpriseinfolistid").submit();
	$("#"+formid).attr("action",url+"?cutPage="+page);
	$("#"+formid).submit();
}

//翻页
function turnPage(url,page,formid){
	$("#viewenterpriseinfolistid").action="/enterpriseinfo/viewenterpriseinfolist?page="+page;
	$("#viewenterpriseinfolistid").submit();
	$("#"+formid).attr("action",url+"?cutPage="+page);
	$("#"+formid).submit();
}

//翻页中的下一页
function nextPage(url,page,formid){
	$("#viewenterpriseinfolistid").action="/enterpriseinfo/viewenterpriseinfolist?page="+page;
	$("#viewenterpriseinfolistid").submit();
	$("#"+formid).attr("action",url+"?cutPage="+page);
	$("#"+formid).submit();
}*/

//转向到个人信息添加页
function redirectToPerson(enterpriseFlag){
//	window.location.href="http://www.baidu.com";
	window.location.href="/enterpriseinfo/addenterprisepersoninit?enterpriseFlag="+enterpriseFlag;
}

//转向到企业信息添加页
function redirectToEnterprise(enterpriseFlag){
	window.location.href="/enterpriseinfo/addenterpriseinit?enterpriseFlag="+enterpriseFlag;
}

//转向到企业信息添加页
function createEnterprise(){
	window.location.href="/enterpriseinfo/addenterpriseinit";
}

//转向到企业基本资料审核列表
function basicRedirectToList(){
	window.location.href="/enterpriseinfo/basicenterpriseinfolist";
}

//转向到银行资料审核列表
function bankRedirectToList(){
	window.location.href="/enterpriseinfo/bankenterpriseinfolist";
}

//转向到增票资料列表
function invoiceRedirectToList(){
	window.location.href="/enterpriseinfo/invoiceenterpriseinfolist";
}

//转向到个人资料列表
function personRedirectToList(){
	window.location.href="/enterpriseinfo/personenterpriseinfolist";
}