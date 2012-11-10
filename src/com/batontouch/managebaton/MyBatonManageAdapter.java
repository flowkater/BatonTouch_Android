package com.batontouch.managebaton;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.batontouch.R;
import com.batontouch.model.Task;

class MyBatonManageAdapter extends ArrayAdapter<Task> {
	private Context mContext;
	private ArrayList<Task> mTasks;
	private int mResource;
	private LayoutInflater mInflater;
	private Task task;

	public MyBatonManageAdapter(Context context, int mResource,
			ArrayList<Task> mTasks) {
		super(context, mResource, mTasks);
		this.mContext = context;
		this.mResource = mResource;
		this.mTasks = mTasks;
	}

	// 각 항목의 뷰 생성
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		task = mTasks.get(position);
		if (convertView == null) {
			this.mInflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(mResource, parent, false);
			holder = new ViewHolder();

			holder.dealName = (TextView) convertView.findViewById(R.id.name);
			holder.dealDay = (TextView) convertView.findViewById(R.id.day);
			holder.dealStatus = (TextView) convertView
					.findViewById(R.id.status);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (task != null) {
			holder.dealName.setText(task.getName());
			holder.dealDay.setText(task.getDay());
			holder.dealStatus.setText(task.getStatus());
		}

		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				task = mTasks.get(position);
				Intent intent = new Intent(mContext,
						BatonManageActivity_ManageTasks_Footer.class);
				Bundle extras = new Bundle();
				extras.putString("task_id", task.getId());
				intent.putExtras(extras);
				mContext.startActivity(intent);
			}
		});

		return convertView;
	}

	class ViewHolder {
		TextView dealName;
		TextView dealDay;
		TextView dealStatus;
	}
}