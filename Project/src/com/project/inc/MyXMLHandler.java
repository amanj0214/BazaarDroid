package com.project.inc;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.util.Log;

public class MyXMLHandler extends DefaultHandler{
	DataHelper dh;
	private int flag=0;
	boolean currentElement;
	String currentValue;
	int i=0;

	/** Called when tag starts ( ex:- <name>AndroidPeople</name>
	* -- <name> )*/
	public void getContext(Context databaseParsing){
		dh = new DataHelper(databaseParsing);
		Log.v("asdfgh","21");
	}
	
	public String getId(){
		Log.v("asdfgh","22");
		String id = new String (dh.getId());
		Log.v("asdfgh","23");
		return id;
	}
	
	public String getDomain(){
		Log.v("asdfgh","22");
		String id = new String (dh.getDomain());
		Log.v("asdfgh","23");
		return id;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
	Attributes attributes) throws SAXException {
		//Log.v("asdfgh","1");
		currentElement = true;
		//Log.v(localName,"1");
		if(localName.equals("Student")){
			dh.createTableStud();
			Log.v("asdfgh","11");
		}
		else if(localName.equals("Project_ID")){
			flag = 1;
			Log.v("asdfgh","12");
		}
		else if(localName.equals("Project_Name")){
			flag = 2;
			Log.v("asdfgh","13");
		}
		else if(localName.equals("Domain")){
			flag = 3;
			Log.v("asdfgh","14");
		}
		else if(localName.equals("Abstract")){
			flag = 4;
			Log.v("asdfgh","15");
		}
		else{
			Log.v("asdfgh","16");
		}
		//Log.v("asdfgh","2");
		
	}

	/** Called when tag closing ( ex:- <name>AndroidPeople</name>
	* -- </name> )*/
	@Override
	public void endElement(String uri, String localName, String qName)
	throws SAXException {
		
		
		
		Log.v("asdfgh","31");
		currentElement = false;
		switch(flag){
		case 1: dh.Project_Id = currentValue;
				break;
		case 2: dh.Project_Name = currentValue;
				break;
		case 3: dh.Domain = currentValue;
				break;
		case 4: dh.Abstract = currentValue;
				break;
			default:break;
		
		}
		flag = 0;
		if(localName.equals("Project")){
			i = i + 1;
			dh.insertEntry();
			System.out.println(i);
		}
		//Log.v("asdfgh","4");

	}

	/** Called to get tag characters ( ex:- <name>AndroidPeople</name>
	* -- to get AndroidPeople Character ) */
	@Override
	public void characters(char[] ch, int start, int length)
	throws SAXException {
		//Log.v("asdfgh","41");
		if (currentElement) {
			currentValue = new String(ch, start, length);
			currentElement = false;
			}
	}
}

