package com.batontouch.managebaton;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.batontouch.R;
import com.batontouch.homeindex.MyItem2;
import com.batontouch.homeindex.MyListAdapter2;
import com.batontouch.model.Task;

public class BatonManageActivity_ManageTasks_Footer extends Activity {
	ListView listView;

	static boolean checked = true;

	Button chooseYou;
	ArrayList<Task> mArrayList;

	private ArrayList<String> items;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonmanage_managetasks_footer);

		MyListAdapter2 MyAdapter = new MyListAdapter2(this,
				R.layout.featured_adapter_footer, mArrayList);

		chooseYou = (Button) findViewById(R.id.chooseIt);

		listView = (ListView) findViewById(R.id.listView);

		LayoutInflater li = getLayoutInflater();

		LinearLayout linear = (LinearLayout) li.inflate(
				R.layout.batonmanage_managetasks, null);
		listView.addHeaderView(linear);

		listView.setAdapter(MyAdapter);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

	}

	public void chooseYou(View v) {

		// v.setEnabled(false);
		// v.setVisibility(View.GONE);
		// Toast.makeText(getApplicationContext(), "선택 됐습니다!", 3000).show();
		// listView.setEnabled(false);
		// if (checked == true) {

		if (checked == true) {
			Toast.makeText(getApplicationContext(), "Clicked", 3000).show();
			v.setEnabled(false);
			checked = false;
		} else {

			v.setVisibility(View.GONE);
	
			// Toast.makeText(maincon, pos + "", 3000).show();
		}

	}
}
