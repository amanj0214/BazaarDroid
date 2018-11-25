package com.project.inc;

import org.xml.sax.XMLReader;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PublicInfo {
	
	static String[] Domain;
	static String[] Id;
	static String[] Title;
	public static Context context;
	static  SQLiteDatabase db;
	static String Current_Id;
	static int count;
	static boolean Reg_Done;
	static boolean Music;
	

	
	
	public static String getDomain(){
		String text = new String("");
		
		 Cursor c = PublicInfo.db.rawQuery("SELECT distinct Domain FROM student" , null);
		 Log.v("I am in","getIDomain");
		 if (c != null ) {
			 int i = c.getCount();
			 int j = 0;
			 if  (c.moveToFirst())				 
				 while(i>0){
	//				 Log.v(text,"i=" + i + "  "+c.getString(c.getColumnIndex("Domain")));
			//		 text = new String(text +(c.getString(c.getColumnIndex("Domain"))));
					 text=text + c.getString(c.getColumnIndex("Domain"));
					 //PublicInfo.Domain[j] = c.getString(c.getColumnIndex("Domain"));
					 if(i!=1)
						 text = new String(text  + "asdf");
					 c.moveToNext();
					 i--;
					 Log.v(text,"i="+i);
				 }
				//String id = new String(idSb);
			 Log.v("you", "are out");
			 PublicInfo.Domain = text.split("asdf");
			 System.out.print(PublicInfo.Domain[0]);
		 }
		 c.close();
        System.out.print(PublicInfo.Domain[0]);
		return text;
	}
	
	
	
	
	
	public static String getId(){
		String text = new String("");
		
		 Cursor c = PublicInfo.db.rawQuery("SELECT distinct Project_Id FROM student" , null);
		 Log.v("I am in","getIDomain");
		 if (c != null ) {
			 int i = c.getCount();
			 int j = 0;
			 if  (c.moveToFirst())				 
				 while(i>0){
	//				 Log.v(text,"i=" + i + "  "+c.getString(c.getColumnIndex("Domain")));
			//		 text = new String(text +(c.getString(c.getColumnIndex("Domain"))));
					 text=text + c.getString(c.getColumnIndex("Project_Id"));
					 //PublicInfo.Domain[j] = c.getString(c.getColumnIndex("Domain"));
					 if(i!=1)
						 text = new String(text  + "asdf");
					 c.moveToNext();
					 i--;
					 Log.v(text,"i="+i);
				 }
				//String id = new String(idSb);
			 Log.v("you", "are out");
			 PublicInfo.Id = text.split("asdf");
			 System.out.print(PublicInfo.Domain[0]);
		 }
		 c.close();
        System.out.print(PublicInfo.Domain[0]);
		return text;
	}
	
	
	
	public static String gettitle(){
		String text = new String("");
		
		 Cursor c = PublicInfo.db.rawQuery("SELECT distinct Project_Name FROM student" , null);
		 Log.v("I am in","getIDomain");
		 if (c != null ) {
			 int i = c.getCount();
			 int j = 0;
			 if  (c.moveToFirst())				 
				 while(i>0){
	//				 Log.v(text,"i=" + i + "  "+c.getString(c.getColumnIndex("Domain")));
			//		 text = new String(text +(c.getString(c.getColumnIndex("Domain"))));
					 text=text + c.getString(c.getColumnIndex("Project_Name"));
					 //PublicInfo.Domain[j] = c.getString(c.getColumnIndex("Domain"));
					 if(i!=1)
						 text = new String(text  + "asdf");
					 c.moveToNext();
					 i--;
					 Log.v(text,"i="+i);
				 }
				//String id = new String(idSb);
			 Log.v("you", "are out");
			 PublicInfo.Title = text.split("asdf");
			 System.out.print(PublicInfo.Title[0]);
		 }
		 c.close();
        System.out.print(PublicInfo.Title[0]);
		return text;
	}
}
