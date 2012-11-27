package com.batontouch.profile;

import java.util.ArrayList;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.batontouch.main.R;
import com.batontouch.model.Giftcon;
import com.batontouch.model.User;
import com.batontouch.utils.Global;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;

public class ProfileActivity_Manage_Gifticon extends Activity {
	private ListView mlistView;
	private ArrayList<Giftcon> giftcons;
	private SharedPreferences mPreferences;
	private String auth_token;

	private ProfileGiftconAdapter giftconAdapter;
	private String mResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_manage_gifticon);

		mlistView = (ListView) findViewById(R.id.gifticonList);

		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		auth_token = mPreferences.getString("AuthToken", "");

		giftcons = new ArrayList<Giftcon>();

		new GetMyGiftcons().execute();

		giftconAdapter = new ProfileGiftconAdapter(this,
				R.layout.featured_adapter_gift, giftcons);
		mlistView.setAdapter(giftconAdapter);
	}

	private class GetMyGiftcons extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			mResult = NetHelper.DownloadHtml(Global.ServerUrl
					+ "users/user_current?auth_token=" + auth_token);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Gson gson = new Gson();
			User user = gson.fromJson(mResult, User.class);
			try {
				for (Giftcon giftcon : user.getGiftcons()) {
					giftcons.add(giftcon);
				}
			} catch (Exception e) {
				 Log.e("giftcon",
						e.getClass().getName() + " " + e.getMessage()
								+ " Giftcons Gson Exception");
			}

			giftconAdapter.notifyDataSetChanged();
			
			super.onPostExecute(result);
		}
	}
}
