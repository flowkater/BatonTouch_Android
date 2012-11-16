package com.batontouch.profile;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.batontouch.R;

public class ProfileActivity_Manage_Gifticon extends Activity{
	ListView listView;
	ArrayList<MyItem4> arItem;

	private ArrayList<String> items;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_manage_gifticon);
		
		arItem = new ArrayList<MyItem4>();
		MyItem4 mi;
		mi = new MyItem4(R.drawable.starbucks, "스타벅스");
		arItem.add(mi);
		mi = new MyItem4(R.drawable.starbucks, "스타벅스");
		arItem.add(mi);
		mi = new MyItem4(R.drawable.starbucks, "스타벅스");
		arItem.add(mi);
		mi = new MyItem4(R.drawable.starbucks, "스타벅스");
		arItem.add(mi);
		mi = new MyItem4(R.drawable.starbucks, "스타벅스");
		arItem.add(mi);
		mi = new MyItem4(R.drawable.starbucks, "스타벅스");
		arItem.add(mi);
		mi = new MyItem4(R.drawable.starbucks, "스타벅스");
		arItem.add(mi);



		MyListAdapter MyAdapter = new MyListAdapter(this, R.layout.featured_adapter_gift, arItem);

		listView = (ListView) findViewById(R.id.gifticonList);

		listView.setAdapter(MyAdapter);
	}

}
class MyItem4 {
	MyItem4(int aIcon, String aName) {
		Icon = aIcon;
		Name = aName;
	}

	int Icon;
	String Name;
}