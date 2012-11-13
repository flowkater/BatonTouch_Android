package com.batontouch.createbaton;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.batontouch.R;

public class BatonCreate_Gift extends Activity {
	private int count;
	static int num;
	// private int[] thumbnails = null;
	ArrayList<MyItem> thumbnails;
	private static boolean[] thumbnailsselection;
	private String[] arrPath;
	private ImageAdapter imageAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batoncreate_postatask_gift);

		// final String[] columns = { MediaStore.Images.Media.DATA,
		// MediaStore.Images.Media._ID };
		// final String orderBy = MediaStore.Images.Media._ID;
		// Cursor imagecursor = managedQuery(
		// MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
		// null, orderBy);
		// int image_column_index =
		// imagecursor.getColumnIndex(MediaStore.Images.Media._ID);

		this.count = 18;
		thumbnails = new ArrayList<MyItem>();

		MyItem mi;
		mi = new MyItem(R.drawable.ic_angle);
		thumbnails.add(mi);
		mi = new MyItem(R.drawable.ic_angle);
		thumbnails.add(mi);
		mi = new MyItem(R.drawable.ic_angle);
		thumbnails.add(mi);
		mi = new MyItem(R.drawable.ic_angle);
		thumbnails.add(mi);
		mi = new MyItem(R.drawable.ic_angle);
		thumbnails.add(mi);
		mi = new MyItem(R.drawable.ic_angle);
		thumbnails.add(mi);
		mi = new MyItem(R.drawable.ic_angle);
		thumbnails.add(mi);
		mi = new MyItem(R.drawable.ic_angle);
		thumbnails.add(mi);
		mi = new MyItem(R.drawable.ic_angle);
		thumbnails.add(mi);
		mi = new MyItem(R.drawable.ic_angle);
		thumbnails.add(mi);

		mi = new MyItem(R.drawable.ic_angle);
		thumbnails.add(mi);
		mi = new MyItem(R.drawable.ic_angle);
		thumbnails.add(mi);
		mi = new MyItem(R.drawable.ic_angle);
		thumbnails.add(mi);
		mi = new MyItem(R.drawable.ic_angle);
		thumbnails.add(mi);
		mi = new MyItem(R.drawable.ic_angle);
		thumbnails.add(mi);

		this.arrPath = new String[this.count];
		this.thumbnailsselection = new boolean[this.count];
	//	GridView imagegrid = (GridView) findViewById(R.id.PhoneImageGrid);
		imageAdapter = new ImageAdapter();
	//	imagegrid.setAdapter(imageAdapter);

		final Button selectBtn = (Button) findViewById(R.id.selectBtn);
		selectBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// // TODO Auto-generated method stub
				final int len = thumbnailsselection.length;
				int cnt = 0;
				String selectImages = "";
				for (int i = 0; i < len; i++) {
					if (thumbnailsselection[i]) {
						cnt++;
					}
				}
				if (cnt == 0) {
					Toast.makeText(getApplicationContext(),
							"í•œ ê°œ ì�´ìƒ�ì�€ ê³¨ë�¼ì•¼ì£ !",
							Toast.LENGTH_LONG).show();
				} else if (cnt <= 3) {
					Toast.makeText(getApplicationContext(),
							"ì„ ë¬¼ " + cnt + "ê°€ì§€ë¥¼ ê³¨ëž�ì–´ìš”!",
							Toast.LENGTH_LONG).show();
					// Log.d("SelectedImages", selectImages);
				} else {

					Toast.makeText(getApplicationContext(),
							"3ê°œë§Œ ê³¨ë�¼ì£¼ì„¸ìš”!", 3000).show();
				}

			}
		});
	}

	public class ImageAdapter extends BaseAdapter {
		private LayoutInflater mInflater;

		public ImageAdapter() {
			mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		public int getCount() {
			return count;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.galleryitem, null);
				holder.imageview = (ImageView) convertView
						.findViewById(R.id.thumbImage);
				holder.checkbox = (CheckBox) convertView
						.findViewById(R.id.itemCheckBox);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.checkbox.setId(position);
			holder.imageview.setId(position);

			// imageview.setLayoutParams(new GridView.LayoutParams(h, w));

			holder.checkbox.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub

					final int len = thumbnailsselection.length;
					int cnt = 0;

					CheckBox cb = (CheckBox) v;
					int id = cb.getId();

					if (thumbnailsselection[id]) {
						cb.setChecked(false);
						thumbnailsselection[id] = false;
					} else {
						cb.setChecked(true);
						thumbnailsselection[id] = true;
					}

					/*
					 * if (num <= 3) {
					 * 
					 * if (thumbnailsselection[id]) { cb.setChecked(false);
					 * thumbnailsselection[id] = false; num--;
					 * 
					 * } else { cb.setChecked(true); thumbnailsselection[id] =
					 * true; cb.setClickable(false);
					 * 
					 * } for (int i = 0; i < thumbnailsselection.length; i++) {
					 * if (thumbnailsselection[i]) { num++;
					 * 
					 * } if (num == 3) { Toast.makeText(getApplicationContext(),
					 * "Wow!", 3000).show();
					 * 
					 * cb.setChecked(false); cb.setClickable(false); //num--; }
					 * 
					 * }
					 * 
					 * Toast.makeText(getApplicationContext(), num + "", 3000)
					 * .show(); num = 0;
					 * 
					 * }
					 */

				}
			});
			// holder.imageview.setOnClickListener(new OnClickListener() {
			//
			// public void onClick(View v) {
			// // TODO Auto-generated method stub
			// int id = v.getId();
			// Intent intent = new Intent();
			// intent.setAction(Intent.ACTION_VIEW);
			// intent.setDataAndType(Uri.parse("file://" + arrPath[id]),
			// "image/*");
			// startActivity(intent);
			// }
			// });
			holder.imageview.setImageResource(thumbnails.get(position).image);
			holder.checkbox.setChecked(thumbnailsselection[position]);
			holder.id = position;
			return convertView;
		}
	}

	class ViewHolder {
		ImageView imageview;
		CheckBox checkbox;
		int id;
	}

	class MyItem {

		MyItem(int image) {

			this.image = image;
		}

		int image;

	}

}
