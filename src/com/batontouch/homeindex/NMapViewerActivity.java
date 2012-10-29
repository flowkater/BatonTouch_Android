package com.batontouch.homeindex;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.batontouch.R;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;

public class NMapViewerActivity extends NMapActivity {
	private static final String API_KEY = "c6cbabefd0ffbcafa3317b9df1f069e4";
	private NMapView mMapView; // MapView Variable
	private NMapController mMapController; // MapController Variable

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// create nmapviewer layout
		setContentView(R.layout.nmapviewer);

		mMapView = (NMapView) findViewById(R.id.mapView);
		mMapView.setApiKey(API_KEY); // API_KEY Setting

		mMapView.setClickable(true);
		mMapView.setEnabled(true);
		mMapView.setFocusable(true);
		mMapView.setFocusableInTouchMode(true);

		mMapView.setOnMapStateChangeListener(onMapViewStateChangeListener); // State
																			// Change
		mMapView.setOnMapViewTouchEventListener(onMapViewTouchEventListener); // Touch
																				// Event

		mMapController = mMapView.getMapController();
		mMapView.setBuiltInZoomControls(true, null);
	}

	private final NMapView.OnMapViewTouchEventListener onMapViewTouchEventListener = new NMapView.OnMapViewTouchEventListener() {
		@Override
		public void onTouchDown(NMapView arg0, MotionEvent arg1) {
			Log.d("tag", "onTouchDOwn");
		}

		@Override
		public void onSingleTapUp(NMapView arg0, MotionEvent arg1) {
		}

		@Override
		public void onScroll(NMapView arg0, MotionEvent arg1, MotionEvent arg2) {
		}

		@Override
		public void onLongPressCanceled(NMapView arg0) {
		}

		@Override
		public void onLongPress(NMapView arg0, MotionEvent arg1) {
		}
	};

	private final NMapView.OnMapStateChangeListener onMapViewStateChangeListener = new NMapView.OnMapStateChangeListener() {
		@Override
		public void onZoomLevelChange(NMapView arg0, int arg1) {
		}

		@Override
		public void onMapInitHandler(NMapView mapView, NMapError errorInfo) {
			Log.d("tag", "Init handler");
			if (errorInfo == null) {
				 mMapController.setMapCenter(new NGeoPoint(126.978371,
				 37.5666091), 11);
			} else { // fail
				Log.e("tag", "OnMapInitHanlder: error=" + errorInfo.toString());
			}
		}

		@Override
		public void onMapCenterChangeFine(NMapView arg0) {
		}

		@Override
		public void onMapCenterChange(NMapView arg0, NGeoPoint arg1) {
		}

		@Override
		public void onAnimationStateChange(NMapView arg0, int arg1, int arg2) {
		}
	};

}
