package com.rp;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class LogUtil {
	public static void LogRequestParams(HttpServletRequest request){
		Enumeration<String> em = request.getParameterNames();
		
		System.out.println("========================================");
		while( em.hasMoreElements()) {
			String param_name = em.nextElement();
			String param_value = request.getParameter(param_name);
			System.out.println(param_name + ":" + param_value);
		}
		System.out.println("========================================");
	}
}
