$(function() {
    detailOrderCancle();
    detailOrderPay();
    detailOrderConfirm();
})

// 取消订单
function detailOrderCancle(){
    $('#order-cancal').on('click', function(){
        var order_name = $(this).attr('data-order');
        MessageBox.confirm('亲，您真的要取消吗~',function(){
            $.ajax({
                type: "POST",
                url: '/ajax/user/orderCancle',
                data: {
                    order_name:order_name ,
                },
                success: function(data) {
                    if(data.code==200){
                        $('#order-status-li').load(location.href + ' #order-status-li>*');
                        $("#m-order").next("nav").remove();
                    }else{
                        MessageBox.error('取消失败，请稍后再试');
                    }
                },
                dataType: 'json'
            });
        });
    });
}

// 支付订单
function detailOrderPay(){
    $('#order-pay').on('click', function(){
        var order_name = $(this).attr('data-order');
        $(this).text("支付跳转中...");
        $(this).attr('disabled',true);
        data = {
            'order_name':order_name
        };
        $.post('/ajax/order/orderPay',data,function(result_data){
            resp = eval('('+result_data+')');
            
            if(resp['code']=='200'){
                window.location.href = resp['msg'];
                $("#order-pay").attr('disabled',false);
            }else{
                $("#order-pay").text("立即支付");
                $("#order-pay").attr('disabled',false);
                MessageBox.error(resp.msg);
            }
        });
    });
}

// 完成订单
function detailOrderConfirm(){
    $('#order-confirm').on('click', function(){
        var order_name = $(this).attr('data-order');
        MessageBox.confirm('是否确认完成？',function(){
            $.ajax({
                type: "POST",
                url: '/ajax/user/orderConfirm',
                data: {
                    order_name:order_name ,
                },
                success: function(data) {
                    if(data.code==200){
                        $('#order-status-li').load(location.href + ' #order-status-li>*');
                        $("#m-order").next("nav").remove();
                    }else{
                        MessageBox.error('确认失败，请稍后再试');
                    }
                },
                dataType: 'json'
            });
        });
    });
}