package com.project.inc;

import android.app.Activity;
import android.app.ListActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SearchByCategory extends ListActivity {


    //@Override
    public void onCresdate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use an existing ListAdapter that will map an array
        // of strings to TextViews
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mStrings));
        getListView().setTextFilterEnabled(true);
    }

 //   private String[] mStrings = {"1","2"};
	
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
	
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new MyListAdapter(this));
    }

    private class MyListAdapter extends BaseAdapter {
        public MyListAdapter(Context context) {
            mContext = context;
        }

        public int getCount() {
            return mStrings.length;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int position) {
            return !mStrings[position].startsWith("-");
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv;
            if (convertView == null) {
                tv = (TextView) LayoutInflater.from(mContext).inflate(
                        android.R.layout.simple_expandable_list_item_1, parent, false);
            } else {
                tv = (TextView) convertView;
            }
            tv.setText(mStrings[position]);
            return tv;
        }

        private Context mContext;
    }
    
    private String[] mStrings = {
            "----------",
            "----------",
            "Abbaye de Belloc",
            "Abbaye du Mont des Cats",
            "Abertam",
            "----------",
            "Abondance",
            "----------",
            "Ackawi",
            "Acorn",
            "Adelost",
            "Affidelice au Chablis",
            "Afuega'l Pitu",
            "Airag",
            "----------",
            "Airedale",
            "Aisy Cendre",
            "----------",
            "Allgauer Emmentaler",
            "Alverca",
            "Ambert",
            "American Cheese",
            "Ami du Chambertin",
            "----------",
            "----------",
            "Anejo Enchilado",
            "Anneau du Vic-Bilh",
            "Anthoriro",
            "----------",
            "----------"
    };

}
