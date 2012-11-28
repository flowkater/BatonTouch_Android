package com.batontouch.profile;

import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.batontouch.main.R;
import com.batontouch.model.Giftcon;
import com.batontouch.model.User;
import com.batontouch.utils.Global;
import com.batontouch.utils.ImageDownloader;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;

public class ProfileActivity extends Activity {

	private TextView textViewIntroduce, authorized, aboutMe, recentActivity,
			reviewBox, introduceMySelf, review;

	private ImageView reviewerImage, profileImage;
	
	private String mResult;
	private String auth_token;
	
	private SharedPreferences mPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		
		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		auth_token = mPreferences.getString("AuthToken", "");

		init_view();
		
		new GetMyProfile().execute();
	}

	private void init_view() {
		profileImage = (ImageView) findViewById(R.id.profileImage); // 프로필이미지
		introduceMySelf = (TextView) findViewById(R.id.introduceMySelf); //내소개
		reviewBox = (TextView) findViewById(R.id.reviewBox); // 리뷰내용
		reviewerImage = (ImageView) findViewById(R.id.reviewImage); //리뷰어이미지
		
		
		authorized = (TextView) findViewById(R.id.authorized);
		aboutMe = (TextView) findViewById(R.id.aboutMe);
		recentActivity = (TextView) findViewById(R.id.recentActivity);
		review = (TextView) findViewById(R.id.review);

		String fontPath = "fonts/NanumPen.ttf";
		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
		authorized.setTypeface(tf);
		aboutMe.setTypeface(tf);
		recentActivity.setTypeface(tf);
		review.setTypeface(tf);
	}
	
	private class GetMyProfile extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			mResult = NetHelper.DownloadHtml(Global.ServerUrl
					+ "users/user_current?auth_token=" + auth_token);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Gson gson = new Gson();
			User user = gson.fromJson(mResult, User.class);
			try {
				String uimage = user.getProfile_image();
				if (uimage!=null) {
					ImageDownloader.download(Global.ServerOriginalUrl+uimage, profileImage);
				}else{
					profileImage.setImageResource(R.drawable.ic_launcher);
				}
				introduceMySelf.setText(user.getIntroduce());
				
			} catch (Exception e) {
				 Log.e("giftcon",
						e.getClass().getName() + " " + e.getMessage()
								+ " Giftcons Gson Exception");
			}
			super.onPostExecute(result);
		}
	}

	public void profileEdit(View v) {
		Intent intent = new Intent(getApplicationContext(),
				ProfileActivity_Edit.class);
		startActivity(intent);
}
	
	public void seeMore(View v){
		Intent intent = new Intent(getApplicationContext(),
				ProfileActivity_ReviewSeeMore.class);
		startActivity(intent);
		
	}
}
