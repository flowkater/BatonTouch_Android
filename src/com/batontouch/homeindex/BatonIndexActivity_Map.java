package com.batontouch.homeindex;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.batontouch.R;

public class BatonIndexActivity_Map extends Activity {


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonindex_map);
		
	}
	
	public void listOnClick(View v){
		
		finish();
		
	}
}
