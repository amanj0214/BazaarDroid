package com.project.inc;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailActivity extends Activity {
	Cursor c;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Second Tab Content */
		setContentView(R.layout.about_proj);
	}

}
