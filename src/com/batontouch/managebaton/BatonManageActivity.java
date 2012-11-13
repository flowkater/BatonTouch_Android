package com.batontouch.managebaton;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.batontouch.R;
import com.batontouch.model.Task;
import com.batontouch.model.Tasks;
import com.batontouch.utils.Global;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;

public class BatonManageActivity extends Activity {
	private ListView mListView;
	private ArrayList<Task> mArrayList;
	private BatonManageAdapter mManageadapter;
	private String mResult;

	private Button askedButton, myTaskBtn;
	private LinearLayout btnLinearManage;

	private String auth_token;
	private boolean auth_client;
	private SharedPreferences mPreferences;

	private Boolean btnStatus = false;
	private ProgressDialog progressdialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonmanage);

		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		auth_token = mPreferences.getString("AuthToken", ""); // auth_token 가져오기
		auth_client = mPreferences.getBoolean("AuthClient", false);

		btnLinearManage = (LinearLayout) findViewById(R.id.btnLinearManage);
		askedButton = (Button) findViewById(R.id.askedTaskBtn);
		myTaskBtn = (Button) findViewById(R.id.myTaskBtn);

		if (auth_client) {// 클라이언트 체크를 해서 버튼을 없앤다.
			btnLinearManage.setVisibility(View.VISIBLE);
		}

		mArrayList = new ArrayList<Task>();

		mListView = (ListView) findViewById(R.id.listView);

		new GetAskedTaskList().execute();

		mManageadapter = new BatonManageAdapter(this,
				R.layout.featured_adapter2, mArrayList, auth_client);

		mListView.setAdapter(mManageadapter);
	}

	// 내가 시킨 일 가져오기 리스트
	private class GetAskedTaskList extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			mResult = NetHelper.DownloadHtml(Global.ServerUrl
					+ "tasks/askedbatons?auth_token=" + auth_token);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Gson gson = new Gson();
			Tasks tasks = gson.fromJson(mResult, Tasks.class);
			try {
				for (Task task : tasks.getTasks()) {
					mArrayList.add(task);
				}
			} catch (Exception e) {
				Log.e("batonmanage",
						e.getClass().getName() + " " + e.getMessage()
								+ " BatonManage Asked Gson Exception");
			}

			mManageadapter.notifyDataSetChanged();
			super.onPostExecute(result);
		}
	}

	// 내가 할 일 가져오기 리스트
	private class GetMyTaskBidList extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			mResult = NetHelper.DownloadHtml(Global.ServerUrl
					+ "tasks/mytaskbatons?auth_token=" + auth_token);
			Log.d("batonmanage", mResult + "");
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Gson gson = new Gson();
			Tasks tasks = gson.fromJson(mResult, Tasks.class);
			try {
				for (Task task : tasks.getTasks()) {
					mArrayList.add(task);
					Log.d("batonmanage", task.getName());
				}
			} catch (Exception e) {
				Log.e("batonmanage",
						e.getClass().getName() + " " + e.getMessage()
								+ " BatonManage Asked Gson Exception");
			}
			mManageadapter.notifyDataSetChanged();

			super.onPostExecute(result);
		}
	}

	// Client Status 가 변할 때 가져온다.
	public void BatonManageBtnClick(View v) {
		switch (v.getId()) {
		case R.id.askedTaskBtn:
			btnStatus = false;
			mArrayList.clear();
			new GetAskedTaskList().execute();
			myTaskBtn.setBackgroundColor(Color.BLACK);
			askedButton.setBackgroundColor(Color.rgb(89, 89, 89));
			break;

		case R.id.myTaskBtn:
			btnStatus = true;
			mArrayList.clear();
			new GetMyTaskBidList().execute();
			askedButton.setBackgroundColor(Color.BLACK);
			myTaskBtn.setBackgroundColor(Color.rgb(89, 89, 89));
			break;
		}
	}

	public void DialogProgress() {
		progressdialog = ProgressDialog.show(BatonManageActivity.this, "",
				"잠시만 기다려 주세요 ...", true);
		// 창을 내린다.
		// progressdialog.dismiss();
	}
}
