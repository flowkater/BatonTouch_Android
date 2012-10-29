package com.batontouch.managebaton;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.batontouch.R;

public class BatonManageActivity extends Activity {
	ListView listView, listView2;
	ArrayList<MyItem> arItem;

	private ArrayList<String> items;


	Button btn, askedButton, myTaskBtn;

	RelativeLayout relative;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonmanage);
		relative = (RelativeLayout) findViewById(R.id.featured_adapter);

		arItem = new ArrayList<MyItem>();
		MyItem mi;
		mi = new MyItem(R.drawable.ic_launcher, "식사 배달.");
		arItem.add(mi);
		mi = new MyItem(R.drawable.ic_launcher, "떡뽁이 사다주세요.");
		arItem.add(mi);
		mi = new MyItem(R.drawable.ic_launcher, "세탁물 맡겨주세요.");
		arItem.add(mi);
		mi = new MyItem(R.drawable.ic_launcher, "밥 사다 주세요~");
		arItem.add(mi);

		MyListAdapter MyAdapter = new MyListAdapter(this,
				R.layout.featured_adapter, arItem);

		listView = (ListView) findViewById(R.id.listView);
		listView2 = (ListView) findViewById(R.id.listView2);
		
		LayoutInflater inflator = getLayoutInflater();

		// listView.addHeaderView(linear);

		listView.setAdapter(MyAdapter);
		listView2.setAdapter(MyAdapter);
		
		
		askedButton = (Button) findViewById(R.id.askedTaskBtn);
		myTaskBtn = (Button) findViewById(R.id.myTaskBtn);
	}
	
	

	public void mOnClick(View v) {

		// btn = (Button) findViewById(R.id.button);
	

		switch (v.getId()) {

		// case R.id.featured_adapter:
		//
		// // Toast.makeText(getApplicationContext(), "featured_adapter",
		// 3000).show();
		// Intent intent = new Intent(getApplicationContext(),
		// ItemExample.class);
		//
		// startActivity(intent);

		case R.id.askedTaskBtn:

			// Toast.makeText(getApplicationContext(), "btnCollections",
			// 3000).show();
			// #8b8989
			myTaskBtn.setBackgroundColor(Color.BLACK);
			askedButton.setBackgroundColor(Color.rgb(89, 89, 89));
			listView2.setVisibility(View.INVISIBLE);
			listView.setVisibility(View.VISIBLE);
			break;

		case R.id.myTaskBtn:

			// Toast.makeText(getApplicationContext(), "btnVideos",
			// 3000).show();

			askedButton.setBackgroundColor(Color.BLACK);
			myTaskBtn.setBackgroundColor(Color.rgb(89, 89, 89));
			listView.setVisibility(View.INVISIBLE);
			listView2.setVisibility(View.VISIBLE);
			break;

		}

	}

}

class MyItem {
	MyItem(int aIcon, String aName) {
		Icon = aIcon;
		Name = aName;
	}

	int Icon;
	String Name;
}
