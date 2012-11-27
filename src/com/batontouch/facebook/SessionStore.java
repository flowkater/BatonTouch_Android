/*
 * Copyright 2010 Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.batontouch.facebook;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.batontouch.main.MainActivity;
import com.batontouch.main.ParsedLoginDataSet;
import com.batontouch.utils.Global;
import com.facebook.android.Facebook;
import com.google.android.gcm.GCMRegistrar;

public class SessionStore {

	private static final String TOKEN = "access_token";
	private static final String EXPIRES = "expires_in";
	private static final String KEY = "facebook-session";

	// == facebook server login
	private static SharedPreferences mPreferences;
	private static Thread t;
	private static JSONObject jObject;
	private static String facebooktoken;

	private static Context mcontext;

	public static boolean save(Facebook session, Context context) {
		mcontext = context;
		Editor editor = context.getSharedPreferences(KEY, Context.MODE_PRIVATE)
				.edit();
		editor.putString(TOKEN, session.getAccessToken());
		editor.putLong(EXPIRES, session.getAccessExpires());
		
		facebooktoken = session.getAccessToken();

		t = new Thread() {
			public void run() {
				
				try {
					authenticate(facebooktoken);
				} catch (ClientProtocolException e) {
					Log.e("my", e.getClass().getName() + e.getMessage() + "cl");
				} catch (IOException e) {
					Log.e("my", e.getClass().getName() + e.getMessage() + "io");
					e.printStackTrace();
				} catch (Exception e) {
					Log.e("my", e.getClass().getName() + e.getMessage() + "io");
				}
			}
		};
		t.start();
		return editor.commit();
	}

	public static boolean restore(Facebook session, Context context) {
		mcontext = context;
		SharedPreferences savedSession = context.getSharedPreferences(KEY,
				Context.MODE_PRIVATE);
		session.setAccessToken(savedSession.getString(TOKEN, null));
		session.setAccessExpires(savedSession.getLong(EXPIRES, 0));
		return session.isSessionValid();
	}

	public static void clear(Context context) {
		mcontext = context;
		Editor editor = context.getSharedPreferences(KEY, Context.MODE_PRIVATE)
				.edit();
		editor.clear();
		editor.commit();
	}
	// ============================= Facebook Server Module

	private static void authenticate(String token) throws ClientProtocolException, IOException {
		FacebookServerLogin(token);
		try {
			 
		} catch (Exception e) {
			Log.e("my", e.getClass().getName() + e.getMessage() + "LoginError");
		}
	}

	private static void FacebookServerLogin(String token) {
		Log.d("my", token);
		HashMap<String, String> sessionTokens = null;
		mPreferences = mcontext.getSharedPreferences("CurrentUser", mcontext.MODE_PRIVATE);

		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(Global.FacebookSendToken + token + "&regid=" + Global.gcm_regid);
		
		get.setHeader("Accept", "application/vnd.batontouch." + Global.version);

		String response = null;
		try {
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			response = client.execute(get, responseHandler);
		} catch (ClientProtocolException e) {
			Log.e("my", e.getClass().getName() + e.getMessage() + " clpt");
		} catch (IOException e) {
			Log.e("my", e.getClass().getName() + e.getMessage() + " IO");
		} catch (Exception e) {
			Log.e("my", e.getClass().getName() + e.getMessage() + " exception");
		}

		ParsedLoginDataSet parsedLoginDataSet = new ParsedLoginDataSet();
		try {
			sessionTokens = parseToken(response);
		} catch (Exception e) {
			Log.e("my", e.getClass().getName() + e.getMessage() + "5");
		}
		parsedLoginDataSet.setExtractedString(sessionTokens.get("error"));
		if (parsedLoginDataSet.getExtractedString().equals("Success")) {
			GCMRegistrar.setRegisteredOnServer(mcontext, true);
			// Store the username and password in SharedPreferences after the
			// successful login
			SharedPreferences.Editor editor = mPreferences.edit();
			// editor.putString("UserName", email);
			// editor.putString("PassWord", password);
			editor.putString("AuthToken", sessionTokens.get("auth_token"));
			editor.commit();
			Message myMessage = new Message();
			myMessage.obj = "SUCCESS";
			handler.sendMessage(myMessage);
		} else {
			Log.e("my", "Login Error!");
		}
	}

	public static HashMap<String, String> parseToken(String jsonResponse)
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

	private static Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String loginmsg = (String) msg.obj;
			if (loginmsg.equals("SUCCESS")) {

				Intent intent = new Intent(mcontext, MainActivity.class);
				mcontext.startActivity(intent);
			}
		}
	};
}
