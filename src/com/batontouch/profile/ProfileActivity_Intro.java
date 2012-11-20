package com.batontouch.profile;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;

import com.batontouch.R;

public class ProfileActivity_Intro extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_intro);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab

		LayoutInflater layout = getLayoutInflater();
		View profileManage = layout.inflate(R.layout.profile_tab_profilemanage,
				null);
		View gifticonManage = layout.inflate(
				R.layout.profile_tab_gifticonmanage, null);
		View cukiManage = layout.inflate(R.layout.profile_tab_cukimanage, null);

		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, ProfileActivity.class);

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("profile").setIndicator(profileManage)
				.setContent(intent);
		tabHost.addTab(spec);

		// Do the same for the other tabs

		intent = new Intent().setClass(this,
				ProfileActivity_Manage_Gifticon.class);
		spec = tabHost.newTabSpec("gifticon").setIndicator(gifticonManage)
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent()
				.setClass(this, ProfileActivity_Manage_Cukis.class);
		spec = tabHost.newTabSpec("cuki").setIndicator(cukiManage)
				.setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);

	}

}
