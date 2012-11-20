package com.batontouch.managebaton;

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

import com.batontouch.R;
import com.batontouch.model.Task;
import com.batontouch.utils.Global;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class BatonManageReviewActivity_Client extends Activity {
	private String mResult;
	private String task_id;
	private String auth_token;
	
	private SharedPreferences mPreferences;
	private RatingBar ratingBar;
	
	private int StatusCode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review_client);
		
		Intent in = getIntent();
		task_id = in.getStringExtra("task_id");
		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		
		ratingBar = (RatingBar)findViewById(R.id.ratingBar);
	}
	
	public void clientComClick(View v){
		Toast.makeText(getApplicationContext(), "콕" , Toast.LENGTH_SHORT).show();
	}
	private class ReviewCreateComplete extends AsyncTask<Void, Void, Void> {
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
				
				postRequest.setEntity(reqEntity);
				postRequest.setHeader("Accept", Global.Acceptversion);
				postRequest.setHeader("Authorization",
						Global.AuthorizationToken);

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
				Log.d("batoncreate", "StatusCode : " + StatusCode + ", "
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
			if (StatusCode == 201) {
			} else if (StatusCode == 422) {
			} else {
			}
			super.onPostExecute(result);
		}
	}
	
}
