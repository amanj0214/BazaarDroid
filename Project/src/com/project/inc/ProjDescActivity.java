package com.project.inc;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class ProjDescActivity extends TabActivity{

	boolean customTitleSupported;
	String diff;
	Cursor c;
	@Override
    public void onCreate(Bundle savedInstanceState) {        
    	super.onCreate(savedInstanceState);   
    	
    	//check if custom title is supported BEFORE setting the content view!
        customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
    	
    	setContentView(R.layout.project_desc);
		diff = getIntent().getStringExtra("ID");   	
    	customTitleBar();
    	
    	
    	
        final TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        /** TabSpec used to create a new tab.
         * By using TabSpec only we can able to setContent to the tab.
         * By using TabSpec setIndicator() we can set name to tab. */

        /** tid1 is firstTabSpec Id. Its used to access outside. */
        TabSpec firstTabSpec = tabHost.newTabSpec("tid1");
        TabSpec secondTabSpec = tabHost.newTabSpec("tid1");

      
        /** TabSpec setIndicator() is used to set name for the tab. */
        /** TabSpec setContent() is used to set content for a particular tab. */
        firstTabSpec.setIndicator("About").setContent(new Intent(this,AboutProj.class));
        secondTabSpec.setIndicator("Comments").setContent(new Intent(this,CommentsOnProj.class));
        
        /** Add tabSpec to the TabHost to display. */
        tabHost.addTab(firstTabSpec);
        tabHost.addTab(secondTabSpec);
        
        tabHost.getTabWidget().setCurrentTab(0);
        tabHost.getTabWidget().getChildAt(0).getLayoutParams().height =
        	35;
        for(int i=0;i<2;i++){
        	tabHost.getTabWidget().getChildAt(i).setBackgroundColor(R.color.bk);
        	tabHost.getTabWidget().getChildAt(i).getLayoutParams().height =
            	35;
        }
        tabHost.setOnTabChangedListener(new OnTabChangeListener(){
        	@Override
        	public void onTabChanged(String tabId) {
                }});
   // 	TextView txt = (TextView) findViewById(R.id.desc);
    //	txt.setText("Hellojdsgfckhckzbxhjcsabnckbxjcbkddsanlkxz");
	}	
	
	public void customTitleBar() {
	       
	        // set up custom title
	        if (customTitleSupported) {
	                getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
	                                R.layout.proj_desc_title_bar);	                
	                TextView titleTvRight = (TextView) findViewById(R.id.title);
	                TextView collName = (TextView) findViewById(R.id.coll_name);
	                ImageView icon = (ImageView) findViewById(R.id.icon);
	                icon.setImageDrawable(getResources().getDrawable(R.drawable.icon2));
	                c = PublicInfo.db.rawQuery("SELECT Project_Name,Project_Id,Domain FROM student where Project_Id='" + diff +"'", null);
	                
	                //Log.v("u have",c.getString(c.getColumnIndex("Project_Name")));

	                if(c!=null){
	                	c.moveToFirst();
	                	collName.setText("PICT,Pune");
	                	titleTvRight.setText(c.getString(c.getColumnIndex("Project_Id")));
	                	Log.v("u have",c.getString(c.getColumnIndex("Project_Name")));
	                	PublicInfo.Current_Id = (String) titleTvRight.getText();
	                }
	   //             titleTvRight.setText("Title");
	                /*	                ProgressBar titleProgressBar;
	                titleProgressBar = (ProgressBar) findViewById(R.id.leadProgressBar);
	 
	                //hide the progress bar if it is not needed
	                titleProgressBar.setVisibility(ProgressBar.GONE);
	                */
	        }
	}
}
