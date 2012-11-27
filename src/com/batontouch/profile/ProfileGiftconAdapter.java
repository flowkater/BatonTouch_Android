package com.batontouch.profile;

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

import com.batontouch.main.R;
import com.batontouch.model.Giftcon;
import com.batontouch.utils.Global;
import com.batontouch.utils.ImageDownloader;

public class ProfileGiftconAdapter extends ArrayAdapter<Giftcon> {
	Context mContext;
	LayoutInflater mInflater;
	ArrayList<Giftcon> giftcons;
	int mResource;
	Giftcon giftcon;

	public ProfileGiftconAdapter(Context context, int mResource,
			ArrayList<Giftcon> giftcons) {
		super(context, mResource, giftcons);
		this.mContext = context;
		this.mResource = mResource;
		this.giftcons = giftcons;
	}

	// 각 항목의 뷰 생성
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		giftcon = giftcons.get(position);
		if (convertView == null) {
			this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(mResource, null);
			
			holder = new ViewHolder();
			
			holder.giftconimage = (ImageView)convertView.findViewById(R.id.image);
			holder.giftconname = (TextView)convertView.findViewById(R.id.name);
			holder.giftconprice = (TextView)convertView.findViewById(R.id.price);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		if (giftcon != null) {
			String image = giftcon.getImage();
			if (image != null) {
				ImageDownloader.download(Global.ServerOriginalUrl + image, holder.giftconimage);
			}else{
				holder.giftconimage.setImageResource(R.drawable.ic_launcher);
			}
			
			holder.giftconname.setText(giftcon.getName());
			holder.giftconprice.setText(giftcon.getPrice());
		}

		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				giftcon = giftcons.get(position);
				String giftcon_id = giftcon.getId();
				Intent intent = new Intent(mContext, ProfileActivity_Manage_Gifticonpage.class);
				Bundle extras = new Bundle();
				extras.putString("giftcon_id", giftcon_id);
				intent.putExtras(extras);
				mContext.startActivity(intent);
			}
		});

		return convertView;
	}
	
	class ViewHolder{
		ImageView giftconimage;
		TextView giftconname;
		TextView giftconprice;
	}
}