$(window).resize(function() {
		var height = document.documentElement.clientHeight - 74;
		$('#contentFrame').height(height);
	});
$(function() {
		$(document.body).css({
			"overflow-y" : "hidden"
		});
		var height = document.documentElement.clientHeight - 74;
		$('#contentFrame').height(height);
		
		//�����˵� ����
		$(".nav_tabDefault").hover(function() {
			var childNav=$(this).find(".nav_Droplist");
			if (childNav.length > 0) {
				$(this).addClass("nav_tabHover");
				childNav.removeClass("dropHidden");
			}
		}, function() {
			var childNav=$(this).find(".nav_Droplist");
			if (childNav.length > 0) {
				$(this).removeClass("nav_tabHover");
				childNav.addClass("dropHidden");
			}
		});
		$(".nav_tabDefault").click(function() {
			//if ($(this).attr("class") != "nav_tabDefault fl headerIco userHelp nav_tabHover")
				$(this).addClass("nav_tabSelected").siblings().removeClass("nav_tabSelected");
			});
		$(".nav_Droplist").find("li").hover(function() {
			$(this).addClass("hover");
		}, function() {
			$(this).removeClass("hover");
		});
		
	});
	function showMenu(obj) {
		$("#" + obj).show();
	}
	function hideMenu(obj) {
		$("#" + obj).hide();
	}

	//注销登录
	function logout(obj){

		if(confirm("确定要注销？")){
			$.ajax( {
				url : "/logout",
				type : "post",
				dataType : "json",
				data : "promotionId=1",
				success : function(data) {
					if (data.msg == "success") {
						//删除成功
						//alert("注销成功！");
						//$(obj).parent().parent().remove();
						window.location.href = data.url;
					} else {
						alert("系统异常，失败！");
					}
				},
				errot : function() {
					alert("系统超时，失败！");
				}
			});
		}
	}