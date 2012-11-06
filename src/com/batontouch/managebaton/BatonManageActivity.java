package com.batontouch.managebaton;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.batontouch.R;
import com.batontouch.model.Task;

public class BatonManageActivity extends Activity {
	ListView listView, listView2;
	ArrayList<Task> maArrayList;

	Button btn, askedButton, myTaskBtn;

	RelativeLayout relative;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonmanage);
		// relative = (RelativeLayout) findViewById(R.id.featured_adapter);

		listView = (ListView) findViewById(R.id.listView);
		listView2 = (ListView) findViewById(R.id.listView2);

		// LayoutInflater inflator = getLayoutInflater();

		// listView.addHeaderView(linear);

		// listView.setAdapter(MyAdapter);

		askedButton = (Button) findViewById(R.id.askedTaskBtn);
		myTaskBtn = (Button) findViewById(R.id.myTaskBtn);
	}

	public void BatonManageBtnClick(View v) {

		switch (v.getId()) {

		case R.id.askedTaskBtn:

			// Toast.makeText(getApplicationContext(), "btnCollections",
			// 3000).show();
			// #8b8989
			myTaskBtn.setBackgroundColor(Color.BLACK);
			askedButton.setBackgroundColor(Color.rgb(89, 89, 89));
			listView2.setVisibility(View.INVISIBLE);
			listView.setVisibility(View.VISIBLE);
			break;

		case R.id.myTaskBtn:

			// Toast.makeText(getApplicationContext(), "btnVideos",
			// 3000).show();

			askedButton.setBackgroundColor(Color.BLACK);
			myTaskBtn.setBackgroundColor(Color.rgb(89, 89, 89));
			listView.setVisibility(View.INVISIBLE);
			listView2.setVisibility(View.VISIBLE);
			break;

		}

	}

}
