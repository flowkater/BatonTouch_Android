package com.batontouch.createbaton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.batontouch.R;

public class BatonCreate_PostATask_Selected extends Activity {
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batoncreate_postatask_gift_selected);
		
		
	}
	
	public void selectGift(View v){
		
		Toast.makeText(getApplicationContext(), "선택하기!", 3000).show();
		
	}


}