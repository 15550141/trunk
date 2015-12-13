
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

function showPayment(payment,appId){
    $("#pz").val(payment);
    $("#appId").val(appId);
}


jQuery(document).ready(function () {
    $('.datetimepicker').datetimepicker({
        format: 'yyyy-MM-dd HH:mm:ss',
        language: 'cn'
    });
    $('.datetimepicker').dblclick(function () {
        $(this).children("input").val("");
    });

    //搜索
    $("#searchJdFoTransfer").click(function () {
            $('#jdFoTransferForm').submit();
    });
    //新增

    $(".applyAudit").click(function () {
        $("#id").val($(this).attr("val"));
        $("#taskId").val($(this).attr("taskId"));
        $("#pageForm").attr("action", $("#action").val());
        $("#pageForm").submit();
    });

    $(".check").click(function () {
        $("#id").val($(this).attr("val"));
        $("#applyForm").attr("action", $("#checkAction").val());
        $("#applyForm").submit();
    });
});

function check(obj){
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
    //obj.value = obj.value.replace(/^0+/,'0');
    obj.value = obj.value.replace(/^(0+)([1-9])/,'$2');
    obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是.
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数
    //obj.value = obj.value.replace(/(\d{1,3})(?=(?:\d{3})+(?!\d))/g,'$1,'); // 加入‘,’
}

function audit(val, url) {
    var remark = $("#remark").val();
    if (remark == null || remark == 'undefined' || remark == '') {
        $("#err-message").show();
        $("#err-message").addClass("alert-error");
        $("#err-span").html("请输入审核意见");
        return false;
    }
    if (confirm("是否确认提交审核信息?")) {
        $("#err-message").hide();
        $(".audit_btn").attr('disabled', true);
        $.ajax({
            type: "GET",
            url: url,
            data: {
                "taskId": $("#taskId").val(),
                "id": $("#id").val(),
                "processId": $("#processId").val(),
                "opinion": val,
                "approvalOpinion": remark,
                "taskKey": $("#taskKey").val()
            },
            dataType: "json",
            async: false,
            success: function (opBackResult) {
                if (opBackResult.status) {
                    $(".audit_btn").hide();
                    $("#remark").attr("readonly", "readonly");
                    $.alert.dialog("提示信息", "text:审核完成，请返回列表", "550px", "auto", "");
                } else {
                    $(".audit_btn").hide();
                    $("#remark").attr("readonly", "readonly");
                    $.alert.dialog("提示信息", "text:审核失败，" + opBackResult.message, "550px", "auto", "");
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



