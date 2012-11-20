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

class BatonManageAdapter extends ArrayAdapter<Task> {
	private Context mContext;
	private ArrayList<Task> mTasks;
	private int mResource;
	private LayoutInflater mInflater;
	private Task task;
	private boolean auth_client;

	public BatonManageAdapter(Context context, int mResource,
			ArrayList<Task> mTasks, boolean auth_client) {
		super(context, mResource, mTasks);
		this.mContext = context;
		this.mResource = mResource;
		this.mTasks = mTasks;
		this.auth_client = auth_client;
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
			holder.dealStatus.setText(task.getClient_size_status(task
					.getStatus()));
			// 하나의 TextView 로 통합해서 status 명시
		}

		// client status 부분
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				task = mTasks.get(position);
				int status = task.getStatus();
				String task_id = task.getId();
				// user 일때
				if (status == 0) {
					startAct(BatonManageActivity_ManageTasks_Footer.class,
							task_id);
					Toast.makeText(mContext, task_id + "", Toast.LENGTH_SHORT)
							.show();
				} else if (status == 1 || status == 2) {
					startAct(BatonManageShowActivity_User.class, task_id);
					Toast.makeText(mContext, task_id + "", Toast.LENGTH_SHORT)
							.show();
				} else if (status == 3) {
					startAct(BatonManageReviewActivity_User.class, task_id);
					Toast.makeText(mContext, task_id + "", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});

		return convertView;
	}

	public void startAct(Class<?> cls, String task_id) {
		Intent in = new Intent(mContext, cls);
		Bundle extras = new Bundle();
		extras.putString("task_id", task_id);
		in.putExtras(extras);
		mContext.startActivity(in);
	}

	class ViewHolder {
		TextView dealName;
		TextView dealDay;
		TextView dealStatus;
	}
}
