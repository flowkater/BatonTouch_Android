package com.batontouch.main;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
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
 
		// Android tab
		Intent intentAndroid = new Intent().setClass(this, BatonIndexActivity.class);
		TabSpec tabSpecAndroid = tabHost
		  .newTabSpec("Android")
		  .setIndicator("홈", null)
		  .setContent(intentAndroid);
 
		// Apple tab
		Intent intentApple = new Intent().setClass(this, BatonManageActivity.class);
		TabSpec tabSpecApple = tabHost
		  .newTabSpec("Apple")
		  .setIndicator("바톤관리", null)
		  .setContent(intentApple);
 
		// Windows tab
		Intent intentWindows = new Intent().setClass(this, BatonCreateActivity.class);
		TabSpec tabSpecWindows = tabHost
		  .newTabSpec("Windows")
		  .setIndicator("", ressources.getDrawable(R.drawable.logo))
		  .setContent(intentWindows);
 
		// Blackberry tab
		Intent intentBerry = new Intent().setClass(this, ProfileActivity.class);
		TabSpec tabSpecBerry = tabHost
		  .newTabSpec("Berry")
		  .setIndicator("프로필", null)
		  .setContent(intentBerry);
		
		Intent intentBerry2 = new Intent().setClass(this, SettingActivity.class);
		TabSpec tabSpecBerry2 = tabHost
		  .newTabSpec("Berry")
		  .setIndicator("설정", null)
		  .setContent(intentBerry2);
 
		// add all tabs 
		tabHost.addTab(tabSpecAndroid);
		tabHost.addTab(tabSpecApple);
		tabHost.addTab(tabSpecWindows);
		tabHost.addTab(tabSpecBerry);
		tabHost.addTab(tabSpecBerry2);
 
		//set Windows tab as default (zero based)
		tabHost.setCurrentTab(0);
    }

}
