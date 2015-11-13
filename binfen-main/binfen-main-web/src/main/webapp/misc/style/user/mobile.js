$(function() {
	verifyeffect();
	doChange();
})

function verifyeffect() {
	$('#TestGetCode').on('click', function() {
		if ($("#TestGetCode span:eq(0)").html() == "请求中...") {
			return false;
		}
		var mobile = $("#mobile").val(),
			p = $("#passwd").val(),
			$this = $(this);
		$(".m-form-tips").remove();
		if (isMobel(mobile)) {
			if (p != "") {
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

function doChange() {
	$("#m-submit").click(function() {
		if ($("#m-submit").html() == "提交中...") {
			return false;
		}
		var mobile = $("#mobile").val(),
			identcode = $("#identcode").val(),
			p = $("#passwd").val(),
			b = false;
		$(".m-form-tips").remove();

		if (isMobel(mobile)) {
			if (p != "") {
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
				url: "/ajax/user/bindmobile",
				beforeSend: function() {
					$("#m-submit").html("提交中...");
				},
				data: {
					mobile: mobile,
					password: p,
					identcode: identcode
				},
				success: function(data) {
					$("#m-submit").html("提 交");
					if (data.code != 200) {
						$(".m-form-tips").remove();
						var c = $('<div class="alert alert-danger m-form-tips" role="alert">' + data.msg + '</div>');
						$('#registerForm').before(c);
					} else {
						window.location.href = "/user";
					}
				},
				dataType: 'json'
			});
		}
	})
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