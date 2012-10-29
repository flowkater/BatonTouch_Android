package com.batontouch.homeindex;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.batontouch.R;

public class BatonIndexActivity extends Activity {

	private Button mapBtn, distanceBtn, priceBtn;

	ListView listView;
	ArrayList<MyItem2> arItem;

	private ArrayList<String> items;

	VideoView video;
	Button btn;

	RelativeLayout relative;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonindex);

		mapBtn = (Button) findViewById(R.id.mapBtn);
		distanceBtn = (Button) findViewById(R.id.distanceBtn);
		priceBtn = (Button) findViewById(R.id.priceBtn);

		arItem = new ArrayList<MyItem2>();
		MyItem2 mi;
		mi = new MyItem2(R.drawable.junghyun, "연대 정문 앞으로 장미 배달.");
		arItem.add(mi);
		mi = new MyItem2(R.drawable.ic_launcher, "고대에 들려서 리포트 제출.");
		arItem.add(mi);
		mi = new MyItem2(R.drawable.ic_launcher, "세탁물 맡겨주세요.");
		arItem.add(mi);
		mi = new MyItem2(R.drawable.ic_launcher, "신밥 찾아와 주세요.");
		arItem.add(mi);

		MyListAdapter2 MyAdapter = new MyListAdapter2(this,
				R.layout.featured_adapter, arItem);

		listView = (ListView) findViewById(R.id.listView);

		LayoutInflater inflator = getLayoutInflater();

		// listView.addHeaderView(linear);

		listView.setAdapter(MyAdapter);

	}

	public void mapClick(View v) {
		Intent mapIn = new Intent(getApplicationContext(),
				NMapViewerActivity.class);
		startActivity(mapIn);
	}

	// android:onClick = "distanceClick"
	public void distanceClick(View v) {
		new AlertDialog.Builder(this).setTitle("거리를 선택하세요.")
				.setIcon(R.drawable.ic_launcher).setItems(R.array.distance,

				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String[] distance = getResources().getStringArray(
								R.array.distance);

						distanceBtn.setText(distance[which] + "");
					}
				}).setNegativeButton("취소", null).show();
	}

	// android:onClick = "priceClick"
	public void priceClick(View v) {
		new AlertDialog.Builder(this).setTitle("가격 때를 설정 하세요.")
				.setIcon(R.drawable.ic_launcher).setItems(R.array.price,

				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String[] distance = getResources().getStringArray(
								R.array.price);

						priceBtn.setText(distance[which] + "");
					}
				}).setNegativeButton("취소", null).show();
	}
}

class MyItem2 {
	MyItem2(int aIcon, String aName) {
		Icon = aIcon;
		Name = aName;
	}

	int Icon;
	String Name;
}
