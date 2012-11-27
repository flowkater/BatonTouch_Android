package com.batontouch.managebaton;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.batontouch.main.R;
import com.batontouch.model.User;
import com.batontouch.utils.Global;
import com.batontouch.utils.ImageDownloader;

public class BatonManageClientAdapter extends ArrayAdapter<User> {
	private Context mContext;
	private int mResource;
	private ArrayList<User> mUsers;
	private LayoutInflater mInflater;
	private User user;
	private String task_id;

	private SharedPreferences mPreferences;
	private String auth_token;
	
	private int StatusCode;
	
	private ProgressDialog progressdialog;

	public BatonManageClientAdapter(Context context, int mResource,
			ArrayList<User> mUsers, String task_id) {
		super(context, mResource, mUsers);
		this.mContext = context;
		this.mResource = mResource;
		this.mUsers = mUsers;
		this.task_id = task_id;

		mPreferences = context.getSharedPreferences("CurrentUser",
				context.MODE_PRIVATE);
		auth_token = mPreferences.getString("AuthToken", "");
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
			holder.selectBtn = (ImageButton) convertView
					.findViewById(R.id.selectBtn);
			holder.facebook = (ImageView)convertView.findViewById(R.id.facebook_auth);
			holder.phone = (ImageView)convertView.findViewById(R.id.phone_auth);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (user != null) {
			String image = user.getProfile_image();
			if (image != null) {
				ImageDownloader.download(Global.ServerOriginalUrl + image, holder.userImage);
			}else{
				holder.userImage.setImageResource(R.drawable.ic_launcher);
			}
			
			if (user.isFacebook()) {
				holder.facebook.setVisibility(View.VISIBLE);
			}
			
			if (user.isPhone_auth()) {
				holder.phone.setVisibility(View.VISIBLE);
			}
			
			holder.userName.setText(user.getName());

			// 버튼 클릭했을때 select Action
			holder.selectBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					user = mUsers.get(position);
					RunnerChoiceDialog(user.getTradestat_id());
				}
			});
		}

		// 선택된 클라이언트의 프로필 사진을 보여준다.
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				user = mUsers.get(position);
				Intent in = new Intent(mContext, BatonManageProfileActivity.class);
				Bundle extras = new Bundle();
				extras.putString("user_id", user.getId());
				in.putExtras(extras);
				mContext.startActivity(in);
			}
		});

		return convertView;
	}

	class ViewHolder {
		ImageView userImage;
		ImageView facebook;
		ImageView phone;
		TextView userName;
		ImageButton selectBtn;
	}

	// 러너 선택 다이얼로그 method
	private void RunnerChoiceDialog(String tradestat_id) {
		final String params[] = new String [1];
		params[0] = tradestat_id;
		AlertDialog.Builder altBld = new AlertDialog.Builder(mContext);
		altBld.setMessage("이 러너를 선택하시겠습니까?").setCancelable(false)
				.setPositiveButton("넹", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						DialogProgress();
						new SelectRunner().execute(params[0]);
					}
				})
				.setNegativeButton("아뇨", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = altBld.create();
		alert.show();
	}

	class SelectRunner extends AsyncTask<String, Void, Void> {
		@Override
		protected Void doInBackground(String... params) {
			try {
				String tradestat_id = params[0];
				HttpClient httpClient = new DefaultHttpClient();
				HttpPut putRequest = new HttpPut(Global.ServerUrl + "tasks/"
						+ task_id + "/selectclient?auth_token=" + auth_token);
				MultipartEntity reqEntity = new MultipartEntity(
						HttpMultipartMode.BROWSER_COMPATIBLE);
				
				reqEntity.addPart("tradestat_id", new StringBody(tradestat_id, Charset.forName("UTF-8")));
				
				putRequest.setEntity(reqEntity);
				putRequest.setHeader("Accept", Global.Acceptversion);
				putRequest.setHeader("Authorization", Global.AuthorizationToken);
				HttpResponse response = httpClient.execute(putRequest);
				
				StatusCode = response.getStatusLine().getStatusCode();

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								response.getEntity().getContent(), "UTF-8"));

				String sResponse;
				StringBuilder s = new StringBuilder();
				while ((sResponse = reader.readLine()) != null) {
					s = s.append(sResponse);
				}
				Log.d("batonmanage", "StatusCode : " + StatusCode + ", "
						+ "Response : " + s);
			} catch (IOException e) {
				Log.e("batonmanage", e.getClass().getName() + e.getMessage()
						+ " Asynctask IOException SelectRunner");
			} catch (Exception e) {
				Log.e("batonmanage", e.getClass().getName() + e.getMessage()
						+ " Asynctask Exception SelectRunner");
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			progressdialog.dismiss();
			if (StatusCode == 200) {
				Toast.makeText(mContext, "러너가 선택되었습니다.", Toast.LENGTH_SHORT).show();
				Intent in = new Intent(mContext, BatonManageShowActivity_User.class);
				in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				Bundle extras = new Bundle();
				extras.putString("task_id", task_id);
				in.putExtras(extras);
				mContext.startActivity(in);
				((Activity)mContext).finish();
			}
			super.onPostExecute(result);
		}
	}
	public void DialogProgress() {
		progressdialog = ProgressDialog.show(mContext, "",
				"잠시만 기다려 주세요 ...", true);
		// 창을 내린다.
		// progressdialog.dismiss();
	}
}
