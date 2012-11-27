package com.batontouch.utils;

public class Global {
	public static String gcm_regid = "";
	// public static String ServerUrl = "http://192.168.0.154:3000/api/";
	// public static String ServerUrl = "http://14.63.222.63:3333/api/";
	// public static String ServerOriginalUrl = "http://14.63.222.63:3333";
	public static String ServerUrl = "http://192.168.0.2:3000/api/";
	public static String ServerOriginalUrl = "http://192.168.0.2:3000";
	public static String FacebookSendToken = ServerUrl
			+ "check_mobile_login.json?token=";
	// == API Versioning

	public static final String SENDER_ID = "128292354542";

	public static String version = "v1"; // Accept
	public static String Acceptversion = "application/vnd.batontouch.v1";

	public static String AuthorizationToken = "Token token=\"5314dce6e025dae96e588d78cf92cbe8\""; // Authorization

	public static String userJudge(int taskStatus) {
		/*
		 * # taskStatus # 0 : 대기중 # 1 : 진행중 # 2 : 클라이언트 완료 # 3 : Task 완료 -- 2, 3
		 * 똑같은 완료 # -1 : 만료 # etc : Error
		 */
		if (taskStatus == 0) {
			return "대기중";
		} else if (taskStatus == 1) {
			return "진행중";
		} else if (taskStatus == 2) {
			return "진행중";
		} else if (taskStatus == 3) {
			return "완료";
		} else if (taskStatus == 4) {
			return "완료";
		} else if (taskStatus == -1) {
			return "만료";
		} else {
			return "Error";
		}
	}

	public static int randomRange(int n1, int n2) {
		return (int) (Math.random() * (n2 - n1 + 1)) + n1;
	}

}
