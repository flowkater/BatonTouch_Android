package com.batontouch.createbaton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.batontouch.R;

public class BatonCreate_PostATask_Map extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batoncreate_postatask_map);
	}
	
	public void mOnClick(View v){
		
		Toast.makeText(getApplicationContext(), "선택 됐습니다!.", Toast.LENGTH_LONG).show();
		finish();
		
	}
	
}
