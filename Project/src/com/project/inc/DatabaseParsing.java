package com.project.inc;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

public class DatabaseParsing extends Activity {
	    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        try {

        	/** Handling XML */
        	SAXParserFactory spf = SAXParserFactory.newInstance();
        	SAXParser sp = spf.newSAXParser();
        	XMLReader xr = sp.getXMLReader();

        	/** Send URL to parse XML Tags */
        	URL sourceUrl = new URL(
        	//"http://www.androidpeople.com/wp-content/uploads/2010/06/example.xml");
        	"http://10.0.2.2:8080/MyServer/asdf.xml");

        	/** Create handler to handle XML Tags ( extends DefaultHandler ) */
      //  	MyXMLHandler myXMLHandler = new MyXMLHandler();
        	
        	MyXMLHandler myXMLHandler=new MyXMLHandler();
			myXMLHandler.getContext(this);
        	xr.setContentHandler(myXMLHandler);
        	xr.parse(new InputSource(sourceUrl.openStream()));
        	
  //      	TextView tv = (TextView) findViewById(R.id.TextView01);
        	// tv.setText("This is Done!!!!!!!!!!!");
//        	 tv.setText(myXMLHandler.getId());
        	Log.v("hey", ""+myXMLHandler.getId());
        	} catch (Exception e) {
        	System.out.println("XML Parsing Excpetion = " + e);
        	}
        	
        	 
        	startActivity(new Intent(DatabaseParsing.this,
					INC.class));
        	
        	finish();

    }
    
    @Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Window window = getWindow();
		window.setFormat(PixelFormat.RGBA_8888);
	}

   
}