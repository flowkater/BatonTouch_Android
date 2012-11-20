package com.batontouch.model;

import java.util.ArrayList;

public class Store {
	private String id;
	private String name;
	private String image;
	private ArrayList<Giftitem> giftitems;
	
	public ArrayList<Giftitem> getGiftitems() {
		return giftitems;
	}
	public void setGiftitems(ArrayList<Giftitem> giftitems) {
		this.giftitems = giftitems;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
