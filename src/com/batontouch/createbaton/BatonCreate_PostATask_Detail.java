package com.batontouch.createbaton;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.batontouch.R;
import com.batontouch.model.Giftitem;
import com.batontouch.model.Store;
import com.batontouch.utils.Global;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;

public class BatonCreate_PostATask_Detail extends Activity {

	private ListView listView;
	private ArrayList<Giftitem> mGiftitems;
	private String store_id;
	private BatonCreate_GiftitemAdapter mAdapter;
	private String mResult;
	private TextView store_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent in = getIntent();
		Bundle extras = in.getExtras();
		store_id = extras.getString("store_id");

		setContentView(R.layout.batoncreate_postatask_gift_detail);

		store_name = (TextView) findViewById(R.id.store_name);

		Toast.makeText(getApplicationContext(), store_id + "",
				Toast.LENGTH_SHORT).show();

		mGiftitems = new ArrayList<Giftitem>();

		mAdapter = new BatonCreate_GiftitemAdapter(this,
				R.layout.featured_adapter_gift, mGiftitems, extras);

		listView = (ListView) findViewById(R.id.giftList);
		listView.setAdapter(mAdapter);

		new GetStoreShow().execute();
	}

	private class GetStoreShow extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			mResult = NetHelper.DownloadHtml(Global.ServerUrl + "stores/"
					+ store_id);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Gson gson = new Gson();
			Store store = gson.fromJson(mResult, Store.class);
			ArrayList<Giftitem> giftitems = store.getGiftitems();
//			Log.d("batoncreate", store.getGiftitems()+"");
			try {
				for (Giftitem giftitem : giftitems) {
					mGiftitems.add(giftitem);
				}
			} catch (Exception e) {
				Log.e("batoncreate",
						e.getClass().getName() + " " + e.getMessage()
								+ " StoreShowActivity GetStoreShow");
			} finally {
				mAdapter.notifyDataSetChanged();
				try {
					store_name.setText(store.getName());
				} catch (Exception e) {
					Log.e("batoncreate",
							e.getClass().getName() + " " + e.getMessage()
									+ " StoreShowActivity GetStoreShow2");
				}
			}
			super.onPostExecute(result);
		}

	}
}
