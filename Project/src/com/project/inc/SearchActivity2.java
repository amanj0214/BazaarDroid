package com.project.inc;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SearchActivity2 extends Activity {	

	
	/** Called when the activity is first created. */
	ListView lv;
	static String[] Title_List;
    @Override
    public void onCreate(Bundle savedInstanceState) {        
    	super.onCreate(savedInstanceState);    
    	
    	setContentView(R.layout.search_main2);
 //   	getDomain();
   // 	getId();
    //	gettitle();
        final String[] DOMAIN_LIST = PublicInfo.Domain;
 //      final String[] ID_LIST = {"1","2","3","4","11","3e"};
        final String[] ID_LIST = PublicInfo.Id;
        final String[] TITLE_LIST = PublicInfo.Title;

        final Button btndomain = ((Button) findViewById(R.id.btndomain)); 
        final Button btnid = ((Button) findViewById(R.id.btnid)); 
        final Button btntitle = ((Button) findViewById(R.id.btntitle));
        
        final TextView domainname = ((TextView) findViewById(R.id.domainname));
        
        lv = (ListView) findViewById(R.id.list);            
        lv.setAdapter(new MySearchHandler(this, DOMAIN_LIST));
        boolean indomain=false;
        
        lv.setTextFilterEnabled(true);
        
        lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				int index = arg2;
				arg1.toString();
				boolean indomain=false;
				String text = new String("");
			
                Log.v("you", "are on click");
                if(!btndomain.isEnabled()){
                	Toast.makeText(getBaseContext(), 
                			"You have selected item : " + DOMAIN_LIST[index],
                			Toast.LENGTH_SHORT).show();
                	
                	domainname.setText(DOMAIN_LIST[index]);
					 Cursor c = PublicInfo.db.rawQuery("SELECT distinct Project_Name FROM student where Domain='"+DOMAIN_LIST[index]+"'" , null);
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
						 System.out.print(PublicInfo.Title[0]);
						 Title_List = text.split("asdf");
					 }
					lv.setAdapter(new MySearchHandler(SearchActivity2.this,Title_List));
					indomain = true;
					
					btndomain.setEnabled(true);
                }
                else if(!btnid.isEnabled()){
                	Toast.makeText(getBaseContext(), 
                            "You have selected item : " + ID_LIST[index],                            
                            Toast.LENGTH_SHORT).show();
                	Intent my = new Intent(SearchActivity2.this,ProjDescActivity.class);
					my.putExtra("ID", ID_LIST[index]);
					startActivity(my);
                }
                else if(!btntitle.isEnabled()){
                	Toast.makeText(getBaseContext(), 
                            "You have selected item : " + TITLE_LIST[index],
                            Toast.LENGTH_SHORT).show();
                	
                	Cursor c = PublicInfo.db.rawQuery("SELECT Project_Name,Project_Id,Domain,Abstract FROM student where Project_Name='" + TITLE_LIST[index] +"'", null);                     
                	
                    if(c!=null){
                    	c.moveToFirst();
                    	Log.v("u have",c.getString(c.getColumnIndex("Project_Name")));
                    	Intent my = new Intent(SearchActivity2.this,ProjDescActivity.class);
    					my.putExtra("ID", c.getString(c.getColumnIndex("Project_Id")));
    					startActivity(my);
                    }
                	
                	
                }
                else if(btntitle.isEnabled() && btndomain.isEnabled() && btnid.isEnabled()){
                	Toast.makeText(getBaseContext(), 
                            "You have selected item : " + Title_List[index],
                            Toast.LENGTH_SHORT).show();
                	
                	Cursor c = PublicInfo.db.rawQuery("SELECT Project_Name,Project_Id,Domain," +
                			"Abstract FROM student where Project_Name='" + Title_List[index] +"'", null);                     

                    if(c!=null){
                    	c.moveToFirst();
                    	Log.v("u have",c.getString(c.getColumnIndex("Project_Name")));
                    	Intent my = new Intent(SearchActivity2.this,ProjDescActivity.class);
    					my.putExtra("ID", c.getString(c.getColumnIndex("Project_Id")));
    					startActivity(my);
                    } 
                }
			}
				        	
        });
        
        btndomain.setOnClickListener(
                new Button.OnClickListener() {
    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					btndomain.setEnabled(false);
    					btnid.setEnabled(true);
    					btntitle.setEnabled(true);
    					lv.setAdapter(new MySearchHandler(SearchActivity2.this,DOMAIN_LIST));
    					domainname.setText("");
    				}
                }
            );
        
        btnid.setOnClickListener(
                new Button.OnClickListener() {
    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					btndomain.setEnabled(true);
    					btnid.setEnabled(false);
    					btntitle.setEnabled(true);    				
    					lv.setAdapter(new MySearchHandler(SearchActivity2.this,ID_LIST));
                    	domainname.setText("");
    				}
                }
            );

        btntitle.setOnClickListener(
                new Button.OnClickListener() {
    				@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub
    					btndomain.setEnabled(true);
    					btnid.setEnabled(true);
    					btntitle.setEnabled(false);
    			        lv.setAdapter(new MySearchHandler(SearchActivity2.this, TITLE_LIST));
    			        domainname.setText("");
    				}
                }
            );
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
	
}