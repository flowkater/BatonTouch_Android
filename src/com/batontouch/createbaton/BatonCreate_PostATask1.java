package com.batontouch.createbaton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.batontouch.R;

public class BatonCreate_PostATask1 extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batoncreate_postatask1);
		

	}
	
	public void mOnClick (View v){
		
		Intent intent = new Intent(getApplicationContext(), BatonCreate_PostATask2.class);
		startActivity(intent);
		
	}

}
