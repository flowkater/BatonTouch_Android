package com.batontouch.managebaton;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.batontouch.R;
import com.batontouch.model.User;

public class BatonManageClientAdapter extends ArrayAdapter<User> {
	private Context mContext;
	private int mResource;
	private ArrayList<User> mUsers;
	private LayoutInflater mInflater;
	private User user;

	public BatonManageClientAdapter(Context context, int mResource,
			ArrayList<User> mUsers) {
		super(context, mResource, mUsers);
		this.mContext = context;
		this.mResource = mResource;
		this.mUsers = mUsers;
	}

	// 각 항목의 뷰 생성
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		user = mUsers.get(position);
		if (convertView == null) {
			this.mInflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(mResource, null);
			holder = new ViewHolder();

			holder.userImage = (ImageView) convertView
					.findViewById(R.id.userImage);
			holder.userName = (TextView) convertView
					.findViewById(R.id.userName);
			holder.selectBtn = (Button) convertView
					.findViewById(R.id.selectBtn);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (user != null) {
			// holder.userImage
			holder.userName.setText(user.getName());
			holder.selectBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					user = mUsers.get(position);
					RunnerChoiceDialog(user.getId());
				}
			});
		}

		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				user = mUsers.get(position);
				
			}
		});

		return convertView;
	}

	class ViewHolder {
		ImageView userImage;
		TextView userName;
		Button selectBtn;
	}

	// 제안 다이얼로그 method
	private void RunnerChoiceDialog(String User_id) {
		AlertDialog.Builder altBld = new AlertDialog.Builder(mContext);
		altBld.setMessage("이 러너를 선택하시겠습니까?").setCancelable(false)
				.setPositiveButton("넹", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

					}
				})
				.setNegativeButton("아뇨", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = altBld.create();
		alert.setTitle(User_id); // User_id 확인
		alert.show();
	}
}
