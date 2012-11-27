package com.batontouch.createbaton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.batontouch.main.R;

public class BatonCreateActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batoncreate);
	}

	public void foodClick(View v) {
		Intent intent = new Intent(getApplicationContext(),
				BatonCreate_PostATask1.class);
		Bundle extras = new Bundle();
		extras.putString("category_id", "1");
		intent.putExtras(extras);
		startActivity(intent);
	}

	public void manClick(View v) {
		Intent intent = new Intent(getApplicationContext(),
				BatonCreate_PostATask1.class);
		Bundle extras = new Bundle();
		extras.putString("category_id", "2");
		intent.putExtras(extras);
		startActivity(intent);
	}

	public void lifeClick(View v) {
		Intent intent = new Intent(getApplicationContext(),
				BatonCreate_PostATask1.class);
		Bundle extras = new Bundle();
		extras.putString("category_id", "3");
		intent.putExtras(extras);
		startActivity(intent);
	}

	public void superClick(View v) {
		Intent intent = new Intent(getApplicationContext(),
				BatonCreate_PostATask1.class);
		Bundle extras = new Bundle();
		extras.putString("category_id", "4");
		intent.putExtras(extras);
		startActivity(intent);
	}
}
