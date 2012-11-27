package com.batontouch.homeindex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.batontouch.main.R;
import com.batontouch.model.Giftcon;
import com.batontouch.model.Task;
import com.batontouch.model.User;
import com.batontouch.utils.Global;
import com.batontouch.utils.ImageDownloader;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;

public class BatonShowActivity extends Activity {

	private TextView dealNametv, fromloctv, toloctv, dealDescriptiontv,
			dealCalldatetv, dealEnddatetv, dealStatus, dealResttime, date,
			specifics, gifticon, fromHere, toHere,runnergift;
	private String task_id;
	private String mResult; // GetBatonShow AsyncTask
	private int StatusCode;
	private SharedPreferences mPreferences;
	private String auth_token;
	private ImageButton suggestBtn;

	private ProgressDialog progressdialog;

	private ImageView user_image, company_image, facebook_auth, phone_auth,giftcon_image;
	private TextView user_name;
	
	private String userimage, companyimage, giftconimage;

	private boolean auth_client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonindex_detail_waiting);
		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);

		auth_token = mPreferences.getString("AuthToken", "");
		auth_client = mPreferences.getBoolean("AuthClient", false);

		Intent intent = getIntent();
		task_id = intent.getStringExtra("task_id");
		
		init_view();

		DialogProgress();
		new GetBatonShow().execute();
	}

	private void init_view() {
		dealNametv = (TextView) findViewById(R.id.name); // 딜이름
		date = (TextView) findViewById(R.id.date); // 딜 생성 날짜
		dealStatus = (TextView) findViewById(R.id.status); // 딜 상태
		//-- date 계산
		dealResttime = (TextView) findViewById(R.id.dealResttime); // 딜남은시간
		//--
		dealDescriptiontv = (TextView) findViewById(R.id.dealDescription); // 딜 상세 설명
		fromloctv = (TextView) findViewById(R.id.fromloctv); // 경유지
		toloctv = (TextView) findViewById(R.id.toloctv); // 도착지
		giftcon_image = (ImageView)findViewById(R.id.giftcon_image); // 기프티콘이미지
		gifticon = (TextView) findViewById(R.id.gifticon); // 기프티콘 이름
		
		user_image = (ImageView)findViewById(R.id.user_image); // 유저이미지
		company_image = (ImageView)findViewById(R.id.company_image); // 유저소속이미지
		facebook_auth = (ImageView) findViewById(R.id.facebook_auth); // 유저페이스북인증
		phone_auth = (ImageView)findViewById(R.id.phone_auth); // 유저전화번호인증
		user_name = (TextView)findViewById(R.id.user_name); // 유저이름 
		
		specifics = (TextView)findViewById(R.id.specifics); // 자세한사항TextView
		fromHere = (TextView)findViewById(R.id.fromHere); //TextView
		toHere = (TextView)findViewById(R.id.toHere); // TextVIew
		runnergift = (TextView)findViewById(R.id.runnergift); // TextView
		
		suggestBtn = (ImageButton)findViewById(R.id.suggestBtn);

		String fontPath = "fonts/NanumPen.ttf";
		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
		dealNametv.setTypeface(tf);
		dealStatus.setTypeface(tf);
		date.setTypeface(tf);
		dealResttime.setTypeface(tf);
		specifics.setTypeface(tf);
		runnergift.setTypeface(tf);
		fromHere.setTypeface(tf);
		toHere.setTypeface(tf);
	}

	private class GetBatonShow extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			mResult = NetHelper.DownloadHtml(Global.ServerUrl + "tasks/"
					+ task_id + "?auth_token=" + auth_token);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Gson gson = new Gson();
			Task task = gson.fromJson(mResult, Task.class);
			User user = task.getUser();
			Giftcon giftcon = task.getGiftcon();
			Log.d("batonindex", mResult + "");
			try {
				int status = task.getStatus();
				boolean current_user = task.isCurrent_user();
				userimage = user.getProfile_image();
//				companyimage 
				giftconimage = giftcon.getImage();
				
				dealNametv.setText(task.getName());
				date.setText(task.getDay());
				dealDescriptiontv.setText(task.getDescription());
				dealStatus.setText(Global.userJudge(status));
				fromloctv.setText(task.getFromloc());
				toloctv.setText(task.getToloc());
				
				if(user.isFacebook()){
					facebook_auth.setVisibility(View.VISIBLE);
				}
				if(user.isPhone_auth()){
					phone_auth.setVisibility(View.VISIBLE);
				}
				user_name.setText(user.getName());
				gifticon.setText(giftcon.getName());
				
				// 대기중일때, auth_client true 일때, current_user 가 아닐때, 이 태스크에서 비딩을
				// 걸수있따.
				if (status == 0 && auth_client && !current_user) {
					suggestBtn.setVisibility(View.VISIBLE);
				}
				
				if (userimage != null) {
					ImageDownloader.download(Global.ServerOriginalUrl + userimage, user_image);
				}else{
					user_image.setImageResource(R.drawable.ic_launcher);
				}
				
				if (giftconimage != null) {
					ImageDownloader.download(Global.ServerOriginalUrl + giftconimage, giftcon_image);
				}else{
					giftcon_image.setImageResource(R.drawable.ic_launcher);
				}
//				user_image
//				company_image = (ImageView)findViewById(R.id.company_image); // 유저소속이미지
				
			} catch (Exception e) {
				Log.e("batonindex",
						e.getClass().getName() + " " + e.getMessage()
								+ " BatonShowActivity GetBatonShow");
			}
			progressdialog.dismiss();
			super.onPostExecute(result);
		}
	}

	public void SuggestButtonClick(View v) {
		SuggestDialog();
	}

	// Client 가 Task에 제안을 할때 이 클래스 호출
	class TradestatCreate extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost postRequest = new HttpPost(Global.ServerUrl + "tasks/"
						+ task_id + "/tradestats?auth_token=" + auth_token);
				MultipartEntity reqEntity = new MultipartEntity(
						HttpMultipartMode.BROWSER_COMPATIBLE);

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
			progressdialog.dismiss();
			if (StatusCode == 201) {
				Toast.makeText(getApplicationContext(), "제안 되었습니다.",
						Toast.LENGTH_SHORT).show();
				suggestBtn.setVisibility(View.GONE);

			} else {
				Toast.makeText(getApplicationContext(),
						"오류가 발생했습니다. 잠시후에 다시 시도해주세요.", Toast.LENGTH_SHORT)
						.show();
			}

			super.onPostExecute(result);
		}
	}

	// 제안 다이얼로그 method
	private void SuggestDialog() {
		AlertDialog.Builder altBld = new AlertDialog.Builder(this);
		altBld.setMessage("제안 하시겠습니까?").setCancelable(false)
				.setPositiveButton("넹", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						DialogProgress();
						new TradestatCreate().execute();
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

	public void DialogProgress() {
		progressdialog = ProgressDialog.show(BatonShowActivity.this, "",
				"잠시만 기다려 주세요 ...", true);
	}
}
