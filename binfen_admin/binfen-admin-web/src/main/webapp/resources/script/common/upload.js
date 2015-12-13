FCASH.register("com.jd.fcash.common.upload");
com.jd.fcash.common.upload = {
    load:function(url,swf,dropUrl,appId,type,use){
        $("#uploadify").uploadify({
            height        : 30,
            swf           : swf,
            uploader      : url,
            width         : 120,
            //浏览按钮的宽度
            'width':'50',
            //浏览按钮的高度
            'height':'22',
            fileObjName:'uploadify',
            //附带值
            formData:{
                'ffpFile.appId':appId,
                'ffpFile.type':type,
                'ffpFile.use':use
            },
            //上传数量
            'queueSizeLimit' : 5,
            'fileTypeDesc':'支持的格式：',
            //允许上传的文件后缀
            'fileTypeExts':'*.jpg;*.jpge;*.gif;*.png',
            //上传文件的大小限制
            'fileSizeLimit':'10MB',
            'buttonText' : '选择',
        //返回一个错误，选择文件的时候触发
        'onSelectError':function(file, errorCode, errorMsg){
            switch(errorCode) {
                case -100:
                    alert("上传的文件数量已经超出系统限制的"+$('#uploadify').uploadify('settings','queueSizeLimit')+"个文件！");
                    break;
                case -110:
                    alert("文件 ["+file.name+"] 大小超出系统限制的"+$('#uploadify').uploadify('settings','fileSizeLimit')+"大小！");
                    break;
                case -120:
                    alert("文件 ["+file.name+"] 大小异常！");
                    break;
                case -130:
                    alert("文件 ["+file.name+"] 类型不正确！");
                    break;
            }
        },
//            'onInit': function () {                        //载入时触发，将flash设置到最小
//                $("#uploadify-queue").hide();
//            },
        //检测FLASH失败调用
        'onFallback':function(){
            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
        },
        //上传到服务器，服务器返回相应信息到data里
        'onUploadSuccess':function(file, data, response){
            //$('#uploadify').uploadify('upload');
           data = data.replace(/\"/g, "");
           var datas= new Array();
           datas = data.split("|");
           var newRow = '<tr id="tr_'+datas[0]+'" ><td><a href='+decodeURIComponent(datas[1])+'>'+file.name+'</a><br/></td><td><a onclick="com.jd.fcash.credit.error.info.dropFile('+datas[0]+')" class="del btn btn-mini btn-primary"  >删除</a></td></tr>';
            $("#msg tr:last").after(newRow);

        }

        });
    }
}
