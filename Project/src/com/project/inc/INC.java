package com.project.inc;

import com.google.android.c2dm.C2DMessaging;

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

import com.google.android.c2dm.C2DMessaging;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class INC extends Activity {
    	//getResources().getStringArray(R.array.home_options);
    static final int PROGRESS_DIALOG = 0;
    ProgressDialog progressDialog;
    ProgressThread progressThread;
    String Imei=null;
    boolean REG_DONE=false;
    
	private static final String SERVLET_URL = "http://10.0.2.2:8080/MyServer/Registration_Server";
	private static final String NEW_DATA_URL = "http://10.0.2.2:8080/MyServer/mytry.xml";

    
	 protected Dialog onCreateDialog(int id) {
	        switch(id) {
	        case PROGRESS_DIALOG:
	            progressDialog = new ProgressDialog(INC.this);
	            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	            progressDialog.setMessage("Loading...");
	            return progressDialog;
	        default:
	            return null;
	        }
	    }

	 @Override
	    protected void onPrepareDialog(int id, Dialog dialog) {
	        switch(id) {
	        case PROGRESS_DIALOG:
	            progressDialog.setProgress(0);
	            progressThread = new ProgressThread(handler);
	            progressThread.start();
	    }

	 }  
	 
	 
	 
	 // Define the Handler that receives messages from the thread and update the progress
	    final Handler handler = new Handler() {
	        public void handleMessage(Message msg) {
	            int total = msg.arg1;
	            progressDialog.setProgress(total);
	            if (total >= 100 || REG_DONE == true){
	                dismissDialog(PROGRESS_DIALOG);
	                progressThread.setState(ProgressThread.STATE_DONE);
	            }
	        }
	    };

	    /** Nested class that performs progress calculations (counting) */
	    private class ProgressThread extends Thread {
	        Handler mHandler;
	        final static int STATE_DONE = 0;
	        final static int STATE_RUNNING = 1;
	        int mState;
	        int total;
	        

	       
	        ProgressThread(Handler h) {
	            mHandler = h;
	        }
	       
	        public void run() {
	            mState = STATE_RUNNING;   
	            total = 0;
	            while (mState == STATE_RUNNING && REG_DONE== false) {
	                try {
	                    Thread.sleep(100);
	                } catch (InterruptedException e) {
	                    Log.e("ERROR", "Thread Interrupted");
	                }
	                Message msg = mHandler.obtainMessage();
	                msg.arg1 = total;
	                mHandler.sendMessage(msg);
	                total++;
	            }
	        }
	        
	        /* sets the current state for the thread,
	         * used to stop the thread */
	        public void setState(int state) {
	            mState = state;
	        }
	    }

	    
	
	
	
	/** Called when the activity is first created. */
	ListView lv;
    @Override
    public void onCreate(Bundle savedInstanceState) {        
    	super.onCreate(savedInstanceState);    
    	
    	setContentView(R.layout.home_main);
    	
        final String[] HOME_LIST = getResources().getStringArray(R.array.home_options);
        
        lv = (ListView) findViewById(R.id.lvEntries);            
        lv.setAdapter(new MyHomeAdapter(this, HOME_LIST));

        lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				int index = arg2;
				
                Log.v("you", "are on click");
                Toast.makeText(getBaseContext(), 
                    "You have selected item : " + HOME_LIST[index],
                    Toast.LENGTH_SHORT).show();
                switch(index){
                	case 3:
                		Show_Rating_Dialog();
                		final AlertDialog.Builder alert = new AlertDialog.Builder(INC.this);
                		final EditText input = new EditText(INC.this);
                		alert.setView(input);
                		alert.setIcon(R.drawable.star_empty);
                		alert.setTitle("Enter Group ID ");
                	
                		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                			public void onClick(DialogInterface dialog, int whichButton) {
                				String value = input.getText().toString().trim();
                				                				
                				
                				Cursor c = PublicInfo.db.rawQuery("SELECT distinct Project_Id FROM student where Project_Id='"+value.toUpperCase()+"'" , null);
                				 Log.v("I am in","getIDomain");
                				 if (c != null ) {
                					c.moveToFirst();
                					if(c.getCount() > 0){
                					Intent my = new Intent(INC.this,ProjDescActivity.class);
            						my.putExtra("ID", c.getString(c.getColumnIndex("Project_Id")));
            						startActivity(my);
                					}
                					else{
                						Toast.makeText(getApplicationContext(), "Invalid Project ID!",
                        						Toast.LENGTH_SHORT).show();
                					}
            					}
                				 c.close();                				
                			}
                		});

                		alert.setNegativeButton("Cancel",
                				new DialogInterface.OnClickListener() {
                					public void onClick(DialogInterface dialog, int whichButton) {
                						dialog.cancel();
                					}
                				});
                		alert.show();
                		break;
                	case 1:
                	//	Intent my = new Intent(INC.this,SearchActivity.class);                 					
                		//startActivity(my);
                		startActivity(new Intent(INC.this,
    							SearchActivity2.class));
                		break;
                	case 4:
                		startActivity(new Intent(INC.this,c2dm.class));
                		break;
                	case 0:
                		break;
                	case 2:
                		String FILE_NAME = "my_try.xml";
                		String txt = "Hello, World!";
                		
                
                		
					try {
						FileOutputStream fos = openFileOutput(FILE_NAME,Context.MODE_PRIVATE);		
						fos.write(NEW_DATA_URL.getBytes());
						fos.close();
						Log.v("file", "createx");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						FileInputStream fis = openFileInput(FILE_NAME);
						InputStreamReader in = new InputStreamReader(fis);
						BufferedReader br = new BufferedReader(in);
						String data = br.readLine();					
											
						Log.v("hey", ""+data);
						fis.close();
					} catch (FileNotFoundException e) {
						Log.v("hey", "error1");

						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						Log.v("hey", "error2");
						e.printStackTrace();
					}
					Log.v("hey", "done");
                		break;
                	case 5:   
                //		Intent my1 = new Intent(INC.this,SearchActivity2.class);                 					
                	//	startActivity(my1);
              //  		startActivity(new Intent(INC.this,
    			//				SearchActivity2.class));
                		break;
                	case 7:
                		final AlertDialog.Builder alert1 = new AlertDialog.Builder(INC.this);
                		final EditText input1 = new EditText(INC.this);                		
                		alert1.setIcon(R.drawable.star_empty);
                		alert1.setTitle("Do you Want to Flush the Data ? ");
                	
                		alert1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                			public void onClick(DialogInterface dialog, int whichButton) {
                				
                				PublicInfo.db.execSQL("DROP TABLE IF EXISTS student");
                        		finish();

                				}
                		});

                		alert1.setNegativeButton("Cancel",
                				new DialogInterface.OnClickListener() {
                					public void onClick(DialogInterface dialog, int whichButton) {
                						dialog.cancel();
                                		finish();

                					}
                		});
                		alert1.show();
                		break;
                	default:
                		break;
                }
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
}