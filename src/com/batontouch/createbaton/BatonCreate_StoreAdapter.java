package com.batontouch.createbaton;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.batontouch.R;
import com.batontouch.model.Store;
import com.batontouch.utils.Global;
import com.batontouch.utils.ImageDownloader;

public class BatonCreate_StoreAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Store> mStores;
	private Store store;
	private Bundle extras;

	public BatonCreate_StoreAdapter(Context context, ArrayList<Store> mStores, Bundle extras) {
		this.context = context;
		this.mStores = mStores;
		this.extras = extras;
	}

	public final int getCount() {
		return Math.min(32, mStores.size());
	}

	public final Object getItem(int position) {
		return mStores.get(position % mStores.size());
	}

	public final long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		store = mStores.get(position % mStores.size());
		if (convertView == null) {
			imageView = new ImageView(context);
			final int h = (int) (90 * context.getResources()
					.getDisplayMetrics().density + 0.5f);
			final int w = (int) (90 * context.getResources()
					.getDisplayMetrics().density + 0.5f);
			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			imageView.setLayoutParams(new GridView.LayoutParams(h, w));
		} else {
			imageView = (ImageView) convertView;
		}

		if (store != null) {
			String image = store.getImage();
			if (image != null) {
				ImageDownloader.download(Global.ServerOriginalUrl + image,
						imageView);
			} else {
				imageView.setImageResource(R.drawable.ic_launcher);
			}
		}

		imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				store = mStores.get(position);
				String store_id = store.getId();
				startAct(BatonCreate_PostATask_Detail.class, store_id, extras);
			}
		});

		return imageView;
	}

	public void startAct(Class<?> cls, String store_id, Bundle extras) {
		Intent in = new Intent(context, cls);
		in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		extras.putString("store_id", store_id);
		in.putExtras(extras);
		context.startActivity(in);
	}
}
