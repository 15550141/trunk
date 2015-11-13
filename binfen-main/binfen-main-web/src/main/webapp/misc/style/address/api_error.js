var result = $("#api_msg_type").val();
var msg = $("#api_msg").val();
$(function(){
    if(result=='error' || result=='confirm'){
    	switch(result){
    		case 'error':
    			MessageBox.error(msg,msg_goto);
    			break;
    		case 'confirm':
    			MessageBox.confirm(msg);
    			break;
    		default:
    			break;
    	}        	
    }	
});

function msg_goto(){
    window.location.href=$("#api_goto_url").val();
}
    
function isMobel(value)
{
    if(/^1\d{10}$/g.test(value)){
        return true;
    }else{
        return false;
    }
}
 