package com.batontouch.setting;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.batontouch.kakaoLink.KakaoLink;
import com.batontouch.main.LoginActivity;
import com.batontouch.main.R;

public class SettingActivity extends Activity {
	private String encoding = "UTF-8";
	private SharedPreferences mPreferences;
	private ProgressDialog progressdialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
	}

	public void privacySettingBtn(View v) {

		switch (v.getId()) {
	
		// case R.id.faebookBtn:
		// Toast.makeText(getApplicationContext(), "페이스북 연동", 3000).show();
		// Intent intent2 = new Intent();
		// startActivity(intent2);
		// break;
		// case R.id.varificationBtn:
		// Toast.makeText(getApplicationContext(), "인증 전화번호 수정", 3000).show();
		// Intent intent3 = new Intent(getApplicationContext(),
		// Setting_Verification.class);
		// startActivity(intent3);
		// break;

		// case R.id.dropoutBtn:
		// Toast.makeText(getApplicationContext(), "회원탈퇴", 3000).show();
		// Intent intent5 = new Intent(getApplicationContext(),
		// Setting_Dropout.class);
		// startActivity(intent5);
		// break;

		}

	}

	public void generalSettingBtn(View v) {

		switch (v.getId()) {
		// case R.id.recommendToFriendsBtn:
		//
		// Intent intent = new Intent(getApplicationContext(),
		// com.batontouch.kakaoLink.MainActivity.class);
		// startActivity(intent);
		//
		// Toast.makeText(getApplicationContext(), "바톤터치를 친구에게 추천",
		// 3000).show();
		// break;
		
		case R.id.annoucementBtn:
			Toast.makeText(getApplicationContext(), "공지사항", 3000).show();
			Intent intent1 = new Intent(getApplicationContext(),
					Setting_Announcement.class);
			startActivity(intent1);
			break;
			
		case R.id.howItWorks:
			Toast.makeText(getApplicationContext(), "이용방법", 3000).show();
			Intent intent2 = new Intent(getApplicationContext(),
					Setting_HowItWorks.class);
			startActivity(intent2);
			break;
		
		case R.id.paymentBtn:
			Toast.makeText(getApplicationContext(), "결제정보 수정/확인", 3000).show();
			Intent intent3 = new Intent(getApplicationContext(),
					Setting_Payment.class);
			startActivity(intent3);
			break;
			
		case R.id.FAQBtn:
			Intent intent5 = new Intent(getApplicationContext(),
					Setting_FAQ.class);
			startActivity(intent5);
			break;
			
		case R.id.alarmBtn:
			Toast.makeText(getApplicationContext(), "알림 설정", 3000).show();
			Intent intent6 = new Intent(getApplicationContext(),
					Setting_Alarm.class);
			startActivity(intent6);
			break;
			
		case R.id.logoutBtn:
			LogoutDialog();
			break;
		

		
		case R.id.suggestionBtn:
			Toast.makeText(getApplicationContext(), "문의 / 제안 / 신고", 3000)
					.show();
			Intent intent8 = new Intent(getApplicationContext(),
					Setting_Suggestion.class);
			startActivity(intent8);
			break;
		
			
		

		case R.id.policyBtn:
			Toast.makeText(getApplicationContext(), "이용약관", 3000).show();
			Intent intent9 = new Intent(getApplicationContext(),
					Setting_Policy.class);
			startActivity(intent9);
			break;
		
		}
	}

	public void alarmSettingBtn(View v) {

		switch (v.getId()) {
	

		// case R.id.pushAreaBtn:
		// Toast.makeText(getApplicationContext(), "관심 푸쉬지역 설정", 3000).show();
		// Intent intent2 = new Intent(getApplicationContext(),
		// Setting_PushArea.class);
		// startActivity(intent2);
		// break;
		// case R.id.pushRangeBtn:
		// Toast.makeText(getApplicationContext(), "푸쉬 범위 설정", 3000).show();
		// break;
		}
	}


	public void sendUrlLink(View v) throws NameNotFoundException {
		// Recommended: Use application context for parameter.
		KakaoLink kakaoLink = KakaoLink.getLink(getApplicationContext());

		// check, intent is available.
		if (!kakaoLink.isAvailableIntent()) {
			Toast.makeText(getApplicationContext(), "카카오톡이 설치되지 않어요~.", 3000)
					.show();
			return;
		}

		/**
		 * @param activity
		 * @param url
		 * @param message
		 * @param appId
		 * @param appVer
		 * @param appName
		 * @param encoding
		 */
		kakaoLink.openKakaoLink(this, "http://batontouch.me",
				"친구야! 나랑 같이 바톤터치하자!", getPackageName(), getPackageManager()
						.getPackageInfo(getPackageName(), 0).versionName,
				"KakaoLink Test App", encoding);

	}

	// 다이얼로그 method
	private void LogoutDialog() {
		AlertDialog.Builder altBld = new AlertDialog.Builder(this);
		altBld.setMessage("로그아웃 하시겠습니까?")
				.setCancelable(false)
				.setPositiveButton("넹", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Logout();
						finish();
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

	// 값(ALL Data) 삭제하기
	private void Logout() {
		SharedPreferences preftoken = getSharedPreferences("CurrentUser",
				MODE_PRIVATE);
		SharedPreferences.Editor editortoken = preftoken.edit();
		editortoken.clear();
		editortoken.commit();
		
		Intent in = new Intent(getApplicationContext(),
				LoginActivity.class);
		startActivity(in);
	}

	public void DialogProgress() {
		progressdialog = ProgressDialog.show(SettingActivity.this, "",
				"잠시만 기다려 주세요 ...", true);
		// 창을 내린다.
		// progressdialog.dismiss();
	}
}
