package com.project.inc;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RatingBar.OnRatingBarChangeListener;

public class AboutProj extends Activity {
	private static final String SERVLET_URL = "http://10.0.2.2:8080/MyServer/Get_Rating";

	Cursor c;
	
	public void get_Rating(){
		String text;
		RatingBar rate = (RatingBar)findViewById(R.id.RatingBar01);
		text = "get_rating";
		String result = getResultFromServlet(text);		
		rate.setRating(Float.parseFloat(result));
		rate.setEnabled(false);
		Toast.makeText(AboutProj.this,result, Toast.LENGTH_LONG).show();
	}
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Second Tab Content */
		setContentView(R.layout.about_proj);
		TextView txt = (TextView)findViewById(R.id.about);
        c = PublicInfo.db.rawQuery("SELECT Project_Name,Project_Id,Domain,Abstract FROM student where Project_Id='" + PublicInfo.Current_Id +"'", null);
        
        Log.v("u have",PublicInfo.Current_Id);

        if(c!=null){
        	c.moveToFirst();
        	Log.v("u have",c.getString(c.getColumnIndex("Project_Name")));
        	txt.setText("Title : \n"+c.getString(c.getColumnIndex("Project_Name"))+
        			"\n\n Abstract :\n"+c.getString(c.getColumnIndex("Abstract")));
        }
        get_Rating();

        ImageButton rate = (ImageButton)findViewById(R.id.rate);
        rate.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final AlertDialog.Builder alert = new AlertDialog.Builder(AboutProj.this);
        		final EditText input = new EditText(AboutProj.this);
        		final RatingBar rate = new RatingBar(AboutProj.this);        		
        		alert.setView(rate);
        		alert.setIcon(R.drawable.star_empty);
        		alert.setTitle("Enter Group ID ");
        		final float ratings;
        		
        		rate.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
        		    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        		        Toast.makeText(AboutProj.this, "New Rating: " + rating, Toast.LENGTH_SHORT).show();        		       
        		    }
        		});
        	
        		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog, int whichButton) {
        				String value = input.getText().toString().trim(); 
        				//rate.getRating();
        				Toast.makeText(AboutProj.this, "Final Rating: " + rate.getRating(), Toast.LENGTH_SHORT).show();
//        				Post_Rating(rate.getRating(),PublicInfo.Current_Id);
        				TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            	        String Imei = telephonyManager.getDeviceId();
        				String text;
        				
        				text = Imei+"*" + PublicInfo.Current_Id +"*"+rate.getRating();
        				String result = getResultFromServlet(text);
        				if (result != null) {
        					display(result);
            				Toast.makeText(AboutProj.this,result, Toast.LENGTH_SHORT).show();
        				} else {
        					display("Not found");
        				}
        				
        			}
        		});

        		alert.setNegativeButton("Cancel",
        				new DialogInterface.OnClickListener() {
        					public void onClick(DialogInterface dialog, int whichButton) {
        						dialog.cancel();
        					}
        				});
        		alert.show();
			}
        	
        });
        
	}

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Window window = getWindow();
		window.setFormat(PixelFormat.RGBA_8888);
	}
	
	
	protected void display(String text) {
	}

	private String getResultFromServlet(String text) {
		String result = "";
		InputStream in = callService(text);
		Log.v("hey","u came here.."+ in.toString());
		
		if(in!=null) {
			result= convertStreamToString(in);
		} else {
			result = "Error: Service not returning result";
		}
		return result;
	}
	
	
	/**
	 * @param envelope
	 */
	private InputStream callService(String text) {
		InputStream in = null;
		
		try {
			URL url = new URL(SERVLET_URL);
			URLConnection conn = url.openConnection();

			
			HttpURLConnection httpConn = (HttpURLConnection) conn;

			httpConn.setRequestMethod("POST");

			httpConn.setDoInput(true); 
			httpConn.setDoOutput(true); 
			httpConn.connect();

			DataOutputStream dataStream = new DataOutputStream(conn.getOutputStream());
			
			dataStream.writeBytes(text);
			dataStream.flush(); 
            dataStream.close();

            int responseCode = httpConn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
			}
		} catch (Exception ex) {
			display("Error: Not not connect");
		}
		return in;
	}
	
	
	private static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
}