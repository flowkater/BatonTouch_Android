package com.batontouch.profile;

import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.batontouch.R;

public class ProfileActivity_Edit extends Activity {
	final int ACTIVITY_SELECT_IMAGE = 100;
	EditText editTextIntroduce;
	InputStream imageStream;
	ImageButton uploadButton;
	ImageView uploadImg;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_edit);

		editTextIntroduce = (EditText) findViewById(R.id.editTextIntroduce);
		uploadImg = (ImageView) findViewById(R.id.uploadImage);
	//	uploadButton = (ImageButton) findViewById(R.id.uploadbutton);
	}

	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SharedPreferences myPref = getSharedPreferences("myPref",
				Activity.MODE_WORLD_WRITEABLE);

		String name = myPref.getString("name", "");
		editTextIntroduce.setText(name);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SharedPreferences myPref = getSharedPreferences("myPref",
				Activity.MODE_WORLD_WRITEABLE);

		SharedPreferences.Editor myEditor = myPref.edit();
		myEditor.putString("name", editTextIntroduce.getText().toString());

		myEditor.commit();
	}

	public void mOnClick(View v) {

		Intent i = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
		Toast.makeText(getApplicationContext(), "업로드!", 3000).show();
	}

	public void editCompleteBtn(View v) {

		Toast.makeText(getApplicationContext(), "수정 완료!", 3000).show();

		// Intent intent = new Intent();
		// intent.putExtra("editComplete",
		// editTextIntroduce.getText().toString() );
		// setResult(RESULT_OK, intent);

		finish();

	}

	protected void onActivityResult(int requestCode, int resultCode,
			Intent imageReturnedIntent) {
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

		switch (requestCode) {
		case ACTIVITY_SELECT_IMAGE:
			if (resultCode == RESULT_OK) {
				Uri selectedImage = imageReturnedIntent.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String filePath = cursor.getString(columnIndex);
				cursor.close();

				//======================== Bug
				Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath); //Error (Camera Upload)
				//========================
				
				uploadImg.setImageBitmap(yourSelectedImage);
				uploadImg.setScaleType(ImageView.ScaleType.FIT_XY);
				uploadImg.setAdjustViewBounds(true);

				// Uri selectedImage = imageReturnedIntent.getData();
				//
				// try {
				// imageStream =
				// getContentResolver().openInputStream(selectedImage);
				// } catch (FileNotFoundException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				// Bitmap yourSelectedImage =
				// BitmapFactory.decodeStream(imageStream);
				//
				uploadImg.setImageBitmap(yourSelectedImage);

				Toast.makeText(getApplicationContext(), "Done!", 3000).show();
			}

			else {

				Toast.makeText(getApplicationContext(), "fail", 3000).show();

			}

		}
	}
}
