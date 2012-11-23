package com.batontouch.createbaton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.batontouch.R;
import com.batontouch.utils.Global;
import com.batontouch.utils.ImageDownloader;

public class BatonCreate_PostATask_Selected extends Activity {
	private ImageView giftitem_image;
	private TextView giftitem_name, giftitem_description, giftitem_price;

	private String gift_id, gift_image_big, gift_name, gift_description,
			gift_price, gift_fromdate, gift_todate;
	private String store_id, name, description, fromloc, toloc, spendtime,
			calldate, enddate;

	private String auth_token;
	private SharedPreferences mPreferences;
	private int StatusCodeCheck;
	private int StatusCodeCreate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		settingVariable();

		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		auth_token = mPreferences.getString("AuthToken", "");

		setContentView(R.layout.batoncreate_postatask_gift_selected);

		giftitem_image = (ImageView) findViewById(R.id.giftitem_image);
		giftitem_name = (TextView) findViewById(R.id.giftitem_name);
		giftitem_description = (TextView) findViewById(R.id.giftitem_description);
		giftitem_price = (TextView) findViewById(R.id.giftitem_price);

		if (gift_image_big != null) {
			ImageDownloader.download(Global.ServerOriginalUrl + gift_image_big,
					giftitem_image);
		} else {
			giftitem_image.setImageResource(R.drawable.ic_launcher);
		}

		giftitem_name.setText(gift_name);
		giftitem_description.setText(gift_description);
		giftitem_price.setText(gift_price);
	}

	public void settingVariable() {
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();

		name = extras.getString("name");
		description = extras.getString("description");
		fromloc = extras.getString("fromloc");
		toloc = extras.getString("toloc");
		spendtime = extras.getString("spendtime");
		calldate = extras.getString("calldate");
		enddate = extras.getString("enddate");
		gift_id = extras.getString("gift_id");
		gift_image_big = extras.getString("gift_image_big");
		gift_name = extras.getString("gift_name");
		gift_description = extras.getString("gift_description");
		gift_price = extras.getString("gift_price");
		gift_fromdate = extras.getString("gift_fromdate");
		gift_todate = extras.getString("gift_todate");
		store_id = extras.getString("store_id");
		
		Toast.makeText(getApplicationContext(), name+"", Toast.LENGTH_SHORT).show();
		
	}

	public void selectGift(View v) {
		giftSelectDialog();
	}

	// 기프티콘 선택 다이얼로그
	private void giftSelectDialog() {
		AlertDialog.Builder altBld = new AlertDialog.Builder(this);
		altBld.setMessage("이 기프티콘을 선택하시게습니까?").setCancelable(false)
				.setPositiveButton("넹", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						new CheckPrice().execute();
					}
				})
				.setNegativeButton("아뇨", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = altBld.create();
		alert.show();
	}

	private class CheckPrice extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet getRequest = new HttpGet(Global.ServerUrl
						+ "giftitems/" + gift_id + "/check?auth_token="
						+ auth_token);
				getRequest.setHeader("Accept", Global.Acceptversion);
				getRequest
						.setHeader("Authorization", Global.AuthorizationToken);
				HttpResponse response = httpClient.execute(getRequest);

				StatusCodeCheck = response.getStatusLine().getStatusCode();

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								response.getEntity().getContent(), "UTF-8"));

				String sResponse;
				StringBuilder s = new StringBuilder();
				while ((sResponse = reader.readLine()) != null) {
					s = s.append(sResponse);
				}
				Log.d("batoncreate", "StatusCode : " + StatusCodeCheck + ", "
						+ "Response : " + s);

			} catch (IOException e) {
				Log.e("baton", e.getClass().getName() + e.getMessage()
						+ " Asynctask IOException CheckPrice");
			} catch (Exception e) {
				Log.e("baton", e.getClass().getName() + e.getMessage()
						+ " Asynctask Exception CheckPrice");
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (StatusCodeCheck == 200) {
				Toast.makeText(getApplicationContext(), "쿠키가 충분합니다.",
						Toast.LENGTH_SHORT).show();
				new TaskCreate().execute();
			} else if (StatusCodeCheck == 422) {
				Toast.makeText(getApplicationContext(), "쿠키가 부족합니다.",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "에러입니다.",
						Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}
	}

	private class TaskCreate extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost postRequest = new HttpPost(Global.ServerUrl
						+ "tasks/task_create?auth_token=" + auth_token);
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
				reqEntity.addPart("price",
						new StringBody(gift_price, Charset.forName("UTF-8")));
				reqEntity.addPart("store_id",
						new StringBody(store_id, Charset.forName("UTF-8")));
				reqEntity.addPart("giftitem_id",
						new StringBody(gift_id, Charset.forName("UTF-8")));

				postRequest.setEntity(reqEntity);
				postRequest.setHeader("Accept", Global.Acceptversion);
				postRequest.setHeader("Authorization",
						Global.AuthorizationToken);

				HttpResponse response = httpClient.execute(postRequest);

				StatusCodeCreate = response.getStatusLine().getStatusCode();

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								response.getEntity().getContent(), "UTF-8"));

				String sResponse;
				StringBuilder s = new StringBuilder();
				while ((sResponse = reader.readLine()) != null) {
					s = s.append(sResponse);
				}
				Log.d("batoncreate", "StatusCode : " + StatusCodeCreate + ", "
						+ "Response : " + s);

			} catch (IOException e) {
				Log.e("batoncreate", e.getClass().getName() + e.getMessage()
						+ " Asynctask IOException Batoncreate");
			} 
//			catch (Exception e) {
//				Log.e("batoncreate", e.getClass().getName() + e.getMessage()
//						+ " Asynctask IOException Batoncreate");
//			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (StatusCodeCreate == 201) {
				Toast.makeText(getApplicationContext(), "바톤이 생성되었습니다!",
						Toast.LENGTH_SHORT).show();
			} else if (StatusCodeCreate == 422) {
				Toast.makeText(getApplicationContext(), "바톤이 생성될떄 에러가 났습니다",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(),
						"바톤이 생성될떄 이상한 에러가 났습니다", Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}
	}
}
