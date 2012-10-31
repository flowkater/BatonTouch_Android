package com.batontouch.managebaton;

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
import com.batontouch.homeindex.BatonIndexActivity_Detail;

class MyListAdapter extends BaseAdapter {
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

		TextView status = (TextView) convertView.findViewById(R.id.status);
		status.setText(arSrc.get(position).Status);

		TextView name = (TextView) convertView.findViewById(R.id.name);
		name.setText(arSrc.get(position).Name);

		TextView date = (TextView) convertView.findViewById(R.id.day);
		date.setText(arSrc.get(position).Date);

		TextView price = (TextView) convertView.findViewById(R.id.price);
		price.setText(Integer.toString(arSrc.get(position).Price) + "원");

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				String str = arSrc.get(pos).Name + "";
				String str2 = arSrc.get(pos).Status + "";
				String str3 = arSrc.get(pos).Date + "";
				String str4 = Integer.toString(arSrc.get(pos).Price) + "원";
				
				
				Intent intent = new Intent(maincon,
						BatonMangeActivity_ManageTasks.class);
				intent.putExtra("taskName", str);
				intent.putExtra("status", str2);
				intent.putExtra("date", str3);
				intent.putExtra("price", str4);

				Toast.makeText(maincon, str, 3000).show();
				maincon.startActivity(intent);

			}
		});

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