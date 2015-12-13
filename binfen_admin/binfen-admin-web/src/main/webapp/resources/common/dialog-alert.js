// Lee dialog 1.0 http://www.xij.cn/blog/?p=68
FCASH.register("com.jd.fcash.alert");
com.jd.fcash.alert={
  dialogFirst:true,
  dialog:function(title,content,width,height,cssName,method){
      var _this = this;
    if(_this.dialogFirst==true){

        var temp_float=new String;
        temp_float="<div class=\"modal-backdrop fade in\"></div>";
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
//        temp_float += "<a href=\"#\" class=\"btn btn-primary\">Save changes</a>";
        temp_float += "</div>";
        temp_float += "</div>";
        $("body").append(temp_float);
        _this.dialogFirst=false;
    }

    $(".close,#close").click(function(){
//        $(".modal-backdrop").animate({opacity:"0"},"normal",function(){$(this).hide();});
//        $("#floatBox").animate({top:($(document).scrollTop()-(height=="auto"?300:parseInt(height)))+"px"},"normal",function(){$(this).hide();});
        //自己加的两行

        _this.dialogFirst=true;
        if(method){
            method();
        }  else{
            $('.modal-backdrop').remove();
            $('#floatBox').remove();
        }
    });

    $("#floatBox .modal-header h3").html(title);
    contentType=content.substring(0,content.indexOf(":"));
    content=content.substring(content.indexOf(":")+1,content.length);
    switch(contentType){
        case "url":
            var content_array=content.split("?");
            /* $("#floatBox .content").ajaxStart(function(){
             $(this).html("loading...");
             });*/
            $.ajax({
                type:content_array[0],
                url:content_array[1],
                data:content_array[2],
                error:function(){
                    $("#floatBox .modal-body p").html("error...");
                },
                success:function(html){
                    $("#floatBox .modal-body p").html(html);
                }
            });
            break;
        case "text":
            $("#floatBox .modal-body p").html(content);

            break;
        case "id":
            $("#floatBox .modal-body p").html($("#"+content+"").html());
            break;
        case "iframe":
            $("#floatBox .modal-body p").html("<iframe src=\""+content+"\" width=\"100%\" height=\""+(parseInt(height)-30)+"px"+"\" scrolling=\"auto\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\"></iframe>");
            break;
        case "img":
            $("#floatBox .modal-body p").html("<img src=\""+content+"\" width=\"100%\" height=\""+(parseInt(height)-30)+"px"+"\" scrolling=\"auto\" border=\"0\" marginheight=\"0\" marginwidth=\"0\" />");
    }

    $("#floatBoxBg").show();
    $("#floatBoxBg").animate({opacity:"0.5"},"normal");
    $("#floatBox").attr("class","modal "+cssName);
    $("#floatBox").css({
        display:"block",
        left:"50%",
//        top:-(height=="auto"?300:parseInt(height))+"px",
        top:"0px",
        marginLeft:-(parseInt(width)/2)+"px"
    });
      $("#floatBox").css({display:"block",width:width,height:height});

//      alert($(document).scrollTop());
//      alert($("#floatBox").css("top"));
//    $("#floatBox").css({display:"block",left:(($(document).width())/2-(parseInt(width)/2))+"px",top:($(document).scrollTop()-(height=="auto"?300:parseInt(height)))+"px",width:width,height:height});
//    $("#floatBox").animate({top:($(document).scrollTop()+10)+"px"},"normal");
      $("#floatBox").animate({top:"200px"},"normal");
//    $(".modal-body").mCustomScrollbar();
}

}