package com.batontouch.setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.batontouch.R;

public class Setting_PushArea extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setting_pusharea);
	}
	

}
