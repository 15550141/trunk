$.extend(validatePrompt, {
    username:{
        onFocus:"",
        succeed:"",
        isNull:"请输入用户名/邮箱/已验证手机",
        error:"不存在此用户名"
    }
});
$.extend(validateFunction, {
    username:function (option) {
        validateSettings.succeed.run(option);
    },
    pwd:function (option) {
        validateSettings.succeed.run(option);
    },

    FORM_validate:function () {
        $("#loginname").jdValidate(validatePrompt.username, validateFunction.username, true);
        $("#loginpwd").jdValidate(validatePrompt.pwd, validateFunction.pwd, true);
        return validateFunction.FORM_submit(["#loginname", "#loginpwd"]);
    }
});
setTimeout(function () {
    if (!$("#loginname").val()) {
        $("#loginname").get(0).focus();
    }
}, 0);

$("#loginname").jdValidate(validatePrompt.username, validateFunction.username);
$("#loginpwd").jdValidate(validatePrompt.empty, validateFunction.pwd);
$("#loginname,#loginpwd").bind('keyup', function (event) {
    if (event.keyCode == 13) {
        $("#loginsubmit").click();
    }
});
$("#loginsubmit").click(function () {
	//$("#loginpwd").val($.md5($("#loginpwd").val()));
    var loginUrl = "/onLogin";
    var flag = loginNameOk() && validateFunction.FORM_validate();
    if (flag) {
        $(this).attr({ "disabled":"disabled" });
        $.ajax({
            type:"POST",
           // url:loginUrl + location.search + "&r=" + Math.random(),
            url:loginUrl, 
            //contentType:"application/x-www-form-urlencoded; charset=utf-8",
            type:"post",
            data:$("#formlogin").serialize(),
            dataType:"json",
            error:function () {
                $("#loginpwd").attr({ "class":"text highlight2" });
                $("#loginpwd_error").html("网络超时，请稍后再试").show().attr({ "class":"error" });
                $("#loginsubmit").removeAttr("disabled");
                $(this).removeAttr("disabled");
            },
            success:function (result) {
            	//alert("result=="+result);
            	//alert("result.success=="+result.success);
                if (result) {
                    if (result.success == true) {
                    	//alert("登录成功！！");
                    	window.location.href = result.url;
                        return;
                    }
                    $("#loginsubmit").removeAttr("disabled");
                    $(this).removeAttr("disabled");
                    
                    $("#loginname").attr({ "class":"text highlight2" });
                    $("#loginpwd_error").html(result.message).show().attr({ "class":"error" });
                }else{
                	$("#loginsubmit").removeAttr("disabled");
                	$(this).removeAttr("disabled");
                }
            }
        });
    }
});

$("#signsubmit").click(function () {
	window.location.href = "/sign";
});
$("#onSignSubmit").click(function () {
	//alert("进入注册方法！");
	//$("#loginpwdSign").val($.md5($("#loginpwdSign").val()));
	//$("#loginpwdSign2").val($.md5($("#loginpwdSign2").val())); 
	
	var mobile=$("#mobile").val();
	if(mobile.length==0){
		alert("手机号不能为空！");
		return;
	}
	var txNum = /^[0-9]+$/;
	if (!txNum.test(mobile)) {
		alert("手机号码不对！");
		return;
	}
	var shopName=$("#shopName").val();
	if(shopName.length==0){
		alert("店铺名称不能为空！");
		return;
	}
	var loginpwdSign=$("#loginpwdSign").val();
	if(loginpwdSign.length==0){
		alert("密码不能为空！");
		return;
	}
	var loginpwdSign2=$("#loginpwdSign2").val();
	if(loginpwdSign2.length==0){
		alert("第二次输入密码不能为空！");
		return;
	}
	
	var verificationCode=$("#verificationCode").val();
	if(verificationCode.length==0){
		alert("验证码不能为空！");
		return;
	}
	if(document.getElementById("agree").checked==false){
		alert("请阅读并同意用户注册协议！");
		return;
		
	}

    var loginUrl = "/onSign";
    $(this).attr({ "disabled":"disabled" });
    $.ajax({
        //type:"POST",
       // url:loginUrl + location.search + "&r=" + Math.random(),
        url:loginUrl, 
        //contentType:"application/x-www-form-urlencoded; charset=utf-8",
        type:"post",
        data:$("#formsign").serialize(),
        dataType:"json",
        error:function () {
            $("#loginpwd2Sign").attr({ "class":"text highlight2" });
            $("#loginpwd2Sign_error").html("网络超时，请稍后再试").show().attr({ "class":"error" });
            $("#onSignSubmit").removeAttr("disabled");
            $(this).removeAttr("disabled");
        },
        success:function (result) {
        	alert("注册成功！！");
        	//alert("result=="+result);
        	//alert("result.success=="+result.success);
            if (result) {
                if (result.success == true) {
                	//alert("成功！！");
                	window.location.href = result.url;
                    return;
                }
                $("#onSignSubmit").removeAttr("disabled");
                $(this).removeAttr("disabled");
                
                $("#loginpwd2Sign").attr({ "class":"text highlight2" });
                $("#loginpwd2Sign_error").html(result.message).show().attr({ "class":"error" });
            }else{
            	$("#onSignSubmit").removeAttr("disabled");
            	$(this).removeAttr("disabled");
            }
        }
    });


});



$("#resetpswSubmit").click(function () {
	//$("#newpassword").val($.md5($("#newpassword").val()));
	//$("#newpassword2").val($.md5($("#newpassword2").val())); 
    var loginUrl = "/onResetPwd";
        $(this).attr({ "disabled":"disabled" });
        $.ajax({
            type:"POST",
           // url:loginUrl + location.search + "&r=" + Math.random(),
            url:loginUrl, 
            //contentType:"application/x-www-form-urlencoded; charset=utf-8",
            type:"post",
            data:$("#formresetPwd").serialize(),
            dataType:"json",
            error:function () {
                $("#newpassword").attr({ "class":"text highlight2" });
                $("#newpassword2_error").html("网络超时，请稍后再试").show().attr({ "class":"error" });
                $("#resetpswSubmit").removeAttr("disabled");
                $(this).removeAttr("disabled");
            },
            success:function (result) {
            	alert("重置密码成功！！");
            	//alert("result=="+result);
            	//alert("result.success=="+result.success);
                if (result) {
                    if (result.success == true) {
                    	//alert("成功！！");
                    	window.location.href = result.url;
                        return;
                    }
                    $("#resetpswSubmit").removeAttr("disabled");
                    $(this).removeAttr("disabled");
                    
                    $("#newpassword").attr({ "class":"text highlight2" });
                    $("#newpassword2_error").html(result.message).show().attr({ "class":"error" });
                }else{
                	$("#resetpswSubmit").removeAttr("disabled");
                	$(this).removeAttr("disabled");
                }
            }
        });
});

$("#getSignSMSCode").click(function () {	

	if($("#mobile").val() == '') {
		alert("手机号不能为空！");
		return false;
	}
	
	
		$.ajax( {
			url : "/getSignSMSCode",
			type : "post",
			dataType : "json",
			data : "mobile="+$("#mobile").val(),
			success : function(data) {
				if (data.msg == "success") {
					alert("获取短信验证码成功！");
					
				} else {
					if(data.reason)
						alert("获取短信验证码失败！原因：" + data.reason);
					else
						alert("获取短信验证码失败！");
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("系统超时！无法获取短信验证码！");
			}
	});
});

function loginNameOk() {
    var loginName = $("#loginname").val();
    if (validateRules.isNull(loginName) || loginName == '用户名/邮箱/已验证手机') {
        $("#loginname").attr({ "class":"text highlight2" });
        $("#loginname_error").html("请输入用户名/邮箱/已验证手机").show().attr({ "class":"error" });
        return false;
    }

    return true;
}