package com.batontouch.profile;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.batontouch.R;

public class ProfileActivity_Edit extends Activity {

	EditText editTextIntroduce;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_edit);

		editTextIntroduce = (EditText) findViewById(R.id.editTextIntroduce);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SharedPreferences myPref = getSharedPreferences("myPref",
				Activity.MODE_WORLD_WRITEABLE);

		SharedPreferences.Editor myEditor = myPref.edit();
		myEditor.putString("name", editTextIntroduce.getText().toString());
		myEditor.commit();
	}

	public void editCompleteBtn(View v) {

		Toast.makeText(getApplicationContext(), "수정 완료!", 3000).show();

		// Intent intent = new Intent();
		// intent.putExtra("editComplete",
		// editTextIntroduce.getText().toString() );
		// setResult(RESULT_OK, intent);

		finish();

	}
}
