package com.batontouch.main;

public class ParsedLoginDataSet {
	private String login = null;
	private String message = null;

	public String getExtractedString() {
		return login;
	}

	public void setExtractedString(String extractedString) {
		this.login = extractedString;
	}

	public void setMessage(String extractedString) {
		this.message = extractedString;
	}

	public String getMessage() {
		return this.message;
	}
}
