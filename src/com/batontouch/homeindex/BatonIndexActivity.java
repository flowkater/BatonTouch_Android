package com.batontouch.homeindex;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.batontouch.R;

public class BatonIndexActivity extends Activity {

	Button distanceBtn, priceBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batonindex);

		distanceBtn = (Button) findViewById(R.id.distanceBtn);
		priceBtn = (Button) findViewById(R.id.priceBtn);

	}

	public void mOnClick(View v) {
		new AlertDialog.Builder(this).setTitle("거리를 선택하세요.")
				.setIcon(R.drawable.ic_launcher).setItems(R.array.distance,

				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String[] distance = getResources().getStringArray(
								R.array.distance);

						distanceBtn.setText(distance[which] + "");
					}
				}).setNegativeButton("취소", null).show();
	}
	
	public void mOnClick2(View v) {
		new AlertDialog.Builder(this).setTitle("가격 때를 설정 하세요.")
				.setIcon(R.drawable.ic_launcher).setItems(R.array.price,

				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String[] distance = getResources().getStringArray(
								R.array.price);

						priceBtn.setText(distance[which] + "");
					}
				}).setNegativeButton("취소", null).show();
	}

}
