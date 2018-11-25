package com.project.inc;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class List1 extends Activity {
    	//getResources().getStringArray(R.array.home_options);
	/** Called when the activity is first created. */
	ListView lv;
    @Override
    public void onCreate(Bundle savedInstanceState) {        
    	super.onCreate(savedInstanceState);    
    	
    	setContentView(R.layout.search_try);
//    	getDomain();
        final String[] HOME_LIST = getResources().getStringArray(R.array.home_options);
        
        lv = (ListView) findViewById(R.id.lvEntries);            
//        lv.setAdapter(new MyHomeAdapter(this, HOME_LIST));
        lv.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, HOME_LIST));

        lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
			}   	
        });                
        
    }   

	private void Show_Rating_Dialog() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Window window = getWindow();
		window.setFormat(PixelFormat.RGBA_8888);
	}
	
	
	
	
	public static String getDomain(){
		String text = new String("");
		
		 Cursor c = PublicInfo.db.rawQuery("SELECT distinct Domain FROM student" , null);
		 Log.v("I am in","getIDomain");
		 if (c != null ) {
			 int i = c.getCount();
			 int j = 0;
			 if  (c.moveToFirst())				 
				 while(i>0){
					 Log.v(text,"i=" + i + "  "+c.getString(c.getColumnIndex("Domain")));
			//		 text = new String(text +(c.getString(c.getColumnIndex("Domain"))));
					 PublicInfo.Domain[j] = new String("");
					 //PublicInfo.Domain[j] = c.getString(c.getColumnIndex("Domain"));
					 if(i!=1)
						 text = new String(text + "sds" + "$");
					 c.moveToNext();
					 i--;
					 Log.v(text,"i="+i);
				 }
				//String id = new String(idSb);
			 Log.v("you", "are out");
	//		 PublicInfo.Domain = text.split("$");
			 System.out.print(PublicInfo.Domain[0]);
		 }
        System.out.print(PublicInfo.Domain[0]);
		return text;
	}
}