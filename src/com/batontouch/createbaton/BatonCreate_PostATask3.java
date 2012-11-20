package com.batontouch.createbaton;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.batontouch.R;
import com.batontouch.model.Store;
import com.batontouch.model.Stores;
import com.batontouch.utils.Global;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;

public class BatonCreate_PostATask3 extends Activity {
	private String mResult;
	private ArrayList<Store> mStores = new ArrayList<Store>();
	private BatonCreate_StoreAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent in = getIntent();
		Bundle extras = new Bundle();
		extras = in.getExtras();

		setContentView(R.layout.batoncreate_postatask3);
		GridView grid = (GridView) findViewById(R.id.gridView);
		
		mAdapter = new BatonCreate_StoreAdapter(getApplicationContext(), mStores);
		grid.setAdapter(mAdapter);
		
		new GetStoreList().execute();
	}
	
	private class GetStoreList extends AsyncTask<Void, Void,Void>{
		@Override
		protected Void doInBackground(Void... params) {
			mResult = NetHelper.DownloadHtml(Global.ServerUrl + "stores");
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			Log.d("batoncreate", mResult+"");
			Gson gson = new Gson();
			Stores stores = gson.fromJson(mResult, Stores.class);
			try {
				for (Store store : stores.getStores()) {
					mStores.add(store);
				}
			} catch (Exception e) {
				Log.d("batoncreate", "gson error baton stores");
			}
			
			mAdapter.notifyDataSetChanged();
			
			super.onPostExecute(result);
		}
	}
}
