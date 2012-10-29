package com.batontouch.setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.batontouch.R;

public class SettingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
	}
	public void privacyBtn(View v){
		
		switch (v.getId()) {
		case R.id.passwordBtn:
			Toast.makeText(getApplicationContext(), "비밀번호 재설정", 3000).show();
			break;
		case R.id.varificationBtn:
			Toast.makeText(getApplicationContext(), "인증번호 수정", 3000).show();
			break;
		case R.id.paymentBtn:
			Toast.makeText(getApplicationContext(), "결제정보 수정/확인", 3000).show();
			break;

		}
		
	}
	
}
