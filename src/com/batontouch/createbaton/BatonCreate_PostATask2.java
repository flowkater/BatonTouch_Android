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

public class BatonCreate_PostATask2 extends Activity {

	EditText expectedtime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batoncreate_postatask2);
		expectedtime = (EditText) findViewById(R.id.expectedtime);

		expectedtime.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					new AlertDialog.Builder(BatonCreate_PostATask2.this)
							.setItems(R.array.expecttime,

							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									String[] distance = getResources()
											.getStringArray(R.array.expecttime);

									expectedtime.setText(distance[which] + "");
								}
							}).setNegativeButton("취소", null).show();

					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(expectedtime.getWindowToken(),
							0);
					break;

				}

				return true;
			}
		});

	}

	public void mOnClick(View v) {

		Intent intent = new Intent(getApplicationContext(),
				BatonCreate_PostATask3.class);
		startActivity(intent);

	}

	public void expectedTime(View v) {

		Toast.makeText(getApplicationContext(), "Clicked", 3000).show();

	}

}
