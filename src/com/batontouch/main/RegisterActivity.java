package com.batontouch.main;

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
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.batontouch.R;
import com.batontouch.utils.ConnectionDetector;
import com.batontouch.utils.Global;
import com.google.android.gcm.GCMRegistrar;

public class RegisterActivity extends Activity {
	private EditText emailEditText;
	private EditText passwordEditText;
	private EditText passwordconfirmEditText;

	private String email;
	private String password;
	private String password_confirmation;

	private int StatusCode;

	private StringBuilder s;

	// Connection detector
	ConnectionDetector cd;
	
	// Asyntask
	AsyncTask<Void, Void, Void> mRegisterTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerpage);

		emailEditText = (EditText) findViewById(R.id.member_email_edit_text);
		passwordEditText = (EditText) findViewById(R.id.member_password_edit_text);
		passwordconfirmEditText = (EditText) findViewById(R.id.member_password_confirmation_edit_text);

//		cd = new ConnectionDetector(getApplicationContext());
//
//		// Check if Internet present
//		if (!cd.isConnectingToInternet()) {
//			// Internet Connection is not present
//			Toast.makeText(getApplicationContext(),
//					"인터넷 연결상태가 좋지 않습니다. 나중에 다시 시도해주세요", Toast.LENGTH_LONG)
//					.show();
//			// stop executing code by return
//			return;
//		}
//
//		// Make sure the device has the proper dependencies.
//		GCMRegistrar.checkDevice(this);
//
//		// Make sure the manifest was properly set - comment out this line
//		// while developing the app, then uncomment it when it's ready.
//		GCMRegistrar.checkManifest(this);
//		
//		// GCM 등록 아이디 가져오기
//		final String regId = GCMRegistrar.getRegistrationId(this);
//		
//		// 존재하는지 체크
//		if (regId.equals("")) { // 존재하지 않는다면
//			GCMRegistrar.register(this, Global.SENDER_ID);
//		}else{
//			// 존재한다면
//			// Device 이미 등록되어있다면
//			if (GCMRegistrar.isRegisteredOnServer(this)) {
//				// skip
//			} else {
//				final Context context = this;
//				
//				mRegisterTask = new AsyncTask<Void, Void, Void>(){
//					protected transient Void doInBackground(Void[] params) {};
//				};
//				mRegisterTask.execute();
//			}
//		}
	}

	public void RegisterClick(View v) {
		email = emailEditText.getText().toString();
		password = passwordEditText.getText().toString();
		password_confirmation = passwordconfirmEditText.getText().toString();
		new Usercreate().execute(email, password, password_confirmation);
	}

	public class Usercreate extends AsyncTask<String, Void, Void> {
		@Override
		protected Void doInBackground(String... infos) {
			try {
				Log.d("my", email + " " + password + " "
						+ password_confirmation);
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost postRequest = new HttpPost(Global.ServerUrl
						+ "registrations");
				MultipartEntity reqEntity = new MultipartEntity(
						HttpMultipartMode.BROWSER_COMPATIBLE);

				reqEntity.addPart("user[email]", new StringBody(infos[0],
						Charset.forName("UTF-8")));
				reqEntity.addPart("user[password]", new StringBody(infos[1],
						Charset.forName("UTF-8")));
				reqEntity.addPart("user[password_confirmation]",
						new StringBody(infos[2], Charset.forName("UTF-8")));
				reqEntity.addPart("user[gcm_regid]", new StringBody(infos[3],
						Charset.forName("UTF-8")));

				postRequest.setEntity(reqEntity);
				postRequest.setHeader("Accept", "application/vnd.batontouch."
						+ Global.version);
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
				Log.d("my", "StatusCode : " + StatusCode + ", " + "Response : "
						+ s);
			} catch (IOException e) {
				Log.e("my", e.getClass().getName() + e.getMessage());
			} catch (Exception e) {
				Log.e("my", e.getClass().getName() + e.getMessage());
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (StatusCode == 201) {
				Toast.makeText(getApplicationContext(), "회원가입에 성공했습니다.",
						Toast.LENGTH_SHORT).show();
				Intent in = new Intent(getApplicationContext(),
						UserLoginActivity.class);
				Bundle extras = new Bundle();
				extras.putString("email", email);
				extras.putString("password", password);
				in.putExtras(extras);
				startActivity(in);
			} else if (StatusCode == 422) {
				Toast.makeText(getApplicationContext(), "회원가입에 실패했습니다.",
						Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}
	}
}
