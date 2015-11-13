$(function() {
	verifyeffect();
	doRegister();
})


function verifyeffect() {
	$('#TestGetCode').on('click', function() {
		if ($("#TestGetCode span:eq(0)").html() == "请求中...") {
			return false;
		}
		var mobile = $("#mobile").val(),
			p = $("#passwd").val(),
			q = $("#c-passwd").val(),
			$this = $(this);
		$(".m-form-tips").remove();
		if (isMobel(mobile)) {
			if (p != "" && q != "" && p == q) {
				$.ajax({
					type: 'POST',
					url: "/ajax/register/sendPhoneTicket",
					beforeSend: function() {
						$("#TestGetCode span:eq(0)").html("请求中...");
					},
					data: {
						mobile: mobile
					},
					success: function(data) {
						$("#TestGetCode span:eq(0)").html("点击获取");
						if (data.code == 300) {
							var c = $('<div class="alert alert-danger m-form-tips" role="alert">' + data.msg + '</div>');
							$('#registerForm').before(c);
						} else {
							$this.find('span:eq(0)').hide().end()
								.find('span:eq(1)').removeClass('hide').end()
								.attr({
									'disabled': ''
								});
							countdown();
						}
					},
					dataType: 'json'
				});
			} else {
				var c = $('<div class="alert alert-danger m-form-tips" role="alert">请填写正确的密码</div>');
				$('#registerForm').before(c);
			}
		} else {
			var c = $('<div class="alert alert-danger m-form-tips" role="alert">请填写正确的手机号</div>');
			$('#registerForm').before(c);
		}
	})
}

/*统计*/
function action_statistics(input_params){
    var args = {};
    var match = null;
    var search = decodeURIComponent(location.search.substring(1));
    var reg = /(?:([^&]+)=([^&]+))/g;
    while((match = reg.exec(search))!==null){
        args[match[1]] = match[2];
    }
    var input_statistics = args.statistics;
    var input_referrer = document.referrer;
    $.post("/ajax/statistics/do_statistics",{statistics:input_statistics,referrer:input_referrer,params:input_params},function(data){
           
    });  
}

function doRegister() {
	$("#m-submit").click(function() {
		if ($("#m-submit").html() == "注册中...") {
			return false;
		}
		var mobile = $("#mobile").val(),
			identcode = $("#identcode").val(),
			p = $("#passwd").val(),
			q = $('#c-passwd').val(),
			b = false,
			authcode = $("#authcode").val();
		$(".m-form-tips").remove();

		if (isMobel(mobile)) {
			if (p != "" && q != "" && p == q) {
				b = true
			} else {
				var c = $('<div class="alert alert-danger m-form-tips" role="alert">请填写正确的密码</div>');
				$('#registerForm').before(c);
			}
		} else {
			var c = $('<div class="alert alert-danger m-form-tips" role="alert">请填写正确的手机号</div>');
			$('#registerForm').before(c);
		}

		if (b) {
			$.ajax({
				type: 'POST',
				url: "/ajax/register",
				beforeSend: function() {
					$("#m-submit").html("注册中...");
				},
				data: {
					mobile: mobile,
					password: p,
					re_password: q,
					identcode: identcode,
					authcode:authcode
				},
				success: function(data) {
					$("#m-submit").html("注 册");
					/*统计start*/
				    var input_params = {
				        isreg:1
				    };
					action_statistics(input_params);
					/*统计end*/
					if (data.code != 200) {
						$(".m-form-tips").remove();
						var c = $('<div class="alert alert-danger m-form-tips" role="alert">' + data.msg + '</div>');
						$('#registerForm').before(c);
					} else {
						window.location.href = "/";
					}
				},
				dataType: 'json'
			});
		}
	})
	$("#authcode-img").click(function(){
		refreshAuth();
	})
}
function refreshAuth(){
	$("#authcode-img").attr('src', '/register/authcode/'+Math.random());
}

function countdown() {
	var delay = $('#timeout').text();
	var t = setTimeout("countdown()", 1000);
	if (delay > 0) {
		delay--;
		$('#timeout').text(delay);
	} else {
		clearTimeout(t);
		$('#TestGetCode').find('span:eq(0)').show().end()
			.find('span:eq(1)').addClass('hide').end()
			.removeAttr('disabled');
		$('#timeout').text(60);
	}
}

function isMobel(value) {
	if (/^1[3,4,5,7,8]\d{9}$/g.test(value)) {
		return true;
	} else {
		return false;
	}
}