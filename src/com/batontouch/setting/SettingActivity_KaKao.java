package com.batontouch.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.batontouch.R;

public class SettingActivity_KaKao extends Activity {

	EditText editTextKaKao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_talkinkakao);
		editTextKaKao = (EditText) findViewById(R.id.editText);
	}
	
	public void mOnClick(View v){
		
		String text = editTextKaKao.getText().toString();
		
		
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		//intent.putExtra(Intent.EXTRA_SUBJECT, "ㅋㅋㅋㅋ 우아 ㅋㅋ 카톡 신기하다 ㅋㅋ");
		intent.putExtra(Intent.EXTRA_TEXT, text);
		startActivity(intent);
		
	}

}
