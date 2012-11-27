package com.batontouch.model;

import java.util.ArrayList;

public class User {
	private String id;
	private String name;
	private String introduce;
	private String tradestat_id;
	private String resttime;
	private String profile_image;
	private String phone;
	private boolean facebook;
	private boolean client_status; 
	private boolean phone_auth;
	private ArrayList<Giftcon> giftcons;
	
	public ArrayList<Giftcon> getGiftcons() {
		return giftcons;
	}
	public void setGiftcons(ArrayList<Giftcon> giftcons) {
		this.giftcons = giftcons;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isPhone_auth() {
		return phone_auth;
	}
	public void setPhone_auth(boolean phone_auth) {
		this.phone_auth = phone_auth;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	public boolean isFacebook() {
		return facebook;
	}
	public void setFacebook(boolean facebook) {
		this.facebook = facebook;
	}
	public String getResttime() {
		return resttime;
	}
	public void setResttime(String resttime) {
		this.resttime = resttime;
	}
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
