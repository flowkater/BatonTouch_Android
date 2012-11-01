package com.batontouch.createbaton;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.batontouch.R;

public class BatonCreate_PostATask3 extends Activity {
	
	private Bundle extras;
	private int gifticonNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 확인 코드
//		Toast.makeText(getApplicationContext(), getIntent().getStringExtra("from"), Toast.LENGTH_SHORT).show();
		
		//=================== 넘어온 인텐트 받아오기
		Intent in = getIntent();
		extras = new Bundle();
		extras = in.getExtras();
		//===================

		loadApps();

		setContentView(R.layout.batoncreate_postatask3);
		GridView grid = (GridView) findViewById(R.id.gridView);

		// LayoutInflater li = getLayoutInflater();

		grid.setAdapter(new AppsAdapter());

	}

	ArrayList<Integer> mApps = new ArrayList<Integer>();

	private void loadApps() {
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

		// mApps = getPackageManager().queryIntentActivities(mainIntent, 0);
		mApps.add(R.drawable.starbucks);
		mApps.add(R.drawable.starbucks);
		mApps.add(R.drawable.starbucks);
		mApps.add(R.drawable.tomntoms);
		mApps.add(R.drawable.tomntoms);
		mApps.add(R.drawable.tomntoms);

		mApps.add(R.drawable.coffeebean);
		mApps.add(R.drawable.coffeebean);
		mApps.add(R.drawable.coffeebean);

		mApps.add(R.drawable.starbucks);
		mApps.add(R.drawable.starbucks);
		mApps.add(R.drawable.starbucks);
		mApps.add(R.drawable.tomntoms);
		mApps.add(R.drawable.tomntoms);
		mApps.add(R.drawable.tomntoms);

		mApps.add(R.drawable.coffeebean);
		mApps.add(R.drawable.coffeebean);
		mApps.add(R.drawable.coffeebean);

	}

	public class AppsAdapter extends BaseAdapter {
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView i = new ImageView(getApplicationContext());

			final int pos = position;

			int info = mApps.get(position % mApps.size());

			i.setImageResource(info);
			i.setScaleType(ImageView.ScaleType.FIT_CENTER);

			final int h = (int) (90 * getResources().getDisplayMetrics().density + 0.5f);
			final int w = (int) (90 * getResources().getDisplayMetrics().density + 0.5f);
			i.setLayoutParams(new GridView.LayoutParams(h, w));

			i.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					gifticonNumber = pos;
//					Toast.makeText(getApplicationContext(), pos + "", 3000)
//							.show();
					Intent intent = new Intent(getApplicationContext(),
							BatonCreate_PostALastTask.class);
					extras.putInt("gifticonNumber", gifticonNumber);
					intent.putExtras(extras);
					
					startActivity(intent);
				}
			});
			return i;
		}

		public final int getCount() {
			return Math.min(32, mApps.size());
		}

		public final Object getItem(int position) {
			return mApps.get(position % mApps.size());
		}

		public final long getItemId(int position) {
			return position;
		}
	}
}
