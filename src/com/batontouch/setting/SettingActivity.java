package com.batontouch.setting;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.batontouch.R;
import com.batontouch.kakaoLink.KakaoLink;

public class SettingActivity extends Activity {
	private String encoding = "UTF-8";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
	}

	public void privacySettingBtn(View v) {

		switch (v.getId()) {
		case R.id.logoutBtn:
			Toast.makeText(getApplicationContext(), "로그아웃", 3000).show();
			Intent intent1 = new Intent(getApplicationContext(), Setting_Logout.class);
			startActivity(intent1);
			break;
		case R.id.faebookBtn:
			Toast.makeText(getApplicationContext(), "페이스북 연동", 3000).show();
			Intent intent2 = new Intent();
		//	startActivity(intent2);
			break;
//		case R.id.varificationBtn:
//			Toast.makeText(getApplicationContext(), "인증 전화번호 수정", 3000).show();
//			Intent intent3 = new Intent(getApplicationContext(), Setting_Verification.class);
//			startActivity(intent3);
//			break;
		case R.id.paymentBtn:
			Toast.makeText(getApplicationContext(), "결제정보 수정/확인", 3000).show();
			Intent intent4 = new Intent(getApplicationContext(), Setting_Payment.class);
			startActivity(intent4);
			break;
		case R.id.dropoutBtn:
			Toast.makeText(getApplicationContext(), "회원탈퇴", 3000).show();
			Intent intent5 = new Intent(getApplicationContext(), Setting_Dropout.class);
			startActivity(intent5);
			break;

		}

	}

	public void generalSettingBtn(View v){
		
		switch (v.getId()) {
//		case R.id.recommendToFriendsBtn:
//			
//			Intent intent = new Intent(getApplicationContext(), com.batontouch.kakaoLink.MainActivity.class);
//			startActivity(intent);
//			
//			Toast.makeText(getApplicationContext(), "바톤터치를 친구에게 추천", 3000).show();
//			break;

//		case R.id.talkInKakao:
//			Toast.makeText(getApplicationContext(), "카톡에서 얘기합시다!", 3000).show();
//			Intent intent = new Intent(getApplicationContext(), SettingActivity_KaKao.class);
//			startActivity(intent);
//			break;
		
		
		case R.id.FAQBtn:
			Intent intent1 = new Intent(getApplicationContext(), Setting_FAQ.class);
			startActivity(intent1);
			break;
		case R.id.suggestionBtn:
			Toast.makeText(getApplicationContext(), "문의 / 제안 / 신고", 3000).show();
			Intent intent2 = new Intent(getApplicationContext(), Setting_Suggestion.class);
			startActivity(intent2);
			break;
		}
	}

	public void alarmSettingBtn(View v) {

		switch (v.getId()) {
		case R.id.alarmBtn:
			Toast.makeText(getApplicationContext(), "알림 설정", 3000).show();
			Intent intent1 = new Intent(getApplicationContext(), Setting_Alarm.class);
			startActivity(intent1);
			break;

		case R.id.pushAreaBtn:
			Toast.makeText(getApplicationContext(), "관심 푸쉬지역 설정", 3000).show();
			Intent intent2 = new Intent(getApplicationContext(), Setting_PushArea.class);
			startActivity(intent2);
			break;
		case R.id.pushRangeBtn:
			Toast.makeText(getApplicationContext(), "푸쉬 범위 설정", 3000).show();
			break;
		}
	}

	public void buttontouchSettingBtn(View v) {

		switch (v.getId()) {
		case R.id.annoucementBtn:
			Toast.makeText(getApplicationContext(), "공지사항", 3000).show();
			Intent intent1 = new Intent(getApplicationContext(), Setting_Announcement.class);
			startActivity(intent1);
			break;

		case R.id.policyBtn:
			Toast.makeText(getApplicationContext(), "이용약관", 3000).show();
			Intent intent2 = new Intent(getApplicationContext(), Setting_Policy.class);
			startActivity(intent2);
			break;
		}

	}
	
	public void sendUrlLink(View v) throws NameNotFoundException {
		// Recommended: Use application context for parameter.
		KakaoLink kakaoLink = KakaoLink.getLink(getApplicationContext());

		// check, intent is available.
		if (!kakaoLink.isAvailableIntent()) {
			Toast.makeText(getApplicationContext(), "카카오톡이 설치되지 않어요~.", 3000).show();			
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
		kakaoLink.openKakaoLink(this, 
				"http://batontouch.me", 
				"친구야! 나랑 같이 바톤터치하자!", 
				getPackageName(), 
				getPackageManager().getPackageInfo(getPackageName(), 0).versionName, 
				"KakaoLink Test App", 
				encoding);
		
		
	}
	
}
