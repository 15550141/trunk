
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
    //��ʾ�û��Ƿ�����ɾ��
    jQuery("a:contains('ɾ��')").click(
            function() {
                return confirm("��ȷ��ɾ����?");
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
//jquery ȥ���ı����ǰ��ո�
$(function(){
   $("input[type='text'],textarea").live("focusout",function(){
       $(this).val($.trim($(this).val()));
   });
});

/**
 * ���˶�js float ��javascript float�����㲻��ȷ��Դ�ķ�����
 *  ����Floatһ�����[1/(10^(17-С����ǰλ��-1))]����(������float���������һλ�򱻽ص�����1/(2^65),����ӱ�־λ����1/(2^64),��64λ);
 * �Ӽ���,����С����(17-С����ǰλ��-1)��,��ȷ�Ȳ���Ӱ��;
 * ���,�Ӽ���ֻ�账����Ч����������;
 * eg: parseFloat((80.002 + parseFloat(1.1)).toFixed(3))
 * �˳���,�������[1/(10^(17-С����ǰλ��-1))*��������������]����;
 * ���,�˳���,����һ����㶼���ᳬ��10^16��,����Ҳ���Բ��迼��;
 * eg : parseFloat((1/3).toFixed(2));
 *
 * ����:
 * var a = 8.03/0.000000000000001; (10^15)��ȷ;
 * var b = parseFloat((8.03/0.000000000000001).toFixed(2));
 * var c = 8.03*500000000000000; (10^16)ƫ��;
 * var d = parseFloat((8.03*500000000000000).toFixed(2));
 * ����:
 * �����޵ļ��㼶����,js���ᵼ�½������ȷ,�����Ƿǳ��ǳ��ӽ���ȷ�Ľ��,ֻ��Ҫ���ʵ��Ľ�ȡ���Ⱥ���Ӧ���������λ��
 * jsͬ�¶�js float���㲻׼ȷ�ĵ��ǺͿֻ��ǲ�����ģ��Լ�ȥ��д������дҲ�ǲ���ѧ�ģ�
 *  �Ӹ��˷�������Ĵ����Ƕ���ģ���������Ŭ���ͷ����ǿ϶��ģ�
 * @notice ���Ͻ����˹۵㣬���ο���ϣ���������棻���в��ף���лָ����
 * @time 2011-01-12;
 */
//���������������õ���ȷ�ĳ������
//˵����javascript�ĳ�����������������������������ʱ���Ƚ����ԡ�����������ؽ�Ϊ��ȷ�ĳ��������
//���ã�accDiv(arg1,arg2)
//����ֵ��arg1����arg2�ľ�ȷ���
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
//��Number��������һ��div�����������������ӷ��㡣
Number.prototype.div = function (arg){
   return accDiv(this, arg);
};

//�˷������������õ���ȷ�ĳ˷����
//˵����javascript�ĳ˷������������������������˵�ʱ���Ƚ����ԡ�����������ؽ�Ϊ��ȷ�ĳ˷������
//���ã�accMul(arg1,arg2)
//����ֵ��arg1����arg2�ľ�ȷ���
function accMul(arg1,arg2)
{
   var m=0,s1=arg1.toString(),s2=arg2.toString();
   try{m+=s1.split(".")[1].length}catch(e){}
   try{m+=s2.split(".")[1].length}catch(e){}
   return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
}
//��Number��������һ��mul�����������������ӷ��㡣
Number.prototype.mul = function (arg){
   return accMul(arg, this);
};

//�ӷ������������õ���ȷ�ļӷ����
//˵����javascript�ļӷ������������������������ӵ�ʱ���Ƚ����ԡ�����������ؽ�Ϊ��ȷ�ļӷ������
//���ã�accAdd(arg1,arg2)
//����ֵ��arg1����arg2�ľ�ȷ���
function accAdd(arg1,arg2){
   var r1,r2,m;
   try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
   try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
   m=Math.pow(10,Math.max(r1,r2));
   return (arg1*m+arg2*m)/m;
}
//��Number��������һ��add�����������������ӷ��㡣
Number.prototype.add = function (arg){
   return accAdd(arg,this);
}

//��������
function accSub(arg1,arg2){
    var r1,r2,m,n;
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2));
    //last modify by deeka
    //��̬���ƾ��ȳ���
    n=(r1>=r2)?r1:r2;
    return ((arg2*m-arg1*m)/m).toFixed(n);
}
///��number������һ��sub�����������������ӷ���
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