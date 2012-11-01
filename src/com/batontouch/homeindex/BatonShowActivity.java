package com.batontouch.homeindex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.batontouch.R;

public class BatonShowActivity extends Activity {

	private TextView dealName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonindex_detail);
		dealName = (TextView) findViewById(R.id.dealName);
		
		Intent intent = getIntent();
		dealName.setText(intent.getStringExtra("taskName"));
	}
}
