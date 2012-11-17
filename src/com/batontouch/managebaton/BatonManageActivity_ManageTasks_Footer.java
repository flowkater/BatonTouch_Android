package com.batontouch.managebaton;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.batontouch.R;
import com.batontouch.model.Task;
import com.batontouch.model.User;
import com.batontouch.utils.Global;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;

public class BatonManageActivity_ManageTasks_Footer extends Activity {
	private ListView mClientListView;

	private ArrayList<User> mUserList;
	private String task_id;
	private String mResult;
	private TextView tvName, tvDate, tvStatus, tvDescription, tvResttime;
	private SharedPreferences mPreferences;
	private String auth_token;

	private BatonManageClientAdapter clientAdapter;
	
	private ProgressDialog progressdialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonmanage_managetasks_footer);

		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		auth_token = mPreferences.getString("AuthToken", "");

		Intent in = getIntent();
		task_id = in.getStringExtra("task_id");

		mUserList = new ArrayList<User>();

		clientAdapter = new BatonManageClientAdapter(this,
				R.layout.featured_adapter_footer, mUserList, task_id);

		mClientListView = (ListView) findViewById(R.id.mClientListView);

		LayoutInflater li = getLayoutInflater();

		///
		ScrollView linear = (ScrollView) li.inflate(
				R.layout.batonmanage_managetasks, null);
		mClientListView.addHeaderView(linear);
///
		tvName = (TextView) linear.findViewById(R.id.name);
		tvDate = (TextView) linear.findViewById(R.id.date);
		tvStatus = (TextView) linear.findViewById(R.id.status);
		tvDescription = (TextView) linear.findViewById(R.id.dealDescription);
		tvResttime = (TextView) linear.findViewById(R.id.dealResttime);

		mClientListView.setAdapter(clientAdapter);

		DialogProgress();
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
			ArrayList<User> users = task.getUsers();
			try {
				for (User user : users) {
					mUserList.add(user);
				}
			} catch (Exception e) {
				Log.e("batonmanage",
						e.getClass().getName() + " " + e.getMessage()
								+ " BatonManage Gson Exception");
			} finally { // User 를 다 못받아오고 Exception 발생해도 Text 내용은 다 받아와야한다.
				clientAdapter.notifyDataSetChanged();

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
			}
			progressdialog.dismiss();
			super.onPostExecute(result);
		}
	}
	public void DialogProgress() {
		progressdialog = ProgressDialog.show(BatonManageActivity_ManageTasks_Footer.this, "",
				"잠시만 기다려 주세요 ...", true);
		// 창을 내린다.
		// progressdialog.dismiss();
	}

}
