package com.batontouch.createbaton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.batontouch.R;

public class BatonCreate_PostATask_Choose extends Activity {
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batoncreate_postatask_gift);
		
		
	}
	
	public void giftClicked(View v){
		
		Toast.makeText(getApplicationContext(), "Clicked", 3000).show();
		Intent intent= new Intent(getApplicationContext(), BatonCreate_PostATask_Detail.class);
		startActivity(intent);
	}

}