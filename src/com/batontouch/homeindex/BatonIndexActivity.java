package com.batontouch.homeindex;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ListView;

import com.batontouch.main.R;
import com.batontouch.model.Task;
import com.batontouch.model.Tasks;
import com.batontouch.utils.Global;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class BatonIndexActivity extends Activity {

	private Button distanceBtn, priceBtn;

	private ArrayList<Task> mArrayList;
	private BatonListAdapter MyAdapter;

	private int mPrevTotalItemCount = 0; // EndlessScrollListener Variable
	private PullToRefreshListView mlistView; // PullToRefreshListener
	private String mResult; // AsyncTask
	private Integer mCurrentPage = 1; // Page
	
	private String auth_token;
	private SharedPreferences mPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.batonindex);
		
		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		auth_token = mPreferences.getString("AuthToken", ""); // auth_token 가져오기

		distanceBtn = (Button) findViewById(R.id.distanceBtn);
		priceBtn = (Button) findViewById(R.id.priceBtn);

		mArrayList = new ArrayList<Task>();

		mlistView = (PullToRefreshListView) findViewById(R.id.listView);
		mlistView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				mArrayList.clear();
				mCurrentPage = 1;
				mPrevTotalItemCount = 0;
			}
		});
		// new GetMyTaskList().execute();

		MyAdapter = new BatonListAdapter(this, R.layout.featured_adapter,
				mArrayList);
		mlistView.getRefreshableView().setAdapter(MyAdapter);
		mlistView.getRefreshableView().setOnScrollListener(
				new EndlessScrollListener());
	}
	
//	@Override
//	protected void onResume() {
//		super.onResume();
//		mArrayList.clear();
//		mCurrentPage = 1;
//		mPrevTotalItemCount = 0;
//		new GetMyTaskList().execute();
//	}

	private class GetMyTaskList extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			mResult = NetHelper.DownloadHtml(Global.ServerUrl
					+ "tasks?page=" + mCurrentPage.toString() + "&auth_token=" + auth_token);
			Log.d("batonindex", Global.ServerUrl + "tasks.json" + mResult + "");
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
				Log.e("batonindex",
						e.getClass().getName() + " " + e.getMessage()
								+ " BatonIndex Gson Exception");
			}

			mCurrentPage++;
			mlistView.onRefreshComplete();
			MyAdapter.notifyDataSetChanged();

			super.onPostExecute(result);
		}
	}

	// EndlessScroll 적용 클래스
	public class EndlessScrollListener implements OnScrollListener {
		private int visibleThreshold = 5;
		private boolean loading = true;

		public EndlessScrollListener() {
		}

		public EndlessScrollListener(int visibleThreshold) {
			this.visibleThreshold = visibleThreshold;
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {

			if (loading) {
				if (totalItemCount > mPrevTotalItemCount) {
					loading = false;
					mPrevTotalItemCount = totalItemCount;
				}
			}
			if (!loading
					&& (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
				new GetMyTaskList().execute(); //
				loading = true;
			}
		}

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
		}
	}

	// android:onClick = "mapClick"
	public void mapClick(View v) {
		Intent mapIn = new Intent(getApplicationContext(),
				NMapViewerActivity.class);
		startActivity(mapIn);
	}

	// android:onClick = "distanceClick"
	public void distanceClick(View v) {
		new AlertDialog.Builder(this).setTitle("거리를 선택하세요.")
				.setIcon(R.drawable.ic_launcher).setItems(R.array.distance,

				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String[] distance = getResources().getStringArray(
								R.array.distance);

						distanceBtn.setText(distance[which] + "");
					}
				}).setNegativeButton("취소", null).show();
	}

	// android:onClick = "priceClick"
	public void priceClick(View v) {
		new AlertDialog.Builder(this).setTitle("가격 때를 설정 하세요.")
				.setIcon(R.drawable.ic_launcher).setItems(R.array.price,

				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String[] distance = getResources().getStringArray(
								R.array.price);

						priceBtn.setText(distance[which] + "");
					}
				}).setNegativeButton("취소", null).show();
	}

//	public void onBackPressed() {
//		Intent intent = new Intent(getApplicationContext(), Logout_Dialog.class);
//		startActivity(intent);
//
//	}
}
