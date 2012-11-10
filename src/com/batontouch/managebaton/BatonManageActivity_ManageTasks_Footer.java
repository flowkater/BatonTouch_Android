package com.batontouch.managebaton;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.batontouch.R;
import com.batontouch.homeindex.MyListAdapter2;
import com.batontouch.model.Task;
import com.batontouch.model.User;
import com.batontouch.utils.Global;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;

public class BatonManageActivity_ManageTasks_Footer extends Activity {
	ListView listView;

	static boolean checked = true;

	Button chooseYou;
	private ArrayList<User> mUserList;
	private String task_id;
	private String mResult;
	private TextView tvName, tvDate, tvStatus, tvDescription, tvResttime;
	private SharedPreferences mPreferences;
	private String auth_token;
	
	ArrayList<Task> mArrayList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonmanage_managetasks_footer);
		
		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		auth_token = mPreferences.getString("AuthToken", "");
		
		Intent in = getIntent();
		task_id = in.getStringExtra("task_id");
		
		mArrayList = new ArrayList();

		MyListAdapter2 MyAdapter = new MyListAdapter2(this,
				R.layout.featured_adapter_footer, mArrayList);

		chooseYou = (Button) findViewById(R.id.chooseIt);
		listView = (ListView) findViewById(R.id.listView);

		LayoutInflater li = getLayoutInflater();

		LinearLayout linear = (LinearLayout) li.inflate(
				R.layout.batonmanage_managetasks, null);
		listView.addHeaderView(linear);
		
		tvName = (TextView) linear.findViewById(R.id.name);
		tvDate = (TextView) linear.findViewById(R.id.date);
		tvStatus = (TextView) linear.findViewById(R.id.status);
		tvDescription = (TextView) linear.findViewById(R.id.dealDescription);
		tvResttime = (TextView) linear.findViewById(R.id.dealResttime);

		listView.setAdapter(MyAdapter);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		new GetBatonShow().execute();
	}

	public void chooseYou(View v) {

		// v.setEnabled(false);
		// v.setVisibility(View.GONE);
		// Toast.makeText(getApplicationContext(), "선택 됐습니다!", 3000).show();
		// listView.setEnabled(false);
		// if (checked == true) {

		if (checked == true) {
			Toast.makeText(getApplicationContext(), "Clicked", 3000).show();
			v.setEnabled(false);
			checked = false;
		} else {
			v.setVisibility(View.GONE);
			// Toast.makeText(maincon, pos + "", 3000).show();
		}
	}
	
	
	private class GetBatonShow extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... arg0) {
			mResult = NetHelper.DownloadHtml(Global.ServerUrl + "tasks/"
					+ task_id);
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			Gson gson = new Gson();
			Task task = gson.fromJson(mResult, Task.class);
			tvName.setText(task.getName());
//			tvDate.setText(task.getDay());
//			tvStatus.setText(task.getStatus());
//			tvDescription.setText(task.getDescription());
//			tvResttime.setText(task.getEnddate());
			
			super.onPostExecute(result);
		}
	}
}
