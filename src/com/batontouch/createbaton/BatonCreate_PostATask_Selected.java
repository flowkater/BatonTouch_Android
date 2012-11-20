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

	private String id,image, name, description, price, fromdate, todate;
	
	private String auth_token;
	private SharedPreferences mPreferences;
	private int StatusCodeCheck;
	private int StatusCodeCreate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		
		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		auth_token = mPreferences.getString("AuthToken", "");

		id = extras.getString("id");
		image = extras.getString("image");
		name = extras.getString("name");
		description = extras.getString("description");
		price = extras.getString("price");
		fromdate = extras.getString("fromdate");
		todate = extras.getString("todate");

		setContentView(R.layout.batoncreate_postatask_gift_selected);

		giftitem_image = (ImageView) findViewById(R.id.giftitem_image);
		giftitem_name = (TextView) findViewById(R.id.giftitem_name);
		giftitem_description = (TextView) findViewById(R.id.giftitem_description);
		giftitem_price = (TextView) findViewById(R.id.giftitem_price);

		if (image != null) {
			ImageDownloader.download(Global.ServerOriginalUrl + image,
					giftitem_image);
		} else {
			giftitem_image.setImageResource(R.drawable.ic_launcher);
		}

		giftitem_name.setText(name);
		giftitem_description.setText(description);
		giftitem_price.setText(price);
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
	
	private class CheckPrice extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet getRequest = new HttpGet(Global.ServerUrl + "giftitems/" + id +"/check?auth_token=" + auth_token);
				getRequest.setHeader("Accept", Global.Acceptversion);
				getRequest.setHeader("Authorization", Global.AuthorizationToken);
				HttpResponse response = httpClient.execute(getRequest);
				
				StatusCodeCheck = response.getStatusLine().getStatusCode();
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
				
				String sResponse;
				StringBuilder s = new StringBuilder();
				while ((sResponse = reader.readLine()) != null) {
					s = s.append(sResponse);
				}
				Log.d("batoncreate", "StatusCode : " + StatusCodeCheck + ", "
						+ "Response : " + s);
				
			}  catch (IOException e) {
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
				Toast.makeText(getApplicationContext(), "쿠키가 충분합니다.", Toast.LENGTH_SHORT).show();
			}else if(StatusCodeCheck == 422){
				Toast.makeText(getApplicationContext(), "쿠키가 부족합니다.", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(getApplicationContext(), "에러입니다.", Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}
	}
	
	private class TaskCreate extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost postRequest = new HttpPost(Global.ServerUrl
						+ "tasks/task_create?auth_token=" + auth_token);
				MultipartEntity reqEntity = new MultipartEntity(
						HttpMultipartMode.BROWSER_COMPATIBLE);

//				reqEntity.addPart("task[name]",
//						new StringBody(name, Charset.forName("UTF-8")));
//				reqEntity.addPart("task[description]", new StringBody(
//						description, Charset.forName("UTF-8")));
//				reqEntity.addPart("task[fromloc]", new StringBody(fromloc,
//						Charset.forName("UTF-8")));
//				reqEntity.addPart("task[toloc]",
//						new StringBody(toloc, Charset.forName("UTF-8")));
//				reqEntity.addPart("task[spendtime]", new StringBody(spendtime,
//						Charset.forName("UTF-8")));
//				reqEntity.addPart("task[calldate]", new StringBody(calldate,
//						Charset.forName("UTF-8")));
//				reqEntity.addPart("task[enddate]", new StringBody(enddate,
//						Charset.forName("UTF-8")));
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
		}
	}
}















