package com.batontouch.managebaton;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.batontouch.R;
import com.batontouch.homeindex.BatonShowActivity;
import com.batontouch.model.Task;

class MyBatonManageAdapter extends ArrayAdapter<Task> {
	private Context mContext;
	private ArrayList<Task> mTasks;
	private int mResource;
	private LayoutInflater mInflater;
	private Task task;
	
	
	public MyBatonManageAdapter(Context context, int mResource, ArrayList<Task> mTasks){
		super(context, mResource, mTasks);
		this.mContext = context;
		this.mResource = mResource;
		this.mTasks = mTasks;
	}

	// 각 항목의 뷰 생성
	public View getView(int position, View convertView, ViewGroup parent) {
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final int pos = position;
		if (convertView == null) {
			convertView = mInflater.inflate(mResource, parent, false);
		}
	//	ImageView img = (ImageView) convertView.findViewById(R.id.image);
		//img.setImageResource(arSrc.get(position).Icon);

//		TextView status = (TextView) convertView.findViewById(R.id.status);
//		status.setText(arSrc.get(position).Status);
//
//		TextView name = (TextView) convertView.findViewById(R.id.name);
//		name.setText(arSrc.get(position).Name);
//
//		TextView date = (TextView) convertView.findViewById(R.id.day);
//		date.setText(arSrc.get(position).Date);

	//	TextView price = (TextView) convertView.findViewById(R.id.price);
	//	price.setText(Integer.toString(arSrc.get(position).Price) + "원");

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
//
//				String str = arSrc.get(pos).Name + "";
//				String str2 = arSrc.get(pos).Status + "";
//				String str3 = arSrc.get(pos).Date + "";
//				String str4 = Integer.toString(arSrc.get(pos).Price) + "원";
				
//				
//				Intent intent = new Intent(maincon,
//						BatonManageActivity_ManageTasks_Footer.class);
//				intent.putExtra("taskName", str);
//				intent.putExtra("status", str2);
//				intent.putExtra("date", str3);
//				intent.putExtra("price", str4);
//
//				Toast.makeText(maincon, str, 3000).show();
//				maincon.startActivity(intent);

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