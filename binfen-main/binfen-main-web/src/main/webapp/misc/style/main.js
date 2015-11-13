//配置页面加载模块参数
require.config({
	paths: {
		"jquery"		:'lib/jquery-1.11.0.min',
		"bootstrap"		:'lib/bootstrap.min',
		"sly"     		:'lib/sly.min',
		"lazyload"      :'lib/jquery.lazyload.min',
		"rose"          :'rose',
		"messagebox"    :'MessageBox',
		"userlogin" : "user/login",
		"userforgetps":"user/forgetps",
		"userregister":"user/register",
		'productdetail':"detail",
		'productlist':"product/list",	
		'userindex':"user/index",
		'userorders':'user/orders',
		'userorder':'user/order',
		'usermobile':'user/mobile'
	},
	shim: {//模块依赖关系
		jquery			: {exports: '$'},
		'bootstrap' : {deps: ['jquery']},
		'sly' : {deps: ['jquery']},
		'lazyload' : {deps: ['jquery']},
		'rose' : {deps: ['jquery']},
		'cart' : {deps: ['jquery']},
		'messagebox' : {deps: ['jquery']},
		'login':{deps: ['jquery']},
		'forgetps':{deps: ['jquery']},
		'register':{deps: ['jquery']},
		'productdetail':{deps: ['jquery']},
		'productlist':{deps: ['jquery']},
		'userindex':{deps: ['jquery']},
		'userorders':{deps: ['jquery']},
		'userorder':{deps: ['jquery']},
		'usermobile':{deps: ['jquery']}
	}
});

var commonJs = ['jquery','bootstrap','sly','lazyload','rose','messagebox'];
var urlPath = window.location.pathname;
urlPath = urlPath.replace(/\/$/,"");
urlPath = urlPath.split("\/",3).join("\/");
switch(urlPath){
	case '/login':
	case '/login/index':
		commonJs.push('userlogin');
		break;
	case '/register':
	case '/register/index':
		commonJs.push('userregister');
		break;
	case '/user':
	case '/user/index':
		commonJs.push('userindex');
		break;
	case '/login/forgetps':
		commonJs.push('userforgetps');
		break;
	case '/detail/index':
		commonJs.push('productdetail');
		commonJs.push('cart');
		break;
	case '/prolist/search':
	case '/prolist/index':
		commonJs.push('productlist');
		commonJs.push('cart');
		break;
	case '/user/orders':
		commonJs.push('userorders');
		break;
	case '/user/orderdetail':
		commonJs.push('userorder');
		break;
	case '/user/mobile':
		commonJs.push('usermobile');
	case '/user/giftsget':
	case '/user/gcouponGet':
		commonJs.push('cart');
		break;
}

if (/^\/cart/.test(urlPath)) {
	commonJs.push('cart');
};

require(commonJs,function($){
	$(function(){
		rosefunction();
	});
});
