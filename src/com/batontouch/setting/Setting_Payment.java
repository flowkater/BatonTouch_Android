package com.batontouch.setting;

import com.batontouch.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class Setting_Payment extends Activity {

	String fontPath = "fonts/NanumPen.ttf";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_payment);

	//	font();
	}
	
//	public void font(){
//		
//		TextView payment_1 = (TextView) findViewById(R.id.payment_1);
//		TextView payment_2 = (TextView) findViewById(R.id.payment_2);
//		TextView payment_3 = (TextView) findViewById(R.id.payment_3);
//		TextView payment_4 = (TextView) findViewById(R.id.payment_4);
//
//		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
//		
//		payment_1.setTypeface(tf);
//		payment_2.setTypeface(tf);
//		payment_3.setTypeface(tf);
//		payment_4.setTypeface(tf);
//		
//	}
}
