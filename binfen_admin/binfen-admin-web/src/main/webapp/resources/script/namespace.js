/**
 * Created with IntelliJ IDEA.
 * User: wanglinlin
 * Date: 13-9-4
 * Time: 下午10:55
 * To change this template use File | Settings | File Templates.
 */
var FCASH = new Object();


FCASH.register = function(path){
    var arr = path.split(".");
    var ns = "";
    for(var i=0;i<arr.length;i++){
        if(i>0) ns += ".";
        ns += arr[i];
        eval("if(typeof(" + ns + ") == 'undefined') " + ns + " = new Object();");
    }
}
