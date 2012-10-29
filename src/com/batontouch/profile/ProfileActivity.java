package com.batontouch.profile;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.batontouch.R;

public class ProfileActivity extends Activity {

	final int MY_REQUEST_CODE = 42;

	TextView textViewIntroduce;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
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
			textViewIntroduce.setText(name);
		}

	}


//	protected void onActivityResult(int requestCode, int resultCode,
//			Intent intent) {
//		// TODO Auto-generated method stub
//		super.onActivityResult(requestCode, resultCode, intent);
//		if (requestCode == MY_REQUEST_CODE && resultCode == RESULT_OK) {
//
//			// textViewIntroduce.setText(intent.getStringExtra("editComplete"));
//
//		}
//	}

}
