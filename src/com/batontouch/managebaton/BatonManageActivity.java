package com.batontouch.managebaton;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.batontouch.R;

public class BatonManageActivity extends Activity {
	ListView listView;
	ArrayList<MyItem> arItem;

	private ArrayList<String> items;

	VideoView video;
	Button btn;

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
	

		MyListAdapter MyAdapter = new MyListAdapter(this, R.layout.featured_adapter, arItem);

		listView = (ListView) findViewById(R.id.listView);

		LayoutInflater inflator = getLayoutInflater();

		// listView.addHeaderView(linear);

		listView.setAdapter(MyAdapter);

	}

	public void mOnClick(View v) {

		// btn = (Button) findViewById(R.id.button);
		video = (VideoView) findViewById(R.id.videoView);

		switch (v.getId()) {

		// case R.id.featured_adapter:
		//
		// // Toast.makeText(getApplicationContext(), "featured_adapter",
		// 3000).show();
		// Intent intent = new Intent(getApplicationContext(),
		// ItemExample.class);
		//
		// startActivity(intent);

		case R.id.btnCollections:

			// Toast.makeText(getApplicationContext(), "btnCollections",
			// 3000).show();
			video.setVisibility(View.INVISIBLE);
			listView.setVisibility(View.VISIBLE);
			break;

		case R.id.btnVideos:

			// Toast.makeText(getApplicationContext(), "btnVideos",
			// 3000).show();
			listView.setVisibility(View.INVISIBLE);
			video.setVisibility(View.VISIBLE);
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
