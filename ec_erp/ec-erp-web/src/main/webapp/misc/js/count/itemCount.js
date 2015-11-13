window.onload=function(){
	if(symbol==1){
		$("#subutton").attr("disabled","disabled");
		$("#skudetailspan").css("display","block");
		appearDiv();
	}
}
function uploadChange(){
	$("#subutton").removeAttr("disabled");
}
function addChild(value){
	if(value==0){
		$("#promotionChildType").html("<option value=0>-所有子促销方式-</option>");
	}
	if(value==1){
		var g="<option value=11>普通促销</option>" +
				   "<option value=12>秒杀（限时抢购）</option>" +
				   "<option value=13>手机秒杀</option>" +
		           "<option value=14>秒杀（走抢购）</option>" ;
		
		$("#promotionChildType").html("<option value=0>-所有子促销方式-</option>");
		$("#promotionChildType").append(g);
	}
	if(value==2){
		var g="<option value=21>赠品</option>" +
                   "<option value=22>附件</option>" ;
		$("#promotionChildType").html("<option value=0>-所有子促销方式-</option>");
         $("#promotionChildType").append(g);
	}
	if(value==3){
		var g="<option value=31>满减</option>" +
				   "<option value=32>每满减</option>" +
				   "<option value=33>百分比</option>" +
		           "<option value=34>阶梯满减</option>" ;
		$("#promotionChildType").html("<option value=0>-所有子促销方式-</option>");
		$("#promotionChildType").append(g);
	}
	if(value==4){
		var g="<option value=51>满赠</option>" +
		           "<option value=52>阶梯满赠</option>" +
		           "<option value=53>加价购</option>" +
                   "<option value=54>阶梯加价购</option>" ;
		$("#promotionChildType").html("<option value=0>-所有子促销方式-</option>");
        $("#promotionChildType").append(g);
	}
	if(value==5){
		var g="<option value=41>M件减N件</option>" +
                   "<option value=42>M元买N件</option>" +
                   "<option value=43>满M件赠N件</option>" +
                   "<option value=44>满M件N折</option>" ;
		$("#promotionChildType").html("<option value=0>-所有子促销方式-</option>");
        $("#promotionChildType").append(g);
	}
	if(value==6){
		var g="<option value=61>普通套装</option>" +
		           "<option value=62>合约计划任务</option>" +
                   "<option value=63>裸机绑合约</option>" ;
		$("#promotionChildType").html("<option value=0>-所有子促销方式-</option>");
         $("#promotionChildType").append(g);
	}
	if(value==7){
		var g="<option value=71>减钱</option>" +
		           "<option value=72>打折</option>" ;
		$("#promotionChildType").html("<option value=0>-所有子促销方式-</option>");
		$("#promotionChildType").append(g);
	}
	if(value==8){
		var g="<option value=81>打折</option>" ;
		$("#promotionChildType").html("<option value=0>-所有子促销方式-</option>");
         $("#promotionChildType").append(g);
	}
	document.getElementById("promotionChildType").removeAttribute("disabled");
}
//-----------------------------统计入口方法-------------------------------------------------------------------------------
function ajaxCount(timeType){
	document.getElementById('timeType').value =timeType;
	if(!checkParam(timeType)){
		return false;
	}
		var url="/itemcount/ajaxcount";
		var data = $('#itemCountForm').serialize();
		$.ajax({
			type : "get",
			url : url,
			data : data,
			cache:false,
			dataType : "json",
			error : function(XMLHttpRequest, textStatus, errorThrow) {
				 alert("系统错误");
			},
			success : function(json) {
			showChart(json);//根据返回的结果统计
			}
		});
	}
function checkParam(timeType){//检查参数并控制显隐
	var promotionType=document.getElementById("promotionType").value;
	var promotionChildType=document.getElementById("promotionChildType").value;
	var skuid=document.getElementById("skuid").value;
	var startTime=document.getElementById("startTime").value;
	var endTime=document.getElementById("endTime").value;
	if((skuid==null||skuid=="")&&skuids==""){
		alert("请输入skuid或批量导入skuid");
		return false;
	}
	if(timeType==0){//如果没有根据固定时间段，0控件选，1 24小时 ；2 一周 ； 3 一月 ；4一季度
	if(startTime==""){
		alert("请选择开始时间");
		return false;
	}
	if(endTime==""){
		alert("请选择结束时间");
		return false;
	}
}
	if(promotionType==0){//全部促销方式，显示饼图，显示div2
		showDiv(1,1,0);
	}else{//选择了某个促销方式
		if(promotionChildType==0){//所有子促销方式，显示子饼图和div3
			showDiv(1,0,1);
		}else{//选择了某个子促销方式，只显示div3
			showDiv(0,0,1);
		}
	}
		return true;
}
function showDiv(a,b,c){
	if(a==1){
		$("#container").css("display","block"); 
	}else{
		$("#container").css("display","none"); 
	}
	if(b==1){
		$("#container2").css("display","block"); 
	}else{
		$("#container2").css("display","none"); 
	}
	if(c==1){
		$("#container3").css("display","block"); 
	}else{
		$("#container3").css("display","none"); 
	}
}
function showChart(json){
	var promotionType=document.getElementById("promotionType").value;//判断显示结果
	var promotionChildType=document.getElementById("promotionChildType").value;
	if(promotionType==0){//全部促销方式，显示饼图，显示div2
		showResult1(json);
	}else{//选择了某个促销方式
		if(promotionChildType==0){//所有子促销方式，显示子饼图和div3
			showResult2(json);
		}else{//选择了某个子促销方式，只显示div3
			showResult3(json);
		}
	}
}
function showResult1(json){//只给饼图和div2赋值
	var data=new Array();
	var divtext="";
	var totalNum=0;//总数量
	var totalTokenNum=0;
	var totalMemNum=0;
	for(var i=0;i<json.promotionResult.length;i++){//遍历结果，拼接html
		totalNum=totalNum+json.promotionResult[i].num;
		totalTokenNum=totalTokenNum+json.promotionResult[i].tokenLimit;
		totalMemNum=totalMemNum+json.promotionResult[i].memberLimit;
		divtext=divtext+'<tr><td>'+json.promotionResult[i].name+'</td><td>'+json.promotionResult[i].num+'</td><td>'+json.promotionResult[i].rate+'</td><td>'+json.promotionResult[i].memberLimit+'</td><td>'+json.promotionResult[i].tokenLimit+'</td></tr>';
		var datac=new Array();
		datac.push(json.promotionResult[i].name);
		datac.push(json.promotionResult[i].num);
		data.push(datac);
	}
	$('#numtable').html('<tr><td></td><td>促销信息数量</td><td>占比率</td><td>有会员限制</td><td>有令牌限制</td></tr>');
	$('#numtable').append(divtext);
	$('#numtable').append('<tr><td>合计</td><td>'+totalNum+'</td><td>'+'100%</td><td>'+totalMemNum+'</td><td>'+totalTokenNum+'</td></tr>');
	showReport(data);
}
function showResult2(json){//饼图和div3赋值
	var data=new Array();
	var divtext="";
	var litext="";
	for(var i=0;i<json.promotionResult.length;i++){//遍历结果，拼接html
		divtext=divtext+'<tr><td>'+json.promotionResult[i].name+'</td><td>'+json.promotionResult[i].num+'</td><td>'+json.promotionResult[i].rate+'</td><td>'+json.promotionResult[i].memberLimit+'</td><td>'+json.promotionResult[i].tokenLimit+'</td></tr>';
		litext=litext+'<li >'+(i+1)+'，'+json.promotionResult[i].name+'：'+json.promotionResult[i].num+'个</li>';
		var datac=new Array();
		datac.push(json.promotionResult[i].name);
		datac.push(json.promotionResult[i].num);
		data.push(datac);
	}
	var areatext="";
	for(var i=0;i<json.areaInfo.length;i++){//遍历地区结果，拼接html
		areatext=areatext+'<tr>'+'<td>'+json.areaInfo[i].name+'</td><td>'+json.areaInfo[i].num+'</td>'+'<td>'+json.areaInfo[i].name+'</td><td>'+json.areaInfo[i].num+'</td>'+'<td>'+json.areaInfo[i].name+'</td><td>'+json.areaInfo[i].num+'</td>'+'</tr>';
	}
	$('#areatable').html('');
	$('#areatable').append(areatext);
	
	$('#childli').html('');
	$('#childli').append(litext);
	showReport(data);
}
function showResult3(json){//只给div3赋值
	var areatext="";
	for(var i=0;i<json.areaInfo.length;i++){//遍历地区结果，拼接html
		areatext=areatext+'<tr>'+'<td>'+json.areaInfo[i].name+'</td><td>'+json.areaInfo[i].num+'</td>'+'<td>'+json.areaInfo[i].name+'</td><td>'+json.areaInfo[i].num+'</td>'+'<td>'+json.areaInfo[i].name+'</td><td>'+json.areaInfo[i].num+'</td>'+'</tr>';
	}
	$('#areatable').html('');
	$('#areatable').append(areatext);
	//var litext="";
	//for(var i=0;i<json.promotionResult.length;i++){//遍历结果，拼接html
		//litext=litext+'<li >'+(i+1)+'，'+json.promotionResult[i].name+'：'+json.promotionResult[i].num+'个</li>';
	//}
	$('#childli').html('');
	//$('#childli').append(litext);
	
}
function showReport(data){
	$('#container').highcharts({
        chart: {
            type: 'pie',
            options3d: {
				enabled: true,
                alpha: 45,
                beta: 0
            }
        },
        title: {
            text: '促销结果图'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                depth: 35,
                dataLabels: {
                    enabled: true,
                    format: '{point.name}'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Browser share',
            data:data
        }]
    });
}
function appearDiv(){//用于控制弹出层
	document.getElementById('popDiv').style.display='block';
	document.getElementById('popIframe').style.display='block';
	document.getElementById('bg').style.display='block';
	}
function closeDiv(){
	document.getElementById('popDiv').style.display='none';
	document.getElementById('bg').style.display='none';
	document.getElementById('popIframe').style.display='none';

	}
function joinCount(){
	document.getElementById('excelForm').submit();
}