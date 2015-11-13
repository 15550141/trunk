var MessageBox = {
    show:function(){
        var mMessage = (typeof(arguments[0]) != 'undefined') ? arguments[0] : '恭喜您，商品已成功加入购物车';
        var autotime = (typeof(arguments[1]) != 'undefined') ? arguments[1] : 3500;
        var effect = (typeof(arguments[2]) != 'undefined') ? arguments[2] : 'scroll';

        var el = $('<div class="m-global-tips"><dl class="clearfix"><dt class="m-global-suctips"></dt><dd><p>操作成功！</p>'+mMessage+'</dd></dl></div>');

        $('body').append(el);
        switch(effect){
            case 'downslide' :
                el.addClass('m-ani-in').delay(autotime).show(200,function(){
                    var $this=$(this)
                    $this.removeClass('m-ani-in').addClass('m-ani-out').delay(1000).hide(0, function(){  $this.remove(); });
                });
                break;
            default:
              el.delay(autotime).fadeOut(200,function(){
                $(this).remove();
              });
              break;
        }

    },
    warning:function(){

    },
    notice:function(){

    },
    error:function(){
        var mMessage = (typeof(arguments[0]) != 'undefined') ? arguments[0] : '亲，您的商品没有成功加入购物车，刷新一下试试哦~';
        var confirmCallback = arguments[1];
        var warning = (typeof(arguments[2]) != 'undefined') ? arguments[2] : '操作失败！';

        var mboxel = $('body').data('mbox:error');
        if (!$.isEmptyObject(mboxel)) {
            mboxel.remove();
        };

        var el = $('<div class="m-global-tips"><dl class="clearfix"><dt class="m-global-failtips"></dt><dd><p>'+warning+'</p>'+mMessage+'</dd></dl><div class="text-center m-global-btngroup"></div></div>');

        var confirmBtn = $('<button>',{
            'type':'button',
            'class':'btn btn-default confirm',
            'text':'确 定',
            'click':function(){
                el.fadeOut(200,function(){
                    $(this).remove();
                    if ($.isFunction(confirmCallback)) {
                        confirmCallback();
                    };
                    $('body').removeData('mbox:error');
                });
            }
        });

        el.find('.m-global-btngroup').append(confirmBtn);

        $('body').append(el);

        $('body').data('mbox:error',el);
    },
    errorFadeout:function(){
        var mMessage = (typeof(arguments[0]) != 'undefined') ? arguments[0] : '错误';

        var mboxel = $('body').data('mbox:errorFadeout');
        if (!$.isEmptyObject(mboxel)) {
            mboxel.remove();
        };

        var el = $('<div class="m-global-tips"><dl class="clearfix"><dt class="m-global-failtips"></dt><dd><p>操作失败！</p>'+mMessage+'</dd></dl></div>');

        $('body').append(el);

        $('body').data('mbox:errorFadeout',el);

      el.delay(2000).fadeOut(200,function(){
        $(this).remove();
        $('body').removeData('mbox:errorFadeout');
      });
    },
    confirm:function(){
        var mMessage = (typeof(arguments[0]) != 'undefined') ? arguments[0] : '亲，您真的要删除吗~';
        var confirmCallback = arguments[1];
        var cancelCallback = arguments[2];

        var mboxel = $('body').data('mbox:confirm');
        if (!$.isEmptyObject(mboxel)) {
            mboxel.remove();
        };

        var el = $('<div class="m-global-tips"><dl class="clearfix"><dt class="m-global-confirm"></dt><dd><br/>'+mMessage+'</dd></dl><div class="text-center m-global-btngroup"></div></div>');

        var cancelBtn = $('<button>',{
            'type':'button',
            'class':'btn btn-default cancel',
            'text':'取 消',
            'click':function(){
                el.fadeOut(200,function(){
                    $(this).remove();
                    if ($.isFunction(cancelCallback)) {
                        cancelCallback();
                    };
                    $('body').removeData('mbox:confirm');
                });
            }
        });

        var confirmBtn = $('<button>',{
            'type':'button',
            'class':'btn btn-default confirm',
            'text':'确 定',
            'click':function(){
                el.fadeOut(200,function(){
                    $(this).remove();
                    if ($.isFunction(confirmCallback)) {
                        confirmCallback();
                    };
                    $('body').removeData('mbox:confirm');
                });
            }
        });

        el.find('.m-global-btngroup').append(confirmBtn);
        el.find('.m-global-btngroup').append(cancelBtn);

        $('body').append(el);

        $('body').data('mbox:confirm',el);
    },
    loading:function(){

        var mboxel = $('body').data('mbox:loading');
        if (!$.isEmptyObject(mboxel)) {
            mboxel.remove();
        };

        var el = $('<div class="m-component-loading"><span class="glyphicon fdayicon fdayicon-loading"></span></div>');

        $('body').append(el);

        $('body').data('mbox:loading',el);
    },
    unloading:function(){
        var mboxel = $('body').data('mbox:loading');
        if (!$.isEmptyObject(mboxel)) {
            mboxel.remove();
        };
    }

};
