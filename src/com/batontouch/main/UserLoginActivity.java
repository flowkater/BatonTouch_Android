package com.batontouch.main;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.batontouch.R;
import com.batontouch.utils.Global;

public class UserLoginActivity extends Activity {
	private EditText idEdit;
	private EditText pwEdit;

	private Thread t;
	private JSONObject jObject;
	private SharedPreferences mPreferences;
	private String email, password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// SharedPreferences 에서 CurrentUser 설정 가져오기
		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);

		setContentView(R.layout.userloginpage);

		idEdit = (EditText) findViewById(R.id.login_id_edit_text);
		pwEdit = (EditText) findViewById(R.id.login_pw_edit_text);

		Intent in = getIntent();
		if (in.getStringExtra("email") != null) {
			idEdit.setText(in.getStringExtra("email"));
			pwEdit.setText(in.getStringExtra("password"));
		}
	}

	public void LoginClick(View v) {
		t = new Thread() {
			public void run() {
				try {
					authenticate();
				} catch (ClientProtocolException e) {
					Log.e("my", e.getClass().getName() + e.getMessage() + "cl");
				} catch (IOException e) {
					Log.e("my", e.getClass().getName() + e.getMessage() + "io");
				} catch (Exception e){
					Log.e("my", e.getClass().getName() + e.getMessage() + "io");
				}

			}
		};
		t.start();
	}

	private void authenticate() throws ClientProtocolException, IOException {
		try {
			String pin = "";
			HashMap<String, String> sessionTokens = Login();
		} catch (Exception e) {
			Log.e("my", e.getClass().getName() + e.getMessage() + "LoginError");
		}
	}

	private HashMap<String, String> Login() {
		HashMap<String, String> sessionTokens = null;

		email = idEdit.getText().toString();
		password = pwEdit.getText().toString();

		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(Global.ServerUrl + "sessions");
		JSONObject holder = new JSONObject();
		JSONObject userObj = new JSONObject();

		try {
			userObj.put("email", email);
			userObj.put("password", password);
			holder.put("user", userObj);
			StringEntity se = new StringEntity(holder.toString());
			post.setEntity(se);
			post.setHeader("Content-type","application/json");
			post.setHeader("Accept", Global.Acceptversion);
			post.setHeader("Authorization", Global.AuthorizationToken );
		} catch (UnsupportedEncodingException e) {
			Log.e("my", e.getClass().getName() + e.getMessage() + "1");
		} catch (JSONException e) {
			Log.e("my", e.getClass().getName() + e.getMessage() + "2");
		}

		String response = null;
		try {
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			response = client.execute(post, responseHandler);
		} catch (ClientProtocolException e) {
			Log.e("my", e.getClass().getName() + e.getMessage() + "3");
		} catch (IOException e) {
			Log.e("my", e.getClass().getName() + e.getMessage() + "4");
		} catch (Exception e){
			Log.e("my", e.getClass().getName() + e.getMessage() + "4");
		}
		

		ParsedLoginDataSet parsedLoginDataSet = new ParsedLoginDataSet();
		try {
			sessionTokens = parseToken(response);
		} catch (Exception e) {
			Log.e("my", e.getClass().getName() + e.getMessage() + "5");
		}
		parsedLoginDataSet.setExtractedString(sessionTokens.get("error"));
		if (parsedLoginDataSet.getExtractedString().equals("Success")) {
			// Store the username and password in SharedPreferences after the
			// successful login
			SharedPreferences.Editor editor = mPreferences.edit();
//			editor.putString("UserName", email);
//			editor.putString("PassWord", password);
			editor.putString("AuthToken", sessionTokens.get("auth_token"));
			editor.commit();
			Message myMessage = new Message();
			myMessage.obj = "SUCCESS";
			handler.sendMessage(myMessage);
		} else {
			Log.e("my", "Login Error!");
		}
		return sessionTokens;
	}

	public HashMap<String, String> parseToken(String jsonResponse)
			throws Exception {
		HashMap<String, String> sessionTokens = new HashMap<String, String>();
		if (jsonResponse != null) {
			jObject = new JSONObject(jsonResponse);
			JSONObject sessionObject = jObject.getJSONObject("session");
			String attributeError = sessionObject.getString("error");
			String attributeToken = sessionObject.getString("auth_token");
			sessionTokens.put("error", attributeError);
			sessionTokens.put("auth_token", attributeToken);
		} else {
			sessionTokens.put("error", "Error");
		}
		return sessionTokens;
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String loginmsg = (String) msg.obj;
			if (loginmsg.equals("SUCCESS")) {

				Intent intent = new Intent(getApplicationContext(),
						MainActivity.class);
				startActivity(intent);
			}
		}
	};
}
