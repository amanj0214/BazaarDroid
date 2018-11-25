package com.project.inc;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import com.google.android.c2dm.C2DMessaging;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class c2dm extends Activity {
    /** Called when the activity is first created. */
    static final int PROGRESS_DIALOG = 0;
	public static final String EXTRA_SENDER = "sender";
	public static final String EXTRA_APPLICATION_PENDING_INTENT = "app";
	public static final String REQUEST_UNREGISTRATION_INTENT = "com.google.android.c2dm.intent.UNREGISTER";
	public static final String REQUEST_REGISTRATION_INTENT = "com.google.android.c2dm.intent.REGISTER";
	public static final String LAST_REGISTRATION_CHANGE = "last_registration_change";
	public static final String BACKOFF = "backoff";
	public static final String GSF_PACKAGE = "com.google.android.gsf";
	private static final String SERVLET_URL = "http://10.0.2.2:8080/MyServer/Registration_Server";

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        
/*        try
        {
        Settings.System.putString(getContentResolver(), Settings.System.HTTP_PROXY, "192.168.15.253:3128");//enable proxy
        Log.v("Proxy", "Proxy Set");
        }catch (Exception ex){
        	Log.e("Proxy", "Proxy Not Set");
        } */        
        
        final TextView reg = (TextView) findViewById(R.id.register);
        
        if(PublicInfo.Reg_Done==true){
        	reg.setText("Unregister For Updates");
        }
        else{
        	reg.setText("Register For Updates");
        }
        reg.setOnClickListener(
                new TextView.OnClickListener() {
					@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
						if(PublicInfo.Reg_Done == false){
    					TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            	        String Imei = telephonyManager.getDeviceId();              	    
                        showDialog(PROGRESS_DIALOG);
                        Do_Reg(Imei);
                        PublicInfo.Reg_Done = true;    		
                    	reg.setText("Unregister For Updates");
						}
						else{
	                        PublicInfo.Reg_Done = false;    		
				        	reg.setText("Register For Updates");							
						}
    				}

                }
            );
        
        
        
        final TextView music = (TextView) findViewById(R.id.music);
        
        if(PublicInfo.Music==true){
        	music.setText("Turn music off");
        }
        else{
        	music.setText("Turn music on");
        }
        music.setOnClickListener(
                new TextView.OnClickListener() {    				

					@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
						if(PublicInfo.Music == false){    				
							PublicInfo.Music=true;   
				        	music.setText("Turn music off");
						}
						else{
							PublicInfo.Music=false;
				        	music.setText("Turn music on");
						}
    				}

                }
            );
        	
    }
    
    
    
    
    private void Do_Reg(String imei) {
		// TODO Auto-generated method stub
		
		Log.v("hi", "I am in register");
//		if(C2DMessaging.getRegistrationId(this) == ""){
        	C2DMessaging.register(this, "pict.c2dm@gmail.com");
        	Log.v("hi", C2DMessaging.getRegistrationId(this));
        	 Toast.makeText(this,"you are trying 22222222222 : " + C2DMessaging.getRegistrationId(this) 
                		, Toast.LENGTH_LONG).show();
  //      }
    //    else{
            Toast.makeText(this,"you are already registered...with id : " + C2DMessaging.getRegistrationId(this) 
            		, Toast.LENGTH_LONG).show();
            Log.v("hi", "I am in else of c2dm");
            Log.v("ID :" , C2DMessaging.getRegistrationId(this));
      //  }
		
		
		
		try {
			URL url = new URL(SERVLET_URL);
			URLConnection conn = url.openConnection();

			
			HttpURLConnection httpConn = (HttpURLConnection) conn;

			httpConn.setRequestMethod("POST");

			httpConn.setDoInput(true); 
			httpConn.setDoOutput(true); 
			httpConn.connect();

			DataOutputStream dataStream = new DataOutputStream(conn.getOutputStream());
			StringBuffer strb = new StringBuffer ("");
			strb.append (imei + "*hbchfg7643fcb-yg7w78cygggewg7cf36t487y833yh");
			dataStream.writeBytes(strb.toString());
			dataStream.flush(); 
            dataStream.close();

            int responseCode = httpConn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				
				Toast.makeText(getBaseContext(), 
	                    "Congratulations U have been registered!",
	                    Toast.LENGTH_SHORT).show();
			}
		} catch (Exception ex) {
			Toast.makeText(getBaseContext(), 
                    "Error While Registering!",
                    Toast.LENGTH_SHORT).show();
		}
	}  
    
	
	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Window window = getWindow();
		window.setFormat(PixelFormat.RGBA_8888);
	}
}