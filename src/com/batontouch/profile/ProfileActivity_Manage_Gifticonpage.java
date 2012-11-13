package com.batontouch.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.batontouch.R;

public class ProfileActivity_Manage_Gifticonpage extends Activity{
	
	ToggleButton tButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_manage_gifticonpage);
		
		tButton = (ToggleButton) findViewById(R.id.toggleButton);
		  
		  tButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

		   @Override
		   public void onCheckedChanged(CompoundButton buttonView,
		     boolean isChecked) {
		    
//		    if(isChecked){
//		     tvStateofToggleButton.setText("ON");
//		    }else{
//		     tvStateofToggleButton.setText("OFF");
//		    }

		   }
		  });
		
		
	}
	
	public void storeinfo(View v){
		
		
		Toast.makeText(getApplicationContext(), "Store name", 3000).show();
		
		Intent intent = new Intent(getApplicationContext(), ProfileActivity_Manage_Gifticonpage_Storeinfo.class);
		startActivity(intent);
		
	}

}
