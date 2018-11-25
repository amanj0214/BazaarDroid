package com.project.inc;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class SplashScreenActivity extends Activity {
	Boolean flag = false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		/** set time to splash out */
		final int welcomeScreenDisplay = 3000;
		/** create a thread to show splash up to splash time */
		Thread welcomeThread = new Thread() {

			boolean wait = true;

			@Override
			public void run() {
				try {
					super.run();
					/**
					 * use while to get the splash time. Use sleep() to increase
					 * the wait variable for every 100L.
					 */
					while (wait == true) {
					      try {try{
					    	  Cursor c = PublicInfo.db.rawQuery("SELECT Domain FROM student", null);
					      }
					      catch (Exception e) {
								flag = true;
							} 
					      if(flag){
					    	  /** Handling XML */
					        	SAXParserFactory spf = SAXParserFactory.newInstance();
					        	SAXParser sp = spf.newSAXParser();
					        	XMLReader xr = sp.getXMLReader();

					        	/** Send URL to parse XML Tags */
					        	URL sourceUrl = new URL(
					        	//"http://www.androidpeople.com/wp-content/uploads/2010/06/example.xml");
					        	"http://10.0.2.2:8080/MyServer/asdf.xml");

					        	/** Create handler to handle XML Tags ( extends DefaultHandler ) */
					        	MyXMLHandler myXMLHandler = new MyXMLHandler();
					        	myXMLHandler.getContext(SplashScreenActivity.this);
					        	xr.setContentHandler(myXMLHandler);
					        	xr.parse(new InputSource(sourceUrl.openStream()));
					        	
					  //      	TextView tv = (TextView) findViewById(R.id.TextView01);
					        	// tv.setText("This is Done!!!!!!!!!!!");
//					        	 tv.setText(myXMLHandler.getId());
					        	Log.v("hey", ""+myXMLHandler.getId());}
					        	} catch (Exception e) {
					        	System.out.println("XML Parsing Excpetion = " + e);
					        	}
					        	
					        	PublicInfo.getDomain();
					        	PublicInfo.getId();
					        	PublicInfo.gettitle();
					        	
					        	wait=false;
					        	
					}
				} catch (Exception e) {
					System.out.println("EXc=" + e);
				} finally {
					/**
					 * Called after splash times up. Do some action after splash
					 * times up. Here we moved to another main activity class
					 */
					
					
					startActivity(new Intent(SplashScreenActivity.this,
							INC.class));
					finish();
				}
			}
		};
		welcomeThread.start();

	}
	
	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Window window = getWindow();
		window.setFormat(PixelFormat.RGBA_8888);
	}

}

