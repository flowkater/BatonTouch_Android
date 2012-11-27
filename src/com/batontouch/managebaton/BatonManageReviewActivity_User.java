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

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.batontouch.main.R;
import com.batontouch.model.Task;
import com.batontouch.model.User;
import com.batontouch.utils.Global;
import com.batontouch.utils.ImageDownloader;
import com.batontouch.utils.NetHelper;
import com.google.gson.Gson;

public class BatonManageReviewActivity_User extends Activity {
	private String mResult;
	private String task_id;
	private String auth_token;
	private SharedPreferences mPreferences;
	private RatingBar ratingBar;

	private ImageView user_profile_image;
	private TextView user_name_tv;

	private int StatusCode;

	private String content, star, target_id;

	private ImageButton usercompBtn;

	private EditText content_edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review_user);

		Intent in = getIntent();
		task_id = in.getStringExtra("task_id");
		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
		auth_token = mPreferences.getString("AuthToken", "");

		init_view();

		new GetBatonUser().execute();
	}

	private void init_view() {
		usercompBtn = (ImageButton) findViewById(R.id.usercompBtn);
		usercompBtn.setOnClickListener(new usercompClick());
		content_edit = (EditText) findViewById(R.id.content_edit);
		user_profile_image = (ImageView) findViewById(R.id.user_profile_image);
		user_name_tv = (TextView) findViewById(R.id.user_name_tv);

		TextView reviewPlz = (TextView) findViewById(R.id.reviewPlz);
		TextView reviewComplete = (TextView) findViewById(R.id.reviewComplete);

		String fontPath = "fonts/NanumPen.ttf";
		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

		reviewPlz.setTypeface(tf);
		reviewComplete.setTypeface(tf);

		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
	}

	private class GetBatonUser extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			mResult = NetHelper.DownloadHtml(Global.ServerUrl + "tasks/"
					+ task_id + "?auth_token=" + auth_token);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Log.d("test", mResult);
			Gson gson = new Gson();
			Task task = gson.fromJson(mResult, Task.class);
			User client = task.getClient();
			try {
				String image = client.getProfile_image();
				target_id = client.getId();
				if (image != null) {
					ImageDownloader.download(Global.ServerOriginalUrl + image,
							user_profile_image);
				} else {
					user_profile_image.setImageResource(R.drawable.ic_launcher);
				}
				user_name_tv.setText(client.getName());
			} catch (Exception e) {
				Log.e("batonmanage",
						e.getClass().getName() + " " + e.getMessage()
								+ " BatonManageReview Gson Exception");
				super.onPostExecute(result);
			}
		}
	}

	private class ReviewCreateComplete extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost postRequest = new HttpPost(Global.ServerUrl
						+ "reviews/create_by_client?auth_token=" + auth_token);
				MultipartEntity reqEntity = new MultipartEntity(
						HttpMultipartMode.BROWSER_COMPATIBLE);

				reqEntity.addPart("review[content]", new StringBody(content,
						Charset.forName("UTF-8")));
				reqEntity.addPart("review[star]",
						new StringBody(star, Charset.forName("UTF-8")));
				reqEntity.addPart("review[target_id]", new StringBody(
						target_id, Charset.forName("UTF-8")));
				reqEntity.addPart("review[status]",
						new StringBody("0", Charset.forName("UTF-8")));
				reqEntity.addPart("review[task_id]", new StringBody(task_id,
						Charset.forName("UTF-8")));

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
						+ " Asynctask IOException Reviewcreatecom");
			} catch (Exception e) {
				Log.e("batoncreate", e.getClass().getName() + e.getMessage()
						+ " Asynctask Exception Reviewcreatecom");
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (StatusCode == 201) {
				finish();
				Intent in = new Intent(getApplicationContext(),
						BatonManageCompleteActivity.class);
				Bundle extras = new Bundle();
				extras.putString("task_id", task_id);
				in.putExtras(extras);
				startActivity(in);
			} else if (StatusCode == 422) {

			} else {
			}
			super.onPostExecute(result);
		}
	}

	private class usercompClick implements OnClickListener {
		@Override
		public void onClick(View v) {
			content = content_edit.getText().toString();
			star = String.valueOf(ratingBar.getRating());
			new ReviewCreateComplete().execute();
		}
	}
}
