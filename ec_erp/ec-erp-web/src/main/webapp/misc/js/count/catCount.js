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
//--------------------------------------------------------------------------------------------------------------
function ajaxCount(timeType){
	document.getElementById('timeType').value =timeType;
	if(!checkParam(timeType)){
		return false;
	}
		var url="/catcount/ajaxcount";
		var data = $('#catCountForm').serialize();
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
function showChart(json){
	var promotionType=document.getElementById("promotionType").value;//判断显示结果
	var promotionChildType=document.getElementById("promotionChildType").value;
	if(promotionType==0){//全部促销方式，显示饼图，显示div2
		showResult1(json);
	}else{//选择了某个促销方式
		if(promotionChildType==0){//所有子促销方式，显示子饼图和div4
			showResult2(json);
		}else{//选择了某个子促销方式，只显示div4
			showResult3(json);
		}
	}
}
function showResult1(json){
	//------------------------------------------饼图-----------------------------------------------------
	var data=new Array();
	var divtext="";
	var totalNum=0;//总数量
	var totalTokenNum=0;
	var totalMemNum=0;
	for(var i=0;i<json.promotionResult.length;i++){//遍历结果，拼接html
		totalNum=totalNum+json.promotionResult[i].num;
		totalTokenNum=totalTokenNum+json.promotionResult[i].tokenLimit;
		totalMemNum=totalMemNum+json.promotionResult[i].memberLimit;
		divtext=divtext+'<tr onclick=appearDiv();><td>'+json.promotionResult[i].name+'</td><td>'+json.promotionResult[i].num+'</td><td>'+json.promotionResult[i].rate+'</td><td>'+json.promotionResult[i].memberLimit+'</td><td>'+json.promotionResult[i].tokenLimit+'</td></tr>';
		var datac=new Array();
		datac.push(json.promotionResult[i].name);
		datac.push(json.promotionResult[i].num);
		data.push(datac);
	}
	$('#numtable').html('<tr><td></td><td>促销信息数量</td><td>占比率</td><td>有会员限制</td><td>有令牌限制</td></tr>');
	$('#numtable').append(divtext);
	$('#numtable').append('<tr><td>合计</td><td>'+totalNum+'</td><td>'+'100%</td><td>'+totalMemNum+'</td><td>'+totalTokenNum+'</td></tr>');
	$("#numtable").css("display","block");
	showPie(data);
	 //------------------------------------------------------------柱状图--------------------------------------------------------------
	 var nameData=new Array();
	 var saleData=new Array();
	 for(var i=0;i<json.catSaleResult.length;i++){
		 nameData.push(json.catSaleResult[i].catName);
		 saleData.push(json.catSaleResult[i].sale);
	 }
	showCollum(nameData,saleData);
}


function showResult2(json){
	//------------------------------------------饼图-----------------------------------------------------
	var data=new Array();
	var litext="";
	for(var i=0;i<json.promotionResult.length;i++){//遍历结果，拼接html
		litext=litext+'<li >'+(i+1)+'，'+json.promotionResult[i].name+'：'+json.promotionResult[i].num+'个</li>';
		var datac=new Array();
		datac.push(json.promotionResult[i].name);
		datac.push(json.promotionResult[i].num);
		data.push(datac);
	}
	$('#childli').html('');
	$('#childli').append(litext);
	var areatext="";
	for(var i=0;i<json.areaInfo.length;i++){//遍历地区结果，拼接html
		areatext=areatext+'<tr>'+'<td>'+json.areaInfo[i].name+'</td><td>'+json.areaInfo[i].num+'</td>'+'<td>'+json.areaInfo[i].name+'</td><td>'+json.areaInfo[i].num+'</td>'+'<td>'+json.areaInfo[i].name+'</td><td>'+json.areaInfo[i].num+'</td>'+'</tr>';
	}
	$('#areatable').html('');
	$('#areatable').append(areatext);
	
	showPie(data);//饼图
	 var nameData=new Array();
	 var saleData=new Array();
	 for(var i=0;i<json.catSaleResult.length;i++){
		 nameData.push(json.catSaleResult[i].catName);
		 saleData.push(json.catSaleResult[i].sale);
	 }
	showCollum(nameData,saleData);//柱状图
}
function showResult3(json){
	var areatext="";
	for(var i=0;i<json.areaInfo.length;i++){//遍历地区结果，拼接html
		areatext=areatext+'<tr>'+'<td>'+json.areaInfo[i].name+'</td><td>'+json.areaInfo[i].num+'</td>'+'<td>'+json.areaInfo[i].name+'</td><td>'+json.areaInfo[i].num+'</td>'+'<td>'+json.areaInfo[i].name+'</td><td>'+json.areaInfo[i].num+'</td>'+'</tr>';
	}
	$('#areatable').html('');
	$('#areatable').append(areatext);
	$('#childli').html('');
	 for(var i=0;i<json.catSaleResult.length;i++){
		 nameData.push(json.catSaleResult[i].catName);
		 saleData.push(json.catSaleResult[i].sale);
	 }
	showCollum(nameData,saleData);//柱状图
}

function showPie(data){//饼图
	$('#container1').highcharts({
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
function showCollum(nameData,data){//柱状图
	 $('#container3').highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: '各品类促销信息数量'
	        },
	        subtitle: {
	            text: ''
	        },
	        xAxis: {
	            categories: nameData
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: '销售额 (万元)'
	            }
	        },
	        tooltip: {
	            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	                '<td style="padding:0"><b>{point.y:.1f} 万元</b></td></tr>',
	            footerFormat: '</table>',
	            shared: true,
	            useHTML: true
	        },
	        plotOptions: {
	            column: {
	                pointPadding: 0.2,
	                borderWidth: 0
	            }
	        },
	        series: [{
	            name: '销售额',
	            data:data
	        }]
	    });
}
function checkParam(timeType){//检查参数
	var startTime=document.getElementById("startTime").value;
	var endTime=document.getElementById("endTime").value;
	var promotionType=document.getElementById("promotionType").value;//判断显示结果
	var promotionChildType=document.getElementById("promotionChildType").value;
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
function showDiv(a,b,c){
	if(a==1){
		$("#container1").css("display","block"); 
	}else{
		$("#container1").css("display","none"); 
	}
	if(b==1){
		$("#container2").css("display","block"); 
	}else{
		$("#container2").css("display","none"); 
	}
	if(c==1){
		$("#container4").css("display","block"); 
	}else{
		$("#container4").css("display","none"); 
	}
}