package com.batontouch.homeindex;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.batontouch.main.R;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapCompassManager;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.nmapmodel.NMapPlacemark;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;

public class NMapViewerActivity extends NMapActivity {
	private static final String API_KEY = "c6cbabefd0ffbcafa3317b9df1f069e4";

	private NMapView mMapView; // MapView Variable
	private NMapController mMapController; // MapController Variable
	private NMapLocationManager mMapLocationManager;
	private NMapMyLocationOverlay mMyLocationOverlay; // MyLocation Overlay
	private NMapOverlayManager mOverlayManager;
	private NMapViewerResourceProvider mMapViewerResourceProvider;
	private NMapCompassManager mMapCompassManager;

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

		mMapView.setOnMapStateChangeListener(onMapViewStateChangeListener); // StateChange
		mMapView.setOnMapViewTouchEventListener(onMapViewTouchEventListener); // TouchEvent

		mMapController = mMapView.getMapController();
		mMapView.setBuiltInZoomControls(true, null);

		// create resource provider
		mMapViewerResourceProvider = new NMapViewerResourceProvider(this);
		super.setMapDataProviderListener(onDataProviderListener);

		// create overlay manager
		mOverlayManager = new NMapOverlayManager(this, mMapView,
				mMapViewerResourceProvider);

		// location manager
		mMapLocationManager = new NMapLocationManager(this);
		mMapLocationManager
				.setOnLocationChangeListener(onMyLocationChangeListener);

		// compass manager
		mMapCompassManager = new NMapCompassManager(this);

		mMyLocationOverlay = mOverlayManager.createMyLocationOverlay(
				mMapLocationManager, mMapCompassManager);

		int markerId = NMapPOIflagType.PIN;

		NMapPOIdata poiData = new NMapPOIdata(3, mMapViewerResourceProvider);
		poiData.beginPOIdata(3);
		poiData.addPOIitem(126.89470767974854, 37.48264941044898, "Baton1",
				markerId, 0);
		poiData.addPOIitem(126.89453601837158, 37.48440321935226, "Baton2",
				markerId, 0);
		poiData.addPOIitem(126.89906358718872, 37.485475917127985, "Baton3",
				markerId, 0);
		poiData.endPOIdata();
		
		NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);
		
		poiDataOverlay.showAllPOIdata(0);
		
//		mOverlayManager.setOnCalloutOverlayListener(this);
	}

	public void onMyLocationClick(View v) {
		startMyLocation();
	}

	private final NMapLocationManager.OnLocationChangeListener onMyLocationChangeListener = new NMapLocationManager.OnLocationChangeListener() {

		@Override
		public void onLocationUpdateTimeout(NMapLocationManager locationManager) {
			Log.d("tag", "Your current location is temporaily unavailable.");
		}

		@Override
		public void onLocationUnavailableArea(
				NMapLocationManager locationManager, NGeoPoint MyLocation) {
			Log.d("tag", "Your current location is unavailable area.");
			stopMyLocation();
		}

		@Override
		public boolean onLocationChanged(NMapLocationManager locationManager,
				NGeoPoint MyLocation) {
			if (mMapController != null) {
				mMapController.animateTo(MyLocation);
			}
			return true;
		}
	};

	// Find My Location
	private void startMyLocation() {
		// MyLocationOverlay is not null..
		if (mMyLocationOverlay != null) {
			// Manager no has Overlay, and then Add Overlay
			if (!mOverlayManager.hasOverlay(mMyLocationOverlay)) {
				mOverlayManager.addOverlay(mMyLocationOverlay);
			}

			if (mMapLocationManager.isMyLocationEnabled()) {

				if (!mMapView.isAutoRotateEnabled()) {
					mMyLocationOverlay.setCompassHeadingVisible(true);

					mMapCompassManager.enableCompass();

					mMapView.setAutoRotateEnabled(true, false);

					mMapView.requestLayout();
				} else {
					stopMyLocation();
				}

				mMapView.postInvalidate();
			} else {
				boolean isMyLocationEnabled = mMapLocationManager
						.enableMyLocation(false);
				if (!isMyLocationEnabled) {
					Log.d("tag",
							"Please enable a My Location source in system settings");

					Intent goToSettings = new Intent(
							Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					startActivity(goToSettings);

					return;
				}
			}
		}
	}

	private void stopMyLocation() {
		if (mMyLocationOverlay != null) {
			mMapLocationManager.disableMyLocation();

			if (mMapView.isAutoRotateEnabled()) {
				mMyLocationOverlay.setCompassHeadingVisible(false);

				mMapCompassManager.disableCompass();

				mMapView.setAutoRotateEnabled(false, false);

				mMapView.requestLayout();
			}
		}
	}

	private final NMapActivity.OnDataProviderListener onDataProviderListener = new NMapActivity.OnDataProviderListener() {
		@Override
		public void onReverseGeocoderResponse(NMapPlacemark placeMark,
				NMapError errInfo) {

		}
	};

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
//				mMapController.setMapCenter(new NGeoPoint(126.978371,
//						37.5666091), 11);
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
