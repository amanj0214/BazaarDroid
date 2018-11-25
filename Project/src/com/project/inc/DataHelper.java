package com.project.inc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.Cursor;

public class DataHelper {
	public String Project_Id;
	public	String Project_Name;
	public	String Domain;
	public	String Abstract;
	public String Create_Query;
	private static final String DATABASE_NAME = "database2.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "student";
	private Context context;
	
	 private  SQLiteDatabase db;
	 //int a[] = new int [9];
	 
	 public DataHelper(Context databaseParsing) {
		 	      this.context = databaseParsing;
		 	     
				//Create_Query = new String("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR,color INTEGER)");
		 	//     Create_Query = new String("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + Project_Id + " VARCHAR, " + Project_Name + " CHAR, " + Domain + " CHAR, " + Abstract + " CHAR)");
		 	  //    OpenHelper openHelper = new OpenHelper(this.context,Create_Query);
		 	    //  this.db = openHelper.getWritableDatabase();	
		 	   
				
		
		 	   }
	 
	/* private void  insert_table(){
		 db.execSQL("INSERT INTO " + TABLE_NAME +  " Values (" + null +  ",'0',-1);");
			db.execSQL("INSERT INTO " + TABLE_NAME +  " Values (" + null +  ",'1',-256);");
			db.execSQL("INSERT INTO " + TABLE_NAME +  " Values (" + null +  ",'2',-16711681);");
			db.execSQL("INSERT INTO " + TABLE_NAME +  " Values (" + null +  ",'3',-8716033);");
			db.execSQL("INSERT INTO " + TABLE_NAME +  " Values (" + null +  ",'3',-8126209);");
			db.execSQL("INSERT INTO " + TABLE_NAME +  " Values (" + null +  ",'4',-32190);");
			db.execSQL("INSERT INTO " + TABLE_NAME +  " Values (" + null +  ",'4',-33479);");
		
			db.execSQL("INSERT INTO " + TABLE_NAME +  " Values (" + null +  ",'5',-16745216);");
			db.execSQL("INSERT INTO " + TABLE_NAME +  " Values (" + null +  ",'6',-8126332);");
			db.execSQL("INSERT INTO " + TABLE_NAME +  " Values (" + null +  ",'6',-8716165);");			
			db.execSQL("INSERT INTO " + TABLE_NAME +  " Values (" + null +  ",'7',-8093118);");
			db.execSQL("INSERT INTO " + TABLE_NAME +  " Values (" + null +  ",'7',-8684231);");
			db.execSQL("INSERT INTO " + TABLE_NAME +  " Values (" + null +  ",'8',-65536);");
			db.execSQL("INSERT INTO " + TABLE_NAME +  " Values (" + null +  ",'9',-16776961);");
			
	 }
	 public String getSection(int myColor){
		// insert_table();
		 String sec_name="asdaasff";
		 Cursor c = db.rawQuery("SELECT name FROM " + TABLE_NAME + " where color = " + myColor , null);
		 Log.w("Example", "Upgrading database, this will drop tables and recreate.");
		 if (c != null ) 
			 if  (c.moveToFirst()) 
				sec_name = c.getString(c.getColumnIndex("name"));
	
	return sec_name;
	 }
	 */
	public void createTableStud(){
		 Create_Query = new String("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( Project_Id  VARCHAR, Project_Name  CHAR, Domain CHAR, Abstract CHAR)");
	 	 OpenHelper openHelper = new OpenHelper(this.context,Create_Query);
	 	 PublicInfo.db=this.db = openHelper.getWritableDatabase();
	 	openHelper.onUpgrade(db, 1,1);
	 	//openHelper.onCreate(db);
		
	}
	
	public String getId(){
		String id = new String("");
		
		 Cursor c = db.rawQuery("SELECT Project_Id FROM " + TABLE_NAME , null);
		 Log.v("I am in","getId");
		 int i = c.getCount();
		 if (c != null ) {
			 Log.v("asdfgh","52");
			 if  (c.moveToFirst()) 
				 while(i!=0){
					 Log.v("asdfgh","53");
					 Log.v(id,"1");
					 System.out.println("i = " + i);
					 id = new String(id + "\n" + (c.getString(c.getColumnIndex("Project_Id"))));
					 c.moveToNext();
					 i--;
				 }
				//String id = new String(idSb);
	
		 }
		return id;
	}
	
	
	public String getDomain(){
		String text = new String("");
		
		 Cursor c = db.rawQuery("SELECT Domain FROM " + TABLE_NAME , null);
		 Log.v("I am in","getId");
		 int i = c.getCount();
		 if (c != null ) {
			 Log.v("asdfgh","52");
			 if  (c.moveToFirst()) 
				 while(i!=0){
					 Log.v("asdfgh","53");
					 Log.v(text,"1");
					 System.out.println("i = " + i);
					 text = new String(text + "\n" + (c.getString(c.getColumnIndex("Project_Id"))));
					 c.moveToNext();
					 i--;
				 }
				//String id = new String(idSb);
	
		 }
        PublicInfo.Domain = text.split("\\*");
		return text;
	}
	
	
	
	public void insertEntry(){
		 db.execSQL("INSERT INTO " + TABLE_NAME +  " Values ('" + Project_Id + "','" + Project_Name + "','" + Domain + "','" + Abstract + "' );");
		 Log.v("iNSERTUING", "" + Project_Id);
	}
	
	private static class OpenHelper extends SQLiteOpenHelper {
		
		public String Create_Query;
			 
				
				
			      OpenHelper(Context context,String cq) {
			         super(context, DATABASE_NAME, null, DATABASE_VERSION);
			         Create_Query = cq;			         
			      }
			 
			      @Override
			      public void onCreate(SQLiteDatabase db) {			    	 
			         db.execSQL(Create_Query);
			         Log.v("asdfgh","61");
			      }
			 
			      @Override
			      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			        Log.w("Example", "dfsfUpgrading database, this will drop tables and recreate.");
			        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			        onCreate(db);
			      }
			   }
			
}