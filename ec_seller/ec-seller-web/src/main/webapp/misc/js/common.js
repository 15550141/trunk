//去除空格
function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g,"");
}

//去除空格
String.prototype.trim = function(){
	return this.replace(/(^\s*)|(\s*$)/g,"");
};

//翻页中的上一页
/**
 * @param url 地址
 * @param page 页码
 * @param formid 表单id
 */
function previousPage(url,page,formid){
	$("#"+formid).attr("action",url+"?cutPage="+page);
	$("#"+formid).submit();
}

//翻页
/**
 * @param url 地址
 * @param page 页码
 * @param formid 表单id
 */
function turnPage(url,page,formid){
	$("#"+formid).attr("action",url+"?cutPage="+page);
	$("#"+formid).submit();
}

//翻页中的下一页
/**
 * @param url 地址
 * @param page 页码
 * @param formid 表单id
 */
function nextPage(url,page,formid){
	$("#"+formid).attr("action",url+"?cutPage="+page);
	$("#"+formid).submit();
}

/**跳转到指定的页面
 * @param url 地址
 */
function redirectToPage(url){
	var URL = url.trim();
	if(URL != ""){
		window.location.href=URL;
	}
}

/**跳转到指定的页面带参数
 * @param url 地址
 * @param data 参数 格式为："variable=value&variable=value" 多个变量中间用‘&’分隔
 */
function redirectToPage(url,data){
	var URL = url.trim();
	var DATA = data.trim();
	if(URL != ""){
		if(DATA != ""){
			window.location.href=URL+"?"+data;
		}else{
			window.location.href=URL;
		}
	}
}
/**
 * 验证服务费率
 * @param obj
 */
function validationServerRate(obj){
	var email = ($("#serviceRate").val()).trim();
	if(email != ""){
	var _email =/^\d+$/;
	if(! _email.test(email)){
		alert("请录入大于等于零的数字");
			$(obj).val("");
			document.getElementById("serviceRate").focus();
			}
	else{
			$(obj).removeAttr("style");
			}
			}
		}
/**验证邮箱是否合法
 * @param id 输入框id
 */
function validationEmail(obj){
	var email = ($(obj).val()).trim();
	if(email != ""){
		var _email = /^[A-Za-z0-9]+(([_]+)*[A-Za-z0-9]+)*@([A-Za-z0-9]+[.])+[A-Za-z0-9]{2,5}$/;
		if(! _email.test(email)){
			$(obj).attr("style","border:1px solid red");
			$(obj).val("");
		}else{
			$(obj).removeAttr("style");
		}
	}
}

/**验证输入的身份证号码是否合法
 * @param id  输入框ID
 */
function validationIdNumber(obj){
//	$(obj).focus();
	var number = ($(obj).val()).trim();
	if(number != ""){
		var _number =/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		if(! _number.test(number)){
			$(obj).attr("style","border:1px solid red");
			$(obj).val("");
		}else{
			$(obj).removeAttr("style");
		}
	}
}
/**验证输入的数字是否合法
 * @param id  输入框ID
 */
function validationNumber(obj){
//	$(obj).focus();
	var number = ($(obj).val()).trim();
	if(number != ""){
		var _number = /^\d+$/;
		if(! _number.test(number)){
			$(obj).attr("style","border:1px solid red");
			$(obj).val("");
		}else{
			$(obj).removeAttr("style");
		}
	}
}

/**验证电话
 * @param id
 */
function validationPhone(obj){
	var phone = ($(obj).val()).trim();
	if(phone != ""){
		var _phone = /^(\(\d{2,4}\)|\d{2,4}-|\s)?\d{7,11}$/;
		if(! _phone.test(phone)){
			$(obj).attr("style","border:1px solid red");
			$(obj).val("");
		}else{
			$(obj).removeAttr("style");
		}
	}
}

/**验证身份证
 * @param id
 */
function validationIdCard(obj){
	var idCard = ($(obj).val()).trim();
	if(idCard != ""){
		//var _idCard = /^(\d{14}|\d{17})[0-9x]$/;
		//var _idCard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
		var _idCard=/^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/;
		if(! _idCard.test(idCard)){
			$(obj).attr("style","border:1px solid red");
			$(obj).val("");
		}else{
			$(obj).removeAttr("style");
		}
	}
}
