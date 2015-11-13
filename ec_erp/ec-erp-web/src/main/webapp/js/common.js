//---弹出层开关控制函数---
function show_layer(id){
	//--展示id所在层--
	$("#"+id).css("display","block");
	//--使id所在层上下居中--
	var hh=($("#"+id+">.tb_window").height())/2;
	var dd=hh+50
	$("#"+id+">.tb_window").css("margin-top","-"+dd+"px");
	//--鼠标移动弹出层--
	$("#"+id+">.tb_window>.title").mousedown(
	function(event){
	var offset=$("#"+id+">.tb_window").offset();
	
	if (navigator.userAgent.indexOf('Firefox') >= 0){
		x1=event.layerX;
		y1=event.layerY;
	}else{
		x1=event.offsetX;
		y1=event.offsetY;
	}
	//x1=event.clientX-offset.left;
	//y1=event.clientY-offset.top;
	//alert (x1+";"+y1);
	var witchButton=false;
	if(document.all&&event.button==1){witchButton=true;}
	else{if(event.button==0)witchButton=true;}
	if(witchButton)//按左键,FF是0，IE是1
		{
		$(document).mousemove(function(event){
		$("#"+id+">.tb_window").css("left",(event.clientX-x1+250)+"px"); 
		$("#"+id+">.tb_window").css("top",(event.clientY-y1+dd)+"px"); 
		})
		}
	})
	$("#"+id+">.tb_window>.title").mouseup(
	function(event){
	$(document).unbind("mousemove");
	})
}

function close_layer(id) {
	$("#" + id).css("display", "none");
}

// --下拉框查询函数---
function find(txt_id, cbl_id) {
	if (document.getElementById(cbl_id).disabled)
		return;
	if (document.getElementById(txt_id) != null
			&& document.getElementById(cbl_id) != null) {
		var find = document.getElementById(txt_id).value;
		if (find != '') {
			for (var i = 0; i < document.getElementById(cbl_id).options.length; i++) {
				if (document.getElementById(cbl_id).options[i].text
						.indexOf(find) > -1) {
					document.getElementById(cbl_id).options[i].selected = 'selected';
					break;
				}
			}
		}
	}
}
function find(txt_id, cbl_id, h_id, h_name) {
	if (document.getElementById(cbl_id).disabled)
		return;
	if (document.getElementById(txt_id) != null
			&& document.getElementById(cbl_id) != null) {
		var find = document.getElementById(txt_id).value;
		if (find != '') {
			for (var i = 0; i < document.getElementById(cbl_id).options.length; i++) {
				if (document.getElementById(cbl_id).options[i].text
						.indexOf(find) > -1) {
					document.getElementById(cbl_id).options[i].selected = 'selected';
					if (h_id != undefined)
						document.getElementById(h_id).value = document
								.getElementById(cbl_id).options[i].value;
					if (h_name != undefined)
						document.getElementById(h_name).value = document
								.getElementById(cbl_id).options[i].text;
					break;
				}
			}
		}
	}
}
function mousedown(txt_id, value) {
	if (document.getElementById(txt_id) != null
			&& document.getElementById(txt_id).value == value) {
		document.getElementById(txt_id).value = '';
	}
}
function mouseup(txt_id, value) {
	if (document.getElementById(txt_id) != null
			&& document.getElementById(txt_id).value == '') {
		document.getElementById(txt_id).value = value;
	}
}
function setGYS(selectid, hidvalue, hidtext) {
	var obj = document.getElementById(selectid);
	var index = obj.selectedIndex;
	var text = obj.options[index].text;
	var value = obj.options[index].value;

	document.getElementById(hidvalue).value = value;
	document.getElementById(hidtext).value = text;
}
/**
 * 表单域校验提示
 * 
 * @param objName
 * @param msgName
 * @param message
 */
function mouseLostFocus(objName, msgName, message) {
	var name = $(objName).val();
	if (name == "") {
		$(msgName).html(message);
		flag = false;
	} else {
		$(msgName).html("");
	}
}
/**
 * 输入框数量限制
 * 
 * @param max
 * @param tid
 * @param message
 */
function gbcount(max,tid,nid)
{
	if (tid.value.length > max) {
	tid.value = tid.value.substring(0,max);
	alert("内容已超过"+max+"个字!");
	}
	else {
	document.getElementById(nid).innerHTML= max - tid.value.length
	}
}
Date.prototype.format = function(format) {
	/*
	 * eg:format="YYYY-MM-dd hh:mm:ss";
	 */
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};
//加法
Number.prototype.add = function(arg) {
	var r1, r2, m;
	try {
		r1 = this.toString().split(".")[1].length;
	} catch (e) {
		r1 = 0;
	}
	try {
		r2 = arg.toString().split(".")[1].length;
	} catch (e) {
		r2 = 0;
	}
	m = Math.pow(10, Math.max(r1, r2));
	return (this * m + arg * m) / m;
};

// 减法
Number.prototype.sub = function(arg) {
	return this.add(-arg);
};

// 乘法
Number.prototype.mul = function(arg) {
	var m = 0, s1 = this.toString(), s2 = arg.toString();
	try {
		m += s1.split(".")[1].length;
	} catch (e) {
	}
	try {
		m += s2.split(".")[1].length;
	} catch (e) {
	}
	return Number(s1.replace(".", "")) * Number(s2.replace(".", ""))
			/ Math.pow(10, m);
};

// 除法
Number.prototype.div = function(arg) {
	var t1 = 0, t2 = 0, r1, r2;
	try {
		t1 = this.toString().split(".")[1].length;
	} catch (e) {
	}
	try {
		t2 = arg.toString().split(".")[1].length;
	} catch (e) {
	}
	with (Math) {
		r1 = Number(this.toString().replace(".", ""));
		r2 = Number(arg.toString().replace(".", ""));
		return (r1 / r2) * pow(10, t2 - t1);
	}
}