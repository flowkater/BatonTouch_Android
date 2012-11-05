package com.batontouch.managebaton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.batontouch.R;

public class BatonMangeActivity_ManageTasks extends Activity {

	TextView dealName1, price1, date1, status1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonmanage_managetasks);
		
		dealName1 = (TextView) findViewById(R.id.name);
	//	price1 = (TextView) findViewById(R.id.price);
		date1 = (TextView) findViewById(R.id.date);
		status1 = (TextView) findViewById(R.id.status);
		
		
		Intent intent = getIntent();
		String taskName = intent.getStringExtra("taskName");
		String status = intent.getStringExtra("status");
		String date = intent.getStringExtra("date");
	//	String price = intent.getStringExtra("price");
		
		
		dealName1.setText(taskName);
	//	price1.setText(price);
		date1.setText(date);
		status1.setText(status);
		
	}
}
