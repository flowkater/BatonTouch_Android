package com.batontouch.managebaton;

import com.batontouch.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class BatonManageActivity_Intro extends TabActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonmanage_intro);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab

		LayoutInflater layout = getLayoutInflater();
		// View askedManage = layout.inflate(resource, null);
		// View mybatonMange = layout.inflate(resource, null);

		Intent intent;

		intent = new Intent().setClass(this, BatonManageAskedActivity.class);
		spec = tabHost.newTabSpec("askedtask").setIndicator("askedtask")
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, BatonManageClientActivity.class);
		spec = tabHost.newTabSpec("clienttask").setIndicator("clienttask")
				.setContent(intent);
		tabHost.addTab(spec);
		
		tabHost.setCurrentTab(0);
	}
}
