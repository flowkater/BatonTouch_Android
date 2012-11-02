package com.batontouch.homeindex;

import java.util.ArrayList;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.batontouch.R;
import com.batontouch.model.Task;

public class MyListAdapter2 extends ArrayAdapter<Task> {
	private Context mContext;
	private int mResource;
	private ArrayList<Task> mTasks;
	private LayoutInflater mInflater;
	private Task task;

	public MyListAdapter2(Context context, int mResource, ArrayList<Task> mTasks) {
		super(context, mResource, mTasks);
		this.mContext = context;
		this.mResource = mResource;
		this.mTasks = mTasks;
	}

	// 각 항목의 뷰 생성
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		task = mTasks.get(position);
		if (convertView == null) {
			this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(mResource, null);
			holder = new ViewHolder();
			
			holder.imageProfile = (ImageView) convertView.findViewById(R.id.image);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.day = (TextView) convertView.findViewById(R.id.day);
			holder.status = (TextView) convertView.findViewById(R.id.status);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		if (task != null) {
			holder.name.setText(task.getName());
			holder.day.setText(task.getDay());
			holder.status.setText(task.getStatus());
		}
		
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(mContext, BatonShowActivity.class);
				Bundle extras = new Bundle();
				extras.putString("task_id", task.getId());
				intent.putExtras(extras);
				mContext.startActivity(intent);
			}
		});

		return convertView;
	}
	
	class ViewHolder{
		ImageView imageProfile;
		TextView name;
		TextView day;
		TextView status;
	}
}























