package com.batontouch.main;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.batontouch.R;
import com.batontouch.facebook.LoginButton;
import com.batontouch.facebook.SessionEvents;
import com.batontouch.facebook.SessionEvents.AuthListener;
import com.batontouch.facebook.SessionEvents.LogoutListener;
import com.batontouch.facebook.SessionStore;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.Facebook;

public class LoginActivity extends Activity {
	private SharedPreferences mPreferences;
	public static final String APP_ID = "423072401083369";
	private Facebook mFacebook;
	private AsyncFacebookRunner mAsyncRunner;
	private LoginButton facebookBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// if (APP_ID == null) {Util.showAlert(this, "Warning",
		// "Facebook Applicaton ID must be specified before running this example: see Example.java");}

		setContentView(R.layout.loginpage);

		facebookBtn = (LoginButton) findViewById(R.id.facebook_login);

		// SharedPreferences 에서 CurrentUser 설정 가져오기
		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);

		if (!checkLoginInfo()) {
			Log.d("my", "User not exis");
		} else {
			Log.d("my", "User exsits");
			Intent intent = new Intent(getApplicationContext(),
					MainActivity.class);
			startActivity(intent);
		}

		// ===================== Facebook 설정
		mFacebook = new Facebook(APP_ID);
		mAsyncRunner = new AsyncFacebookRunner(mFacebook);

		SessionStore.restore(mFacebook, this);
		SessionEvents.addAuthListener(new SampleAuthListener());
		SessionEvents.addLogoutListener(new SampleLogoutListener());
		facebookBtn.init(this, mFacebook);

		// SharedPreferences 에서 facebook session
//		mPreferencesFacebook = getSharedPreferences("facebook-session",
//				MODE_PRIVATE);
//		facebooktoken = mPreferencesFacebook.getString("access_token", "");
//		
//		Log.d("my", "facebooktoken : " + facebooktoken);
		// =====================
	}

	// ========================= Facebook Method Start
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		mFacebook.authorizeCallback(requestCode, resultCode, data);
	}

	// Auth
	public class SampleAuthListener implements AuthListener {
		public void onAuthSucceed() {
			Log.d("my", "You have logged in! ");
		}

		public void onAuthFail(String error) {
			Log.d("my", "Login Failed: " + error);
		}
	}

	// Logout
	public class SampleLogoutListener implements LogoutListener {
		public void onLogoutBegin() {
			Log.d("my", "Logging out...");
		}

		public void onLogoutFinish() {
			Log.d("my", "You have logged out! ");
		}
	}

	// ========================= Facebook Method End

	// 유저 로그인 버튼
	public void UserLoginClick(View v) {
		Intent intent = new Intent(getApplicationContext(),
				UserLoginActivity.class);
		startActivity(intent);
	}

	// 회원 가입 버튼
	public void RegisterClick(View v) {
		Intent intent = new Intent(getApplicationContext(),
				RegisterActivity.class);
		startActivity(intent);
	}

	// 걍 통과 버튼
	public void mOnClick(View v) {
		Toast.makeText(getApplicationContext(), "로그인 완료!", 3000).show();
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(intent);
	}

	// =========================== 토큰 있는지 없는지 확인
	private final boolean checkLoginInfo() {
		boolean authtoken_set = mPreferences.contains("AuthToken");
		if (authtoken_set) {
			return true;
		}
		return false;
	}
}
