package com.batontouch.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.batontouch.R;

public class Logout_Dialog extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logoutdialog);
	}

	public void mOnClick(View v) {

		switch (v.getId()) {
		case R.id.yesBtn:

			finish();
			System.exit(0);

			break;
		case R.id.noBtn:
			finish();
			break;
		}

	}
}
