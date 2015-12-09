$(function($){
    $.substitute = function(
        /*String*/      template,
        /*Object|Array*/map,
        /*Function?*/   transform,
        /*Object?*/     thisObject){
        
        thisObject = thisObject || $.noop;
        transform = transform || thisObject["transform"] || function(v){ return v; };

        return template.replace(/\$\{([^\s\:\}]+)(?:\:([^\s\:\}]+))?\}/g, function(match, key, format){
                var value = map[key] || ("undefined" === typeof map[key] ? match : map[key]);
                format && (value = thisObject[format](value, key));
                return transform(value, key).toString();
            }); // String
    };

    /*赠品*/
    if ($('#usergift-finish-btn').length) {
        $('#usergift-finish-btn').on('click',function(){
            if ($('#m-cart .list-unstyled li input[type="checkbox"]:checked').length==0) return MessageBox.errorFadeout('请选择商品加入');
            var items = [];
            $('#m-cart .list-unstyled li input[type="checkbox"]:checked').each(function(index,item){
                var parent_li = $(item).parents('li');
                items[index] = {
                    'pid':parent_li.attr('pid'),
                    'ppid':parent_li.attr('ppid'),
                    'pno':parent_li.attr('pno'),
                    'type':'user_gift',
                    'qty':parent_li.attr('qty'),
                    'gift_send_id':parent_li.attr('gift_send_id'),
                    'gift_active_type':parent_li.attr('gift_active_type')
                };
            });
            Cart.add(items,function(resp){
                if (resp.code == 200) {
                    location.href = '/cart';
                } else {
                    MessageBox.errorFadeout(resp.msg);
                }
            });
        });
    };

    /*搭配*/
    if ($('#dapeigou-finish-btn').length) {
        $('#dapeigou-finish-btn').on('click',function(){
            if ($('#m-cart .list-unstyled li input[type="checkbox"]:checked').length==0) return MessageBox.errorFadeout('请选择商品加入');
            var items = [];
            $('#m-cart .list-unstyled li input[type="checkbox"]:checked').each(function(index,item){
                var parent_li = $(item).parents('li');
                items[index] = {
                    'pid':parent_li.attr('pid'),
                    'ppid':parent_li.attr('ppid'),
                    'pno':parent_li.attr('pno'),
                    'type':'normal',
                    'qty':1,
                    'pmt_id':parent_li.attr('pmt_id')
                };
            });

            Cart.add(items,function(resp){
                if (resp.code == 200) {
                    location.href = '/cart';
                } else {
                    MessageBox.errorFadeout(resp.msg);
                }
            });
        });
    };

    /*换购*/
    if ($('#huangou-finish-btn').length) {
        $('#huangou-finish-btn').on('click',function(){
            if ($('#m-cart .list-unstyled li input[type="radio"]:checked').length==0) MessageBox.errorFadeout('请选择商品加入');

            var item = $('#m-cart .list-unstyled li input[type="radio"]:checked');

            var parent_li = item.parents('li');
            var items = {
                0:{
                    'pid':parent_li.attr('pid'),
                    'ppid':parent_li.attr('ppid'),
                    'pno':parent_li.attr('pno'),
                    'type':'exch',
                    'qty':1,
                    'pmt_id':parent_li.attr('pmt_id')
                }
            };
            Cart.add(items,function(resp){
                if (resp.code == 200) {
                    location.href = '/cart';
                } else {
                    MessageBox.errorFadeout(resp.msg);
                }
            });
        });
    };


    function updateCart(){
        var $num, _val;var timeout;
        $('.num_sel_lage').delegate('.deC','click',function(event){
            event.stopPropagation();

            $num=$(this).prev('input');
            _val=parseInt($num.val());
            if(_val<=1){
                _val=1;
            }
            _val++;
            $num.val(_val);

            var parent_li = $(this).parents('li');
            var data = {
                'itemId':parent_li.attr('itemId'),
                'skuId':parent_li.attr('skuId'),
                'type':parent_li.attr('type'),
                'salesPropertyName':parent_li.attr('salesPropertyName'),
                'num':_val,
                'ik':parent_li.attr('id')
            };

            if (timeout) {
                clearTimeout(timeout);
            };
            
            timeout = setTimeout(function(){Cart.update(data);},200);
            
        });
        $('.num_sel_lage').delegate('.inC','click',function(event){

            event.stopPropagation();
            $num=$(this).next('input');
            _val=parseInt($num.val()); 

            if (_val==1) return;
            _val--;
            if(_val>1){
                $num.val(_val);
            }else{
                $num.val(1);
            }

            var parent_li = $(this).parents('li');
            var data = {
                'itemId':parent_li.attr('itemId'),
                'skuId':parent_li.attr('skuId'),
                'type':parent_li.attr('type'),
                'salesPropertyName':parent_li.attr('salesPropertyName'),
                'num':$num.val(),
                'ik':parent_li.attr('id')
            };

            if (timeout) {
                clearTimeout(timeout);
            };
            timeout = setTimeout(function(){Cart.update(data);},200);
        
        });
    }

    /*删除与修改*/
    if($('.cart-body').length){
        if($('.cart-body .m-cartlist').length < 1){
            localStorage['cartcount'] = 0;
        }
        var winH=parseInt($(window).height()),
        topH=parseInt($('body').css('paddingTop'));
        updateCart();
        $('body').css({'paddingBottom':47});
        $('.cart-body').css({'minHeight':winH-topH-47});

        $('.m-cartlist').delegate('.m-cartlist-delete','click',function(event){
            event.stopPropagation();
            var $this=$(this);
            MessageBox.confirm('亲，您真的要删除吗~',function(){
                var parent_li = $this.parents('li');
                var data = {
                    'itemId':parent_li.attr('itemId'),
                    'skuId':parent_li.attr('skuId'),
                    'type':parent_li.attr('type'),
                    'ik':parent_li.attr('id')
                };
                Cart.remove(data);
                parent_li.remove();
            });
        });
        $('#m-cart').delegate('.donationTips','click', function(){
            $(this).tooltip('show')
            $(this).next('.tooltip').delay(3000).fadeOut(200)
        })
    }

    /*列表页加购物车*/
    function addTocart(){
            $('#m-prolist').delegate('.m-prolist-car','click', function(event) {
               event.stopPropagation();
               var $proinfo = $(this).parents('.m-prolist-info');
               var qty = 1;
               var items = {
                    0:{
                        'pid' : $proinfo.attr('pid'),
                        'ppid': $proinfo.attr('ppid'),
                        'type': 'normal',
                        'pno' : $proinfo.attr('pno'),
                        'salesPropertyName' : $proinfo.attr('salesPropertyName'),
                        'qty' : qty
                    }
               };
               var $ths = $(this);
               Cart.add(items,function(resp){
                    if (resp.success != true) return MessageBox.errorFadeout(resp.msg);
                    var imgtofly = $ths.parents('li').find('a>img').eq(0);
                    var cart = $('.navbar-cart .fdayicon-cart');

                    if (imgtofly) {
                        var imgclone = imgtofly.clone()
                            .offset({ top:imgtofly.offset().top, left:imgtofly.offset().left })
                            .css({'opacity':'0.7', 'position':'absolute', 'height':'120px', 'width':'120px', 'z-index':'3000'})
                            .appendTo($('body'))
                            .animate({
                                'top':cart.offset().top+5,
                                'left':cart.offset().left+5,
                                'width':10,
                                'height':10
                            }, 500);
                        imgclone.animate({'width':0, 'height':0},0,function(){ 
                            $(this).detach();
                            //var num=parseInt(cart.text());
                            //num=num+qty;
                            //cart.append('<i class="cart-sales-nums">'+num+'</i>');
                            setCartCount();
                         });
                    }
               });
                return false;
            });
    }
    if ($('#m-prolist').length) {
        addTocart();
    };

    if ($('#m-prodetail').length) {
        $('.add-cart,#addCart,.btn-danger').on('click',function(){
        var li = $('.m-pro-parameter .m-proselect-item ul li.selected');
        if (!li.length) return MessageBox.errorFadeout('请选择商品规格');

        var qty = $('#buy_num').val();
        if (parseInt(qty)<1) return MessageBox.errorFadeout('购买数量必须大于0');

        var items = {
            0: {
            'pid' :li.attr('pid'),
            'ppid' :li.attr('ppid'),
            'salesPropertyName':li.attr('salesPropertyName'),
            'qty':qty,
            'type':'normal'
            }
        };

        var $ths = $(this);
        Cart.add(items,function(resp){
            if (resp.success == true) {
                if ($ths.hasClass('btn-danger')) return location.href='/cart';
                MessageBox.show('恭喜您，商品已成功加入购物车',3000,'downslide');
                setCartCount();
            } else {
                MessageBox.errorFadeout(resp.msg);
            }
        });
    });
    };

    /*会员页赠品加购物车*/
    if ($('#m-cart .m-user-donatelist .m-cartlist-choice button').length) {
        $('.m-user-donatelist').delegate('.m-cartlist-choice button:not(:disabled)','click',function(){
            var items = []; var li = $(this).parents('li');
                items[0] = {
                    'pid':li.attr('pid'),
                    'ppid':li.attr('ppid'),
                    'pno':li.attr('pno'),
                    'type':'user_gift',
                    'qty':li.attr('qty'),
                    'gift_send_id':li.attr('gift_send_id'),
                    'gift_active_type':li.attr('gift_active_type')
                };

            var $ths = $(this);
            Cart.add(items,function(resp){
                if (resp.code == 200) {
                    $ths.attr('disabled','').text('已领取');

                    var imgtofly = $ths.parents('li').find('a>img').eq(0);
                    var cart = $('.m-component-nav .fdayicon-cart');

                    if (imgtofly) {
                        var imgclone = imgtofly.clone()
                            .offset({ top:imgtofly.offset().top, left:imgtofly.offset().left })
                            .css({'opacity':'0.7', 'position':'absolute', 'height':'120px', 'width':'120px', 'z-index':'3000'})
                            .appendTo($('body'))
                            .animate({
                                'top':cart.offset().top+5,
                                'left':cart.offset().left+5,
                                'width':10,
                                'height':10
                            }, 500);
                        imgclone.animate({'width':0, 'height':0},0,function(){ 
                            $(this).detach();

                            setCartCount();
                         });
                    }
                } else {
                    MessageBox.errorFadeout(resp.msg);
                }
            });
        });

    };

    /*礼品卷*/
    if ($('#m-cart .m-user-donateinfo #gift-coupons-btn').length) {
        $('#m-cart .m-user-donateinfo #gift-coupons-btn').on('click',function(){
            var input = $(this).parents('.input-group').find('.form-control')
            var val = input.val();
            if ($.trim(val) == ''){
                MessageBox.errorFadeout('请输入礼品券');
                return ;
            }

            var _this = $(this);
            $.post('/user/gcouponGet',{'card_number':val},function(resp){
                if (resp.code != 200){
                    MessageBox.errorFadeout(resp.msg);
                    return ;
                }
                MessageBox.show('礼品码兑换成功，请在我的赠品里查看');
                input.val('');
            },'json');
        });
    };
});

/*统计*/
function action_statistics(input_params){
    var args = {};
    var match = null;
    var search = decodeURIComponent(location.search.substring(1));
    var reg = /(?:([^&]+)=([^&]+))/g;
    while((match = reg.exec(search))!==null){
        args[match[1]] = match[2];
    }
    var input_statistics = args.statistics;
    var input_referrer = document.referrer;
    $.post("/ajax/statistics/do_statistics",{statistics:input_statistics,referrer:input_referrer,params:input_params},function(data){
           
    });  
}

var Cart = {
    sessid:'',
    url:'http://www.binfenguoyuan.cn',
    add:function(){
        var items = arguments[0];
        var callback = arguments[1];

        var data = {
            'skuId':items[0].ppid,
            'itemId':items[0].pid,
            'salesPropertyName':items[0].salesPropertyName,
            'num':items[0].qty
        };
        $.ajax('/cart/add',{
            'type': 'POST',
            'data':data,
            'dataType':'json',
            'beforeSend':function(XHR){
            },
            'success':function(resp, textStatus, jqXHR){
                if (resp.success == true){
                    //localStorage['cartcount'] = resp.cart_items_count;
                }

                if ($.isFunction(callback)) {
                    callback(resp);
                    return ;
                }

                if (resp.success == true) {
                    MessageBox.show('恭喜您，商品已成功加入购物车');
                } else {
                    MessageBox.errorFadeout(resp.msg);
                } 
            }
        });
    },
    update:function(){
        var item = arguments[0];

        item.ik = item.ik.substr(2);
        var data = item;

        var _this = this;
        $.ajax('/cart/update',{
            'type': 'POST',
            'data':data,
            'dataType':'json',
            'beforeSend':function(XHR){
                MessageBox.loading();
            },
            'success':function(resp, textStatus, jqXHR){
                MessageBox.unloading();
                if (resp.success == true) {
                    //localStorage['cartcount'] = resp.cart_items_count;

                    _this.body(resp.result);

                } else {
                    MessageBox.errorFadeout(resp.resultMessage);
                }
            }
        });
    },
    remove:function(){
        var item = arguments[0];

        var data = item;

        var _this = this;

        $.ajax('/cart/remove',{
            'type': 'POST',
            'data':data,
            'dataType':'json',
            'beforeSend':function(XHR){
                MessageBox.loading();
            },
            'success':function(resp, textStatus, jqXHR){
                MessageBox.unloading();
                if (resp.success == true) {
                    //localStorage['cartcount'] = resp.cart_items_count;

                    // $('.cart-body').html(resp.cartbody);
                    // lazyloadImg();
                    if ($.isEmptyObject(resp.result)) {
                        $('.cart-body').html('<section class="m-component-cart" id="m-cart"><div class="text-center"><span class="glyphicon fdayicon fdayicon-procart"></span><h4>您的购物车现在是空的噢~</h4><h5>现在就去选购吧</h5><a href="/" class="btn btn-warning navbar-btn">去逛逛</a></div></div>');
                    }else{
                        _this.body(resp.result);
                    }
                } else {
                    MessageBox.errorFadeout(resp.resultMessage,function(){});
                }
            }
        });

    },
    empty:function(){
        localStorage.removeItem('cartcount');
    },
    index:function(){
        var cart = localStorage['cartitems'];
        var form = $('<form action="/cart" method="post"><input type="hidden" name="carttmp" value="'+cart+'"></input></form>');
        form.submit();
    },
    body:function(cart){
        this.itemT(cart);
        this.pmtT(cart);

        $('.cart-body .navbar .container .navbar-text').html('合计：￥'+cart.totleSalePrice+'<p>商品总价￥'+cart.totleOriginalPrice+'， 运费￥'+cart.freightMoney+'，优惠￥'+cart.totlePreferentialPrice+'</p>');
    },
    pmtT:function(cart){
        var manjian = '<li class="donationTips" data-toggle="tooltip" data-placement="top" title="首单满19元，立减5元，快去凑单吧" ><i>减</i>首单满19元，立减5元，快去凑单吧</li>';
        var manzeng = '<li class="donationTips" data-toggle="tooltip" data-placement="top" title="满50元，送越南白心火龙果，快去凑单吧 " ><i>促</i>满50元，送越南白心火龙果，快去凑单吧 </li>';
        var yunfei = '<li class="donationTips" data-toggle="tooltip" data-placement="top" title="满39元免配送费，快去凑单吧 " ><i>包</i>满39元免配送费，快去凑单吧</li>';
        /*优惠提醒*/
        if ($('.cart-body .m-carttips').length) {$('.cart-body .m-carttips').remove();};
        
        var tipEl = $('<div class="m-carttips"><ul class="list-unstyled"></ul></div>');
            
        if(cart.totleOriginalPrice < 19 && cart.first){
        	tipEl.find('ul').append(manjian);
        }
        if(cart.totleOriginalPrice < 50){
        	tipEl.find('ul').append(manzeng);
        }
        if(cart.freightMoney > 0){
        	tipEl.find('ul').append(yunfei);
        }
        
        $('.cart-body #m-cart').append(tipEl);
    },
    itemT:function(cart){
        var T = '<li type="normal" salespropertyname="规格：1个(约700g)" skuid="100019" itemid="100019" id="zengpin"><a href="/detail/index/100019.html"><img alt="" src="/misc/style/image/2015102302.jpg" data-original="/misc/style/image/2015102302.jpg" class="lazy pull-left" style="display: block;"><div class="m-cartlist-info"><h3>越南白心火龙果（赠品）</h3><h4>规格：1个(约700g)</h4><h5>￥0</h5></div></a></li>';
        
        if ($('#zengpin').length) {
        	$('#zengpin').remove();
        };
            
        if(cart.totleOriginalPrice >= 50){
        	$('.cart-body #m-cart .m-cartlist .list-unstyled').append(T);
        }
        
        
    }
};

