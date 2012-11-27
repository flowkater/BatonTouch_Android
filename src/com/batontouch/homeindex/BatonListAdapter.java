package com.batontouch.homeindex;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.batontouch.main.R;
import com.batontouch.model.Giftcon;
import com.batontouch.model.Task;
import com.batontouch.model.User;
import com.batontouch.utils.Global;
import com.batontouch.utils.ImageDownloader;

public class BatonListAdapter extends ArrayAdapter<Task> {
	private Context mContext;
	private int mResource;
	private ArrayList<Task> mTasks;
	private LayoutInflater mInflater;
	private Task task;
	private User user;
	private Giftcon giftcon;
	private String userimage;
	private String giftconimage;

	public BatonListAdapter(Context context, int mResource,
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
		user = task.getUser();
		giftcon = task.getGiftcon();
		if (convertView == null) {
			this.mInflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(mResource, null);
			holder = new ViewHolder();

			holder.imageProfile = (ImageView) convertView
					.findViewById(R.id.imageProfile);
			holder.imageGiftcon = (ImageView) convertView
					.findViewById(R.id.imageGiftcon);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.day = (TextView) convertView.findViewById(R.id.day);
			holder.status = (TextView) convertView.findViewById(R.id.status);
			holder.resttime = (TextView) convertView
					.findViewById(R.id.resttime);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (task != null) {
			userimage = user.getProfile_image();
			giftconimage = giftcon.getImage();
			
			Log.d("userimage", Global.ServerOriginalUrl + userimage + " \n"+ Global.ServerOriginalUrl + giftconimage);
			
			if (userimage != null) {
				ImageDownloader.download(Global.ServerOriginalUrl + userimage,
						holder.imageProfile);
			} else {
				holder.imageProfile.setImageResource(R.drawable.ic_launcher);
			}

			if (giftconimage != null) {
				ImageDownloader.download(Global.ServerOriginalUrl
						+ giftconimage, holder.imageGiftcon);
			} else {
				holder.imageGiftcon.setImageResource(R.drawable.ic_launcher);
			}
			holder.name.setText(task.getName());
			holder.day.setText(task.getDay());
			holder.status.setText(Global.userJudge(task.getStatus()));
		}

		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				task = mTasks.get(position);
				Intent intent = new Intent(mContext, BatonShowActivity.class);
				Bundle extras = new Bundle();
				extras.putString("task_id", task.getId());
				intent.putExtras(extras);
				mContext.startActivity(intent);
			}
		});

		return convertView;
	}

	class ViewHolder {
		ImageView imageProfile;
		ImageView imageGiftcon;
		TextView name;
		TextView day;
		TextView status;
		TextView resttime;
	}
}
