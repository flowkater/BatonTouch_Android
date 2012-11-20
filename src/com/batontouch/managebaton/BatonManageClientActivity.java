package com.batontouch.managebaton;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.batontouch.R;
import com.batontouch.model.Task;
import com.batontouch.model.Tasks;
import com.batontouch.utils.Global;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;

public class BatonManageClientActivity extends Activity {
	private ListView mListView;
	private ArrayList<Task> mArrayList;
	private BatonManageAdapter_Client mManageadapter;
	private String mResult;

	private LinearLayout btnLinearManage;

	private String auth_token;
	private boolean auth_client;
	private SharedPreferences mPreferences;

	private ProgressDialog progressdialog;

	private ImageView clientimg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonmanage_client);

		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		auth_token = mPreferences.getString("AuthToken", ""); // auth_token 가져오기
		auth_client = mPreferences.getBoolean("AuthClient", false);

		clientimg = (ImageView) findViewById(R.id.upper_line_client);

		if (auth_client) {
			mArrayList = new ArrayList<Task>();

			mListView = (ListView) findViewById(R.id.listView);

			new GetMyTaskBidList().execute();

			mManageadapter = new BatonManageAdapter_Client(this,
					R.layout.featured_adapter2, mArrayList, auth_client);

			mListView.setAdapter(mManageadapter);
		} else {
			clientimg.setVisibility(View.VISIBLE);
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

	public void DialogProgress() {
		progressdialog = ProgressDialog.show(BatonManageClientActivity.this,
				"", "잠시만 기다려 주세요 ...", true);
		// 창을 내린다.
		// progressdialog.dismiss();
	}
}
