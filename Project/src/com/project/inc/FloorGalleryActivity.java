package com.project.inc;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class FloorGalleryActivity extends Activity
{    
    //---the images to display---
    Integer[] imageIDs = {
            R.drawable.ic_category,
            R.drawable.ic_launcher_phone_dialer,
            R.drawable.ic_launcher_musicplayer_2,
            R.drawable.dark_black_gradient,
            R.drawable.ic_more,
            R.drawable.schedule,
            R.drawable.ic_menu_mapmode                    
    };
 
    @Override    
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.floor_gallery);
        RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.relativelayout);
        final TextView txt = (TextView) findViewById(R.id.txt);
        Gallery gallery = (Gallery) findViewById(R.id.gallery1);
 
        gallery.setAdapter(new ImageAdapter(this));        
        
        gallery.setOnItemClickListener(new OnItemClickListener() 
        {
            public void onItemClick(AdapterView parent, 
            View v, int position, long id) 
            {                
                Toast.makeText(getBaseContext(), 
                        "pic" + (position + 1) + " selected", 
                        Toast.LENGTH_SHORT).show();
                txt.setText("FLOOR : "+ (position+1));	
            }
        });
    }
 
    public class ImageAdapter extends BaseAdapter 
    {
        private Context context;
        private int itemBackground;
 
        public ImageAdapter(Context c) 
        {
            context = c;
            //---setting the style---
            TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);
            itemBackground = a.getResourceId(
                R.styleable.Gallery1_android_galleryItemBackground, 0);
            a.recycle();                    
        }
 
        //---returns the number of images---
        public int getCount() {
            return imageIDs.length;
        }
 
        //---returns the ID of an item--- 
        public Object getItem(int position) {
            return position;
        }            
 
        public long getItemId(int position) {
            return position;
        }
 
        //---returns an ImageView view---
        public View getView(int position, View convertView, ViewGroup parent) {
        	
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(imageIDs[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new Gallery.LayoutParams(150, 120));
            imageView.setBackgroundResource(itemBackground);
            return imageView;
        }
    }    
}