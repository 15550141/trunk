function wap_oauth(){
    var args = {};
    var match = null;
    var search = decodeURIComponent(location.search.substring(1));
    var reg = /(?:([^&]+)=([^&]+))/g;
    while((match = reg.exec(search))!==null){
        args[match[1]] = match[2];
    }
    var service = args.service;
    if(service=='wapuser.App_oAuthSignin'){
        var params = {
            from:args.from,
            mobile:args.mobile,
            service:args.service,
            sign:args.sign,
            time:args.time
        }
        $.post("/ajax/oauth/signin",params,function(data){
           
        });  
    }
}

$(function(){
    wap_oauth();
});