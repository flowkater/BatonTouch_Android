package com.batontouch.setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.batontouch.R;

public class Setting_PushArea extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//==== Title 커스터마이징
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//====
		setContentView(R.layout.setting_pusharea);
	}
}
