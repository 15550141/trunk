$(function() {
	doLogin();
})

function doLogin(){
	$("#m-submit").click(function() {
		if ($("#m-submit").html() == "登陆中...") {
			return false;
		}
		var f = $("#username").val(),
			d = $("#password").val(),
			b = true,
			e = $("#identcode").val();
		$(".m-form-tips").remove();

		if (d == "" || f == "") {
			var c = $('<div class="alert alert-danger m-form-tips" role="alert">不要心急哟，账号或密码还空着呐！</div>');
			$(".form-group :eq(0)").before(c);
			b = false;
		}
		if (b) {
			$.ajax({
				type: 'POST',
				url: "/ajax/login",
				beforeSend: function() {
					$("#m-submit").html("登陆中...");
				},
				data: {
					username: f,
					password: d,
					authcode:e
				},
				success: function(data) {
					if (data.code != 200) {
						var datamsg = data.msg;
						var msg =  datamsg.substr(datamsg.indexOf("#")+1);
						var login_error = datamsg.substring(0,datamsg.indexOf("#"));
						refreshAuth();
						if(login_error >=3){
							$("#auth-div").removeClass('hide');
						}else{
							if(!$("#auth-div").hasClass('hide')){
								$("#auth-div").addClass('hide');
							}
						}
						$(".m-form-tips").remove();
						$("#m-submit").html("登 陆");
						var c = $('<div class="alert alert-danger m-form-tips" role="alert">' + msg + '</div>');
						$(".form-group :eq(0)").before(c);
					} else {
						localStorage['cartcount'] = data.data.cartcount;

						var ref_url = $("#ref_url").val();
						window.location.href = ref_url;
					}
				},
				dataType: 'json'
			});
		}
	});
	$("#authcode-img").click(function(){
		refreshAuth();
	})
}
function refreshAuth(){
	$("#authcode-img").attr('src', '/login/authcode/'+Math.random());
}