package com.batontouch.managebaton;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.batontouch.R;
import com.batontouch.model.Task;
import com.batontouch.utils.Global;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;

public class BatonManageReviewActivity_User extends Activity {
	private String mResult;
	private String task_id;
	private String auth_token;
	String fontPath = "fonts/NanumPen.ttf";
	private SharedPreferences mPreferences;
	private RatingBar ratingBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review_user);

		TextView reviewPlz = (TextView) findViewById(R.id.reviewPlz);
		
		TextView reviewComplete = (TextView) findViewById(R.id.reviewComplete);
		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
		reviewPlz.setTypeface(tf);
		reviewComplete.setTypeface(tf);

		Intent in = getIntent();
		task_id = in.getStringExtra("task_id");
		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);

		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
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
			} catch (Exception e) {
				Log.e("batonmanage",
						e.getClass().getName() + " " + e.getMessage()
								+ " BatonManageReview Gson Exception");
				super.onPostExecute(result);
			}
		}
	}
}
