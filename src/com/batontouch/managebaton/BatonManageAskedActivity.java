package com.batontouch.managebaton;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.batontouch.main.R;
import com.batontouch.model.Task;
import com.batontouch.model.Tasks;
import com.batontouch.utils.Global;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;

public class BatonManageAskedActivity extends Activity {
	private ListView mListView;
	private ArrayList<Task> mArrayList;
	private BatonManageAdapter mManageadapter;
	private String mResult;

	private String auth_token;
	private boolean auth_client;
	private SharedPreferences mPreferences;

	private ProgressDialog progressdialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonmanage_ask);

		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		auth_token = mPreferences.getString("AuthToken", ""); // auth_token 가져오기
		auth_client = mPreferences.getBoolean("AuthClient", false);

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

	public void DialogProgress() {
		progressdialog = ProgressDialog.show(BatonManageAskedActivity.this, "",
				"잠시만 기다려 주세요 ...", true);
		// 창을 내린다.
		// progressdialog.dismiss();
	}
}
