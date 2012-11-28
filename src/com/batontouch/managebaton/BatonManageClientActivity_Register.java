package com.batontouch.managebaton;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.batontouch.main.R;

public class BatonManageClientActivity_Register extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.batonmanage_client_register);

	}
}
