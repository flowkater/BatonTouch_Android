package com.batontouch.profile;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.batontouch.R;
import com.batontouch.createbaton.BatonCreate_PostATask_Selected;

public class MyListAdapter extends BaseAdapter {
	Context maincon;
	LayoutInflater Inflater;
	ArrayList<MyItem4> arSrc;
	int layout;

	public <MyItem3> MyListAdapter(Context context, int alayout, ArrayList<MyItem4> arItem) {
		maincon = context;
		Inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		arSrc = arItem;
		layout = alayout;

	}

	public int getCount() {
		return arSrc.size();
	}

	public String getItem(int position) {

		return arSrc.get(position).Name;
	}

	public long getItemId(int position) {
		return position;
	}

	// 각 항목의 뷰 생성
	public View getView(int position, View convertView, ViewGroup parent) {
		final int pos = position;
		if (convertView == null) {
			convertView = Inflater.inflate(layout, parent, false);
		}
		ImageView img = (ImageView) convertView.findViewById(R.id.image);
		img.setImageResource(arSrc.get(position).Icon);

		TextView txt = (TextView) convertView.findViewById(R.id.name);
		txt.setText(arSrc.get(position).Name);

		// Button btn = (Button)convertView.findViewById(R.id.btn);
		// btn.setOnClickListener(new Button.OnClickListener() {
		// public void onClick(View v) {
		// String str = arSrc.get(pos).Name + "를 주문합니다.";
		// Toast.makeText(maincon, str, Toast.LENGTH_SHORT).show();
		// }
		// });

		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(maincon, pos + "", 3000).show();
				Intent intent = new Intent(maincon, ProfileActivity_Manage_Gifticonpage.class);
				maincon.startActivity(intent);
			}
		});

		return convertView;
	}
}