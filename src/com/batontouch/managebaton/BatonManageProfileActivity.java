package com.batontouch.managebaton;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.batontouch.main.R;
import com.batontouch.model.User;
import com.batontouch.utils.Global;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;

public class BatonManageProfileActivity extends Activity {

	private ImageView user_image, company_image, writer_image, activeImage1,
			activeImage2, activeImage3, activeImage4;
	private TextView user_name, user_introduce, writer_review_content;
	private String mResult;
	private String user_id;

	private String auth_token;
	private SharedPreferences mPreferences;
	
	private ProgressDialog progressdialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonmanage_profile);

		Intent in = getIntent();
		user_id = in.getStringExtra("user_id");

		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		auth_token = mPreferences.getString("AuthToken", "");

		user_image = (ImageView) findViewById(R.id.user_image);
		company_image = (ImageView) findViewById(R.id.company_image);
		writer_image = (ImageView) findViewById(R.id.writer_image);
		activeImage1 = (ImageView) findViewById(R.id.activeImage1);
		activeImage2 = (ImageView) findViewById(R.id.activeImage2);
		activeImage3 = (ImageView) findViewById(R.id.activeImage3);
		activeImage4 = (ImageView) findViewById(R.id.activeImage4);

		user_name = (TextView) findViewById(R.id.user_name);
		user_introduce = (TextView) findViewById(R.id.user_introduce);
		writer_review_content = (TextView) findViewById(R.id.writer_review_content);

		DialogProgress();
		new GetUserProfile().execute();
	}

	private class GetUserProfile extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			mResult = NetHelper.DownloadHtml(Global.ServerUrl + "users/"
					+ user_id + "?auth_token=" + auth_token);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Gson gson = new Gson();
			User user = gson.fromJson(mResult, User.class);
			try {
				user_name.setText(user.getName());
				 user_introduce.setText(user.getIntroduce());
			} catch (Exception e) {
				Log.e("batonmanage", e.getClass().getName() + e.getMessage()
						+ " Asynctask Exception GetUserProfile");
			}
			progressdialog.dismiss();
			super.onPostExecute(result);
		}
	}
	public void DialogProgress() {
		progressdialog = ProgressDialog.show(BatonManageProfileActivity.this, "",
				"잠시만 기다려주세요...", true);
		// 창을 내린다.
		// progressdialog.dismiss();
	}
}
