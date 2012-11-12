package com.batontouch.profile;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.batontouch.R;
import com.batontouch.homeindex.BatonIndexActivity;

public class ProfileActivity_Intro extends TabActivity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_intro);
		
		TabHost tabHost = getTabHost();

//		LayoutInflater layout = getLayoutInflater();
//		View homeView = layout.inflate(R.layout.main_tab_home, null);
//		View manageView = layout.inflate(R.layout.main_tab_dealmanage, null);
//		View createView = layout.inflate(R.layout.main_tab_home, null);
//		View profileView = layout.inflate(R.layout.main_tab_profile, null);
//		View settingView = layout.inflate(R.layout.main_tab_setting, null);

		// Baton Home tab
		Intent intentHome = new Intent(this,
				BatonIndexActivity.class);
		TabSpec tabSpecHome = tabHost.newTabSpec("home")
				.setIndicator("profile").setContent(intentHome);
		
		
		// Baton Manage tab
		Intent intentBatonMng = new Intent(this, ProfileActivity_Edit.class);
		TabSpec tabSpecBatonMng = tabHost.newTabSpec("BatonMng")
				.setIndicator("cuki").setContent(intentBatonMng);

		// Baton Touch tab
		
		
		Intent intentBatonTouch = new Intent().setClass(this,
				ProfileActivity_Edit.class);
		TabSpec tabSpecBatonTouch = tabHost
				.newTabSpec("BatonTouch")
				.setIndicator("Gift")
				.setContent(intentBatonTouch);

		// Profile tab
//		Intent intentProfile = new Intent().setClass(this,
//				ProfileActivity.class);
		
	//	intentProfile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		
//		TabSpec tabSpecProfile = tabHost.newTabSpec("Profile")
//				.setIndicator(profileView).setContent(intentProfile);

		// Preference tab
//		Intent intentPreference = new Intent().setClass(this,
//				SettingActivity.class);
		
		//intentPreference.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
//		TabSpec tabSpecPreference = tabHost.newTabSpec("Preference")
//				.setIndicator(settingView).setContent(intentPreference);

		// add all tabs
		tabHost.addTab(tabSpecHome);
		tabHost.addTab(tabSpecBatonMng);
		tabHost.addTab(tabSpecBatonTouch);
//		tabHost.addTab(tabSpecProfile);
//		tabHost.addTab(tabSpecPreference);

		// set Windows tab as default (zero based)
		tabHost.setCurrentTab(1);
		
	}

	

}
