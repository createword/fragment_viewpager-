package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.frag.R;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HomeAdapter extends BaseAdapter {

	Context context;
	LayoutInflater layoutInflater;
	ArrayList<String> array;
	public int foodpoition;

	public HomeAdapter(Context context, ArrayList<String> array) {
		this.context = context;
		this.array = array;
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return array.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return getItem(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.home_pager_item, null);
			viewHolder = new ViewHolder();
			viewHolder.textView = (TextView) convertView
					.findViewById(R.id.home_text_u);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.textView.setText(array.get(position).toString());

		return convertView;
	}

	public static class ViewHolder {
		public TextView textView;
	}

}
