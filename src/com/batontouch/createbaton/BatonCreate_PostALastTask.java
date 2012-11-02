package com.batontouch.createbaton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.batontouch.R;
import com.batontouch.main.MainActivity;
import com.batontouch.utils.Global;

public class BatonCreate_PostALastTask extends Activity {

	private int StatusCode;
	private String name, description, fromloc, toloc, spendtime, calldate,
			enddate, giftcon, price;
	private String auth_token;
	private SharedPreferences mPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batoncreate_postalasttask);

		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);

		auth_token = mPreferences.getString("AuthToken", "");

		Intent intent = getIntent();
		name = intent.getStringExtra("name");
		fromloc = intent.getStringExtra("fromloc");
		toloc = intent.getStringExtra("toloc");
		description = intent.getStringExtra("description");
		spendtime = intent.getStringExtra("spendtime");
		calldate = intent.getStringExtra("calldate");
		enddate = intent.getStringExtra("enddate");
		giftcon = intent.getIntExtra("gifticonNumber", 0) + "";
	}

	public void batonCreateClick(View v) {
		// Toast.makeText(
		// getApplicationContext(),
		// fromloc + " "+ toloc + " "+description + " "+spendtime +" " +calldate
		// + enddate
		// + " "+giftcon, Toast.LENGTH_SHORT).show();
		new Batoncreate().execute();
	}

	// 바톤 생성 모듈
	class Batoncreate extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost postRequest = new HttpPost(Global.ServerUrl
						+ "tasks.json?auth_token=" + auth_token);
				MultipartEntity reqEntity = new MultipartEntity(
						HttpMultipartMode.BROWSER_COMPATIBLE);

				reqEntity.addPart("task[name]",
						new StringBody(name, Charset.forName("UTF-8")));
				reqEntity.addPart("task[description]", new StringBody(
						description, Charset.forName("UTF-8")));
				reqEntity.addPart("task[fromloc]", new StringBody(fromloc,
						Charset.forName("UTF-8")));
				reqEntity.addPart("task[toloc]",
						new StringBody(toloc, Charset.forName("UTF-8")));
				reqEntity.addPart("task[spendtime]", new StringBody(spendtime,
						Charset.forName("UTF-8")));
				reqEntity.addPart("task[calldate]", new StringBody(calldate,
						Charset.forName("UTF-8")));
				reqEntity.addPart("task[enddate]", new StringBody(enddate,
						Charset.forName("UTF-8")));
				reqEntity.addPart("task[giftcon]", new StringBody(giftcon,
						Charset.forName("UTF-8")));
				// reqEntity.addPart("task[price]",
				// new StringBody(price, Charset.forName("UTF-8")));

				postRequest.setEntity(reqEntity);
				HttpResponse response = httpClient.execute(postRequest);

				StatusCode = response.getStatusLine().getStatusCode();

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								response.getEntity().getContent(), "UTF-8"));

				String sResponse;
				StringBuilder s = new StringBuilder();
				while ((sResponse = reader.readLine()) != null) {
					s = s.append(sResponse);
				}
				Log.d("baton", "StatusCode : " + StatusCode + ", "
						+ "Response : " + s);
			} catch (IOException e) {
				Log.e("baton", e.getClass().getName() + e.getMessage()
						+ " Asynctask IOException PostTask");
			} catch (Exception e) {
				Log.e("baton", e.getClass().getName() + e.getMessage()
						+ " Asynctask Exception PostTask");
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Toast.makeText(getApplicationContext(), "일 등록 완료!", 3000).show();
			Intent intent = new Intent(getApplicationContext(),
					MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			super.onPostExecute(result);
		}
	}
}
