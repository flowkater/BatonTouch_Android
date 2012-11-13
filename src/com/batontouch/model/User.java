package com.batontouch.model;

public class User {
	private String id;
	private String name;
	private String introduce;
	private String tradestat_id;
	private boolean client_status; 
	
	public boolean isClient_status() {
		return client_status;
	}
	public void setClient_status(boolean client_status) {
		this.client_status = client_status;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTradestat_id() {
		return tradestat_id;
	}
	public void setTradestat_id(String tradestat_id) {
		this.tradestat_id = tradestat_id;
	}
	
	
}
