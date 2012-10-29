package com.batontouch.createbaton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.batontouch.R;
import com.batontouch.homeindex.BatonIndexActivity;
import com.batontouch.main.MainActivity;

public class BatonCreate_PostATask3 extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batoncreate_postatask3);
		

	}
	
	public void mOnClick(View v){
		
		Toast.makeText(getApplicationContext(), "일 등록 완료!", 3000).show();
		
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(intent);
		
	}

}
