package com.batontouch.utils;

public class Global {
	// public static String ServerUrl = "http://192.168.0.154:3000/api/";
	// public static String ServerUrl = "http://14.63.222.63:3333//api/";
	public static String ServerUrl = "http://192.168.0.2:3000/api/";
	public static String FacebookSendToken = ServerUrl
			+ "check_mobile_login.json?token=";
	// == API Versioning
	public static String version = "v1"; // Accept
	public static String Acceptversion = "application/vnd.batontouch.v1";
	public static String AuthorizationToken = "Token token=\"1ada089cf516f14ca7802d227799699a\""; // Authorization

	public static String clientJudge(int taskStatus, boolean tradeStatus) {
		/*
		 * tradeStatus => Client && taskStatus => Deal t f # taskStatus # 0 :
		 * 대기중 # 1 : 진행중 # 2 : 클라이언트 완료 # 3 : Task 완료 -- 2, 3 똑같은 완료 # -1 : 만료 #
		 * etc : Error
		 * 
		 * # tradeStatus # false : non select # true : select
		 */
		if (taskStatus == 0 && !tradeStatus) {
			return "대기중";
		} else if (taskStatus == 1 && tradeStatus) {
			return "선택되었습니다. 일을 진행해주세요.";
		} else if (taskStatus == 2 && tradeStatus) {
			return "유저의 확인을 기다리고 있습니다.";
		} else if (taskStatus == 3 && tradeStatus) {
			return "일을 완료하였습니다. 리뷰를 확인하세요!";
		} else if (taskStatus == -1 && tradeStatus) {
			return "만료처리.";
		} else {
			return "다음 기회에.";
		}
	}

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
		} else if (taskStatus == -1) {
			return "만료";
		} else {
			return "Error";
		}
	}
}
