package com.batontouch.homeindex;


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

public class MyListAdapter2 extends BaseAdapter {
	Context maincon;
	LayoutInflater Inflater;
	ArrayList<MyItem2> arSrc;
	int layout;
	

	

	

	public MyListAdapter2(Context context, int alayout, ArrayList<MyItem2> aarSrc) {
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
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String str = arSrc.get(pos).Name +"";
				Intent intent = new Intent(maincon, BatonIndexActivity_Detail.class);
				intent.putExtra("taskName", str);
				
				Toast.makeText(maincon, str, 3000).show();
				maincon.startActivity(intent);
			}
		});
		
		
		TextView txt = (TextView) convertView.findViewById(R.id.name);
		txt.setText(arSrc.get(position).Name);

		// Button btn = (Button)convertView.findViewById(R.id.btn);
		// btn.setOnClickListener(new Button.OnClickListener() {
		// public void onClick(View v) {
		// String str = arSrc.get(pos).Name + "called";
		// Toast.makeText(maincon, str, Toast.LENGTH_SHORT).show();
		// }
		// });

	

		return convertView;
	}
}