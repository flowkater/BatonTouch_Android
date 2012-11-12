package com.batontouch.managebaton;

import com.batontouch.R;
import com.batontouch.model.Task;
import com.batontouch.utils.Global;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class BatonManageShowActivity extends Activity {
	private TextView tvName, tvDate, tvStatus, tvDescription, tvResttime;
	private SharedPreferences mPreferences;
	private String auth_token;
	private String task_id;
	private String mResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.batonmanage_managetasks);

		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		auth_token = mPreferences.getString("AuthToken", "");

		Intent in = getIntent();
		task_id = in.getStringExtra("task_id");

		tvName = (TextView) findViewById(R.id.name);
		tvDate = (TextView) findViewById(R.id.date);
		tvStatus = (TextView) findViewById(R.id.status);
		tvDescription = (TextView) findViewById(R.id.dealDescription);
		tvResttime = (TextView) findViewById(R.id.dealResttime);
		
		new GetBatonShow().execute();

		super.onCreate(savedInstanceState);
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
				tvName.setText(task.getName());
				tvDate.setText(task.getDay());
				tvStatus.setText(Global.userJudge(task.getStatus()));
				tvDescription.setText(task.getDescription());
				tvResttime.setText(task.getEnddate());
			} catch (Exception e) {
				Log.e("batonmanage",
						e.getClass().getName() + " " + e.getMessage()
								+ " BatonManageShow Gson Exception");
			}
			super.onPostExecute(result);
		}
	}
}
