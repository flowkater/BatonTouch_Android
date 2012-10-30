package com.batontouch.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.batontouch.R;

public class UserLoginActivity extends Activity {
	private EditText idEdit;
	private EditText pwEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.userloginpage);

		idEdit = (EditText) findViewById(R.id.login_id_edit_text);
		pwEdit = (EditText) findViewById(R.id.login_pw_edit_text);
		
		Intent in = getIntent();
		if (in != null) {
			idEdit.setText(in.getStringExtra("email"));
			pwEdit.setText(in.getStringExtra("password"));
		}
	}

	public void LoginClick(View v) {

	}
}
