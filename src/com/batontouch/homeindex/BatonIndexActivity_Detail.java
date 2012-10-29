package com.batontouch.homeindex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.batontouch.R;

public class BatonIndexActivity_Detail extends Activity {

	TextView dealName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonindex_detail);
		dealName = (TextView) findViewById(R.id.dealName);
		
		Intent intent = getIntent();
		dealName.setText(intent.getStringExtra("taskName"));
		
	}
}
