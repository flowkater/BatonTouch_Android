package com.batontouch.model;

import java.util.ArrayList;

import com.batontouch.utils.Global;

public class Task {
	private String id;
	private String category_id;
	private String name;
	private String day;
	private int status;
	private String imageProfile;
	private String description;
	private String fromloc;
	private String toloc;
	private String calldate;
	private String enddate;
	private int client_size;
	private ArrayList<User> users;
	private boolean current_user;
	private boolean client_status;
	private boolean review_toclient;
	private boolean review_touser;
	private User user;
	private User client;
	private Giftcon giftcon;
	private Review review;
	
	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public Giftcon getGiftcon() {
		return giftcon;
	}

	public void setGiftcon(Giftcon giftcon) {
		this.giftcon = giftcon;
	}

	public boolean isReview_toclient() {
		return review_toclient;
	}

	public void setReview_toclient(boolean review_toclient) {
		this.review_toclient = review_toclient;
	}

	public boolean isReview_touser() {
		return review_touser;
	}

	public void setReview_touser(boolean review_touser) {
		this.review_touser = review_touser;
	}

	public String getClient_size_status(int status){
		if (status == 0) {
			return this.client_size + "명  " + Global.userJudge(status);
		} else {
			return Global.userJudge(status)+"";
		}
	}
	
	public boolean isClient_status() {
		return client_status;
	}

	public void setClient_status(boolean client_status) {
		this.client_status = client_status;
	}

	public boolean isCurrent_user() {
		return current_user;
	}

	public void setCurrent_user(boolean current_user) {
		this.current_user = current_user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public String getCalldate() {
		return calldate;
	}

	public void setCalldate(String calldate) {
		this.calldate = calldate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getFromloc() {
		return fromloc;
	}

	public void setFromloc(String fromloc) {
		this.fromloc = fromloc;
	}

	public String getToloc() {
		return toloc;
	}

	public void setToloc(String toloc) {
		this.toloc = toloc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageProfile() {
		return imageProfile;
	}

	public void setImageProfile(String imageProfile) {
		this.imageProfile = imageProfile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
