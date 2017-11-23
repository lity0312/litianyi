package com.neuedu.util;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {
	
	public static String covertEmpty(Object obj) {
		if(null==obj||String.valueOf(obj).equalsIgnoreCase("null")) {
			return "";
		}else {
			return String.valueOf(obj);
		}
	}
	
	public static String parseNull(String param) {
		if(null==param||param.equalsIgnoreCase("null")) {
			return "";
		}else {
			return param;
		}
	}
	
	public static String parseNull(HttpServletRequest request,String name) {
		return parseNull(request.getParameter(name));
	}
	
	public static String[] parseNullArray(String[] param) {
		if(null==param) {
			return new String[] {};
		}else {
			return param;
		}
		
	}
	
	public static String[] parseNullArray(HttpServletRequest req,String name) {
		return parseNullArray(req.getParameterValues(name));
	}

}
