package com.ec.api.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class BFUtils {

	public static String sha1Hex(String data){
		return DigestUtils.shaHex(data);
	}
	
	
}