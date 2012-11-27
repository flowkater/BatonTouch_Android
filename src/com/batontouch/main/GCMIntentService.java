package com.batontouch.main;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.batontouch.utils.Global;
import com.batontouch.utils.WakeLocker;
import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {

	private static final String TAG = "GCMIntentService";
	private String push_message;

	public GCMIntentService() {
		super(Global.SENDER_ID);
	}

	/**
	 * Method called on device registered
	 **/
	@Override
	protected void onRegistered(Context context, String registrationId) {
		Log.i(TAG, "Device registered: regId = " + registrationId);
		// Log.d("NAME", MainActivity.name);
		Global.gcm_regid = registrationId;
	}

	/**
	 * Method called on device un registred
	 * */
	@Override
	protected void onUnregistered(Context context, String registrationId) {
		Log.i(TAG, "Device unregistered");
//		ServerUtilities.unregister(context, registrationId);
	}

	/**
	 * Method called on Receiving a new message
	 * */
	@Override
	protected void onMessage(Context context, Intent intent) {
		Log.i(TAG, "Received message");
		push_message = intent.getExtras().getString("text");

		// Waking up mobile if it is sleeping
		WakeLocker.acquire(getApplicationContext());
		 
		// notifies user
		handler.sendEmptyMessage(0);
		generateNotification(context, push_message);
		 
		// Releasing wake lock
		WakeLocker.release();
	}

	/**
	 * Method called on receiving a deleted message
	 * */
	@Override
	protected void onDeletedMessages(Context context, int total) {
		Log.i(TAG, "Received deleted messages notification");
//		String message = getString("Delete", total);
		// notifies user
//		generateNotification(context, message);
	}

	/**
	 * Method called on Error
	 * */
	@Override
	public void onError(Context context, String errorId) {
		Log.i(TAG, "Received error: " + errorId);
	}

	@Override
	protected boolean onRecoverableError(Context context, String errorId) {
		// log message
		Log.i(TAG, "Received recoverable error: " + errorId);
		return super.onRecoverableError(context, errorId);
	}

	private void generateNotification(final Context context,
			final String message) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				int icon = R.drawable.ic_launcher;
				long when = System.currentTimeMillis();
				NotificationManager notificationManager = (NotificationManager) context
						.getSystemService(Context.NOTIFICATION_SERVICE);
				Notification notification = new Notification(icon, message,
						when);

				String title = context.getString(R.string.app_name);

				Intent notificationIntent = new Intent(context,
						MainActivity.class);
				// set intent so it does not start a new activity
				notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_SINGLE_TOP);
				PendingIntent intent = PendingIntent.getActivity(context, 0,
						notificationIntent, 0);
				notification
						.setLatestEventInfo(context, title, message, intent);
				notification.flags |= Notification.FLAG_AUTO_CANCEL;

				// Play default notification sound
				notification.defaults |= Notification.DEFAULT_SOUND;

				// Vibrate if vibrate is enabled
				notification.defaults |= Notification.DEFAULT_VIBRATE;
				notificationManager.notify(0, notification);
			}
		});
		thread.start();
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			Toast.makeText(getApplicationContext(), push_message,
					Toast.LENGTH_LONG).show();
		}
	};
}