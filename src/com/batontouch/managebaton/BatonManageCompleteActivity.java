package com.batontouch.managebaton;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.batontouch.main.R;
import com.batontouch.model.Giftcon;
import com.batontouch.model.Review;
import com.batontouch.model.Task;
import com.batontouch.model.User;
import com.batontouch.utils.Global;
import com.batontouch.utils.ImageDownloader;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;

public class BatonManageCompleteActivity extends Activity {
	private TextView name, date, status, resttime, username, description,
			fromloctv, toloctv, giftconname, reviewtv;
	private ImageView user_image, facebook, phoneauth, reviewerimage,
			giftconimage;

	private String mResult, task_id;
	private String auth_token;

	private SharedPreferences mPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonmanage_managetasks_done);

		Intent in = getIntent();
		task_id = in.getStringExtra("task_id");
		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		auth_token = mPreferences.getString("AuthToken", "");

		init_view();

		new GetBatonShow().execute();
	}

	private void init_view() {
		// textview
		name = (TextView) findViewById(R.id.name);
		date = (TextView) findViewById(R.id.date);
		status = (TextView) findViewById(R.id.status);
		resttime = (TextView) findViewById(R.id.resttime);
		username = (TextView) findViewById(R.id.username);
		description = (TextView) findViewById(R.id.description);
		fromloctv = (TextView) findViewById(R.id.fromloctv);
		toloctv = (TextView) findViewById(R.id.toloctv);
		giftconname = (TextView) findViewById(R.id.giftconname);
		reviewtv = (TextView) findViewById(R.id.reviewtv);

		// imageview
		giftconimage = (ImageView) findViewById(R.id.giftconimage);
		user_image = (ImageView) findViewById(R.id.userimage);
		facebook = (ImageView) findViewById(R.id.facebook);
		phoneauth = (ImageView) findViewById(R.id.phoneauth);
		reviewerimage = (ImageView) findViewById(R.id.reviewerimage);
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
			Log.d("test", mResult);
			Gson gson = new Gson();
			Task task = gson.fromJson(mResult, Task.class);
			User user = task.getUser();
			User client = task.getClient();
			Giftcon giftcon = task.getGiftcon();
			Review review = task.getReview();
			try {
				String uimage = user.getProfile_image();
				String cimage = client.getProfile_image();
				String gimage = giftcon.getImage();
				if (uimage != null) {
					ImageDownloader.download(Global.ServerOriginalUrl + uimage,
							user_image);
				} else {
					user_image.setImageResource(R.drawable.ic_launcher);
				}
				if (cimage != null) {
					ImageDownloader.download(Global.ServerOriginalUrl + cimage,
							reviewerimage);
				} else {
					reviewerimage.setImageResource(R.drawable.ic_launcher);
				}

				if (gimage != null) {
					ImageDownloader.download(Global.ServerOriginalUrl + gimage,
							giftconimage);
				} else {
					giftconimage.setImageResource(R.id.giftconimage);
				}

				if (user.isFacebook()) {
					facebook.setVisibility(View.VISIBLE);
				}
				if (user.isPhone_auth()) {
					phoneauth.setVisibility(View.VISIBLE);
				}

				name.setText(task.getName());
				date.setText(task.getDay());
				status.setText(Global.userJudge(task.getStatus()));
				// resttime.setText();
				username.setText(user.getName());
				description.setText(task.getDescription());
				fromloctv.setText(task.getFromloc());
				toloctv.setText(task.getToloc());
				giftconname.setText(giftcon.getName());
				reviewtv.setText(review.getContent());
			} catch (Exception e) {
				Log.e("batonmanage",
						e.getClass().getName() + " " + e.getMessage()
								+ " BatonManageReview Gson Exception");
				super.onPostExecute(result);
			}
		}
	}
}