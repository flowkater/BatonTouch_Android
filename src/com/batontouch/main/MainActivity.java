package com.batontouch.main;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

import com.batontouch.R;
import com.batontouch.createbaton.BatonCreateActivity;
import com.batontouch.homeindex.BatonIndexActivity;
import com.batontouch.managebaton.BatonManageActivity;
import com.batontouch.profile.ProfileActivity;
import com.batontouch.setting.SettingActivity;

public class MainActivity extends TabActivity  {

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Resources ressources = getResources(); 
		TabHost tabHost = getTabHost(); 
 
		// Home tab
		Intent intentHome = new Intent().setClass(this, BatonIndexActivity.class);
		TabSpec tabSpecHome = tabHost
		  .newTabSpec("Home")
		  .setIndicator("홈", null)
		  .setContent(intentHome);
		

		// Baton Manager tab
		Intent intentBatonMng = new Intent().setClass(this, BatonManageActivity.class);
		TabSpec tabSpecBatonMng = tabHost
		  .newTabSpec("BatonMng")
		  .setIndicator("바톤관리", null)
		  .setContent(intentBatonMng);
 
		// Baton Touch tab
		Intent intentBatonTouch = new Intent().setClass(this, BatonCreateActivity.class);
		TabSpec tabSpecBatonTouch = tabHost
		  .newTabSpec("BatonTouch")
		  .setIndicator("", getResources().getDrawable(R.drawable.junghyun))
		  .setContent(intentBatonTouch);
 
		// Profile tab
		Intent intentProfile = new Intent().setClass(this, ProfileActivity.class);
		TabSpec tabSpecProfile = tabHost
		  .newTabSpec("Profile")
		  .setIndicator("프로필", null)
		  .setContent(intentProfile);
		
		// Preference tab
		Intent intentPreference = new Intent().setClass(this, SettingActivity.class);
		TabSpec tabSpecPreference = tabHost
		  .newTabSpec("Preference")
		  .setIndicator("설정", null)
		  .setContent(intentPreference);
 
		// add all tabs 
		tabHost.addTab(tabSpecHome);
		tabHost.addTab(tabSpecBatonMng);
		tabHost.addTab(tabSpecBatonTouch);
		tabHost.addTab(tabSpecProfile);
		tabHost.addTab(tabSpecPreference);
 
		//set Windows tab as default (zero based)
		tabHost.setCurrentTab(0);
		
		
		
    }


}
