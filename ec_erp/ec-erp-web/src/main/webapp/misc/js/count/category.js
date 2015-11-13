function initcat(cat1Id,level){
	var url="/category/getchild";
	$.ajax({
		type : "get",
		url : url,
		data : 'catId='+cat1Id,
		cache:false,
		dataType : "json",
		error : function(XMLHttpRequest, textStatus, errorThrow) {
			 alert("系统错误");
		},
		success : function(json) {
			if(level==2){
				showCat2(json);
			}else{
				showCat3(json);
			}
		}
	});
}
function showCat2(value){
	$("#cat2Id").html("");
	$("#cat3Id").html("<option value=0>-3级分类-</option>");
	var g="";
	for(var i=0;i<value.length;i++){
		g=g+'<option value='+value[i].id+'>'+value[i].name+'</option>';
	}
	$("#cat2Id").html("<option value=0>-2级分类-</option>");
	$("#cat2Id").append(g);
}
function showCat3(value){
	var g="";
	for(var i=0;i<value.length;i++){
		g=g+'<option value='+value[i].id+'>'+value[i].name+'</option>';
	}
	$("#cat3Id").html("<option value=0>-3级分类-</option>");
	$("#cat3Id").append(g);
}