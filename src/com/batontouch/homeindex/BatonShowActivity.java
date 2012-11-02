package com.batontouch.homeindex;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.batontouch.R;
import com.batontouch.model.Task;
import com.batontouch.utils.Global;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;

public class BatonShowActivity extends Activity {

	private TextView dealName;
	private String task_id; // number
	private String mResult; // GetBatonShow AsyncTask
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonindex_detail);
		dealName = (TextView) findViewById(R.id.dealName);
		
		Intent intent = getIntent();
		task_id = intent.getStringExtra("task_id");
		
		new GetBatonShow().execute();
	}
	
	private class GetBatonShow extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			mResult = NetHelper.DownloadHtml(Global.ServerUrl + "tasks/" + task_id + ".json");
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			Gson gson = new Gson();
			Task task = gson.fromJson(mResult, Task.class);
			Log.d("batonshow",task.getName());
			super.onPostExecute(result);
		}
	}
}
