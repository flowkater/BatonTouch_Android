package com.batontouch.managebaton;

import java.util.ArrayList;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.batontouch.R;
import com.batontouch.model.Task;
import com.batontouch.model.Tasks;
import com.batontouch.utils.Global;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class BatonManageActivity extends Activity {
	private PullToRefreshListView askedlistView, myTasklistView;
	private ArrayList<Task> askedArrayList, myTaskArrayList;
	private MyBatonManageAdapter askedManageadapter, myTaskManageAdapter;

	private String askedResult, myTaskResult;

	private Button btn, askedButton, myTaskBtn;
	
	private String auth_token;
	private SharedPreferences mPreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonmanage);
		
		mPreferences = getSharedPreferences("CurrentUser",
				MODE_PRIVATE);
		auth_token = mPreferences.getString("AuthToken", "");

		askedButton = (Button) findViewById(R.id.askedTaskBtn);
		myTaskBtn = (Button) findViewById(R.id.myTaskBtn);
		
		askedArrayList = new ArrayList<Task>();
		myTaskArrayList = new ArrayList<Task>();

		askedlistView = (PullToRefreshListView) findViewById(R.id.listView);
		askedlistView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				askedArrayList.clear();
				new GetAskedTaskList().execute();
			}
		});
		myTasklistView = (PullToRefreshListView) findViewById(R.id.listView2);
		myTasklistView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				myTaskArrayList.clear();
				new GetMyTaskBidList().execute();
			}
		});
		new GetAskedTaskList().execute();

		askedManageadapter = new MyBatonManageAdapter(this,
				R.layout.featured_adapter2, askedArrayList);
		myTaskManageAdapter = new MyBatonManageAdapter(this,
				R.layout.featured_adapter2, myTaskArrayList);
		
		askedlistView.getRefreshableView().setAdapter(askedManageadapter);
		myTasklistView.getRefreshableView().setAdapter(myTaskManageAdapter);
	}
	
	private class GetAskedTaskList extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			askedResult = NetHelper.DownloadHtml(Global.ServerUrl+ "tasks/askedbatons?auth_token=" + auth_token);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			Gson gson = new Gson();
			Tasks tasks = gson.fromJson(askedResult, Tasks.class);
			try {
				for (Task task : tasks.getTasks()) {
					askedArrayList.add(task);
				}
			} catch (Exception e) {
				Log.e("batonmanage", e.getClass().getName() + " " + e.getMessage()
								+ " BatonManage Asked Gson Exception");
			}
			
			askedlistView.onRefreshComplete();
			askedManageadapter.notifyDataSetChanged();
			
			super.onPostExecute(result);
		}
	}
	
	private class GetMyTaskBidList extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			myTaskResult = NetHelper.DownloadHtml(Global.ServerUrl+ "tasks/mytaskbatons?auth_token=" + auth_token);
			Log.d("batonmanage", myTaskResult+"");
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			Gson gson = new Gson();
			Tasks tasks = gson.fromJson(myTaskResult, Tasks.class);
			try {
				for (Task task : tasks.getTasks()) {
					myTaskArrayList.add(task);
					Log.d("batonmanage", task.getName());
				}
			} catch (Exception e) {
				Log.e("batonmanage", e.getClass().getName() + " " + e.getMessage()
								+ " BatonManage Asked Gson Exception");
			}
			
			myTasklistView.onRefreshComplete();
			myTaskManageAdapter.notifyDataSetChanged();
			
			super.onPostExecute(result);
		}
	}

	public void BatonManageBtnClick(View v) {
		switch (v.getId()) {
		case R.id.askedTaskBtn:

			// Toast.makeText(getApplicationContext(), "btnCollections",
			// 3000).show();
			// #8b8989
			myTaskArrayList.clear();
			askedArrayList.clear();
			new GetAskedTaskList().execute();
			myTaskBtn.setBackgroundColor(Color.BLACK);
			askedButton.setBackgroundColor(Color.rgb(89, 89, 89));
			askedlistView.setVisibility(View.INVISIBLE);
			askedlistView.setVisibility(View.VISIBLE);
			break;

		case R.id.myTaskBtn:

			// Toast.makeText(getApplicationContext(), "btnVideos",
			// 3000).show();
			
			myTaskArrayList.clear();
			askedArrayList.clear();
			new GetMyTaskBidList().execute();
			askedButton.setBackgroundColor(Color.BLACK);
			myTaskBtn.setBackgroundColor(Color.rgb(89, 89, 89));
			myTasklistView.setVisibility(View.INVISIBLE);
			myTasklistView.setVisibility(View.VISIBLE);
			break;
		}
	}
}
