package com.batontouch.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.batontouch.R;

public class SplashActivity extends Activity {

	ProgressDialog progressBar;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);

		initialize();
	}

	private void initialize() {
		progressBar = new ProgressDialog(this);
		
	
		progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressBar.show();
		// Handler
		Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				finish();
				startActivity(new Intent(SplashActivity.this, LoginActivity.class));
				progressBar.dismiss();
			}
		};
		// 2 sec
		handler.sendEmptyMessageDelayed(0, 2000);
	}
}
