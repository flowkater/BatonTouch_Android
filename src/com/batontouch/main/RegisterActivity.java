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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.batontouch.R;
import com.batontouch.utils.Global;

public class RegisterActivity extends Activity {
	private EditText emailEditText;
	private EditText passwordEditText;
	private EditText passwordconfirmEditText;

	private String email;
	private String password;
	private String password_confirmation;

	private int StatusCode;

	private StringBuilder s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerpage);

		emailEditText = (EditText) findViewById(R.id.member_email_edit_text);
		passwordEditText = (EditText) findViewById(R.id.member_password_edit_text);
		passwordconfirmEditText = (EditText) findViewById(R.id.member_password_confirmation_edit_text);
	}

	public void RegisterClick(View v) {
		email = emailEditText.getText().toString();
		password = passwordEditText.getText().toString();
		password_confirmation = passwordconfirmEditText.getText().toString();
		new Usercreate().execute();
	}

	class Usercreate extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			try {
				Log.d("my", email + " " + password + " "
						+ password_confirmation);
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost postRequest = new HttpPost(Global.ServerUrl
						+ "registrations");
				MultipartEntity reqEntity = new MultipartEntity(
						HttpMultipartMode.BROWSER_COMPATIBLE);

				reqEntity.addPart("user[email]",
						new StringBody(email, Charset.forName("UTF-8")));
				reqEntity.addPart("user[password]", new StringBody(password,
						Charset.forName("UTF-8")));
				reqEntity.addPart(
						"user[password_confirmation]",
						new StringBody(password_confirmation, Charset
								.forName("UTF-8")));

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
