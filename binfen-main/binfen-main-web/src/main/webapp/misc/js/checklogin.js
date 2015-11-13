var allcookies = document.cookie;  
var logincookie_name = "uid";
if(allcookies.indexOf(logincookie_name) == -1){   //索引的长度  
	window.location.href="http://www.binfenguoyuan.cn/oauth/login?rurl="+window.location.href;
}