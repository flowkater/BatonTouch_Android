package com.batontouch.profile;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.batontouch.R;

public class ProfileActivity extends Activity {

	final int MY_REQUEST_CODE = 42;

	TextView textViewIntroduce;
	String fontPath = "fonts/NanumPen.ttf";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		
		TextView authorized = (TextView) findViewById(R.id.authorized);
		
		TextView aboutMe = (TextView) findViewById(R.id.aboutMe);
		TextView recentActivity = (TextView) findViewById(R.id.recentActivity);
		TextView review = (TextView) findViewById(R.id.review);
		
		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
		authorized.setTypeface(tf);
		aboutMe.setTypeface(tf);
		recentActivity.setTypeface(tf);
		review.setTypeface(tf);
		
		textViewIntroduce = (TextView) findViewById(R.id.textViewIntroduce);
	}

	public void profileEdit(View v) {
		Intent intent = new Intent(getApplicationContext(),
				ProfileActivity_Edit.class);
		startActivity(intent);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SharedPreferences myPref = getSharedPreferences("myPref",
				Activity.MODE_WORLD_WRITEABLE);

		if (myPref != null && myPref.contains("name")) {
			String name = myPref.getString("name", "");
			name = "";
			textViewIntroduce.setText(name);
		}

	}

	// protected void onActivityResult(int requestCode, int resultCode,
	// Intent intent) {
	// // TODO Auto-generated method stub
	// super.onActivityResult(requestCode, resultCode, intent);
	// if (requestCode == MY_REQUEST_CODE && resultCode == RESULT_OK) {
	//
	// // textViewIntroduce.setText(intent.getStringExtra("editComplete"));
	//
	// }
	// }

}
