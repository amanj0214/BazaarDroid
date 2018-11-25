package com.project.inc;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyHomeAdapter extends ArrayAdapter<String> {
	private final String[] values;
	private LayoutInflater inflator;
	int position;
	static class ViewHolder {
		public TextView text;
		public ImageView image;
		public ImageView icon;
	}

	public MyHomeAdapter(Context context, String[] values) {
		super(context, R.id.TextView01, values);
		this.values = values;
		inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getViewTypeCount(){
		return 1;
	}

	@Override
	public int getItemViewType(int position) {
		return (position);
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		ViewHolder holder;
//		position = position%5;
		Log.v("Position", ""+position);
		position = pos;
		
		if (convertView == null) {
			if (getItemViewType(position) == 0) {
	//			Log.v(":(", "this is not expected "+getItemViewType(position));
				convertView = inflator.inflate(R.layout.home_list, null);
			} else {
				convertView = inflator.inflate(R.layout.home_list, null);
			}
			holder = new ViewHolder();
			
			ImageView img = (ImageView) convertView.findViewById(R.id.ImageView01);
			ImageView img1 = (ImageView) convertView.findViewById(R.id.ImageView02);
			
			
			switch(getItemViewType(position)){
				case 0:
					img.setBackgroundResource(R.drawable.ic_menu_mapmode);
					img1.setBackgroundResource(R.drawable.ic_more);
					break;
				case 1:
					img.setBackgroundResource(R.drawable.ic_menu_search);
					img1.setBackgroundResource(R.drawable.ic_more);					
					break;
				case 2:
					img.setBackgroundResource(R.drawable.schedule);
					img1.setBackgroundResource(R.drawable.ic_more);					
					break;
				case 3:
					img.setBackgroundResource(R.drawable.ic_menu_favorite);
					img1.setBackgroundResource(R.drawable.ic_more);					
					break;
				case 4:
					img.setBackgroundResource(R.drawable.ic_launcher_settings);
					img1.setBackgroundResource(R.drawable.ic_more);				
					break;
				case 5:
					img.setBackgroundResource(R.drawable.ic_launcher_phone_dialer);
					img1.setBackgroundResource(R.drawable.ic_more);			
					break;
				case 6:
					img.setBackgroundResource(R.drawable.ic_launcher_voicedial);
					break;
				case 7:		
					img.setBackgroundResource(R.drawable.ic_menu_close_clear_cancel);
					break;
			}
			
			holder.text = (TextView) convertView.findViewById(R.id.TextView01);
			holder.icon = (ImageView) convertView.findViewById(R.id.ImageView01);
						
			convertView.setTag(holder);		
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.text.setText(values[position]);
		
		return convertView;
	}
}