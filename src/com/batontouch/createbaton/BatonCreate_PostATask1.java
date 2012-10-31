package com.batontouch.createbaton;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.batontouch.R;

public class BatonCreate_PostATask1 extends Activity {

	EditText fromHereEt, toThereEt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batoncreate_postatask1);

		fromHereEt = (EditText) findViewById(R.id.fromHereEt);
		toThereEt = (EditText) findViewById(R.id.toThereEt);

		fromHereEt.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:

					Intent intent = new Intent(getApplicationContext(), BatonCreate_PostATask_Map.class);
					startActivity(intent);
				}

				return true;
			}
		});

		toThereEt.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					
					Intent intent = new Intent(getApplicationContext(), BatonCreate_PostATask_Map.class);
					startActivity(intent);

				}
				return true;
			}
		});

	}

	public void mOnClick(View v) {

		Intent intent = new Intent(getApplicationContext(),
				BatonCreate_PostATask2.class);
		startActivity(intent);

	}

}
