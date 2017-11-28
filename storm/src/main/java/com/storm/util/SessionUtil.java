package com.storm.util;

import javax.servlet.http.HttpSession;

public class SessionUtil {
	public static boolean isLogin(HttpSession session) {
		String	id = (String) session.getAttribute("UID");

		boolean	isNull = StringUtil.isNull(id);
		return !isNull;
	}
	
	public static String getLoginId(HttpSession session) {
		return (String) session.getAttribute("UID");
	}
}









