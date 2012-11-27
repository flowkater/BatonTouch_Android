package com.batontouch.managebaton;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;

import com.batontouch.main.R;

public class BatonManageActivity_Intro extends TabActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonmanage_intro);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab

		LayoutInflater layout = getLayoutInflater();
		View createmanage_doing = layout.inflate(R.layout.createmanage_tab_doinglist, null);
		View createmanage_todo = layout.inflate(R.layout.createmanage_tab_todolist, null);
		Intent intent;

		intent = new Intent().setClass(this, BatonManageAskedActivity.class);
		spec = tabHost.newTabSpec("askedtask").setIndicator(createmanage_doing)
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, BatonManageClientActivity.class);
		spec = tabHost.newTabSpec("clienttask").setIndicator(createmanage_todo)
				.setContent(intent);
		tabHost.addTab(spec);
		
		tabHost.setCurrentTab(0);
	}
}
