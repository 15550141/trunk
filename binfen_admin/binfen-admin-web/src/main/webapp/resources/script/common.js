
function gete(elementId)
{
    return document.getElementById(elementId);
}

function getv(e)
{
    obj = document.getElementById(e).value;
    if (obj != null)
        return obj.value;
    else
        return "undefined";
}

function gett(e)
{
    obj = document.getElementById(e);
    if (obj != null)
        return obj.text;
    else
        return "undefined";
}

function checkEmpty(ov)
{
    if (ov==null || ov=="" || ov=="undefined" || ov.value=="")
        return false;
    else
        return true;
}
function checkNum(ov)
{
    var re = /"^[0-9]*$"/;
    if (!re.test(ov))
    {
        return false
    }
    else
    {
        return true;
    }
}
function checkFloat(ov)
{
    var re = /^[1-9]d*.d*|0.d*[1-9]d*|0?.0+|0$/;
    if (!re.test(ov))
    {
        return false
    }
    else
    {
        return true;
    }
}


function getl(o)
{
    return o.replace(/[^\x00-\xff]/g,"**").length;
}

 

function checkl(o,l)
{
    return getl(o)<=l;
}
function showe(elementId)
{
    gete(elementId).style.display = "block";
}
function hidee(elementId)
{
    gete(elementId).style.display = "none";
}

function checkIsInteger(str)
{
    if(str == "")
        return false;
    if(/^(\-?)(\d+)$/.test(str) && str>=0)
        return true;
    else
        return false;
}



function checkIntegerMinValue(str,val)
{
    if(str == "")
        return true;
    if(typeof(val) != "string")
        val = val + "";
    if(checkIsInteger(str) == true)
    {
        if(parseInt(str,10)>=parseInt(val,10))
            return true;
        else
            return false;
    }
    else
        return false;
}


function checkIntegerMaxValue(str,val)
{
    if(str == "")
        return true;
    if(typeof(val) != "string")
        val = val + "";
    if(checkIsInteger(str) == true)
    {
        if(parseInt(str,10)<=parseInt(val,10))
            return true;
        else
            return false;
    }
    else
        return false;
}


function checkIsDouble(str)
{
    if(str == "")
        return true;
    if(str.indexOf(".") == -1)
    {
        if(checkIsInteger(str) == true)
            return true;
        else
            return false;
    }
    else
    {
        if(/^(\-?)(\d+)(.{1})(\d+)$/g.test(str))
            return true;
        else
            return false;
    }
}



function isLongDate(os)
{
        var r = os.replace(/(^\s*)|(\s*$)/g, "").match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/); 
        if(r==null)
        {
                return false; 
        }
        var d = new Date(r[1], r[3]-1,r[4],r[5],r[6],r[7]); 
        return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]&&d.getHours()==r[5]&&d.getMinutes()==r[6]&&d.getSeconds()==r[7]);
}

function timeComp(datestr1, datestr2)
{
    tmpBeginTime = new Date(datestr1.replace(/-/g,"\/"));
    tmpEndTime = new Date(datestr2.replace(/-/g,"\/"));
    if ( tmpEndTime > tmpBeginTime )
    {
        return true;
    }
    else 
    {
        return false;
    }
}


jQuery(document).ready(function() {
    //提示用户是否真正删除
    jQuery("a:contains('删除')").click(
            function() {
                return confirm("您确认删除吗?");
            }
            );
});



function closeWin() {
    window.opener = null;
    if (window.opener == null) {
        opener = ""
        window.open(self.location, "_self")
    }
    window.close();
}

function groupShow(groupkey) {
    $("*[group]").hide();
    $("*[group='" + groupkey + "']").show();
    $("div.tab").children().show().attr("class", "normal");
    $("div.tab").children("*[group='" + groupkey + "']").attr("class", "current");
}
//jquery 去掉文本框的前后空格
$(function(){
   $("input[type='text'],textarea").live("focusout",function(){
       $(this).val($.trim($(this).val()));
   });
});

/**
 * 个人对js float （javascript float）运算不精确来源的分析：
 *  单个Float一般相差[1/(10^(17-小数点前位数-1))]左右(是由于float二进制最后一位或被截掉而少1/(2^65),或被添加标志位而多1/(2^64),总64位);
 * 加减法,保留小数在(17-小数点前位数-1)内,精确度不受影响;
 * 因此,加减方只需处理有效数四舍五入;
 * eg: parseFloat((80.002 + parseFloat(1.1)).toFixed(3))
 * 乘除法,精度相差[1/(10^(17-小数点前位数-1))*被乘数的数量级]左右;
 * 因此,乘除法,我们一般计算都不会超过10^16级,所以也可以不予考虑;
 * eg : parseFloat((1/3).toFixed(2));
 *
 * 测试:
 * var a = 8.03/0.000000000000001; (10^15)正确;
 * var b = parseFloat((8.03/0.000000000000001).toFixed(2));
 * var c = 8.03*500000000000000; (10^16)偏差;
 * var d = parseFloat((8.03*500000000000000).toFixed(2));
 * 结论:
 * 在有限的计算级别下,js不会导致结果不正确,其结果是非常非常接近正确的结果,只需要做适当的截取精度和相应四舍五入进位；
 * js同事对js float运算不准确的担忧和恐慌是不必须的，自己去编写代码重写也是不科学的；
 *  从个人分析下面的代码是多余的；但对于其努力和奉献是肯定的；
 * @notice 以上仅个人观点，供参考，希望于人有益；若有不妥，还谢指正；
 * @time 2011-01-12;
 */
//除法函数，用来得到精确的除法结果
//说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
//调用：accDiv(arg1,arg2)
//返回值：arg1除以arg2的精确结果
function accDiv(arg1,arg2){
   var t1=0,t2=0,r1,r2;
   try{t1=arg1.toString().split(".")[1].length}catch(e){}
   try{t2=arg2.toString().split(".")[1].length}catch(e){}
   with(Math){
       r1=Number(arg1.toString().replace(".",""));
       r2=Number(arg2.toString().replace(".",""));
       return (r1/r2)*pow(10,t2-t1);
   }
}
//给Number类型增加一个div方法，调用起来更加方便。
Number.prototype.div = function (arg){
   return accDiv(this, arg);
};

//乘法函数，用来得到精确的乘法结果
//说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
//调用：accMul(arg1,arg2)
//返回值：arg1乘以arg2的精确结果
function accMul(arg1,arg2)
{
   var m=0,s1=arg1.toString(),s2=arg2.toString();
   try{m+=s1.split(".")[1].length}catch(e){}
   try{m+=s2.split(".")[1].length}catch(e){}
   return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
}
//给Number类型增加一个mul方法，调用起来更加方便。
Number.prototype.mul = function (arg){
   return accMul(arg, this);
};

//加法函数，用来得到精确的加法结果
//说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
//调用：accAdd(arg1,arg2)
//返回值：arg1加上arg2的精确结果
function accAdd(arg1,arg2){
   var r1,r2,m;
   try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
   try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
   m=Math.pow(10,Math.max(r1,r2));
   return (arg1*m+arg2*m)/m;
}
//给Number类型增加一个add方法，调用起来更加方便。
Number.prototype.add = function (arg){
   return accAdd(arg,this);
}

//减法函数
function accSub(arg1,arg2){
    var r1,r2,m,n;
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2));
    //last modify by deeka
    //动态控制精度长度
    n=(r1>=r2)?r1:r2;
    return ((arg2*m-arg1*m)/m).toFixed(n);
}
///给number类增加一个sub方法，调用起来更加方便
Number.prototype.sub = function (arg){
   return accSub(arg,this);
}

function fmoney(s, n) {
   var regex = /^[\d.]+$/; 
   if(!regex.test(s)){
	   return "0.00";
   }
   n = n > 0 && n <= 20 ? n : 2;
   s = parseFloat((s + "").replace(",", "")).toFixed(n) + "";
   var l = s.split(".")[0].split("").reverse(),
           r = s.split(".")[1];
   t = "";
   for (i = 0; i < l.length; i ++) {
       t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
   }
   t = t.split("").reverse().join("") + "." + r;
   t = t.replace(/(-,)/g,"-");
   return t;
}