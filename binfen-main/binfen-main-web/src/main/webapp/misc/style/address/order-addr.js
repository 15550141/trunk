$(function(){
    /*地址选择框start*/
    $('#myarea').on('click',function(){
        $('.modal-orderadd-area').modal('show');
    });
    /*地址选择框end*/
    
    /*三级地址获取start*/
    getArea(0,'province'); 
    /*三级地址获取end*/

    /*地址选事件start*/
    $(".province span").on('click', function(){
        $('.modal-orderadd-area').modal('hide');
    });
    $(".city span").on('click', function(){
        $('.city').fadeOut(100);
        $('.province').fadeIn(100);
        
    });
    $(".county span").on('click', function(){
        $('.county').fadeOut(100);
        $('.city').fadeIn(100);
        
    });
    /*地址选事件end*/

    /*默认地址开关事件start*/
    $('.onoffswitch-label').click(function(){
        var $checkbox=$('#myonoffswitch'),
            _val = $checkbox.val();
        if(_val==='0'){
            $checkbox.val('1');
        }else{
            $checkbox.val('0');
        }
    });
    /*默认地址开关事件end*/

    /*新增地址提交事件start*/
    $('#editEnd').on('click', function(){
        var m = $("#name").val(),
            province = $("#province").attr('areaid'),
            city = $("#city").attr('areaid'),
            county = $("#county").attr('areaid'),
            q = $("#addressDetail").val(),
            n = $('#tel').val(),
            t = $('#addressType').val(),
            d = $('#myonoffswitch').val();
        
        var address_id = $('#address_id').val();
        $this=$(this);
        $(".m-form-tips").remove();
        if(m==''){
            msgBox('请填写收货人');
        }else if(province=='' || city=='' || county==''){
            msgBox('请选择完整的配送区域');
        }else if(q==''){
            msgBox('请填写详细地址');
        }else if(n==''){
            msgBox('请填写收货人手机');
        }else if(!isMobel(n)){
            msgBox('手机格式错误');
        }else{
            $("#editEnd").text('提交中...');
            $("#editEnd").attr('disabled',true);
            var data = {
                'name':m,
                'province':province,
                'city':city,
                'county':county,
                'address':q,
                'mobile':n,
                'flag':t,
                'defaultFlag':d
            };
            if(address_id){
                data.address_id = address_id;
                var fun = 'updateAddr';
            }else{
                var fun = 'addAddr';
            }
            $.post('/receiveAddr/'+fun,data,function(resp){
                if(resp.success == true){
                    // choseAddr(resp.msg,'addAddr');
                    window.location.href='/receiveAddr/addrList';
                }else{
                    $("#editEnd").text('完成');
                    $("#editEnd").attr('disabled',false);
                    msgBox(resp.msg);
                }
            });
        }
        
    });
    /*新增地址提交事件end*/

    /*选择地址事件start*/
    $('.m-order-address').on('click', function(){
        var address_id = $(this).find(".address_id").val();
        choseAddr(address_id,'choseAddr');
    });
    /*选择地址事件end*/

    /*删除地址事件start*/
    $('.m-order-address-delete').on('click', function(){
        addr_obj = $(this);
        del_address_id = addr_obj.parent('li').find(".address_id").val();
        MessageBox.confirm('确定删除收货地址?',delAddr);
    });
    /*删除地址事件end*/



    
    
    
});


/*地址选择start*/
function choseAddr(address_id,usecase){
    data = {
        'id':address_id
    };
    $.post('/receiveAddr/choseAddr',data,function(resp){
        if(resp.success == true){
            window.location.href='/order?showwxpaytitle=1';
        }else{
            if(usecase=='addAddr'){
                $("#editEnd").text('完成');
                $("#editEnd").attr('disabled',false);
                msgBox(resp.resultMessage);
            }else if(usecase=='choseAddr'){
                MessageBox.error(resp.resultMessage);
            }
        }
    });
}
/*地址选择end*/

/*地址删除start*/
function delAddr(){
    data = {
        'id':del_address_id
    };
    $.post('/receiveAddr/delAddr',data,function(resp){
        if(resp.success == true){
            addr_obj.parent('li').remove();
        }else{
            MessageBox.error(resp.resultMessage);
        }
    });
}
/*地址删除end*/

/*错误通知start*/
function msgBox(msg){
    var c = $('<div class="alert alert-danger m-form-tips" role="alert">'+msg+'</div>');
    $('.m-order-address-form').before(c);
}
/*错误通知end*/

/*地址库获取start*/
function getArea(fid,act){
    var data = {
        'fid':fid
    };
    $.post('/address/getArea',data,function(resp){
        $("."+act+" dd").remove();
        $(resp).each(function(i){
            var dd = $('<dd>'+resp[i].addressName+'</dd>');
            $("."+act+" dt").after(dd);
            dd.on('click', function(){
                if(act=='province'){
                    $('.area').fadeOut(50);
                    getArea(resp[i].addressId,'city')
                    $('.city').fadeIn(100);
                    $("#province").attr('areavalue',resp[i].addressName);
                    $("#province").attr('areaid',resp[i].addressId);
                }else if(act=='city'){
                    $('.area').fadeOut(50);
                    getArea(resp[i].addressId,'county')
                    $('.county').fadeIn(100);
                    $("#city").attr('areavalue',resp[i].addressName);
                    $("#city").attr('areaid',resp[i].addressId);
                }else if(act=='county'){
                    $('.modal-orderadd-area').modal('hide');
                    $("#county").attr('areavalue',resp[i].addressName);
                    $("#county").attr('areaid',resp[i].addressId);
                    $("#myarea").text($("#province").attr('areavalue')+' '+$("#city").attr('areavalue')+' '+$("#county").attr('areavalue'));   
                }                
            });
        });
    },'json'); 
}
/*地址库获取end*/