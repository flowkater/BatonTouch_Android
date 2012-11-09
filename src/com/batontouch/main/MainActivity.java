package com.batontouch.main;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.batontouch.R;
import com.batontouch.createbaton.BatonCreateActivity;
import com.batontouch.homeindex.BatonIndexActivity;
import com.batontouch.managebaton.BatonManageActivity;
import com.batontouch.profile.ProfileActivity;
import com.batontouch.setting.SettingActivity;

public class MainActivity extends TabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TabHost tabHost = getTabHost();

		LayoutInflater layout = getLayoutInflater();
		View homeView = layout.inflate(R.layout.main_tab_home, null);
		View manageView = layout.inflate(R.layout.main_tab_dealmanage, null);
		View createView = layout.inflate(R.layout.main_tab_home, null);
		View profileView = layout.inflate(R.layout.main_tab_profile, null);
		View settingView = layout.inflate(R.layout.main_tab_setting, null);



		// Baton Home tab
		Intent intentHome = new Intent(this,
				BatonIndexActivity.class);
		TabSpec tabSpecHome = tabHost.newTabSpec("home")
				.setIndicator(homeView).setContent(intentHome);

		
		
		// Baton Manage tab
		Intent intentBatonMng = new Intent(this,
				com.batontouch.managebaton.BatonManageActivity.class);
		TabSpec tabSpecBatonMng = tabHost.newTabSpec("BatonMng")
				.setIndicator(manageView).setContent(intentBatonMng);

		// Baton Touch tab
		Intent intentBatonTouch = new Intent().setClass(this,
				BatonCreateActivity.class);
		TabSpec tabSpecBatonTouch = tabHost
				.newTabSpec("BatonTouch")
				.setIndicator(createView)
				.setContent(intentBatonTouch);

		// Profile tab
		Intent intentProfile = new Intent().setClass(this,
				ProfileActivity.class);
		TabSpec tabSpecProfile = tabHost.newTabSpec("Profile")
				.setIndicator(profileView).setContent(intentProfile);

		// Preference tab
		Intent intentPreference = new Intent().setClass(this,
				SettingActivity.class);
		TabSpec tabSpecPreference = tabHost.newTabSpec("Preference")
				.setIndicator(settingView).setContent(intentPreference);

		// add all tabs
		tabHost.addTab(tabSpecHome);
		tabHost.addTab(tabSpecBatonMng);
		tabHost.addTab(tabSpecBatonTouch);
		tabHost.addTab(tabSpecProfile);
		tabHost.addTab(tabSpecPreference);

		// set Windows tab as default (zero based)
		tabHost.setCurrentTab(0);

	}

}
