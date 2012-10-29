package com.batontouch.managebaton;


import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.batontouch.R;

public class MyListAdapter extends BaseAdapter {
	Context maincon;
	LayoutInflater Inflater;
	ArrayList<MyItem> arSrc;
	int layout;
	

	

	

	public MyListAdapter(Context context, int alayout, ArrayList<MyItem> aarSrc) {
		maincon = context;
		Inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		arSrc = aarSrc;
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

	

		return convertView;
	}
}