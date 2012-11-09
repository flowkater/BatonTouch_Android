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

public class BatonManageActivity extends Activity {
	ListView listView, listView2;
	ArrayList<MyItem> arItem;

	private ArrayList<String> items;

	Button btn, askedButton, myTaskBtn;

	RelativeLayout relative;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonmanage);
	//	relative = (RelativeLayout) findViewById(R.id.featured_adapter);

		arItem = new ArrayList<MyItem>();
		MyItem mi;
		mi = new MyItem(R.drawable.junghyun, "이대 앞에서 닭 사다 주세요.", 2000, "2013.2.23",
				"대기중");
		arItem.add(mi);
		mi = new MyItem(R.drawable.junghyun, "연대에서 물건 가져다 주세요.", 3000, "2010.2.25", "대기중");
		arItem.add(mi);
		mi = new MyItem(R.drawable.junghyun, "연대에서 물건 가져다 주세요.", 3000, "2010.2.25", "대기중");
		arItem.add(mi);
		mi = new MyItem(R.drawable.junghyun, "연대에서 물건 가져다 주세요.", 3000, "2010.2.25", "대기중");
		arItem.add(mi);
		mi = new MyItem(R.drawable.junghyun, "밥줘", 5000, "2011.4.23", "확정");
		arItem.add(mi);
		mi = new MyItem(R.drawable.junghyun, "밥줘", 6000, "2012.2.24", "대기중");
		arItem.add(mi);
		mi = new MyItem(R.drawable.junghyun, "놀아 주세요.", 1000, "2012.2.25", "확정");
		arItem.add(mi);

		MyListAdapter MyAdapter = new MyListAdapter(this,
				R.layout.featured_adapter2, arItem);

		listView = (ListView) findViewById(R.id.listView);
		listView2 = (ListView) findViewById(R.id.listView2);
		
	//	LayoutInflater inflator = getLayoutInflater();

		// listView.addHeaderView(linear);

		listView.setAdapter(MyAdapter);
		listView2.setAdapter(MyAdapter);
		
		
		
		
		askedButton = (Button) findViewById(R.id.askedTaskBtn);
		myTaskBtn = (Button) findViewById(R.id.myTaskBtn);
	}
	
	

	public void mOnClick(View v) {

		// btn = (Button) findViewById(R.id.button);
	

		switch (v.getId()) {

		// case R.id.featured_adapter:
		//
		// // Toast.makeText(getApplicationContext(), "featured_adapter",
		// 3000).show();
		// Intent intent = new Intent(getApplicationContext(),
		// ItemExample.class);
		//
		// startActivity(intent);

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

/*class MyItem {
	MyItem(int aIcon, String aName, int price, String date, String status) {
		Icon = aIcon;
		Price = price;
		Name = aName;
		Status = status;
		Date = date;
	}

	int Icon;
	int Price;

	
	String Name;
	String Status;
	String Date;
}*/

//어댑터 클래스
//class MyListAdapter extends BaseAdapter {
/*	Context maincon;
	LayoutInflater Inflater;
	ArrayList<MyItem> arSrc;
	int layout;

	public MyListAdapter(Context context, int alayout, ArrayList<MyItem> aarSrc) {
		maincon = context;
		Inflater = (LayoutInflater)context.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		arSrc = aarSrc;
		layout = alayout;
	}

	public int getCount() {
		return arSrc.size();
	}

	public String getItem(int position) {
		return arSrc.get(position).Name;
	}

	public long getItemId(int position) {
		return position;
	}

	// 각 항목의 뷰 생성
	public View getView(int position, View convertView, ViewGroup parent) {
		final int pos = position;
		if (convertView == null) {
			convertView = Inflater.inflate(layout, parent, false);
		}
		ImageView img = (ImageView) convertView.findViewById(R.id.image);
		img.setImageResource(arSrc.get(position).Icon);

		TextView status = (TextView) convertView.findViewById(R.id.status);
		status.setText(arSrc.get(position).Status);

		TextView name = (TextView) convertView.findViewById(R.id.name);
		name.setText(arSrc.get(position).Name);

		TextView date = (TextView) convertView.findViewById(R.id.day);
		date.setText(arSrc.get(position).Date);
		
		TextView price = (TextView) convertView.findViewById(R.id.price);
		price.setText(Integer.toString(arSrc.get(position).Price) + "원");
		
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(maincon, BatonMangeActivity_ManageTasks.class);
				maincon.startActivity(intent);
				
			}
		});
		
	//	Button btn = (Button)convertView.findViewById(R.id.btn);
//		btn.setOnClickListener(new Button.OnClickListener() {
//			public void onClick(View v) {
//				String str = arSrc.get(pos).Name + "를 주문합니다.";
//				Toast.makeText(maincon, str, Toast.LENGTH_SHORT).show();
//			}
//		});

		return convertView;
	}
}*/