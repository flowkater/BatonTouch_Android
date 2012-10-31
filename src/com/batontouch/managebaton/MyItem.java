package com.batontouch.managebaton;

class MyItem {
	MyItem(int aIcon, String aName, int price, String date, String status) {
		Icon = aIcon;
		Price = price;
		Name = aName;
		Status = status;
		Date = date;
	}

	int Icon;
	int Price;

	
	String Name;
	String Status;
	String Date;
}