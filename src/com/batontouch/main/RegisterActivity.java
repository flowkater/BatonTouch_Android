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
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerpage_1);

		emailEditText = (EditText) findViewById(R.id.member_email_edit_text);
		passwordEditText = (EditText) findViewById(R.id.member_password_edit_text);
		passwordconfirmEditText = (EditText) findViewById(R.id.member_password_confirmation_edit_text);
	}

	public void RegisterClick(View v) {
		email = emailEditText.getText().toString();
		password = passwordEditText.getText().toString();
		password_confirmation = passwordconfirmEditText.getText().toString();
		
		Intent in = new Intent(this, Register2Activity.class);
		Bundle extras = new Bundle();
		extras.putString("email", email);
		extras.putString("password", password);
		extras.putString("password_confirmation", password_confirmation);
		in.putExtras(extras);
		startActivity(in);
	}
}
