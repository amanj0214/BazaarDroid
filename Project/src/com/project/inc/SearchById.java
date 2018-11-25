package com.project.inc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchById extends  Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Second Tab Content */
		TextView textView = new TextView(this);
		textView.setText("Second Tab");
		setContentView(textView);
	}

}