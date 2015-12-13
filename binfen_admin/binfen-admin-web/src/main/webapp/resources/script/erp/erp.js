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


jQuery(document).ready(function () {
    $('.datetimepicker').datetimepicker({
        format: 'yyyy-MM-dd hh:mm:ss',
        language: 'en'
    });
    $('.datetimepicker').dblclick(function () {
        $(this).children("input").val("");
    });

    $("#apply").click(function () {
        if (isBlank($("#erp").val())) {
            $("#err-message").removeClass("alert-info").addClass("alert-error");
            $("#err-span").html("ERP为空");
            $("#err-message").show();
            return false;
        }
        if (isBlank($("#tureName").val())) {
            $("#err-message").removeClass("alert-info").addClass("alert-error");
            $("#err-span").html("姓名为空");
            $("#err-message").show();
            return false;
        }
        if (isBlank($("#deptName").val())) {
            $("#err-message").removeClass("alert-info").addClass("alert-error");
            $("#err-span").html("部门为空");
            $("#err-message").show();
            return false;
        }
        $('#form1').submit();
    });
});

function toModify(id, url) {
    window.location.href = url + '?id=' + id;
}




