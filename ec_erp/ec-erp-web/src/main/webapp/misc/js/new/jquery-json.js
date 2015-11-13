/**
 * 将数字型字符串转化为指定小数位的数值
 * @param src 源字符串
 * @param len 小数位数
 */
jQuery.extend({  
	 toFixed:function(src,len){
		if(null==src){
			return "";
		}
		if(0==src){
			return new Number(src).toFixed(len);
		}
		return src.toFixed(len);
	}
});
/**
 * 由于JAVA后端序列化JSON数据时，若属性为空，JSON对象中值为Null，与展示的值不符，
 * 故使用此方法改变展示默认值
 */
jQuery.extend({
	eliminateNull:function(object,replaceValue){
		if(null==object){
			return null;
		}
		var type = typeof object;
		if ('object' == type) {        
			if (Array == object.constructor) 
				type = 'array';        
			else if (RegExp == object.constructor) 
				type = 'regexp';        
			else 
				type = 'object';     
		}
		switch (type) { 
			case 'object':   
				for (var property in object) { 
					var value=object[property];
					if(value==null){
						object[property]=replaceValue;
					}
				}
		}
		return object;
		
	}

});
jQuery.extend({    
	/** * @see 将json字符串转换为对象 
	 * 
	 * @param json字符串
	 * @return 返回object,array,string等对象 */   
	evalJSON: function(strJson) {     
		return eval("(" + strJson + ")");    } }); 
	/***
	 *  将javascript数据类型转换为json字符串的方法。
	 *@public*
	 * @param  {object}  需转换为json字符串的对象, 
	 * 一般为Json 【支持object,array,string,function,number,boolean,regexp *】
	 * @return 返回json字符串**/
jQuery.extend({    
	toJSONString: function(object) {
		if(null==object){
			return null;
		}
		var type = typeof object; 
		if ('object' == type) {        
			if (Array == object.constructor) 
				type = 'array';        
			else if (RegExp == object.constructor) 
				type = 'regexp';        
			else 
				type = 'object';     
		}      
			switch (type) {      
				case 'undefined':      
				case 'unknown':        
					return;   break;      
				case 'function':      
				case 'boolean':      
				case 'regexp':        
					return object.toString();    break;      
				case 'number':       
					return isFinite(object) ? object.toString() : 'null';        break;     
				case 'string':       
					return '"' + object.replace(/(\\|\")/g, "\\$1").replace(/\n|\r|\t/g, function() { 
						var a = arguments[0];          
						return (a == '\n') ? '\\n': (a == '\r') ? '\\r': (a == '\t') ? '\\t': "" ;       
						}) + '"';        break;      
				case 'object':        
					if (object === null) return 'null';        
					var results = [];        
					for (var property in object) { 
						var value = jQuery.toJSONString(object[property]);          
						if (value !== undefined) 
							results.push(jQuery.toJSONString(property) + ':' + value);        
						}        
					return '{' + results.join(',') + '}';        break;     
				case 'array':        
					var results = [];        
					for (var i = 0; i < object.length; i++) {          
						var value = jQuery.toJSONString(object[i]);         
						if (value !== undefined) results.push(value);        }       
					return '[' + results.join(',') + ']';        break;      
					}    
			} 
});