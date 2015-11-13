$(function() {
	doLogout();
})

function doLogout(){
	$('#m-quit>.btn').on('click', function(){
		MessageBox.confirm('亲，您真的要退出吗~',function(){
                        localStorage['cartcount'] = 0;
                		location.href='/user/logout';
             	});
	})
}