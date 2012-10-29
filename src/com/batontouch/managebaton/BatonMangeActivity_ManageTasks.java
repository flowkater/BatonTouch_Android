package com.batontouch.managebaton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.batontouch.R;

public class BatonMangeActivity_ManageTasks extends Activity {

	TextView dealName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonmanage_managetasks);
		
		dealName = (TextView) findViewById(R.id.dealName);
		
		Intent intent = getIntent();
		String taskName = intent.getStringExtra("taskName");
		dealName.setText(taskName);
	}
}
