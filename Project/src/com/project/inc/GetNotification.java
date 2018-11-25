package com.project.inc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class GetNotification extends Activity{
	private static final String SERVLET_URL = "http://10.0.2.2:8080/MyServer/Notification1";

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main2);
	        TextView txt = (TextView)findViewById(R.id.txt);
	        Log.v("hey", "sdfhsjdk");

	        String text = "request_judge";
	        String result =getResultFromServlet(text);
	        Log.v("hey", "" + result);
			txt.setText(result);
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

