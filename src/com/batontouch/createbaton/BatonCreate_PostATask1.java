package com.batontouch.createbaton;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.TextView;

import com.batontouch.R;

public class BatonCreate_PostATask1 extends Activity {
	String fontPath = "fonts/NanumPen.ttf";
	private EditText nameEd,fromHereEt, toThereEt, taskDescriptionEt;
	private String name, fromAt, toAt, description;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batoncreate_postatask1);

		
		TextView batonName = (TextView) findViewById(R.id.batonName);
		TextView fromHere = (TextView) findViewById(R.id.fromHere);
		TextView toHere = (TextView) findViewById(R.id.toHere);
		TextView askForThis = (TextView) findViewById(R.id.askForThis);
		
		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
		batonName.setTypeface(tf);
		fromHere.setTypeface(tf);
		toHere.setTypeface(tf);
		askForThis.setTypeface(tf);
		
		nameEd = (EditText) findViewById(R.id.nameEd);
		fromHereEt = (EditText) findViewById(R.id.fromHereEt);
		toThereEt = (EditText) findViewById(R.id.toThereEt);
		taskDescriptionEt = (EditText) findViewById(R.id.taskDescriptionEt);

		// from NaverMap Touch
//		fromHereEt.setOnTouchListener(new fromTouchOnListener());
		
		// to NaverMap Touch
//		toThereEt.setOnTouchListener(new toTouchOnListener());
	}

	private class fromTouchOnListener implements OnTouchListener {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Intent intent = new Intent(getApplicationContext(),
						BatonCreate_PostATask_Map.class);
				startActivity(intent);
			}
			return true;
		}
	}

	private class toTouchOnListener implements OnTouchListener {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Intent intent = new Intent(getApplicationContext(),
						BatonCreate_PostATask_Map.class);
				startActivity(intent);
			}
			return true;
		}
	}

	public void nextBtnOnClick(View v) {
		name = nameEd.getText().toString();
		fromAt = fromHereEt.getText().toString();
		toAt = toThereEt.getText().toString();
		description = taskDescriptionEt.getText().toString();

		Intent intent = new Intent(getApplicationContext(),
				BatonCreate_PostATask2.class);
		Bundle extras = new Bundle();
		extras.putString("name", name);
		extras.putString("fromloc", fromAt);
		extras.putString("toloc", toAt);
		extras.putString("description", description);
		intent.putExtras(extras);

		startActivity(intent);
	}
}
