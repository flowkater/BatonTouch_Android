package com.batontouch.managebaton;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.batontouch.R;

public class BatonMangeActivity_ManageTasks extends Activity {
	String fontPath = "fonts/NanumPen.ttf";
	TextView dealName1, price1, date1, status1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonmanage_managetasks);

		
		
		
//		TextView name = (TextView) findViewById(R.id.name);
//		TextView dealDescription = (TextView) findViewById(R.id.dealDescription);
//		TextView status = (TextView) findViewById(R.id.status);
//		TextView giftcon = (TextView) findViewById(R.id.giftcon);
//		TextView dealResttime = (TextView) findViewById(R.id.dealResttime);
//		
//		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
//		
//		name.setTypeface(tf);
//		dealDescription.setTypeface(tf);
//		status.setTypeface(tf);
//		giftcon.setTypeface(tf);
//		dealResttime.setTypeface(tf);

		dealName1 = (TextView) findViewById(R.id.name);
		// price1 = (TextView) findViewById(R.id.price);
		date1 = (TextView) findViewById(R.id.date);
		status1 = (TextView) findViewById(R.id.status);

		Intent intent = getIntent();
		String taskName = intent.getStringExtra("taskName");
		String status = intent.getStringExtra("status");
		String date = intent.getStringExtra("date");
		// String price = intent.getStringExtra("price");

		dealName1.setText(taskName);
		// price1.setText(price);
		date1.setText(date);
		status1.setText(status);

	}
}
