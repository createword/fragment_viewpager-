package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.frag.R;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SelectSchoolAdapter extends BaseAdapter {

	Context context;
	LayoutInflater layoutInflater;
	ArrayList<String> arrySchool;


	public SelectSchoolAdapter(Context context, ArrayList<String> arrySchool
			) {
		this.context = context;
		this.arrySchool = arrySchool;
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public int getCount() {
		// TODO Auto-generated method stub
		return arrySchool.size();
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
			convertView = layoutInflater.inflate(R.layout.item_ada_text, null);
			viewHolder = new ViewHolder();
			viewHolder.textView = (TextView) convertView
					.findViewById(R.id.provincename);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.textView.setText(arrySchool.get(position).toString());

		return convertView;
	}

	public static class ViewHolder {
		public TextView textView;
	}

}
