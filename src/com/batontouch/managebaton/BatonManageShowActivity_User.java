package com.batontouch.managebaton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.batontouch.R;
import com.batontouch.model.Task;
import com.batontouch.utils.Global;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;

public class BatonManageShowActivity_User extends Activity {
	private TextView tvName, tvDate, tvStatus, tvDescription, tvResttime;
	private LinearLayout linearfinish;
	private SharedPreferences mPreferences;
	private String auth_token;
	private String task_id;
	private String mResult;
	private int Status;
	
	private int StatusCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonmanage_managetasks_userpage_progress);

		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		auth_token = mPreferences.getString("AuthToken", "");

		Intent in = getIntent();
		task_id = in.getStringExtra("task_id");

		tvName = (TextView) findViewById(R.id.name);
//		tvDate = (TextView) findViewById(R.id.date);
//		tvStatus = (TextView) findViewById(R.id.status);
		tvDescription = (TextView) findViewById(R.id.dealDescription);
		tvResttime = (TextView) findViewById(R.id.dealResttime);
		linearfinish = (LinearLayout) findViewById(R.id.finish);

		new GetBatonShow().execute();
	}

	private class GetBatonShow extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			mResult = NetHelper.DownloadHtml(Global.ServerUrl + "tasks/"
					+ task_id + "?auth_token=" + auth_token);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Gson gson = new Gson();
			Task task = gson.fromJson(mResult, Task.class);
			try {
				Status = task.getStatus();
				tvName.setText(task.getName());
//				tvDate.setText(task.getDay());
//				tvStatus.setText(Global.userJudge(Status));
				tvDescription.setText(task.getDescription());
				tvResttime.setText(task.getEnddate());
			} catch (Exception e) {
				Log.e("batonmanage",
						e.getClass().getName() + " " + e.getMessage()
								+ " BatonManageShow Gson Exception");
			}
			if (Status == 2) {
				linearfinish.setVisibility(View.VISIBLE);
			}
			super.onPostExecute(result);
		}
	}

	public void finishedBtn(View v) {
		UserYesorNoDialog();
	}

	// 클라이언트 일 확인 다이얼로그
	private void UserYesorNoDialog() {
		AlertDialog.Builder altBld = new AlertDialog.Builder(this);
		altBld.setMessage("러너가 일을 완료하셨습니까?").setCancelable(false)
				.setPositiveButton("넹", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						new UserRequest().execute();
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

	// User Request Yes
	private class UserRequest extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPut putRequest = new HttpPut(Global.ServerUrl + "tasks/"
						+ task_id + "/userconfirm_yes?auth_token=" + auth_token);
				putRequest.setHeader("Accept", Global.Acceptversion);
				putRequest
						.setHeader("Authorization", Global.AuthorizationToken);
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
				Log.e("batonmange", e.getClass().getName() + e.getMessage()
						+ " Asynctask IOException UserConfirm");
			} catch (Exception e) {
				Log.e("batonmange", e.getClass().getName() + e.getMessage()
						+ " Asynctask Exception UserConfirm");
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (StatusCode == 200) {
				
			}
			super.onPostExecute(result);
		}
	}
}
