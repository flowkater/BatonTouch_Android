package com.batontouch.managebaton;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.batontouch.main.R;

public class BatonMangeActivity_ManageTasks extends Activity {
	String fontPath = "fonts/NanumPen.ttf";
	TextView dealNametv, price1, date, dealStatus, specifics, fromHere, toHere, waitingForRunner, dealResttime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonmanage_managetasks_waiting);

		
		
		
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

		dealNametv = (TextView) findViewById(R.id.name);
		// price1 = (TextView) findViewById(R.id.price);
		date = (TextView) findViewById(R.id.date);
		dealStatus = (TextView) findViewById(R.id.status);

		Intent intent = getIntent();
		String taskName1 = intent.getStringExtra("taskName1");
		String status1 = intent.getStringExtra("status1");
		String date1 = intent.getStringExtra("date1");
		// String price = intent.getStringExtra("price");

		specifics = (TextView) findViewById(R.id.specifics);
		dealNametv.setText(taskName1);
		// price1.setText(price);
		date.setText(date1);
		dealStatus.setText(status1);
		
		dealResttime = (TextView) findViewById(R.id.dealResttime);

		dealNametv = (TextView) findViewById(R.id.name);
		dealStatus = (TextView) findViewById(R.id.status);
		waitingForRunner = (TextView) findViewById(R.id.waitingForRunner);
		font();
	}
	
	
	
private void font(){
		
		String fontPath = "fonts/NanumPen.ttf";
		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
		dealNametv.setTypeface(tf);
		dealStatus.setTypeface(tf);
		date.setTypeface(tf);
		dealResttime.setTypeface(tf);
		specifics.setTypeface(tf);
		waitingForRunner.setTypeface(tf);
		fromHere.setTypeface(tf);
		toHere.setTypeface(tf);
		
	}
}
