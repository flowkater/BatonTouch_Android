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
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.batontouch.utils.Global;
import com.google.android.gcm.GCMRegistrar;

public class Register2Activity extends Activity {
	private EditText member_phone_edit_text, member_company_edit_text,
			member_authnum_edit_text;
	private String email, password, password_confirmation, phone, company,
			authnum;
	private String ranNum, message;

	private int StatusCode;
	
	private boolean auth_status = false;
	
	private ImageButton sendBtn, authBtn, registerBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerpage_2);
		
		Intent in = getIntent();
		Bundle extras = in.getExtras();
		
		email = extras.getString("email");
		password = extras.getString("password");
		password_confirmation = extras.getString("password_confirmation");
		
		member_phone_edit_text = (EditText)findViewById(R.id.member_phone_edit_text);
		member_authnum_edit_text = (EditText)findViewById(R.id.member_authnum_edit_text);
		member_company_edit_text = (EditText)findViewById(R.id.member_company_edit_text);
		
		sendBtn = (ImageButton)findViewById(R.id.sendBtn);
		authBtn = (ImageButton)findViewById(R.id.authBtn);
		registerBtn = (ImageButton)findViewById(R.id.registerBtn);
		
		sendBtn.setOnClickListener(new sendClick());
		authBtn.setOnClickListener(new authClick());
		registerBtn.setOnClickListener(new regiClick());
		
		
//		TelephonyManager telManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//		phone = telManager.getLine1Number();
		ranNum = Global.randomRange(1000, 9999) + "";
		
		message = "[인증번호:" + ranNum + "]" + " 바톤터치에서 보낸 메시지입니다.";
	}
	
	public class sendClick implements OnClickListener{
		@Override
		public void onClick(View v) {
			sendSMS(phone, message);
		}
	}
	public class authClick implements OnClickListener{
		@Override
		public void onClick(View v) {
			String  authNum = member_authnum_edit_text.getText().toString();
			if ( authNum.equals(ranNum) ) {
				// 성공
			}else{
				// 실패
			}
		}
	}
	public class regiClick implements OnClickListener{
		@Override
		public void onClick(View v) {
			
			new Usercreate().execute(email, password, password_confirmation, Global.gcm_regid, company, phone);
		}
	}
	
	private void sendSMS(String phoneNum, String message){
    	PendingIntent pi = PendingIntent.getBroadcast(this, 0, new Intent(), 0);
    	SmsManager sms = SmsManager.getDefault();
    	sms.sendTextMessage(phoneNum, null, message, pi, null);
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
				reqEntity.addPart("user[company]", new StringBody(infos[4],
						Charset.forName("UTF-8")));
				reqEntity.addPart("user[phone]", new StringBody(infos[5],
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
				GCMRegistrar.setRegisteredOnServer(getApplicationContext(),
						true);
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
