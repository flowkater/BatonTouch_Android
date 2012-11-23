package com.batontouch.createbaton;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.batontouch.R;
import com.batontouch.model.Giftitem;
import com.batontouch.utils.Global;
import com.batontouch.utils.ImageDownloader;

public class BatonCreate_GiftitemAdapter extends ArrayAdapter<Giftitem> {
	private Context context;
	private LayoutInflater Inflater;
	private ArrayList<Giftitem> mGiftitems;
	private Giftitem giftitem;
	private Bundle extras;
	int mResource;

	public BatonCreate_GiftitemAdapter(Context context, int mResource,
			ArrayList<Giftitem> mGiftitems, Bundle extras) {
		super(context, mResource, mGiftitems);
		this.context = context;
		this.mResource = mResource;
		this.mGiftitems = mGiftitems;
		this.extras = extras;
	}

	// 각 항목의 뷰 생성
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		giftitem = mGiftitems.get(position);
		if (convertView == null) {
			Inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = Inflater.inflate(mResource, parent, false);
			holder = new ViewHolder();

			holder.image = (ImageView) convertView.findViewById(R.id.image);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.price = (TextView) convertView.findViewById(R.id.price);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (giftitem != null) {
			String image = giftitem.getImage();
			if (image != null) {
				ImageDownloader.download(Global.ServerOriginalUrl + image,
						holder.image);
			} else {
				holder.image.setImageResource(R.drawable.ic_launcher);
			}
			holder.name.setText(giftitem.getName());
			holder.price.setText(giftitem.getPrice());
		}

		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				giftitem = mGiftitems.get(position);
				Intent intent = new Intent(context,
						BatonCreate_PostATask_Selected.class);
				extras.putString("gift_id", giftitem.getId());
				extras.putString("gift_image", giftitem.getImage());
				extras.putString("gift_name", giftitem.getName());
				extras.putString("gift_description", giftitem.getDescription());
				extras.putString("gift_price", giftitem.getPrice());
				extras.putString("gift_fromdate", giftitem.getFromdate());
				extras.putString("gift_todate", giftitem.getTodate());
				extras.putString("gift_image_big", giftitem.getImage_big());
				intent.putExtras(extras);
				context.startActivity(intent);
			}
		});
		return convertView;
	}

	class ViewHolder {
		ImageView image;
		TextView name;
		TextView price;
	}
}