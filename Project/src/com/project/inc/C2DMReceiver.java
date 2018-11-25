package com.project.inc;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.c2dm.C2DMBaseReceiver;
import com.google.android.c2dm.C2DMessaging;

public class C2DMReceiver extends C2DMBaseReceiver {
	private NotificationManager mNM ;

	public C2DMReceiver() {
		super("this.is.not@real.biz");
	}

	@Override
	public void onRegistrered(Context context, String registrationId) {
		Log.w("C2DMReceiver-onRegistered", registrationId);
		Log.v("hi_I am C2DMReceiver", C2DMessaging.getRegistrationId(this));
	}
	
	@Override
	public void onUnregistered(Context context) {
		Log.w("C2DMReceiver-onUnregistered", "got here!");
	}
	
	@Override
	public void onError(Context context, String errorId) {
		Log.w("C2DMReceiver-onError", errorId);
	}
	
	@Override
	protected void onMessage(Context context, Intent intent) {
		Log.v("C2DMReceiver", intent.getStringExtra("payload"));
		Log.v("You Received : ","    " + intent.getStringExtra("payload"));
        Toast.makeText(context,"You Received : " + intent.getStringExtra("payload") , Toast.LENGTH_LONG).show();
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        
        C2DMReceiver.this.showNotification();
	}
	
	private void showNotification() {
		// TODO Auto-generated method stub
	//	Intent contentIntent = new Intent(this,dev.class);
		Intent appIntent = new Intent(this,GetNotification.class);
		//startActivity(appIntent);
		
		boolean showIconOnly = false;
		String tickerText = showIconOnly  ? null : "hello";
		Notification notification = new Notification(				
				R.drawable.icon2,
				"New Notification",
				System.currentTimeMillis());
		
				notification.setLatestEventInfo(C2DMReceiver.this, "App Name", "Description Of notification", 
				PendingIntent.getActivity(this.getBaseContext(), 0, appIntent, android.content.Intent.FLAG_ACTIVITY_NEW_TASK));
		
		
				mNM.notify(1,notification);				
				
//				notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent)
	}

}