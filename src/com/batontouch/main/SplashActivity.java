package com.batontouch.main;

import com.batontouch.utils.ConnectionDetector;
import com.batontouch.utils.Global;
import com.google.android.gcm.GCMRegistrar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class SplashActivity extends Activity {

	ProgressDialog progressBar;
	ConnectionDetector cd;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);
		
		cd = new ConnectionDetector(getApplicationContext());
		

        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            Toast.makeText(getApplicationContext(), "인터넷 연결상태가 좋지 않습니다. 나중에 다시 시도해주세요.", Toast.LENGTH_LONG).show();
            // stop executing code by return
            finish();
            return;
        }
		
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);
		
		final String regId = GCMRegistrar.getRegistrationId(this);
		
		Log.d("gcm", regId+"");
		
		if ("".equals(regId)) {
			GCMRegistrar.register(this, Global.SENDER_ID);
		}else{
			Log.d("gcm", regId+"");
			Global.gcm_regid = regId;
		}
		initialize();
	}

	private void initialize() {
		progressBar = new ProgressDialog(this);
	
	//	progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	//	progressBar.show();
		
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
