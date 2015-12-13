/**
 * 判断字符串是否为空
 * @param str 要判断的字符串
 * @return {boolean}
 */
function isBlank(str) {
    if (!str) {
        return true;
    }
    if (jQuery.trim(str) == '') {
        return true;
    }
    return false;
}

function searchData(url) {
    var shqId=$("#shqId").val();
    var oldShq=$("#oldShq").val();
    if (isBlank(shqId)) {
        $(".audit_btn").attr('disabled', false);
        $(".audit_btn").show();
        $.alert.dialog("提示信息", "text:结算单号为空", "550px", "auto", "");
        return false;
    }
    if(shqId==oldShq){
        $(".audit_btn").attr('disabled', false);
        $(".audit_btn").show();
        $.alert.dialog("提示信息", "text:您输入的结算单号与列表选择要替换的结算单号相同不允许替换", "550px", "auto", "");
        return false;
    }
    jQuery.ajax({
        type: "POST",
        url: url,
        data: {
            "appId": $("#shqId").val()
        },
        async: false,
        success: function (result) {
            if (result) {
                $("#amount").html(result.amountApp);
                $("#appDate").html(result.appDateApp);
                $("#approvedPayDate").html(result.approvedPayDateApp);
                $("#acceptanceTime").html(result.acceptanceTimeApp);
                $("#payType").html("电汇");
                $("#nowTime").html(result.nowTime);
            } else {
                $(".audit_btn").hide();
                $("#remark").attr("readonly", "readonly");
                $.alert.dialog("提示信息", "text:获取数据失败", "550px", "auto", "");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $(".audit_btn").attr('disabled', false);
            $(".audit_btn").show();
            $.alert.dialog("提示信息", "text:服务器出现异常，请稍后再试", "550px", "auto", "");
        }
    });
}
function replaceData(url) {
    var shqId=$("#shqId").val();
    var appId=$("#appId").val();
    var oldShq=$("#oldShq").val();
    var payType=$("#payType").text();
    if (payType=='---') {
        $(".audit_btn").attr('disabled', false);
        $(".audit_btn").show();
        $.alert.dialog("提示信息", "text:请获取到新结算单号后再替换", "550px", "auto", "");
        return false;
    }
    if (isBlank(shqId)) {
        $(".audit_btn").attr('disabled', false);
        $(".audit_btn").show();
        $.alert.dialog("提示信息", "text:结算单号为空", "550px", "auto", "");
        return false;
    }
    if(isBlank(appId)){
        $(".audit_btn").attr('disabled', false);
        $(".audit_btn").show();
        $.alert.dialog("提示信息", "text:获取融资申请编号错误请重新打开页面重试", "550px", "auto", "");
        return false;
    }
    if (confirm("您确定要用结算单号["+shqId+"]替换为结算单号["+oldShq+"]？,融资申请编号["+appId+"]")) {
        jQuery.ajax({
            type: "POST",
            url: url,
            data: {
                "appId":appId,
                "shqId": shqId,
                "oldShq": oldShq
            },
            async: false,
            success: function (result) {
                if (result) {
                    $(".audit_btn").hide();
                    $("#remark").attr("readonly", "readonly");
                    $.alert.dialog("提示信息", "text:" + result=='true'?"替换成功":"替换失败", "550px", "auto", "");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $(".audit_btn").attr('disabled', false);
                $(".audit_btn").show();
                $.alert.dialog("提示信息", "text:服务器出现异常，请稍后再试", "550px", "auto", "");
            }
        });
    }
}
$.alert = {
    dialogFirst: true,
    dialog: function (title, content, width, height, cssName, method) {
        var _this = this;
        if (_this.dialogFirst == true) {

            var temp_float = new String;
            temp_float = "<div class=\"modal-backdrop fade in\"></div>";
            temp_float += "<div id=\"floatBox\" class=\"modal fade in\" style=' overflow-y: auto;' >";
            temp_float += "<div class=\"modal-header\">";
            temp_float += "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">×</button>";
            temp_float += "<h3>对话框标题</h3>";
            temp_float += "</div>";
            temp_float += "<div class=\"modal-body\" >";
            temp_float += "<p>loading...</p>";
            temp_float += "</div>";
            temp_float += "<div class=\"modal-footer\">";
            temp_float += "<button type='button' class=\"btn btn-primary  \" id='close'>关闭</button>";
            temp_float += "</div>";
            temp_float += "</div>";
            $("body").append(temp_float);
            _this.dialogFirst = false;
        }

        $(".close,#close").click(function () {
            _this.dialogFirst = true;
            if (method) {
                method();
            } else {
                $('.modal-backdrop').remove();
                $('#floatBox').remove();
            }
        });

        $("#floatBox .modal-header h3").html(title);
        contentType = content.substring(0, content.indexOf(":"));
        content = content.substring(content.indexOf(":") + 1, content.length);
        switch (contentType) {
            case "url":
                var content_array = content.split("?");
                $.ajax({
                    type: content_array[0],
                    url: content_array[1],
                    data: content_array[2],
                    error: function () {
                        $("#floatBox .modal-body p").html("error...");
                    },
                    success: function (html) {
                        $("#floatBox .modal-body p").html(html);
                    }
                });
                break;
            case "text":
                $("#floatBox .modal-body p").html(content);

                break;
            case "id":
                $("#floatBox .modal-body p").html($("#" + content + "").html());
                break;
            case "iframe":
                $("#floatBox .modal-body p").html("<iframe src=\"" + content + "\" width=\"100%\" height=\"" + (parseInt(height) - 30) + "px" + "\" scrolling=\"auto\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\"></iframe>");
                break;
            case "img":
                $("#floatBox .modal-body p").html("<img src=\"" + content + "\" width=\"100%\" height=\"" + (parseInt(height) - 30) + "px" + "\" scrolling=\"auto\" border=\"0\" marginheight=\"0\" marginwidth=\"0\" />");
        }

        $("#floatBoxBg").show();
        $("#floatBoxBg").animate({opacity: "0.5"}, "normal");
        $("#floatBox").attr("class", "modal " + cssName);
        $("#floatBox").css({
            display: "block",
            left: "50%",
            top: "0px",
            marginLeft: -(parseInt(width) / 2) + "px"
        });
        $("#floatBox").css({display: "block", width: width, height: height});
        $("#floatBox").animate({top: "200px"}, "normal");
    }
}





