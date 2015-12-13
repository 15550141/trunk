jQuery(document).ready(function() {

    //部门级联效果
    jQuery("#selDept").change(function() {
        var id = jQuery("#selDept").val();
        jQuery.ajax({
            url:"/json_getGroupList.action?fid=" + id,
            type:"post",
            cache:false,
            success:function(data) {
                jQuery("#selGroup option:not(:first)").remove();
                var groupId = jQuery("#selGroup");
                jQuery.each(data, function(index, entry) {
                    groupId.append("<option value='" + entry.bmId + "'>" + entry.bmName + "</option>");
                });
                if (data.length == 1) {
                    function setIndex() {
                        groupId[0].selectedIndex = 1;
                    }
                    setTimeout(setIndex, 0);
                }
            }
        });
    });

});