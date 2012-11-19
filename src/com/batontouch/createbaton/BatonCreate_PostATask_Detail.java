package com.batontouch.createbaton;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.batontouch.R;

public class BatonCreate_PostATask_Detail extends Activity {

	ListView listView;
	ArrayList<MyItem3> arItem;

	private ArrayList<String> items;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batoncreate_postatask_gift_detail);

		arItem = new ArrayList<MyItem3>();
		MyItem3 mi;
		mi = new MyItem3(R.drawable.ic_launcher, "민수");
		arItem.add(mi);
		mi = new MyItem3(R.drawable.ic_launcher, "민수");
		arItem.add(mi);
		mi = new MyItem3(R.drawable.ic_launcher, "민수");
		arItem.add(mi);
		mi = new MyItem3(R.drawable.ic_launcher, "민수");
		arItem.add(mi);
		mi = new MyItem3(R.drawable.ic_launcher, "Kihong");
		arItem.add(mi);

		MyListAdapter_Createbaton MyAdapter = new MyListAdapter_Createbaton(this, R.layout.featured_adapter_gift, arItem);

		listView = (ListView) findViewById(R.id.giftList);

		listView.setAdapter(MyAdapter);

		//

	}

}

class MyItem3 {
	MyItem3(int aIcon, String aName) {
		Icon = aIcon;
		Name = aName;
	}

	int Icon;
	String Name;
}
